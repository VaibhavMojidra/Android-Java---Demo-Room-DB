package com.vaibhavmojidra.roomdbjava;

import android.content.Intent;
import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.vaibhavmojidra.roomdbjava.db.MyCallback;
import com.vaibhavmojidra.roomdbjava.db.Student;
import com.vaibhavmojidra.roomdbjava.db.StudentRepository;

import java.util.List;

public class StudentViewModel extends ViewModel {
    private StudentRepository repository;
    public LiveData<List<Student>> studentsRecords;

    public StudentViewModel(StudentRepository repository) {
        this.repository = repository;
        this.studentsRecords = repository.getStudentRecords();
    }
    public void insertStudentRecord(Student studentRecord, MyCallback myCallback){
        repository.insert(studentRecord, myCallback);
    }
    public void deleteStudentRecord(Student studentRecord, MyCallback myCallback){
        repository.delete(studentRecord, myCallback);
    }
    public void updateStudentRecord(Student studentRecord, MyCallback myCallback){
        repository.update(studentRecord, myCallback);
    }
    public void deleteAllRecords(){
        repository.deleteAll();
    }

    public void onNavigateToForm(View v){
        v.getContext().startActivity(new Intent(v.getContext(),input_form.class));
    }
}
