// import java.sql.Connection;

// import database.*;

import java.util.Scanner;

import command.Command;
import command.user.LoginCommand;
import command.user.SignUpCommand;
import command.user.UserCommandInvoker;
import entities.User;
import services.UserService;

public class App {
    public static void main(String[] args) throws Exception {
        // MySQL db = MySQL.getInstance();
        // Connection conn = db.getConnection();
        Scanner input = new Scanner(System.in);
        UserService userService = new UserService();
        boolean in = true;

        while (in) {
            System.out.println();
            System.out.println("Press 1 to signup");
            System.out.println("Press 2 to login");
            System.out.println("Press 3 to exit");

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
                if (loginCommand.isLoginSuccessful()) {
                    System.out.println();
                    System.out.println("Welcome to CodeKicks!");
                    boolean loggedIn = true;
                    while (loggedIn) {
                        System.out.println();
                        System.out.println("Press 1 to view products");
                        System.out.println("Press 2 to view cart");
                        System.out.println("Press 3 to logout");
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
        input.close();
    }
}
