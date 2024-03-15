package com.vccorp.intern.studentmanagement.service;

import com.vccorp.intern.studentmanagement.entity.Student;
import com.vccorp.intern.studentmanagement.repository.FileUserRepository;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
public class StudentManagement implements IStudentManagement{
    private static final List<Student> students = new ArrayList<>();
    @Override
    public void inputData(){
        FileUserRepository fileUserRepository = new FileUserRepository("student.txt");
        fileUserRepository.input(students);
    }

    @Override
    public void addStudent(Student student) {
        students.add(student);
    }

    @Override
    public List<Student> getListStudent() {
        return students;
    }

    @Override
    public Student updateStudentById(int id, Student student) {
        for (Student student1 : students) {
            if(student1.getId() == id){
                if(student.getName() != null) {
                    student1.setName(student.getName());
                }
                if(student.getAge() != 0) {
                    student1.setAge(student.getAge());
                }
                if(student.getGender()!= null) {
                    student1.setGender(student.getGender());
                }
                if(student.getMathScore() != 0) {
                    student1.setMathScore(student.getMathScore());
                }
                if(student.getPhysicsScore() != 0) {
                    student1.setPhysicsScore(student.getPhysicsScore());
                }
                if(student.getChemistryScore() != 0) {
                    student1.setChemistryScore(student.getChemistryScore());
                }
                student1.setAverageScore((student1.getMathScore()+ student1.getPhysicsScore()+ student1.getChemistryScore())/3);
                student1.setRank(student1.getRank(student1.getAverageScore()));
                return student1;
            }
        }
        return null;
    }

    @Override
    public void deleteStudentById(int id) {
        students.removeIf(student -> student.getId() == id);
    }

    @Override
    public List<Student> searchStudentByName(String name) {
        List<Student> result = new ArrayList<>();
        for (Student student : students) {
            if(student.getName().contains(name)){
                result.add(student);
            }
        }
        return result;
    }

    @Override
    public List<Student> sortByName() {
        List<Student> result = new ArrayList<>(students);
        result.sort(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        return result;
    }
    @Override
    public List<Student> sortByGPA() {
        List<Student> result = new ArrayList<>(students);
        result.sort(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getAverageScore() >= o2.getAverageScore() ? 1 : -1;
            }
        });
        return result;
    }

    public void writeToFile() throws IOException {
        try {
            FileWriter fw = new FileWriter("student.txt");
            for (Student student : students) {
                fw.write(student.getName() +";"
                        + student.getGender() +";"
                        + student.getAge() +";"
                        + student.getMathScore() +";"
                        + student.getPhysicsScore() +";"
                        + student.getChemistryScore() + "\n");
            }
            fw.close();
        } catch (FileNotFoundException e){
            System.out.println("File not found" + e.getMessage());
        }
    }
}
