package com.example.tse.listviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.security.cert.CertificateException;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
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
        final TextView LogTest = (TextView) findViewById(R.id.log);



//        {
//            "loginName":"admin",
//                "password":123456
//        }


//        //基礎網路設定
//        Retrofit retrofit = new Retrofit.Builder()//--> compile 'com.squareup.retrofit2:retrofit:2.3.0'
//                .baseUrl("https://api.github.com/")
//                .addConverterFactory(GsonConverterFactory.create())//--> compile 'com.squareup.retrofit2:converter-gson:2.1.0'
//                .build();

//https://api.sandan.store/pos/login
        Retrofit retrofitEgg = new Retrofit.Builder()//--> compile 'com.squareup.retrofit2:retrofit:2.3.0'
                .baseUrl("https://api.sandan.store/")
                .addConverterFactory(GsonConverterFactory.create())//--> compile 'com.squareup.retrofit2:converter-gson:2.1.0'
                .build();
        //根據設定創建網路服務
//        GitHubService service = retrofit.create(GitHubService.class);
        EggService serviceEgg = retrofitEgg.create(EggService.class);

        //該服務創建實際需要的進程
//        Call<List<Repo>> repos = service.listRepos("HSin-Tse");
        Call<Egg> reposEgg = serviceEgg.Login();

        //進程 入站
        reposEgg.enqueue(new Callback<Egg>() {
            @Override
            public void onResponse(Call<Egg> call, Response<Egg> response) {
                Log.d("hcia", "call:" + call);
                Log.d("hcia", "response:" + response);
//                Log.d("hcia", "response message:" + response.toString());
                LogTest.setText("NetWork Log: " + response.body());
//                LogTest.setText("NetWork Log: "+response.message());
//                LogTest.setText("NetWork Log: "+response.toString());
//                LogTest.setText("NetWork Log: "+response.raw());
//                Log.d("tse", "repos.size():" + response.body().size());
//                Log.d("tse", "repos.size():" + response.body().get(0).getName());

            }

            @Override
            public void onFailure(Call<Egg> call, Throwable t) {

            }
        });

//        repos.enqueue(new Callback<List<Repo>>() {
//            @Override
//            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
//                Log.d("tse", "repos.size():" + response.body().size());
//                Log.d("tse", "repos.size():" + response.body().get(0).getName());
//
//            }
//
//            @Override
//            public void onFailure(Call<List<Repo>> call, Throwable t) {
//
//            }
//        });
    }

    public static OkHttpClient getUnsafeOkHttpClient() {

        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                @Override
                public void checkClientTrusted(
                        java.security.cert.X509Certificate[] chain,
                        String authType) throws CertificateException {
                }

                @Override
                public void checkServerTrusted(
                        java.security.cert.X509Certificate[] chain,
                        String authType) throws CertificateException {
                }

                @Override
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return new java.security.cert.X509Certificate[0];
                }
            }};

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCerts,
                    new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext
                    .getSocketFactory();

            OkHttpClient okHttpClient = new OkHttpClient();
            okHttpClient = okHttpClient.newBuilder()
                    .sslSocketFactory(sslSocketFactory)
                    .hostnameVerifier(org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER).build();

            return okHttpClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}

