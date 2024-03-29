package com.example.myquickappapplication.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myquickappapplication.Adapter.ContactRecycleViewActivity;
import com.example.myquickappapplication.AddContant.AppConstant;
import com.example.myquickappapplication.Models.User;
import com.example.myquickappapplication.Models.UserContact;
import com.example.myquickappapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.List;



public class ContactFragment extends Fragment {
    ContactRecycleViewActivity adapter;
    Context context;

    DatabaseReference databaseUser;

    FirebaseDatabase firebaseDatabase;
    View view;
    RecyclerView recyclerView;
    User user;
    List<UserContact>userList;


    private SharedPreferences mSharedPreference;
    String mLoggedInUserContactNumber;
    private static final String TAG = "ContactFragment";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    public ContactFragment() {
        // Required empty public constructor
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.d(TAG, "onCreateView: of ContactFragment");
        context = container.getContext();
        view=inflater.inflate(R.layout.fragment_contact,container,false);
/*
        //user = new User();
        recyclerView= view.findViewById(R.id.contact_recycle_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayout = new LinearLayoutManager(context);
            recyclerView.setLayoutManager(linearLayout);
        DividerItemDecoration decoration = new DividerItemDecoration(context,DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(decoration);

        mSharedPreference = context.getSharedPreferences(AppConstant.PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
        mLoggedInUserContactNumber = mSharedPreference.getString(AppConstant.LOGGED_IN_USER_CONTACT_NUMBER,null);

     //   final DatabaseHandler databaseHandler=new DatabaseHandler(context);

        //fetch Data From Database
        firebaseDatabase = FirebaseDatabase.getInstance();

        userList = new ArrayList<>();
        databaseUser = firebaseDatabase.getReference("users");
        try {

            Log.d(TAG, "onDataChange: ");
            databaseUser.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Log.d(TAG, "onDataChange: "+dataSnapshot);
                    List<UserContact> contactsList = new ArrayList<>();
                    for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                        user = userSnapshot.getValue(User.class);
                        Log.d(TAG, "onDataChange: " + user);
                        if(mLoggedInUserContactNumber!=null && !mLoggedInUserContactNumber.equalsIgnoreCase(user.getPhoneNumber()))
                        {
                            String userID, userName, userLastName, userPhoneNumber;
                            userID = user.getId().toString().trim();
                            userName = user.getFirstname().toString().trim();
                            userLastName = user.getLastname().toString().trim();
                            userPhoneNumber= user.getPhoneNumber().toString().trim();
                            Log.d(TAG, "onDataChange: user Data"+userID+","+userName+","+userLastName+","+userPhoneNumber);
                            Log.d(TAG, "onDataChange: inside If");
                            Log.d(TAG, "onDataChange: "+user.toString());

                            UserContact userContact=new UserContact();
                            userContact.setUserName(userName);
                            userContact.setUserLastName(userLastName);
                            userContact.setUserPhoneNumber(userPhoneNumber);
                            userContact.setUserId(userID);
                            Log.d(TAG, "onDataChange: ");
                            Log.d(TAG, "onDataChange: inside If"+userList+"\n"+userID);
                           // databaseHandler.insertUser(userContact);
                            contactsList.add(userContact);
                        }
                        else {
                            Log.d(TAG, "onDataChange: inside Else");
                            checkCurrentUser();
                        }
                        Log.d(TAG, "onDataChange: UserId  " +user.getId());
                    }
                    //Log.d(TAG, "onDataChange: UserContactList "+ databaseHandler.displayUserContact());
                    adapter.setContactList(contactsList);
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Log.w(TAG, "Failed to read value.", databaseError.toException());
                }
            });


            Log.d(TAG, "onCreateView: ");
            //Log.d(TAG, "Database Size: "+databaseHandler.displayUserID().size());
        }catch (Exception e){
            Log.e(TAG, "Exception: "+e.toString());
        }

     //   adapter = new ContactRecycleViewActivity(context, databaseHandler.displayUserContact());

        Log.d(TAG, "after Send Adapter: " + adapter);
        Log.d(TAG, "onCreateView: " + userList);
        recyclerView.setAdapter(adapter);

        //MyContactAdapter adapter=new MyContactAdapter(context,userList);*/
        return view;


    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
    }

    private void checkCurrentUser()
    {
        SharedPreferences.Editor editor = mSharedPreference.edit();
        editor.putString(AppConstant.LOGGED_IN_USER_NAME,user.getFirstname().trim()+" "+user.getLastname().trim());
        editor.putString(AppConstant.LOGGED_IN_USER_ID,user.getId());
        editor.apply();

        FirebaseMessaging.getInstance().subscribeToTopic(user.getId())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = getString(R.string.msg_subscribed);
                        if (!task.isSuccessful()) {
                            msg = getString(R.string.msg_subscribe_failed);
                        }
                        Log.d(TAG, msg);
                    }
                });
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
    @Override
    public void onDetach() {
    super.onDetach();

    }
}
