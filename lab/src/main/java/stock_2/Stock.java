package stock_2;

public class Stock {
    private double currentPrice;

    private int numSharesOwned;

    public Stock(String tickerSymbol, double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public void buyShares(int numSharesToBuy) {
        numSharesOwned += numSharesToBuy;
    }

    public void sellShares(int numSharesToSell) {
        if (numSharesOwned > numSharesToSell) {
            numSharesOwned -= numSharesToSell;
        } else {
            System.out.println("Not enough num shares to sell.");
        }
    }

    public double getTotalValue() {
        return currentPrice * numSharesOwned;
    }
}
