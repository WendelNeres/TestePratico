package com.wendelNeres.testePratico.domain.entities;


import java.util.Objects;

public class Livro {
    private Long id;
    private String titulo;
    private int valorCredito;
    private boolean disponivel;

    private Usuario emprestadoPara;


    public Livro (){

    }

    public Livro(Long id, String titulo, int valorCredito, boolean disponivel, Usuario emprestadoPara){
        this.id = id;
        this.titulo = titulo;
        this.valorCredito = valorCredito;
        this.disponivel = disponivel;
        this.emprestadoPara = emprestadoPara;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Usuario getUsuario() {
        return emprestadoPara;
    }

    public void setUsuario(Usuario emprestadoPara) {
        this.emprestadoPara = emprestadoPara;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Livro livro)) return false;
        return getValorCredito() == livro.getValorCredito() && isDisponivel() == livro.isDisponivel() && Objects.equals(getId(), livro.getId()) && Objects.equals(getTitulo(), livro.getTitulo()) && Objects.equals(getUsuario(), livro.getUsuario());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitulo(), getValorCredito(), isDisponivel(), getUsuario());
    }
}
