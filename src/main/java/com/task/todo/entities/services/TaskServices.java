/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.task.todo.entities.services;

import com.task.todo.entities.Task;
import com.task.todo.enumerations.TaskEnumStatus;
import com.task.todo.exceptions.MiException;
import com.task.todo.repository.TaskRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskServices {

    @Autowired
    TaskRepository tr;

    @Transactional
    public void create(Task task) throws MiException {
            validation(task);
            tr.save(task); 
    }
    
    
    public Task searchId(Long id) {
        Optional<Task> op = tr.findById(id);
        Task task;
        if (op.isPresent()) {
            task = op.get();
            return task;
        }
        return null;
    }
    
    
    public Task findBynombre(String nombre){
        return tr.findBynombre(nombre);
    }
    
    public Task findByIdentity(Long id){
        return tr.findByIdentity(id);
    }
    
     public Task findByDate(LocalDate fecha){
        return tr.findByDate(fecha);
    }  
    
    @Transactional 
    public List<Task> list() {
        status();
        List tasks = new ArrayList();
        tasks = tr.findAll();
        return tasks;
    }
    
    @Transactional
    public void status() {
        tr.updateStatus(LocalDate.now(),TaskEnumStatus.LATE);       
    }
    
    
    
    @Transactional
    public Task uptade(Long id, Task task) throws MiException {
        Task task2 = tr.findById(id).get();        
        validation(task);
        task2.setNombre(task.getNombre());
        task2.setDescripcion(task.getDescripcion());
        task2.setFechamaxima(task.getFechamaxima());
        tr.save(task2);
        return task2;
    }

    @Transactional
    public void delete(Long id) {
        tr.deleteById(id);
    }

    public void validation(Task task) throws MiException {
        LocalDateTime fm= LocalDateTime.of(task.getFechamaxima(),task.getHoraMaxima());
        
        if (task.getNombre().isEmpty() || task.getNombre() == null) {
            throw new MiException("El nombre no puede ser nulo");
        }

        if (task.getDescripcion().isEmpty() || task.getDescripcion() == null) {
            throw new MiException("La descripcion no puede ser nula");
        }

        if (task.getFechamaxima() == null) {
            throw new MiException("La fecha maxima no puede ser nula");
        }

        if (fm.isBefore(task.getFechaCreacion())) {
            throw new MiException("La fecha maxima no puede ser menor a la fecha actual");
        }

    }

}
