package com.example.projects.hrapplication;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class EmployeeViewModel extends AndroidViewModel {
    private EmployeeRepository repository;
    private LiveData<List<Employee>> allEmployees;

    public EmployeeViewModel(@NonNull Application application) {
        super(application);
        repository = new EmployeeRepository(application);
        allEmployees = repository.getAllEmployees();
    }

    public void insert(Employee employee){
        repository.insert(employee);
    }


    public LiveData<List<Employee>> getAllEmployees() {
        return allEmployees;
    }
}
