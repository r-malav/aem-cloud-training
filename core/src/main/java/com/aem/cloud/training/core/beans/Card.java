package com.aem.cloud.training.core.beans;

import java.util.List;

public class Card
{
    private String cardimage;
    private String fullname;
    private String discription;
    private List<String >foodItems;

    public Card(String cardimage, String fullname, String discription, List<String> foodItems) {
        this.cardimage = cardimage;
        this.fullname = fullname;
        this.discription = discription;
        this.foodItems = foodItems;
    }

    public String getCardimage() {
        return cardimage;
    }

    public String getFullname() {
        return fullname;
    }

    public String getDiscription() {
        return discription;
    }

    public List<String> getFoodItems() {
        return foodItems;
    }
}
