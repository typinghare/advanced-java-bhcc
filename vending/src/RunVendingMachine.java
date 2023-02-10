import java.util.Stack;

public class RunVendingMachine {

    // Illustrates the Vending Machine.
    public static void main(String[] args) {
        // Create a new Empty Vending Machine.
        VendingMachine machine = new VendingMachine();

        // Stock up of Diet Cokes
        Stack<Product> dietCokeList = new Stack<>();
        for (int i = 0; i < 5; i++) {
            Drink dietCoke = new Drink("Diet Coke");
            dietCokeList.push(dietCoke);
        }
        machine.stockItems("A1", dietCokeList, 1.25);

        // Stock up on Cliff Bars
        Stack<Product> cliffBarList = new Stack<>();
        for (int i = 0; i < 3; i++) {
            Snack cliffBar = new Snack("Cliff Bar");
            cliffBarList.add(cliffBar);
        }
        machine.stockItems("A2", cliffBarList, 4.00);

        //  What do we have now?
        machine.printInventory();

        //  Try to purchase and drink a diet coke.
        VendingMachineOutput output = machine.purchase("A1", 2.00);
        System.out.println("Got: " + output.product.productName);
        System.out.println("Received change:  " + output.change);
        output.product.consume();
        System.out.println("Total Sales:  " + machine.getTotalSales());

        //  Try to purchase and eat a cliff bar
        output = machine.purchase("A2", 5.00);
        System.out.println("Got: " + output.product.productName);
        System.out.println("Received change:  " + output.change);
        output.product.consume();
        System.out.println("Total Sales:  " + machine.getTotalSales());
    }
}
