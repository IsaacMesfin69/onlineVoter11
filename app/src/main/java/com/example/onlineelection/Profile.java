package com.example.onlineelection;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class Profile extends AppCompatActivity {

    TextView yourname, youremail, yourphone,lastname;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firestore;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        yourname = findViewById(R.id.Yournameprofile);
        yourphone = findViewById(R.id.yourphoneProfile);
        youremail= findViewById(R.id.YourEmail);
        lastname = findViewById(R.id.yourlastname);
        firebaseAuth= FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        userId = firebaseAuth.getCurrentUser().getUid();
        final DocumentReference documentReference = firestore.collection("Users").document(userId);
        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
           DocumentSnapshot documentSnapshot=task.getResult();
           if (documentSnapshot.exists()){
               yourname.setText(documentSnapshot.get("FirstName").toString());
               lastname.setText(documentSnapshot.get("LastName").toString());
               youremail.setText(documentSnapshot.get("Email").toString());
               yourphone.setText(documentSnapshot.get("PhoneNumber").toString());
           }
            }
        });
    }
}