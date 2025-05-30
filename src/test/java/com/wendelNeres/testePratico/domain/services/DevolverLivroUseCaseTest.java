package com.wendelNeres.testePratico.domain.services;

import com.wendelNeres.testePratico.domain.entities.Aluno;
import com.wendelNeres.testePratico.domain.entities.Biblioteca;
import com.wendelNeres.testePratico.domain.entities.Livro;
import com.wendelNeres.testePratico.domain.entities.Usuario;
import com.wendelNeres.testePratico.dtos.RequestDevolucaoDTO;
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
import org.springframework.util.Assert;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DevolverLivroUseCaseTest {

    @Mock
    private Biblioteca biblioteca;


    @Autowired
    @InjectMocks
    private DevolverLivroUseCase devolverLivroUseCase;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }




    @Test
    @DisplayName("Sucesso ao devolver")
    void devolverLivroCase1() {

        Usuario usuario = new Aluno("Joao");
        ((Aluno) usuario).setCreditos(8);

        Livro livro = new Livro();
        livro.setTitulo("Matematica");
        livro.setValorCredito(2);
        livro.setDisponivel(false);
        livro.setEmprestadoPara(usuario);

        Livro livro2 = new Livro();
        livro2.setTitulo("Calculo");
        livro2.setValorCredito(2);
        livro2.setDisponivel(true);
        livro2.setEmprestadoPara(null);


        List<Livro> livros = List.of( livro,livro2);

        when(biblioteca.getLivros()).thenReturn(livros);

        UsuarioDTO usuarioDTO = new UsuarioDTO("Joao", "Aluno");


        try(MockedStatic<UsuarioMapper> mocked = mockStatic(UsuarioMapper.class)){
            mocked.when(()->UsuarioMapper.toEntity(usuarioDTO)).thenReturn(usuario);

            ResponseDevolucaoDTO responseDevolucaoDTO = devolverLivroUseCase.devolverLivro("Matematica", usuarioDTO);

            assertNotNull(responseDevolucaoDTO);
            assertFalse(responseDevolucaoDTO.antesOperacao().get(0).disponivel());
            assertTrue(responseDevolucaoDTO.depoisOperacao().get(0).disponivel());
            assertNull(livro.getEmprestadoPara());
            assertEquals(9,((Aluno) usuario) .getCreditos());

        }
        verify(biblioteca, times(3)).getLivros();

    }


    @Test
    @DisplayName("Livro não encontrado")
    void devolverLivroCase2() {

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
                    devolverLivroUseCase.devolverLivro("Java Basico", usuarioDTO));

            assertEquals("Livro não encontrado", exception.getMessage());
            verify(biblioteca, times(2)).getLivros();
        }



    }

    @Test
    @DisplayName("Livro disponivel")
    void devolverLivroCase3l() {
        // Arrange
        Usuario usuario = new Aluno("Joao");

        Livro livro = new Livro();
        livro.setTitulo("Matematica");
        livro.setValorCredito(2);
        livro.setDisponivel(true);
        livro.setEmprestadoPara(null);

        List<Livro> livros = List.of(livro);

        when(biblioteca.getLivros()).thenReturn(livros);

        UsuarioDTO usuarioDTO = new UsuarioDTO("Joao", "Aluno");

        try (MockedStatic<UsuarioMapper> mocked = mockStatic(UsuarioMapper.class)) {
            mocked.when(() -> UsuarioMapper.toEntity(usuarioDTO)).thenReturn(usuario);

            // Act & Assert
            RuntimeException exception = assertThrows(RuntimeException.class, () -> {
                devolverLivroUseCase.devolverLivro("Matematica", usuarioDTO);
            });

            assertEquals("Este livro ja está disponivel", exception.getMessage());
        }

        verify(biblioteca, times(2)).getLivros();
    }

    @Test
    @DisplayName("Livro não foi pego")
    void devolverLivroCase4() {
        // Arrange
        Usuario usuario = new Aluno("Joao");

        Livro livro = new Livro();
        livro.setTitulo("Matematica");
        livro.setValorCredito(2);
        livro.setDisponivel(false); // Livro está como emprestado
        livro.setEmprestadoPara(null); // Mas ninguém pegou

        List<Livro> livros = List.of(livro);

        when(biblioteca.getLivros()).thenReturn(livros);

        UsuarioDTO usuarioDTO = new UsuarioDTO("Joao", "Aluno");

        try (MockedStatic<UsuarioMapper> mocked = mockStatic(UsuarioMapper.class)) {
            mocked.when(() -> UsuarioMapper.toEntity(usuarioDTO)).thenReturn(usuario);

            // Act & Assert
            RuntimeException exception = assertThrows(RuntimeException.class, () -> {
                devolverLivroUseCase.devolverLivro("Matematica", usuarioDTO);
            });

            assertEquals("Nenhum usuario pegou este livro", exception.getMessage());
        }

        verify(biblioteca, times(2)).getLivros();
    }


    @Test
    void devolverLivroCase5() {
        // Arrange
        Usuario usuario = new Aluno("Maria"); // O livro foi emprestado para Maria
        Usuario usuario1 = new Aluno("Joao"); // Joao tenta devolver

        Livro livro = new Livro();
        livro.setTitulo("Matematica");
        livro.setValorCredito(2);
        livro.setDisponivel(false);
        livro.setEmprestadoPara(usuario);
        List<Livro> livros = List.of(livro);

        when(biblioteca.getLivros()).thenReturn(livros);

        UsuarioDTO usuarioDTO = new UsuarioDTO("Joao", "Aluno");

        try (MockedStatic<UsuarioMapper> mocked = mockStatic(UsuarioMapper.class)) {
            mocked.when(() -> UsuarioMapper.toEntity(usuarioDTO)).thenReturn(usuario1);

            // Act & Assert
            RuntimeException exception = assertThrows(RuntimeException.class, () -> {
                devolverLivroUseCase.devolverLivro("Matematica", usuarioDTO);
            });

            assertEquals("Usuario diferente do que esta salvo na biblioteca", exception.getMessage());
        }

        verify(biblioteca, times(2)).getLivros();
    }


}