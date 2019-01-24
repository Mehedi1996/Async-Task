package com.example.asynctask;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ProgressBar progressBar;

    Handler handler = new Handler();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.idprogres);
    }

    public void Save(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "Data is Show", Toast.LENGTH_SHORT).show();
                        }
                    });

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        }).start();

    }



    public void showProgressber(View view) {

     new progressTask().execute(); //Asynctask handle this

    }

    public class progressTask extends AsyncTask<Void,Integer, Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            for (int i = 0; i <= 100; i = i+10){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress(i);
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) { //progressbar ta array size initi 0 thake start hobe
            super.onProgressUpdate(values);
            progressBar.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Void aVoid) { //last complete execute show korbe
            super.onPostExecute(aVoid);
            Toast.makeText(MainActivity.this, "Progress finish", Toast.LENGTH_SHORT).show();
        }
    }

}
