package com.example.prac02;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private StaffViewModel staffViewModel;
    private EditText etStaffId, etStaffFullName, etBirthDate, etSalary;
    private TextView tvResult, tvStatus;
    private Button btnAddStaff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etStaffId = findViewById(R.id.etStaffId);
        etStaffFullName = findViewById(R.id.etStaffFullName);
        etBirthDate = findViewById(R.id.etBirthDate);
        etSalary = findViewById(R.id.etSalary);
        tvResult = findViewById(R.id.tvResult);
        tvStatus = findViewById(R.id.tvStatus);
        btnAddStaff = findViewById(R.id.btnAddStaff);

        staffViewModel = new ViewModelProvider(this).get(StaffViewModel.class);
        staffViewModel.getStaffList().observe(this, new Observer<List<Staff>>() {
            @Override
            public void onChanged(List<Staff> staffList) {
                if (staffList.isEmpty()) {
                    tvResult.setText("No Result!");
                    tvStatus.setText("Chưa nhập dữ liệu");
                } else {
                    StringBuilder result = new StringBuilder();
                    for (Staff staff : staffList) {
                        result.append(staff.getStaffId()).append("-")
                                .append(staff.getStaffFullName()).append("-")
                                .append(staff.getBirthDate()).append("-")
                                .append(staff.getSalary()).append("\n");
                    }
                    tvResult.setText(result.toString());
                    if (staffList.size() == 1) {
                        tvStatus.setText("Đã nhấn nút thêm mới");
                    } else if (staffList.size() > 1) {
                        tvStatus.setText("Sau khi thêm vài nhân viên");
                    }
                }
            }
        });

        TextWatcher inputWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkIfAllFieldsAreFilled();
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        };
        etStaffId.addTextChangedListener(inputWatcher);
        etStaffFullName.addTextChangedListener(inputWatcher);
        etBirthDate.addTextChangedListener(inputWatcher);
        etSalary.addTextChangedListener(inputWatcher);
    }

    private void checkIfAllFieldsAreFilled() {
        String staffId = etStaffId.getText().toString();
        String staffFullName = etStaffFullName.getText().toString();
        String birthDate = etBirthDate.getText().toString();
        String salary = etSalary.getText().toString();

        if (!staffId.isEmpty() && !staffFullName.isEmpty() && !birthDate.isEmpty() && !salary.isEmpty()) {
            tvStatus.setText("Đã nhập nhưng chưa nhấn nút");
            btnAddStaff.setEnabled(true);
        } else {
            btnAddStaff.setEnabled(false);
        }
    }

    public void addNewStaff(View view) {
        String staffId = etStaffId.getText().toString();
        String staffFullName = etStaffFullName.getText().toString();
        String birthDate = etBirthDate.getText().toString();
        String salaryStr = etSalary.getText().toString();
        if (staffId.isEmpty() || staffFullName.isEmpty() || birthDate.isEmpty() || salaryStr.isEmpty()) {
            tvStatus.setText("Vui lòng nhập đủ dữ liệu trước khi thêm");
            return;
        }
        int salary;
        try {
            salary = Integer.parseInt(salaryStr);
        } catch (NumberFormatException e) {
            tvStatus.setText("Lỗi: Lương phải là số");
            return;
        }
        Staff newStaff = new Staff(staffId, staffFullName, birthDate, salary);
        staffViewModel.addStaff(newStaff);
        etStaffId.setText("");
        etStaffFullName.setText("");
        etBirthDate.setText("");
        etSalary.setText("");
    }
}