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
import java.util.List;


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

        List<Student> studentList=setData();

      /*  MyAdapter myAdapter=new MyAdapter(SecondActivity.this,R.layout.item_spinner,list);
        listView.setAdapter(myAdapter);*/

      CustomeAdapter customeAdapter=new CustomeAdapter(SecondActivity.this,R.layout.item_student,studentList);
      listView.setAdapter(customeAdapter);


    }

    private List<Student> setData() {

        List<Student> list=new ArrayList<>();

        Student student1=new Student();
        student1.setName("Shivam");
        student1.setMarks(70.0);
        student1.setBranch("CSE");
        student1.setSection('A');

        list.add(student1);


        Student student2=new Student();
        student2.setName("Shivam2");
        student2.setMarks(72.0);
        student2.setBranch("CSE2");
        student2.setSection('B');

        list.add(student2);

        Student student3=new Student();
        student3.setName("Shivam3");
        student3.setMarks(73.0);
        student3.setBranch("CSE3");
        student3.setSection('C');

        list.add(student3);

        Student student4=new Student();
        student4.setName("Shivam4");
        student4.setMarks(74.0);
        student4.setBranch("CSE4");
        student4.setSection('D');
        list.add(student4);

        Student student5=new Student();
        student5.setName("Shivam5");
        student5.setMarks(75.0);
        student5.setBranch("CSE5");
        student5.setSection('E');

        list.add(student5);

        return list;
    }


}
