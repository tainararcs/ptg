package br.trcs.ptg.logic;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Component;

import br.trcs.ptg.dao.TestDAO;
import br.trcs.ptg.model.Test;
import br.trcs.ptg.model.User;
import br.trcs.ptg.utils.Consts;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

/**
 * Componente Spring responsável por gerar um relatório em PDF dos testes realizados.
 * Implementa a inteface {@link Logic}.
 */
@Component(Consts.EXPORT_PDF_LOGIC)
public class ExportPDF implements Logic {

    // Constantes de formatação do PDF.
    private static final float MARGIN = 50;
    private static final float START_Y = 780;
    private static final float ROW_HEIGHT = 20;
    private static final float FONT_SIZE = 10;

    private static final String[] HEADER = {"Data", "Usuário", "Disciplina", "Bimestre", "Acertos", "%"};
    private static final float[] COL_X = {50, 120, 220, 300, 410, 480};

    /**
     * Método principal que processa a requisição HTTP para exportar os testes em PDF.
     * Recupera o usuário logado, consulta os testes correspondentes e monta um PDF.
     * 
     * @param request  HttpServletRequest para obter o usuário logado da sessão.
     * @param response HttpServletResponse para enviar o PDF como anexo.
     * @return null, pois o PDF é escrito diretamente no {@link HttpServletResponse}.
     * 
     * @throws IOException em caso de erro durante a criação ou escrita do PDF.
     */
    @Override
    public String service(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // Recupera usuário logado da sessão.
        User userLogged = (User) request.getSession().getAttribute(Consts.USER_LOGGED);
        if (userLogged == null) {
            // Redireciona para login se não houver usuário logado.
            response.sendRedirect("login");
            return null;
        }

        // Consulta testes do banco de dados.
        TestDAO dao = new TestDAO();
        List<Test> tests = "admin".equalsIgnoreCase(userLogged.getProfile()) 
                            ? dao.searchAllTests() 
                            : dao.searchUserTests(userLogged);

        // Configura resposta HTTP como PDF.
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=relatorio-testes.pdf");

        // Criação do documento PDF.
        try (PDDocument doc = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            doc.addPage(page);

            PDPageContentStream cs = new PDPageContentStream(doc, page);
            float y = START_Y;

            // Título do relatório.
            cs.beginText();
            cs.setFont(PDType1Font.HELVETICA_BOLD, 16);
            cs.newLineAtOffset(MARGIN, y);
            cs.showText("Relatório de Testes");
            cs.endText();
            y -= 40;

            // Caso não haja testes.
            if (tests == null || tests.isEmpty()) {
                cs.beginText();
                cs.setFont(PDType1Font.HELVETICA, 12);
                cs.newLineAtOffset(MARGIN, y);
                cs.showText("Nenhum teste realizado.");
                cs.endText();

                cs.close();
                doc.save(response.getOutputStream());
                return null;
            }

            // Desenha cabeçalho.
            y = drawLine(cs, y, HEADER, PDType1Font.HELVETICA_BOLD);

            // Preenche linhas com informações dos testes.
            for (Test t : tests) {
                if (y < 80) { // Nova página caso chegue ao fim da página.
                    cs.close();
                    page = new PDPage(PDRectangle.A4);
                    doc.addPage(page);
                    cs = new PDPageContentStream(doc, page);
                    y = drawLine(cs, START_Y, HEADER, PDType1Font.HELVETICA_BOLD);
                }

                String[] row = {
                    t.getDate() != null ? t.getDate().toString() : "",
                    t.getUserID().getName(),
                    t.getSubjectId().getName(),
                    t.getBimester().toString(),
                    String.valueOf(t.getNumberCorrectAnswers()),
                    (t.getNumberCorrectAnswers() * 100 / Consts.NUMBER_QUESTIONS) + "%"
                };

                y = drawLine(cs, y, row, PDType1Font.HELVETICA);
            }

            cs.close();
            doc.save(response.getOutputStream());
        }

        return null;
    }

    /**
     * Desenha uma linha de texto no PDF.
     * Cada elemento do array {@code data} é posicionado nas colunas definidas por {@link #COL_X}.
     * 
     * @param cs   PDPageContentStream para escrever no PDF
     * @param y    posição vertical inicial
     * @param data array de strings a serem exibidas na linha
     * @param font fonte utilizada para a linha
     * @return nova posição vertical após desenhar a linha
     * 
     * @throws IOException em caso de erro durante a escrita no PDF
     */
    private float drawLine(PDPageContentStream cs, float y, String[] data, PDType1Font font) throws IOException {
        cs.setFont(font, FONT_SIZE);

        for (int i = 0; i < data.length; i++) {
            cs.beginText();
            cs.newLineAtOffset(COL_X[i], y);
            cs.showText(data[i] != null ? data[i] : "");
            cs.endText();
        }

        return y - ROW_HEIGHT;
    }
}