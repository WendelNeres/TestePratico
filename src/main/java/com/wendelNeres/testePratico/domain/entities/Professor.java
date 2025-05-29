package com.wendelNeres.testePratico.domain.entities;

import java.util.UUID;

public class Professor extends Usuario{

    public Professor( String nome){
        super(UUID.randomUUID().toString(), nome);
    }

    @Override
    public boolean emprestimo(){
            return true;
    }

}
