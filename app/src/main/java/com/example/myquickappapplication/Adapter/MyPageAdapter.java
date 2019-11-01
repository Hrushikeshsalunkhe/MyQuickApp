package com.example.myquickappapplication.Adapter;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.myquickappapplication.Fragment.ChatsFragment;
import com.example.myquickappapplication.Fragment.ContactFragment;

import java.util.List;

public class MyPageAdapter  extends FragmentStatePagerAdapter {
    private static final String TAG = "MyPageAdapter";
    private List<String> mtablist;

    public MyPageAdapter(@NonNull FragmentManager fm, List<String> tablist) {
        super(fm);
        mtablist = tablist;
        Log.d(TAG, "MyPageAdapter: "+mtablist);


    }
    @NonNull
    @Override
    public Fragment getItem(int i) {
        if (i == 0) {
            Log.d(TAG, "getItem: Chatfragment");
            return new ChatsFragment();

        }
        else
            {
                Log.d(TAG, "getItem: Contactfragment");
            return new ContactFragment();

        }
    }
    @NonNull
    @Override
    public CharSequence getPageTitle(int i) {
        Log.d(TAG, "getPageTitle: "+i);
        return mtablist.get(i);

    }

    @Override
    public int getCount() {
        return mtablist.size();

    }


}