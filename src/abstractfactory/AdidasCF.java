package abstractfactory;

public class AdidasCF implements ShoeComponentFactory {
    @Override
    public String createSize() {
        return "9";
    }

    @Override
    public String createColor() {
        return "Blue";
    }

    @Override
    public String createMaterial() {
        return "Synthetic";
    }
}
