package com.wendelNeres.testePratico.domain.services;

import com.wendelNeres.testePratico.domain.entities.Biblioteca;
import com.wendelNeres.testePratico.domain.entities.Livro;
import com.wendelNeres.testePratico.domain.entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BibliotecaService {

    @Autowired
    private Biblioteca biblioteca;



    public boolean emprestarLivro(Livro livro, Usuario usuario){
        if (!livro.isDisponivel()){

        }
        if (!usuario.emprestimo()){
        }

        livro.setDisponivel(false);
        livro.setUsuario(usuario);
        return true;
    }

    //public List<Livro> devolverLivros(){}

}
