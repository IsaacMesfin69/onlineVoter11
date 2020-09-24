package com.example.onlineelection;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class SelectParty extends AppCompatActivity {
    TextView partyname,partyid;
    Button votefor;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_party);
        partyname=findViewById(R.id.partyname);
        partyid=findViewById(R.id.partyid);
        votefor=findViewById(R.id.vote);
        partyname.setText(getIntent().getStringExtra("Partyname"));
        partyid.setText(getIntent().getStringExtra("id"));
        votefor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String party=partyname.getText().toString();
                String id=partyid.getText().toString();
                FirebaseFirestore firestore=FirebaseFirestore.getInstance();
                FirebaseAuth auth=FirebaseAuth.getInstance();
                String userid=auth.getCurrentUser().getUid();
                DocumentReference reference=firestore.collection("Users").document(userid).collection("VotedVor").document("party");
                Map<String,Object> map=new HashMap<>();
                map.put("Partname",party);
                map.put("id",id);
                reference.set(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                      if (task.isSuccessful()){

                          SweetAlertDialog sweetAlertDialog=new SweetAlertDialog(SelectParty.this);
                          sweetAlertDialog.setTitle("Vote Added");
                          sweetAlertDialog.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                          sweetAlertDialog.show();
                          sweetAlertDialog.setConfirmButton("OK", new SweetAlertDialog.OnSweetClickListener() {
                              @Override
                              public void onClick(SweetAlertDialog sweetAlertDialog) {
                                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                              }
                          });
                      }
                    }

                });
            }
        });
    }
}
