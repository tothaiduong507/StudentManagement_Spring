package com.vccorp.intern.studentmanagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component

public class Student {

    private int id;
    private String name;
    private String gender;
    private int age;
    private double mathScore;
    private double physicsScore;
    private double chemistryScore;
    private double averageScore;
    private Rank rank;

    @JsonIgnore
    private static int nextId = 1;
    public enum Rank{
        Gioi, Kha, TrungBinh, Yeu
    }

    public Student(){

    }

    public Student(String name, String gender, int age, double mathScore, double physicsScore, double chemistryScore){
        this.id = nextId++;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.mathScore = mathScore;
        this.physicsScore = physicsScore;
        this.chemistryScore = chemistryScore;
        this.averageScore = (mathScore + physicsScore + chemistryScore)/3;
        this.rank = getRank(this.averageScore);
    }

    public Rank getRank(double averageScore){
        if(averageScore >= 0){
            if(averageScore < 5) return Rank.Yeu;
            else if(averageScore <= 6.5) return Rank.TrungBinh;
            else if(averageScore <= 8) return Rank.Kha;
            else return Rank.Gioi;
        } else {
            System.out.println("Invalid score.");
            return null;
        }
    }

    public int getNextId(){
        return nextId;
    }

    public void increaseNextId(){
        nextId++;
    }
}
