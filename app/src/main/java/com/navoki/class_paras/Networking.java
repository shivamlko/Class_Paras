package com.navoki.class_paras;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
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

public class Networking extends AppCompatActivity {
    private Button button;
    private TextView size;
    private ProgressDialog progressDialog;
    private ListView list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_networking);

        button = (Button) findViewById(R.id.button);
        size = (TextView) findViewById(R.id.size);
        list = (ListView) findViewById(R.id.list);

        progressDialog = new ProgressDialog(Networking.this);
        progressDialog.setMessage("Loading...");


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Task task = new Task();
                task.execute();

            }
        });
    }


    class Task extends AsyncTask<String, String, String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.show();
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
            progressDialog.dismiss();

            try {
                JSONObject jsonObject = new JSONObject(s);
                Log.e("JSON", jsonObject + "");

                JSONObject data = jsonObject.getJSONObject("data");
                Log.e("DATA", data + "");
                JSONArray filesArray = data.getJSONArray("files");
                Log.e("FIles", filesArray + "");

                size.setText("Size : " + filesArray.length());
                JSONObject object = filesArray.getJSONObject(1);

                size.append(" " + object.getString("id") + " " + object.getString("createdAt"));

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
