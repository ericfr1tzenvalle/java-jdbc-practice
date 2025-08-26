/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ericfr1tzenvalle.java.jdbc.practice.model;

/**
 *
 * @author Éric
 */
public class Ideia {
    private String titulo;
    private String descricao;
    private int urgencia;
    private static int id = 0;
    
    public Ideia(String titulo, String descricao){
        id++;
        this.titulo = titulo;
        this.descricao = descricao;
    }
    public Ideia(String titulo, String descricao, int urgencia){
        this(titulo,descricao);
        this.urgencia = urgencia;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getUrgencia() {
        return urgencia;
    }

    public void setUrgencia(int urgencia) {
        this.urgencia = urgencia;
    }
    // 1. Metodo
    
    
    
}
