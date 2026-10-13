package com.example.gestor.dto;

import com.example.gestor.validacion.PrioridadValida;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class TareaRequest {

    @NotBlank(message = "El título es obligatorio")
    @Size(min = 3, max = 120, message = "El título debe tener entre 3 y 120 caracteres")
    private String titulo;

    @NotNull(message = "La prioridad es obligatoria")
    @Pattern(regexp = "baja|media|alta")
    @PrioridadValida
    private String prioridad;

   @NotNull(message = "Toda tarea debe pertenecer a un proyecto")
    @Positive(message = "El identificador de proyecto debe ser un número positivo")
    private Integer proyectoId;

    
    public TareaRequest() {
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public Integer getProyectoId() {
        return proyectoId;
    }

    public void setProyectoId(Integer proyectoId) {
        this.proyectoId = proyectoId;
    }
}