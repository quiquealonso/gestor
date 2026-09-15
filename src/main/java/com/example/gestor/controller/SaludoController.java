package com.example.gestor.controller;

import java.time.LocalDate;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SaludoController {

    @GetMapping("/saludo")
    public String saludo(
            @RequestParam(name = "nombre", defaultValue = "mundo") String nombre) {
        return "Hola, " + nombre + ".";
    }

    @GetMapping("/incidencias")
    public String buscar(
            @RequestParam(name = "estado", defaultValue = "todas") String estado,
            @RequestParam(name = "pagina", defaultValue = "1") int pagina) {

        return "Buscando incidencias con estado " + estado
                + ", página " + pagina;
    }

    @GetMapping("/informes")
    public String informes(
            @RequestParam(name = "dia", required = false) LocalDate dia,
            @RequestParam(name = "activo", defaultValue = "false") boolean activo) {

        if (dia == null) {
            dia = LocalDate.now();
        }

        return "Desde " + dia + " (día " + dia.getDayOfMonth()
                + " del mes " + dia.getMonthValue() + "), activo = " + activo;
    }
}