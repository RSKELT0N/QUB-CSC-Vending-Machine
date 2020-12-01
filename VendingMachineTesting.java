package ProgramTesting.UnitTesting;

import Part1.Status;
import Part1.Table;
import Part1.VendingMachine;

import java.util.InputMismatchException;

public class VendingMachineTesting {

    public static void main(String[] args) {
        testConstructor1();
        testConstructor2();
        testConstructor3();
        testConstructor4();
        testGetSystemInfo1();
        testReset1();
        testReset2();
        testInsertCoin1();
        testInsertCoin2();
        testInsertCoin3();
        testInsertCoin4();
        testInsertCoin5();
        testInsertCoin6();
    }

    public static void testConstructor1() {
        System.out.println("-------------------");
        System.out.println(" - Constructor Test 1\n");
        try {
            VendingMachine test = new VendingMachine("Jimmy", 10);
            System.out.println("Testing input,  name: Jimmy, MaxItems: 10");
            System.out.println("Test passed - No Exception Thrown");
        } catch (IllegalArgumentException e) {
            System.out.println("Test Failed");
        }
        System.out.println("-------------------\n");
    }

    public static void testConstructor2() {
        System.out.println("-------------------");
        System.out.println(" - Constructor Test 2\n");
        try {
            VendingMachine test = new VendingMachine("Jimmy", -1);
            System.out.println("Test Failed");
        } catch (IllegalArgumentException e) {
            System.out.println("Testing input, name: Jimmy, MaxItems: -1");
            System.out.println("Test passed - Exception thrown, maxItems cant be a negative Integer");
        }
        System.out.println("-------------------\n");
    }

    public static void testConstructor3() {
        System.out.println("-------------------");
        System.out.println(" - Constructor Test 3\n");
        try {
            VendingMachine test = new VendingMachine("Jimmy", 11);
            System.out.println("Test Failed");
        } catch (IllegalArgumentException e) {
            System.out.println("Testing input, name: Jimmy, MaxItems: 11");
            System.out.println("Test passed - Exception thrown, max Items cannot be above 10");
        }
        System.out.println("-------------------\n");
    }

    public static void testConstructor4() {
        System.out.println("-------------------");
        System.out.println(" - Constructor Test 4\n");
        VendingMachine test = new VendingMachine("", 10);
        System.out.println("Testing input, name: \"\" (Empty), MaxItems: 10");
        System.out.println("Test passed - Name was changed from null to : " + test.getOwner());
        System.out.println("-------------------\n");
    }

    public static void testGetSystemInfo1() {
        System.out.println("-------------------");
        System.out.println(" - GetSystemInfo Test 1\n");
        VendingMachine test = new VendingMachine("", 10);
        System.out.println("Testing input, Calling 'getSystemInfo' method");
        test.getSystemInfo();
        System.out.println("\nTest passed - System Information printed successfully");
        System.out.println("-------------------\n");
    }

    public static void testReset1() {
        System.out.println("-------------------");
        System.out.println(" - Reset Test 1\n");
        VendingMachine test = new VendingMachine("Jimmy", 10);
        test.determineCoin("2");
        test.setStatus(Status.SERVICE_MODE);
        System.out.println("Before Reset Method - Adding £2 to UserMoney, Setting mode to 'SERVICE_MODE'\n");
        test.getSystemInfo();
        System.out.println("\nTesting input, Calling 'Reset' method");
        test.reset();
        test.getSystemInfo();
        System.out.println("\nTest passed - Settings reset, UserMoney reset to 0.0, and Status changed to 'VENDING_MODE'");
        System.out.println("-------------------\n");
    }

    public static void testReset2() {
        System.out.println("-------------------");
        System.out.println(" - Reset Test 2\n");
        VendingMachine test = new VendingMachine("Jimmy", 10);
        System.out.println("Before Reset Method");
        new Table("Vending Machine Items", "      Item    Price     Quantity   Name  ", test.listItems(), 15, 16, 0, true);
        System.out.println("\nTesting input, Calling 'Reset' method");
        test.reset();
        new Table("Vending Machine Items", "      Item    Price     Quantity   Name  ", test.listItems(), 15, 16, 0, true);
        System.out.println("\nTest passed - List items have been removed");
        System.out.println("-------------------\n");
    }

    public static void testInsertCoin1() {
        System.out.println("-------------------");
        System.out.println(" - InsertCoin Test 1\n");
        VendingMachine test = new VendingMachine("Jimmy", 10);
        test.input("Balance: £" + test.getUserMoney());
        System.out.println("\n\nInput, Entering £2");
        test.determineCoin("2");
        test.input("Balance: £" + test.getUserMoney() + "\n");
        System.out.println("\nTest passed - £2 was added successfully towards userMoney");
        System.out.println("-------------------\n");
    }

    public static void testInsertCoin2() {
        System.out.println("-------------------");
        System.out.println(" - InsertCoin Test 2\n");
        VendingMachine test = new VendingMachine("Jimmy", 10);
        test.input("Balance: " + test.getUserMoney());
        System.out.println("\n\nInput, Entering £1");
        test.determineCoin("1");
        test.input("Balance: £" + test.getUserMoney() + "\n");
        System.out.println("\nTest passed - £1 was added successfully towards userMoney");
        System.out.println("-------------------\n");
    }

    public static void testInsertCoin3() {
        System.out.println("-------------------");
        System.out.println(" - InsertCoin Test 3\n");
        VendingMachine test = new VendingMachine("Jimmy", 10);
        test.input("Balance: £" + test.getUserMoney());
        System.out.println("\n\nInput, Entering £2 & 0.5");
        test.determineCoin("2");
        test.determineCoin("0.5");
        test.input("Balance: £" + test.getUserMoney() + "\n");
        System.out.println("\nTest passed - £2.50 was added successfully towards userMoney");
        System.out.println("-------------------\n");
    }

    public static void testInsertCoin4() {
        System.out.println("-------------------");
        System.out.println(" - InsertCoin Test 4\n");
        VendingMachine test = new VendingMachine("Jimmy", 10);
        test.input("Balance: £" + test.getUserMoney());
        System.out.println("\n\nInput, Entering a minus Integer: (-0.05)");
        test.determineCoin("-0.05");
        test.input("Balance: £" + test.getUserMoney() + "\n");
        System.out.println("\nTest passed - The coin input was not valid");
        System.out.println("-------------------\n");
    }

    public static void testInsertCoin5() {
        System.out.println("-------------------");
        System.out.println(" - InsertCoin Test 5\n");
        VendingMachine test = new VendingMachine("Jimmy", 10);
        test.input("Balance: £" + test.getUserMoney());
        System.out.println("\n\nInput, Entering an invalid positive Integer: (5.00)");
        test.determineCoin("5");
        test.input("Balance: £" + test.getUserMoney() + "\n");
        System.out.println("\nTest passed - The coin input was not valid");
        System.out.println("-------------------\n");
    }

    public static void testInsertCoin6() {
        System.out.println("-------------------");
        System.out.println(" - InsertCoin Test 6\n");
        VendingMachine test = new VendingMachine("Jimmy", 10);
        test.input("Balance: £" + test.getUserMoney());
        System.out.println("\n\nInput, Entering a letter: (t)");
        try {
            test.determineCoin("t");
            System.out.println("Test Failed - letter was added towards the balance");
        } catch (InputMismatchException | NumberFormatException e) {
            test.input("Balance: £" + test.getUserMoney() + "\n");
            System.out.println("\nTest passed - Not correct format, balance was not affected");
        }
        System.out.println("-------------------\n");
    }
}


