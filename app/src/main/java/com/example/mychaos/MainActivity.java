package com.example.mychaos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    FirebaseAuth.AuthStateListener mAuthListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView t=(TextView) findViewById(R.id.tap);
        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth = FirebaseAuth.getInstance();

                mAuthListener = new FirebaseAuth.AuthStateListener(){
                    @Override
                    public  void  onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth){
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        if(user!=null){
                            Intent intent = new Intent(MainActivity.this,opening.class);
                            startActivity(intent);
                            finish();
                        }
                    }


                };
                next();
            }
        });
    }

    public void next()
    {
        Intent i=new Intent(this,login_page.class);
        startActivity(i);
        finish();
    }
}