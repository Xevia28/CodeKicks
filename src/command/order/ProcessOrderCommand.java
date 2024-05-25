package command.order;

import command.Command;
import entities.Order;

public class ProcessOrderCommand implements Command {
    private Order order;

    public ProcessOrderCommand(Order order) {
        this.order = order;
    }

    @Override
    public void execute() {
        order.process();
        System.out.println("Order processed.");
    }
}
