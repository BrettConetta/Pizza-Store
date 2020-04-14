package application;

import java.util.ArrayList;

/**
 * This subclass provides the state and behavior for a deluxe style pizza. It
 * implements the pizzaPrice() method and overrides the toString() method from
 * its Pizza parent class. Constants are provided for the pizza's style and
 * pricing.
 * 
 * @author Brett Conetta, Stephen Prospero
 *
 */
public class Deluxe extends Pizza
{
    public static final String DELUXE_STYLE = "Deluxe";
    public static final int DELUXE_SMALL_PRICE = 14;
    public static final int DELUXE_MEDIUM_PRICE = DELUXE_SMALL_PRICE + 2;
    public static final int DELUXE_LARGE_PRICE = DELUXE_SMALL_PRICE + 4;

    /**
     * This constructor creates a deluxe style using the provided size
     * parameter. It adds the deluxe pizza's preset toppings to its ArrayList
     * toppings instance variable that declared in the Pizza parent class.
     * 
     * @param size String representation of the size of the deluxe pizza
     */
    public Deluxe(String size)
    {
        super(DELUXE_STYLE, size);
        this.toppings = new ArrayList<String>();
        this.toppings.add(Pizza.SAUSAGE_TOPPING);
        this.toppings.add(Pizza.PEPPERONI_TOPPING);
        this.toppings.add(Pizza.GREEN_PEPPER_TOPPING);
        this.toppings.add(Pizza.ONION_TOPPING);
        this.toppings.add(Pizza.MUSHROOM_TOPPING);
    }

    /**
     * This method provides the implementation for the pizzaPrice() declared in
     * the Pizza parent class to calculate the price of the pizza according to
     * the deluxe pricing criteria.
     * 
     * @return integer price of the deluxe pizza or -1 if the pizza's size is
     *         invalid
     */
    public int pizzaPrice()
    {
        if (this.size.equals(Pizza.SMALL_SIZE))
        {
            return DELUXE_SMALL_PRICE;
        }
        else if (this.size.equals(Pizza.MEDIUM_SIZE))
        {
            return DELUXE_MEDIUM_PRICE;
        }
        else if (this.size.equals(Pizza.LARGE_SIZE))
        {
            return DELUXE_LARGE_PRICE;
        }
        else
        {
            return -1;
        }
    }

    /**
     * This method overrides the Pizza parent class's toString() method to
     * append the pricing information to the returned String.
     * 
     * @return String representation of the deluxe pizza that details its style,
     *         size, toppings and price
     */
    public String toString()
    {
        return super.toString() + "\nPrice: $" + this.pizzaPrice();
    }
}
