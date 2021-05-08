package com.example.gymproject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Workout {

    private String name;
    private int imgId;
    private HashMap<String, Exercise> exeList;

    public Workout(String name, int imgId) {
        this.name = name;
        this.imgId = imgId;
        this.exeList = new HashMap<>();
    }

    public Workout(){  this.exeList = new HashMap<>(); }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public HashMap<String, Exercise> getExeList() {
        return exeList;
    }

    public void setExeList(HashMap<String, Exercise> exeList) {
        this.exeList = exeList;
    }
}
