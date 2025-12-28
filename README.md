# Practice Test Generator (PTG)


O PTG é um **sistema web** para a criação e administração de testes (provas) práticas para usuários/estudantes. 

Ele foi idealizado para suportar:

- Criação e edição de questões e provas.
- Associação de provas a grupos de usuários.
- Execução de provas através de interface web.
- Armazenamento de resultados e relatórios.

Este projeto é construído como **aplicação web tradicional “WAR”** empacotável em um servidor de aplicações compatível com Jakarta EE (ex.: Tomcat).


### - Funcionalidades Principais 

#### Gerenciamento de Questões
- Cadastro completo de questões com múltiplas alternativas
- Definição de resposta correta e observações explicativas
- Categorização e organização de questões por matéria

#### Criação e Administração de Provas
- Montagem de provas selecionando questões do banco
- Associação de provas a grupos específicos de usuários

#### Execução de Provas
- Interface web intuitiva para realização de testes
- Navegação entre questões durante a prova
- Salvamento automático de respostas
- Submissão e finalização de provas

#### Relatórios e Análises
- Correção automática com pontuação
- Histórico de desempenho por usuário
- Relatórios estatísticos por prova
- Exportação de resultados em PDF

#### Gerenciamento de Usuários
- Autenticação e controle de acesso
- Organização de usuários (administrador e usuário comum)


### - Tecnologias Utilizadas

#### Backend

| Tecnologia | Versão | Descrição |
|------------|--------|-----------|
| **Java** | 22 | Linguagem base do projeto |
| Java Servlet API | 6.0.0 | Especificação para aplicações web Java |
| **Spring Web MVC** | 7.0.1 | Framework MVC para desenvolvimento web |
| Hibernate ORM | 6.2.7 | Framework de persistência JPA |
| Jakarta Persistence API | 3.2.0 | Especificação para persistência de dados |
| Hibernate Commons Annotations | 6.0.6 | Anotações comuns do Hibernate |
| Spring Validation | 3.1.2 | Validação de beans usando Spring (via spring-boot-starter-validation) |
| EL API | 5.0.1 | Expressões para JSP/Servlet |
| JSP API | 3.1.1 | Servlets Java Server Pages |

#### Banco de Dados

| Tecnologia | Versão | Descrição |
|------------|--------|-----------|
| **PostgreSQL** | 12+ | Sistema gerenciador de banco de dados |
| PostgreSQL JDBC Driver | 42.7.4 | Driver de conexão JDBC |

#### Frontend

| Tecnologia | Versão | Descrição |
|------------|--------|-----------|
| **JSTL** | 3.0.1 | Tag library para JSP |
| **CSS3** | - | Estilização das páginas |
| **JavaScript** | ES6+ | Interatividade no cliente |

#### Utilitários

| Tecnologia | Versão | Descrição |
|------------|--------|-----------|
| **Jackson Databind** | 2.17.0 | Processamento JSON |
| **Apache PDFBox** | 2.0.30 | Geração de relatórios PDF |


**Build e empacotamento:** Maven com `maven-compiler-plugin` e `maven-war-plugin` configurados para gerar um `WAR`.


### - Pré‑requisitos

Antes de compilar e executar o PTG localmente, certifique-se de ter instalado e configurado:

- **JDK 22**  
- **Apache Maven 3.8+**  
- **Servidor de aplicações Java** (ex.: Apache Tomcat 10+)  
- **PostgreSQL 12+**  
- **Driver JDBC PostgreSQL**  


### - Estrutura de Pastas

```
ptg/
├── .settings/                  # Configurações do Eclipse IDE
├── build/                      # Arquivos compilados
│   └── classes/
│       └── META-INF/
├── src/
│   └── main/
│       ├── java/              # Código-fonte Java
│       │   └── com/ptg/
│       │       ├── controller/    # Controladores Spring MVC
│       │       ├── dao/           # Camada de acesso a dados
│       │       ├── filter/        # Filtro web
│       │       ├── logic/         # Lógica de negócios
│       │       ├── model/         # Entidades JPA
│       │       ├── config/        # Configurações da aplicação
│       │       └── utils/         # Classes utilitárias
│       └── webapp/            # Recursos web
│           ├── WEB-INF/
│           │   ├── views/        # Páginas JSP
│           │   ├── web.xml       # Descritor de deployment
│           │   └── spring-config.xml
│           └── resources/
│               ├── css/
│               ├── js/
│               └── img/
├── target/                    # Artefatos gerados pelo Maven
├── .classpath                 # Configuração do classpath
├── .project                   # Configuração do projeto Eclipse
├── pom.xml                    # Configuração Maven
└── README.md                  # Documentação (este arquivo)
```


### - Arquitetura do Sistema

O PTG segue o padrão arquitetural **MVC (Model-View-Controller)** com separação clara de responsabilidades:

```
┌─────────────────────────────────────────────────────────────┐
│                         CLIENTE                             │
│                    (Navegador Web)                          │
└────────────────────────┬────────────────────────────────────┘
                         │ HTTP Request
                         ▼
┌─────────────────────────────────────────────────────────────┐
│                    PRESENTATION LAYER                       │
│  ┌────────────────────────────────────────────────────────┐ │
│  │           Controller (Spring MVC)                      │ │
│  │  • Recebe requisições HTTP                             │ │
│  │  • Valida dados de entrada                             │ │
│  │  • Delega processamento ao Service                     │ │
│  │  • Retorna View apropriada                             │ │
│  └────────────┬───────────────────────────┬───────────────┘ │
└───────────────┼───────────────────────────┼─────────────────┘
                │                           │
                ▼                           ▼
┌───────────────────────────┐   ┌──────────────────────────────┐
│      BUSINESS LAYER       │   │        VIEW LAYER            │
│  ┌─────────────────────┐  │   │  ┌────────────────────────┐  │
│  │  Service            │  │   │  │  JSP Templates         │  │
│  │  • Regras de        │  │   │  │  • Renderiza HTML      │  │
│  │    negócio          │  │   │  │  • JSTL para lógica    │  │
│  │  • Transações       │  │   │  │  • CSS/JS para UI      │  │
│  │  • Validações       │  │   │  └────────────────────────┘  │
│  │    complexas        │  │   └──────────────────────────────┘
│  └──────────┬──────────┘  │
└─────────────┼──────────────┘
              │
              ▼
┌─────────────────────────────────────────────────────────────┐
│                    PERSISTENCE LAYER                        │
│  ┌────────────────────────────────────────────────────────┐ │
│  │           Repository (Spring Data JPA)                 │ │
│  │  • Acesso ao banco de dados                            │ │
│  │  • Queries personalizadas                              │ │
│  │  • Mapeamento objeto-relacional                        │ │
│  └────────────┬───────────────────────────────────────────┘ │
└───────────────┼──────────────────────────────────────────────┘
                │
                ▼
┌─────────────────────────────────────────────────────────────┐
│                      DATA LAYER                             │
│  ┌────────────────────────────────────────────────────────┐ │
│  │           PostgreSQL Database                          │ │
│  │  • Armazenamento persistente                           │ │
│  │  • Integridade referencial                             │ │
│  │  • Transações ACID                                     │ │
│  └────────────────────────────────────────────────────────┘ │
└─────────────────────────────────────────────────────────────┘
```


### - Instalação e Configuração

#### 1. Clone o Repositório
#### 2. Configure o servidor Tomcat
#### 3. Crie um banco de dados PostgreSQL 
```
CREATE DATABASE ptg; 
```
#### 4. Configure as Propriedades da Aplicação no arquivo `persistence.xml`:
```
URL: jdbc:postgresql://localhost:5432/ptg
Usuário: <seu_usuario>
Senha: <sua_senha>
```
#### 5. Execute o sql abaixo:
```
CREATE TABLE users (
    id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(80) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    profile VARCHAR(30) NOT NULL
);

-- Administrador (Senha: 3333)
INSERT INTO users (name, password, profile)
VALUES ('<nome-de-sua-escolha>', '1569984', 'admin');
```
#### 6. Execute o Projeto 
#### 7. Acesse a Aplicação (http://localhost:8080/PraticeTestGenerator)

|      USERNAME      |  SENHA  |
|--------------------|---------|
|  <nome-escolhido>  |  3333   |

#### 6. Certifique-se de que as tabelas foram criadas pelo Hibernate
#### 7. No terminal ou pgAdmin, execute o arquivo `database/backup.sql`:
```
psql -U <seu_usuario> -d ptg -f path/para/backup.sql
```


### - Fluxo de uma Requisição

1. **Cliente** faz uma requisição HTTP (ex: submeter uma prova)
2. **Controller** recebe a requisição e valida os dados
3. **Logic/Service** aplica regras de negócio (ex: calcular pontuação)
4. **Repository** persiste/recupera dados do banco
5. **Model** representa as entidades (Question, Test, User, Result)
6. **View** renderiza a resposta (JSP) e envia ao cliente
