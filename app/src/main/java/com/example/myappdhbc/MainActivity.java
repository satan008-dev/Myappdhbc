package com.example.myappdhbc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myappdhbc.api.layCauHoi;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new layCauHoi().execute();
    }

    public void batDauChoi(View view) {
      if(DATA.getData().arrCauHoi.size()>0){
          startActivity(new Intent(this, choiGameActivity.class));
      }
    }
}
