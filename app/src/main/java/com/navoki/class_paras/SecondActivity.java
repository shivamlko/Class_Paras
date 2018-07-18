package com.navoki.class_paras;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


public class SecondActivity extends AppCompatActivity {

    private TextView text;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.secondpage);

        text = (TextView) findViewById(R.id.text);

        Intent intent=getIntent();
        if(intent!=null){

            String email=intent.getStringExtra("email");

            text.setText(email);
            text.append("\n");
            text.append(intent.getStringExtra("mobile"));

            Bundle bundle=intent.getBundleExtra("bundle");
            text.append("\n");
            text.append(bundle.getString("strPin","N/A"));

            text.setTextColor(intent.getIntExtra("textColor", Color.RED));


        }

    }
}
