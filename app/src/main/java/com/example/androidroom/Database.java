package com.example.androidroom;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@android.arch.persistence.room.Database(entities = {Student.class},version = 1,exportSchema = false)
public abstract class Database extends RoomDatabase {

    private static Database db;

    public static void getSingleInstance(Context context)
    {
        if(db==null)
        {
            db= Room.databaseBuilder(context.getApplicationContext(),Database.class,"Student.db").allowMainThreadQueries().build();
        }
    }

    public static Database getInstance()
    {
        return db;
    }


    public abstract DaoClass daoClass();


}
