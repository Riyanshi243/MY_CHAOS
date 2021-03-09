package com.example.mychaos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import java.sql.Time;

public class Timetable extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);
        Toast.makeText(Timetable.this,"TIMETABLE WILL BE SHOWN HERE",Toast.LENGTH_LONG).show();
    }
}