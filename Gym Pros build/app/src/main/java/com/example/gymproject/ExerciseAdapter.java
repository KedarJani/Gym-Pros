package com.example.gymproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder> {

    private List<Exercise> exercises;
    private ExercisesListener listener;

    interface ExercisesListener{
        void onCheckBoxListener(int position);
    }

    public void setLisener(ExercisesListener exercisesListener){this.listener = exercisesListener;};

    public ExerciseAdapter(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    public class ExerciseViewHolder extends RecyclerView.ViewHolder{

        TextView exeName;
        ImageView exeImg;
        CheckBox checkBox;

        public ExerciseViewHolder(@NonNull View itemView) {
            super(itemView);

            exeName = itemView.findViewById(R.id.exercise_name);
            exeImg =  itemView.findViewById(R.id.exercise_img);
            checkBox = itemView.findViewById(R.id.exe_checkbox);


            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null)
                        listener.onCheckBoxListener(getAdapterPosition());
                }
            });
        }
    }

    @NonNull
    @Override
    public ExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.exercise_layout,parent,false);
        ExerciseViewHolder exerciseViewHolder = new ExerciseViewHolder(view);

        return exerciseViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseViewHolder holder, int position) {

        Exercise exercise = exercises.get(position);
        holder.exeName.setText(exercise.getName());
        holder.exeImg.setImageResource(exercise.getExeImgId());

        if(exercise.isSelected())
        holder.checkBox.setChecked(true);
        else
            holder.checkBox.setChecked(false);

    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }
}
