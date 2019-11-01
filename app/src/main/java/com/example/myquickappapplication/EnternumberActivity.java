package com.example.myquickappapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.myquickappapplication.Database.CountryData;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EnternumberActivity extends AppCompatActivity {

    //@BindView(R.id.enternumber_text)
    EditText editText;
    //@BindView(R.id.contact_button)
    Button button;

    //    EditText editText;
    //  MaterialButton button;
    private Spinner countryCode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enternumber);
        editText = findViewById(R.id.enternumber_text);
        button = findViewById(R.id.contact_button);


        countryCode = findViewById(R.id.spinnerCountries);
        countryCode.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,CountryData.countriesNames));
        //ButterKnife.bind(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = editText.getText().toString();
                String code= CountryData.countriesAreaCode[countryCode.getSelectedItemPosition()];

                if (number.isEmpty() || number.length() < 10) {
                    editText.setError("Enter a valid mobile");
                    editText.requestFocus();
                    return;
                }
                String phoneNumber = "+91" + number;

                Intent intent = new Intent(EnternumberActivity.this, VerificationActivity.class);
                intent.putExtra("phoneNumber", number);
                startActivity(intent);
            }


        });

    }
}

