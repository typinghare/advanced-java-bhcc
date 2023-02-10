/**
 * Abstract product class.
 */
public abstract class Product {
    public final String productName;

    /**
     * Creates a product.
     * @param productName the name of the product
     */
    public Product(String productName) {
        this.productName = productName;
    }

    /**
     * User consumes the product.
     */
    public abstract void consume();
}
