package com.example.myquickappapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;

import com.example.myquickappapplication.Adapter.MyPageAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    MyPageAdapter myPageAdapter;
    private static final String TAG = "HomeActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        tabLayout=findViewById(R.id.tablayout);
        viewPager=findViewById(R.id.viewpager);
        tabLayout.setupWithViewPager(viewPager);

        List<String> tablist=new ArrayList<>();
        tablist.add("chats");
        tablist.add("contacts");

        myPageAdapter=new MyPageAdapter(getSupportFragmentManager(),tablist);
        viewPager.setAdapter(myPageAdapter);
        viewPager.addOnPageChangeListener (new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                Log.d(TAG, "onPageScrolled: ");
            }

            @Override
            public void onPageSelected(int i) {
                Log.d(TAG, "onPageSelected: " + i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }

        });
    }
}
