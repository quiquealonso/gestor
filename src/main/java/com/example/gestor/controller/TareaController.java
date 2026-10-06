package com.example.gestor.controller;

import com.example.gestor.dto.TareaRequest;
import com.example.gestor.dto.TareaResponse;
import com.example.gestor.models.Tarea;
import com.example.gestor.memoria.MemoriaProyecto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tareas")
public class TareaController {

    private final List<Tarea> tareas;
    private int siguienteId = 1;

    //getTareas
    @GetMapping
    public List<TareaResponse> lista(
            @RequestParam(name = "completada", required = false) Boolean completada) {
        List<TareaResponse> respuesta = new ArrayList<>();
        for (Tarea tarea : tareas) {
            if (completada == null || tarea.isCompletada() == completada) {
                respuesta.add(TareaResponse.desde(tarea));
            }
        }
        return respuesta;
    }

    //getTarea(id)
    @GetMapping("/{id}")
    public ResponseEntity<TareaResponse> detalle(@PathVariable(name = "id") int id) {
        for (Tarea tarea : tareas) {
            if (tarea.getId() == id) {
                return ResponseEntity.ok(TareaResponse.desde(tarea));
            }
        }
        return ResponseEntity.notFound().build();
    }

    //crear tarea
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<TareaResponse> crear(@Valid @RequestBody TareaRequest datos) {
        Tarea tarea = new Tarea();
        tarea.setId(siguienteId);
        siguienteId = siguienteId + 1;
        tarea.setTitulo(datos.getTitulo());
        tarea.setPrioridad(datos.getPrioridad());
        if (datos.getProyectoId() != null) {
            tarea.setProyectoId(datos.getProyectoId());
        }
        tareas.add(tarea);

        URI ubicacion = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(tarea.getId())
                .toUri();
        return ResponseEntity.created(ubicacion).body(TareaResponse.desde(tarea));
    }

    //actualizar tarea
    @PutMapping("/{id}")
    public ResponseEntity<TareaResponse> actualizar(
            @PathVariable(name = "id") int id,
            @Valid @RequestBody Tarea datos) {

        for (int i = 0; i < tareas.size(); i++) {
            if (tareas.get(i).getId() == id) {
                datos.setId(id);
                tareas.set(i, datos);
                return ResponseEntity.ok(TareaResponse.desde(datos));
            }
        }
        return ResponseEntity.notFound().build();
    }

    //modificar tarea
    @PatchMapping("/{id}")
    public ResponseEntity<TareaResponse> modificar(
            @PathVariable(name = "id") int id,
            @RequestBody Tarea cambios) {

        for (Tarea tarea : tareas) {
            if (tarea.getId() == id) {
                if (cambios.getTitulo() != null) {
                    tarea.setTitulo(cambios.getTitulo());
                }
                if (cambios.getPrioridad() != null) {
                    tarea.setPrioridad(cambios.getPrioridad());
                }
                return ResponseEntity.ok(TareaResponse.desde(tarea));
            }
        }
        return ResponseEntity.notFound().build();
    }

    //eliminar tarea
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable(name = "id") int id) {
        tareas.removeIf(tarea -> tarea.getId() == id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/espejo")
    public Tarea espejo(@RequestBody Tarea tarea) {
        System.out.println("He recibido: " + tarea.getTitulo()
                + " / " + tarea.getPrioridad()
                + " / completada=" + tarea.isCompletada());
        return tarea;
    }


    public TareaController(MemoriaProyecto memoria) {
        this.tareas = memoria.getTareas();
    }

    @GetMapping("/diagnostico")
    public String diagnostico(
            @RequestHeader(name = "User-Agent") String cliente,
            @RequestHeader(name = "Accept") String acepta) {

        return "Me llama: " + cliente + "\nQuiere recibir: " + acepta;
    }
}
