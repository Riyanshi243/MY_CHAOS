
package com.example.mychaos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class signup extends AppCompatActivity {
    EditText name,username,email,phone,passw,dob;
    TextView already;
    Button signup;
    ProgressBar progressBar;
    private FirebaseAuth firebaseAuth;
    FirebaseFirestore firestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        name=(EditText)findViewById(R.id.name);
        username=(EditText)findViewById(R.id.username);
        email=(EditText)findViewById(R.id.mail);
        phone=(EditText)findViewById(R.id.phone);
        passw=(EditText)findViewById(R.id.passw);
        dob=(EditText)findViewById(R.id.dob);
        signup=(Button)findViewById(R.id.signupnew);
        progressBar=(ProgressBar)findViewById(R.id.progressBar2);
        already=(TextView)findViewById(R.id.already_in);
        firebaseAuth=FirebaseAuth.getInstance();
        already.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(signup.this, login_page.class);
                startActivity(intent);
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String e,p,n,pn,u,d;
                n=name.getText().toString().trim();
                u=username.getText().toString().trim();
                e=email.getText().toString().trim();
                pn=phone.getText().toString().trim();
                d=dob.getText().toString().trim();
                p=passw.getText().toString().trim();
                if (TextUtils.isEmpty(n)){
                    name.setError("NAME IS REQUIRED");
                    return;
                }
                if (TextUtils.isEmpty(u)){
                    username.setError("USERNAME IS REQUIRED");
                    return;
                }
                if(TextUtils.isEmpty(e)) {
                    email.setError("E-MAIL IS REQUIRED");
                    return;

                }
                if (TextUtils.isEmpty(pn)){
                    phone.setError("PHONE NUMBER IS REQUIRED");
                    return;
                }
                if (TextUtils.isEmpty(d)){
                    dob.setError("DATE OF BIRTH IS REQUIRED");
                    return;
                }
                if(TextUtils.isEmpty(p)) {
                    passw.setError("PASSWORD IS REQUIRED");
                    return;
                }
                if(p.length()<6)
                {
                    passw.setError("MUST BE GREATER THAN 6");
                    return;
                }
                if (pn.length()!=10)
                {
                    phone.setError("Enter a valid number");
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                firestore=FirebaseFirestore.getInstance();

                firebaseAuth.createUserWithEmailAndPassword(e,p).addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            FirebaseUser fuser = firebaseAuth.getCurrentUser();
                            fuser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(signup.this, "VERIFICATION LINK SENT.VERIFY YOUR EMAIL", Toast.LENGTH_LONG).show();

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(signup.this, "Email not sent " + e.getMessage(), Toast.LENGTH_LONG).show();

                                }
                            });
                            Toast.makeText(signup.this,"NOW LOGIN",Toast.LENGTH_SHORT).show();
                            String userId=firebaseAuth.getCurrentUser().getUid();
                           FirebaseDatabase database=FirebaseDatabase.getInstance();
                            DatabaseReference myref=database.getReference("DETAILS");
                            details de=new details(userId,n,e,pn,u,d,"0");
                            myref.child(userId).setValue(de);
                            startActivity(new Intent(getApplicationContext(),login_page.class));
                            finish();
                        }
                        else
                        {
                            Toast.makeText(signup.this,"ERROR:"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(signup.this,"FAILED ",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        String uid=firebaseAuth.getCurrentUser().getUid();
        Log.d("TAG", uid);

        firestore=FirebaseFirestore.getInstance();
        DocumentReference documentReference=firestore.collection("DATA").document(uid);
        Map<String,Object> user=new HashMap<>();
        user.put("NOTE",0);

        documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

            }
        });


    }
}