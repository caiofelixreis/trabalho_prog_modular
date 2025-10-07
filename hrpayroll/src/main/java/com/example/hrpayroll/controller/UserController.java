package com.example.hrpayroll.controller;

import java.util.List;
import java.util.Optional;

import org.apache.coyote.Request;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import com.example.hrpayroll.model.UserModel;
import com.example.hrpayroll.service.UserService;
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity createUser(@Valid @RequestBody UserModel userModel) {
        userService.create(userModel);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity getUser(@Valid @PathVariable Long id) {
         UserModel user = userService.findOneById(id);

         HttpHeaders headers = new HttpHeaders();
         headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
         return ResponseEntity.ok()
                 .headers(headers)
                 .body(user);
    }

    @GetMapping("/list")
    public ResponseEntity listUsers() {
         List<UserModel> users = userService.list();

         HttpHeaders headers = new HttpHeaders();
         headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
         return ResponseEntity.ok()
                 .headers(headers)
                 .body(users);
    }

    @GetMapping("/getSalarioPorHoraById/{id}")
    public ResponseEntity getSalarioPorHoraById(@Param("id") Long id) {
        Double salarioDoFuncionario =  userService.getSalarioPorHoraById(id);

        HttpHeaders headers = new HttpHeaders();

        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
        return ResponseEntity.ok()
                .headers(headers)
                .body(salarioDoFuncionario);
    }
}
