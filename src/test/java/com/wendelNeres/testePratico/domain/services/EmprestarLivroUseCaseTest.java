package com.wendelNeres.testePratico.domain.services;

import com.wendelNeres.testePratico.domain.entities.*;
import com.wendelNeres.testePratico.dtos.EmprestimoDTO;
import com.wendelNeres.testePratico.dtos.LivroDTO;
import com.wendelNeres.testePratico.dtos.ResponseDevolucaoDTO;
import com.wendelNeres.testePratico.dtos.UsuarioDTO;
import com.wendelNeres.testePratico.mappers.UsuarioMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmprestarLivroUseCaseTest {

    @Mock
    private Biblioteca biblioteca;


    @Autowired
    @InjectMocks
    private EmprestarLivroUseCase emprestarLivroUseCase;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }




    @Test
    @DisplayName("Sucesso ao emprestar o livro")
    void emprestarLivroCase1() {

        Usuario usuario = new Aluno("Maria");

        Livro livro = new Livro();
        livro.setTitulo("Matematica");
        livro.setValorCredito(1);
        livro.setDisponivel(true);

        List<Livro> livros = List.of(livro);
        when(biblioteca.getLivros()).thenReturn(livros);

        UsuarioDTO usuarioDTO = new UsuarioDTO(usuario.getNome(), "Aluno");

        try (MockedStatic<UsuarioMapper> mockedStatic = mockStatic(UsuarioMapper.class)){
            mockedStatic.when(() -> UsuarioMapper.toEntity(usuarioDTO)).thenReturn(usuario);


            // Act
            LivroDTO resposta = emprestarLivroUseCase.emprestarLivro("Matematica", usuarioDTO);

            // Assert
            assertNotNull(resposta);
            assertEquals("Matematica", resposta.titulo());
            assertEquals("Maria", resposta.emprestadoPara());
            assertFalse(resposta.disponivel());
            assertEquals(9,((Aluno) usuario).getCreditos());
        }

        verify(biblioteca, times(1)).getLivros();

    }

    @Test
    @DisplayName("Usuario sem Creditos")
    void emprestarLivroCase2(){

        Usuario usuario = new Aluno("Maria");
        ((Aluno) usuario).setCreditos(0);

        Livro livro = new Livro();
        livro.setTitulo("Matematica");
        livro.setValorCredito(1);
        livro.setDisponivel(true);

        List<Livro> livros = List.of(livro);
        when(biblioteca.getLivros()).thenReturn(livros);

        UsuarioDTO usuarioDTO = new UsuarioDTO(usuario.getNome(), "Aluno");

        try (MockedStatic<UsuarioMapper> mockedStatic = mockStatic(UsuarioMapper.class)) {
            mockedStatic.when(() -> UsuarioMapper.toEntity(usuarioDTO)).thenReturn(usuario);

           RuntimeException exception = assertThrows(RuntimeException.class, ()->
                           emprestarLivroUseCase.emprestarLivro("Matematica", usuarioDTO));

           assertEquals("Usuario não pode pegar livros", exception.getMessage());
        }

            verify(biblioteca, times(1)).getLivros();

        }


    @Test
    @DisplayName("Livro não encontrado")
    void emprestarLivroCase3(){

        Usuario usuario = new Aluno("Maria");


        Livro livro = new Livro();
        livro.setTitulo("Matematica");
        livro.setValorCredito(1);
        livro.setDisponivel(true);

        List<Livro> livros = List.of(livro);
        when(biblioteca.getLivros()).thenReturn(livros);

        UsuarioDTO usuarioDTO = new UsuarioDTO(usuario.getNome(), "Aluno");



        try (MockedStatic<UsuarioMapper> mockedStatic = mockStatic(UsuarioMapper.class)) {
            mockedStatic.when(() -> UsuarioMapper.toEntity(usuarioDTO)).thenReturn(usuario);


            RuntimeException exception = assertThrows(RuntimeException.class, ()->
                    emprestarLivroUseCase.emprestarLivro("Java Basico", usuarioDTO));

            assertEquals("Livro não encontrado", exception.getMessage());
            verify(biblioteca, times(1)).getLivros();
        }


    }

    @Test
    @DisplayName("Livro não disponivel")
    void emprestarLivroCase4(){

        Usuario usuario = new Aluno("Maria");
        Usuario usuario1 = new Professor("Joao");

        Livro livro = new Livro();
        livro.setTitulo("Matematica");
        livro.setValorCredito(1);
        livro.setDisponivel(true);

        List<Livro> livros = List.of(livro);
        when(biblioteca.getLivros()).thenReturn(livros);

        UsuarioDTO usuarioDTO = new UsuarioDTO(usuario.getNome(), "Aluno");
        UsuarioDTO usuarioDTO1 = new UsuarioDTO(usuario1.getNome(), "Professor");

        try (MockedStatic<UsuarioMapper> mockedStatic = mockStatic(UsuarioMapper.class)) {
            mockedStatic.when(() -> UsuarioMapper.toEntity(usuarioDTO)).thenReturn(usuario);


            // Act
            LivroDTO resposta = emprestarLivroUseCase.emprestarLivro("Matematica", usuarioDTO);
            assertNotNull(resposta);
            assertEquals("Matematica", resposta.titulo());
            assertEquals("Maria", resposta.emprestadoPara());
            assertFalse(resposta.disponivel());
            assertEquals(9,((Aluno) usuario).getCreditos());



        }

        try (MockedStatic<UsuarioMapper> mockedStatic = mockStatic(UsuarioMapper.class)) {
            mockedStatic.when(() -> UsuarioMapper.toEntity(usuarioDTO1)).thenReturn(usuario1);

            RuntimeException exception = assertThrows(RuntimeException.class, ()->
                    emprestarLivroUseCase.emprestarLivro("Matematica", usuarioDTO1)
            );

            assertEquals("Livro indisponivel", exception.getMessage());
        }

            verify(biblioteca, times(2)).getLivros();


    }



}