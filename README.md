# Java JDBC Practice

Este repositório é um projeto de prática para demonstrar a persistência de dados em Java utilizando JDBC. O projeto explora duas abordagens diferentes para a manipulação de dados com um banco de dados H2 em memória: o padrão de projeto **Data Access Object (DAO)** e o padrão **Active Record**.

## Padrões de Persistência Implementados

O objetivo principal é comparar duas formas de estruturar a camada de persistência de dados em uma aplicação Java.

### 1. Active Record

Nesta abordagem, o próprio objeto de modelo é responsável por sua persistência. A lógica de acesso ao banco de dados está contida dentro da própria classe de modelo.

- **Modelo Principal:** `IdeiaActiveRecord.java` (no pacote `...model`)
- **Exemplo de Uso:** `TesteFormato1.java` (no pacote `...app`)

### 2. Data Access Object (DAO)

Aqui, a responsabilidade de persistência é separada do objeto de modelo. Uma classe DAO é criada para centralizar todas as operações de acesso a dados, promovendo uma melhor separação de responsabilidades.

- **Interface Genérica:** `DAO.java` (no pacote `...dao`)
- **Implementação do DAO:** `IdeiaDAO.java` (no pacote `...dao`)
- **Modelo (POJO):** `Ideia.java` (no pacote `...model`)
- **Exemplo de Uso:** `TesteFormato2.java` (no pacote `...app`)

## Tecnologias Utilizadas

- **Java:** Linguagem de programação principal.
- **JDBC (Java Database Connectivity):** API padrão do Java para conexão com bancos de dados.
- **H2 Database Engine:** Um banco de dados relacional em memória, escrito em Java, ideal para testes e desenvolvimento rápido.
- **Maven:** Ferramenta para gerenciamento de dependências e automação de build do projeto.

## Estrutura do Projeto

```
├── pom.xml
└── src
    └── main
        └── java
            └── com
                └── ericfr1tzenvalle
                    └── java
                        └── jdbc
                            └── practice
                                ├── app      // Classes para testar as implementações
                                ├── dao      // Classes do padrão DAO
                                ├── model    // Classes de modelo (POJO e Active Record)
                                └── utils    // Classes utilitárias (conexão com BD)
```

## Como Executar

1. **Clone o repositório:**
    ```bash
    git clone https://github.com/ericfr1tzenvalle/java-jdbc-practice.git
    ```

2. **Compile e execute com o Maven:**

    O projeto está configurado para ser executado via Maven. Você pode executar as classes de teste (`TesteFormato1` ou `TesteFormato2`) diretamente da sua IDE (como NetBeans, IntelliJ ou Eclipse).

    Para executar a classe principal definida no `pom.xml`, use:
    ```bash
    mvn exec:java
    ```
