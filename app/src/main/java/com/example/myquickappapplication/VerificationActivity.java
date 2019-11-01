package com.example.myquickappapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.TimeUnit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Button;

import android.widget.Toast;

import com.example.myquickappapplication.AddContant.AppConstant;
import com.example.myquickappapplication.Models.User;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class VerificationActivity extends AppCompatActivity {

    private static final String TAG = "VerificationActivity";
    Button button;
    EditText editText;
    //For data Store
    //sharedpreference  is used save the Database and manage the data preference to write and read
    SharedPreferences mSharedPreference;
    String phoneNumber;
    Boolean numberexit;
    // A DatabaseReference  specific location in
    // database and can be used for reading or writing data to that database location
    //key → String
    //The last part of the path at location for this DataSnapshot.
    //read-only
    DatabaseReference mDatabaseReference;
    FirebaseAuth mAuth;
    User users ;
    private String mVerificationId;
    ProgressBar mProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
        phoneNumber = getIntent().getStringExtra("phoneNumber");
        Log.d(TAG, "onCreate: phonenumber"+phoneNumber);
        mProgressBar = findViewById(R.id.progressbar);
        sentVerificationCode(phoneNumber);
        mAuth = FirebaseAuth.getInstance();
        editText=findViewById(R.id.otp_text);
        button= findViewById(R.id.contact_button);


        //for store to Database
      //  mDatabaseReference = FirebaseDatabase.getInstance().getReference("users");

        mSharedPreference=getSharedPreferences(AppConstant.PREFERENCE_FILE_NAME,MODE_PRIVATE);
    }
   /* @Override
    protected void onStart() {
        super.onStart();
    }

    public void signIn(View view) {
        String code=editText.getText().toString().trim();

        if(code.isEmpty()|| code.length()<6){
            editText.setError("Enter OTP....");
            editText.requestFocus();
            return;
        }
        Log.d(TAG, "mobile No  "+phoneNumber);
        Log.d(TAG, "code: "+code);
        mProgressBar.setVisibility(View.VISIBLE);
        verifyCode(code);
    }*/
    private void verifyCode(String code) {
        Log.d(TAG, "verifyCode: ");
        PhoneAuthCredential credential =PhoneAuthProvider.getCredential(mVerificationId,code);
        signInWithCredential(credential);
    }
     //The last part of the path at location for this DataSnapshot.
      //read-only
    private  void  signInWithCredential(PhoneAuthCredential credential){
        Log.d(TAG, "signInWithCredential: ");
        mAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            mDatabaseReference = FirebaseDatabase.getInstance().getReference("User");
                            mDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                //A DataSnapshot is an generate copy of the data at a Firebase Location.
                                // They cannot be modified and will never change
                                //Snapshot are generally created the protection
                                public void onDataChange(@NonNull DataSnapshot mDataSnapshot) {
                                    Log.d(TAG, "onDataChange: 1");
                                 numberexit=false;
                                    //if contact number is not their then is will go to boolean false

                                    SharedPreferences.Editor editor = mSharedPreference.edit();
                                    editor.putBoolean(AppConstant.isLogin, true);//boolen true if number is their in database
                                    editor.putString(AppConstant.PREFERENCE_FILE_NAME,phoneNumber);
                                    editor.apply();

                                    for (DataSnapshot userSnapshot : mDataSnapshot.getChildren()) {
                                        users = userSnapshot.getValue(User.class);

                                        Log.d(TAG, "onDataChange:" + users);


                                        if (users.getPhoneNumber().equals(phoneNumber)) {
                                            Log.d(TAG, "onDataChange: 123");
                                            numberexit = true;
                                        }
                                    }

                                        if (numberexit.equals(true)) {
                                            //  Log.d(TAG,"onDataChange:"+numberIsAvailable);

                                            Intent intent = new Intent(VerificationActivity.this, HomeActivity.class);
                                           // intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            intent.putExtra("phoneNumber",phoneNumber);
                                            startActivity(intent);
                                            finish();
                                        } else
                                            {
                                            Intent intent = new Intent(VerificationActivity.this, RegisterActivity.class);
                                            intent.putExtra("phoneNumber", phoneNumber);
                                            startActivity(intent);
                                            finish();
                                            Log.d(TAG, "onComplete: 1");
                                        }

                                    }



                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {
                                  //failed to read value
                                    Log.d(TAG, "onCancelled: ",databaseError.toException());
                                }
                            });

                        } else {
                            Toast.makeText(VerificationActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }


                    }
                });
    }
    private  void sentVerificationCode(String number){
        Log.d(TAG, "sentVerificationCode: ");
    PhoneAuthProvider.getInstance().verifyPhoneNumber(
        number,          //phone number to verify
        60,            //Timeout duration
        TimeUnit.SECONDS, //unit of timeout
                 //TaskExecutors.MAIN_THREAD,
            this,  //Activity for callbackbind
        mCallBack        //onverificatonstatedchangedcallback
    );
        Log.d(TAG, "sentVerificationCode: ");
    }
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallBack
            = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            String code =phoneAuthCredential.getSmsCode();
            if(code !=null){
                editText.setText(code);
                mProgressBar.setVisibility(View.VISIBLE);
                verifyCode(code);
                Log.d(TAG, "onVerificationCompleted: ");
                Toast.makeText(VerificationActivity.this,"Verification complete",Toast.LENGTH_SHORT).show();
            }

        }
        @Override
        public void onCodeSent(@NonNull String s,@NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken){
            super.onCodeSent(s, forceResendingToken);
            Log.d(TAG, "onCodeSent: 0" + s);
            mVerificationId = s;
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Toast.makeText(VerificationActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
            Log.d(TAG, "onVerificationFailed: ");

        }
    };
    public void verify(View view){
        String code =editText.getText().toString();
        verifyCode(code);
        Log.d(TAG, "Onclick: "+code);
    }




}
