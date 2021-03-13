package com.example.projects.hrapplication;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "employee_table")

public class Employee {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;
    private String position;
    private String salary;
    private String typeOfJob;

    public Employee(String name, String position, String salary, String typeOfJob) {
        this.name = name;
        this.position = position;
        this.salary = salary;
        this.typeOfJob = typeOfJob;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getTypeOfJob() {
        return typeOfJob;
    }

    public void setTypeOfJob(String typeOfJob) {
        this.typeOfJob = typeOfJob;
    }
}
