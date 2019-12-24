package com.example.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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

      //   tv = findViewById(R.id.textView);

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

    private class MyDownload extends AsyncTask<Void, Void, Bitmap>{
        ImageView bmImage;

            HttpURLConnection httpurl;

    @Override
        protected Bitmap doInBackground(Void... voids) {
            try {
               // URL url=new URL("http://api.weatherstack.com/current?access_key=6d3ecccfce14b8bf1efaba43c5c8f18e&query=Krasnodar");
              //  httpurl= (HttpURLConnection) url.openConnection();
              //  httpurl.setRequestMethod("GET");
               // httpurl.connect();
                Bitmap mIcon11 = null;
                InputStream in = new java.net.URL("http://www.playcast.ru/uploads/2015/11/07/15780970.png").openStream();
                mIcon11 = BitmapFactory.decodeStream(in);

                //InputStream inputStream = httpurl.getInputStream();
                //Scanner scan=new Scanner(inputStream);
                //StringBuffer buffer= new StringBuffer();
               // while (scan.hasNextLine()){

                //    buffer.append(scan.nextLine());

                ///}
                return mIcon11;

            } catch (java.io.IOException e) {
                e.printStackTrace();
                Log.e("RRRR",e.toString());
            }



            return null;
        }

        @Override
        protected void onPostExecute(Bitmap s) { // метод, который выполняется после doInBackround
            super.onPostExecute(s);
            bmImage=findViewById(R.id.imageView);
            bmImage.setImageBitmap(s);


        }
    }


}
