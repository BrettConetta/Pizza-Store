package application;

import java.util.ArrayList;

/**
 * This subclass provides the state and behavior for a Hawaiian style pizza. It
 * implements the pizzaPrice() method and overrides the toString() method from
 * its Pizza parent class. Constants are provided for the pizza's style and
 * pricing.
 * 
 * @author Brett Conetta, Stephen Prospero
 *
 */
public class Hawaiian extends Pizza
{
    public static final String HAWAIIAN_STYLE = "Hawaiian";
    public static final int HAWAIIAN_SMALL_PRICE = 8;
    public static final int HAWAIIAN_MEDIUM_PRICE = HAWAIIAN_SMALL_PRICE + 2;
    public static final int HAWAIIAN_LARGE_PRICE = HAWAIIAN_SMALL_PRICE + 4;

    /**
     * This constructor creates a Hawaiian style using the provided size
     * parameter. It adds the Hawaiian pizza's preset toppings to its ArrayList
     * toppings instance variable that declared in the Pizza parent class.
     * 
     * @param size String representation of the size of the Hawaiian pizza
     */
    public Hawaiian(String size)
    {
        super(HAWAIIAN_STYLE, size);
        this.toppings = new ArrayList<String>();
        this.toppings.add(Pizza.HAM_TOPPING);
        this.toppings.add(Pizza.PINEAPPLE_TOPPING);
    }

    /**
     * This method provides the implementation for the pizzaPrice() method
     * declared in the Pizza parent class to calculate the price of the pizza
     * according to the Hawaiian pricing criteria.
     * 
     * @return integer price of the Hawaiian pizza or -1 if the pizza's size is
     *         invalid
     */
    public int pizzaPrice()
    {
        if (this.size.equals(Pizza.SMALL_SIZE))
        {
            return HAWAIIAN_SMALL_PRICE;
        }
        else if (this.size.equals(Pizza.MEDIUM_SIZE))
        {
            return HAWAIIAN_MEDIUM_PRICE;
        }
        else if (this.size.equals(Pizza.LARGE_SIZE))
        {
            return HAWAIIAN_LARGE_PRICE;
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
     * @return String representation of the Hawaiian pizza that details its
     *         style, size, toppings and price
     */
    public String toString()
    {
        return super.toString() + "\nPrice: $" + this.pizzaPrice();
    }

    public static void main(String[] args)
    {
        Pizza ween = new Deluxe("Large");
        System.out.println(ween);
    }
}
