package com.navoki.class_paras;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class Storage extends AppCompatActivity {

    private EditText input;
    private Button shared;
    private EditText edtname;
    private EditText edtSubject;
    private Button sqlinsert;
    private Button select;
    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);


        input = (EditText) findViewById(R.id.input);
        shared = (Button) findViewById(R.id.shared);
        edtname = (EditText) findViewById(R.id.edtname);
        edtSubject = (EditText) findViewById(R.id.edtSubject);
        sqlinsert = (Button) findViewById(R.id.sqlinsert);
        select = (Button) findViewById(R.id.select);

        database=new Database(Storage.this);



        final SharedPreferences preferences=getSharedPreferences("UserData",MODE_PRIVATE);

        shared.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name=input.getText().toString();

                SharedPreferences.Editor editor=preferences.edit();
                editor.putString("name",name);
                editor.commit();

            }
        });

       input.setText(preferences.getString("name","N/A"));



       sqlinsert.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               String name=edtname.getText().toString();
               String sub=edtSubject.getText().toString();
               database.insert(name,sub);

           }
       });

       select.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               ArrayList<Student> list=database.filter("civil");

               for(Student student:list){
                   Log.e("DATA",student.getName()+" "+student.getBranch());
               }


           }
       });






    }
}
