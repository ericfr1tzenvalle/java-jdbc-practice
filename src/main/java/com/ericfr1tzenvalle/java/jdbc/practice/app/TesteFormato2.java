package com.ericfr1tzenvalle.java.jdbc.practice.app;

import com.ericfr1tzenvalle.java.jdbc.practice.dao.IdeiaDAO;
import com.ericfr1tzenvalle.java.jdbc.practice.model.Ideia;
import com.ericfr1tzenvalle.java.jdbc.practice.utils.ConexaoFactory;

import java.util.List;

public class TesteFormato2 {
    public static void main(String[] args) {
        System.out.println("=====Executando metodo 2========");
        ConexaoFactory.criarTabelaSeNaoExistir();
        IdeiaDAO ideiaDao = IdeiaDAO.getInstance();

        // Inserindo dados
        Ideia ideia = new Ideia("Programar em java", "Realizar exercícios", 3);
        ideiaDao.inserir(ideia);
        Ideia ideia1 = new Ideia("Programar jogos", "Prototipar um jogo", 2);
        ideiaDao.inserir(ideia1);

        //Imprimindo ideias
        List<Ideia> ideias = ideiaDao.listar();
        for(Ideia i : ideias){
            System.out.println(i.toString());
        }

        //Excluindo primeira ideia
        ideiaDao.excluir(ideia);

        //Imprimindo novamente as ideias para mostrar exclusão
        ideias = ideiaDao.listar();
        for(Ideia i : ideias){
            System.out.println(i.toString());
        }
        //Alterar a segunda ideia
        ideia1.setTitulo("Novo titulo");
        ideiaDao.alterar(ideia1);

        //Imprimindo novamente a ideia para mostrar alteração
        Ideia ideiaBuscada = ideiaDao.buscar(2);
        System.out.println(ideiaBuscada.toString());
    }
}
