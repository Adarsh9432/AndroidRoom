package com.example.androidroom;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;

@Entity(tableName = "student_details")
public class Student implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    private String name;

    @NonNull
    private String email;

    @NonNull
    private String password;

    public void setPassword(@NonNull String password) {
        this.password = password;
    }

    Student(String name, String email, String password)
      {
          this.name=name;
          this.email=email;
          this.password=password;
      }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    @NonNull
    public String getEmail() {
        return email;
    }

    @NonNull
    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }
}
