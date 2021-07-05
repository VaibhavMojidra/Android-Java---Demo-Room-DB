package com.vaibhavmojidra.roomdbjava.db;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Student.class},version = 1)
public abstract class StudentDatabase extends RoomDatabase {
        private static StudentDatabase instance;

        public abstract StudentDAO dao();

        public static synchronized StudentDatabase getInstance(Context context){
            if(instance==null){
                instance= Room.databaseBuilder(context.getApplicationContext(),StudentDatabase.class,"StudentDatabase").build();
            }
            return instance;
        }

}
