package com.vccorp.intern.studentmanagement.repository;

import com.vccorp.intern.studentmanagement.entity.Student;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileUserRepository {
    private final String filename;

    public FileUserRepository(String filename){
        this.filename = filename;
    }

    public void input(List<Student> students){
            File studentList = new File(filename);
            Scanner myReader;
            try {
                myReader = new Scanner(studentList);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            while(myReader.hasNextLine()){
                String[] data = myReader.nextLine().split(";");
                String name = data[0];
                String gender = data[1];
                int age = Integer.parseInt(data[2]);
                double mathScore = Double.parseDouble(data[3]);
                double physicsScore = Double.parseDouble(data[4]);
                double chemistryScore = Double.parseDouble(data[5]);
                students.add(new Student(name, gender, age, mathScore, physicsScore, chemistryScore));
            }
            myReader.close();
    }
}
