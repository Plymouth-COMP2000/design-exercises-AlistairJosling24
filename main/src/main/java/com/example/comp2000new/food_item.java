package com.example.comp2000new;

public class food_item {
    private String Title;
    private String Description;
    private String Price;
    private int Image;

    public food_item(String Title, String Description, String Price, int Image){
        this.Title = Title;
        this.Description = Description;
        this.Price = Price;
        this.Image = Image;
    }

    public String getTitle() { return Title; }
    public String getDescription() { return Description; }
    public String getPrice() { return Price; }
    public int getImage() { return Image;}

    }

