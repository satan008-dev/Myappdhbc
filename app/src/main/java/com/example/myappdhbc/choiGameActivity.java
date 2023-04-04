package com.example.myappdhbc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.*;

import com.bumptech.glide.Glide;
import com.example.myappdhbc.adapter.DapanAdapter;
import com.example.myappdhbc.model.choiGameModels;
import com.example.myappdhbc.object.Cauhoi;

import java.lang.String;
import java.util.ArrayList;
import java.util.Random;

public class choiGameActivity extends AppCompatActivity {
    choiGameModels models;
    Cauhoi cauHoi;

    private String dapAn = "xakep";
    ArrayList<String> arrCauTraLoi;
    GridView gdvCauTraLoi;

    int index = 0;

    ArrayList<String> arrDapAn;
    GridView gdvDapAn;
    ImageView imgCauhoi;
    TextView txvTienNguoiDung;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choigame_activity_main);
        anhxa();
        init();
        setOnclick();
        hienCauHoi();
    }

    private void anhxa() {
        gdvCauTraLoi = findViewById(R.id.gdvCauTraLoi);
        gdvDapAn = findViewById(R.id.gdvDapAn);
        imgCauhoi = findViewById(R.id.imgCauhoi);
        txvTienNguoiDung = findViewById(R.id.txvTienNguoiDung);
    }

    private void init() {
        models = new choiGameModels(this);
        arrCauTraLoi = new ArrayList<>();
        arrDapAn = new ArrayList<>();

    }

    private void hienCauHoi() {
        cauHoi = models.layCauHoi();
        dapAn = cauHoi.dapAn;
        xuLyData();
        hienThiCauTraLoi();
        hienThiDapAn();
        Glide.with(this)
                .load(cauHoi.anh)
                .into(imgCauhoi);
        models.layTT();
        txvTienNguoiDung.setText(models.nguoiDung.tien + "$");
    }

    private void hienThiCauTraLoi() {
        gdvCauTraLoi.setNumColumns(arrCauTraLoi.size());
        gdvCauTraLoi.setAdapter(new DapanAdapter(this, 0, arrCauTraLoi));

    }

    private void hienThiDapAn() {
        gdvDapAn.setNumColumns(arrDapAn.size() / 2);
        gdvDapAn.setAdapter(new DapanAdapter(this, 0, arrDapAn));
    }

    private void setOnclick() {
        gdvDapAn.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = (String) parent.getItemAtPosition(position);
                if (s.length() != 0 && index < arrCauTraLoi.size()) {

                    for (int i = 0; i < arrCauTraLoi.size(); i++) {
                        if (arrCauTraLoi.get(i).length() == 0) {
                            index = i;
                            break;
                        }
                    }
                    arrDapAn.set(position, "");
                    arrCauTraLoi.set(index, s);
                    index++;
                    hienThiCauTraLoi();
                    hienThiDapAn();
                    checkWin();
                }
            }
        });
        gdvCauTraLoi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = (String) parent.getItemAtPosition(position);
                if (s.length() != 0) {
                    index = position;

                    arrCauTraLoi.set(position, "");
                    for (int i = 0; i < arrDapAn.size(); i++) {
                        if (arrDapAn.get(i).length() == 0) {
                            arrDapAn.set(i, s);
                            break;
                        }
                    }
                    hienThiCauTraLoi();
                    hienThiDapAn();
                }
            }
        });
    }

    private void xuLyData() {
        index = 0;
        arrCauTraLoi.clear();
        arrDapAn.clear();
        Random r = new Random();
        for (int i = 0; i < dapAn.length(); i++) {
            arrCauTraLoi.add("");
            String s = "" + (char) (r.nextInt(26) + 65);
            arrDapAn.add(s);
            String s1 = "" + (char) (r.nextInt(26) + 65);
            arrDapAn.add(s1);
        }
        for (int i = 0; i < dapAn.length(); i++) {
            String s = "" + dapAn.charAt(i);
            arrDapAn.set(i, s.toUpperCase());
        }
        for (int i = 0; i < arrDapAn.size(); i++) {
            String s = arrDapAn.get(i);
            int vt = r.nextInt(arrDapAn.size());
            arrDapAn.set(i, arrDapAn.get(vt));
            arrDapAn.set(vt, s);
        }

    }

    private void checkWin() {
        String s = "";
        for (String s1 : arrCauTraLoi) {
            s = s + s1;
        }
        s = s.toUpperCase();
        if (s.equals(dapAn.toUpperCase())) {
            Toast.makeText(this, "Bạn Là Nhất", Toast.LENGTH_SHORT).show();
            models.layTT();
            models.nguoiDung.tien = models.nguoiDung.tien + 10;
            models.luuTT();
            hienCauHoi();

        }
    }

    public void moGoiy(View view) {
        models.layTT();
        if (models.nguoiDung.tien < 5) {
            Toast.makeText(this, "Bạn Đã Hết Tiền", Toast.LENGTH_SHORT).show();
            return;
        }
        int id = -1;
        for (int i = 0; i < arrCauTraLoi.size(); i++) {
            if (arrCauTraLoi.get(i).length() == 0) {
                id = i;
                break;
            }

        }
        if (id == -1) {
            for (int i = 0; i < arrCauTraLoi.size(); i++) {
                String s = dapAn.toUpperCase().charAt(i) + "";
                if (!arrCauTraLoi.get(i).toUpperCase().equals(s)) {
                    id = i;
                    break;
                }
            }
            for (int i = 0; i < arrDapAn.size(); i++) {
                if (arrDapAn.get(i).length() == 0) {
                    arrDapAn.set(i, arrCauTraLoi.get(id));
                    break;
                }

            }
        }
        String goiY = "" + dapAn.charAt(id);
        goiY = goiY.toUpperCase();
        for (int i = 0; i < arrCauTraLoi.size(); i++) {
            if (arrCauTraLoi.get(i).toUpperCase().equals(goiY)) {
                arrCauTraLoi.set(i, "");
                break;
            }
        }
        for (int i = 0; i < arrDapAn.size(); i++) {
            if (goiY.equals(arrDapAn.get(i))) {
                arrDapAn.set(i, "");
                break;
            }
        }
        arrCauTraLoi.set(id, goiY);
        hienThiCauTraLoi();
        hienThiDapAn();
        models.layTT();
        models.nguoiDung.tien = models.nguoiDung.tien - 20;
        models.luuTT();
        txvTienNguoiDung.setText(models.nguoiDung.tien + "$");
    }

    public void doiCauHoi(View view) {
        models.layTT();
        if (models.nguoiDung.tien < 15) {
            Toast.makeText(this, "Bạn Đã Hết Tiền", Toast.LENGTH_SHORT).show();
            return;
        }
        models.nguoiDung.tien = models.nguoiDung.tien - 15;
        models.luuTT();
        txvTienNguoiDung.setText(models.nguoiDung.tien + "$");
        hienCauHoi();

    }
    public void dung(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }


}
