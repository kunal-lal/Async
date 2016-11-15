package com.example.kunal.fetcher;

import android.os.AsyncTask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.io.IOException;
import org.jsoup.*;
import org.jsoup.nodes.Document;


public class FetcherActivity extends AppCompatActivity {
    TextView iiitdtext;
    Button fetchdata;
    UIfragment fragment;
    String dataStr;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetcher);
        iiitdtext = (TextView) findViewById(R.id.iiitdtext);
        fetchdata = (Button) findViewById(R.id.btn_fetch_data);
        fetchdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new FetchData().execute();
            }
        });
        if (savedInstanceState == null) {
            fragment = new UIfragment();
            getSupportFragmentManager().beginTransaction().add(fragment, "taskfragment").commit();
        } else {
            fragment = (UIfragment) getSupportFragmentManager().findFragmentByTag("taskfragment");
        }
    }


    private class FetchData extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {

            try {

              Document  doc =  Jsoup.connect("https://www.iiitd.ac.in/about").get();

                dataStr=doc.text();

            } catch (IOException e) {
                e.printStackTrace();
            }

        return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            iiitdtext.setText(dataStr);
            iiitdtext.setMovementMethod(new ScrollingMovementMethod());

        }

    }

}


