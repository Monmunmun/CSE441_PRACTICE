package com.example.btth003;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class AddStudentActivity extends AppCompatActivity {

    private EditText etHoTen;
    private EditText etNgaySinh;
    private EditText etEmail;
    private EditText etDiaChi;
    private EditText etChuyenNganh;
    private EditText etGpa;
    private EditText etGioiTinh;
    private StudentManager studentManager;
    private int studentId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        studentManager = new StudentManager(this);

        etHoTen = findViewById(R.id.etHoTen);
        etNgaySinh = findViewById(R.id.etNgaySinh);
        etEmail = findViewById(R.id.etEmail);
        etDiaChi = findViewById(R.id.etDiaChi);
        etChuyenNganh = findViewById(R.id.etChuyenNganh);
        etGpa = findViewById(R.id.etGPA);
        etGioiTinh = findViewById(R.id.etGioiTinh);

        Button btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hoTen = etHoTen.getText().toString();
                String ngaySinh = etNgaySinh.getText().toString();
                String email = etEmail.getText().toString();
                String diaChi = etDiaChi.getText().toString();
                String chuyenNganh = etChuyenNganh.getText().toString();
                double gpa = Double.parseDouble(etGpa.getText().toString());
                String gioiTinh = etGioiTinh.getText().toString();


                Student newStudent = new Student(generateNewStudentId(), hoTen, ngaySinh, email, diaChi, chuyenNganh, gpa, gioiTinh);
                studentManager.saveStudent(newStudent);


                Intent intent = new Intent(AddStudentActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
    }

    private int generateNewStudentId() {
        int maxId = 0;
        List<Student> students = studentManager.loadStudents();
        for (Student student : students) {
            if (student.getId() > maxId) {
                maxId = student.getId();
            }
        }
        return maxId + 1;
    }
}

