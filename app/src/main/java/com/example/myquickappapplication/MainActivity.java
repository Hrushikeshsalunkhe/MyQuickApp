package com.example.myquickappapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.myquickappapplication.AddContant.AppConstant;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    boolean login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*
        Intent intent=new Intent(MainActivity.this,Get_startedActivity.class);
      startActivity(intent);

 */


        sharedPreferences=getSharedPreferences(AppConstant.PREFERENCE_FILE_NAME,MODE_PRIVATE);
        login=sharedPreferences.getBoolean(AppConstant.isLogin,false);
        if (!login){
            Intent intent=new Intent(this,Get_startedActivity.class);
            startActivity(intent);

        }
        else {
            Intent intent=new Intent(this,HomeActivity.class);
            startActivity(intent);
        }
        finish();




    }
}
