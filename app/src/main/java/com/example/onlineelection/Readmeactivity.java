package com.example.onlineelection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class Readmeactivity extends AppCompatActivity {
    CheckBox checkBox;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_readmeactivity);
        checkBox=findViewById(R.id.checkBox);
        button = findViewById(R.id.nextbtm);
      if (checkBox.isChecked()){
          button.setClickable(true);
      }else{
          button.setClickable(false);
          return;
      }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        });

    }

}