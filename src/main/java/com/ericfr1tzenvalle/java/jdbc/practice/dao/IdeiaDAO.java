package com.ericfr1tzenvalle.java.jdbc.practice.dao;

import com.ericfr1tzenvalle.java.jdbc.practice.model.Ideia;
import com.ericfr1tzenvalle.java.jdbc.practice.utils.ConexaoFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IdeiaDAO implements DAO<Ideia>{

    private static IdeiaDAO instance;
    public static IdeiaDAO getInstance(){ // Implementação de singleton
        if(instance == null){
            instance = new IdeiaDAO();
        }
        return instance;
    }


    @Override
    public void inserir(Ideia obj) {
        String sql = "INSERT INTO ideia (titulo, descricao, urgencia) VALUES (?, ?, ?)";
        try(Connection conn = ConexaoFactory.getConexao();
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            stmt.setString(1,obj.getTitulo());
            stmt.setString(2,obj.getDescricao());
            stmt.setInt(3,obj.getUrgencia());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if(rs.next()){
                obj.setId(rs.getInt(1));
            }
            System.out.println("IDEIA inserido com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao inserir IDEIA: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Ideia> listar() {
        List<Ideia> ideias = new ArrayList<>();
        String sql = "SELECT * FROM ideia";
        try(Connection conn = ConexaoFactory.getConexao();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String titulo = rs.getString("titulo");
                String descricao = rs.getString("descricao");
                int urgencia = rs.getInt("urgencia");
                ideias.add(new Ideia(id, titulo, descricao, urgencia));

            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar ideias: " + e.getMessage());
            throw new RuntimeException(e);
        }
        return ideias;
    }

    @Override
    public void excluir(Ideia obj) {
        String sql = "DELETE FROM ideia WHERE id = ?";
        try(Connection conn = ConexaoFactory.getConexao();
            PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setInt(1,obj.getId());
            stmt.executeUpdate();
            System.out.println("IDEIA excluido com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao excluir IDEIA: " + e.getMessage());
            throw new RuntimeException(e);
        }

    }

    @Override
    public void alterar(Ideia obj) {
        if(buscar(obj.getId()) == null) {
            return;
        }
        String sql = "UPDATE ideia SET titulo = ?, descricao = ?, urgencia = ? WHERE id = ?";
        try(Connection conn = ConexaoFactory.getConexao();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(4, obj.getId());
            pstmt.setString(1, obj.getTitulo());
            pstmt.setString(2, obj.getDescricao());
            pstmt.setInt(3, obj.getUrgencia());
            pstmt.executeUpdate();
            System.out.println("IDEIA alterado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao alterar IDEIA: " + e.getMessage());
            throw new RuntimeException(e);
        }


    }

    @Override
    public Ideia buscar(int id) {
        Ideia ideia = null;
        String sql = "SELECT * FROM ideia WHERE id = ?";
        try(Connection conn = ConexaoFactory.getConexao();
        PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setInt(1,id);
            try(ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int idBanco = rs.getInt(1);
                    if(id == idBanco){
                        String titulo = rs.getString("titulo");
                        String descricao = rs.getString("descricao");
                        int urgencia = rs.getInt("urgencia");
                        ideia = new Ideia(id, titulo, descricao, urgencia);

                    }
                }
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ideia;
    }
}
