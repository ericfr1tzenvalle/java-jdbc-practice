/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ericfr1tzenvalle.java.jdbc.practice.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Luísa
 */
public class ConexaoFactory {
    private static final String URL = "jdbc:h2:mem:ideiasdb;DB_CLOSE_DELAY=-1"; // URL DO BANCO
    private static final String USUARIO = "sa"; // Usuario do banco
    private static final String SENHA = ""; // Senha
    
    static{
        try{
            Class.forName("org.h2.Driver"); // Aqui dizemos pra carregar o Driver do H2.
        } catch (ClassNotFoundException ex) {
             // Lançar uma exceção de tempo de execução é fundamental aqui,
            // pois a aplicação não pode funcionar sem o driver.
           throw new RuntimeException("Driver JDBC do H2 não encontrado.");
        }
    }
    
    
    /**
     * Obtém uma nova conexão com o banco de dados.
     * @return um objeto Connection.
     * @throws SQLException se ocorrer um erro ao obter a conexão.
     */
    
    public static Connection getConexao() throws SQLException {
         return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
    /**
     * Método utilitário para criar a tabela de ideias se ela não existir.
     * Chamado uma vez no início da aplicação.
     */
    public static void criarTabelaSeNaoExistir() {
        String sql = "CREATE TABLE IF NOT EXISTS ideia ("
                   + "id INT AUTO_INCREMENT PRIMARY KEY,"
                   + "titulo VARCHAR(255) NOT NULL,"
                   + "descricao TEXT,"
                   + "urgencia INT NOT NULL"
                   + ");";

        try (Connection conn = getConexao();
            Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabela 'ideia' verificada/criada com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar a tabela 'ideia'.", e);
        }
    }
}
