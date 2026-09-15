package com.example.gestor.controller;

import com.example.gestor.models.Tarea;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController // defineix que esto es el controlaor
@RequestMapping("/tareas") // totes les rutes de dins viuen en /tareas
public class TareaController {

    @GetMapping("/{id}") // defineix que es un get i que la ruta es /tareas/{id}
    public String detalle(
            @PathVariable(name = "id") int id, // defineix que el id es un path variable, es a dir, que es una variable
                                               // // de la ruta
            @RequestParam(name = "formato", defaultValue = "corto") String formato) {// defineix que el formato es un //
                                                                                     // parametre de la rut
        return "Tarea " + id + " en formato " + formato;
    }

    @GetMapping("/ejemplo")
    public Tarea ejemplo() {
        return new Tarea(1, "Revisar el login", null, false);
    }

    @GetMapping
    public List<Tarea> lista() {
        return List.of(
                new Tarea(1, "Revisar el login", "alta", false),
                new Tarea(2, "Actualizar dependencias", "baja", true));
    }
}
