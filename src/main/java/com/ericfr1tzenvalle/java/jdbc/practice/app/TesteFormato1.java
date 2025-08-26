/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.ericfr1tzenvalle.java.jdbc.practice.app;

import com.ericfr1tzenvalle.java.jdbc.practice.model.IdeiaActiveRecord;
import com.ericfr1tzenvalle.java.jdbc.practice.utils.ConexaoFactory;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Éric
 */
public class TesteFormato1 {

    public static void main(String[] args) {
        System.out.println("==== Teste formato [1] ====");
        ConexaoFactory.criarTabelaSeNaoExistir();
        // Criamos a tabela
        System.out.println("Iniciando teste com ACTIVE RECORD ");
        
        System.out.println("Inserindo ideias....");
        IdeiaActiveRecord ideia1 = new IdeiaActiveRecord("Estudar java",
                "Estudo detalhado dos conceitos da linguagem", 3);
        ideia1.inserir();
        
        IdeiaActiveRecord ideia2 = new IdeiaActiveRecord("Estudar python",
                "Aprender sobre machine learning", 1);
        ideia2.inserir();
        
        System.out.println("Listando ideias salvas.");
        List<IdeiaActiveRecord> ideias = IdeiaActiveRecord.listar();
        ideias.forEach(System.out::println);
        
        System.out.println("Deletando a primeira ideia.");
        ideia1.deletar();
        
        System.out.println("Listando novamente para mostrar exclusão");
        ideias = IdeiaActiveRecord.listar();
        ideias.forEach(System.out::println);
        
        System.out.println("Teste [Active Record] finalizado");
    }
}
