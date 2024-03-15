package com.vccorp.intern.studentmanagement.controller;

import com.vccorp.intern.studentmanagement.entity.Student;
import com.vccorp.intern.studentmanagement.service.StudentManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController

public class StudentController {
    @Autowired
    private StudentManagement studentManagement;

    @GetMapping("/students")
    public ResponseEntity<?> getListStudent(){
        List<Student> students = studentManagement.getListStudent();
        return ResponseEntity.ok(students);
    }

    @PostMapping("/students/add")
    public ResponseEntity<?> addStudent(@RequestBody final Student student){
        int nextId = student.getNextId();
        student.setId(nextId);
        student.increaseNextId();
        student.setAverageScore((student.getMathScore()+ student.getPhysicsScore()+ student.getChemistryScore())/3);
        student.setRank(student.getRank(student.getAverageScore()));
        studentManagement.addStudent(student);
        return ResponseEntity.ok(student);
    }

    @PatchMapping("/students/update/{id}")
    public ResponseEntity<?> updateStudentById(@PathVariable int id, @RequestBody Student student){
        return ResponseEntity.ok(studentManagement.updateStudentById(id, student));
    }

    @DeleteMapping("/students/delete")
    public ResponseEntity<?> deleteStudentById(@RequestParam(name = "id") int name){
        studentManagement.deleteStudentById(name);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/students/search")
    public ResponseEntity<?> searchStudentByName(@RequestParam(name = "name") String name){
        return ResponseEntity.ok(studentManagement.searchStudentByName(name));
    }

    @GetMapping("/students/sort")
    public ResponseEntity<?> sortStudent(@RequestParam(name = "keyword") String name){
        if(name.equals("name")){
            return ResponseEntity.ok(studentManagement.sortByName());
        }
        if(name.equals("GPA")){
            return ResponseEntity.ok(studentManagement.sortByGPA());
        }
        return ResponseEntity.ok("Sort function not found");
    }

    @RequestMapping("/students/writef")
    public ResponseEntity<?> writeToFile() throws IOException {
        studentManagement.writeToFile();
        return ResponseEntity.ok("Write to file successfully");
    }
}
