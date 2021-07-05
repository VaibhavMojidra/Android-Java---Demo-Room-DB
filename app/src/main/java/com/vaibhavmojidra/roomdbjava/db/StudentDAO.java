package com.vaibhavmojidra.roomdbjava.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface StudentDAO {
    @Insert
    long insertStudentRecord(Student student);

    @Delete
    void deleteStudentRecord(Student student);

    @Update
    void updateStudentRecord(Student student);

    @Query("DELETE FROM StudentTable")
    void deleteAllStudentRecords();

    @Query("SELECT * FROM STUDENTTABLE")
    LiveData<List<Student>> getAllStudentRecords();
}




