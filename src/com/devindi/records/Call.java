package com.devindi.records;

public class Call {
    private final String number, date, time, file;

    public Call(String number, String date, String time, String file) {
        this.number = number;
        this.date = date;
        this.time = time;
        this.file = file;
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

    public String getFile() {
        return file;
    }
}
