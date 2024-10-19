package com.example.sinhvien;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditStudentActivity extends AppCompatActivity {

    private EditText etName, etClass, etGpa;
    private Button btnSaveChanges;
    private DatabaseReference databaseReference;
    private String studentKey; // Thay đổi tên biến thành studentKey

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);

        etName = findViewById(R.id.et_student_name);
        etClass = findViewById(R.id.et_student_class);
        etGpa = findViewById(R.id.et_student_gpa);
        btnSaveChanges = findViewById(R.id.btn_save_changes);


        studentKey = getIntent().getStringExtra("studentKey"); // Lấy studentKey
        String studentName = getIntent().getStringExtra("studentName");
        String studentClass = getIntent().getStringExtra("studentClass");
        String studentGpa = getIntent().getStringExtra("studentGpa");


        etName.setText(studentName);
        etClass.setText(studentClass);
        etGpa.setText(studentGpa);


        databaseReference = FirebaseDatabase.getInstance().getReference("sinhvien").child(studentKey);


        btnSaveChanges.setOnClickListener(view -> {
            String newName = etName.getText().toString().trim();
            String newClass = etClass.getText().toString().trim();
            String newGpa = etGpa.getText().toString().trim();

            if (TextUtils.isEmpty(newName) || TextUtils.isEmpty(newClass) || TextUtils.isEmpty(newGpa)) {
                Toast.makeText(EditStudentActivity.this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }


            Student updatedStudent = new Student(newName, newClass, Double.parseDouble(newGpa));
            databaseReference.setValue(updatedStudent)
                    .addOnSuccessListener(aVoid -> {
                        Toast.makeText(EditStudentActivity.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                        finish();
                    })
                    .addOnFailureListener(e -> Toast.makeText(EditStudentActivity.this, "Cập nhật thất bại", Toast.LENGTH_SHORT).show());
        });
    }
}
