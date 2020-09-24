package com.example.onlineelection;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;

public class Mydrawerpage extends AppCompatActivity {
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mydrawerpage);
        drawerLayout= findViewById(R.id.drawer_layout);
        toolbar=findViewById(R.id.toolbar);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        toggle.syncState();
    }
    public void closeDrawer(){
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }
    public void Actionofvoting (View view){
        redirectActivity(this,MainActivity.class);
        closeDrawer();
    }


    public void ClickHome(View view){
        recreate();
        closeDrawer();

    }
    public void Profile (View view){
       redirectActivity(this,Profile.class);
         closeDrawer();

    }
    public void Logout(View view){
        redirectActivity(this,LoginActivity.class);
        finish();
        closeDrawer();
    }
    public void ClickAboutus (View view){
        redirectActivity(this,Aboutus.class);
        closeDrawer();
    }
    public  void Exit (View view){
        Exit(this);
        closeDrawer();
    }

    public static void Exit (final Activity activity) {
        //initail alert message
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        // set title
        builder.setTitle("Exit");
        // set message
        builder.setMessage("Are Your sure to Exit?");
        //postive yes button
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                activity.finishAffinity();
                //exit app
                System.exit(0);

            }
        });
        //set negative to no
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        //show dialog
        builder.show();
    }


    public static void redirectActivity(Activity activity,Class aClass) {
        Intent intent = new Intent(activity,aClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);

    }
    public void addContentView(int layoutId) {
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(layoutId, null, false);
        drawerLayout.addView(contentView, 0);
    }
    @Override
    protected void onPause() {
        super.onPause();

    }
}