package com.example.prac02;

public class Staff {
    private String staffId;
    private String staffFullName;
    private String birthDate;
    private int salary;

    public Staff(String staffId, String staffFullName, String birthDate, int salary) {
        this.staffId = staffId;
        this.staffFullName = staffFullName;
        this.birthDate = birthDate;
        this.salary = salary;
    }

    public String getStaffId() {
        return staffId;
    }

    public String getStaffFullName() {
        return staffFullName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public int getSalary() {
        return salary;
    }
}
