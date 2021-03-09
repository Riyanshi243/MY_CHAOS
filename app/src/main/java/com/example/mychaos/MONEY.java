package com.example.mychaos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class MONEY extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m_o_n_e_y);
        Toast.makeText(MONEY.this,"BUDGET WILL BE MANAGED HERE",Toast.LENGTH_LONG).show();
    }
}