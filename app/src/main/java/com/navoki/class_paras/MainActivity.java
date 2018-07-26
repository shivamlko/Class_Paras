package com.navoki.class_paras;

import android.content.Context;
import android.content.Intent;
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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView text1, text2;
    Button button;
    private EditText edtEmail;
    private EditText pass;
    private EditText mobile;
    private EditText pincode;

    Context context;
    private CheckBox checkBox;
    private EditText college;
    private RadioGroup radioGroup;

    String gender;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_layout);

        text1 = findViewById(R.id.t1);
        text2 = findViewById(R.id.t2);
        button = findViewById(R.id.button);
        edtEmail =   findViewById(R.id.email);
        pass =   findViewById(R.id.pass);
        mobile =  findViewById(R.id.mobile);
        pincode = findViewById(R.id.pincode);

        checkBox = findViewById(R.id.checkBox);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        college = (EditText) findViewById(R.id.college);

        context = MainActivity.this;

        text1.setText("hello world");

        text1.setBackgroundColor(ContextCompat.getColor(context, R.color.textbackgroundColor));
        text2.setBackgroundColor(ContextCompat.getColor(context, R.color.textbackgroundColor));

        text1.setTextSize(getResources().getDimension(R.dimen.textSize));
        button.setText("LOGIN");

        button.setOnClickListener(this);

        pincode.setHint("Enter pin code");

        InputFilter[] inputFilters = {new InputFilter.LengthFilter(6)};
        pincode.setFilters(inputFilters);

        pincode.setInputType(InputType.TYPE_CLASS_NUMBER);


        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.e("checkBox", "isGraduate "+isChecked);
                if(isChecked){
                    college.setVisibility(View.VISIBLE);
                }
                else
                    college.setVisibility(View.GONE);

            }
        });


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Log.e("IDs", checkedId+" "+R.id.male+" "+R.id.female);
                if(checkedId==R.id.male){
                    gender="Male";
                }
                else
                    gender="Female";

            }
        });



    }

    @Override
    public void onClick(View v) {

    /*    text1.setText(getString(R.string.succesMSg));
        text1.setTextSize(getResources().getDimension(R.dimen.textSize2));
        button.setBackgroundColor(Color.MAGENTA);
        Toast.makeText(context, "Button Click", Toast.LENGTH_SHORT).show();

[a-z][@][a-z][.][a-z]

*/
        String strEmail = edtEmail.getText().toString();
        String strMobile = mobile.getText().toString();
        String strPass = pass.getText().toString();
        String strPin = pincode.getText().toString();

        boolean isGraduate=checkBox.isChecked();

        Log.e("MainActivity", strEmail);
        Log.e("MainActivity", strPass + " " + strMobile);
        Log.e("MainActivity", "isGraduate "+isGraduate);

        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";



        if (!TextUtils.isEmpty(strEmail) && strEmail.matches(emailPattern)) { // null ""

            if (!TextUtils.isEmpty(strMobile) && strMobile.length() == 10) {
                if(!TextUtils.isEmpty(gender)) {

                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    intent.putExtra("email", strEmail);
                    intent.putExtra("mobile", strMobile);
                    intent.putExtra("pass", strPass);
                    intent.putExtra("textColor", Color.BLUE);
                    intent.putExtra("graduate", isGraduate);

                    Bundle bundle = new Bundle();
                    bundle.putString("strPin", strPin);
                    bundle.putString("gender", gender);

                    intent.putExtra("bundle", bundle);
                    startActivity(intent);
                    finish();
                }
                else
                    Toast.makeText(context, "Select gender", Toast.LENGTH_SHORT).show();

            } else
                Toast.makeText(context, "Enter valid mobile", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(context, "Enter valid edtEmail", Toast.LENGTH_SHORT).show();

    }
}
