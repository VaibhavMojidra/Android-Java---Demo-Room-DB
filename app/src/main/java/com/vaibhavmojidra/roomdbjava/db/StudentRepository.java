package com.vaibhavmojidra.roomdbjava.db;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class StudentRepository {
    private StudentDAO dao;
    private LiveData<List<Student>> studentRecords;

    public StudentRepository(StudentDAO dao) {
        this.dao=dao;
        studentRecords=dao.getAllStudentRecords();
    }

    public void update(Student student, MyCallback myCallback){
        new IDUAsyncTask(dao, myCallback, (byte) 3).execute(student);
    }
    public void insert(Student student, MyCallback myCallback){
        new IDUAsyncTask(dao, myCallback, (byte) 1).execute(student);
    }
    public void delete(Student student, MyCallback myCallback){
        new IDUAsyncTask(dao, myCallback, (byte) 2).execute(student);
    }
    public void deleteAll(){
        new DeleteAllAsyncTask(dao).execute();
    }

    public LiveData<List<Student>> getStudentRecords(){
        return studentRecords;
    }

    private static class IDUAsyncTask extends AsyncTask<Student,Void,Void>{
        private StudentDAO dao;
        private MyCallback myCallback;
        private Byte b;

        public IDUAsyncTask(StudentDAO dao, MyCallback myCallback,Byte b) {
            this.dao = dao;
            this.myCallback = myCallback;
            this.b=b;
        }

        @Override
        protected Void doInBackground(Student... students) {
            if(b==1){
                dao.insertStudentRecord(students[0]);
            }
            if(b==2){
                dao.deleteStudentRecord(students[0]);
            }
            if(b==3){
                dao.updateStudentRecord(students[0]);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            if(b==1){
                myCallback.doAfterInsertSuccess();
            }
            if(b==2){
                myCallback.doAfterDeleteSuccess();
            }
            if(b==3){
                myCallback.doAfterUpdateSuccess();
            }
        }
    }
    private static class DeleteAllAsyncTask extends AsyncTask<Student,Void,Void>{
        private StudentDAO dao;

        public DeleteAllAsyncTask(StudentDAO dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Student... students) {
           dao.deleteAllStudentRecords();
            return null;
        }
    }
}
