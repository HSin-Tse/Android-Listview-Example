package com.example.tse.listviewdemo;

import retrofit2.Call;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by tse on 2017/10/9.
 */

public interface EggService {
    @POST("pos/login")
    @Headers({
            "Content-Type: application/json;charset=UTF-8",
            "User-Agent: Retrofit-your-App"})
    Call<Egg> Login();
//    Call<playlist> addToPlaylist(@Header("Content-Type") String content_type, @Body PlaylistParm parm);

}

//http://api.sandan.store