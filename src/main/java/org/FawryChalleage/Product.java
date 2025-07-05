package org.FawryChalleage;

import java.time.LocalDate;

public class Product implements  ShippableItem{
    private String name ;
    private double price ;
    private Long quantity ;
    private Boolean isExpired ;
    private LocalDate expiredDate ;
    private Boolean isShippable ;
    private double weight ;

    public Product(String name , double price , Long quantity ,Boolean isExpired , LocalDate expiredDate ,boolean shippable , double weight){
        this.name = name;
        this.price = price ;
        this.expiredDate = expiredDate ;
        this.quantity = quantity ;
        this.isExpired = isExpired ;
        this.isShippable = shippable ;
        this.weight = weight ;
    }



    public String getName() {
        return name;
    }


    public double getPrice() {
        return price;
    }

    public double getQuantity() {
        return quantity;
    }

    // check Product ExpiredDate
    public Boolean getExpired() {
        return isExpired && LocalDate.now().isAfter(expiredDate);
    }

    public void decreaseQuantity(Long qty){
        this.quantity -= qty ;
    }

    public LocalDate getExpiredDate() {
        return expiredDate;
    }


    public Boolean isShippable() {
        return isShippable;
    }


    public double getItemWeight() {
        return weight;
    }


}
