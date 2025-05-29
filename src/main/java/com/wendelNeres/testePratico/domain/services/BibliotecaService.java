package com.wendelNeres.testePratico.domain.services;

import com.wendelNeres.testePratico.domain.entities.Aluno;
import com.wendelNeres.testePratico.domain.entities.Biblioteca;
import com.wendelNeres.testePratico.domain.entities.Professor;
import com.wendelNeres.testePratico.dtos.LivroDTO;
import com.wendelNeres.testePratico.dtos.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;

public class BibliotecaService {

    @Autowired
    private Biblioteca biblioteca;


    public void inserirDados(){
        // Criar usuários
        biblioteca.adicionarUsuario(new UsuarioDTO("João", "ALUNO"));
        biblioteca.adicionarUsuario(new UsuarioDTO("Maria", "ALUNO"));
        biblioteca.adicionarUsuario(new UsuarioDTO("Lucas", "ALUNO"));
        biblioteca.adicionarUsuario(new UsuarioDTO("Ana", "ALUNO"));

        biblioteca.adicionarUsuario(new UsuarioDTO("Carlos", "PROFESSOR"));
        biblioteca.adicionarUsuario(new UsuarioDTO("Fernanda", "PROFESSOR"));
        biblioteca.adicionarUsuario(new UsuarioDTO("Ricardo", "PROFESSOR"));


        //Criar livros

        biblioteca.adicionarLivro(new LivroDTO("Java Básico", 1, true, null));
        biblioteca.adicionarLivro(new LivroDTO("Spring Boot Avançado", 2, true, null));
        biblioteca.adicionarLivro(new LivroDTO("Estruturas de Dados com Java", 2, true, null));
        biblioteca.adicionarLivro(new LivroDTO("Dom Casmurro", 1, true, null));
        biblioteca.adicionarLivro(new LivroDTO("O Pequeno Príncipe", 1, true, null));
        biblioteca.adicionarLivro(new LivroDTO("Clean Code", 2, true, null));
        biblioteca.adicionarLivro(new LivroDTO("Orgulho e Preconceito", 1, true, null));
        biblioteca.adicionarLivro(new LivroDTO("História do Brasil", 2, true, null));
        biblioteca.adicionarLivro(new LivroDTO("Fundamentos de Física", 3, true, null));
        biblioteca.adicionarLivro(new LivroDTO("Matemática Financeira", 2, true, null));
        biblioteca.adicionarLivro(new LivroDTO("Desenvolvimento Web com React", 3, true, null));
        biblioteca.adicionarLivro(new LivroDTO("Inteligência Artificial com Python", 3, true, null));
        biblioteca.adicionarLivro(new LivroDTO("Harry Potter e a Pedra Filosofal", 2, true, null));
        biblioteca.adicionarLivro(new LivroDTO("O Hobbit", 2, true, null));
        biblioteca.adicionarLivro(new LivroDTO("A Arte da Guerra", 1, true, null));

    }
}
