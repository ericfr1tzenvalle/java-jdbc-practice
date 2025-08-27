package com.ericfr1tzenvalle.java.jdbc.practice.dao;

import java.util.List;

public interface DAO<T> {
    public void inserir(T obj);
    public List<T> listar();
    public void excluir(T obj);
    public void alterar(T obj);
    public T buscar(int id);

}
