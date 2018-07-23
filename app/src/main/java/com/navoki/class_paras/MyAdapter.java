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
import android.widget.Toast;

import java.util.ArrayList;

public class MyAdapter extends ArrayAdapter {

    ArrayList<String> list;
    Context context;

    public MyAdapter(@NonNull Context context, int resource, ArrayList<String> list) {
        super(context, resource,list);
        this.list=list;
        this.context=context;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view=View.inflate(context,R.layout.item_spinner,null);
        TextView textView = view.findViewById(R.id.textView);

        textView.setText(list.get(position));

        if(position==1) {
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "position " + position, Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(context,MainActivity.class);
                    context.startActivity(intent);
                }
            });
        }


        if(position%2==0){
            textView.setTextColor(Color.BLUE);
        }


        return view;
    }


    @Override
    public int getCount() {
        return list.size();
    }
}
