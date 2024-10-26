package com.example.note;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    // Khai báo các biến
    ListView lv;
    ArrayList<String> arraywork;
    ArrayAdapter<String> arrAdapter;
    EditText edtwork, edth, edtm;
    TextView txtdate;
    Button btnwork;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edth = findViewById(R.id.edthour);
        edtm = findViewById(R.id.edtmi);
        edtwork = findViewById(R.id.edtwork);
        btnwork = findViewById(R.id.btnadd);
        lv = findViewById(R.id.listView1);
        txtdate = findViewById(R.id.txtdate);

        arraywork = new ArrayList<>();

        arrAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arraywork);

        lv.setAdapter(arrAdapter);

        Date currentDate = Calendar.getInstance().getTime();


        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");


        txtdate.setText("Hôm Nay: " + simpleDateFormat.format(currentDate));


        btnwork.setOnClickListener(v -> {

            if (edtwork.getText().toString().equals("") || edth.getText().toString().equals("") || edtm.getText().toString().equals("")) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Thiếu thông tin");
                builder.setMessage("Vui lòng nhập đầy đủ thông tin công việc");
                builder.setPositiveButton("Tiếp tục", (dialog, which) -> {

                });
                builder.show();
            } else {

                String str = edtwork.getText().toString() + " - " + edth.getText().toString() + ":" + edtm.getText().toString();

                arraywork.add(str);


                arrAdapter.notifyDataSetChanged();

                edth.setText("");
                edtm.setText("");
                edtwork.setText("");
            }
        });
    }
}