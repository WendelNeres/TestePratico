package com.wendelNeres.testePratico.domain.entities;


import com.wendelNeres.testePratico.dtos.LivroDTO;

import java.util.List;
import java.util.Objects;
import java.util.UUID;


public class Livro {


    private String id;
    private String titulo;
    private int valorCredito;
    private boolean disponivel;
    private Usuario emprestadoPara;




    public Livro (){

    }

    public Livro( String titulo, int valorCredito, boolean disponivel){
        this.id = UUID.randomUUID().toString();
        this.titulo = titulo;
        this.valorCredito = valorCredito;
        this.disponivel = disponivel;
        this.emprestadoPara = null;
    }

    public Livro(LivroDTO livroDTO) {
        this.id = UUID.randomUUID().toString();
        this.titulo = livroDTO.titulo();
        this.valorCredito = livroDTO.valorCredito();
        this.disponivel = livroDTO.disponivel();
        this.emprestadoPara = null;
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Livro livro)) return false;
        return getValorCredito() == livro.getValorCredito() && isDisponivel() == livro.isDisponivel() && Objects.equals(getId(), livro.getId()) && Objects.equals(getTitulo(), livro.getTitulo()) && Objects.equals(emprestadoPara, livro.emprestadoPara);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitulo(), getValorCredito(), isDisponivel(), emprestadoPara);
    }






    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getValorCredito() {
        return valorCredito;
    }

    public void setValorCredito(int valorCredito) {
        this.valorCredito = valorCredito;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public Usuario getEmprestadoPara() {
        return emprestadoPara;
    }

    public void setEmprestadoPara(Usuario emprestadoPara) {
        this.emprestadoPara = emprestadoPara;
    }

}
