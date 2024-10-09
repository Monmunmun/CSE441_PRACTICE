package com.example.btth003;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements StudentAdapter.OnStudentListener {

    private RecyclerView studentListRecyclerView;
    private StudentAdapter studentAdapter;
    private List<Student> students;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        studentListRecyclerView = findViewById(R.id.student_list);
        studentListRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        loadStudents();


        FloatingActionButton addStudentButton = findViewById(R.id.add_student_button);
        addStudentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddStudentActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadStudents();
    }
    private void loadStudents() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(getAssets().open("students.gson")))) {
            Gson gson = new Gson();
            students = gson.fromJson(reader, new TypeToken<List<Student>>() {}.getType());
            if (students == null) {
                students = new ArrayList<>();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        studentAdapter = new StudentAdapter(students, this);
        studentListRecyclerView.setAdapter(studentAdapter);
    }

    @Override
    public void onStudentClick(int position) {
        Intent intent = new Intent(MainActivity.this, StudentDetailActivity.class);
        intent.putExtra("student_id", students.get(position).getId());
        startActivity(intent);
    }
}
