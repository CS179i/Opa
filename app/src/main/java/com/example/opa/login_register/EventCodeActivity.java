package com.example.opa.login_register;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.opa.R;
import com.example.opa.questionnaire.QuestionnaireActivity;

import androidx.appcompat.app.AppCompatActivity;

public class EventCodeActivity extends AppCompatActivity {

    private Button btnSubmit;
    private Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_code);

        context = this;
        btnSubmit = findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkValidEventId()) {
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

    private boolean checkValidEventId() {
        //TODO query the database for event id
        return true;
    }
}
