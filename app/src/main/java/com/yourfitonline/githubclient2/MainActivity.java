package com.yourfitonline.githubclient2;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    // Описывает базовый путь к API сервиса


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }



    public void get(View view){
        Log.d("LOG","get()");
        MTask mTask = new MTask();
        mTask.execute();
    }
    class MTask extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... params) {
            GithubClient client = ServiceGenerator.createService(GithubClient.class);
            Call<List<Contributor>> call = client.contributors("square", "retrofit");



            try {
                List<Contributor>  contributors = call.execute().body();
                for (Contributor c : contributors) {
                    Log.d("LOG", "Contributer " + c.login
                            + " has " + c.contributions + " contributions");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }

}
