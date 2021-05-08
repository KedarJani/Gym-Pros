package com.example.gymproject;

public abstract class Card {

   protected String header;
   protected int cardImgId;

    public Card(String header, int cardImgId) {
        this.header = header;
        this.cardImgId = cardImgId;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public int getCardImgId() {
        return cardImgId;
    }

    public void setCardImgId(int cardImgId) {
        this.cardImgId = cardImgId;
    }
}
