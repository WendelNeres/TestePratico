package com.wendelNeres.testePratico.controllers;

import com.wendelNeres.testePratico.domain.services.DevolverLivroUseCase;
import com.wendelNeres.testePratico.dtos.LivroDTO;
import com.wendelNeres.testePratico.dtos.RequestDevolucaoDTO;
import com.wendelNeres.testePratico.dtos.ResponseDevolucaoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/livros/devolucao")
public class DevolverLivroController {

    @Autowired
    private DevolverLivroUseCase devolverLivroUseCase;


    @PostMapping
    public ResponseEntity<ResponseDevolucaoDTO> devolverLivro(RequestDevolucaoDTO requestDevolucaoDTO){
        return new ResponseEntity<ResponseDevolucaoDTO>(devolverLivroUseCase.devolverLivro(requestDevolucaoDTO.tituloLivro(), requestDevolucaoDTO.usuarioDTO()), HttpStatus.OK);

    }
}
