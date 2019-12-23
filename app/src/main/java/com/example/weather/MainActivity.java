package com.example.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    private Button btn;
    private TextView tv;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tc = findViewById(R.id.textView);
        btn= findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Вызов AsyncTask
                // создание отдельного потока

                MyDownload mytask= new MyDownload();
                mytask.execute();




            }
        });

    }

    private class MyDownload extends AsyncTask<Void, Void,String>{

            HttpURLConnection httpurl;
        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url=new URL("http://api.weatherstack.com/current?access_key=6d3ecccfce14b8bf1efaba43c5c8f18e&query=Krasnodar");
                httpurl= (HttpURLConnection) url.openConnection();
                httpurl.setRequestMethod("GET");
                httpurl.connect();


                InputStream inputStream = httpurl.getInputStream();
                Scanner scan=new Scanner(inputStream);
                StringBuffer buffer= new StringBuffer();
                while (scan.hasNextLine()){

                    buffer.append(scan.nextLine());

                }
                return buffer.toString();

            } catch (java.io.IOException e) {
                e.printStackTrace();
            }



            return null;
        }

        @Override
        protected void onPostExecute(String s) { // метод, который выполняется после doInBackround
            super.onPostExecute(s);

            tv.setText(s);


        }
    }


}
