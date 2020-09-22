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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    public static final String TAG = "TAG";
    EditText mfullname, email, mpassword,mphone,mlastname,mkebele,mwereda,msubsity,mcity,mhousenumber;
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
        mkebele=findViewById(R.id.Kebele);
        msubsity=findViewById(R.id.subcity);
        mhousenumber=findViewById(R.id.houseNo);
        mwereda=findViewById(R.id.woreda);
        mcity=findViewById(R.id.City);
        reigisterbtn = findViewById(R.id.RegisterBtnm);
        ifregisterbttn = findViewById(R.id.ifregibtn);
        firebaseAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        progressBar = findViewById(R.id.progressBar);
        reigisterbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String memail = email.getText().toString().trim();
                String password = mpassword.getText().toString().trim();
                 final String lastname = mlastname.getText().toString().trim();
                 final String Fullname = mfullname.getText().toString().trim();
                 final String PhoneNumber = mphone.getText().toString();
                 final String city=mcity.getText().toString();
                final String subcity=msubsity.getText().toString();
                final String kebele=mkebele.getText().toString();
                final String woreda=mwereda.getText().toString();
                final String houseno=mhousenumber.getText().toString();


                if(TextUtils.isEmpty(memail)){
                    email.setError("Insert Your Email");
                }
                if(TextUtils.isEmpty(lastname)){
                    Toast.makeText(RegisterActivity.this, "Insert Your LastName", Toast.LENGTH_SHORT).show();

                }
                if (TextUtils.isEmpty(city)&&TextUtils.isEmpty(kebele)&&TextUtils.isEmpty(woreda)&&TextUtils.isEmpty(subcity)&&TextUtils.isEmpty(houseno)){
                    Toast.makeText(RegisterActivity.this, "Please Fill in the Forms", Toast.LENGTH_SHORT).show();
                    //add set error to them all
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
                            user.put("Kebele",kebele);
                            user.put("Woreda",woreda);
                            user.put("City",city);
                            user.put("SubCity",subcity);
                            user.put("HouseNumber",houseno);
                            documentReference.set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                   if (task.isSuccessful()){
                                       Toast.makeText(RegisterActivity.this, "Created Account", Toast.LENGTH_SHORT).show();
                                       startActivity(new Intent(getApplicationContext(),Mydrawerpage.class));

                                   }else{
                                       Toast.makeText(RegisterActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
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
