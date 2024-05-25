package command.shoe;

import java.util.List;
import command.Command;

import entities.Shoe;
import services.ShoeService;

public class ViewShoesCommand implements Command {
    private ShoeService shoeService;

    public ViewShoesCommand(ShoeService shoeService) {
        this.shoeService = shoeService;
    }

    @Override
    public void execute() {
        List<Shoe> shoes = shoeService.getShoes();
        if (shoes.isEmpty()) {
            System.out.println("No shoes available.");
        } else {
            System.out.println("Available shoes:");
            for (Shoe shoe : shoes) {
                System.out.println("    Shoe ID " + shoe.getId() + " details:");
                System.out.println("        Type: " + shoe.getType());
                System.out.println("        Brand: " + shoe.getBrand());
                System.out.println("        Style: " + shoe.getStyle());
                System.out.println("        Size: " + shoe.getSize());
                System.out.println("        Price: Nu." + shoe.getPrice());
            }
        }
    }
}
