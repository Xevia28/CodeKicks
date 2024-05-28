package command.shoe;

import framework.Command;
import services.ShoeService;

public class DeleteShoesCommand implements Command {
    private ShoeService shoeService;
    private int shoeId;

    public DeleteShoesCommand(ShoeService shoeService, int shoeId) {
        this.shoeService = shoeService;
        this.shoeId = shoeId;
    }

    @Override
    public void execute() {
        boolean result = shoeService.deleteShoe(shoeId);
        if (result) {
            System.out.println("Shoe deleted successfully.");
        } else {
            System.out.println("Failed to delete shoe.");
        }
    }
}
