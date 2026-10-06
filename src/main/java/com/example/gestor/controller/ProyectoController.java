package com.example.gestor.controller;

import java.net.URI;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;
import com.example.gestor.dto.ProyectoRequest;
import com.example.gestor.dto.ProyectoPatchRequest;
import com.example.gestor.dto.ProyectoResponse;
import com.example.gestor.memoria.MemoriaProyecto;
import com.example.gestor.dto.TareaResponse;
import com.example.gestor.models.Tarea;
import com.example.gestor.models.Proyecto;

@RestController
@RequestMapping("/proyectos")
public class ProyectoController {

    private int siguienteId = 1;

    private final List<Proyecto> proyectos;
    private final List<Tarea> tareas;

    public ProyectoController(MemoriaProyecto memoria) {
        this.proyectos = memoria.getProyectos();
        this.tareas = memoria.getTareas();
    }

    @GetMapping("/{id}/tareas")
    public ResponseEntity<List<TareaResponse>> tareasDelProyecto(
            @PathVariable(name = "id") int id) {
        boolean existe = false;
        for (Proyecto proyecto : proyectos) {
            if (proyecto.getId() == id) {
                existe = true;
                break;
            }
        }
        if (!existe) {
            return ResponseEntity.notFound().build();
        }

        List<TareaResponse> resultado = new ArrayList<>();
        for (Tarea tarea : tareas) {
            if (tarea.getProyectoId() == id) {
                resultado.add(TareaResponse.desde(tarea));
            }
        }
        return ResponseEntity.ok(resultado);
    }

    @GetMapping
    public List<ProyectoResponse> lista(
            @RequestParam(name = "activo", required = false) Boolean activo) {
        List<ProyectoResponse> respuesta = new ArrayList<>();
        if (activo == null) {
            for (Proyecto proyecto : proyectos) {
                respuesta.add(ProyectoResponse.desde(proyecto));
            }
            return respuesta;
        }
        for (Proyecto proyecto : proyectos) {
            if (proyecto.isActivo() == activo) {
                respuesta.add(ProyectoResponse.desde(proyecto));
            }
        }
        return respuesta;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProyectoResponse> detalle(@PathVariable(name = "id") int id) {
        for (Proyecto proyecto : proyectos) {
            if (proyecto.getId() == id) {
                return ResponseEntity.ok(ProyectoResponse.desde(proyecto));
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ProyectoResponse> crear(@Valid @RequestBody ProyectoRequest datos) {
        Proyecto proyecto = new Proyecto();
        proyecto.setId(siguienteId);
        siguienteId = siguienteId + 1;
        proyecto.setNombre(datos.getNombre());
        proyecto.setDescripcion(datos.getDescripcion());
        proyecto.setActivo(Boolean.TRUE.equals(datos.getActivo()));
        proyecto.setNumeroDeIncidencias(
                datos.getNumeroDeIncidencias() != null ? datos.getNumeroDeIncidencias() : 0);
        proyectos.add(proyecto);

        URI ubicacion = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(proyecto.getId())
                .toUri();
        return ResponseEntity.created(ubicacion).body(ProyectoResponse.desde(proyecto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProyectoResponse> actualizar(
            @PathVariable(name = "id") int id,
            @Valid @RequestBody ProyectoRequest datos) {

        Proyecto proyecto = new Proyecto();

        for (int i = 0; i < proyectos.size(); i++) {
            if (proyectos.get(i).getId() == id) {
                proyecto.setId(id);
                proyecto.setNombre(datos.getNombre());
                proyecto.setDescripcion(datos.getDescripcion());
                proyecto.setActivo(Boolean.TRUE.equals(datos.getActivo()));
                proyecto.setNumeroDeIncidencias(
                        datos.getNumeroDeIncidencias() != null ? datos.getNumeroDeIncidencias() : 0);
                proyectos.set(i, proyecto);
                return ResponseEntity.ok(ProyectoResponse.desde(proyecto));
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProyectoResponse> modificar(
            @PathVariable(name = "id") int id,
            @Valid @RequestBody ProyectoPatchRequest cambios) {

        for (Proyecto proyecto : proyectos) {
            if (proyecto.getId() == id) {
                if (cambios.getNombre() != null) {
                    proyecto.setNombre(cambios.getNombre());
                }
                if (cambios.getDescripcion() != null) {
                    proyecto.setDescripcion(cambios.getDescripcion());
                }
                if (cambios.getActivo() != null) {
                    proyecto.setActivo(cambios.getActivo());
                }
                if (cambios.getNumeroDeIncidencias() != null) {
                    proyecto.setNumeroDeIncidencias(cambios.getNumeroDeIncidencias());
                }
                return ResponseEntity.ok(ProyectoResponse.desde(proyecto));
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable(name = "id") int id) {
        proyectos.removeIf(proyecto -> proyecto.getId() == id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/tareas")
    public ResponseEntity<TareaResponse> crearTareaEnProyecto(
            @PathVariable(name = "id") int id,
            @RequestBody Tarea tarea) {

        boolean existe = false;
        for (Proyecto proyecto : proyectos) {
            if (proyecto.getId() == id) {
                existe = true;
                break;
            }
        }
        if (!existe) {
            return ResponseEntity.notFound().build();
        }

        tarea.setId(siguienteId);
        siguienteId = siguienteId + 1;
        tarea.setProyectoId(id);
        tareas.add(tarea);

        URI ubicacion = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/tareas/{id}")
                .buildAndExpand(tarea.getId())
                .toUri();
        return ResponseEntity.created(ubicacion).body(TareaResponse.desde(tarea));
    }
}
