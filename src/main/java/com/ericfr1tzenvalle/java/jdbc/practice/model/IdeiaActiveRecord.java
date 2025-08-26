/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ericfr1tzenvalle.java.jdbc.practice.model;

import com.ericfr1tzenvalle.java.jdbc.practice.utils.ConexaoFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Éric
 */
public class IdeiaActiveRecord extends Ideia {
    
    public IdeiaActiveRecord(int id, String titulo, String descricao, int urgencia) {
        super(id, titulo, descricao, urgencia);
    }
     public IdeiaActiveRecord(String titulo, String descricao, int urgencia) {
        super(titulo, descricao, urgencia);
    }
     
     
    /**
     * Salva o objeto atual no banco de dados.
     * Se o objeto não tem ID, insere um novo registro.
     * (Poderia ser estendido para atualizar um existente se tivesse ID).
     */
     public void inserir(){
         String sql = "INSERT INTO ideia (titulo,descricao,urgencia) VALUES (?,?,?)";
         try(Connection conn = ConexaoFactory.getConexao();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
             pstmt.setString(1,this.getTitulo());
             pstmt.setString(2, this.getDescricao());
             pstmt.setInt(3, this.getUrgencia());
             
             pstmt.executeUpdate();
             
             try(ResultSet generatedKeys = pstmt.getGeneratedKeys()){
                 if(generatedKeys.next()){
                     this.setId(generatedKeys.getInt(1));
                 }
             }
             System.out.println("Ideia '" + this.getTitulo() + "' inserida com sucesso! ID: " + this.getId());
         } catch (SQLException ex) {
             System.out.println("Erro ao inserir a ideia: " + ex.getMessage());
        }
     }
     
      /**
     * Deleta do banco de dados o registro correspondente a este objeto (pelo ID).
     */
     public void deletar(){
        
         String sql = "DELETE FROM ideia WHERE id = ?";
         try(Connection conn = ConexaoFactory.getConexao();
              PreparedStatement pstmt = conn.prepareStatement(sql)){
             pstmt.setInt(1, this.getId());
             
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows > 0) {
                 System.out.println("Ideia com ID " + this.getId() + " deletada com sucesso.");
            } else {
                 System.out.println("Nenhuma ideia encontrada com o ID " + this.getId());
            }
             
         } catch (SQLException ex) {
            System.getLogger(IdeiaActiveRecord.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
     }
       /**
     * Método estático para buscar todas as ideias no banco de dados.
     * @return Uma lista de objetos IdeiaActiveRecord.
     */
     public List<IdeiaActiveRecord> listar(){
         List<IdeiaActiveRecord> ideias = new ArrayList<>();
         String sql = "SELECT * FROM ideia";
         try(Connection conn = ConexaoFactory.getConexao();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)){
             while(rs.next()){
                 int id = rs.getInt("id");
                 String titulo = rs.getString("titulo");
                 String descricao = rs.getString("descricao");
                 int urgencia = rs.getInt("urgencia");
                 // Cria o objeto e adiciona na lista
                 ideias.add(new IdeiaActiveRecord(id,titulo,descricao,urgencia));
             }
             
         
     }  catch (SQLException ex) {
            System.getLogger(IdeiaActiveRecord.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
         return ideias;
     }
    
}
