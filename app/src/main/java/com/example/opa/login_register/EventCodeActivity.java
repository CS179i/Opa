package com.example.opa.login_register;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.opa.R;
import com.example.opa.questionnaire.QuestionnaireActivity;

import androidx.appcompat.app.AppCompatActivity;

public class EventCodeActivity extends AppCompatActivity {

    Button btnSubmit;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_code);

        btnSubmit = findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EventCodeActivity.this, QuestionnaireActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
