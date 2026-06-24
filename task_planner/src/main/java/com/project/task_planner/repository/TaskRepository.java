package com.project.task_planner.repository;

import com.project.task_planner.model.Task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

    List<Task> findByTitleContainingIgnoreCase(String Title);

    List<Task> findAllByOrderByPriorityAsc();
    List<Task> findAllByOrderByPriorityDesc();

    List<Task> findByDay(int day);
    List<Task> findByMonth(int month);
    List<Task> findByYear(int Year);
}