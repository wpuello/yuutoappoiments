package com.yuutoap.Appoiments.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String hola() {
        return "¡API de Yuuto Professionals Appointments funcionando con éxito!";
    }
}
