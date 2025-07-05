package org.FawryChalleage;

import java.util.ArrayList;
import java.util.List;

public class CheckoutServices {
    public static void checkout(Customer currentCustomer){
       Cart custmerCart =  currentCustomer.getCart();
       if(custmerCart.isEmpty()){
           System.out.println("the Cart is Empty");
           return;
       }
       double subTotal = 0 ;
       double totalWeight = 0 ;
       List<ShippableItem> shippableItems = new ArrayList<>() ;

       for(CartItem item : custmerCart.getCartItems()) {
           Product product = item.product;
           Long quantity = item.quantity;
           double productPrice = product.getPrice();
           double productWeight = product.getItemWeight();

           if (product.getExpired()) {
               System.out.println("Error Product " + product.getName() + " is Expired ");
               System.out.println("here is the Expired Date " + product.getExpiredDate());
                return;
           }

           if (quantity > product.getQuantity()) {
               System.out.println("Error Not Enough for the Stock , please try to decrease the quantity for " + product.getName());
               return;
           }

           subTotal = subTotal + productPrice * quantity;

           if (product.isShippable()) {
               for (int i = 0; i < quantity; i++) {
                   shippableItems.add(product);
                   totalWeight = totalWeight + productWeight;
               }
           }
       }
           double shippingFees = shippableItems.isEmpty() ? 0 : 25;
           double totalPrice = subTotal + shippingFees ;
           if(currentCustomer.getBalance() <totalPrice){
               System.out.println("Error , Total Price exceed your Current Balance ");
               return;
           }

           if(!shippableItems.isEmpty()){
               System.out.println("** Shipment Notices **");
                for( ShippableItem item : shippableItems){
                System.out.printf("1x %s %.0fg%n",item.getName(),item.getItemWeight()*1000);
                }
                System.out.println("Total Package Weight "+totalWeight+"Kg");

           }

        System.out.println("** checkout Receipt **");
        for(CartItem item : custmerCart.getCartItems()){
            Product p  = item.product ;
            Long quantity = item.quantity ;
            double linetotal = quantity * p.getPrice() ;
            System.out.println(quantity +" " +  p.getName() + " " +linetotal);
            p.decreaseQuantity(quantity);

        }
        currentCustomer.deduce(totalPrice);
        System.out.println("--------------------------------------");
        System.out.printf("Subtotal             %.0f%n",subTotal);
        System.out.printf("Shipping             %.0f%n",shippingFees);
        System.out.printf("Total             %.0f%n",totalPrice);
        System.out.println("--------------------------------------");
        System.out.printf("Customer balance              %.0f%n",currentCustomer.getBalance());


        //remove cart items after checkout
        currentCustomer.getCart().getCartItems().clear();



    }

    }

