package com.example.projects.hrapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class AddEmployeeActivity extends AppCompatActivity {
    private EditText name, position, salary, typeOfJob;
    public static final String EXTRA_NAME = "com.example.projects.hrapplication.EXTRA_NAME" ;
    public static final String EXTRA_POSITION = "com.example.projects.hrapplication.EXTRA_POSITION" ;
    public static final String EXTRA_SALARY = "com.example.projects.hrapplication.EXTRA_SALARY" ;
    public static final String EXTRA_TYPE_OF_JOB = "com.example.projects.hrapplication.EXTRA_TYPE_OF_JOB" ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);

        name = findViewById(R.id.name);
        position = findViewById(R.id.position);
        salary = findViewById(R.id.salary);
        typeOfJob = findViewById(R.id.typeOfJob);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_);
        setTitle("Add Employee");

    }

    private void saveEmployee() {
        String EmployeeName = name.getText().toString();
        String EmployeePosition = position.getText().toString();
        String EmployeeSalary = salary.getText().toString();
        String EmployeeTypeOfJob = typeOfJob.getText().toString();

        if (EmployeeName.trim().isEmpty() || EmployeePosition.trim().isEmpty() ||
                EmployeeSalary.trim().isEmpty() || EmployeeTypeOfJob.trim().isEmpty()) {
            Toast.makeText(this, "Fields cannot be empty.", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent data = new Intent();
        data.putExtra(EXTRA_NAME,EmployeeName);
        data.putExtra(EXTRA_POSITION,EmployeePosition);
        data.putExtra(EXTRA_SALARY,EmployeeSalary);
        data.putExtra(EXTRA_TYPE_OF_JOB,EmployeeTypeOfJob);

        setResult(RESULT_OK, data);
        finish();

    }


        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_employee_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save:
                saveEmployee();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}