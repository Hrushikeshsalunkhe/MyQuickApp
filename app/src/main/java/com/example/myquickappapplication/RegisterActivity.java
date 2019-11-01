package com.example.myquickappapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myquickappapplication.Models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    EditText editTextName;
    EditText editTextLastName;
    Button button_Register;
    FirebaseAuth mFirebaseAuth;
    String phoneNumber;
    //String Base64id;
    private static final String TAG = "RegistrationActivity";

    DatabaseReference mDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        editTextName=findViewById(R.id.firstname_txt);
        editTextLastName=findViewById(R.id.lastname_txt);
        button_Register=findViewById(R.id.Register_button);
        //txtName = findViewById(R.id.txtUserName);

        phoneNumber=getIntent().getStringExtra("phoneNumber");
        mDatabaseReference=FirebaseDatabase.getInstance().getReference("users");
        mFirebaseAuth = FirebaseAuth.getInstance();
        Log.d(TAG,"onCreate:"+mDatabaseReference.toString());
        button_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Firstname= editTextName.getText().toString();
                String Lastname = editTextLastName.getText().toString();

                byte[] bytesEncoded = Base64.encode(phoneNumber.getBytes(), Base64.DEFAULT);
                String Base64id= new String(bytesEncoded);

                String id=Base64id.replace("==","").trim();   //base 64 id to send for messeage
                if (TextUtils.isEmpty(Firstname)){
                    Toast.makeText(RegisterActivity.this, "please enter firstname", Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(Lastname)){
                    Toast.makeText(RegisterActivity.this,"please enter lastname",Toast.LENGTH_SHORT).show();
                }
                Log.d(TAG, "onComnplete after: ");
                User information=new User(Firstname,Lastname,phoneNumber,id);
                FirebaseDatabase.getInstance().getReference("Users")
                        .child(new String(bytesEncoded).trim())
                        .setValue(information).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Intent intent =new Intent(RegisterActivity.this,HomeActivity.class);

                        //intent of new activity
                        Toast.makeText(RegisterActivity.this, "logged in successfully", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                        finish();
                    }
                });

            }
        });
    }



}
