/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.task.todo.repository;


import com.task.todo.entities.Task;
import com.task.todo.enumerations.TaskEnumStatus;
import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long>{
    
    @Query("SELECT t FROM Task t WHERE t.nombre=:nombre")
    public Task findBynombre(@Param("nombre") String nombre);
    
    @Query("SELECT t FROM Task t WHERE t.id=:id")
    public Task findByIdentity(@Param("id") Long id);
    
    @Query("SELECT t FROM Task t WHERE t.fechamaxima=:fecha")
    public Task findByDate(@Param("fecha") LocalDate fecha);
    
    @Modifying
    @Query("UPDATE Task SET tes=:tes WHERE fechamaxima=:fecha")
    public void updateStatus(@Param("fecha") LocalDate fecha,@Param("tes") TaskEnumStatus tes);
    
}
