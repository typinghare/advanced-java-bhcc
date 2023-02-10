import java.util.Stack;

public class Slot {
    private Class<? extends Product> productType;

    private final Stack<Product> productStack = new Stack<>();

    private double unitPrice;

    public Slot(Class<? extends Product> productType) {
        this.productType = productType;
    }

    /**
     * Adds products to the slot.
     * @param productStack products to add
     */
    public void addProducts(Stack<Product> productStack) {
        if (productStack.isEmpty()) {
            return;
        }

        if (productStack.peek().getClass() != productType) {
            throw new RuntimeException("What are you doing, replenish person?");
        }

        this.productStack.addAll(productStack);
    }

    /**
     * Sets the type of the product.
     * @param productType the type of the product
     */
    public void setProductType(Class<? extends Product> productType) {
        this.productType = productType;
    }

    /**
     * Returns the unit price of the product.
     * @return the unit price of the product
     */
    public double getUnitPrice() {
        return unitPrice;
    }

    /**
     * Sets the unit price of the product
     * @param unitPrice the unit price of the product
     */
    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    /**
     * Returns a product
     * @return a product; null if no product left
     */
    public Product getProduct() {
        return productStack.isEmpty() ? null : productStack.pop();
    }

    public String getProductName() {
        return productStack.peek().productName;
    }

    /**
     * Prints products in this slot.
     */
    public void print() {
        productStack.forEach(product -> System.out.println("- " + product.productName));
    }
}
