package com.example.myquickappapplication.Retrofit;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.myquickappapplication.Models.MessageData;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONObject;

public class FCMService extends FirebaseMessagingService {
    private static final String TAG = "FCMService";

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.d(TAG, "onMessageReceived: " + remoteMessage.getData());

        //DatabaseHandler messageDatabaseHandler = new DatabaseHandler(this);

        try {

            Log.d(TAG, "onMessageReceived: ");

            JSONObject jsonObject = new JSONObject(remoteMessage.getData().toString());
            String senderId = jsonObject.getString("senderId");
            String messageBody = jsonObject.getString("body");
            String messageId = jsonObject.getString("messageId");
            String conversationId = senderId;
            String timeStamp = jsonObject.getString("timeStamp");

            MessageData message = new MessageData();

            message.setMessageId(messageId);
            message.setConversionID(conversationId);
            message.setBody(messageBody);
            message.setTimeStamp(timeStamp);
            message.setSenderId(senderId);

            Log.d(TAG, "MessageEntity Class: "+message.toString());

          //  messageDatabaseHandler.insertMessage(message);

        }
        catch (Exception e)
        {
            Log.e(TAG, "onMessageReceived: Exception " + e.getMessage());
        }
    }
}
