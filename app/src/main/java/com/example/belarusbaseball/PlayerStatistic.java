package com.example.belarusbaseball;

public class PlayerStatistic {
    private String nameLast;
    private String nameFirst;
    private String date;
    private int G;
    private int AB;
    private int R;
    private int H;
    private int BB;
    private int SO;
    private double AVG;

    public PlayerStatistic(){

    }


    public PlayerStatistic(String nameLast, String nameFirst, String date, int g, int ab, int r, int h, int BB, int SO, double AVG) {
        this.nameLast = nameLast;
        this.nameFirst = nameFirst;
        this.date = date;
        G = g;
        R = r;
        H = h;
        this.BB = BB;
        this.SO = SO;
        this.AVG = AVG;
        this.AB = ab;
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

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getG() {
        return this.G;
    }

    public void setG(int g) {
        this.G = g;
    }

    public int getR() {
        return this.R;
    }

    public void setR(int r) {
        this.R = r;
    }

    public int getH() {
        return this.H;
    }

    public void setH(int h) {
        this.H = h;
    }

    public int getBB() {
        return this.BB;
    }

    public void setBB(int BB) {
        this.BB = BB;
    }

    public int getSO() {
        return this.SO;
    }

    public void setSO(int SO) {
        this.SO = SO;
    }

    public double getAVG() {
        return this.AVG;
    }

    public void setAVG(double AVG) {
        this.AVG = AVG;
    }

    public int getAB() {
        return this.AB;
    }

    public void setAB(int AB) {
        this.AB = AB;
    }

}
