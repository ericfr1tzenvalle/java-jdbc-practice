/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ericfr1tzenvalle.java.jdbc.practice.model;

import java.util.Objects;

/**
 *
 * @author Éric
 */
public class Ideia {
    private String titulo;
    private String descricao;
    private int urgencia;
    private Integer id; // Usamos Integer para permitir que seja nulo antes de persistir
    
     // Construtor para quando criamos uma ideia que ainda não foi para o BD
    public Ideia(String titulo, String descricao, int urgencia){
        this.titulo = titulo;
        this.descricao = descricao;
        setUrgencia(urgencia);
    }
    // Construtor para quando lemos uma ideia que já veio do BD (com ID)
    public Ideia(Integer id,String titulo, String descricao, int urgencia){
        this(titulo,descricao,urgencia);
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public void setId(int id){
        this.id = id;
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
        if (urgencia < 1 || urgencia > 5) {
            throw new IllegalArgumentException("A urgência deve ser um valor entre 1 e 5.");
        }
        this.urgencia = urgencia;
    }
    public int getId(){
        return id;
    }
    @Override
    public String toString(){
        return "Id: " + id + "\n - Titulo: " + titulo + "\n - Descrição: " + descricao + "\n - Urgencia: " + urgencia;
    }
    @Override
    public boolean equals(Object o){
        if(this == null) return false;
        if(o.getClass() != getClass() || o == null) return false;
        Ideia i = (Ideia) o;
        return Objects.equals(i.getId(), id); // É comparado pelo ID
        
    }
    @Override
    public int hashCode(){
        return Objects.hash(id);
    }
    
}
