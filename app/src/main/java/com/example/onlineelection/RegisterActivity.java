package com.example.onlineelection;

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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    public static final String TAG = "TAG";
    EditText mfullname, email, mpassword,mphone,mlastname;
    Button reigisterbtn, ifregisterbttn;
    ProgressBar progressBar;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firestore;
    String userID;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mfullname = findViewById(R.id.fullname);
        mlastname = findViewById(R.id.lastname);
        email = findViewById(R.id.Email);
        mpassword = findViewById(R.id.Password);
        mphone = findViewById(R.id.phoneNumber);
        reigisterbtn = findViewById(R.id.RegisterBtnm);
        ifregisterbttn = findViewById(R.id.ifregibtn);
        firebaseAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        progressBar = findViewById(R.id.progressBar);




        if(firebaseAuth.getCurrentUser()!= null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }


        reigisterbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String memail = email.getText().toString().trim();
                String password = mpassword.getText().toString().trim();
                 final String lastname = mlastname.getText().toString().trim();
                 final String Fullname = mfullname.getText().toString().trim();
                 final String PhoneNumber = mphone.getText().toString();



                if(TextUtils.isEmpty(memail)){
                    email.setError("Insert Your Email");
                }
                if(TextUtils.isEmpty(lastname)){
                    Toast.makeText(RegisterActivity.this, "Insert Your LastName", Toast.LENGTH_SHORT).show();

                }


                if(TextUtils.isEmpty(password)){

                    mpassword.setError("Password is Required");

                }
                if(password.length()<6){
                    mpassword.setError("Your password should be more than 6 characters");
                }
                progressBar.setVisibility(View.VISIBLE);
                firebaseAuth.createUserWithEmailAndPassword(memail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(RegisterActivity.this, "Successfully Created Account!", Toast.LENGTH_SHORT).show();
                            userID = firebaseAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = firestore.collection("Users").document(userID);
                            Map<String,Object> user = new HashMap<>();
                             user.put("Email",memail);
                            user.put("FirstName",Fullname);
                            user.put("LastName",lastname);
                            user.put("PhoneNumber",PhoneNumber);
                             documentReference.set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                 @Override
                                 public void onComplete(@NonNull Task<Void> task) {
                                      if (task.isSuccessful()){
                                          Log.d("Sucess","Entered Data");
                                          startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                      }   else{
                                          Toast.makeText(RegisterActivity.this, "error:"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                      }
                                 }
                             });
                        }else {

                            Toast.makeText(RegisterActivity.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }

                    }
                });
            }
        });



    }
}
