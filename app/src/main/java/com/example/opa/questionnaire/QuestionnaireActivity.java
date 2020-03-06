package com.example.opa.questionnaire;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.opa.HomeActivity;

import com.example.opa.R;
import com.example.opa.models.Question;
import com.example.opa.popups.RegisterUserPopUpActivity;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class QuestionnaireActivity extends AppCompatActivity {

    List<Question> questions;
    Button btnSubmit;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire);

        btnSubmit = findViewById(R.id.btn_submit_questionnaire);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QuestionnaireActivity.this, RegisterUserPopUpActivity.class);
                startActivity(intent);
            }
        });

        RecyclerView rvQuestions = findViewById(R.id.rv_questions);
        QuestionAdapter adapter = new QuestionAdapter(questions);
        // Todo add items to the recycler view
    }
}
