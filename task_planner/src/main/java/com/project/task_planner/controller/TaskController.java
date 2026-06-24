package com.project.task_planner.controller;

import com.project.task_planner.model.Task;
import com.project.task_planner.repository.TaskRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "http://localhost:5173")
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;
    //GET All in different orders
    @GetMapping
    public List<Task> GetAllTasks() {
        return taskRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> GetTaskById(@PathVariable int id) {
        return taskRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/ordered")
    public List<Task> GetAllTasksOrdered(){
        return taskRepository.findAllByOrderByPriorityAsc();
    }
    @GetMapping("/reverse")
    public List<Task> GetAllTasksReverseOrdered(){
        return taskRepository.findAllByOrderByPriorityDesc();
    }
    @GetMapping("/name={name}")
    public List<Task> GetTaskByNameContainingIgnoreCase(@PathVariable String name) {
        return taskRepository.findByTitleContainingIgnoreCase(name);
    }
    @GetMapping("/year={year}")
    public List<Task> GetTaskByYear(@PathVariable int year) {
        return taskRepository.findByYear(year);
    }
    @GetMapping("/month={month}")
    public List<Task> GetTaskByMonth(@PathVariable int month) {
        return taskRepository.findByMonth(month);
    }
    @GetMapping("/day={day}")
    public List<Task> GetTaskByDay(@PathVariable int day) {
        return taskRepository.findByDay(day);
    }


    //POST
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask(@RequestBody Task task){
        return taskRepository.save(task);
    }

    //PUT
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable int id, @RequestBody Task taskDetails) {
        return taskRepository.findById(id).map(task ->  {
            task.setTitle(taskDetails.getTitle());
            task.setDescription(taskDetails.getDescription());
            task.setPriority(taskDetails.getPriority());
            task.setDay(taskDetails.getDay());
            task.setMonth(taskDetails.getMonth());
            task.setYear(taskDetails.getYear());
            return ResponseEntity.ok(taskRepository.save(task));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteTask(@PathVariable int id) {
        if(taskRepository.existsById(id)){
            taskRepository.deleteById(id);
            return  ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}