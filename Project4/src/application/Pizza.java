package application;

import java.util.ArrayList;

/**
 * This abstract class provides the state and behavior for an individual pizza
 * pie. Namely, it has constants that denote the size of and toppings for a
 * pizza. Also, it declares a pizzaPrice() method that must be implemented by
 * subclasses since the price of a pizza depends on its specific style and size.
 * toString() and getToppings() methods are also provided so that information
 * about the pizza can be returned as Strings.
 * 
 * @author Brett Conetta, Stephen Prospero
 *
 */
public abstract class Pizza
{
    protected String style;
    protected String size;
    protected ArrayList<String> toppings;

    public static final String LARGE_SIZE = "Large";
    public static final String MEDIUM_SIZE = "Medium";
    public static final String SMALL_SIZE = "Small";

    public static final String BEEF_TOPPING = "Beef";
    public static final String CHEESE_TOPPING = "Cheese";
    public static final String CHICKEN_TOPPING = "Chicken";
    public static final String GREEN_PEPPER_TOPPING = "Green Pepper";
    public static final String HAM_TOPPING = "Ham";
    public static final String MUSHROOM_TOPPING = "Mushroom";
    public static final String ONION_TOPPING = "Onion";
    public static final String PEPPERONI_TOPPING = "Pepperoni";
    public static final String PINEAPPLE_TOPPING = "Pineapple";
    public static final String SAUSAGE_TOPPING = "Sausage";

    /**
     * This constructor creates a Pizza object using the three supplied
     * parameters.
     * 
     * @param style type of pizza
     * @param size size of pizza
     * @param toppings ArrayList object that contains strings representing the
     *        pizza's toppings
     */
    public Pizza(String style, String size, ArrayList<String> toppings)
    {
        this.style = style;
        this.size = size;
        this.toppings = toppings;
    }

    /**
     * This constructor creates a Pizza object using the two supplied
     * parameters.
     * 
     * @param style type of pizza
     * @param size size of pizza
     */
    public Pizza(String style, String size)
    {
        this.style = style;
        this.size = size;
    }

    /**
     * This method calculates a pizza's price, according to its style, size
     * and/or toppings. It must be implemented by subclasses because every style
     * of pizza has different pricing criteria.
     * 
     * @return integer price of the pizza
     */
    public abstract int pizzaPrice();

    /**
     * This method returns a String containing a pizza's style, size and
     * toppings information. Subclasses that override this method append pricing
     * information to the returned String.
     * 
     * @return String representation of the pizza's style, size and toppings
     */
    public String toString()
    {
        return "Style: " + this.style + "  Size: " + this.size + "\nToppings: "
                + this.getToppings();
    }

    /**
     * This method returns the String representation of the ArrayList containing
     * the pizza's toppings
     * 
     * @return String whose contents is the list of the pizza's toppings
     */
    public String getToppings()
    {
        return this.toppings.toString();
    }
}
