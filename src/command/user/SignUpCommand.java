package command.user;

import command.Command;
import entities.User;
import services.UserService;

public class SignUpCommand implements Command {
    private UserService userService;
    private User user;

    public SignUpCommand(UserService userService, User user) {
        this.userService = userService;
        this.user = user;
    }

    @Override
    public void execute() {
        if (userService.signUp(user)) {
            System.out.println("User signed up successfully.");
        } else {
            System.out.println("Signup failed.");
        }
    }
}