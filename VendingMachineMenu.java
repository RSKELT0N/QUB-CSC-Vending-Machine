package Part2;

/** Importing rngEng method() */
import static Part2.EngineerMenu.runEng;
/** Importing input method() */
import static Part2.VendingMachine.input;
/** Importing vendingMachine object */
import static Part2.Main.vendingMachine;
/** Importing scanner object */
import static Part2.VendingMachine.sc;



import java.io.IOException;
import java.util.InputMismatchException;

/**
 * Author: Ryan Skelton, 40266093
 *
 * The purpose of this class is too carry out the procedure of the User menu access
 * to the vending machine. Therefore this involve all of the options the general user can see. Once the
 * program method has been called within this method, the display for the menu of the vending machine will be displayed.
 * From there they can see all options that they can see and carry out, as they are given with a option to enter one of these following options.
 *
 * Depending on what option they, this will differ the function of what happens next. for example, if the user enters the value '1'. They will
 * be promoted with teh different items that are stored within stock array variable of the vending machine object that has been created.
 */
public class VendingMachineMenu {

    //Initialising the variable choice
    private static int choice = 0;
    //Initialising the boolean breakEng, to break out of the Engineer menu
    static boolean breakEng = false;

    /**
     * The program method is called when the program starts. THe purpose of this method is to display provide input for the users
     * to enter, therefore they can control the vending Machine and functionally use it effectively. This method is very important part of the
     * project as it carries the functionality of the menu and allowing them to enter money, view items purchase request and
     * finally purchase an item.
     * @throws IOException - This exception is used for input mismatch
     * @throws InterruptedException - This excpetion is used for the 'sleep' method, as it stops errors being interrupted.
     */
    public static void program() throws IOException, InterruptedException {

        //A boolean variable to decide when to intake a next line with the scanner object.
        boolean nextline = false;
        //This boolean is to print out 'press enter to continue' function within the program.
        boolean print = false;
        //This is a boolean used to determine weather to intake nextLine, for inputting the balance inputted option
        boolean skipScanLine = false;

        //User
        String title = "Vending Machine";
        String[] options = {"View Items", "Balance", "Purchase Item", "Add Money","Quit"};
        Menu menu = new Menu(title, options);

        //While loop
        //Includes exceptions to handle errors and data input mismatch.
        while (true) {
            //Taking in the nextLine(Reducing error)
            if (nextline) {
                if (print)
                    input("Please press enter continue...");
                if (!skipScanLine && choice != -1)
                sc.nextLine();
            }
            //Changing booleans to false  for next loop
            print = false;
            skipScanLine = false;
            breakEng = false;
            try {
                //Inputting option
                choice = menu.getChoice();
                //Changing the boolean nextLine to true, to intake line
                nextline = true;
                //Taking exceptions for input mismatch
            } catch (Exception e) {
                System.out.println("Invalid Input!\n");
            }

            //Switch case loop
            switch (choice) {
                //Engineer level
                case -1: runEng();
                break;
                //Listing items
                case 1: String[] items = vendingMachine.listItems();
                if(items == null) {
                    skipScanLine = true;
                    break;
                }
                    new Table("Vending Machine Items","      Item    Price     Quantity   Name  ", items,15,16,0, true);
                    print = true;
                break;
                //Adding money
                case 2: input("Balance added: £" + String.format("%.2f", vendingMachine.getUserMoney()));skipScanLine = true;break;
                //Purchasing an item
                case 3: input("Item ID: ");
//                try {
                    int choice = sc.nextInt();
                    System.out.print(vendingMachine.purchaseItem(choice));
//                } catch (Exception e) {
//                    System.out.print("~~ Please enter an Integer only, within the range of the items available.\n");
//                }
                    break;
                //Inputting coins
                case 4: input("Coins valid: 0.05, 0.10, 0.20, 0.50, £1, £2");input("Coin: ");
                    try {
                        vendingMachine.determineCoin(sc.nextLine());
                        skipScanLine = true;
                        //Input exception
                    } catch (Exception e) {
                        input("Not a valid coin type!");
                        skipScanLine = true;
                    }
                    break;
                //Quiting program, will save data to 'structure.csv' file.
                case 5: input("[Machine is in '" + vendingMachine.getVmStatus() + "' And Has Now Shut Down]");
                input("Saving data");Thread.sleep(500);System.out.print(".");Thread.sleep(500);System.out.print(".");Thread.sleep(500);
                System.out.print(".");
                vendingMachine.onStoreData();
                Thread.sleep(2000);
                input("Vending machine structure has been saved\n");
                System.exit(1);
                default: skipScanLine = true;
            }
            }
        }
    }