
package com.example.comp2000new;
public class payment_item {
    private String Title;

    private String Price;



    public payment_item(String Title ,  String Price){
        this.Title = Title;
        this.Price = Price;
    }

    public String getTitle() { return Title; }

    public String getPrice() { return Price; }

    public double getActualPrice() {
        return Double.parseDouble(Price.replace("£", ""));
    }

}