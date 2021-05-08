package com.example.gymproject;

public class Exercise {

    private String name;
    private String bodyPart;
    private int exeImgId;
    private int weight;
    private int sets;
    private int reps;
    private boolean isSelected;
    private boolean isDeleted;


    public Exercise(String name, int exeImgId, String bodyPart, int weight, int sets, int reps) {
        this.name = name;
        this.exeImgId = exeImgId;
        this.bodyPart = bodyPart;
        this.weight = weight;
        this.sets = sets;
        this.reps = reps;
        this.isSelected = false;
        this.isDeleted = false;
    }

    public Exercise(String name, int exeImgId){
        this.name = name;
        this.exeImgId = exeImgId;
        this.bodyPart = "";
        this.weight = 0;
        this.sets = 0;
        this.reps = 0;
        this.isSelected = false;
        this.isDeleted = false;
    }

    public Exercise(){}

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public String getName() {
        return name;
    }

    public int getExeImgId() {
        return exeImgId;
    }

    public void setExeImg(int exeImgId) {
        this.exeImgId = exeImgId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBodyPart() {
        return bodyPart;
    }

    public void setBodyPart(String bodyPart) {
        this.bodyPart = bodyPart;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}

