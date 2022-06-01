package com.revature.hai_app.ui;

import com.revature.hai_app.daos.*;
import com.revature.hai_app.models.*;
import com.revature.hai_app.services.*;
import com.revature.hai_app.util.custom_exceptions.InvalidPasswordException;
import jdk.nashorn.internal.runtime.StoredScript;
import sun.util.calendar.LocalGregorianCalendar;

import java.awt.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class MainMenu implements MenuTemplate{
    // Dependency
    private final User user;
    public MainMenu(User user){this.user=user;}
    UserService userService = new UserService(new userDAO());
    ProductService productService = new ProductService(new productDAO());
    StoreService storeService = new StoreService(new storeDAO());
    InventoryService inventoryService = new InventoryService(new inventInstDAO());
    CartService cartService = new CartService(new cartDAO());
    OrderService orderService = new OrderService(new ordersDAO());
    OrderInstanceService orderInstanceService = new OrderInstanceService(new orderInstDAO());
    //Font Service
    FontService font = new FontService();
    //Variables
    int count = 0;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    LocalDateTime now = LocalDateTime.now();
    Orders orders = new Orders(UUID.randomUUID().toString(), dtf.format(now).toString(), 0, 0, "0");
    Cart cart = new Cart(orders.getId(), UUID.randomUUID().toString(), "0", false);


    @Override
    public void start(){
        displayMainMenu();

    }

    private void displayMainMenu(){
        System.out.println(font.yellow("\n+------------------------------------------------------------>\n"));
        System.out.println(font.cyanBold("                   WELCOME TO THE RIFT SHOP!"));
        System.out.println(font.purpleBold("               HOW MAY WE HELP YOU SLAY TODAY?\n"));
        System.out.println(font.whiteBold("  User: ")+font.yellowBold(user.getUsername())+ font.whiteBold("                         Store Credits: ")+ font.yellowBold(""+user.getStorecredits()));
        System.out.println(font.yellow("+------------------------------------------------------------>\n"));
        System.out.println(font.greenBold("            PLEASE SELECT FROM THE FOLLOWING OPTIONS.\n"));
        System.out.println(font.yellow("+------------------------------------------------------------>\n"));
        System.out.println("[1]"+font.whiteBold(" Browse by Rarity"));
        System.out.println("[2]"+font.whiteBold(" View Order History") );
        System.out.println("[3]"+font.whiteBold(" View Account Information"));
        System.out.println("[c]"+font.whiteBold(" Check Out Cart"+font.greenBold(" ("+count+")")));
        System.out.println("[x]"+font.redBold(" Log Out\n"));
        System.out.println(font.yellow("+------------------------------------------------------------>\n"));

        selectMenu:
        {
            Scanner scan = new Scanner(System.in);
            System.out.print(font.green("Enter: "));
            String input = scan.nextLine();
            switch (input) {
                case "1":
                    orders.setUser_id(this.user.getId());
                    orderService.registerOrder(orders);
                    System.out.println("[1] Browsing by Rarity...\n");
                    browseByRarity();
                    break;
                case "2":
                    displayOrderHistory(user);
                    System.out.println("[2] Viewing Order History...\n");
                    break;
                case "3":
                    System.out.println("[3] Viewing Account Information...\n");
                    displayAccountInfo();
                    break;
                case "x":
                    if (!cart.isChecked_out()) deleteCookies();
                    new StartMenu(userService).start();
                    break;
                case "c":
                    System.out.println("[c] Checking out...\n");
                    displayCart(cart);
                    break;
                default:
                    System.out.println("Wrong input.");
                    break selectMenu;
            }
        }
    }
    private void displayAccountInfo(){
        System.out.println(font.yellow("+------------------------------------------------------------+\n"));
        System.out.println(font.greenBold("            Your account information is listed below.\n"));
        System.out.println(font.yellow("+------------------------------------------------------------+\n"));
        System.out.println(font.whiteBold("             Username: ") + user.getUsername());
        System.out.println(font.whiteBold("\n             Email: ") + user.getEmail());
        System.out.println(font.whiteBold("\n             Store Credits: ") + font.yellowBold(user.getStorecredits()+""));
        System.out.println(font.yellow("\n+------------------------------------------------------------+\n"));
        System.out.println("[1]"+font.whiteBold(" Change password"));
        System.out.println("[2]"+font.whiteBold(" Change email"));
        System.out.println("[3]"+font.yellowBold(" Add Store Credits"));
        System.out.println("[4]"+font.red(" Back to Main Menu"));
        System.out.print(font.green("\nEnter: "));

        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        switch(input){
            case "1":
                changePassword(user);
                break;
            case "2":
                changeEmail(user);
                break;
            case "3":
                addStoreCredits(user);
                break;
            case "4":
                displayMainMenu();
                break;
            default:
                System.out.println(font.red("Wrong input."));
        }

    }
    private User addStoreCredits(User us){
        Scanner scan = new Scanner(System.in);
        System.out.println(font.greenBold("How much store credits do you want to add?"));
        System.out.println(font.greenBold("Current credits to dollar ratio: 1000:$1"));
        System.out.print(font.green("\nEnter: "));
        String input = scan.nextLine();
        System.out.println(font.whiteBold("Deducting payment from card..."));

        int totalCreds = us.getStorecredits() + Integer.parseInt(input);
        us.setStorecredits(totalCreds); // Setting storeCredits
        userService.update(us);
        System.out.println(font.greenBold("Purchase successful! Returning to account information.\n"));
        displayAccountInfo();
        return us;
    }
    private User changeEmail(User us){
        Scanner scan = new Scanner(System.in);
        System.out.println(font.whiteBold("Current email: ") + us.getEmail());
        while(true) {
            System.out.print(font.whiteBold("\nEnter new email: "));
            String newEmail = scan.nextLine();
            System.out.print(font.whiteBold("Confirm email: "));
            String confirmEmail = scan.nextLine();
            if(confirmEmail.equals(newEmail)){
                System.out.println(font.whiteBold("Updating email..."));
                us.setEmail(confirmEmail);
                userService.update(us);
                System.out.println(font.greenBold("Email updated successfully!"));
                displayAccountInfo();
                break;
            } else System.out.println(font.redBold("Emails entered does not match!"));
        }
        return us;

    }
    private User changePassword(User us){
        Scanner scan = new Scanner(System.in);

        while(true) {
            System.out.print(font.whiteBold("Enter your current password: "));
            String input = scan.nextLine();
                if (input.equals(us.getPassword())) {
                    System.out.print(font.whiteBold("Enter new password: "));
                    String newPass = scan.nextLine();
                    userService.isValidPassWord(newPass);
                    System.out.println(font.whiteBold("Changing password..."));
                    us.setPassword(newPass);
                    userService.update(us);
                    System.out.println(font.greenBold("Password changed successfully!"));
                    displayAccountInfo();
                    break;
                } else {
                    System.out.println(font.redBold("Invalid Password."));
                }
        }
        return us;
    }
    private void displayItem(List<Product> prods){

        Scanner scan = new Scanner(System.in);
        while(true){
        browse:
        {
            System.out.println(font.yellow("+---------------------------------------------> >>"));
            for (int i = 0; i < prods.size(); i++) {
                System.out.println(font.whiteBold("[" + (i + 1) + "] Name: ") + font.yellowBold(prods.get(i).getName()));
                System.out.println(font.whiteBold("Class Recommendation: ") + font.green(prods.get(i).getClassRec()));
                System.out.println(font.whiteBold("Rarity: ") + font.green(prods.get(i).getRarity()));
                System.out.println(font.whiteBold("Description: ") + font.green(prods.get(i).getDescription()));
                System.out.println(font.whiteBold("Price: ") + font.yellowBold(prods.get(i).getPrice()+""));
                System.out.println(font.whiteBold("Location: ") + storeService.getLocationByStoreID(inventoryService.getStoreIDByItemID(prods.get(i).getId())) + font.whiteBold("  Amount Left: ") + font.green(inventoryService.getProductCountByProdID(prods.get(i).getId())+""));
                System.out.println(font.yellow("\n+---------------------------------------------> >>"));
            }
            System.out.println(font.greenBold("Please select the item to purchase or type in '0' to go back to browse by rarity."));
            System.out.print(font.green("Enter: "));
            String input = scan.nextLine();
            int numInput = Integer.parseInt(input) - 1;
            if (numInput >= 0 && numInput < prods.size()) {
                System.out.println(font.green("Add to cart: ") + font.yellowBold(prods.get(numInput).getName()) + font.whiteBold(" | | Price: ") + font.yellowBold(prods.get(numInput).getPrice()+"") + font.whiteBold(" store credits? (y/n)"));
                System.out.print(font.green("Enter: "));
                String confirmPurchase = scan.nextLine();
                switch (confirmPurchase) {
                    case "y":
                        addToCart(prods.get(numInput));
                        System.out.println(font.greenBold("Added to cart!"));
                        browseByRarity();
                        break;
                    case "n":
                        break browse;
                }
            } else {
                browseByRarity();
                break;
            }
        }
        }
    }
    private void browseByRarity(){
        Scanner scan = new Scanner(System.in);
        System.out.println(font.yellow("+------------------------------------------------------------+"));
        System.out.println(font.greenBold("     Please select what rarity items you would like to see."));
        System.out.println(font.yellow("+------------------------------------------------------------+\n"));
        System.out.println("[1] Basic");
        System.out.println("[2]" +font.greenBold(" Uncommon") );
        System.out.println("[3]" + font.purpleBold(" Legendary") );
        System.out.println("[4]" + font.cyanBold(" Mythic") );
        System.out.println("[x]"+font.red(" Back"));
        System.out.print(font.green("\nEnter: "));
        String input = scan.nextLine();
        switch(input){
            case "1":
                 displayItem(productService.getProductsByRarity("Basic"));
                break;
            case "2":
                 displayItem(productService.getProductsByRarity("Uncommon"));
                break;
            case "3":
                 displayItem(productService.getProductsByRarity("Legendary"));
                break;
            case "4":
                 displayItem(productService.getProductsByRarity("Mythic"));
                break;
            case "x":
                displayMainMenu();
                break;
            default:
                System.out.println(font.red("Invalid input"));
                break;
        }
    }
    private void addToCart(Product product){
        count++;
        int totalPrice = this.cart.getCart_prodprice_total();
        this.cart.setProduct_id(product.getId());
        this.cart.setCart_prodprice_total(totalPrice+=product.getPrice());
        this.cart.setCart_count(count);
        this.cart.setChecked_out(false);
        cartService.registerCart(this.cart);
    }
    private void deleteCookies() {
        orderService.deleteOrder(this.orders.getId());
        cartService.deleteCart(this.cart.getCart_id());
    }
    private void displayCart(Cart cart) {
        Scanner scan = new Scanner(System.in);
        List<String> prodIDs = cartService.getProductIDsByCartID(cart.getCart_id());
        int total_price = 0;
        if (count <= 0) {
            System.out.println(font.green("There are no items in your cart! Press Enter to browse items."));
            scan.nextLine();
            browseByRarity();
        } else {
            System.out.println(font.whiteBold("Current items in your cart: \n"));
            System.out.println(font.yellow("+---------------------------------------------> >>"));
            for (String s : prodIDs) {
                total_price += productService.getProducePriceByID(s);
                System.out.println(font.whiteBold("ITEM: ") + font.yellowBold(productService.getProductNameByID(s)) + font.whiteBold(" PRICE: ") + font.yellowBold(productService.getProducePriceByID(s)+""));
            }
            System.out.println(font.whiteBold("\n Total Price: ") + font.yellowBold(total_price+""));
            System.out.println(font.yellow("+---------------------------------------------> >>"));
            System.out.println(font.whiteBold("Current Store Credits: ") + font.yellowBold(user.getStorecredits()+""));
            System.out.println(font.whiteBold("\n Would you like to check out? (y/n)"));
            System.out.print(font.green("Enter: "));
            String input = scan.nextLine();
            switch (input) {
                case "y":
                    if (user.getStorecredits() < total_price) {
                        System.out.println(font.red("Insufficient funds purchase! Please add store credits under Account Information.  Press enter to go there."));
                        scan.nextLine();
                        displayAccountInfo();
                    } else {
                        //Store cart data
                        cart.setChecked_out(true);
                        cart.setCart_prodprice_total(total_price);
                        cartService.updateCart(cart);
                        //Getting money from users
                        user.setStorecredits(user.getStorecredits() - total_price);
                        userService.update(user); //Updating Store Credits for user;
                        for (String s : prodIDs) {
                            //Updating inventory
                            Inventinstance inventoryInstance = new Inventinstance(
                                    inventoryService.getStoreIDByItemID(s), s, inventoryService.getByProdID(s).getProd_count() - 1
                            );
                            inventoryService.updateInventoryCount(inventoryInstance);
                            // Updating order instance for store
                            Orderinstance orderinstance = new Orderinstance(inventoryService.getStoreIDByItemID(s), orders.getId());
                            orderInstanceService.registerOrderInst(orderinstance);
                        }
                        //Updating orders
                        orders.setPrice_total(total_price);
                        orders.setProduct_qty(count);
                        orderService.updateOrder(orders);
                        //Reset cart count
                        count = 0;
                        //Create new cart and order
                        Orders newOrders = new Orders(UUID.randomUUID().toString(), dtf.format(now).toString(), 0, 0, "0");
                        Cart newCart = new Cart(newOrders.getId(), UUID.randomUUID().toString(), "0", false);
                        this.cart = newCart;
                        this.orders = newOrders;
                        System.out.println(font.greenBold("Purchase successful! Orders are recorded under order history."));
                        System.out.println(font.greenBold("Press enter to go back to main menu."));
                        scan.nextLine();
                        displayMainMenu();
                    }
                case "n":
                    System.out.println(font.green("Going back to main menu."));
                    displayMainMenu();
                    break;
            }
        }
    }
    private void displayOrderHistory(User us){
        Scanner scan = new Scanner(System.in);
        List<Orders> orderHistory = orderService.getOrdersByUserID(us.getId());
        System.out.println(font.yellow("+--------------------------------------------------------------+"));
        System.out.println(font.greenBold("Please select from the following options to sort order history."));
        System.out.println("[1]"+font.whiteBold(" By Date: Oldest to Most Recent"));
        System.out.println("[2]"+font.whiteBold(" By Date: Most Recent to Most Oldest"));
        System.out.println("[3]"+font.whiteBold(" By Price: Lowest Purchase to Highest Purchase"));
        System.out.println("[4]"+font.whiteBold(" By Price: Highest Purchase to Lowest Purchase"));
        System.out.println("[x]"+font.red(" Back To Main Menu"));
        System.out.println(font.yellow("+--------------------------------------------------------------+"));
        System.out.print(font.green("\nEnter: "));
        String inputString = scan.nextLine();

        switch(inputString){
            case "1":
                List<Orders> dateSortedMostRecent = orderHistory.stream().sorted(Comparator.comparing(Orders::getDate)).collect(Collectors.toList());
                orderHistory = dateSortedMostRecent;
                break;
            case "2":
                List<Orders> dateSortedOldest = orderHistory.stream().sorted(Comparator.comparing(Orders::getDate).reversed()).collect(Collectors.toList());
                orderHistory = dateSortedOldest;
                break;
            case "3":
                List<Orders> priceSortedHighest = orderHistory.stream().sorted(Comparator.comparing(Orders::getPrice_total)).collect(Collectors.toList());
                orderHistory = priceSortedHighest;
                break;
            case "4":
                List<Orders> priceSortedLowest = orderHistory.stream().sorted(Comparator.comparing(Orders::getPrice_total).reversed()).collect(Collectors.toList());
                orderHistory = priceSortedLowest;
                break;
            case "x":
                displayMainMenu();
                break;
            default:
                System.out.println(font.red("Invalid input."));
        }

        System.out.println(font.greenBold("Displayed below is your order history: \n"));
        System.out.println(font.yellow("+---------------------------------------------> >>"));
        for(Orders ord:orderHistory){
            System.out.println(font.whiteBold("Order ID: ") + ord.getId());
            System.out.println(font.whiteBold("Order Date: ")+ ord.getDate());
            //Printing items bought in list
            List<String> prodIDsArray = cartService.getProductIDsByOrderID(ord.getId());
            StringBuilder itemsBought = new StringBuilder("Item Bought: ");
            for(String s:prodIDsArray){
                itemsBought.append(productService.getProductNameByID(s));
                itemsBought.append(", ");
            }
            itemsBought.delete(itemsBought.length()-2, itemsBought.length());
            System.out.println(font.whiteBold(itemsBought.toString()));
            System.out.println(font.whiteBold("Price Total: ")+ ord.getPrice_total());
            System.out.println(font.whiteBold("Total Items: ")+ ord.getProduct_qty());
            System.out.println(font.yellow("+---------------------------------------------> >>"));
        }
        System.out.println(font.green("Enter to go back to main menu."));
        scan.nextLine();
        displayMainMenu();
    }

}
