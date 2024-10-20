package com.example.ptb2;

import android.app.Activity;
import android.icu.text.DecimalFormat;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
    Button btnTieptuc, btnGiai, btnThoat;
    EditText edita, editb, editc;
    TextView txtkq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnTieptuc = findViewById(R.id.btntieptuc);
        btnGiai = findViewById(R.id.btngiaipt);
        btnThoat = findViewById(R.id.btnthoat);
        edita = findViewById(R.id.edta);
        editb = findViewById(R.id.edtb);
        editc = findViewById(R.id.edtc);
        txtkq = findViewById(R.id.txtkq);

        btnGiai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sa = edita.getText().toString();
                String sb = editb.getText().toString();
                String sc = editc.getText().toString();

                if (sa.isEmpty() || sb.isEmpty() || sc.isEmpty()) {
                    txtkq.setText("Vui lòng nhập đủ giá trị a, b, c");
                    return;
                }

                double a = Double.parseDouble(sa);
                double b = Double.parseDouble(sb);
                double c = Double.parseDouble(sc);
                String kq = "";
                DecimalFormat dcf = new DecimalFormat("0.00");

                if (a == 0) {
                    if (b == 0) {
                        if (c == 0) {
                            kq = "PT vô số nghiệm";
                        } else {
                            kq = "PT vô nghiệm";
                        }
                    } else {
                        kq = "PT có 1 nghiệm: x = " + dcf.format(-c / b);
                    }
                } else {
                    double delta = b * b - 4 * a * c;
                    if (delta < 0) {
                        kq = "PT vô nghiệm";
                    } else if (delta == 0) {
                        kq = "PT có nghiệm kép: x1 = x2 = " + dcf.format(-b / (2 * a));
                    } else {
                        double x1 = (-b + Math.sqrt(delta)) / (2 * a);
                        double x2 = (-b - Math.sqrt(delta)) / (2 * a);
                        kq = "PT có 2 nghiệm: x1 = " + dcf.format(x1) + "; x2 = " + dcf.format(x2);
                    }
                }
                txtkq.setText(kq);
            }
        });

        btnTieptuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edita.setText("");
                editb.setText("");
                editc.setText("");
                edita.requestFocus();
                txtkq.setText("");
            }
        });

        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
