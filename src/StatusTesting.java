package ProgramTesting.UnitTesting;

import Part1.Status;

public class StatusTesting {

    public static void main(String[] args) {
        testGetStatus1();
        testGetStatus2();
    }

    public static void testGetStatus1() {
        System.out.println("-------------------");
        System.out.println(" - getStatus Test 1\n");
        try {
            Status test = Status.SERVICE_MODE;
            System.out.println("Testing input, Change Status to 'SERVICE_MODE'\n");
            System.out.println("Testing 'getStatus' method, Output: " + test.getStatus());
            System.out.println("\nTest passed - No Exception Thrown");
        } catch (Exception e) {
            System.out.println("Test Failed");
        }
        System.out.println("-------------------\n");
    }

    public static void testGetStatus2() {
        System.out.println("-------------------");
        System.out.println(" - getStatus Test 2\n");
        try {
            Status test = Status.VENDING_MODE;
            System.out.println("Testing input, Change Status to 'VENDING_MODE'\n");
            System.out.println("Testing 'getStatus' method, Output: " + test.getStatus());
            System.out.println("\nTest passed - No Exception Thrown");
        } catch (Exception e) {
            System.out.println("Test Failed");
        }
        System.out.println("-------------------\n");
    }
}
