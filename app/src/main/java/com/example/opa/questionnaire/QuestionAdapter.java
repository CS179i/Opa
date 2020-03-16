package com.example.opa.questionnaire;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.opa.R;
import com.example.opa.models.Question;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {

    public ArrayList<Question> questions;
    private Context context;


    public QuestionAdapter(ArrayList<Question> questions, Context context) {
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
        String pos = String.valueOf(position + 1);
        holder.tvPosition.setText(pos + ". ");
        holder.tvQuestion.setText(question.getQuestion());


        for (int i = 0; i < holder.radioGroup .getChildCount(); i++) {
            ((RadioButton) holder.radioGroup.getChildAt(i)).setText(question.getResponseList().get(i).getResponse());
        }
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvPosition;
        private TextView tvQuestion;
        private RadioGroup radioGroup;

        public ViewHolder(View itemView){
            super(itemView);

            tvPosition = itemView.findViewById(R.id.tv_position);
            tvQuestion = itemView.findViewById(R.id.tv_question);
            radioGroup = itemView.findViewById(R.id.radio_group);

            int radioId = radioGroup.getCheckedRadioButtonId();
        }
    }
}
