package command.user;

import entities.User;
import framework.Command;
import services.UserService;

public class LoginCommand implements Command {
    private UserService userService;
    private User user;
    private String loginSuccessful;

    public LoginCommand(UserService userService, User user) {
        this.userService = userService;
        this.user = user;
    }

    @Override
    public void execute() {
        loginSuccessful = userService.login(user);
        if (loginSuccessful != "fail") {
            System.out.println("Login successful.");
        } else {
            System.out.println("Login failed.");
        }
    }

    public String isLoginSuccessful() {
        return loginSuccessful;
    }
}