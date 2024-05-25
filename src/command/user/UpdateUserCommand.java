package command.user;

import command.Command;
import entities.User;

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
