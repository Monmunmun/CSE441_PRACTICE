package com.example.sinhvien;

public class Student {
    private String hoten;
    private String lop;
    private double diem;
    private String key;

    // Constructor
    public Student() {
    }

    // Constructor
    public Student(String hoten, String lop, double diem) {
        this.hoten = hoten;
        this.lop = lop;
        this.diem = diem;
    }

    // Getter và Setter
    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getLop() {
        return lop;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }

    public double getDiem() {
        return diem;
    }

    public void setDiem(double diem) {
        this.diem = diem;
    }
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}


