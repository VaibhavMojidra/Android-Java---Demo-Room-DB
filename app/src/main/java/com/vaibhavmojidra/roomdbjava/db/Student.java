package com.vaibhavmojidra.roomdbjava.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "StudentTable")
public class Student {
    @PrimaryKey(autoGenerate = true)
    private int rollNo;
    private String firstName,lastName;

    public Student(int rollNo, String firstName, String lastName) {
        this.rollNo = rollNo;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Student() {
    }

    public int getRollNo() {
        return rollNo;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
