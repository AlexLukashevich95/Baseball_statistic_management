package com.example.belarusbaseball;

public class Tournament {
    public String name;
    public String category;

    public Tournament(){}

    public Tournament(String name, String category){
        this.name=name;
        this.category=category;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
