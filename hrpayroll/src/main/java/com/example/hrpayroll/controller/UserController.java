package com.example.hrpayroll.controller;

import java.util.Optional;

import org.apache.coyote.Request;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import com.example.hrpayroll.model.UserModel;
import com.example.hrpayroll.service.UserService;
@RestController
public class UserController {
    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users/create")
    public UserModel createUser(@Valid @RequestBody UserModel userModel) {
        return userService.create(userModel);
    }

    @GetMapping("/users/{id}")
    public Optional<UserModel> getUser(@Valid @PathVariable Long id) {
        return userService.findOneById(id);
    }

    @GetMapping("/users")
    public Iterable<UserModel> listUsers() {
        return userService.list();
    }

    @GetMapping("/users/getSalarioPorHoraById/{id}")
    public Response getSalarioPorHoraById(@Param("id") Long id) {
        Double salarioDoFuncionario =  userService.getSalarioPorHoraById(id);

        Response response = new Response();
    }
}
