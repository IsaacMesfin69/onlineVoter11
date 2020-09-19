package com.example.onlineelection;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

public class MydataAdapter extends RecyclerView.Adapter<MydataAdapter.TargetViewHolder> {

   List<Myclass> myclassList;
    Context context;

    public MydataAdapter(List<Myclass> myclassList, Context context) {
        this.myclassList = myclassList;
        this.context = context;
    }

    @NonNull
    @Override
    public TargetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.myshow,parent,false);
        return new TargetViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull TargetViewHolder holder, int position) {
       holder.name.setText(myclassList.get(position).getName());
       holder.id.setText(myclassList.get(position).getId());
    }

    @Override
    public int getItemCount() {
        if (myclassList!=null){
            return myclassList.size();
        }
        return 0;

    }


    public static class TargetViewHolder extends RecyclerView.ViewHolder {
        protected  TextView name;
        protected  TextView id;
        CardView cardView;
        public TargetViewHolder(@NonNull View itemView) {
            super(itemView);
            name= itemView.findViewById(R.id.nameview);
            id = itemView.findViewById(R.id.cousrename);
            cardView=itemView.findViewById(R.id.cardview);

        }

    }
}





















