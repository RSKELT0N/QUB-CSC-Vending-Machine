package Part2;

import java.io.IOException;

/**Importing the program method */
import static Part2.VendingMachineMenu.program;

/**
 * Author: Ryan Skelton, 40266093
 *
 * This class is used to run program(Vending Machine) as I am adding a main method to run the 'program()'
 * method from the 'VendingMachineMenu.java' class. */
public class Main {

    /**Declaring the max Item size */
    private static int MAX_ITEMS = 10;

    /**Declaring the vending machine Object */
    static VendingMachine vendingMachine;

    /**Creating a static block inside the class to initialise the 'vendingMachine' object, with the name and the maxItem size
     * into the parameters. */
    static {
        try {
            vendingMachine = new VendingMachine("Jimmy", MAX_ITEMS);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**Running the 'program()' method
     *
     * @throws IOException - This exception is used as to catch errors for reading file
     * @throws InterruptedException - This exception is used to control the 'sleep' method.
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        program();
    }
}
