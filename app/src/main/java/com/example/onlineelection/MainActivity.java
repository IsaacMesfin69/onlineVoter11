package com.example.onlineelection;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.android.AndroidEventTarget;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.local.TargetData;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import java.lang.reflect.Type;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    ArrayList<Myclass> mTargetData = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private MyAdapter mAdapter;
    Button send;
    FirebaseAuth firebaseAuth;
    String userID;
    FirebaseFirestore firestore;
    EditText partyname, partyid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        send= findViewById(R.id.send);
        partyid= findViewById(R.id.idvoted);
        partyname = findViewById(R.id.votedname);
        firebaseAuth= FirebaseAuth.getInstance();

        mRecyclerView=findViewById(R.id.recycleview);
        mAdapter=new MyAdapter(mTargetData);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new MyAdapter(mTargetData);
        mRecyclerView.setAdapter(mAdapter);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String PartyName = partyname.getText().toString().trim();
                String PartyId = partyid.getText().toString().trim();



                CollectionReference voice = firestore.collection("users");
                UserVoice userVoice = new UserVoice(PartyName,PartyId);

                voice.add(userVoice).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {

                        Toast.makeText(MainActivity.this, "Successfully Sent! ", Toast.LENGTH_SHORT).show();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this,e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });



                partyid.setVisibility(View.INVISIBLE);
                partyname.setVisibility(View.INVISIBLE);
            }
        });

    }

        public void List_Parties (View view){
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference("Parties");
      databaseReference.addValueEventListener(new ValueEventListener() {
          @Override
          public void onDataChange(@NonNull DataSnapshot snapshot) {
              mTargetData.clear();
              for (DataSnapshot dataSnapshot:snapshot.getChildren()){

                  Myclass myclass=dataSnapshot.getValue(Myclass.class);
                  mTargetData.add(myclass);
              }
              mAdapter.notifyDataSetChanged();
              Log.e("Value", "Data received:" + mTargetData.size());



          }

          @Override
          public void onCancelled(@NonNull DatabaseError error) {
              Log.w("ERROR", "fetchData onCancelled", error.toException());

          }
      });



    }


} 