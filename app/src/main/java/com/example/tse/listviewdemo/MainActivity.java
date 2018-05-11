package com.example.tse.listviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.tse.listviewdemo.beans.Login;

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

//https://api.sandan.store/pos/login


        Retrofit retrofitEgg = new Retrofit.Builder()//--> compile 'com.squareup.retrofit2:retrofit:2.3.0'
                .baseUrl("https://api.sandan.store/")
                .addConverterFactory(GsonConverterFactory.create())//--> compile 'com.squareup.retrofit2:converter-gson:2.1.0'
                .build();
        //根據設定創建網路服務
        EggService serviceEgg = retrofitEgg.create(EggService.class);

        //該服務創建實際需要的進程
        Call<Login> reposEgg = serviceEgg.Login("application/json",new FooRequest("admin","123456"));

        //進程 入站
        reposEgg.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                Log.d("hcia", "call:" + call);
                Log.d("hcia", "response :" + response);
                Log.d("hcia", "response body():" + response.body().toString());
                Log.d("hcia", "response body():" + response.body().getMsg());
                Log.d("hcia", "response body():" + response.body().getCode());
                Log.d("hcia", "response body():" + response.body().getCode());
//                Log.d("hcia", "response.headers():" + response.headers());
//                String result = response.body().string();
//                Log.d("hcia", "response message:" + response.toString());
                LogTest.setText("NetWork Log: " + response.body());

            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {

            }
        });

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

