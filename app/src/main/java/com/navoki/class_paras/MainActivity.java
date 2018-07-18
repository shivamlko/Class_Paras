package com.navoki.class_paras;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView text1, text2;
    Button button;
    private EditText email;
    private EditText pass;
    private EditText mobile;
    private EditText pincode;

    Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_layout);

        text1 = findViewById(R.id.t1);
        text2 = findViewById(R.id.t2);
        button = findViewById(R.id.button);
        email = (EditText) findViewById(R.id.email);
        pass = (EditText) findViewById(R.id.pass);
        mobile = (EditText) findViewById(R.id.mobile);
        pincode = (EditText) findViewById(R.id.pincode);

        context = MainActivity.this;


        text1.setText("hello world");

        text1.setBackgroundColor(ContextCompat.getColor(context, R.color.textbackgroundColor));
        text2.setBackgroundColor(ContextCompat.getColor(context, R.color.textbackgroundColor));

        text1.setTextSize(getResources().getDimension(R.dimen.textSize));
        button.setText("LOGIN");

        button.setOnClickListener(this);

        pincode.setHint("Enter pin code");

        InputFilter[] inputFilters={new InputFilter.LengthFilter(6)};
        pincode.setFilters(inputFilters);

        pincode.setInputType(InputType.TYPE_CLASS_NUMBER);

    }

    @Override
    public void onClick(View v) {

        text1.setText(getString(R.string.succesMSg));
        text1.setTextSize(getResources().getDimension(R.dimen.textSize2));
        button.setBackgroundColor(Color.MAGENTA);
        Toast.makeText(context, "Button Click", Toast.LENGTH_SHORT).show();


        String strEmail=email.getText().toString();
        String strMobile=mobile.getText().toString();
        String strPass=pass.getText().toString();
        String strPin=pincode.getText().toString();

        Log.e("MainActivity",strEmail);
        Log.e("MainActivity",strPass+" "+strMobile);

        if(!TextUtils.isEmpty(strEmail)) { // null ""

            if(!TextUtils.isEmpty(strMobile) && strMobile.length()==10){
                Intent intent=new Intent(MainActivity.this,SecondActivity.class);
                intent.putExtra("email",strEmail);
                intent.putExtra("mobile",strMobile);
                intent.putExtra("pass",strPass);
                intent.putExtra("textColor",Color.BLUE);

                Bundle bundle=new Bundle();
                bundle.putString("strPin",strPin);

                intent.putExtra("bundle",bundle);

                startActivity(intent);
                finish();
            }
            else
                Toast.makeText(context, "Enter valid mobile", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(context, "Enter valid email", Toast.LENGTH_SHORT).show();

    }
}
