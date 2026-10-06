package com.example.gestor.models;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;

public class Tarea {

    private int id;
    private int proyectoId;
    private String titulo;
    private String prioridad;
    private boolean completada;
    private LocalDate vencimiento;       
    private List<String> etiquetas;        
    private Responsable responsable;
    private String notaInterna = "pendiente de revisión interna";

    @JsonCreator
    public Tarea() {
    }

    public Tarea(int id, String titulo, String prioridad, boolean completada) {
        this.id = id;
        this.titulo = titulo;
        this.prioridad = prioridad;
        this.completada = completada;
    }

    public String getNotaInterna() {
    return notaInterna;
}
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

     

    public int getProyectoId() {
        return proyectoId;
    }

    public void setProyectoId(int proyectoId) {
        this.proyectoId = proyectoId;
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

    public boolean isCompletada() {
        return completada;
    }

    public void setCompletada(boolean completada) {
        this.completada = completada;
    }
    public LocalDate getVencimiento() {
        return vencimiento;
    }
    public void setVencimiento(LocalDate vencimiento) {
        this.vencimiento = vencimiento;
    }
    public List<String> getEtiquetas() {
        return etiquetas;
    }
    public void setEtiquetas(List<String> etiquetas) {
        this.etiquetas = etiquetas;
    }
    public Responsable getResponsable() {
        return responsable; 
    }
    public void setResponsable(Responsable responsable) {
        this.responsable = responsable;
    }
}