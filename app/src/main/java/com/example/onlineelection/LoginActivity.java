package com.example.onlineelection;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {
    EditText email,password;
    Button createAccount, login;
    FirebaseAuth auth;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        createAccount = findViewById(R.id.Creatbtn);
        login = findViewById(R.id.Loginbtn);
       auth=FirebaseAuth.getInstance();
       email=findViewById(R.id.email);
       password=findViewById(R.id.password);
       user=auth.getCurrentUser();
       if (user!=null){
           startActivity(new Intent(getApplicationContext(),MainActivity.class));
           finish();
       }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String memail=email.getText().toString();
                String mpassword=password.getText().toString();
                auth.signInWithEmailAndPassword(memail,mpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }    else{
                            Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),RegisterActivity.class));
                finish();
            }
        });
    }

}