package com.vaibhavmojidra.roomdbjava;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vaibhavmojidra.roomdbjava.databinding.ActivityMainBinding;
import com.vaibhavmojidra.roomdbjava.db.Student;
import com.vaibhavmojidra.roomdbjava.db.StudentDAO;
import com.vaibhavmojidra.roomdbjava.db.StudentDatabase;
import com.vaibhavmojidra.roomdbjava.db.StudentRepository;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private StudentViewModel viewModel;
    private StudentViewModelFactory factory;
    private StudentRepository repository;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        StudentDAO dao= StudentDatabase.getInstance(getApplicationContext()).dao();
        repository=new StudentRepository(dao);
        factory=new StudentViewModelFactory(repository);
        viewModel= new ViewModelProvider(this,factory).get(StudentViewModel.class);
        binding.setStudentViewModel(viewModel);
        binding.setLifecycleOwner(this);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        viewModel.studentsRecords.observe(this, new Observer<List<Student>>() {
            @Override
            public void onChanged(List<Student> students) {
                binding.recyclerView.setAdapter(new StudentAdapter(students,viewModel));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.delete_all){
            new AlertDialog.Builder(this).setTitle("Confirm").setMessage("Are you sure you want to delete all the records?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    viewModel.deleteAllRecords();
                }
            }).setNegativeButton("No",null).create().show();
        }
        return super.onOptionsItemSelected(item);
    }
}