package ProgramTesting.UnitTesting;

import Part1.VendItem;

public class VendItemTesting {

    public static void main(String[] args) {
        testVendItemConstructor1test1();
        testVendItemConstructor1test2();
        testVendItemConstructor1test3();
        testVendItemConstructor1test4();
        testVendItemConstructor1test5();
        testVendItemConstructor2test1();
        testVendItemConstructor2test2();
        testVendItemConstructor2test3();
        testVendItemConstructor2test4();
        testGetName1();
        testGetName2();
        testGetPrice1();
        testGetQty1();
        testRestock1();
        testRestock2();
        testRestock3();
        testRestock4();
    }

    public static void testVendItemConstructor1test1() {
        System.out.println("-------------------");
        System.out.println(" - VendItem Constructor 1, Test 1\n");
        try {
            System.out.println("Testing input, name: Item 1, UnitPrice: 1.5\n");
            VendItem test = new VendItem("Item 1", 1.5);
            System.out.println("Test passed - No exception thrown, Item name set to: Item 1 & UnitPrice set to: 1.5");
        } catch (IllegalArgumentException e) {
            System.out.println("Test Failed");
        }
        System.out.println("-------------------\n");
    }

    public static void testVendItemConstructor1test2() {
        System.out.println("-------------------");
        System.out.println(" - VendItem Constructor 1, Test 2\n");
        try {
            System.out.println("Testing input - empty name String, name: \"\", UnitPrice: 1.5\n");
            VendItem test = new VendItem("Item 1", 1.5);
            System.out.println("Test passed - No exception thrown, Item name set to: 'Item' & UnitPrice set to: 1.5");
        } catch (IllegalArgumentException e) {
            System.out.println("Test Failed");
        }
        System.out.println("-------------------\n");
    }

    public static void testVendItemConstructor1test3() {
        System.out.println("-------------------");
        System.out.println(" - VendItem Constructor 1, Test 3\n");
        try {
            System.out.println("Testing input, name: Item 1, UnitPrice: 2.5\n");
            VendItem test = new VendItem("Item 1", 2.5);
            System.out.println("Test Failed");
        } catch (IllegalArgumentException e) {
            System.out.println("Test passed - exception thrown, Item Price cannot be above the '2'");
        }
        System.out.println("-------------------\n");
    }

    public static void testVendItemConstructor1test4() {
        System.out.println("-------------------");
        System.out.println(" - VendItem Constructor 1, Test 4\n");
        try {
            System.out.println("Testing input, name: Item 1, UnitPrice: 0\n");
            VendItem test = new VendItem("Item 1", 0.0);
            System.out.println("Test Failed");
        } catch (IllegalArgumentException e) {
            System.out.println("Test passed - exception thrown, price cannot equal '0.0'");
        }
        System.out.println("-------------------\n");
    }

    public static void testVendItemConstructor1test5() {
        System.out.println("-------------------");
        System.out.println(" - VendItem Constructor 1, Test 5\n");
        try {
            System.out.println("Testing input, name: Item 1, UnitPrice: -1.5\n");
            VendItem test = new VendItem("Item 1", -1.5);
            System.out.println("Test Failed");
        } catch (IllegalArgumentException e) {
            System.out.println("Test passed - exception thrown, item price cannot be negative");
        }
        System.out.println("-------------------\n");
    }

    public static void testVendItemConstructor2test1() {
        System.out.println("-------------------");
        System.out.println(" - VendItem Constructor 2, Test 1\n");
        try {
            System.out.println("Testing input, name: Item 1, UnitPrice: 1.5, Qty: 6\n");
            VendItem test = new VendItem("Item 1", 1.5, 6);
            System.out.println("Test passed - No exception thrown, Item name set to: Item 1 & UnitPrice set to: 1.5 & Qty was set to: 6");
        } catch (IllegalArgumentException e) {
            System.out.println("Test Failed");
        }
        System.out.println("-------------------\n");
    }

    public static void testVendItemConstructor2test2() {
        System.out.println("-------------------");
        System.out.println(" - VendItem Constructor 2, Test 2\n");
        try {
            System.out.println("Testing input, name: Item 1, UnitPrice: 1.5, Qty: -2\n");
            VendItem test = new VendItem("Item 1", 1.5, -2);
            System.out.println("Test Failed");
        } catch (IllegalArgumentException e) {
            System.out.println("Test passed - exception thrown, QtyAvailable cannot be set below 1");
        }
        System.out.println("-------------------\n");
    }

    public static void testVendItemConstructor2test3() {
        System.out.println("-------------------");
        System.out.println(" - VendItem Constructor 2, Test 3\n");
        try {
            System.out.println("Testing input, name: Item 1, UnitPrice: 1.5, Qty: 12\n");
            VendItem test = new VendItem("Item 1", 1.5, 12);
            System.out.println("Test Failed");
        } catch (IllegalArgumentException e) {
            System.out.println("Test passed - Exception thrown, qty Available cannot be above 10");
        }
        System.out.println("-------------------\n");
    }

    public static void testVendItemConstructor2test4() {
        System.out.println("-------------------");
        System.out.println(" - VendItem Constructor 2, Test 4\n");
        try {
            System.out.println("Testing input, name: Item 1, UnitPrice: -0.5, Qty: 6\n");
            VendItem test = new VendItem("Item 1", -0.5, 6);
            System.out.println("Test Failed");
        } catch (IllegalArgumentException e) {
            System.out.println("Test passed - exception thrown, item price cannot be negative");
        }
        System.out.println("-------------------\n");
    }

    public static void testGetName1() {
        System.out.println("-------------------");
        System.out.println(" - getName, Test 1\n");
        System.out.println("Testing input, Name: 'Crisps'\n");
        VendItem test = new VendItem("Crisps", 1.5, 6);
        if (test.getName().equals("Crisps"))
            System.out.println("Test passed - Name is equal to: "+test.getName());
        else
            System.out.println("Test Failed");
        System.out.println("-------------------\n");
    }

    public static void testGetName2() {
        System.out.println("-------------------");
        System.out.println(" - getName, Test 1\n");
        System.out.println("Testing input - Default name, Name: \"\"\n");
        VendItem test = new VendItem("", 1.5, 6);
        if (test.getName().equals("Item"))
            System.out.println("Test passed - Name changed and is equal to: "+test.getName());
        else
            System.out.println("Test Failed");
        System.out.println("-------------------\n");
    }

    public static void testGetPrice1() {
        System.out.println("-------------------");
        System.out.println(" - getUnitPrice, Test 1\n");
        System.out.println("Testing input - Price: 1.5: \n");
        VendItem test = new VendItem("", 1.5, 6);
        if (test.getPrice() == 1.5)
            System.out.println("Test passed - Price is equal to: "+test.getPrice());
        else
            System.out.println("Test Failed");
        System.out.println("-------------------\n");
    }

    public static void testGetQty1() {
        System.out.println("-------------------");
        System.out.println(" - getQty, Test 1\n");
        System.out.println("Testing input - Qty: 8\n");
        VendItem test = new VendItem("", 1.5, 8);
        if (test.getQty() == 8)
            System.out.println("Test passed - QtyAvailable changed and is equal to: "+test.getQty());
        else
            System.out.println("Test Failed");
        System.out.println("-------------------\n");
    }

    public static void testRestock1() {
        System.out.println("-------------------");
        System.out.println(" - Restock, Test 1\n");
        System.out.println("Testing input - Item current Qty: 5, Inputting Qty: 7 into 'Restock' method\n");
        VendItem test = new VendItem("Sweets", 1.5, 5);
        if (test.restock(7)) {
            test.setQtyAvailable(7);
            System.out.println("Test passed - QtyAvailable changed and is equal to: " + test.getQty());
        } else
            System.out.println("Test Failed");
        System.out.println("-------------------\n");
    }

    public static void testRestock2() {
        System.out.println("-------------------");
        System.out.println(" - Restock, Test 2\n");
        System.out.println("Testing input - Item current Qty: 5, Inputting Qty: 4 into 'Restock' method\n");
        VendItem test = new VendItem("Sweets", 1.5, 5);
        if (test.restock(4)) {
            test.setQtyAvailable(4);
            System.out.println("Test Failed - QtyAvailable changed and is equal to: " + test.getQty());
        } else
            System.out.println("Test passed - Item restock amount is cannot be > 10 & >1, Restock Qty needs to be greater than current amount, Qty didn't change");
        System.out.println("-------------------\n");
    }

    public static void testRestock3() {
        System.out.println("-------------------");
        System.out.println(" - Restock, Test 3\n");
        System.out.println("Testing input - Item current Qty: 5, Inputting Qty: -1 into 'Restock' method\n");
        VendItem test = new VendItem("Sweets", 1.5, 5);
        if (test.restock(-1)) {
            test.setQtyAvailable(-1);
            System.out.println("Test Failed - QtyAvailable changed and is equal to: " + test.getQty());
        } else
            System.out.println("Test passed - Item restock amount is cannot be > 10 & >1, Restock Qty needs to be greater than current amount, Qty didn't change");
        System.out.println("-------------------\n");
    }

    public static void testRestock4() {
        System.out.println("-------------------");
        System.out.println(" - Restock, Test 4\n");
        System.out.println("Testing input - Item current Qty: 5, Inputting Qty: 12 into 'Restock' method\n");
        VendItem test = new VendItem("Sweets", 1.5, 5);
        if (test.restock(12)) {
            test.setQtyAvailable(12);
            System.out.println("Test Failed - QtyAvailable changed and is equal to: " + test.getQty());
        } else
            System.out.println("Test passed - Item restock amount is cannot be > 10 & >1, Restock Qty needs to be greater than current amount, Qty didn't change");
        System.out.println("-------------------\n");
    }
}

