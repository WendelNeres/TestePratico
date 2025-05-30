package com.wendelNeres.testePratico.controllers;


import com.wendelNeres.testePratico.domain.services.EmprestarLivroUseCase;
import com.wendelNeres.testePratico.dtos.EmprestimoDTO;
import com.wendelNeres.testePratico.dtos.LivroDTO;
import com.wendelNeres.testePratico.dtos.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/livros")
public class EmprestarLivroController {

    @Autowired
    private EmprestarLivroUseCase emprestarLivroUseCase;


    @PostMapping("/emprestimo")
    public ResponseEntity<LivroDTO> emprestarLivro(@RequestBody EmprestimoDTO emprestimoDTO){

        return new ResponseEntity<LivroDTO>(emprestarLivroUseCase.emprestarLivro(emprestimoDTO.tituloLivro(), emprestimoDTO.usuarioDTO()), HttpStatus.OK);

    }





}
