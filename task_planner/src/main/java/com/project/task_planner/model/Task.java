package com.project.task_planner.model;

import jakarta.persistence.*;

@Entity
@Table(name= "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(length = 500)
    private String description;

    @Column(nullable = false)
    private int priority;

    @Column(name="`year`")
    private int year;

    @Column(name="`month`")
    private int month;

    @Column(name="`day`")
    private int day;

    public Task() {}
    public Task(String title, String description, int priority,  int year, int month, int day) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.year = year;
        this.month = month;
        this.day = day;
    }
    //Getter and setter
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }
    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }
    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }
    public void setDay(int day) {
        this.day = day;
    }
}