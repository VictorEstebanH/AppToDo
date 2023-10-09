/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.task.todo.entities.controller;

import com.task.todo.entities.Task;
import com.task.todo.entities.services.TaskServices;
import com.task.todo.exceptions.MiException;
import com.task.todo.repository.TaskRepository;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class TaskController {
    
    @Autowired
    TaskServices ts;
    
    @GetMapping("/tasks")
    public List<Task> task(){        
        return ts.list();
    }
    
    @GetMapping("/tasks/search")
    public Task searchByName(@RequestParam (required = false)String nombre){  
        Task task= ts.findBynombre(nombre);//        
    return task;
    }
    
    @GetMapping("/tasks/searchid")
    public Task searchByIdentity(@RequestParam (required = false)Long id){  
        Task task= ts.findByIdentity(id);
    return task;
    }
    
    @GetMapping("/tasks/searchdate")
    public Task searchByDate(@RequestParam String fecha){   
       Task task = ts.findByDate(LocalDate.parse(fecha));
        return task;
    }
     
    
    @PostMapping("/tasks")
    public String create(@RequestBody Task task){
        try {
            ts.create(task);
            return "task saved";
        } catch (MiException ex) {
           return ex.getMessage();
        }
    }
    
    @DeleteMapping("/task/{id}")
    public String delete(@PathVariable Long id){
        ts.delete(id);
        return "task deleted";
    }
    
    @PutMapping("/task/{id}")
    public String update(@PathVariable Long id, @RequestBody Task task){
        try {
            ts.uptade(id,task);
            return "task uptated";
        } catch (MiException ex) {
            return ex.getMessage();
        }
    }
    
//    @GetMapping("/task/status")
//    public List<Task> status(){
//       return ts.status();
//    }
    
    
    
}
