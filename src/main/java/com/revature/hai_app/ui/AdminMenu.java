package com.revature.hai_app.ui;

import com.revature.hai_app.daos.*;
import com.revature.hai_app.models.Inventinstance;
import com.revature.hai_app.models.Orders;
import com.revature.hai_app.models.Product;
import com.revature.hai_app.models.User;
import com.revature.hai_app.services.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminMenu implements MenuTemplate{
    OrderInstanceService orderInstanceService = new OrderInstanceService(new orderInstDAO());
    StoreService StoreService = new StoreService(new storeDAO());
    OrderService orderService = new OrderService(new ordersDAO());
    UserService userService = new UserService(new userDAO());
    CartService cartService = new CartService(new cartDAO());
    ProductService productService = new ProductService(new productDAO());
    InventoryService inventoryService = new InventoryService(new inventInstDAO());
    @Override
    public void start(){
        displayAdminMenu();
    }

    private void displayAdminMenu() {
        System.out.println("\n+------------------------------+");
        System.out.println("Welcome to  the Admin Menu");
        System.out.println("[1] View Store Order History");
        System.out.println("[2] Restock Inventory");
        System.out.println("[3] Search Users");
        System.out.println("[x] Log out");
        System.out.println("+------------------------------+");

        Scanner scan = new Scanner(System.in);
            while (true) {
                System.out.print("Enter: ");
                String input = scan.nextLine();
                switch (input) {
                    case "1":
                        historyByStore();
                        break;
                    case "2":
                        displayItemsSearched(searchItemRestock());
                        break;
                    case "3":
                        displayUserSearched(searchUsers());
                    case "x":
                        new StartMenu(new UserService(new userDAO())).start();
                        break;
                    default:
                        System.out.println("Invalid input.");
                }
            }
    }
    private void historyByStore() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please select which location you would like to view order history.\n");
        System.out.println("[1] DIMENSION 0");
        System.out.println("[2] DIMENSION 161");
        System.out.println("[3] DIMENSION 225");
        System.out.println("[4] DIMENSION 665");
        System.out.println("[5] DIMENSION 998");
        System.out.println("[x] Back to Admin Menu");

            while(true) {
                System.out.print("\nEnter: ");
                String input = scan.nextLine();
                switch (input) {
                    case "1":
                    try {
                        displayOrders(orderInstanceService.getOrdersByStoreID(StoreService.getByStoreLocation("DIMENSION 0").getId()));
                        break;
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("There are no existing orders under this store. \n");
                        historyByStore();
                        break;
                    }
                    case "2":
                    try {
                        displayOrders(orderInstanceService.getOrdersByStoreID(StoreService.getByStoreLocation("DIMENSION 161").getId()));
                        break;
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("There are no existing orders under this store. \n");
                        historyByStore();
                        break;
                    }
                    case "3":
                    try {
                        displayOrders(orderInstanceService.getOrdersByStoreID(StoreService.getByStoreLocation("DIMENSION 225").getId()));
                        break;
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("There are no existing orders under this store. \n");
                        historyByStore();
                        break;
                    }
                    case "4":
                    try {
                        displayOrders(orderInstanceService.getOrdersByStoreID(StoreService.getByStoreLocation("DIMENSION 665").getId()));
                        break;
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("There are no existing orders under this store. \n");
                        historyByStore();
                        break;
                    }
                    case "5":
                        try {
                            displayOrders(orderInstanceService.getOrdersByStoreID(StoreService.getByStoreLocation("DIMENSION 998").getId()));
                            break;
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("There are no existing orders under this store. \n");
                            historyByStore();
                            break;
                        }
                    case "x":
                        displayAdminMenu();
                        break;
                    default:
                        System.out.println("Invalid input");
                }
            }
        }
    private void displayOrders(List<String> orderIDs){
        Scanner scan = new Scanner(System.in);
        List<Orders> ordersList = new ArrayList<>();
        for(String s:orderIDs){ //Getting Orders object and putting into list.
            ordersList.add(orderService.getByOrderID(s));
        }
        System.out.println("Below are orders completed under "+ StoreService.getLocationByStoreID(orderInstanceService.getByOrderID(orderIDs. get(0)).getStore_id()) + ".");
        System.out.println("+---------------------------------------------> >>");
        for(Orders ord:ordersList){
            System.out.println("Order ID: "+ord.getId());
            System.out.println("Order Date: "+ord.getDate());
            System.out.println("User: "+userService.getUserByID(ord.getUser_id()).getUsername());
            //Printing Items Bought in a list
            List<String> prodIDsArray = cartService.getProductIDsByOrderID(ord.getId());
            StringBuilder itemsBought = new StringBuilder("Item Bought: ");
            for(String s:prodIDsArray){
                itemsBought.append(productService.getProductNameByID(s));
                itemsBought.append(", ");
            }
            itemsBought.delete(itemsBought.length()-2, itemsBought.length());
            System.out.println(itemsBought);
            System.out.println("Product Quantity: "+ord.getProduct_qty());
            System.out.println("Purchase Total: "+ord.getPrice_total());
            System.out.println("+---------------------------------------------> >>");
        }
        System.out.println("Press enter to back to browse order history by store.");
        scan.nextLine();
        historyByStore();
    }
    private List<Product> searchItemRestock(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please type in the item you would like to restock: ");
        System.out.print("\nEnter: ");
        String input = scanner.nextLine();
        List<Product> productsBySearch = productService.searchProdsByName(input);
        return productsBySearch;
    }
    private List<User> searchUsers(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please type in the user you would like to view order history: ");
        System.out.print("\nEnter: ");
        String input = scanner.nextLine();
        List<User> usersBySearch = userService.searchUsersByName(input);
        return usersBySearch;
    }
    private void displayUserSearched(List<User> usersBySearch) {
        Scanner scanner = new Scanner(System.in);
                System.out.println("Search result: " + usersBySearch.size() + " user(s) found.");
                System.out.println("+---------------------------------------------> >>");
                for (int i = 0; i < usersBySearch.size(); i++) {
                    System.out.println("[" + (i + 1) + "] Username: " + usersBySearch.get(i).getUsername());
                    System.out.println("Address: " + usersBySearch.get(i).getAddress());
                    System.out.println("State: " + usersBySearch.get(i).getState());
                    System.out.println("Role: " + usersBySearch.get(i).getRole());
                    System.out.println("Store Credits: " + usersBySearch.get(i).getStorecredits());
                    System.out.println("+---------------------------------------------> >>");
                }
                System.out.println("Select a user to view order history. Or Type in 0 to back to Admin Menu.");
                System.out.print("Enter: ");
                String input = scanner.nextLine();
                int numInput = Integer.parseInt(input) - 1;
                if (numInput >= 0 && numInput < usersBySearch.size()) {
                    displayUserOrderHistory(usersBySearch.get(numInput));
                    System.out.println("Press enter to go back to Admin Menu");
                    scanner.nextLine();
                    displayAdminMenu();
                } else displayAdminMenu();
    }
    private void displayItemsSearched(List<Product> productsBySearch){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Search results: " +productsBySearch.size()+" product(s) found.");
        System.out.println("+---------------------------------------------> >>");
        for (int i=0; i<productsBySearch.size(); i++){
            System.out.println("["+(i+1)+"] Item Name: "+productsBySearch.get(i).getName());
            System.out.println("Store Location: "+ StoreService.getLocationByStoreID(inventoryService.getStoreIDByItemID(productsBySearch.get(i).getId())));
            //Max Quantity based on rarity
            String rarity = "";
            int maxCount = 0;
            if (productsBySearch.get(i).getRarity().equals("Basic") || productsBySearch.get(i).getRarity().equals("Uncommon")){
                maxCount = 10;
            } else if (productsBySearch.get(i).getRarity().equals("Legendary")) maxCount = 5;
            else if (productsBySearch.get(i).getRarity().equals("Mythic")) maxCount = 1;
            System.out.println("Inventory Count: "+inventoryService.getProductCountByProdID(productsBySearch.get(i).getId()) +" / "+ maxCount);
            System.out.println("+---------------------------------------------> >>");
        }
        System.out.println("Select an item to restock. Or Type in 0 to back to Admin Menu." );
        System.out.print("Enter: ");
        String input = scanner.nextLine();
        int numInput = Integer.parseInt(input) - 1;
        if (numInput >= 0 && numInput < productsBySearch.size()){
            //Printing Item:
            System.out.println("\nItem Name: "+productsBySearch.get(numInput).getName());
            System.out.println("Store Location: "+ StoreService.getLocationByStoreID(inventoryService.getStoreIDByItemID(productsBySearch.get(numInput).getId())));
            String rarity = "";
            int maxCount = 0;
            if (productsBySearch.get(numInput).getRarity().equals("Basic") || productsBySearch.get(numInput).getRarity().equals("Uncommon")){
                maxCount = 10;
            } else if (productsBySearch.get(numInput).getRarity().equals("Legendary")) maxCount = 5;
            else if (productsBySearch.get(numInput).getRarity().equals("Mythic")) maxCount = 1;
            System.out.println("Inventory Count: "+inventoryService.getProductCountByProdID(productsBySearch.get(numInput).getId()) +" / "+ maxCount);
            while(true) {
                System.out.print("\nPlease enter new inventory count for item: ");
                int newCount = scanner.nextInt();
                //Check if newCount is > current max count for item
                if (newCount > maxCount) {
                    System.out.println("ERROR: Value entered is greater than max storage for item");
                }  else {
                    //Update inventory instance
                    Inventinstance inventinstance = new Inventinstance(inventoryService.getStoreIDByItemID(productsBySearch.get(numInput).getId()), productsBySearch.get(numInput).getId(), newCount);
                    inventoryService.updateInventoryCount(inventinstance);
                    System.out.println("Updated successfully!");
                    System.out.println("Press enter to go back to main menu.");
                    scanner.nextLine();
                    displayAdminMenu();
                }
            }

        } else displayAdminMenu();
    }
    private void displayUserOrderHistory(User us){
        Scanner scan = new Scanner(System.in);
        List<Orders> orderHistory = orderService.getOrdersByUserID(us.getId());

        System.out.println("Displayed below is order history for: "+ us.getUsername() +"\n");
        System.out.println("+--------------------------------------------->");
        for(Orders ord:orderHistory){
            System.out.println("Order ID: " + ord.getId());
            System.out.println("Order Date: "+ ord.getDate());
            //Printing items bought in list
            List<String> prodIDsArray = cartService.getProductIDsByOrderID(ord.getId());
            StringBuilder itemsBought = new StringBuilder("Item Bought: ");
            for(String s:prodIDsArray){
                itemsBought.append(productService.getProductNameByID(s));
                itemsBought.append(", ");
            }
            itemsBought.delete(itemsBought.length()-2, itemsBought.length());
            System.out.println(itemsBought);
            System.out.println("Price Total: "+ ord.getPrice_total());
            System.out.println("Total Items: "+ ord.getProduct_qty());
            System.out.println("+--------------------------------------------->");
        }
    }

}
