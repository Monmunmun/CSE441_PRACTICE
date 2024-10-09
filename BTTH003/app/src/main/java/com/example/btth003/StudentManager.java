package com.example.btth003;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class StudentManager {

    private Context context;
    private Gson gson;

    public StudentManager(Context context) {
        this.context = context;
        this.gson = new GsonBuilder().create();
    }

    public void saveStudent(Student student) {
        try {
            FileOutputStream fos = context.openFileOutput("student_" + student.getId() + ".txt", Context.MODE_PRIVATE);
            String studentJson = gson.toJson(student);
            fos.write(studentJson.getBytes(StandardCharsets.UTF_8));
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Student> loadStudents() {
        List<Student> students = new ArrayList<>();
        String[] files = context.fileList();
        for (String file : files) {
            if (file.startsWith("student_")) {
                try {
                    FileInputStream fis = context.openFileInput(file);
                    InputStreamReader inputStreamReader = new InputStreamReader(fis, StandardCharsets.UTF_8);
                    BufferedReader reader = new BufferedReader(inputStreamReader);
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        stringBuilder.append(line);
                    }
                    String studentJson = stringBuilder.toString();
                    Student student = gson.fromJson(studentJson, Student.class);
                    students.add(student);
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return students;
    }

    public void deleteStudent(Student student) {
        context.deleteFile("student_" + student.getId() + ".txt");
    }

    public Student getStudentById(int studentId) {
        try {
            FileInputStream fis = context.openFileInput("student_" + studentId + ".txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fis, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            String studentJson = stringBuilder.toString();
            Student student = gson.fromJson(studentJson, Student.class);
            fis.close();
            return student;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
