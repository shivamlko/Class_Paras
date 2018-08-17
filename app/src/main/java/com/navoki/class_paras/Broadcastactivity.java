package com.navoki.class_paras;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class Broadcastactivity extends AppCompatActivity {
    private ImageView image;
    BroadcastReceiver broadcastReceiver;
    private TextView text;
    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcastactivity);

        image = (ImageView) findViewById(R.id.image);

        text = (TextView) findViewById(R.id.text);


        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                Log.e("MSG", "1111");
                if (intent.getAction().equals(Intent.ACTION_POWER_CONNECTED)) {
                    image.setImageResource(R.drawable.ic_battery_charging_80_black_24dp);
                    Intent intent1 = new Intent(Broadcastactivity.this, MyServices.class);
                    startService(intent1);

                } else if (intent.getAction().equals(Intent.ACTION_POWER_DISCONNECTED)) {
                    image.setImageResource(R.drawable.ic_battery_60_black_24dp);
                } else if (intent.getAction().equals("com.navoki.class_paras.BATTERY")) {
                    text.setText("Battery: " + intent.getIntExtra("battery", -1));
                    showDialog("Battery: " + intent.getIntExtra("battery", -1));
                }
            }
        };

    }


    void showDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Broadcastactivity.this);
        builder.setTitle("Battery Status");
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Task task = new Task();
                task.execute();
                alertDialog.dismiss();
            }
        });
        builder.setNegativeButton("CACNEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }
        });
        builder.setNeutralButton("LATER", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }
        });


        alertDialog = builder.create();
        alertDialog.show();


    }

    @Override
    protected void onResume() {
        super.onResume();

        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_POWER_CONNECTED);
        IntentFilter intentFilter2 = new IntentFilter(Intent.ACTION_POWER_DISCONNECTED);
        IntentFilter intentFilter3 = new IntentFilter("com.navoki.class_paras.BATTERY");

        registerReceiver(broadcastReceiver, intentFilter);
        registerReceiver(broadcastReceiver, intentFilter2);
        registerReceiver(broadcastReceiver, intentFilter3);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(broadcastReceiver);
    }


    class Task extends AsyncTask<String, String, String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
         }

        @Override
        protected String doInBackground(String... strings) {
            String str = getResponse();
            onProgressUpdate("10");
            return str;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                JSONObject jsonObject = new JSONObject(s);
                Log.e("JSON", jsonObject + "");

                JSONObject data = jsonObject.getJSONObject("data");
                Log.e("DATA", data + "");
                JSONArray filesArray = data.getJSONArray("files");
                Log.e("FIles", filesArray + "");

                 JSONObject object = filesArray.getJSONObject(1);


                ArrayList<DataModel> modelArrayList = new ArrayList<>();

                for (int i = 0; i < filesArray.length(); i++) {

                    JSONObject ob = filesArray.getJSONObject(i);
                    DataModel dataModel = new DataModel();
                    dataModel.setId(ob.getString("id"));
                    dataModel.setImage(ob.getString("url"));
                    dataModel.setDate(ob.getString("createdAt"));

                    modelArrayList.add(dataModel);
                }


              /*  NetworkAdapter networkAdapter=new NetworkAdapter(Networking.this,modelArrayList);
                list.setAdapter(networkAdapter);
*/


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


    private String getResponse() {


        try {
            URL url = new URL("https://demo9974529.mockable.io/getdata");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");

            int status = httpURLConnection.getResponseCode();

            if (status == 200) {
                Scanner scanner = new Scanner(httpURLConnection.getInputStream());
                scanner.useDelimiter("\\A");

                String resp = scanner.next();
                return resp;
            } else {
                return null;
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
