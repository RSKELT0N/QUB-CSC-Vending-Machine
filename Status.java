package Part2;

/**
 * Author: Ryan Skelton, 40266093
 * The purpose of this class is to represent the status class within
 * the specification. As this class will create the enumeration for the status of
 * the vending machine. Such as 'Vending mode' and 'Service Mode'. As the status/enum that is selected within
 * the vending machine is set to a certain enum. This will determine how the vending machine functions.
 *
 */
public enum Status {
    VENDING_MODE("Vending Mode"),
    SERVICE_MODE("Service Mode");

    /**This variable will hold the String for the Status, depending on what is selected.*/
    String name;

    /**
     *This is the constructor is the Status enumeration class, when an object of this class
     * is called. Depending on which enumeration is chosen, this will return String that is stored within
     * the memory of each enum.
     * @param i - This is string/enum that will be inputted. Sets 'i' to this String.
     */
    Status(String i) {
        this.name = i;
    }

    /**
     * This method is used to return the type of status that is Stored within the 'i' String.
     * @return - This will return the name the Status chosen.
     */
    public String getStatus() {
        return name;
    }
}
