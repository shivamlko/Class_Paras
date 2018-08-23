package com.navoki.class_paras;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class FragmentsActivity extends AppCompatActivity {
    private Button image1;
    private Button image2;
    private Button image3;
    private FrameLayout frame;


    FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragments);

        image1 =   findViewById(R.id.image1);
        image2 =   findViewById(R.id.image2);
        image3 =   findViewById(R.id.image3);
        frame =   findViewById(R.id.frame);
        manager=getSupportFragmentManager();

        Log.e("MSG","---------");

        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("MSG","33333");

                MyFragment myFragment=MyFragment.getInstance("Rose","https://images.pexels.com/photos/67636/rose-blue-flower-rose-blooms-67636.jpeg?auto=compress&cs=tinysrgb&h=350");
                FragmentTransaction transaction=manager.beginTransaction();
                transaction.add(R.id.frame,myFragment);
                transaction.commit();
                Log.e("MSG","222");
            }
        });


        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MyFragment myFragment=MyFragment.getInstance("Plants","https://media.istockphoto.com/photos/growing-money-chart-in-rise-picture-id506181336");
                FragmentTransaction transaction=manager.beginTransaction();
                transaction.replace(R.id.frame,myFragment);
                transaction.commit();
                Log.e("MSG","222");
            }
        });


        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MyFragment myFragment=MyFragment.getInstance("Love","https://images.pexels.com/photos/949586/pexels-photo-949586.jpeg?auto=compress&cs=tinysrgb&h=350");
                FragmentTransaction transaction=manager.beginTransaction();
                transaction.add(R.id.frame,myFragment);
                transaction.commit();
                Log.e("MSG","222");
            }
        });


    }
}
