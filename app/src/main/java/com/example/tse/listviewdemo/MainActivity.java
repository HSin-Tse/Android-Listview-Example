package com.example.tse.listviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //基礎網路設定
        Retrofit retrofit = new Retrofit.Builder()//--> compile 'com.squareup.retrofit2:retrofit:2.3.0'
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())//--> compile 'com.squareup.retrofit2:converter-gson:2.1.0'

                .build();

        //根據設定創建網路服務
        GitHubService service = retrofit.create(GitHubService.class);

        //該服務創建實際需要的進程
        Call<List<Repo>> repos = service.listRepos("HSin-Tse");

        //進程 入站
        repos.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                Log.d("tse", "repos.size():" + response.body().size());
                Log.d("tse", "repos.size():" + response.body().get(0).getName());

            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {

            }
        });
    }
}
