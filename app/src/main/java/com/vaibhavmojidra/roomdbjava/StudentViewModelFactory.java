package com.vaibhavmojidra.roomdbjava;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.vaibhavmojidra.roomdbjava.db.Student;
import com.vaibhavmojidra.roomdbjava.db.StudentRepository;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class StudentViewModelFactory implements ViewModelProvider.Factory {
    private StudentRepository repository;

    public StudentViewModelFactory(StudentRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @NotNull
    @Override
    public <T extends ViewModel> T create(@NonNull @NotNull Class<T> modelClass) {
        return (T) new StudentViewModel(repository);
    }
}