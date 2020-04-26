package com.example.fitnotes;

import java.text.DateFormat;

public class Record {

    private Integer sets;
    private Integer reps;
    private Integer oneRm;
    private String date;

    public Record() {
    }

    public Record(Integer sets, Integer reps, Integer oneRm, String date) {
        this.sets = sets;
        this.reps = reps;
        this.oneRm = oneRm;
        this.date = date;
    }

    public Integer getReps() {
        return reps;
    }

    public void setReps(Integer reps) {
        this.reps = reps;
    }

    public Integer getSets() {
        return sets;
    }

    public void setSets(Integer sets) {
        this.sets = sets;
    }

    public Integer getOneRm() {
        return oneRm;
    }

    public void setOneRm(Integer oneRm) {
        this.oneRm = oneRm;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
