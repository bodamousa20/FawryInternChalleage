package org.FawryChalleage;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleApp {
    private static final List<Product>products = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);
    private static Customer customer = new Customer("Abdelrahman",5000L);

    public static void main(String[] args) {
    while (true){
        System.out.println("========================");
        System.out.println("== Welcome To fawry Ecommerace Application ==");
        System.out.println("========================");

        System.out.println("1 . Admin Mode ");
        System.out.println("2 . Customer Mode");
        System.out.println("Enter Choice");
       switch (scanner.nextInt()){
           case 1 -> adminMode();
           case 2 -> customerMode();
           default -> System.out.println("Choose Correct Number");

       }




    }


    }

    private static void customerMode() {
        System.out.println("--------------------------");
        System.out.println("Customer Mode");
        System.out.println("--------------------------");

        System.out.println("1. view product");
        System.out.println("2. add product to cart");
        System.out.println("3. view cart");
        System.out.println("4. checkout");
        System.out.println("4. clean cart");
        System.out.println("Enter Choice");
       int choice = scanner.nextInt();
       switch (choice){
           case 1 -> listProduct();
           case 2 -> addToCart();
           case 3 -> viewCart();
           case 4 -> CheckoutServices.checkout(customer);
           case 5 -> clearCart();
           default -> System.out.println("Invalid option");
       }



    }

    private static void viewCart() {
        if(customer.getCart().getCartItems().isEmpty()){
            System.out.println("Your Cart is Empty");
        }else {
            System.out.println("Your Cart");
            for (CartItem cartItem : customer.getCart().getCartItems()) {
                System.out.println(cartItem.quantity + " " + cartItem.product.getName());
            }
        }
    }

    private static void addToCart() {
        listProduct();
        System.out.println("Enter Product Number :");
        int index = scanner.nextInt()-1;
        if(index < 0 || index >=products.size()){
            System.out.println("Invalid Product");
            return;
        }
        Product selected = products.get(index);
        System.out.println("Enter Product quantity");
        long quantity = scanner.nextInt();
        try {
            customer.getCart().addProduct(selected,quantity);
            System.out.println("Added to Cart");


        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


    private static void adminMode() {
        while (true){
            System.out.println("Admin panel");
            System.out.println("1 . Add product");
            System.out.println("2 . List product");
            System.out.println("3. Back to menu");
            System.out.println("Enter Choice");
          int choice =  scanner.nextInt();
          if(choice ==3 ) break;
          switch (choice){
              case 1 -> addProduct();
              case 2 -> listProduct();
              default -> System.out.println("please select correct ");

          }
        }
    }

    private static void listProduct() {
        System.out.println("Avaliable Product");
        if(products.isEmpty()){
            System.out.println("No product Yet");
        }
        for(int i = 0 ;i< products.size();i++){
           Product p = products.get(i);
            System.out.println(i+1 +" name : "+p.getName()+ "   " +"price : "+ p.getPrice()+"  "+"quantity : "+ p.getQuantity());
            System.out.println();
        }


    }

    private static void addProduct() {
        try {
            System.out.println("Enter Product Name");
            scanner.nextLine(); // consume leftover newline
            String name = scanner.nextLine();
            System.out.println("Enter Product price");
            double price = scanner.nextDouble();
            System.out.println("Enter Product quantity");
            long quantity = scanner.nextLong();
            System.out.println("Is product exipriable ? (true/false");
            Boolean isExpirable = scanner.nextBoolean();
            LocalDate expireDate = null ;
            if(isExpirable){
                System.out.println("Please Enter product Date (yyyy-mm-dd) ");
                expireDate = LocalDate.parse(scanner.next());
            }
            System.out.println("Is product is shippable (true/false)");
            boolean shippable = scanner.nextBoolean();
            double weight = 0 ;
            if(shippable){
                System.out.println(" Enter Weight (kg)");
                weight = scanner.nextDouble();

            }
            products.add(new Product(name,price,quantity,isExpirable,expireDate,shippable,weight));

            System.out.println("Product added Successfully");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            scanner.nextLine();
        }
    }
    private static void clearCart(){
        customer.getCart().getCartItems().clear();
        System.out.println("Cart Cleaned Successfully");
    }

}
