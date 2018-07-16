package com.navoki.class_paras;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView text1, text2;
    Button button;

    Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_layout);

        text1 = findViewById(R.id.t1);
        text2 = findViewById(R.id.t2);
        button = findViewById(R.id.button);

        context = MainActivity.this;


        text1.setText("hello world");

        text1.setBackgroundColor(ContextCompat.getColor(context, R.color.textbackgroundColor));
        text2.setBackgroundColor(ContextCompat.getColor(context, R.color.textbackgroundColor));

        text1.setTextSize(getResources().getDimension(R.dimen.textSize));
        button.setText("LOGIN");

        button.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        text1.setText(getString(R.string.succesMSg));
        text1.setTextSize(getResources().getDimension(R.dimen.textSize2));
        button.setBackgroundColor(Color.MAGENTA);
        Toast.makeText(context, "Button Click", Toast.LENGTH_SHORT).show();
    }
}
