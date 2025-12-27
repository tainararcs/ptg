
-- Administrador (Senha: 3333)
INSERT INTO users (id, name, password, profile)
VALUES (1, 'Mariana', '1569984', 'admin');

-- Usuários comuns (Senha: 2222)
INSERT INTO users (id, name, password, profile)
VALUES 
    (2, 'Maria', '1539200', 'user'),
    (3, 'Marcela', '1539200', 'user');


-- Disciplinas
INSERT INTO subjects (id, name) 
VALUES
    (1, 'Matemática'), 
	(2, 'Português');


-- Matérias de matemática
INSERT INTO topics (id, name, bimester, grade, subjectid) 
VALUES 
    (1, 'Geometria', 'FIRST_BIMESTER', 'FIRST_GRADE', 1),
    (2, 'Progressão Aritmética', 'FIRST_BIMESTER', 'FIRST_GRADE', 1),
    (3, 'Progressão Geométrica', 'FIRST_BIMESTER', 'FIRST_GRADE', 1);

-- Matérias de Portugês
INSERT INTO topics (id, name, bimester, grade, subjectid) 
VALUES
    (4, 'Gêneros Textuais', 'FIRST_BIMESTER', 'FIRST_GRADE', 2),
    (5, 'Regência Verbal', 'FIRST_BIMESTER', 'FIRST_GRADE', 2),
    (6, 'Ortografia', 'FIRST_BIMESTER', 'FIRST_GRADE', 2);


-- Questões (7 por matéria)
INSERT INTO questions (id, statement, obs, subjectid, topicid) 
VALUES
    -- Geometria
    (1, 'Qual é a soma dos ângulos internos de um triângulo?', '', 1, 1),
    (2, 'Quantos lados tem um hexágono?', '', 1, 1),
    (3, 'A soma dos ângulos internos de um quadrilátero é:', '', 1, 1),
    (4, 'Qual é a área de um quadrado de lado 5 cm?', '', 1, 1),
    (5, 'O perímetro de um círculo é chamado de:', '', 1, 1),
    (6, 'Qual a fórmula da área do círculo?', '', 1, 1),
    (7, 'Um triângulo equilátero tem todos os ângulos iguais a:', '', 1, 1),

    -- Progressão Aritmética
    (8, 'Qual é a fórmula do termo geral de uma PA?', '', 1, 2),
    (9, 'A soma dos 10 primeiros termos da PA 2, 5, 8... é:', '', 1, 2),
    (10, 'Em uma PA, a diferença entre dois termos consecutivos é chamada de:', '', 1, 2),
    (11, 'O 5º termo da PA 3, 7, 11... é:', '', 1, 2),
    (12, 'A soma dos n primeiros termos de uma PA é dada por:', '', 1, 2),
    (13, 'Se o 1º termo é 1 e o 10º termo é 19, qual a razão?', '', 1, 2),
    (14, 'Uma PA é crescente se:', '', 1, 2),

    -- Progressão Aritmética
    (15, 'Qual é a fórmula do termo geral de uma PG?', '', 1, 3),
    (16, 'O produto dos 4 primeiros termos da PG 2, 6, 18... é:', '', 1, 3),
    (17, 'Em uma PG, a razão é o quociente entre:', '', 1, 3),
    (18, 'O 5º termo da PG 3, 6, 12... é:', '', 1, 3),
    (19, 'Uma PG é decrescente se a razão é:', '', 1, 3),
    (20, 'Se o 1º termo é 2 e a razão é 3, o 4º termo é:', '', 1, 3),
    (21, 'A soma dos termos de uma PG finita é dada por:', '', 1, 3),

    -- Gêneros Textuais
    (22, 'O que caracteriza um texto narrativo?', '', 2, 4),
    (23, 'Qual é o objetivo de um texto dissertativo-argumentativo?', '', 2, 4),
    (24, 'Um texto poético se diferencia pelo uso de:', '', 2, 4),
    (25, 'O que é um texto injuntivo?', '', 2, 4),
    (26, 'Qual gênero textual utiliza diálogos e personagens?', '', 2, 4),
    (27, 'O gênero carta é classificado como:', '', 2, 4),
    (28, 'Um texto publicitário tem como objetivo principal:', '', 2, 4),

    -- Regência Verbal
    (29, 'Assinale a frase correta quanto à regência verbal.', '', 2, 5),
    (30, 'O verbo assistir exige:', '', 2, 5),
    (31, 'O verbo obedecer exige:', '', 2, 5),
    (32, 'O verbo aspirar pode exigir:', '', 2, 5),
    (33, 'Assinale a frase correta: gostar de / gostar em', '', 2, 5),
    (34, 'O verbo intervir rege:', '', 2, 5),
    (35, 'O verbo aproximar-se rege:', '', 2, 5),

    -- Ortografia
    (36, 'Assinale a palavra com grafia correta:', '', 2, 6),
    (37, 'Qual destas palavras é escrita com “s”?', '', 2, 6),
    (38, 'Qual é a forma correta: conciente ou consciente?', '', 2, 6),
    (39, 'Assinale a palavra escrita corretamente:', '', 2, 6),
    (40, 'Escolha a palavra com uso correto de “ç”:', '', 2, 6),
    (41, 'Qual é a grafia correta de: “exceção/excessão”?', '', 2, 6),
    (42, 'A palavra “sociedade” está correta?', '', 2, 6);


-- Alternativas das questões
INSERT INTO options_question (correct, text, questionid) 
VALUES
    -- Geometria
        -- 1
        (TRUE, '180 graus', 1),
        (FALSE, '90 graus', 1),
        (FALSE, '360 graus', 1),
        (FALSE, '270 graus', 1),
        (FALSE, '200 graus', 1),
        -- 2
        (TRUE, '6', 2),
        (FALSE, '5', 2),
        (FALSE, '8', 2),
        (FALSE, '7', 2),
        (FALSE, '4', 2),
        -- 3
        (TRUE, '360 graus', 3),
        (FALSE, '180 graus', 3),
        (FALSE, '90 graus', 3),
        (FALSE, '270 graus', 3),
        (FALSE, '540 graus', 3),
        -- 4
        (TRUE, '25 cm²', 4),
        (FALSE, '20 cm²', 4),
        (FALSE, '10 cm²', 4),
        (FALSE, '15 cm²', 4),
        (FALSE, '30 cm²', 4),
        -- 5
        (TRUE, 'Circunferência', 5),
        (FALSE, 'Diâmetro', 5),
        (FALSE, 'Raio', 5),
        (FALSE, 'Perímetro', 5),
        (FALSE, 'Área', 5),
        -- 6
        (TRUE, 'π * r²', 6),
        (FALSE, '2 * π * r', 6),
        (FALSE, 'π * r', 6),
        (FALSE, 'r² * 2', 6),
        (FALSE, 'π * d', 6),
        -- 7
        (TRUE, '60 graus', 7),
        (FALSE, '45 graus', 7),
        (FALSE, '90 graus', 7),
        (FALSE, '120 graus', 7),
        (FALSE, '30 graus', 7),

    -- Progressão Aritmética
        -- 8
        (TRUE, 'an = a1 + (n-1) * r', 8),
        (FALSE, 'an = a1 * r^(n-1)', 8),
        (FALSE, 'an = n * a1', 8),
        (FALSE, 'an = a1 - (n-1) * r', 8),
        (FALSE, 'an = a1 ÷ r', 8),
        -- 9
        (TRUE, '65', 9),
        (FALSE, '60', 9),
        (FALSE, '55', 9),
        (FALSE, '50', 9),
        (FALSE, '70', 9),
        -- 10
        (TRUE, 'Razão', 10),
        (FALSE, 'Termo geral', 10),
        (FALSE, 'Soma', 10),
        (FALSE, 'Produto', 10),
        (FALSE, 'Diferença de quadrados', 10),
        -- 11
        (TRUE, '19', 11),
        (FALSE, '20', 11),
        (FALSE, '18', 11),
        (FALSE, '17', 11),
        (FALSE, '15', 11),
        -- 12
        (TRUE, 'S = n/2 * (a1 + an)', 12),
        (FALSE, 'S = n * a1 * r', 12),
        (FALSE, 'S = n * a1', 12),
        (FALSE, 'S = a1 + (n-1) * r', 12),
        (FALSE, 'S = n * r', 12),
        -- 13
        (TRUE, '2', 13),
        (FALSE, '1', 13),
        (FALSE, '3', 13),
        (FALSE, '4', 13),
        (FALSE, '5', 13),
        -- 14
        (TRUE, 'Quando a razão r > 0', 14),
        (FALSE, 'Quando r = 0', 14),
        (FALSE, 'Quando r < 0', 14),
        (FALSE, 'Quando a1 = 0', 14),
        (FALSE, 'Quando n > 10', 14),

    -- Progressão Geométrica
        -- 15
        (TRUE, 'an = a1 * r^(n-1)', 15),
        (FALSE, 'an = a1 + (n-1) * r', 15),
        (FALSE, 'an = n * a1', 15),
        (FALSE, 'an = a1 ÷ r^(n-1)', 15),
        (FALSE, 'an = r * n', 15),
        -- 16
        (TRUE, '648', 16),
        (FALSE, '216', 16),
        (FALSE, '432', 16),
        (FALSE, '324', 16),
        (FALSE, '256', 16),
        -- 17
        (TRUE, 'Quociente entre um termo e o anterior', 17),
        (FALSE, 'Diferença entre dois termos', 17),
        (FALSE, 'Soma dos termos', 17),
        (FALSE, 'Produto dos termos', 17),
        (FALSE, 'Quociente entre o primeiro e o último termo', 17),
        -- 18
        (TRUE, '48', 18),
        (FALSE, '36', 18),
        (FALSE, '24', 18),
        (FALSE, '60', 18),
        (FALSE, '72', 18),
        -- 19
        (TRUE, 'Se 0 < r < 1', 19),
        (FALSE, 'Se r > 1', 19),
        (FALSE, 'Se r = 1', 19),
        (FALSE, 'Se r < 0', 19),
        (FALSE, 'Se r = 0', 19),
        -- 20
        (TRUE, '54', 20),
        (FALSE, '48', 20),
        (FALSE, '36', 20),
        (FALSE, '60', 20),
        (FALSE, '42', 20),
        -- 21
        (TRUE, 'S = a1 * (r^n - 1)/(r-1)', 21),
        (FALSE, 'S = n * a1', 21),
        (FALSE, 'S = a1 + (n-1) * r', 21),
        (FALSE, 'S = a1 * r^n', 21),
        (FALSE, 'S = a1 / (r-1)', 21),

    -- Gêneros Textuais
        -- 22
        (TRUE, 'Apresenta personagens e uma sequência de eventos', 22),
        (FALSE, 'Defende uma ideia com argumentos', 22),
        (FALSE, 'Tem versos e ritmo', 22),
        (FALSE, 'Instrui o leitor', 22),
        (FALSE, 'Promove produtos ou serviços', 22),
        -- 23
        (TRUE, 'Defender uma opinião ou argumento', 23),
        (FALSE, 'Contar uma história', 23),
        (FALSE, 'Embelezar a linguagem', 23),
        (FALSE, 'Dar instruções', 23),
        (FALSE, 'Entreter com diálogo', 23),
        -- 24
        (TRUE, 'Uso de figuras de linguagem e ritmo', 24),
        (FALSE, 'Uso de gráficos', 24),
        (FALSE, 'Uso de argumentos', 24),
        (FALSE, 'Uso de comandos', 24),
        (FALSE, 'Uso de diálogos', 24),
        -- 25
        (TRUE, 'Texto que instrui ou orienta o leitor', 25),
        (FALSE, 'Texto narrativo', 25),
        (FALSE, 'Texto poético', 25),
        (FALSE, 'Texto dissertativo', 25),
        (FALSE, 'Texto publicitário', 25),
        -- 26
        (TRUE, 'Narrativo', 26),
        (FALSE, 'Dissertativo', 26),
        (FALSE, 'Poético', 26),
        (FALSE, 'Injuntivo', 26),
        (FALSE, 'Carta', 26),
        -- 27
        (TRUE, 'Carta', 27),
        (FALSE, 'Poema', 27),
        (FALSE, 'Crônica', 27),
        (FALSE, 'Relatório', 27),
        (FALSE, 'Narrativa', 27),
        -- 28
        (TRUE, 'Persuadir ou vender algo', 28),
        (FALSE, 'Ensinar', 28),
        (FALSE, 'Narrar', 28),
        (FALSE, 'Argumentar', 28),
        (FALSE, 'Descrever', 28),

    -- Regência Verbal
        -- 29
        (TRUE, 'Ele assistiu ao filme corretamente', 29),
        (FALSE, 'Ele assistiu o filme', 29),
        (FALSE, 'Ele assistir o filme', 29),
        (FALSE, 'Ele assistiu de filme', 29),
        (FALSE, 'Ele assistiu filme', 29),
        -- 30
        (TRUE, 'Assistir a', 30),
        (FALSE, 'Assistir de', 30),
        (FALSE, 'Assistir em', 30),
        (FALSE, 'Assistir com', 30),
        (FALSE, 'Assistir por', 30),
        -- 31
        (TRUE, 'Obedecer a', 31),
        (FALSE, 'Obedecer de', 31),
        (FALSE, 'Obedecer em', 31),
        (FALSE, 'Obedecer com', 31),
        (FALSE, 'Obedecer por', 31),
        -- 32
        (TRUE, 'Aspirar a / Aspirar por', 32),
        (FALSE, 'Aspirar em', 32),
        (FALSE, 'Aspirar de', 32),
        (FALSE, 'Aspirar com', 32),
        (FALSE, 'Aspirar no', 32),
        -- 33
        (TRUE, 'Gosto de chocolate', 33),
        (FALSE, 'Gosto em chocolate', 33),
        (FALSE, 'Gosto com chocolate', 33),
        (FALSE, 'Gosto sobre chocolate', 33),
        (FALSE, 'Gosto por chocolate', 33),
        -- 34
        (TRUE, 'Intervir em', 34),
        (FALSE, 'Intervir a', 34),
        (FALSE, 'Intervir de', 34),
        (FALSE, 'Intervir com', 34),
        (FALSE, 'Intervir sobre', 34),
        -- 35
        (TRUE, 'Aproximar-se de', 35),
        (FALSE, 'Aproximar-se a', 35),
        (FALSE, 'Aproximar-se em', 35),
        (FALSE, 'Aproximar-se por', 35),
        (FALSE, 'Aproximar-se com', 35),

    -- Ortografia
        -- 36
        (TRUE, 'Consciente', 36),
        (FALSE, 'Conciente', 36),
        (FALSE, 'Consiente', 36),
        (FALSE, 'Consciente', 36),
        (FALSE, 'Consciennte', 36),
        -- 37
        (TRUE, 'Casa', 37),
        (FALSE, 'Caça', 37),
        (FALSE, 'Caza', 37),
        (FALSE, 'Cassa', 37),
        (FALSE, 'Kasa', 37),
        -- 38
        (TRUE, 'Consciente', 38),
        (FALSE, 'Conciente', 38),
        (FALSE, 'Consiente', 38),
        (FALSE, 'Consciente', 38),
        (FALSE, 'Consciennte', 38),
        -- 39
        (TRUE, 'Exceção', 39),
        (FALSE, 'Excessão', 39),
        (FALSE, 'Exseção', 39),
        (FALSE, 'Exceçāo', 39),
        (FALSE, 'Excesão', 39),
        -- 40
        (TRUE, 'Atenção', 40),
        (FALSE, 'Atenssão', 40),
        (FALSE, 'Atencão', 40),
        (FALSE, 'Atencião', 40),
        (FALSE, 'Atensião', 40),
        -- 41
        (TRUE, 'Exceção', 41),
        (FALSE, 'Excessão', 41),
        (FALSE, 'Exseção', 41),
        (FALSE, 'Exceçāo', 41),
        (FALSE, 'Excesão', 41),
        -- 42
        (TRUE, 'Sim', 42),
        (FALSE, 'Não', 42),
        (FALSE, 'Talvez', 42),
        (FALSE, 'Nunca', 42),
        (FALSE, 'Errado', 42);


-- Testes 
INSERT INTO tests (id, userid, date, bimester, numbercorrectanswers, subjectid) 
VALUES
    (1, 2, '2025-12-01', 'FIRST_BIMESTER', 3, 1),
    (2, 3, '2025-12-05', 'FIRST_BIMESTER', 5, 2);

INSERT INTO questions_test (testid, questionid) 
VALUES
    (1, 1),
    (1, 2),
    (1, 3),
    (1, 4),
    (2, 22),
    (2, 23),
    (2, 24), 
    (2, 25);