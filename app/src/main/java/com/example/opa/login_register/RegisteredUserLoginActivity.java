package com.example.opa.login_register;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.opa.HomeActivity;
import com.example.opa.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class RegisteredUserLoginActivity extends AppCompatActivity{
    private Button btnLogIn;
    private Button btnSignUp;
    private EditText etName;
    private EditText etPassword;
    private FirebaseAuth mAuth;
    private Context context;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered_user_login);

        context = this;
        btnLogIn = findViewById(R.id.bLogIn);
        btnSignUp = findViewById(R.id.bSignUp);
        etName = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        mAuth = FirebaseAuth.getInstance();

        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkValidName() && checkValidPass()){
                    mAuth.signInWithEmailAndPassword(etName.getText().toString(), etPassword.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(RegisteredUserLoginActivity.this,
                                                "Authentication Successful!",
                                                Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(RegisteredUserLoginActivity.this, HomeActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                    else{
                                        badLogin();
                                    }

                                }
                            });

                }
                else{
                    badLogin();
                }

            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisteredUserLoginActivity.this, RegisterUserActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void badLogin(){
        new AlertDialog.Builder(context)
                .setTitle("Authentication Error")
                .setMessage("Username or Password incorrect.")
                .setPositiveButton(android.R.string.ok, null)
                .show();
    }

    private boolean checkValidName() {
        return etName.getText().toString().length() > 0;
    }

    private boolean checkValidPass() { return etName.getText().toString().length() > 0; }

}
