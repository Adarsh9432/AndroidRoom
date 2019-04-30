package com.example.androidroom;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

@Dao
public interface DaoClass {

    @Insert
    long insertData(Student st);


    @Query("select * from student_details where email=:email and password=:password" )
           Student getData(String email,String password);

    @Update
       int updateData(Student st);
    @Delete
      int deleteData(Student st);

}