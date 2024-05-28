package command.user;

import entities.User;
import framework.Command;

public class UpdateUserCommand implements Command {
    private User user;
    private String newEmail;

    public UpdateUserCommand(User user, String newEmail) {
        this.user = user;
        this.newEmail = newEmail;
    }

    @Override
    public void execute() {
        user.setEmail(newEmail);
        System.out.println("User email updated.");
    }
}
