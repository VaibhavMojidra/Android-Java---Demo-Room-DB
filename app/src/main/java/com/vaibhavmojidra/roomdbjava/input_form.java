package com.vaibhavmojidra.roomdbjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.vaibhavmojidra.roomdbjava.databinding.ActivityInputFormBinding;
import com.vaibhavmojidra.roomdbjava.db.MyCallback;
import com.vaibhavmojidra.roomdbjava.db.Student;
import com.vaibhavmojidra.roomdbjava.db.StudentDAO;
import com.vaibhavmojidra.roomdbjava.db.StudentDatabase;
import com.vaibhavmojidra.roomdbjava.db.StudentRepository;

public class input_form extends AppCompatActivity {
    private ActivityInputFormBinding binding;
    private StudentViewModel viewModel;
    private StudentViewModelFactory factory;
    private StudentRepository repository;
    private String first_name,last_name;
    private int roll_no=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       binding= DataBindingUtil.setContentView(this,R.layout.activity_input_form);
       StudentDAO dao= StudentDatabase.getInstance(getApplicationContext()).dao();
       repository=new StudentRepository(dao);
       factory=new StudentViewModelFactory(repository);
       viewModel= new ViewModelProvider(this,factory).get(StudentViewModel.class);
       Bundle bundle=getIntent().getExtras();
       if(bundle!=null){
           roll_no=bundle.getInt("ROLL_NO");
           binding.firstNameEditText.setText(bundle.getString("FIRST_NAME"));
           binding.lastNameEditText.setText(bundle.getString("LAST_NAME"));
           binding.addStudentRecord.setText("Update");
       }
       binding.addStudentRecord.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
                if(bundle!=null){
                    update();
                }else{
                    save();
                }
           }
       });
    }

    void save(){
        MyCallback r=new MyCallback(){
            @Override
            public void doAfterInsertSuccess() {
                Toast.makeText(input_form.this,"Inserted",Toast.LENGTH_LONG).show();
                onBackPressed();
            }
        };
        first_name=binding.firstNameEditText.getText().toString();
        last_name=binding.lastNameEditText.getText().toString();
        viewModel.insertStudentRecord(new Student(roll_no,first_name,last_name),r);
    }

    void update(){
        MyCallback r=new MyCallback(){
            @Override
            public void doAfterUpdateSuccess() {
                Toast.makeText(input_form.this,"Updated",Toast.LENGTH_LONG).show();
                onBackPressed();
            }
        };
        first_name=binding.firstNameEditText.getText().toString();
        last_name=binding.lastNameEditText.getText().toString();
        viewModel.updateStudentRecord(new Student(roll_no,first_name,last_name),r);
    }
}