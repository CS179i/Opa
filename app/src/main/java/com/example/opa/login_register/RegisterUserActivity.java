package com.example.opa.login_register;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.opa.HomeActivity;
import com.example.opa.R;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterUserActivity extends AppCompatActivity {
    Button btnSignUp;
    EditText email, password, name, phone, company;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        btnSignUp = findViewById(R.id.bSURegister);

        email = findViewById(R.id.etEmail);
        password = findViewById(R.id.etSUPassword);
        name = findViewById(R.id.etSUName);
        phone = findViewById(R.id.etSUPhone);
        company = findViewById(R.id.etSUCompany);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(RegisterUserActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
