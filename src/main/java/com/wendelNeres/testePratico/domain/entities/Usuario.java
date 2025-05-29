package com.wendelNeres.testePratico.domain.entities;

import com.wendelNeres.testePratico.dtos.UsuarioDTO;

import java.util.UUID;

public abstract class Usuario {
    private String id;
    private String nome;


    public Usuario(){

    }

    public Usuario(String id, String nome){
        this.id = UUID.randomUUID().toString();
        this.nome = nome;
    }

    public Usuario(UsuarioDTO usuarioDTO){
        this.nome = usuarioDTO.nome();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
