package com.example.onlineelection;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter. MyownViewHolder>{

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
        final Myclass myclass = targetsArrayList.get(i);
        viewHolder.name.setText(targetsArrayList.get(i).getName());
        viewHolder.id.setText(Integer.toString(targetsArrayList.get(i).getID()));

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
        protected ImageView imageView;
        CardView cardView;
        public MyownViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.cousrename);
            id = itemView.findViewById(R.id.viewcredit);

            imageView= itemView.findViewById(R.id.imagedis);


            cardView=itemView.findViewById(R.id.cardview);
        }


    }
}
