package com.example.gymproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class MyWorkoutAdapter extends RecyclerView.Adapter<MyWorkoutAdapter.MyWorkoutViewHolder> {

    private List<Workout> workouts;
    private MyWorkoutListener listener;

    interface MyWorkoutListener{
        void onClickListener(int position);
        void onWorkoutLongClicked(int position, View view, ImageView recycleBin);
        void onDeleteListener(int position);
    }

    public void setListener(MyWorkoutListener listener){
        this.listener = listener;
    }

    public MyWorkoutAdapter(List<Workout> workouts) {
        this.workouts = workouts;
    }

    public class MyWorkoutViewHolder extends RecyclerView.ViewHolder{

        TextView workoutName;
        ImageView workoutImg;
        ImageView recycleBin;

        public MyWorkoutViewHolder(@NonNull View itemView) {
            super(itemView);

            workoutName = itemView.findViewById(R.id.workout_name);
            workoutImg =  itemView.findViewById(R.id.workout_img);
            recycleBin = itemView.findViewById(R.id.workout_recycle_bin);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener != null){
                        listener.onClickListener(getAdapterPosition());
                    }
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if(listener != null){
                        listener.onWorkoutLongClicked(getAdapterPosition(), view, recycleBin);
                    }
                    return true;
                }
            });

            recycleBin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null){
                        listener.onDeleteListener(getAdapterPosition());
                    }
                }
            });

        }
    }

    @NonNull
    @Override
    public MyWorkoutViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.workout_card_layout,parent,false);
        MyWorkoutViewHolder MyWorkoutViewHolder = new MyWorkoutViewHolder(view);

        return MyWorkoutViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyWorkoutViewHolder holder, int position) {

        Workout workout = workouts.get(position);
        holder.workoutName.setText(workout.getName());
        holder.workoutImg.setImageResource(workout.getImgId());

    }

    @Override
    public int getItemCount() {
        return workouts.size();
    }

}
