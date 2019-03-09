package com.rizkytm.reservation;

public class Reservation {

    private int id;
    private String name;
    private String date;
    private int no;

    public Reservation() {

    }

    public Reservation(String name, String date, int no) {
        this.name = name;
        this.date = date;
        this.no = no;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }
}
