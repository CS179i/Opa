package com.example.opa.login_register;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.opa.MyCallBack;
import com.example.opa.MyCallBackString;
import com.example.opa.R;
import com.example.opa.questionnaire.QuestionnaireActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.appcompat.app.AppCompatActivity;

public class EventCodeActivity extends AppCompatActivity {

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private DatabaseReference mDatabase;
    private Button btnSubmit;
    private EditText etEventId;
    private Context context;

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
                checkValidEventId(etEventId.getText().toString());
            }
        });
    }

    private void enterEvent(String title, String eventId) {
        Intent intent = new Intent(EventCodeActivity.this, QuestionnaireActivity.class);
        intent.putExtra("questionnaire", title);
        intent.putExtra("eventId", eventId);
        startActivity(intent);
        finish();
    }

    private void checkValidEventId(final String searchText) {
        readData(new MyCallBack() {
            @Override
            public void onCallback(boolean value) {
                Log.d("TAG", "value");
                if (value) {
                    getQuestionnaire(new MyCallBackString() {
                        @Override
                        public void onCallback(String value) {
                            enterEvent(value, searchText);
                        }
                    }, searchText);
                } else {
                    new AlertDialog.Builder(context)
                            .setTitle("Event Code Error")
                            .setMessage("Please make sure to enter a valid event code.")
                            .setPositiveButton(android.R.string.ok, null)
                            .show();
                }
            }
        }, searchText);
    }

    private void getQuestionnaire(final MyCallBackString myCallback, String searchtext) {
        mDatabase.child("events").child(searchtext).child("questionnaire").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                myCallback.onCallback(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
    }

    private void readData(final MyCallBack myCallback, final String searchtext) {
        mDatabase.child("events").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild(searchtext)) myCallback.onCallback(true);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
    }
}
