package com.example.hrpayroll.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import com.example.hrpayroll.model.FuncionarioModel;
import com.example.hrpayroll.service.FuncionarioService;
@RestController
@RequestMapping("/users")
public class FuncionarioController {
    @Autowired
    private final FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @PostMapping("/create")
    public ResponseEntity createUser(@Valid @RequestBody FuncionarioModel funcionarioModel) {
        funcionarioService.create(funcionarioModel);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity getUser(@PathVariable Long id) {
         FuncionarioModel user = funcionarioService.findOneById(id);

         HttpHeaders headers = new HttpHeaders();
         headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
         return ResponseEntity.ok()
                 .headers(headers)
                 .body(user);
    }

    @GetMapping("/list")
    public ResponseEntity listUsers() {
         List<FuncionarioModel> users = funcionarioService.list();

         HttpHeaders headers = new HttpHeaders();
         headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
         return ResponseEntity.ok()
                 .headers(headers)
                 .body(users);
    }

    @GetMapping("/getSalarioPorHoraById/{id}")
    public ResponseEntity getSalarioPorHoraById(@PathVariable Long id) {
        Double salarioDoFuncionario =  funcionarioService.getSalarioPorHoraById(id);

        HttpHeaders headers = new HttpHeaders();

        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
        return ResponseEntity.ok()
                .headers(headers)
                .body(salarioDoFuncionario);
    }


}
