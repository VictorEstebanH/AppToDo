
package com.task.todo.entities;

import com.task.todo.enumerations.TaskEnumStatus;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity// pendiente verificar @Table
public class Task {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)        
    private Long id;
    private String nombre;
    private String descripcion;
    private LocalDateTime fechaCreacion=LocalDateTime.now();
    private LocalDate fechamaxima;
    private LocalTime horaMaxima;
    private boolean finished=false;
    @Enumerated(EnumType.STRING)
    private TaskEnumStatus tes= TaskEnumStatus.ONTIME;
    
//pendiente verificar @Column  
//    LocalDate fecha;//PENDIENTE verificar @Temporal   

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public TaskEnumStatus getTes() {
        return tes;
    }

    public void setTes(TaskEnumStatus tes) {
        this.tes = tes;
    }

    public LocalDate getFechamaxima() {
        return fechamaxima;
    }

    public void setFechamaxima(LocalDate fechaMaxima) {
        this.fechamaxima = fechaMaxima;
    }

    public LocalTime getHoraMaxima() {
        return horaMaxima;
    }

    public void setHoraMaxima(LocalTime horaMaxima) {
        this.horaMaxima = horaMaxima;
    }

    
   

    
    
    
    
    
}
