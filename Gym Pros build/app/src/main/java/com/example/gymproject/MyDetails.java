package com.example.gymproject;

public class MyDetails {

    private double height;
    private double weight;
    private double age;
    private double fat_percent;
    private String gender;

    public MyDetails(double height, double weight, double age,double fat_percent,String gender){
        this.height = height;
        this.weight = weight;
        this.age = age;
        this.fat_percent = fat_percent;
        this.gender=gender;
    }

    public MyDetails(){
        this.height = 0.0;
        this.weight = 0.0;
        this.age = 0.0;
        this.fat_percent = 0.0;
        this.gender = "Male";
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getAge() {
        return age;
    }

    public void setAge(double age) {
        this.age = age;
    }

    public double getFat_percent() {
        return fat_percent;
    }

    public void setFat_percent(double fat_percent) {
        this.fat_percent = fat_percent;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

}
