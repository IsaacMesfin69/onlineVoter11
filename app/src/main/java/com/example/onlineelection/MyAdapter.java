package com.example.onlineelection;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter. MyownViewHolder>{
    final String TAG="Value";
    ArrayList<Myclass> targetsArrayList;
    public MyAdapter(ArrayList<Myclass> mTargetData) {
        targetsArrayList = mTargetData;
    }

    @NonNull
    @Override
    public MyAdapter.MyownViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.myshow,parent,false );
        return new MyownViewHolder(view);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final MyownViewHolder viewHolder, final int i) {

        viewHolder.name.setText(targetsArrayList.get(i).getName());
        viewHolder.id.setText(Integer.toString(targetsArrayList.get(i).getId()));
        FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
        String userid=firebaseAuth.getCurrentUser().getUid();
        DocumentReference reference = FirebaseFirestore.getInstance().collection("Users")
                .document(userid).collection("VotedVor").document("party");
       reference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
           @Override
           public void onComplete(@NonNull Task<DocumentSnapshot> task) {
             DocumentSnapshot documentSnapshot=task.getResult();
             if (documentSnapshot.exists()){
                 Log.d(TAG, "onComplete: File Already Added");
                 viewHolder.cardView.setClickable(false);
             }else{
                 Log.d(TAG, "onComplete: Empty");
                 viewHolder.cardView.setClickable(true);
             }
           }
       });
        // kefeleke name on click madereg techelale
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  String partyname=viewHolder.name.getText().toString();
                  String id=viewHolder.id.getText().toString();
                  Intent intent=new Intent(view.getContext(),SelectParty.class);
                  intent.putExtra("Partyname",partyname);
                  intent.putExtra("id",id);
                  view.getContext().startActivity(intent);

            }
        });

        }

    @Override
    public int getItemCount() {
        if(targetsArrayList == null)
            return 0;
        return targetsArrayList.size();
    }


    public static class MyownViewHolder extends RecyclerView.ViewHolder{
        protected TextView name;
        protected TextView id;

        CardView cardView;
        public MyownViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            id = itemView.findViewById(R.id.id);
            cardView=itemView.findViewById(R.id.cardview);
        }


    }
}
