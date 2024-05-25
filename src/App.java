// import java.sql.Connection;

// import database.*;

import java.util.Scanner;

import command.Command;
import command.user.LoginCommand;
import command.user.SignUpCommand;
import command.user.UserCommandInvoker;
import entities.User;
import services.UserService;
// import services.NotificationService;

public class App {
    public static void main(String[] args) throws Exception {
        // MySQL db = MySQL.getInstance();
        // Connection conn = db.getConnection();
        Scanner input = new Scanner(System.in);
        UserService userService = new UserService();
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
                    while (loggedIn) {
                        System.out.println();
                        System.out.println("Select an action:");
                        System.out.println("1. View Products");
                        System.out.println("2. Add Product to Cart");
                        System.out.println("3. View Cart");
                        System.out.println("4. Logout");
                        System.out.print("Option: ");
                        int userSelect = input.nextInt();

                        switch (userSelect) {
                            case 1:
                                // Code to view products
                                System.out.println("Displaying products...");
                                break;
                            case 2:
                                // Code to view cart
                                System.out.println("Displaying cart...");
                                break;
                            case 3:
                                // Code to view cart
                                System.out.println("Adding to cart...");
                                break;
                            case 4:
                                // Logout
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
                        System.out.println("1. View Products");
                        System.out.println("2. Add Product");
                        System.out.println("3. Edit Product");
                        System.out.println("4. Logout");
                        System.out.print("Option: ");
                        int userSelect = input.nextInt();

                        switch (userSelect) {
                            case 1:
                                // Code to view products
                                System.out.println("Displaying products...");
                                break;
                            case 2:
                                // Code to add products
                                System.out.println("Adding...");
                                break;
                            case 3:
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
