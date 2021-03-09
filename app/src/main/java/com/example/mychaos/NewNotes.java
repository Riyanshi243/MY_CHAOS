package com.example.mychaos;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static java.lang.Math.toIntExact;

public class NewNotes extends AppCompatActivity {
Date time;
    FirebaseFirestore firestore;
    FirebaseAuth firebaseAuth;
    String uid,uid2;
    static int count;
    DocumentReference dRef;
    DocumentReference documentReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_notes);
        EditText notes=(EditText)findViewById(R.id.new_notes);
        Button cancel=(Button)findViewById(R.id.cancel);
        Button save=(Button)findViewById(R.id.save);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(NewNotes.this,Notes.class);
                startActivity(i);
                finish();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* String note=notes.getText().toString();
                time= Calendar.getInstance().getTime();
                note=time.toString()+'\n'+note;
                firebaseAuth=FirebaseAuth.getInstance();
                firestore=FirebaseFirestore.getInstance();
                uid= firebaseAuth.getCurrentUser().getUid();
                dRef= firestore.collection("DATA").document(uid);
                dRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if(documentSnapshot.exists())
                            {
                                Map<String,Object> n=documentSnapshot.getData();
                            }
                            //assert documentSnapshot!=null;
                            NewNotes.count= toIntExact( documentSnapshot.getLong("NOTE"));
                            HashMap<String, Object> m=new HashMap<>();
                            m.put("NOTE",(NewNotes.count+1));
                            dRef.update(m);
                        }
                    }
                );
                Map<String,Object > user=new HashMap<>();
                int k=1+NewNotes.count;
                String name=Integer.toString(k);
                user.put(name,note);
                dRef.update(user);

*/
                Toast.makeText(NewNotes.this,"NOTES ADDED",Toast.LENGTH_LONG).show();
                Intent i=new Intent(NewNotes.this,Notes.class);
                startActivity(i);
                finish();

            }
        });
    }
}