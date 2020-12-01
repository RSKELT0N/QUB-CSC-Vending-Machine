package Part2;

/**
 * Author: Ryan Skelton, 40266093
 * <p>
 * The purpose fo this class is to represent each item that has been added
 * to the vending machine. Which is the 'VendItem' class. The class is quite important as it allows
 * me to create a new object of this class, which holds a name, price and quantity and an itemID. From there
 * I can add these items towards the Vending Machine stock array, therefore I can see and view these items in
 * a formatted way within the program. Allowing the user to see which items are available and how much one
 * costs.
 * <p>
 * I added features towards these class whenever user tries to 'buy' an item. There will be a quantity available,
 * and this will determine weather if this item can be bought or not. As it cannot exceed below '0'.
 * <p>
 * This class implements an interface which basically adds a method towards this class class 'deliver'.
 * The method carry's out the procedure that checks the availability of this item, is still valid
 * for purchase. Which then returns a boolean request back-towards the 'VendingMachine' class, carrying
 * out the purchase.
 */
public class VendItem implements Vendible {

    /**
     * This variable is unique to every item, allows the user specify which item they want
     */
    private int itemID;

    /**
     * The variable increases by 1, each time a new item has been created
     */
    private static int nextID;

    /**
     * Name of the item
     */
    private String name;

    /**
     * This holds how much the item will cost
     */
    private double unitPrice;

    /**
     * This holds how much of item is still available.
     */
    private int qtyAvailable;

    /**
     * This is a variable that is final, therefore doesn't change. This is a condition that
     * stops that item 'qtyAvailable' greater than this value
     */
    private final int MAX_QTY = 10;

    /**
     * This is total amount of sold of each item
     */
    private int amountSold;

    /**
     * This is the default name for an item, if it has not been initialised
     */
    private final String DEFAULT = "Item";

    /**
     * This is the 1st constructor for the VendItem class, therefore with the using this constructor.
     * The user has to add implement the parameters name and unitPrice of the item they are creating.
     * As this constructor doesn't specify a qtyAvailable, if will be set to default 5.
     * <p>
     * I have used some methods within this constructor, such as 'setName(String name)' as this will called
     * towards a method and pass through validation making sure that there is a name valid. If not, the default
     * String will be replaced.
     *
     * @param name      - Name of the item
     * @param unitPrice - Price of the item
     */
    public VendItem(String name, double unitPrice) {
        if (unitPrice > 2)
            throw new IllegalArgumentException("Item Price cant be greater than £2");

        if (unitPrice == 0)
            throw new IllegalArgumentException("Item price can't equal 0");

        if (unitPrice < 0)
            throw new IllegalArgumentException("Item price cannot be a negative Double");


        this.nextID++;
        this.itemID++;
        setName(name);
        setUnitPrice(unitPrice);
        this.qtyAvailable = 5;
    }

    /**
     * This is the second constructor of the VendItem class. This can be called, however the user has to
     * enter the third parameter, 'qtyAvailable'. The constructor is similar to the first, however the qty will
     * be specified by the user and not be set to default by the program. At the start of this method,
     * the first constructor method will called, to carry out the same. As it has the same process, therefore
     * reducing code redundancy.
     * <p>
     * If the qtyAvailable parameter is greater than 10. It will throw exception, however if it is
     * less or equal to 10. The quantity available will be set to this amount.
     *
     * @param name         - Name of the item
     * @param unitPrice    - Price of the item
     * @param qtyAvailable - The amount available of that item
     * @throws IllegalArgumentException
     */
    public VendItem(String name, double unitPrice, int qtyAvailable) throws IllegalArgumentException {
        this(name, unitPrice);

        if (qtyAvailable > 10)
            throw new IllegalArgumentException("Quantity cannot exceed the value: 10");

        if (qtyAvailable <= this.MAX_QTY)
            this.qtyAvailable = qtyAvailable;
    }

    /**
     * This method is used to set the next ID
     */
    public static void setNextID() {
        VendItem.nextID++;
    }

    /**
     * This method is used to set the name with validation.
     * By making sure a name is valid.
     *
     * @param name - Name parameter being passed through
     */
    private void setName(String name) {

        if (name.equals(""))
            this.name = "Item";
        else
            this.name = name;

    }

    /**
     * This method has been created to set the unitPrice of the item, as it will be checked with validation.
     * As this item needs to be in-between that values '0.0' and '2.0'. This price will determine weather how much
     * userMoney will have to be entered for the user to uy this single item.
     *
     * @param unitPrice - The price parameter that has been set.
     * @throws IllegalArgumentException
     */
    private void setUnitPrice(double unitPrice) throws IllegalArgumentException {

        if (unitPrice <= 0 || unitPrice > 2) {
            System.out.println("Item Price cant be negative! | Price cannot be above £2.00");
            return;
        } else {
            this.unitPrice = unitPrice;
        }
    }

    /**
     * This method is used to return the name of the item
     *
     * @return - This will return name of the item
     */
    public String getName() {
        return name;
    }

    /**
     * This method is used to return the price of the item.
     *
     * @return - The price of the item.
     */
    public double getPrice() {
        return unitPrice;
    }

    /**
     * This method is used to return the qtyAvailable of the item.
     *
     * @return - This will return the quantity of the item.
     */
    public int getQty() {
        return qtyAvailable;
    }

    /**
     * This method is implemented from the 'Vendible' interface. The method 'deliver' is used to
     * check the quantity of the item and compare if is there any of the item left. This method is to deliver the item
     * towards the user, once the item is available. It will reduce the item by one.
     *
     * @return
     */
    @Override
    public String deliver() {
        String result;
        //Checking the availability
        if (this.qtyAvailable <= 0)
            result = "Item not Available, '" + this.name + "' Contact Engineer for restock!";
        else {
            //Decreasing amount by 1
            decrement();
            //Printing purchase receipt
            result = "Thanks for purchasing: " + this.name;
            //increase the amount sold
            increaseAmountSold();
        }
        //Returning result
        return result;
    }

    /**
     * This method is used to decrease the qtyAvailable by 1.
     */
    private void decrement() {
        qtyAvailable--;
    }

    /**
     * This method is used to increase the amount sold.
     */
    private void increaseAmountSold() {
        this.amountSold++;
    }

    /**
     * This method is used return the amount sold of each item.
     *
     * @return
     */
    public int getAmountSold() {
        return amountSold;
    }

    /**
     * This method is used to set the qtyAvailable of the each item, this method is found within
     * the engineer level the vending machine. This allows the user to set this variable, however if this qty available,
     * is above the integer 10, then the qtyAvailable is set to 10.
     *
     * @param qtyAvailable
     */
    public void setQtyAvailable(int qtyAvailable) {
        if (qtyAvailable > 10)
            this.qtyAvailable = 10;
        else
            this.qtyAvailable = qtyAvailable;
    }

    /**
     *This method will be called to check if restock is valid, once the that input
     * has been complete, the user will then be prompted. With the choice of how much they want to restock the item by.
     * From there, this parameter will be set on towards the level of that item quantity.
     */
    public boolean restock(int qty) {
        if(qty < 1)
            return false;

        if(qty > 10 || qty < getQty())
            return false;
        else
            return true;
    }
}

