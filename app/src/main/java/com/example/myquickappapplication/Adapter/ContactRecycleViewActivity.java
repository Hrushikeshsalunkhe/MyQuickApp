package com.example.myquickappapplication.Adapter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.example.myquickappapplication.Models.UserContact;
import com.example.myquickappapplication.R;
import com.firebase.ui.auth.data.model.PhoneNumber;

import java.util.List;

public class ContactRecycleViewActivity extends RecyclerView.Adapter<ContactRecycleViewActivity.MyViewHolder> {

        private static final String TAG = "ContactsRecyclerAdapter";

        private List<UserContact> mUserList;
        private Context mContext;
        UserContact user;

        public ContactRecycleViewActivity(Context context,List<UserContact> userlist) {
            mUserList =userlist;
            Log.d(TAG, "ContactsRecyclerAdapter: ");
            Log.d(TAG, "MyContactAdapter: "+mUserList);
            mContext = context;
            Log.d(TAG, "MyContactAdapter: "+mContext);

        }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Log.d(TAG, "onCreateViewHolder: ");
        View view= LayoutInflater.from(mContext).inflate(R.layout.activity_contact_recycle_view,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactRecycleViewActivity.MyViewHolder holder, int position) {
      user = mUserList.get(position);

        String name = user.getUserName().toString().trim().substring(0,1).toUpperCase() + user.getUserName().toString().trim().substring(1).toLowerCase();

        Log.d(TAG, "onBindViewHolder: name "+name);

        String LastName = user.getUserLastName().toString().trim().substring(0,1).toUpperCase() + user.getUserLastName().toString().trim().substring(1).toLowerCase();

        Log.d(TAG, "onBindViewHolder: surname "+LastName);

        final String fullName = name + " " + LastName;
        Log.d(TAG, "onBindViewHolder: "+fullName);

        final String PhoneNumber = user.getUserPhoneNumber().toString();

        final String userId = user.getUserId().toString();
        Log.d(TAG, "onBindViewHolder: "+userId);

        String s =fullName.substring(0,1);

        Log.d(TAG, "onBindViewHolder: s = "+s);
        holder.txtUserName.setText(fullName);
        holder.txtUserContact.setText(PhoneNumber);

        ColorGenerator generator = ColorGenerator.DEFAULT;

        int color = generator.getRandomColor();

        TextDrawable drawable = TextDrawable.builder().buildRound(s, color);
        holder.image.setImageDrawable(drawable);

        //String umob = userContacts.getUserMobileno().toString();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: ReciverUserId "+user.getUserId());
                Log.d(TAG, "onClick: ReciverUserId "+userId);

                Log.d(TAG, "onClick: number "+user.getUserPhoneNumber());
               // Log.d(TAG, "onClick: number "+U);

                Intent intent = new Intent(mContext, Message.class);
                intent.putExtra("ReciverUserID",userId);
                intent.putExtra("number",PhoneNumber);
                intent.putExtra("name",fullName);
                mContext.startActivity(intent);

            }
        });

    }
    public void setContactList(List<UserContact>contactList){
            if(mUserList!=null)
                mUserList.clear();

            mUserList=contactList;
            notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
            return mUserList.size();
    }
   class MyViewHolder extends RecyclerView.ViewHolder{
        TextView txtUserName;
        TextView txtUserContact;
        ImageView image;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            Log.d(TAG, "MyViewHolder: ");
            txtUserName = itemView.findViewById(R.id.username);
            txtUserContact = itemView.findViewById(R.id.contact_number);
            image = itemView.findViewById(R.id.userimage);


        }
    }
}
