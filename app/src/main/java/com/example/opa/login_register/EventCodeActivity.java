package com.example.opa.login_register;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.opa.R;
import com.example.opa.models.User;
import com.example.opa.questionnaire.QuestionnaireActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class EventCodeActivity extends AppCompatActivity {

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private DatabaseReference mDatabase;
    private Button btnSubmit;
    private EditText etEventId;
    private Context context;
    private boolean found = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_code);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        context = this;
        etEventId = findViewById(R.id.etEventId);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkValidEventId(etEventId.getText().toString())) {
                    enterEvent();
                }
                else{
                    new AlertDialog.Builder(context)
                            .setTitle("Event Code Error")
                            .setMessage("Please make sure to enter a valid event code.")
                            .setPositiveButton(android.R.string.ok, null)
                            .show();
                }
            }
        });
    }

    private void enterEvent() {
        // TODO: add user to event in database
        Intent intent = new Intent(EventCodeActivity.this, QuestionnaireActivity.class);
        startActivity(intent);
        finish();
    }

    private boolean checkValidEventId(final String searchText) {
        final Query query = mDatabase.child("events").orderByChild("eventID").equalTo(searchText);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() == searchText) {
                    found = true;
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return found;
    }
}
