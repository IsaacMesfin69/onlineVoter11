package com.example.onlineelection;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class Profile extends Mydrawerpage {

    TextView yourname, youremail, yourphone,lastname;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firestore;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.addContentView(R.layout.activity_profile);
        yourname = findViewById(R.id.Yournameprofile);
        yourphone = findViewById(R.id.yourphoneProfile);
        youremail= findViewById(R.id.YourEmail);
        lastname = findViewById(R.id.yourlastname);
        firebaseAuth= FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        userId = firebaseAuth.getCurrentUser().getUid();
        final DocumentReference documentReference = firestore.collection("User").document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                yourphone.setText(value.getString("PhoneNumber"));
                yourname.setText(value.getString("FirstName"));
                youremail.setText(value.getString("Email"));
                lastname.setText(value.getString("LastName"));
            }
        });
    }
}