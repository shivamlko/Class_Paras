package com.navoki.class_paras;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class NetworkAdapter extends ArrayAdapter {


    List<Student> list2;
    Context context;

    public NetworkAdapter(@NonNull Context context, int resource, List<Student> list) {
        super(context, resource);
         list2=list;
         this.context=context;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Student student=list2.get(position);

        if(student.getMarks()>=75)
        convertView=View.inflate(context,R.layout.item_student2,null);
        else
            convertView=View.inflate(context,R.layout.item_student,null);

          TextView name;
          TextView myclass;
          TextView marks;

        name = (TextView) convertView.findViewById(R.id.name);
        myclass = (TextView) convertView.findViewById(R.id.myclass);
        marks = (TextView) convertView.findViewById(R.id.marks);


        name.setText(student.getName());
        myclass.setText(student.getBranch()+"-"+student.getSection());
        marks.setText(String.valueOf(student.getMarks()));

        if(student.getMarks()>=75){
            name.setTextColor(Color.RED);
            name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context,MainActivity.class));
                }
            });
        }


        return convertView;

    }

    @Override
    public int getCount() {
        return list2.size();
    }
}
