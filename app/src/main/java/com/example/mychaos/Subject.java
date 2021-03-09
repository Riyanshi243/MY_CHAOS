package com.example.mychaos;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.SignInButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Subject extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);
        FloatingActionButton f=(FloatingActionButton)findViewById(R.id.add);
        f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText resetMail=new EditText(v.getContext());
                AlertDialog.Builder passres=new AlertDialog.Builder(v.getContext());
                passres.setTitle("ENTER NAME OF THE SUBJECT");
                passres.setView(resetMail);
                passres.setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String sub=resetMail.getText().toString();
                        FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
                        String uid=firebaseAuth.getCurrentUser().getUid();
                        FirebaseFirestore firestore=FirebaseFirestore.getInstance();

                    DocumentReference dRef= firestore.collection("DATA").document(uid).collection("SUBJECTS").document(sub);
                    }
                });
                passres.create().show();
                Toast.makeText(Subject.this,"SUBJECT ADDED",Toast.LENGTH_LONG).show();
            }
        });
    }
}