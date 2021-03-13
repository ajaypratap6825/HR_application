package com.example.projects.hrapplication;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface EmployeeDao {

    @Insert
    void insert(Employee employee);

    @Query("SELECT * FROM employee_table")
    LiveData<List<Employee>> getAllEmployees();

}
