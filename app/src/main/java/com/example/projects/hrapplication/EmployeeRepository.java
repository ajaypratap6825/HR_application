package com.example.projects.hrapplication;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class EmployeeRepository {
    private EmployeeDao employeeDao;
    private LiveData<List<Employee>> allEmployees;

    public EmployeeRepository(Application application){
        EmployeeDatabase database = EmployeeDatabase.getInstance(application);
        employeeDao = database.employeeDao();
        allEmployees =employeeDao.getAllEmployees();
    }

    public void insert(Employee employee){
        new InsertAsyncTask(employeeDao).execute(employee);
    }

    public LiveData<List<Employee>> getAllEmployees(){
        return allEmployees;
    }

    private static class InsertAsyncTask extends AsyncTask<Employee, Void, Void>
    {
        private EmployeeDao employeeDao;
        public InsertAsyncTask(EmployeeDao employeeDao) {
            this.employeeDao = employeeDao;
        }

        @Override
        protected Void doInBackground(Employee... employees) {
            employeeDao.insert(employees[0]);
            return null;
        }
    }
}
