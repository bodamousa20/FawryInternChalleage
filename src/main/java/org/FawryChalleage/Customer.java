package org.FawryChalleage;

public class Customer {
    private String name ;
    private Cart cart ;
    private double balance ;


    public Customer(String name , Long balance) {
        this.name = name;
        this.cart = new Cart();
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public Cart getCart() {
        return cart;
    }

    public double getBalance() {
        return balance;
    }
    public void deduce(double totalPrice){
        balance -= totalPrice;
    }
}
