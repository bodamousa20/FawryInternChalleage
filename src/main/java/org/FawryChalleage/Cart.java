package org.FawryChalleage;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem>cartItems = new ArrayList<>() ;

    public String addProduct(Product product ,Long quantity) {
        if(quantity > product.getQuantity()){
            throw new RuntimeException("Not enough stock for "+product.getName());
        }
        CartItem newCartItem = new CartItem(product,quantity);

        cartItems.add(newCartItem);
        return "Product Added Successfully";
    }


    public Boolean isEmpty(){
        return cartItems.isEmpty() ;
    }

    public List<CartItem>getCartItems(){
        return cartItems ;
    }
}
