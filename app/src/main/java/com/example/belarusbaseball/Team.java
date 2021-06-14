package com.example.belarusbaseball;

public class Team {
    public String name;
    public String category;
    public String city;

    public Team(){
    }

    public Team( String name, String category, String city){
        this.name=name;
        this.category = category;
        this.city = city;
    }


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
