package Part2;

/**Importing the vending machine object from the 'Main.java' */
import static Part2.Main.vendingMachine;
/**Importing the method 'input()' */
import static Part2.VendingMachine.input;
/**Importing the scanner object */
import static Part2.VendingMachine.sc;

import javax.swing.*;
import java.io.IOException;

/**
 * Author: Ryan Skelton, 40266093
 * This classes purpose is to carry out the Engineer level of the vending machine
 * Therefore this part will offer a new menu and new options to select. Such as changing status, view system
 * information or adding a new item etc.
 */
public class EngineerMenu {

    /**This is boolean to check if the password is valid that has been entered. */
    private static boolean valid = false;

    /**
     * The 'runEng()' method is called whenever the user enters '-1' into the getChoice scanner, within Menu Class. Therefore, once the password
     * entered is correct, the user will be able to access different functions within the vending machine.
     * @throws IOException
     */
    public static void runEng() throws IOException {

        //Title
        String titleEngineer = "Engineer Access";
        //Options
        String[] optionsEngineer = {"Get System Information", "Change Vending-Machine Mode", "Reset(Empty Money, Empty Stock)", "Maintenance", "Quit"};
        //Creating Menu Object
        Menu menuEngineer = new Menu(titleEngineer, optionsEngineer);

        boolean flip = false;
        boolean print = false;
        boolean skipScanLine = false;
        int choiceEngineer = 0;
        //password
        final String passwordEng = "Java";
        //Creating JOptionPane method that opens up GUI to prompt user with password
        String password = JOptionPane.showInputDialog(null, "Password");
        //Tests Password
        if (password.equals(passwordEng))
            valid = true;
        else
            return;
        while (valid) {
            sc.reset();
            if (flip) {
                if(print)
                    input("Please press enter to continue..");

                if(!skipScanLine)
                    sc.nextLine();
                print = false;
                skipScanLine = false;
            }
            try {
                //Input choice
                choiceEngineer = menuEngineer.getChoice();
                flip = true;
            } catch (Exception e) {
                //Error message for input mismatch
                System.out.println("Invalid Input!\n");
            }
            switch (choiceEngineer) {
                //1 = System info
                case 1: vendingMachine.getSystemInfo();print = true;break;
                //2 = Engineer change status
                case 2: vendingMachine.engineerChange();break;
                //3 = reset data
                case 3: vendingMachine.reset();break;
                //4 = access maintenance settings
                case 4: vendingMachine.maintenance();break;
                //5 = quit Engineer level of Vending machine
                case 5: VendingMachineMenu.breakEng = true;skipScanLine = true;break;
                default: skipScanLine = true;
            }
            //break out of Engineer mode
            if (VendingMachineMenu.breakEng)
                break;
        }
        return;
    }
}
