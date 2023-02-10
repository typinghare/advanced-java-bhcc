package stock_2;

public class Portfolio {
    public static void main(String[] args) {
        Stock stock1 = new Stock("MSFT", 239.39);
        stock1.buyShares(100);
        System.out.println(stock1.getTotalValue());
        stock1.sellShares(50);
        System.out.println(stock1.getTotalValue());
    }
}
