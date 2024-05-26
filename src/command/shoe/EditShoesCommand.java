package command.shoe;

import command.Command;
import entities.Shoe;
import services.ShoeService;

public class EditShoesCommand implements Command {
    private ShoeService shoeService;
    private Shoe shoe;

    public EditShoesCommand(ShoeService shoeService, Shoe shoe) {
        this.shoeService = shoeService;
        this.shoe = shoe;
    }

    @Override
    public void execute() {
        boolean success = shoeService.updateShoe(shoe);
        if (success) {
            System.out.println("Shoe updated successfully!");
        } else {
            System.out.println("Failed to update shoe.");
        }
    }
}
