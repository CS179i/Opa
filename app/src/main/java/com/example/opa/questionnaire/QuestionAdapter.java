package com.example.opa.questionnaire;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.opa.R;
import com.example.opa.models.Question;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {

    public List<Question> questions;
    private Context context;


    public QuestionAdapter(List<Question> questions, Context context) {
        this.questions = questions;
        this.context = context;
    }

    @NonNull
    @Override
    public QuestionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_question, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionAdapter.ViewHolder holder, int position) {
        final Question question = questions.get(position);
        holder.tvPosition.setText(String.valueOf(position + 1));
        holder.tvQuestion.setText(question.getQuestion());
        question.setResponse(holder.etResponse.getText().toString());
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvPosition;
        private TextView tvQuestion;
        private EditText etResponse;

        public ViewHolder(View itemView){
            super(itemView);

            tvPosition = itemView.findViewById(R.id.tv_position);
            tvQuestion = itemView.findViewById(R.id.tv_question);
            etResponse = itemView.findViewById(R.id.et_response);
        }
    }
}
