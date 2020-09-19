package com.example.onlineelection;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.android.AndroidEventTarget;
import com.google.firebase.firestore.local.TargetData;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.List;
import android.util.Log;
import java.lang.reflect.Type;

public class MainActivity extends AppCompatActivity {

    Button button;
    List<Myclass> mclouddata=new ArrayList<>();
    private  MydataAdapter mAdapter;
    private RecyclerView recyclerView;
    ArrayAdapter<String> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button =  findViewById(R.id.logoutbtn);
        recyclerView=findViewById(R.id.recycleview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter= new MydataAdapter(mclouddata,getApplicationContext());


        recyclerView.setAdapter(mAdapter);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                finish();
            }
        });
    }
    public void List_Parties (View view){
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference("Parties");
      databaseReference.addValueEventListener(new ValueEventListener() {
          @Override
          public void onDataChange(@NonNull DataSnapshot snapshot) {
              mclouddata.clear();
              for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                  Myclass myclass=dataSnapshot.getValue(Myclass.class);
                  mclouddata.add(myclass);
              }
              mAdapter.notifyDataSetChanged();
          }

          @Override
          public void onCancelled(@NonNull DatabaseError error) {

          }
      });



    }
} 