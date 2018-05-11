package com.example.tse.listviewdemo;

import com.example.tse.listviewdemo.beans.Login;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by tse on 2017/10/9.
 */

public interface EggService {
    @POST("pos/login")
//    @Headers({"Content-Type: application/json",})
    Call<Login> Login(@Body FooRequest body);
//    Call<Login> Login(@Header("Content-Type") String contentRange , @Body FooRequest body);
//    Call<playlist> addToPlaylist(@Header("Content-Type") String content_type, @Body PlaylistParm parm);

}

//http://api.sandan.store