package com.example.travetonghieu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


public class SubActivity extends AppCompatActivity {
    private EditText edtAA, edtBB;
    private Button btnSendTong, btnSendHieu;
    private Intent myIntent;
    private int a, b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        // Ánh xạ các thành phần UI
        edtAA = findViewById(R.id.edtAA);
        edtBB = findViewById(R.id.edtBB);
        btnSendTong = findViewById(R.id.btnsendtong);
        btnSendHieu = findViewById(R.id.btnsendhieu);

        // Nhận Intent và lấy dữ liệu
        myIntent = getIntent();
        a = myIntent.getIntExtra("soa", 0);
        b = myIntent.getIntExtra("sob", 0);

        // Hiển thị giá trị a và b trong EditText
        edtAA.setText(String.valueOf(a));
        edtBB.setText(String.valueOf(b));

        // Xử lý nút gửi tổng
        btnSendTong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Tính tổng
                int sum = a + b;
                // Đẩy kết quả trở lại Intent
                myIntent.putExtra("kq", sum);
                setResult(33, myIntent);
                // Thoát Activity
                finish();
            }
        });

        // Xử lý nút gửi hiệu
        btnSendHieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Tính hiệu
                int sub = a - b;
                myIntent.putExtra("kq", sub);
                setResult(34, myIntent);
                // Thoát Activity
                finish();
            }
        });
    }
}
