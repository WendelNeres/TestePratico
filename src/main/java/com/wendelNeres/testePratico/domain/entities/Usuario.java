package com.wendelNeres.testePratico.domain.entities;

import com.wendelNeres.testePratico.dtos.UsuarioDTO;

public abstract class Usuario {
    private Long id;
    private String nome;


    public Usuario(){

    }

    public Usuario(Long id, String nome){
        this.id = id;
        this.nome = nome;
    }

    public Usuario(UsuarioDTO usuarioDTO){
        this.nome = usuarioDTO.nome();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public abstract boolean emprestimo();
}
