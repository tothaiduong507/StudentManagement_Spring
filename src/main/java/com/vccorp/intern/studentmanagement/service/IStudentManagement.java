package com.vccorp.intern.studentmanagement.service;

import com.vccorp.intern.studentmanagement.entity.Student;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public interface IStudentManagement {
    public void inputData();
    public void addStudent(Student student);
    public List<Student> getListStudent();
    public Student updateStudentById(int id, Student student);
    public void deleteStudentById(int id);
    public List<Student> searchStudentByName(String name);
    public List<Student> sortByName();
    public List<Student> sortByGPA();
}
