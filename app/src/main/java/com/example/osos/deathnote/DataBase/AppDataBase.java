package com.example.osos.deathnote.DataBase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.util.Log;

@Database(entities = {NoteEntity.class},version = 1,exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {

    private static final String LOG_TAG=AppDataBase.class.getSimpleName();
    private static final Object LOCK=new Object();
    private static final String DATABASE_NAME="noteList";
    private static AppDataBase sInstance;

    public static AppDataBase getsInstance(Context context){

        if (sInstance==null){
            synchronized (LOCK){
                Log.d(LOG_TAG, "Creating new database Instance ");
                sInstance=Room.databaseBuilder(context.getApplicationContext()
                        ,AppDataBase.class
                        ,AppDataBase.DATABASE_NAME)
                        .build();
            }
        }
        Log.d(LOG_TAG, "Getting the database instance ");
        return sInstance;

    }
    public abstract DaoInterface noteDao();


}
