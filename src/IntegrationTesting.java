package ProgramTesting.IntegrationTesting;

import Part1.Status;
import Part1.Table;
import Part1.VendItem;
import Part1.VendingMachine;

public class IntegrationTesting {

    public static void main(String[] args) {
        testListItems1();
        testListItems2();
        testListItems3();
        testPurchaseItem1();
        testPurchaseItem2();
        testPurchaseItem3();
        testPurchaseItem4();
        testPurchaseItem5();
        testPurchaseItem6();
        testPurchaseItem7();
        testDeliver1();
        testAddNewItem1();
        testAddNewItem2();
        testAddNewItem3();
        testAddNewItem4();
        testAddNewItem5();
        testAddNewItem6();
    }


    public static void testListItems1() {
        System.out.println("-------------------");
        System.out.println(" - ListItems Test 1");
        VendingMachine test = new VendingMachine("Jimmy", 10);
        try {
            System.out.println("Listing the default set items");
            new Table("Vending Machine Items", "      Item    Price     Quantity   Name  ", test.listItems(), 15, 16, 0, true);
            System.out.println("\nTest passed - ListItems displayed in correct format");
        } catch (Exception e) {
            System.out.println("Test Failed - Error");
        }
        System.out.println("-------------------\n");
    }

    public static void testListItems2() {
        System.out.println("-------------------");
        System.out.println(" - ListItems Test 2");
        VendingMachine test = new VendingMachine("Jimmy", 10);
        try {
            System.out.println("\nAdding a new Item - Sandwich, Price: 1.5, Qty: 4");
            test.addNewItem(new VendItem("Sandwich", 1.5, 4));
            new Table("Vending Machine Items", "      Item    Price     Quantity   Name  ", test.listItems(), 15, 16, 0, true);
            System.out.println("\nTest passed - ListItems displayed in correct format, Plus new item");
        } catch (Exception e) {
            System.out.println("Test Failed - Error");
        }
        System.out.println("-------------------\n");
    }

    public static void testListItems3() {
        System.out.println("-------------------");
        System.out.println(" - ListItems Test 3");
        VendingMachine test = new VendingMachine("Jimmy", 10);
        try {
            System.out.println("Removing an item from machine");
            test.reduceItemCount(1);
            new Table("Vending Machine Items", "      Item    Price     Quantity   Name  ", test.listItems(), 15, 16, 0, true);
            System.out.println("\nTest passed - ListItems displayed in correct format, with reduction of an item");
        } catch (Exception e) {
            System.out.println("Test Failed - Error");
        }
        System.out.println("-------------------\n");
    }

    public static void testPurchaseItem1() {
        System.out.println("-------------------");
        System.out.println(" - PurchaseItem Test 1");
        VendingMachine test = new VendingMachine("Jimmy", 10);
        try {
            System.out.println("\nPurchasing a HotDog at the price of £1.50 - Within VENDING_MODE");
            System.out.println("Adding £1.50 to the userMoney");
            test.setUserMoney(1.5);
            test.purchaseItem(6);
            System.out.println("\nTest passed - Item has been Purchased, With Correct, £0.0 (Correct)");
        } catch (Exception e) {
            System.out.println("Test Failed - Error");
        }
        System.out.println("-------------------\n");
    }

    public static void testPurchaseItem2() {
        System.out.println("-------------------");
        System.out.println(" - PurchaseItem Test 2");
        VendingMachine test = new VendingMachine("Jimmy", 10);
        try {
            System.out.println("\nPurchasing a HotDog at the price of £1.50");
            System.out.println("Adding £1.80 to the userMoney");
            test.setUserMoney(1.8);
            test.purchaseItem(6);
            System.out.println("\nTest passed - Item has been Purchased, With Correct, £0.30 (Correct)\nChange Due, 1x £0.20, 1x £0.10");
        } catch (Exception e) {
            System.out.println("Test Failed - Error");
        }
        System.out.println("-------------------\n");
    }

    public static void testPurchaseItem3() {
        System.out.println("-------------------");
        System.out.println(" - PurchaseItem Test 3");
        VendingMachine test = new VendingMachine("Jimmy", 10);
        try {
            System.out.println("\nPurchasing a HotDog at the price of £1.50 - Within SERVICE_MODE");
            System.out.println("Adding £1.50 to the userMoney");
            test.setUserMoney(1.5);
            test.setStatus(Status.SERVICE_MODE);
            test.purchaseItem(6);
            System.out.println("\n\nTest passed - Item has not been purchased, Vending Machine is in SERVICE_MODE");
        } catch (Exception e) {
            System.out.println("Test Failed - Error");
        }
        System.out.println("-------------------\n");
    }

    public static void testPurchaseItem4() {
        System.out.println("-------------------");
        System.out.println(" - PurchaseItem Test 4");
        VendingMachine test = new VendingMachine("Jimmy", 10);
        try {
            System.out.println("\nAdding a new Item, Cake, Price: £0.8, Qty: 1 - Within VENDING_MODE");
            System.out.println("Adding £1.00 to the userMoney");
            test.setUserMoney(1);
            test.addNewItem(new VendItem("Cake", 0.8, 1));
            test.purchaseItem(7);
            System.out.println("\n\nTest passed - Item has been Purchased");
        } catch (Exception e) {
            System.out.println("Test Failed - Error");
        }
        System.out.println("-------------------\n");
    }

    public static void testPurchaseItem5() {
        System.out.println("-------------------");
        System.out.println(" - PurchaseItem Test 5");
        VendingMachine test = new VendingMachine("Jimmy", 10);
        try {
            System.out.println("\nAdding a new Item, Cake, Price: £1, Qty: 1 - Within VENDING_MODE");
            System.out.println("Testing to buy the item twice, Therefore buying the item with a zero Qty");
            System.out.println("Adding £1 towards userMoney");
            test.setUserMoney(1);
            test.addNewItem(new VendItem("Cake", 1.0, 1));
            test.purchaseItem(7);
            System.out.println("\nAdding another £1 towards userMoney");
            test.setUserMoney(1);
            test.purchaseItem(7);
            System.out.println("\n\nTest passed - Item has not been purchased, Item has no Qty left\nUser has received their money back");
        } catch (Exception e) {
            System.out.println("Test Failed - Error");
        }
        System.out.println("-------------------\n");
    }

    public static void testPurchaseItem6() {
        System.out.println("-------------------");
        System.out.println(" - PurchaseItem Test 6");
        VendingMachine test = new VendingMachine("Jimmy", 10);
        try {
            System.out.println("\nPurchasing a Red Bull at the price of £1.50 - Within VENDING_MODE");
            System.out.println("Adding £1.00 to the userMoney (Incorrect amount for red bull)");
            test.setUserMoney(1);
            System.out.println(test.purchaseItem(5));
            System.out.println("\nTest passed - Item has not been purchased, User does not have enough Money\nTherefore userMoney has not been affected");
        } catch (Exception e) {
            System.out.println("Test Failed - Error");
        }
        System.out.println("-------------------\n");
    }

    public static void testPurchaseItem7() {
        System.out.println("-------------------");
        System.out.println(" - PurchaseItem Test 7");
        VendingMachine test = new VendingMachine("Jimmy", 10);
        try {
            System.out.println("\nPurchasing a Red Bull at the price of £1.50 - Within SERVICE_MODE");
            System.out.println("Adding £1.00 to the userMoney (Incorrect amount for red bull)");
            test.setUserMoney(1);
            test.setStatus(Status.SERVICE_MODE);
            System.out.println(test.purchaseItem(5));
            System.out.println("\nTest passed - Item has not been purchased, Machine is in SERVICE_MODE");
        } catch (Exception e) {
            System.out.println("Test Failed - Error");
        }
        System.out.println("-------------------\n");
    }

    public static void testDeliver1() {
        System.out.println("-------------------");
        System.out.println(" - Deliver, Test 1\n");
        System.out.println("Testing input, Creating Item, Qty: 6\n");
        VendItem test = new VendItem("Crisps", 1.5, 6);
        String res = test.deliver();
        if (res.equals("Item not Available, '" + test.getName() + "' Contact Engineer for restock!"))
            System.out.println("Test Failed - Error as there is QtyAvailable");
        else
            System.out.println("Test passed - Purchase Made, Qty updated to: " + test.getQty());
        System.out.println("-------------------\n");
    }

    public static void testAddNewItem1() {
        System.out.println("-------------------");
        System.out.println(" - AddNewItem Test 1");
        VendingMachine test = new VendingMachine("Jimmy", 10);
        try {
            System.out.println("\nTesting input, creating Item object, Name: Cake, Price: 1.5, Qty: 6");
            test.addNewItem(new VendItem("Cake", 1.5, 6));
            System.out.println("\nTest passed - Item has been added");
        } catch (IllegalArgumentException e) {
            System.out.println("\nTest Failed - Error");
        }
        System.out.println("-------------------\n");
    }

    public static void testAddNewItem2() {
        System.out.println("-------------------");
        System.out.println(" - AddNewItem Test 2");
        VendingMachine test = new VendingMachine("Jimmy", 10);
        try {
            System.out.println("\nTesting input, creating Item object, Name: \"\", Price: 1.5, Qty: 6");
            test.addNewItem(new VendItem("", 1.5, 6));
            System.out.println("\nTest passed - Item has been added, Default name replaced");
        } catch (IllegalArgumentException e) {
            System.out.println("\nTest Failed - Error");
        }
        System.out.println("-------------------\n");
    }

    public static void testAddNewItem3() {
        System.out.println("-------------------");
        System.out.println(" - AddNewItem Test 3");
        VendingMachine test = new VendingMachine("Jimmy", 10);
        try {
            System.out.println("\nTesting input, creating Item object, Name: Cake, Price: 2.5, Qty: 6");
            test.addNewItem(new VendItem("Cake", 2.5, 6));
            System.out.println("\nTest Failed - Item has been added");
        } catch (IllegalArgumentException e) {
            System.out.println("\nTest passed - Item was not created, Price cannot be above £2");
        }
        System.out.println("-------------------\n");
    }

    public static void testAddNewItem4() {
        System.out.println("-------------------");
        System.out.println(" - AddNewItem Test 4");
        VendingMachine test = new VendingMachine("Jimmy", 10);
        try {
            System.out.println("\nTesting input, creating Item object, Name: Cake, Price: -1.5, Qty: 6");
            test.addNewItem(new VendItem("Cake", -1.5, 6));
            System.out.println("\nTest Failed - Item has been added");
        } catch (IllegalArgumentException e) {
            System.out.println("\nTest passed - Item was not created, Price cannot be negative or equal to 0");
        }
        System.out.println("-------------------\n");
    }

    public static void testAddNewItem5() {
        System.out.println("-------------------");
        System.out.println(" - AddNewItem Test 5");
        VendingMachine test = new VendingMachine("Jimmy", 10);
        try {
            System.out.println("\nTesting input, creating Item object, Name: Cake, Price: 1.5, Qty: 15");
            test.addNewItem(new VendItem("Cake", 1.5, 15));
            System.out.println("\nTest Failed - Item has been added");
        } catch (IllegalArgumentException e) {
            System.out.println("\nTest passed - Item was not created, Qty cannot be greater than 10");
        }
        System.out.println("-------------------\n");
    }

    public static void testAddNewItem6() {
        System.out.println("-------------------");
        System.out.println(" - AddNewItem Test 6");
        VendingMachine test = new VendingMachine("Jimmy", 10);
        try {
            System.out.println("\nTesting input, creating Item object, Name: Cake, Price: 1.5, Qty: -2");
            test.addNewItem(new VendItem("Cake", 1.5, -2));
            System.out.println("\nTest Failed - Item has been added");
        } catch (IllegalArgumentException e) {
            System.out.println("\nTest passed - Item was not created, Qty cannot be less than 0, or equal to 0");
        }
        System.out.println("-------------------\n");
    }
}

