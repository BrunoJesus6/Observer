package org.example;

import java.util.Observable;
import java.util.Observer;

public class Usuario implements Observer {

    private String nome;
    private String ultimaNotificacao;

    public Usuario(String nome) {
        this.nome = nome;
    }

    public String getUltimaNotificacao() {
        return this.ultimaNotificacao;
    }

    public void seguirGerenciador(GerenciadorDeTarefas gerenciador) {
        gerenciador.addObserver(this);
    }

    @Override
    public void update(Observable gerenciador, Object arg1) {
        this.ultimaNotificacao = this.nome + ", a tarefa foi atualizada: " + gerenciador.toString();
    }
}