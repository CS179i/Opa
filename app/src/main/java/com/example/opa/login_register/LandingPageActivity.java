package com.example.opa.login_register;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.opa.R;

import androidx.appcompat.app.AppCompatActivity;

public class LandingPageActivity extends AppCompatActivity {

    private Button guestLogin;
    private Button registeredUserLogin;
    private Button organizerLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landingpage);

        guestLogin = findViewById(R.id.guestLoginButton);
        registeredUserLogin = findViewById(R.id.registeredUserLoginButton);
        organizerLogin = findViewById(R.id.organizerLoginButton);

        setOnClicks();
    }

    private void setOnClicks() {
        guestLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(LandingPageActivity.this, GuestLoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        registeredUserLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(LandingPageActivity.this, RegisteredUserLoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        organizerLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(LandingPageActivity.this, OrganizerLoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
