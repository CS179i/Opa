package com.example.opa.questionnaire;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.opa.R;
import com.example.opa.models.Question;
import com.example.opa.popups.RegisterUserPopUpActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class QuestionnaireActivity extends AppCompatActivity {

    ArrayList<Question> questions = new ArrayList<>();
    Button btnSubmit;
    String questionnaireTitle;
    Context context;

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private DatabaseReference mDatabase;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire);

        context = this;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        questionnaireTitle = getIntent().getStringExtra("questionnaire");

        final RecyclerView rvQuestions = findViewById(R.id.rv_questions);
        QuestionAdapter adapter = new QuestionAdapter(questions, this);
        populateQuestions(rvQuestions, adapter, questionnaireTitle);


        btnSubmit = findViewById(R.id.btn_submit_questionnaire);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> results = new ArrayList<>();
                boolean flag = false;
                for (Question q : questions) {
                    if (q.getResponse() == "") {
                        Toast.makeText(context, "Error: Answer all questions!", Toast.LENGTH_LONG);
                        flag = true;
                    } else {
                        results.add(q.getResponse());
                    }
                }
                if (!flag) {
                    mDatabase.child("events").child(questionnaireTitle).child("users").child(mAuth.getUid()).setValue(results);
                    Intent intent = new Intent(QuestionnaireActivity.this,
                            RegisterUserPopUpActivity.class);
                    startActivity(intent);
                }
            }
        });

    }

    private void populateQuestions(final RecyclerView rvQuestions, final QuestionAdapter adapter,
                                   String questionnaireTitle) {

        mDatabase.child("questionnaires").child(questionnaireTitle).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot d : dataSnapshot.getChildren()) {
                    adapter.questions.add(new Question(d.child("content").getValue().toString(), ""));
                }
                rvQuestions.setAdapter(adapter);
                rvQuestions.setLayoutManager(new LinearLayoutManager(QuestionnaireActivity.this));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });
    }
}
