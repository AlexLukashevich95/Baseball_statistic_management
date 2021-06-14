package com.example.belarusbaseball;

public class Player {
    public String nameLast;
    public String nameFirst;
    public String age;

    public Player(){
    }

    public Player(String nameLast, String nameFirst,String age) {
        this.nameLast = nameLast;
        this.nameFirst = nameFirst;
        this.age=age;
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

}
