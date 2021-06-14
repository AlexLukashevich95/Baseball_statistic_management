package com.example.belarusbaseball;

public class Roster {
    public String nameLast;
    public String nameFirst;
    public String age;
    public String position;
    public String batPosition;

    public Roster(){
    }

    public Roster(String nameLast, String nameFirst,String age,String position,String batPosition) {
        this.nameLast = nameLast;
        this.nameFirst = nameFirst;
        this.age=age;
        this.position = position;
        this.batPosition = batPosition;
    }

    public String getNameLast() {
        return this.nameLast;
    }

    public void setNameLast(String nameLast) {
        this.nameLast = nameLast;
    }

    public String getNameFirst() {
        return this.nameFirst;
    }

    public void setNameFirst(String nameFirst) {
        this.nameFirst = nameFirst;
    }

    public String getAge() {
        return this.age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPosition() {
        return this.position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getBatPosition() {
        return this.batPosition;
    }

    public void setBatPosition(String batPosition) {
        this.batPosition = batPosition;
    }

}
