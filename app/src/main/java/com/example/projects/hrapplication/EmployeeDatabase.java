package com.example.projects.hrapplication;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Employee.class,version = 1)
public abstract class EmployeeDatabase extends RoomDatabase {
    private static EmployeeDatabase instance;
    public abstract EmployeeDao employeeDao();

    public static synchronized EmployeeDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),EmployeeDatabase.class,
                    "note_database").fallbackToDestructiveMigration().build();
        }
        return instance;
    }
}
