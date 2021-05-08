package com.example.gymproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CardMainMenuAdapter extends RecyclerView.Adapter<CardMainMenuAdapter.CardMainMenuAdapterViewHolder> {

    private List<CardMainMenu> cards;

    public CardMainMenuAdapter(List<CardMainMenu> cards) {
        this.cards = cards;
    }

    public class CardMainMenuAdapterViewHolder extends RecyclerView.ViewHolder{

        TextView activityName;
        ImageView cardImg;

        public CardMainMenuAdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            activityName = itemView.findViewById(R.id.activity_name);
            cardImg =  itemView.findViewById(R.id.card_img);
        }
    }

    @NonNull
    @Override
    public CardMainMenuAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_menu_layout,parent,false);
        CardMainMenuAdapterViewHolder cardMainMenuAdapterViewHolder = new CardMainMenuAdapterViewHolder(view);

        return cardMainMenuAdapterViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CardMainMenuAdapterViewHolder holder, int position) {

        CardMainMenu card = cards.get(position);
        holder.activityName.setText(card.getHeader());
        holder.cardImg.setImageResource(card.getCardImgId());
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }
}
