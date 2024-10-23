package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioTest {

    @Test
    void deveNotificarUmUsuario() {
        GerenciadorDeTarefas gerenciador = new GerenciadorDeTarefas("Fazer relatório");
        Usuario usuario = new Usuario("Alice");
        usuario.seguirGerenciador(gerenciador);

        gerenciador.atualizarTarefa("Fazer relatório e enviar para revisão");

        assertEquals("Alice, a tarefa foi atualizada: Tarefa atual: Fazer relatório e enviar para revisão", usuario.getUltimaNotificacao());
    }

    @Test
    void deveNotificarUsuarios() {
        GerenciadorDeTarefas gerenciador = new GerenciadorDeTarefas("Fazer relatório");
        Usuario usuario1 = new Usuario("Alice");
        Usuario usuario2 = new Usuario("Bob");
        usuario1.seguirGerenciador(gerenciador);
        usuario2.seguirGerenciador(gerenciador);

        gerenciador.atualizarTarefa("Fazer relatório e enviar para revisão");

        assertEquals("Alice, a tarefa foi atualizada: Tarefa atual: Fazer relatório e enviar para revisão", usuario1.getUltimaNotificacao());
        assertEquals("Bob, a tarefa foi atualizada: Tarefa atual: Fazer relatório e enviar para revisão", usuario2.getUltimaNotificacao());
    }

    @Test
    void naoDeveNotificarUsuario() {
        GerenciadorDeTarefas gerenciador = new GerenciadorDeTarefas("Fazer relatório");
        Usuario usuario = new Usuario("Alice");

        gerenciador.atualizarTarefa("Fazer relatório e enviar para revisão");

        assertEquals(null, usuario.getUltimaNotificacao());
    }

    @Test
    void deveNotificarUsuarioGerenciadorDiferente() {
        GerenciadorDeTarefas gerenciadorA = new GerenciadorDeTarefas("Fazer relatório");
        GerenciadorDeTarefas gerenciadorB = new GerenciadorDeTarefas("Preparar apresentação");
        Usuario usuario1 = new Usuario("Alice");
        Usuario usuario2 = new Usuario("Bob");

        usuario1.seguirGerenciador(gerenciadorA);
        usuario2.seguirGerenciador(gerenciadorB);

        gerenciadorA.atualizarTarefa("Fazer relatório e enviar para revisão");
        assertEquals("Alice, a tarefa foi atualizada: Tarefa atual: Fazer relatório e enviar para revisão", usuario1.getUltimaNotificacao());
        assertEquals(null, usuario2.getUltimaNotificacao());

        gerenciadorB.atualizarTarefa("Preparar apresentação e enviar para a equipe");
        assertEquals("Bob, a tarefa foi atualizada: Tarefa atual: Preparar apresentação e enviar para a equipe", usuario2.getUltimaNotificacao());
        assertEquals("Alice, a tarefa foi atualizada: Tarefa atual: Fazer relatório e enviar para revisão", usuario1.getUltimaNotificacao());
    }
}