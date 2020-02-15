package com.example.opa.login_register;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.opa.R;
import com.example.opa.models.GuestUser;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class GuestLoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private Button btnNext;
    private EditText etName;
    private Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_login);

        //mDatabase = FirebaseDatabase.getInstance().getReference();

        context = this;
        etName = findViewById(R.id.etName);
        btnNext = findViewById(R.id.btnNext);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkValidName()) {
                        makeGuestAccount();
                    }
                    else{
                        new AlertDialog.Builder(context)
                                .setTitle("Authentication Error")
                                .setMessage("There is an error authenticating. 1) Make sure your email is formatted properly \n 2) Passwords match \n 3)Password is at least 6 characters.")
                                .setPositiveButton(android.R.string.ok, null)
                                .show();
                    }
                }
            });
    }

    private void makeGuestAccount() {
        mAuth = FirebaseAuth.getInstance();
        mAuth.signInAnonymously().addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    writeNewGuestUser(mAuth.getCurrentUser().getUid(), etName.getText().toString());
                    Intent intent = new Intent(GuestLoginActivity.this, EventCodeActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private boolean checkValidName() {
        return etName.getText().toString().length() > 0;
    }

    private void writeNewGuestUser(String userId, String name) {
        GuestUser guest = new GuestUser(userId, name);
        mDatabase.child("guestUsers").child(userId).setValue(guest);
    }

}
