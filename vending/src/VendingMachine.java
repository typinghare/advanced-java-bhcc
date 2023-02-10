import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class VendingMachine {
    private final Map<String, Slot> locationSlotMap = new HashMap<>();

    private double totalSales = 0;

    /**
     * Stocks items into this vending machine.
     * @param location     the location of the products to stock into
     * @param productStack the stack of products
     * @param unitPrice    the unit price of the product
     */
    public void stockItems(String location, Stack<Product> productStack, double unitPrice) {
        // get or create the slot
        final Slot slot = locationSlotMap.getOrDefault(location, new Slot(productStack.peek().getClass()));
        locationSlotMap.put(location, slot);

        // add product to the slot
        slot.addProducts(productStack);

        // set unit price
        slot.setUnitPrice(unitPrice);
    }

    /**
     * Prints out all the slots with product details in this vending machine.
     */
    public void printInventory() {
        locationSlotMap.forEach((location, slot) -> {
            System.out.println(location);
            slot.print();
        });
    }

    /**
     * Returns the total sales of this vending machine.
     * @return the total sales of this vending machine
     */
    public double getTotalSales() {
        return totalSales;
    }

    /**
     * Users purchase a product.
     * @param location the location of the product.
     * @param amount   the amount of money the user deposits
     * @return VendingMachineOutput
     */
    public VendingMachineOutput purchase(String location, double amount) {
        final Slot slot = locationSlotMap.get(location);

        // throw an exception if the location does not exist
        if (slot == null) {
            throw new NoSuchLocationException(location);
        }

        final double productUnitPrice = slot.getUnitPrice();
        final double change = amount - productUnitPrice;

        // throw an exception if the money given is not enough
        if (change < 0) {
            throw new NotEnoughMoneyException(slot.getProductName(), amount);
        }

        // retrieve a product; throws an exception if there is no more product in the slot
        final Product product = slot.getProduct();
        if (product == null) {
            throw new NotEnoughProductException(location);
        }

        totalSales += productUnitPrice;

        return new VendingMachineOutput(slot.getProduct(), change);
    }
}
