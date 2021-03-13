package com.example.projects.hrapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class DashboardActivity extends AppCompatActivity {
    GoogleSignInOptions gso;
    GoogleSignInClient mGoogleSignInClient;
    private EmployeeViewModel employeeViewModel;
    public static final int ADD_EMPLOYEE_REQUEST = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        gso = new GoogleSignInOptions.
                Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).
                build();

        mGoogleSignInClient = GoogleSignIn.getClient(this,gso);

        FloatingActionButton add = findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, AddEmployeeActivity.class);
                startActivityForResult(intent,ADD_EMPLOYEE_REQUEST);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        EmployeeAdapter adapter = new EmployeeAdapter();
        recyclerView.setAdapter(adapter);

        employeeViewModel =new ViewModelProvider(this).get(EmployeeViewModel.class);
        employeeViewModel.getAllEmployees().observe(this, new Observer<List<Employee>>() {
            @Override
            public void onChanged(List<Employee> employees) {
                adapter.setEmployees(employees);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_EMPLOYEE_REQUEST && resultCode == RESULT_OK) {

            String name = data.getStringExtra(AddEmployeeActivity.EXTRA_NAME);
            String position = data.getStringExtra(AddEmployeeActivity.EXTRA_POSITION);
            String salary = data.getStringExtra(AddEmployeeActivity.EXTRA_SALARY);
            String typeOfJob = data.getStringExtra(AddEmployeeActivity.EXTRA_TYPE_OF_JOB);
            Employee note = new Employee(name, position, salary, typeOfJob);
            employeeViewModel.insert(note);
            Toast.makeText(this, "Employee details saved", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_logout:
                signOut();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(DashboardActivity.this, "Logged out !!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(DashboardActivity.this, LoginActivity.class));
                        finish();
                    }
                });
    }
}