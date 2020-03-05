package com.example.opa.login_register;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.opa.HomeActivity;
import com.example.opa.R;
import com.example.opa.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterUserActivity extends AppCompatActivity {
    private Button btnSignUp;
    private EditText email, password, name, phone, company;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

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
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference("users");

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                // Intentionally check in this order. If all three checks pass, they go to "else"
                // Company and Phone are optional
                if (!checkValidEmail()){
                    Toast.makeText(RegisterUserActivity.this,
                            "Please provide an email.",
                            Toast.LENGTH_SHORT).show();
                }
                else if(!checkValidPass()){
                    Toast.makeText(RegisterUserActivity.this,
                            "Please provide a password.",
                            Toast.LENGTH_SHORT).show();
                }
                else if(!checkValidName()){
                    Toast.makeText(RegisterUserActivity.this,
                            "Please provide a name.",
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    mAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                            .addOnCompleteListener(RegisterUserActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()) {
                                        storeInDatabase(mAuth.getCurrentUser().getUid(),
                                                name.getText().toString(),
                                                company.getText().toString(),
                                                phone.getText().toString());
                                        Intent intent = new Intent(RegisterUserActivity.this, HomeActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                    else{
                                        Toast.makeText(RegisterUserActivity.this,
                                                "Account creation failed.",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
    }

    private void storeInDatabase(String uID, String uName, String cmpny, String phn){
        User user = new User(uID, uName, "registered_user");
        mDatabase.child(uID).setValue(user);
        // Fix adding company and phone later. Cant figure it out now.
        //mDatabase.child(uID).push({'company':cmpny});

    }

    private boolean checkValidEmail() { return email.getText().toString().length() > 0; }

    private boolean checkValidName() { return name.getText().toString().length() > 0; }

    private boolean checkValidPass() { return password.getText().toString().length() > 0; }
}
