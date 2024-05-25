// import java.sql.Connection;

// import database.*;

import java.util.Scanner;

import command.Command;
import command.cart.AddToCartCommand;
import command.cart.ViewCartCommand;
import command.shoe.DeleteShoesCommand;
import command.shoe.ViewShoesCommand;
import command.user.LoginCommand;
import command.user.SignUpCommand;
import command.user.UserCommandInvoker;
import entities.Cart;
import entities.User;
import services.CartService;
import services.ShoeService;
import services.UserService;
// import services.NotificationService;

public class App {
    public static void main(String[] args) throws Exception {
        // MySQL db = MySQL.getInstance();
        // Connection conn = db.getConnection();
        Scanner input = new Scanner(System.in);
        UserService userService = new UserService();
        ShoeService shoeService = new ShoeService();
        CartService cartService = new CartService();
        boolean in = true;

        while (in) {
            System.out.println();
            System.out.println("Select an action:");
            System.out.println("1. Create a user account");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            System.out.print("Option: ");
            int select = input.nextInt();

            if (select == 1) {
                input.nextLine();
                System.out.print("Enter your name: ");
                String name = input.nextLine();
                System.out.print("Enter your email: ");
                String email = input.next();
                System.out.print("Enter your password: ");
                String password = input.next();
                User newUser = new User(name, email, password);
                Command signUpCommand = new SignUpCommand(userService, newUser);
                UserCommandInvoker invoker = new UserCommandInvoker();
                invoker.setCommand(signUpCommand);
                invoker.executeCommand();

            } else if (select == 2) {
                System.out.print("Enter your email: ");
                String email = input.next();
                System.out.print("Enter your password: ");
                String password = input.next();
                User newUser = new User("name", email, password);
                LoginCommand loginCommand = new LoginCommand(userService, newUser);
                UserCommandInvoker invoker = new UserCommandInvoker();
                invoker.setCommand(loginCommand);
                invoker.executeCommand();
                if (loginCommand.isLoginSuccessful() == "user") {
                    System.out.println();
                    System.out.println("Welcome to CodeKicks!");
                    boolean loggedIn = true;
                    User currUser = userService.getUserByEmail(newUser.getEmail());
                    Cart userCart = new Cart(currUser.getId());
                    while (loggedIn) {
                        System.out.println();
                        System.out.println("Select an action:");
                        System.out.println("1. View Shoes");
                        System.out.println("2. Add Shoe to Cart");
                        System.out.println("3. View Cart");
                        System.out.println("4. Checkout");
                        System.out.println("5. Logout");
                        System.out.print("Option: ");
                        int userSelect = input.nextInt();

                        switch (userSelect) {
                            case 1:
                                Command viewShoesCommand = new ViewShoesCommand(shoeService);
                                invoker.setCommand(viewShoesCommand);
                                invoker.executeCommand();
                                break;
                            case 2:
                                System.out.print("Enter the ID of the shoe you want to add to cart: ");
                                int shoeId = input.nextInt();
                                Command addToCartCommand = new AddToCartCommand(cartService, userCart, shoeId);
                                invoker.setCommand(addToCartCommand);
                                invoker.executeCommand();
                                break;
                            case 3:
                                Command viewCartCommand = new ViewCartCommand(cartService, userCart);
                                invoker.setCommand(viewCartCommand);
                                invoker.executeCommand();
                                break;
                            case 4:
                                System.out.println("Checkout...");
                                break;
                            case 5:
                                System.out.println("Logging out...");
                                loggedIn = false;
                                break;
                            default:
                                System.out.println("Invalid input! Please try again.");
                        }
                    }
                } else if (loginCommand.isLoginSuccessful() == "admin") {
                    System.out.println();
                    System.out.println("Welcome to Admin Dashboard!");
                    boolean loggedIn = true;
                    while (loggedIn) {
                        System.out.println();
                        System.out.println("Select an action:");
                        System.out.println("1. View Shoes");
                        System.out.println("2. Add Shoe");
                        System.out.println("3. Edit Shoe");
                        System.out.println("4. Remove Shoe");
                        System.out.println("5. Logout");
                        System.out.print("Option: ");
                        int userSelect = input.nextInt();

                        switch (userSelect) {
                            case 1:
                                Command viewShoesCommand = new ViewShoesCommand(shoeService);
                                invoker.setCommand(viewShoesCommand);
                                invoker.executeCommand();
                                break;
                            case 2:
                                // Code to add products
                                System.out.println("Adding...");
                                break;
                            case 3:
                                // Logout
                                System.out.println("Logging out...");
                                break;
                            case 4:
                                System.out.print("Enter the ID of the product to remove: ");
                                int shoeId = input.nextInt();
                                Command deleteProductCommand = new DeleteShoesCommand(shoeService, shoeId);
                                invoker.setCommand(deleteProductCommand);
                                invoker.executeCommand();
                                break;
                            case 5:
                                // Logout
                                System.out.println("Logging out...");
                                loggedIn = false;
                                break;
                            default:
                                System.out.println("Invalid input! Please try again.");
                        }
                    }
                }
            } else if (select == 3) {
                System.out.println("Bye");
                break;
            } else {
                System.out.println("Invalid input! Please try again.");
            }
        }
        // NotificationService notificationService = new NotificationService();
        // // UserService userService = new UserService(notificationService);

        // User user1 = new User("Alice", "alice@example.com");
        // User user2 = new User("Bob", "bob@example.com");

        // userService.signUp(user1);
        // userService.signUp(user2);

        // notificationService.orderStatusUpdate("Shipped");
        // notificationService.newPromotion("20% off all items!");

        // userService.login(user1);

        // notificationService.productAvailabilityChange("Laptop", true);
        input.close();
    }
}
