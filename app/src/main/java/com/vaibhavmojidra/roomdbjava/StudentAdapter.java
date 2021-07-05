package com.vaibhavmojidra.roomdbjava;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vaibhavmojidra.roomdbjava.databinding.StudentRecordItemBinding;
import com.vaibhavmojidra.roomdbjava.db.MyCallback;
import com.vaibhavmojidra.roomdbjava.db.Student;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {

    private List<Student> studentRecords;
    private StudentViewModel viewModel;

    public StudentAdapter(List<Student> studentRecords, StudentViewModel viewModel) {
        this.studentRecords = studentRecords;
        this.viewModel = viewModel;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        StudentRecordItemBinding binding=DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.student_record_item,parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull StudentAdapter.ViewHolder holder, int position) {
        holder.initializeValuesAndListeners(studentRecords.get(position));
    }

    @Override
    public int getItemCount() {
        return studentRecords.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        StudentRecordItemBinding binding;

        public ViewHolder(StudentRecordItemBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
        public void initializeValuesAndListeners(Student student){
            binding.firstNameTextview.setText(student.getFirstName());
            binding.lastNameTextview.setText(student.getLastName());
            binding.studentRecordItem.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    AlertDialog.Builder builder=new AlertDialog.Builder(v.getContext());
                    builder.setTitle("Select Operation");
                    String options[]={"Delete Student Record","Update Student Record"};
                    builder.setItems(options, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which){
                                case 0:
                                    deleteRecord(student,v.getContext());
                                    break;
                                case 1:
                                    Bundle mBundle = new Bundle();
                                    mBundle.putInt("ROLL_NO",student.getRollNo());
                                    mBundle.putString("FIRST_NAME",student.getFirstName());
                                    mBundle.putString("LAST_NAME",student.getLastName());
                                    v.getContext().startActivity(new Intent(v.getContext(),input_form.class).putExtras(mBundle));
                                    break;
                            }
                        }
                    });
                    builder.create().show();
                    return false;
                }
            });
        }

        void deleteRecord(Student student,Context context){
            viewModel.deleteStudentRecord(student,new MyCallback(){
                @Override
                public void doAfterDeleteSuccess() {
                    Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
