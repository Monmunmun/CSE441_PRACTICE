package com.example.prac01;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    private EditText editTextFullName, editTextGPA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        editTextFullName = findViewById(R.id.editText_fullName);
        editTextGPA = findViewById(R.id.editText_gpa);
        Button buttonSubmit = findViewById(R.id.button_submit);

        buttonSubmit.setOnClickListener(v -> {
            String fullName = editTextFullName.getText().toString();
            float gpa = Float.parseFloat(editTextGPA.getText().toString());

            Intent resultIntent = new Intent();
            resultIntent.putExtra("fullName", fullName);
            resultIntent.putExtra("gpa", gpa);
            setResult(RESULT_OK, resultIntent); // Gửi dữ liệu về MainActivity
            finish(); // Đóng activity
        });
    }
}

