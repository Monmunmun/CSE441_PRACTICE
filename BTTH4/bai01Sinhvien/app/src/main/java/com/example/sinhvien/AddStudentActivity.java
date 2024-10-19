package com.example.sinhvien;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddStudentActivity extends AppCompatActivity {

    private EditText etHoTen, etLop, etDiem;
    private Button btnAddStudent;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);


        etHoTen = findViewById(R.id.et_hoten);
        etLop = findViewById(R.id.et_lop);
        etDiem = findViewById(R.id.et_diem);
        btnAddStudent = findViewById(R.id.btn_add_student);


        databaseReference = FirebaseDatabase.getInstance().getReference("sinhvien");

        btnAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String hoTen = etHoTen.getText().toString().trim();
                String lop = etLop.getText().toString().trim();
                String diemString = etDiem.getText().toString().trim();

                if (hoTen.isEmpty() || lop.isEmpty() || diemString.isEmpty()) {
                    Toast.makeText(AddStudentActivity.this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }

                double diem = Double.parseDouble(diemString);


                String mssv = "mssv" + System.currentTimeMillis();


                Student student = new Student(hoTen, lop, diem);
                databaseReference.child(mssv).setValue(student)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                Toast.makeText(AddStudentActivity.this, "Thêm sinh viên thành công", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(AddStudentActivity.this, "Lỗi khi thêm sinh viên", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}


