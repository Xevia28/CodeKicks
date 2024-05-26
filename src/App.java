import java.util.List;
import java.util.Scanner;

import command.Command;
import command.cart.AddToCartCommand;
import command.cart.CheckoutCommand;
import command.cart.RemoveFromCartCommand;
import command.cart.ViewCartCommand;
import command.order.UpdateOrderCommand;
import command.shoe.DeleteShoesCommand;
import command.shoe.EditShoesCommand;
import command.shoe.ViewShoesCommand;
import command.user.LoginCommand;
import command.user.SignUpCommand;
import command.user.UserCommandInvoker;
import entities.Cart;
import entities.Order;
import entities.Shoe;
import entities.User;
import factory.ShoeFactory;
import factory.shoefactories.AESF;
import factory.shoefactories.AdidasSF;
import factory.shoefactories.BirkenstockSF;
import factory.shoefactories.ConverseSF;
import factory.shoefactories.JMSF;
import factory.shoefactories.MerrellSF;
import factory.shoefactories.NikeSF;
import factory.shoefactories.TimberlandSF;
import factory.shoefactories.UGGSF;
import services.CartService;
import services.OrderService;
import services.ShoeService;
import services.UserService;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        UserService userService = new UserService();
        ShoeService shoeService = new ShoeService();
        CartService cartService = new CartService();
        OrderService orderService = new OrderService();
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
                        System.out.println("4. Remove Shoe from Cart");
                        System.out.println("5. Checkout");
                        System.out.println("6. View my orders");
                        System.out.println("7. Logout");
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
                                System.out.print("Enter the ID of the shoe you want to remove from cart: ");
                                int rmId = input.nextInt();
                                Command removeFromCartCommand = new RemoveFromCartCommand(cartService, userCart,
                                        rmId);
                                invoker.setCommand(removeFromCartCommand);
                                invoker.executeCommand();
                                break;
                            case 5:
                                input.nextLine();
                                System.out.print("Enter your address: ");
                                String address = input.nextLine();

                                Command checkoutCommand = new CheckoutCommand(userCart, address, orderService,
                                        cartService);
                                invoker.setCommand(checkoutCommand);
                                invoker.executeCommand();
                                userCart = new Cart(currUser.getId());
                                break;
                            case 6:
                                List<Order> orders = orderService.getOrders(currUser.getId());
                                if (orders != null) {
                                    for (Order order : orders) {
                                        System.out.println();
                                        order.printOrderDetails();
                                    }
                                } else {
                                    System.out.println("No orders found.");
                                }
                                break;
                            case 7:
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
                        System.out.println("5. Update Order Status");
                        System.out.println("6. Logout");
                        System.out.print("Option: ");
                        int userSelect = input.nextInt();

                        switch (userSelect) {
                            case 1:
                                Command viewShoesCommand = new ViewShoesCommand(shoeService);
                                invoker.setCommand(viewShoesCommand);
                                invoker.executeCommand();
                                break;
                            case 2:
                                input.nextLine();
                                System.out.print("Enter shoe type: ");
                                String type = input.nextLine();
                                System.out.print("Enter shoe style: ");
                                String style = input.nextLine();
                                System.out.print("Enter shoe size: ");
                                String size = input.nextLine();
                                System.out.print("Enter shoe price: ");
                                double price = input.nextDouble();
                                System.out.println("Enter shoe brand: ");
                                System.out.println("1.  Adidas");
                                System.out.println("2.  Nike");
                                System.out.println("3.  Johnston & Murphy");
                                System.out.println("4.  Birkenstock");
                                System.out.println("5.  Timberland");
                                System.out.println("6.  Merrell");
                                System.out.println("7.  Converse");
                                System.out.println("8.  Allen Edmonds");
                                System.out.println("9.  UGG");
                                int brand = input.nextInt();
                                ShoeFactory factory = null;
                                switch (brand) {
                                    case 1:
                                        factory = new AdidasSF();
                                        break;
                                    case 2:
                                        factory = new NikeSF();
                                        break;
                                    case 3:
                                        factory = new JMSF();
                                        break;
                                    case 4:
                                        factory = new BirkenstockSF();
                                        break;
                                    case 5:
                                        factory = new TimberlandSF();
                                        break;
                                    case 6:
                                        factory = new MerrellSF();
                                        break;
                                    case 7:
                                        factory = new ConverseSF();
                                        break;
                                    case 8:
                                        factory = new AESF();
                                        break;
                                    case 9:
                                        factory = new UGGSF();
                                        break;
                                    default:
                                        System.out.println("Invalid brand! Please try again.");
                                        break;
                                }
                                boolean success = shoeService.createShoes(factory, type, style, size, price);
                                if (success) {
                                    System.out.println("Shoe added successfully!");
                                } else {
                                    System.out.println("Failed to add shoe.");
                                }
                                break;
                            case 3:
                                input.nextLine();
                                System.out.print("Enter shoe ID to edit: ");
                                int shoeId = input.nextInt();
                                input.nextLine();
                                System.out.print("Enter new shoe type: ");
                                String newType = input.nextLine();
                                System.out.print("Enter new shoe style: ");
                                String newStyle = input.nextLine();
                                System.out.print("Enter new shoe size: ");
                                String newSize = input.nextLine();
                                System.out.print("Enter new shoe price: ");
                                double newPrice = input.nextDouble();
                                input.nextLine();
                                System.out.println("Enter new shoe brand: ");
                                System.out.println("1.  Adidas");
                                System.out.println("2.  Nike");
                                System.out.println("3.  Johnston & Murphy");
                                System.out.println("4.  Birkenstock");
                                System.out.println("5.  Timberland");
                                System.out.println("6.  Merrell");
                                System.out.println("7.  Converse");
                                System.out.println("8.  Allen Edmonds");
                                System.out.println("9.  UGG");
                                int choice = input.nextInt();
                                String newBrand = "";
                                switch (choice) {
                                    case 1:
                                        newBrand = "Adidas";
                                        break;
                                    case 2:
                                        newBrand = "Nike";
                                        break;
                                    case 3:
                                        newBrand = "Johnston & Murphy";
                                        break;
                                    case 4:
                                        newBrand = "Birkenstock";
                                        break;
                                    case 5:
                                        newBrand = "Timberland";
                                        break;
                                    case 6:
                                        newBrand = "Merrell";
                                        break;
                                    case 7:
                                        newBrand = "Converse";
                                        break;
                                    case 8:
                                        newBrand = "Allen Edmonds";
                                        break;
                                    case 9:
                                        newBrand = "UGG";
                                        break;
                                    default:
                                        System.out.println("Invalid brand! Please try again.");
                                        break;
                                }
                                if (newBrand == "") {
                                    break;
                                }

                                Shoe updatedShoe = new Shoe(shoeId, newType, newBrand, newStyle, newSize, newPrice);
                                Command editShoeCommand = new EditShoesCommand(shoeService, updatedShoe);
                                invoker.setCommand(editShoeCommand);
                                invoker.executeCommand();
                                break;
                            case 4:
                                System.out.print("Enter the ID of the product to remove: ");
                                int shoe_id = input.nextInt();
                                Command deleteProductCommand = new DeleteShoesCommand(shoeService, shoe_id);
                                invoker.setCommand(deleteProductCommand);
                                invoker.executeCommand();
                                break;
                            case 5:
                                System.out.print("Enter order ID to update status: ");
                                int orderId = input.nextInt();
                                System.out.println("Enter new status: ");
                                System.out.println("1. Shipped");
                                System.out.println("2. Delivered");
                                int option = input.nextInt();
                                String newStatus = "";
                                switch (option) {
                                    case 1:
                                        newStatus = "Shipped";
                                        break;
                                    case 2:
                                        newStatus = "Delivered";
                                        break;
                                    default:
                                        System.out.println("Invalid option. Please try again.");
                                        break;
                                }
                                if (newStatus == "") {
                                    break;
                                }
                                Command updateOrderStatusCommand = new UpdateOrderCommand(orderService, orderId,
                                        newStatus);
                                invoker.setCommand(updateOrderStatusCommand);
                                invoker.executeCommand();
                                break;
                            case 6:
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