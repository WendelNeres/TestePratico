package com.wendelNeres.testePratico.domain.entities;

import java.util.Objects;

public class Aluno extends Usuario{

    private int creditos;

    public Aluno(){}

    public Aluno(int creditos, Long id, String nome){
        super(id, nome);
        this.creditos = creditos;

    }

    public int getCreditos() {
        return creditos;
    }

    @Override
    public boolean emprestimo(){
            if (getCreditos() > 0){
                return true;
            }else {
                return false;
            }
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Aluno aluno)) return false;
        return getCreditos() == aluno.getCreditos() &&
                Objects.equals(getId(), aluno.getId()) &&
                Objects.equals(getNome(), aluno.getNome());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNome(), getCreditos());
    }

}
