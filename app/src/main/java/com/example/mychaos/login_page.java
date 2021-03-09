package com.example.mychaos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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

public class login_page extends AppCompatActivity {
    ProgressBar progressBar;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        TextView signup=(TextView)findViewById(R.id.signup);
        EditText email=(EditText)findViewById(R.id.email);
        EditText password=(EditText)findViewById(R.id.password);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        Button login=(Button)findViewById(R.id.login);
        TextView for_pass=(TextView)findViewById(R.id.forgot_pass);
        firebaseAuth=FirebaseAuth.getInstance();
        for_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText resetMail=new EditText(v.getContext());
                AlertDialog.Builder passres=new AlertDialog.Builder(v.getContext());
                passres.setTitle("Reset Password?");
                passres.setMessage("Enter your E-mail to reset password.");
                passres.setView(resetMail);
                passres.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String mail=resetMail.getText().toString();
                        firebaseAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(login_page.this,"Reset link sent to your E-mail",Toast.LENGTH_LONG).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(login_page.this,"Error! Reset Link not sent "+e.getMessage(),Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });
                passres.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //close dialog
                    }
                });
                passres.create().show();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String e,p;
                e=email.getText().toString().trim();
                p=password.getText().toString().trim();
                if (TextUtils.isEmpty(e)&& TextUtils.isEmpty(p)){
                    password.setError("PASSWORD IS REQUIRED");
                    email.setError("E-MAIL IS REQUIRED");
                    Toast.makeText(login_page.this,"FIELDS ARE REQUIRED",Toast.LENGTH_LONG).show();
                    return;
                }
                else  if(TextUtils.isEmpty(e)) {
                    email.setError("E-MAIL IS REQUIRED");
                    return;

                }
                else if(TextUtils.isEmpty(p)) {
                    password.setError("PASSWORD IS REQUIRED");
                    return;
                }
                else if(p.length()<6)
                {
                    password.setError("MUST BE GREATER THAN 6");
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                firebaseAuth.signInWithEmailAndPassword(e,p).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(login_page.this,"WELCOME TO MY CHAOS",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(login_page.this,opening.class));
                            finish();


                        }
                        else{
                            Toast.makeText(login_page.this,"ERROR:"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });

            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next();
            }
        });
    }
    void next()
    {
        Intent i=new Intent(this,signup.class);
        startActivity(i);
    }
}