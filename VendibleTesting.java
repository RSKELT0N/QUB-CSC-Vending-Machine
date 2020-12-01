package ProgramTesting.UnitTesting;

import Part1.VendItem;

public class VendibleTesting {

    public static void main(String[] args) {
        testDeliver1();
        testDeliver2();
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

    public static void testDeliver2() {
        System.out.println("-------------------");
        System.out.println(" - Deliver, Test 2\n");
        System.out.println("Testing input, Creating Item, Qty: 0\n");
        VendItem test = new VendItem("Crisps", 1.5, 1);
        test.setQtyAvailable(0);
        String res = test.deliver();
        if (res.equals("Item not Available, '" + test.getName() + "' Contact Engineer for restock!"))
            System.out.println("Test passed - Purchase not made, Qty is equal to or less than '0'");
        else
            System.out.println("Test Failed - Purchase Made");
        System.out.println("-------------------\n");
    }
}
