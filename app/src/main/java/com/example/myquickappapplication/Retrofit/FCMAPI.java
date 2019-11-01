package com.example.myquickappapplication.Retrofit;

import com.example.myquickappapplication.Models.MessageData;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface FCMAPI {

    @Headers({
            "Content-Type: application/json",
            "Authorization: key=AAAAdX9EZnM:APA91bH-KDYg39FY7Z8YomfND5gLk_Deg6Wn-cOzBJUgBgVnE2vCk3H-nI74_bY4metGuKN4xPR3GHVpjA2x2Gm_YhCExhabK7pTnXDxrKuYSY9FGuBPuyVNk7B5uPN_2xuXghRsgblq"
    })
    @POST("/fcm/send")
    Call<ResponseBody> sendMessage(@Body MessageEntity messageEntity);

}
