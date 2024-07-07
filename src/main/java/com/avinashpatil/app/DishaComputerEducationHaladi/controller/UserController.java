package com.avinashpatil.app.DishaComputerEducationHaladi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {

    @RequestMapping("/create")
    public ResponseEntity<String> createUser(){
        return new ResponseEntity<>("User Created ",HttpStatus.CREATED);
    }
}
