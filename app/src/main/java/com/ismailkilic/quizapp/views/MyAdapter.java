package com.ismailkilic.quizapp.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ismailkilic.quizapp.R;
import com.ismailkilic.quizapp.models.Score;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private Context context;
    private List<Score> scores;
    public MyAdapter(Context context) {
        this.context = context;
    }

    public void setScores(List<Score> scores) {
        this.scores = scores;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_view_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txtCategory.setText(this.scores.get(position).getCategory());
        holder.txtTotal.setText(String.valueOf(this.scores.get(position).getTotalQuestions()));
        holder.txtWrong.setText(String.valueOf(this.scores.get(position).getWrongAnswer()));
        holder.txtCorrect.setText(String.valueOf(this.scores.get(position).getCorrectAnswer()));
        holder.txtMaxScore.setText(String.valueOf(this.scores.get(position).getMaxScore()));
        int percent = (this.scores.get(position).getCorrectAnswer() * 100 ) / this.scores.get(position).getTotalQuestions();
        holder.txtCorrectPercent.setText( String.valueOf(percent) + "%");
        holder.progressBar.setMax(this.scores.get(position).getTotalQuestions());
        holder.progressBar.setProgress(this.scores.get(position).getCorrectAnswer());
        int id = context.getResources().getIdentifier(this.scores.get(position).getCategoryImage(), null, context.getPackageName());
        holder.imageView.setImageResource(id);
    }

    @Override
    public int getItemCount() {
        return  this.scores.size();

    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView txtCategory, txtTotal, txtWrong, txtCorrect, txtMaxScore, txtCorrectPercent;
        ProgressBar progressBar;
        ImageView imageView;

        public MyViewHolder(View view) {
            super(view);
            txtCategory = view.findViewById(R.id.txtCategory);
            txtTotal = view.findViewById(R.id.txtTotal);
            txtWrong = view.findViewById(R.id.txtWrong);
            txtCorrect = view.findViewById(R.id.txtCorrect);
            txtMaxScore = view.findViewById(R.id.txtMaxScore);
            txtCorrectPercent = view.findViewById(R.id.txtCorrectPercent);
            progressBar = view.findViewById(R.id.progressBar);
            imageView = view.findViewById(R.id.categoryImg);
        }
    }
}
