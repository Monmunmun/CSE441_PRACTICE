package com.example.convertyear;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {


    String[] canArray = {"Giáp", "Ất", "Bính", "Đinh", "Mậu", "Kỷ", "Canh", "Tân", "Nhâm", "Quý"};


    String[] chiArray = {"Tý", "Sửu", "Dần", "Mão", "Thìn", "Tỵ", "Ngọ", "Mùi", "Thân", "Dậu", "Tuất", "Hợi"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button btnChuyenDoi = findViewById(R.id.btn_chuyen_doi);
        EditText edtDuongLich = findViewById(R.id.edt_duong_lich);
        EditText edtAmLich = findViewById(R.id.edt_am_lich);


        btnChuyenDoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String duongLichStr = edtDuongLich.getText().toString();
                if (!duongLichStr.isEmpty()) {
                    try {
                        int duongLich = Integer.parseInt(duongLichStr);

                        String amLich = convertToLunarYear(duongLich);

                        edtAmLich.setText(amLich);
                    } catch (NumberFormatException e) {
                        Toast.makeText(MainActivity.this, "Vui lòng nhập một số hợp lệ", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập năm dương lịch", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public String convertToLunarYear(int year) {

        int canIndex = (year + 6) % 10;

        int chiIndex = (year + 8) % 12;

        return canArray[canIndex] + " " + chiArray[chiIndex];
    }
}