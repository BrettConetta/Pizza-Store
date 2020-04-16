package application;

import java.util.ArrayList;

/**
 * This subclass provides the state and behavior for a Build Your Own style
 * pizza. It implements the pizzaPrice() method and overrides the toString()
 * method from its Pizza parent class. Constants are provided for the pizza's
 * style and pricing.
 * 
 * @author Brett Conetta, Stephen Prospero
 *
 */
public class BuildYourOwn extends Pizza
{
    public static final String BYO_STYLE = "Build Your Own";
    public static final int BYO_SMALL_BASE_PRICE = 5;
    public static final int BYO_MEDIUM_BASE_PRICE = BYO_SMALL_BASE_PRICE + 2;
    public static final int BYO_LARGE_BASE_PRICE = BYO_SMALL_BASE_PRICE + 4;
    public static final int BYO_TOPPING_PRICE = 2;
    public static final int BYO_MAX_TOPPING_COUNT = 6;

    /**
     * This constructor creates a Build You Own style using the provided size
     * and toppings parameters.
     * 
     * @param size String representation of the size of the Build Your Own pizza
     * @param toppings ArrayList object that contains the user-selected toppings
     *        for the pizza
     */
    public BuildYourOwn(String size, ArrayList<String> toppings)
    {
        super(BYO_STYLE, size, toppings);
    }

    /**
     * This method provides the implementation for the pizzaPrice() declared in
     * the Pizza parent class to calculate the price of the pizza according to
     * the Build Your Own pricing criteria. At least one topping is required for
     * a Build Your Own style pizza and each topping has an additional cost.
     * 
     * @return integer price of the Build Your Own pizza, -1 if the pizza's
     *         number of toppings is not in the range of [1,6] or -1 if the
     *         pizza's size is invalid
     */
    public int pizzaPrice()
    {
        int toppingCount = this.toppings.size();
        if (toppingCount < 1 || toppingCount > BYO_MAX_TOPPING_COUNT)
        {
            return -1;
        }

        int toppingsPrice = toppingCount * BYO_TOPPING_PRICE;

        if (this.size.equals(Pizza.SMALL_SIZE))
        {
            return BYO_SMALL_BASE_PRICE + toppingsPrice;
        }
        else if (this.size.equals(Pizza.MEDIUM_SIZE))
        {
            return BYO_MEDIUM_BASE_PRICE + toppingsPrice;
        }
        else if (this.size.equals(Pizza.LARGE_SIZE))
        {
            return BYO_LARGE_BASE_PRICE + toppingsPrice;
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
     * @return String representation of the Build Your Own pizza that details
     *         its style, size, toppings and price
     */
    public String toString()
    {
        return super.toString() + "\nPrice: $" + this.pizzaPrice();
    }
}
