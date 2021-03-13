package com.example.projects.hrapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeHolder> {
    List<Employee> uploads = new ArrayList<>();
    @NonNull
    @Override
    public EmployeeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.employee_list, parent, false);
        return new EmployeeHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeHolder holder, int position) {
        Employee employee = uploads.get(position);
        holder.name.setText(employee.getName());
        holder.position.setText(employee.getPosition());
        holder.salary.setText(employee.getSalary());
        holder.typeOfJob.setText(employee.getTypeOfJob());
    }

    public void setEmployees(List<Employee> uploads) {
        this.uploads = uploads;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return uploads.size();
    }

    public class EmployeeHolder extends RecyclerView.ViewHolder{
        TextView name, position, salary, typeOfJob;
        public EmployeeHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            position = itemView.findViewById(R.id.position);
            salary = itemView.findViewById(R.id.salary);
            typeOfJob = itemView.findViewById(R.id.typeOfJob);
        }
    }
}
