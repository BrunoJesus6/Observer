package org.example;

import java.util.Observable;

public class GerenciadorDeTarefas extends Observable {

    private String tarefa;

    public GerenciadorDeTarefas(String tarefa) {
        this.tarefa = tarefa;
    }

    public void atualizarTarefa(String novaTarefa) {
        this.tarefa = novaTarefa;
        setChanged();
        notifyObservers();
    }

    @Override
    public String toString() {
        return "Tarefa atual: " + tarefa;
    }
}