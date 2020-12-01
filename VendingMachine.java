package Part2;

import java.io.*;
import java.util.*;

/**
 * Author: Ryan Skelton, 40266093
 * This class is here to represent the class 'Vending Machine'.
 * This class is very important part of the project as it holds the main methods to carry out of
 * the Main class, to allow the to functionally use the vending machine, insert coins. Purchase items
 * and finally given them the correct change back. I have implemented different features that keep of the VendItems,
 * with the use of the array type. That allows me to view the different items that are held within the array.
 * The Engineer level, has an option to change the structure of the vending machine, such as the status, restock or add
 * new items. However, they will be restricted to add more items than the maxItem count, as this will overflow the
 * Vending machine.
 *
 * The vending machine also keeps track of money entered, amount of coins. The user money that has been entered.
 * This data will be stored and be compared with different processes that are being carried out, such as buying items and giving change.
 * As the user money entered will depend if they can buy an item in the vending machine, along with the status.
 */
public class VendingMachine {


    /**This array holds the different types of coins */
    private final double[] coinType = {0.05, 0.1, 0.2, 0.5, 1.0, 2.0};

    /** The HashSet(coins) is used to test and check when a user enters money into the Vending Machine,
     * As all types of coins that are accepted are entered into the HashSet, therefore once a coin is entered
     * it is tested against this data-type with the method (contains) to see if they HashSet has it stored. If so
     * the coin is valid/otherwise it is not.*/
    private static HashSet<Double> coins = new HashSet<>();{ {
            coins.add(0.05);coins.add(0.1);coins.add(0.2);coins.add(0.5);coins.add(1.0);coins.add(2.0);
        }
    }

    /**The HashMap used is to keep track of the amount of each type of coin. As this is very important as this will
     * determine if the Vending machine can provide this type of coin back to the user. The Key(Double) in the HashMap represents the
     * coin type, the value(Integer) represents the amount of that coin is available. */
    private HashMap<Double, Integer> trackCoinsVal = new HashMap<>(6);

    /**This default String is used, whenever an object of the Vending machine class is being created
     * this String will be replaced as the name, as therefore it wont be valid*/
    private final static String DEFAULT = "DEFAULT";

    /**The owner String is used to contain the name of the Vending of machine, when a object is being created created */
    private String owner;

    /**The variable is used to set the total amount of items that can be created within the vending machine */
    private int maxItems;

    /**The variable 'itemCount' is used to count of the amount of items that are currently held within the
     * vending machine */
    private int itemCount;

    /**This array is used to hold the different objects that are created from the vendItems class, therefore this allows me to store the different
     * items that are created currently within the vending machine, along with their information such as the price, quantity and name.*/
    private VendItem[] stock;

    /**The variable is used to hold the total money made by the vending machine */
    private double totalMoney;

    /**The variable is used and will be updated every time a user inputs one of the coins, keeps
     * track of how much money has been entered */
    private double userMoney;

    /** The variable declared here is of type Status, which is enumeration which helps describe the state of the vending machine.
     * Such as 'VENDING_MODE' and 'SERVICE_MODE', therefore this state will change the behaviour of the vending machine.  */
    private Status vmStatus;

    /**This keeps track how many coins have been entered into the machine, tells the user how many they have entered. */
    private int AmountOfCoinsEntered;

    /**The scanner allows me to read input */
      static public final Scanner sc = new Scanner(System.in);

    /**This variable, String allows me login into the Engineer level of the vending machine.  */
    private final String password = "Java";

    /**This boolean checks weather if the file 'structure.csv' is valid or not. Therefore laoding data of the file or the default settings
     * that are preset. */
    private boolean fileValid;

    /**
     *This method is used for the constructing an object of the vending machine class. The constructor accepts two parameters owner and maxItems.
     * The constructor is programmed to read a csv file and structure the vending machine like so. However if the file doesn't exist or is
     * corrupted, the vending machine readings the data from the default settings. Therefore having the default structure.
     *
     * The constructor makes the vending machine class all setup for the user interactivity, therefore making all the coins are valid and existed,
     * for the user to get change. The constructor also adds the VendItems objects towards the stock array, therefore the user can see the item
     * within the vending machine and also allowing them to buy the item without any errors. All variables up above are initialised either from the file or
     * from the default settings.
     *
     * @param owner - This is the name of the vending machine, if null. Owner String, will be replaced with the default String.
     * @param maxItems - This is amount of items that can be added towards the vending machine.
     * @throws IllegalArgumentException - This is to throw an exception if the maxItems is either negative or above 10.
     * @throws IOException - This is used to catch any errors within the constructor and deal with them professionally
     * @throws FileNotFoundException - This exception is used to determine if the csv file exists or not.
     */
    public VendingMachine(String owner,int maxItems) throws IllegalArgumentException, IOException, FileNotFoundException {

        //Loading the csv file
        try {
            addCoinsToHash();
            onLoadData();
            setTotalMoney();
            fileValid = true;
            input("Data being loaded from 'structure.csv'...");
        } catch (FileNotFoundException e) {
            input("CSV file not found, loading default settings...\n");
        }

        /** Exception handling for max items. */
            if (maxItems < 0)
                throw new IllegalArgumentException("Max items must be positive");

            if (maxItems > 10)
                throw new IllegalArgumentException("Max Item, can exceed the Integer value: 10");

            /** Default settings, iof csv file not found. */
        if(fileValid == false) {
            addCoinsToHash();
            setOwner(owner);
            this.maxItems = maxItems;
            this.stock = new VendItem[maxItems];
            addSetItems();
            setStatus(Status.VENDING_MODE);
        }
    }

    /**The getSystemInfo method is used to print out the Vending Machine information within the program, therefore grabbing all
     * the data that has been stored about the program. Such as the name, max Items, items that are still available, the total
     * amount of money that has been made, status, coins availability etc.
     *
     * I use the class Table, which I created which help me formats the information effectively within the console without any
     * errors. Therefore i create a object of this class and pass the System information through as a parameter.
     *
     */
    public void getSystemInfo() {

        String[] coins = new String[coinType.length];
        for (int i = 0; i < trackCoinsVal.size(); i++) {
            coins[i] = "Coin: £" + String.format("%.2f", coinType[i]) + ", x" + trackCoinsVal.get(coinType[i]);
        }

        String[] data = {"Owner: " + this.owner,
                "Type of Items Remaining: " + countRemainingItems(),
                "Type's of Items: " + this.itemCount,
                "Total Money: £" + this.totalMoney,
                "User's Money entered: £" + this.userMoney,
                "Status: " +  vmStatus.getStatus(), coins[0],coins[1],coins[2],coins[3],coins[4],coins[5]};


        new Table("System Information","Coins Available", data,16,16, 6,false);

}

    /**This reset method is used to write the sales data towards the text file, 'data.txt'. As giving the option towards the user to
     * carry this procedure allowing them to save this data. This method is found within the Engineer level of the program. This method also
     * resets the total money of the machine and the itemCount of the machine.
     *
     * @throws IOException - This method throws an exception if the user inputs a different value other than Integers.
     */
    public void reset() throws IOException {
        System.out.print("\nSave Data?\n[1] Yes \n[2] Reset (Without Saving Data)\n~~ :  ");

        /**Choice from the user */
        int choice = 0;
        try {
            choice = sc.nextInt();
        } catch (Exception e) {
            System.out.print("~~ Please enter one of following options");
        }

        if (choice == 1) {
            saveTextFileData();
            input("Data has been saved towards the 'data.txt' file...");
        }
        else if (choice == 2) {
        }
        //Resetting the items
        this.itemCount = 0;
        this.totalMoney = 0;
        this.userMoney = 0.0;
        vmStatus = Status.VENDING_MODE;
    }

    /**
     * This method is used to collect all the items that are stored within the vending machine
     * within a formatted way. Displaying them correctly. From here, this string array is passed through an Table class object
     * and created/displayed within a formatted table within the program.
     *
     * @return - An array of a list items. Holding each piece of every information of every item stored in the
     * vending machine.
     */
    public String[] listItems() {
        if(vmStatus == Status.SERVICE_MODE) {
            input("Vending Machine is in Service mode, Contact Engineer!");
            return null;
        }

        System.out.println();
        //Array for items
        String[] items = new String[itemCount];

        for (int i = 0; i < itemCount; i++) {
            items[i] = "       "+i + "       £" +stock[i].getPrice() +"       "
                    + stock[i].getQty()+"x       "
                    +stock[i].getName();
        }
        return items;

    }

    /**
     * The purchaseItem method is one of most important methods that is used within the bending machine, as this allows the user to view the different
     * Items from the listItems method, From there they can use this method to simply see the and ID and select the item they want. This method will
     * allow them to grant the item towards the user, by reducing the quantity of the item selected, however the user can only purchase that item. If the userMoney
     * is greater than or equal to the piece of the item. However, the user is also not allowed to buy the item, if the quantity is lower than one or that the Vending
     * machine status is in 'Service mode'. All these error messages will pop up individually depending what is caused if the user is not capable of purchasing the item
     *
     * Once the user has purchased an item, the change method will be called. Which works out the most efficient way the change can be given
     * to the user which is due. However this depends on what coins is valid in the vending machine. For the receipt of the purchasing the item,
     * i have created another object of the table class, which will present the item and change that was stored during the purcahse within a formatted
     * table within the program.
     *
     * The method calls the deliver method to check the quantity of the item is still above 0. And able to buy. Which returns a boolean variable.
     * Allowing the user to buy the item.
     * @param index - The index of this method parameter is to determine which item the user wants to buy.
     * @return - This return the result of the purchase.
     */
    public String purchaseItem(int index) {
        boolean returnMoney = false;
        boolean purchaseMade = false;

        if(vmStatus != Status.VENDING_MODE) {
            input("Vending Machine is in Service mode, Contact Engineer!");
            return "";
        }

        if (stock[index].getQty() == 0) {
            input("Sorry we have ran out of '" + stock[index].getName() + "', Please contact engineer for restock. Now in Service Mode");
            setStatus(Status.SERVICE_MODE);
            returnMoney = true;
        }

        double change = round(userMoney - stock[index].getPrice(),2);
        String changeRes = change(change);


        //Creating data for Table Object
        String[] data = {"Change                  Total: £" +round(userMoney-stock[index].getPrice(),2),
                "------", "£"+round(this.userMoney - stock[index].getPrice(),2),
                "", changeRes,"","",
                (stock[index].deliver())};

       if(changeRes.length() > 25)
            return changeRes;

        String result = "";
        //Deliver method being called
        if (purchaseRequest(index)) {
            new Part2.Table("Receipt", "", data, 16, 16, 6, false);
            purchaseMade = true;
        } else {
//            return change(userMoney);
        }

        if(returnMoney) {
            input("Change: \n"+change(userMoney));
            userMoney = 0.0;
            return "";
        }

        if(purchaseMade) {
            //Updating variables
            userMoney = 0.0;
            totalMoney += stock[index].getPrice();
            totalMoney -= userMoney;
            //Returning result
        }
            return result;
    }

    /**
     * This method is used to format output similarly, therefore calling this message. Will display the message
     * with '~~' before it. Therefore reduces redundancy.
     * @param input - This the message that is being sent towards the console
     */
    public static void input(String input) {
        System.out.print("\n~~ " + input);
    }

    /**
     *This method is used to add the value 'two' towards each coin which is held within the HashMap.
     * Therefore this allows the vending machine to dispense two of each coin towards the
     * user for their change. This information will be displayed.
     */
    private void addCoinsToHash() {
        for (int i = 0; i < coins.size(); i++) {
            trackCoinsVal.put(coinType[i], 2);
        }
    }

    /**
     * This method determines what is bring inputted, stepping stone 'insertCoin' method.
     * @param coin - String that takes coin parameter
     */
    public void determineCoin(String coin) {
        if(coin.equals(""))
            return;
        int coinInput = 0;
        if(coin.contains("£"))
            insertCoin(Integer.parseInt(coin.substring(1)));
        else {
            double newDoubleTmp = Double.parseDouble(String.valueOf(coin));
            coinInput = (int) (newDoubleTmp*100);
            insertCoin(coinInput);
        }

    }

    /**
     *The method allows the user to enter a coin within the vending machine, as the value tat they enter in the variable
     * 'amount' will be tested against the HashSet too see if it contains the value that was entered. If the result turns out ot be true,
     * the coin will be entered and the userMoney, will be added depending on the coin was entered. However if the result if false. An message will pop
     * up, wrong coin input.
     * @param amount - Is the amount of money being entered, (type of coin).
     * @return - This returns a boolean, if the coin entered is valid.
     */
    public boolean insertCoin(int amount) {
        double newVal;

        if (amount <= 2) {
            newVal = amount;
        } else {
            newVal = (amount / 100.0);
        }


        if (coins.contains(newVal)) {
            //Setting the user money
            setUserMoney(newVal);
            //Increasing amount of coins entered
            trackCoinsVal.put(newVal, trackCoinsVal.get(newVal)+1);
            return true;
        } else {
            //Error message
            input("Please enter one of valid Coin inputs!");
            return false;
        }
    }

    /**
     *This method is used to count the amount of items that have a higher quantity than 0.
     * @return - Returns an int. the amount of items.
     */
    private int countRemainingItems() {
        int res = 0;
        for (int i = 0; i < itemCount; i++)
            if (stock[i].getQty() > 0)
                res++;
        return res;
    }

    /**This method is used within the Engineer level of the program, this allows the user to add a new item towards
     * the Vending machine. Therefore if the amount of current items is greater than the max Items.
     * The item wont be added and an error message will be displayed. However if this condition isn't true. The item will be added towards
     * the vending machine. Allowing item to be bought by the user.
     *
     * @param item - The item is the VendItem object that will be added towards the vending machine stock array.
     */
    public void addNewItem(VendItem item) {

        if(item.getPrice() > 2 ) {
            return;
        }
            //Testing condition
            if (itemCount >= maxItems) {
                input("Vending machine can't hold any more items");
                return;
            }
        //Adding item
        stock[itemCount++] = item;
        System.out.println("\n~~ Item '" + stock[itemCount - 1].getName() + "' has been added to the Vending Machine");
        }

    /**This method will set the owner of the vending machine, if the String 'owner' is null. The default
     * String will be replaced.
     * @param owner - This is the name that will take over the String owner name.
     */
    private void setOwner(String owner) {
        if (owner.equals(""))
            this.owner = DEFAULT;
        else
            this.owner = owner;
    }

    /**This method is used set the status of the vending machine
     * @param status - The state of the vending machine that will be set.
     */
    public void setStatus(Status status) {
        this.vmStatus = status;
    }

    /**This method is used to get the userMoney value
     * @return - The method is returning the value of the current userMoney
     */
    public double getUserMoney() {
        return userMoney;
    }


    /**This method is used to set the userMoney, therefore by passing the parameter into the method
     * this will plus the current value of the userMoney. Within this method will aslo plus the amount of coins entered.
     * @param amount - The amount of money that will be added towards the userMoney.
     */
    public void setUserMoney(double amount) {
        //Adding the userMoney
        this.userMoney += amount;
        this.AmountOfCoinsEntered++;
        input("'" + getAmountOfCoinsEntered() + "' Coin(s) has been entered into the Vending Machine\n");
    }

    /**
     *This method is used to add the total money from the start of the vending machine.
     * By adding the amount of each coins there is, multiplied by the coin.. Stored within that HashMap.
     */
    private void setTotalMoney() {
        double totalMoney = 0.0;
        for(int i = 0; i < trackCoinsVal.size(); i++) {
                totalMoney += coinType[i] * trackCoinsVal.get(coinType[i]);
        }
        this.totalMoney = totalMoney;
    }

    /**This method is used to count amount of coins entered into the vending, set by the user.
     * @return -  the amount of coins entered
     */
    public int getAmountOfCoinsEntered() {
        return this.AmountOfCoinsEntered;
    }


    /**This method is to find out which state the vending machine is in.
     * @return - returns the Status
     */
    public String getVmStatus() {
        return vmStatus.getStatus();
    }

    /** Method is used to test if the user can buy the item they have requested. Therefore this
     * will test if the item they requested, has a greater quantity than 0. and making sure if the Vending machine status is set to
     * 'vending mode'. If both of these conditions are true.
     * @param index - The index variable is the item the user is looking to request.
     * @return - This will return a true or false. Variable.
     */
    public boolean purchaseRequest(int index) {
        boolean requestValid = false;
        try {
            if (getUserMoney() >= stock[index].getPrice()) {
                if(vmStatus != Status.VENDING_MODE) {
                    input("Vending Machine is in Service mode, Contact Engineer!");
                    return false;
                }
                requestValid = true;
            } else {
                input("Money entered is not enough for required item, please insert more coins");
                requestValid = false;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        return requestValid;
    }

    /**
     *This method is used to set a default set of items towards the vending machine if the csv file cannot be found.
     * Therefore these items, will be added to the stock array. Therefore the users, can see and purchase these items.
     */
    private void addSetItems() {
        String[] items = {"Coke", "Fanta", "Crisps", "Sweets", "Chicken", "Red Bull", "Hot Dog"};

        stock[itemCount++] = new VendItem(items[0], 1.5, 4);
        stock[itemCount++] = new VendItem(items[1], 1.5, 2);
        stock[itemCount++] = new VendItem(items[2], 0.5, 3);
        stock[itemCount++] = new VendItem(items[3], 1.2, 3);
        stock[itemCount++] = new VendItem(items[4], 2.0, 5);
        stock[itemCount++] = new VendItem(items[5], 1.5, 2);
        stock[itemCount++] = new VendItem(items[6], 1.5, 2);

    }

    /**
     *This part of the method is to do with the Engineer part of the vending machine, as this method will allow
     * them to change the status of the service mode. Therefore, overriding any other condition.
     *
     * Only engineers with the password, can access this method.
     */
    public void engineerChange() {
        int choice = 0;
        input("[1] Vending Mode" + " [2] Service mode\n~~: ");
        try {
            choice = sc.nextInt();
        } catch (Exception e) {
            input("Please enter one of the following options!");
        }

        if (choice > 2)
            System.out.print("\n~~ Please enter one of the following options, Mode has not been changed!");

        switch (choice) {
            case 1:
                this.vmStatus = Status.VENDING_MODE;
                input("Vending-Machine mode has be changed to: " + vmStatus.getStatus() + "\n");
                break;
            case 2:
                this.vmStatus = Status.SERVICE_MODE;
                input("Vending-Machine mode has be changed to: " + vmStatus.getStatus() + "\n");
                break;
        }
    }

    /**This method is an abstract method for the Engineers, as this will give them options to either restock
     * or add a new item. From here other methods will be called, such as restock and newItemMaintenance.
     */
    public void maintenance() {
        int choice = 0;
        int id = 0;
        int qty = 0;
        System.out.println("\nMaintenance\n" +
                "+----------+");
        System.out.print("\n[1] Restock Item\n[2] Add New Item\n~~: ");

        try {
            choice = sc.nextInt();
        } catch (Exception e) {
            input("Please enter one of the following options");
        }

        //calling methods, depending on input
        switch (choice) {
            case 1:
                try {
                    input("Item ID: ");
                    id = sc.nextInt();
                    input("Quantity: ");
                    qty = sc.nextInt();
                } catch (Exception e) {
                    input("Wrong input expected");
                }
                if(id < 0 || id > itemCount) {
                    System.out.println("Error - Item is not valid");
                    return;
                }
                if (stock[id].restock(qty)) {
                    stock[id].setQtyAvailable(qty);
                    input("Item: '" + stock[id].getName() + "', Qty is now updated to: " + stock[id].getQty());
                } else
                    input("Item restock amount is cannot be > 10 & >1, Restock Qty needs to be greater than current amount\nCurrent Qty of '"+stock[id].getName()+"' is: "+stock[id].getQty());
            break;
            case 2: newItemMaintenance();
        }
    }

    /**
     *This method within class will allow the user to add a new item towards the vending machine. The user will
     * get the option to alter the name, price and quantity of the item that will be added.
     * Once the user types these details, this data will be saved and added towards the stock array.
     *
     * However i have added different exceptions that will catch errors for wrong input that is done by the user.
     *
     * @throws IllegalArgumentException
     */
    private void newItemMaintenance() throws IllegalArgumentException {
        boolean validBreak = false;
        double price = 0.0;
        int qty = 0;
        String item = "";

        //Entering name
        System.out.print("Item Name: ");
        sc.nextLine();
        try {
            item = sc.nextLine();
        } catch (Exception e) {
            input("Invalid input expected");
            validBreak = true;
        }
        //Entering price
        System.out.print("Item Price: ");
        try {
            price = sc.nextDouble();
        } catch (Exception e) {
            input("Invalid input expected");
            validBreak = true;
        }

        if (price < 0) {
            input("Price can't be negative");
            validBreak = true;
        }
        if (price % 0.5 != 0) {
            input("Price isn't divisible by the Integer: 5. Price must be nomination of '0.05, '0.01");
            validBreak = true;
        }
        //Entering the quantity of the item.
        System.out.print("~~ Quantity: ");
        try {
            qty = sc.nextInt();
        } catch (Exception e) {
            input("Invalid input expected");
            validBreak = true;
        }
        if (qty > 10) {
            input("Quantity cannot greater than 10");
            validBreak = true;
        }
        if (!validBreak) {
            this.maxItems++;
            //Adding the item
            addNewItem(new VendItem(item, price, qty));
        } else
            input("Input was incorrect, item was not added!");
    }

    /**This method is used to gather all data of the sales made by the vending machine. Therefore, collecting the amount of items that have been sold
     * and total amount of that has been made. From there this data, will be sent towards the 'data.txt' file. Where it display the information
     * in a formatted way.
     *
     * This allows the user to see the data the vending machine has made.
     */
    private void saveTextFileData() throws IOException {
        String print = "";
        Date date = new Date();
        date.getTime();
        PrintWriter writer = new PrintWriter("data.txt");
        String title = date + "\n";
        title += "+------------------------------------------+" + "\n|            Vending Machine Data          |"
                + "\n+------------------------------------------+";

        print = title;
        print += "\n" + "\n               ITEMS SOLD" + "\n+-------------------------------------------+\n";
        print += "   ID    |   QUANTITY SOLD     |    ITEM    \n";
        for (int i = 0; i < itemCount; i++) {
            print += "   " + i + "     |         " + stock[i].getAmountSold() + "           |    " + stock[i].getName() + "\n";
        }
        print += "+-------------------------------------------+\nTOTAL AMOUNT MADE: £"
                + this.totalMoney;
        writer.println(print + "\n");
        writer.close();
    }

    /**The change method is quite important part of the vending machine, as this algorithm will iterate each coin from ascending to descending
     * in amount. Which will test how many times the coin can go into the price, from there it will deduct the amount of times, multiplied  by the coin
     * that fits into it. This process will repeat until the change reaches to zero. From there it will print out the amount of how coins used
     * to give the change for the user, along with the amount of times each coin was used. Which should accurately add up towards the change given  back to the user.
     *
     * However, giving the change back towards the user also depends on the availability of each type of coin. Therefore, the algorithm will find the next best efficient
     * way. However if there isn't enough coins available to add up towards the change due, An error message will appear in the program.
     *
     * @param change
     * @return
     */
    private String change(double change) {
        String res = "";
        ArrayList<Double> list = new ArrayList<>();

        while (change > 0) {
            for (int i = coinType.length - 1; i >= 0; i--) {
                if (change >= coinType[i] && trackCoinsVal.get(coinType[i]) > 0) {
                    int count = (int) (change / coinType[i]);
                    change = round(change - count * coinType[i], 2);
                    res += count + "x " + coinType[i]+"| ";
                    for(int j = 0; j < count-1; i++)
                        trackCoinsVal.put(coinType[i], trackCoinsVal.get(coinType[i]) - 1);
                }
            }
            if (change > 0) {
                res = "The amount of coins available, doesn't create the nomination of the change that is due, Please ask Engineer for support";
                return res;
            }
        }
        return res;
    }


    /**This method is used to read from the 'structure.csv' file. Therefore once the Vending machine object is created, it will check
     * weather if the file exists or not. If it does exist this method will be carried out successfully, depending on the format of the csv file
     * as it needs to formatted correctly. I have used the class Scanner to read the file easily, I have used this object as it easy to use and
     * didn't have to implement methods/or surround with a try catch loop. Therefore, this method reads each line at a time and splits it up into
     * parts using ',' and the split method. The is held within the array, 'sepData[0]' and will tested within a switch case loop. Depending on what is found,
     * the data following that line will be loaded appropriately into the vending machine object.
     *
     * However if the 'structure.csv' file does not exist. The vending machine settings will be default.
     *
     * @throws IOException - This is used to throw an argument exception if the file cannot be found.
     */
    private void onLoadData() throws IOException {
        String file = "structure.csv";
        Scanner read = new Scanner(new FileReader(file));
        while(read.hasNextLine()) {
            String line = read.nextLine();
            String[] sepData = line.split(",");

            switch (sepData[0]) {
                case "owner":
                    setOwner(sepData[1]);
                    break;
                case "maxItems":
                    this.maxItems = Integer.parseInt(sepData[1]);
                    this.stock = new VendItem[maxItems];
                    break;
                case "totalMoney":
                    this.totalMoney = Double.parseDouble(sepData[1]);
                    break;
                case "userMoney":
                    this.userMoney = Double.parseDouble(sepData[1]);
                    break;
                case "status":
                    if (sepData[1].equals("VENDING_MODE"))
                        this.vmStatus = Status.VENDING_MODE;
                    else
                        this.vmStatus = Status.SERVICE_MODE;
                    break;
                case "item":
                    this.stock[itemCount++] = new VendItem(sepData[1], Double.parseDouble(sepData[2]), Integer.parseInt(sepData[3]));
                    break;
                case "Coin": trackCoinsVal.put(Double.parseDouble(sepData[1]), Integer.parseInt(sepData[2]));
            }
        }
    }

    /**
     *This method is used whenever the user is closing down the vending machine program, therefore the data
     * data is currently saved within the structure side of the vending machine, such as the coins, name, items etc.
     * Will be saved towards the 'structure.csv' file in a formatted way. Therefore updating the items, totalMoney or
     * the state of vending machine Therefore, the next time the program is ran, the 'structure.csv' is found and it loads the new data
     * that has been written the last time the program was closed. Such as old data that was previously stored.
     *
     * @throws FileNotFoundException - This is used to check if the file exists or not. If not, it will create one.
     */
    public void onStoreData() throws FileNotFoundException {
        PrintWriter writer = new PrintWriter("structure.csv");

        writer.write("owner,"+this.owner+"\n");
        writer.write("maxItems,"+this.maxItems+"\n");
        writer.write("totalMoney,"+this.totalMoney+"\n");
        writer.write("userMoney,"+this.userMoney+"\n");
        writer.write("status,"+this.vmStatus+"\n");

        for(int i = 0; i < itemCount; i++)
            writer.write("item,"+stock[i].getName()+","+stock[i].getPrice()+","+stock[i].getQty()+"\n");

        for(int i = 0; i < coins.size(); i++)
            writer.write("Coin,"+coinType[i]+","+trackCoinsVal.get(coinType[i])+"\n");
        writer.close();
    }

    /**
     * This method is used to fix the error that was occurring within the change method.
     * As working with precise numbers can cause an error, such '1.4-0.2'. Due to IEEE floating point format,
     * the result of this might end like '1.19999999997' Therefore, using this method has allowed me to round my
     * change to two decimal places.
     *
     * @param value
     * @param places
     * @return
     */
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}