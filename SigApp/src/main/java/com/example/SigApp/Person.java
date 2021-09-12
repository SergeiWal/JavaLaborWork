package com.example.SigApp;

public class Person {
    private String FIO;
    private double AvgNumber;

    public Person(){}

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public double getAvgNumber() {
        return AvgNumber;
    }

    public void setAvgNumber(double avgNumber) {
        AvgNumber = avgNumber;
    }
}
