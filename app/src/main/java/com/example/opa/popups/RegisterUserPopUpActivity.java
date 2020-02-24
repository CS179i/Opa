package com.example.opa.popups;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.opa.HomeActivity;
import com.example.opa.R;
import com.example.opa.login_register.RegisterUserActivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterUserPopUpActivity extends AppCompatActivity {

    private Button btnRegister;
    private Button btnNotNow;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user_popup);

        btnRegister = findViewById(R.id.register_button);
        btnNotNow = findViewById(R.id.btn_not_now);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterUserPopUpActivity.this, RegisterUserActivity.class);
                startActivity(intent);
            }
        });
        btnNotNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterUserPopUpActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
