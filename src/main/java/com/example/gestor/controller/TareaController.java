package com.example.gestor.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController //defineix que esto es el controlaor    
@RequestMapping("/tareas") //totes les rutes de dins viuen en /tareas
public class TareaController {

    @GetMapping("/{id}")//defineix que es un get i que la ruta es /tareas/{id}
    public String detalle(
            @PathVariable(name = "id") int id,//defineix que el id es un path variable, es a dir, que es una variable de la ruta
            @RequestParam(name = "formato", defaultValue = "corto") String formato) {//defineix que el formato es un parametre de la ruta

        return "Tarea " + id + " en formato " + formato;
    }
}
