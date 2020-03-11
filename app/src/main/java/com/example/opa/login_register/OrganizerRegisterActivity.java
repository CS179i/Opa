package com.example.opa.login_register;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.opa.HomeActivity;
import com.example.opa.R;
import com.example.opa.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class OrganizerRegisterActivity extends AppCompatActivity {
    private Button btnSignUp;
    private EditText email, password, name;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_organizer);

        btnSignUp = findViewById(R.id.bSUOLRegister);

        email = findViewById(R.id.etOLEmail);
        password = findViewById(R.id.etSUOLPassword);
        name = findViewById(R.id.etSUOLName);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference("users");

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                // Intentionally check in this order. If all three checks pass, they go to "else"
                // Company and Phone are optional
                if (!checkValidEmail()){
                    Toast.makeText(OrganizerRegisterActivity.this,
                            "Please provide an email.",
                            Toast.LENGTH_SHORT).show();
                }
                else if(!checkValidPass()){
                    Toast.makeText(OrganizerRegisterActivity.this,
                            "Please provide a password.",
                            Toast.LENGTH_SHORT).show();
                }
                else if(!checkValidName()){
                    Toast.makeText(OrganizerRegisterActivity.this,
                            "Please provide a name.",
                            Toast.LENGTH_SHORT).show();
                }
                else if(!checkPassLen()){
                    Toast.makeText(OrganizerRegisterActivity.this,
                            "Password must be at least 6 characters.",
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    mAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                            .addOnCompleteListener(OrganizerRegisterActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()) {
                                        storeInDatabase(mAuth.getCurrentUser().getUid(),
                                                name.getText().toString());
                                        Intent intent = new Intent(OrganizerRegisterActivity.this, HomeActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                    else{
                                        Toast.makeText(OrganizerRegisterActivity.this,
                                                "Account creation failed.",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
    }

    private void storeInDatabase(String uID, String uName){
        User user = new User(uName, "organizer_user", "", "");
        mDatabase.child(uID).setValue(user);
        // Fix adding company and phone later. Cant figure it out now.
        //mDatabase.child(uID).push({'company':cmpny});

    }

    private boolean checkValidEmail() { return email.getText().toString().length() > 0; }

    private boolean checkValidName() { return name.getText().toString().length() > 0; }

    private boolean checkValidPass() { return password.getText().toString().length() > 0; }

    private boolean checkPassLen() { return password.getText().toString().length() > 5; }

}
