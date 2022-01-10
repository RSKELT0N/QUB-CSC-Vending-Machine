package Part2;

/**
 * Author: Ryan Skelton, 40266093
 *
 * This interface is used to implement methods towards the VendItems class.
 * Such as the deliver method.
 */
public interface Vendible {
    /**
     *This method is being implemented into the VendItem class. Which will check the
     * qty of each item and determine if a user can buy an item or not.
     * @return - This will return a String of whenever buying an item
     */
    String deliver();
}
