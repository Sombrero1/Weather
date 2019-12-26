package com.example.weather;


import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    private Button btn;
    private TextView tv;
    private EditText edit;
    private String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.button);

        edit=findViewById(R.id.editText);




        tv = findViewById(R.id.textView);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // вызов AsyncTask
                // создание отдельного потока

                text= edit.getText().toString(); //считываем текст по нажатию
                MyDownload mytask = new MyDownload();
                mytask.execute();
            }
        });
    }

    private class MyDownload extends AsyncTask<Void, Void, String> {

        HttpURLConnection httpurl;

        @Override
        protected String doInBackground(Void... voids) {

            try {



                String v="https://translate.yandex.net/api/v1.5/tr.json/translate?key=trnsl.1.1.20191226T080016Z.119e512f37c2e2f1.054a00bcaf9ff4d6d65db962bfdf982829706e54&text=" + text +"&lang=en-ru&format=plain" ;
                URL url = new URL(v);
                httpurl = (HttpURLConnection) url.openConnection();
                httpurl.setRequestMethod("GET");
                httpurl.connect();

                InputStream inputStream = httpurl.getInputStream();

                Scanner scan = new Scanner(inputStream);
                StringBuffer buffer = new StringBuffer();
                while(scan.hasNextLine()) {
                    buffer.append(scan.nextLine());
                }
                return buffer.toString();

            } catch (java.io.IOException e) {
                e.printStackTrace();
                //Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
                Log.e("RRRRR",e.toString());
            }


            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            // метод, который выполняется после doInBackground



            Log.e("RRRRRRRRR",s);



          Gson g = new Gson();
          Yandex yandex=g.fromJson(s,Yandex.class);
          List<String> a=yandex.getText();

            tv.setText(a.get(0));






    }
}}