package com.example.personal_information;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
 
    private EditText edtTen, edtCMND, edtBoSung;
    private CheckBox chkDocBao, chkDocSach, chkDocCode;
    private Button btnSend;
    private RadioGroup group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        edtTen = findViewById(R.id.edtten);
        edtCMND = findViewById(R.id.edtcmnd);
        edtBoSung = findViewById(R.id.edtbosung);
        chkDocBao = findViewById(R.id.chkdocbao);
        chkDocSach = findViewById(R.id.chkdocsach);
        chkDocCode = findViewById(R.id.chkcode);
        btnSend = findViewById(R.id.btnsend);
        group = findViewById(R.id.idgroup);


        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doShowInformation();
            }
        });
    }


    public void doShowInformation() {

        String ten = edtTen.getText().toString().trim();
        if (ten.length() < 3) {
            edtTen.requestFocus();
            edtTen.selectAll();
            Toast.makeText(this, "Tên phải >= 3 ký tự", Toast.LENGTH_LONG).show();
            return;
        }


        String cmnd = edtCMND.getText().toString().trim();
        if (cmnd.length() != 9) {
            edtCMND.requestFocus();
            edtCMND.selectAll();
            Toast.makeText(this, "CMND phải đúng 9 ký tự", Toast.LENGTH_LONG).show();
            return;
        }


        String bangCap = "";
        int selectedId = group.getCheckedRadioButtonId();
        if (selectedId == -1) {
            Toast.makeText(this, "Phải chọn bằng cấp", Toast.LENGTH_LONG).show();
            return;
        }
        RadioButton radBangCap = findViewById(selectedId);
        bangCap = radBangCap.getText().toString();


        StringBuilder soThich = new StringBuilder();
        if (chkDocBao.isChecked()) {
            soThich.append(chkDocBao.getText()).append("\n");
        }
        if (chkDocSach.isChecked()) {
            soThich.append(chkDocSach.getText()).append("\n");
        }
        if (chkDocCode.isChecked()) {
            soThich.append(chkDocCode.getText()).append("\n");
        }


        String boSung = edtBoSung.getText().toString().trim();


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thông tin cá nhân");
        builder.setPositiveButton("Đóng", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });


        String message = ten + "\n" +
                cmnd + "\n" +
                bangCap + "\n" +
                soThich +
                "—————————–\n" +
                "Thông tin bổ sung:\n" +
                boSung + "\n" +
                "—————————–";

        builder.setMessage(message);
        builder.create().show();
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Question");
        builder.setMessage("Are you sure you want to exit?");
        builder.setIcon(R.drawable.inform);


        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish(); // Đóng ứng dụng
            }
        });


        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });


        builder.create().show();
    }
}
