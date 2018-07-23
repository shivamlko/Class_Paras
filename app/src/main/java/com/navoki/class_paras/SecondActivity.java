package com.navoki.class_paras;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public class SecondActivity extends AppCompatActivity {

    private TextView text;
    private Spinner spinner2;
    private ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.secondpage);

        text = (TextView) findViewById(R.id.text);

        spinner2 = (Spinner) findViewById(R.id.spinner2);

        listView = (ListView) findViewById(R.id.list);

        Intent intent = getIntent();

        if (intent != null) {

            String email = intent.getStringExtra("email") == null ? "N/A" : intent.getStringExtra("email");

            text.setText(email);
            text.append("\n");
            text.append(intent.getStringExtra("mobile") == null ? "N/A" : intent.getStringExtra("mobile"));

            Bundle bundle = intent.getBundleExtra("bundle");
            if (bundle != null) {
                text.append("\n");
                text.append(bundle.getString("strPin", "N/A"));

                text.setTextColor(intent.getIntExtra("textColor", Color.RED));

                String gender = bundle.getString("gender", "Female");

                text.append(gender);
            }


        }


        String[] arr=getResources().getStringArray(R.array.contries);

        ArrayList<String> list=new ArrayList<>(Arrays.asList(arr));

     //   Collections.shuffle(list);

        ArrayAdapter<String> adapter=new ArrayAdapter<>(SecondActivity.this,R.layout.item_spinner,R.id.textView,list);
        spinner2.setAdapter(adapter);


        MyAdapter myAdapter=new MyAdapter(SecondActivity.this,R.layout.item_spinner,list);
        listView.setAdapter(myAdapter);

    }



}
