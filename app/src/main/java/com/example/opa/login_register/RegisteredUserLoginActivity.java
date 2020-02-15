package com.example.opa.login_register;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.opa.R;

import androidx.appcompat.app.AppCompatActivity;

public class RegisteredUserLoginActivity extends AppCompatActivity{
    Button btnLogIn;
    Button btnSignUp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered_user_login);

        btnLogIn = findViewById(R.id.bLogIn);
        btnSignUp = findViewById(R.id.bSignUp);

        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisteredUserLoginActivity.this, EventCodeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisteredUserLoginActivity.this, EventCodeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
