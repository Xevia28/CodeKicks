package command.user;

import command.Command;
import entities.User;
import services.UserService;

public class LoginCommand implements Command {
    private UserService userService;
    private User user;
    private boolean loginSuccessful;

    public LoginCommand(UserService userService, User user) {
        this.userService = userService;
        this.user = user;
    }

    @Override
    public void execute() {
        loginSuccessful = userService.login(user);
        if (loginSuccessful) {
            System.out.println("Login successful.");
        } else {
            System.out.println("Login failed.");
        }
    }

    public boolean isLoginSuccessful() {
        return loginSuccessful;
    }
}