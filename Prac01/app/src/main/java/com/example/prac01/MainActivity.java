package com.example.prac01;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView textViewResult;
    private ActivityResultLauncher<Intent> activityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonStart = findViewById(R.id.button_start_activity);
        textViewResult = findViewById(R.id.textView_result);

        activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {

                        Intent data = result.getData();
                        String fullName = data.getStringExtra("fullName");
                        float gpa = data.getFloatExtra("gpa", 0);


                        textViewResult.setText("Họ và tên: " + fullName + "\nĐiểm GPA: " + gpa);
                    }
                }
        );


        buttonStart.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            activityResultLauncher.launch(intent); //
        });
    }
}
