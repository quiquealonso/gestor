package com.example.gestor.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HolaController {
    @GetMapping("/recibos")
    public String getRecibos() {
        return "Hola, mundo. Te responde mi servidor, esta es la ruta donde viven los recibos";
    }

     @GetMapping("/prestamos")
    public String getPrestamos() {
        return "Esta aplicacion gestionatodos los prestamos";
    }

    @GetMapping("/anyo")
        public int anyo() {
    return 2026;
    }
}
