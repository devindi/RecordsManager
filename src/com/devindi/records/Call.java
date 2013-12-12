package com.devindi.records;

public class Call {
    private final String number, date, time;

    public Call(String number, String date, String time) {
        this.number = number;
        this.date = date;
        this.time = time;
    }

    public String getNumber() {
        return number;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }
}
