package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.stage.Stage;


/**
 * This class contains every event handler method for the OrderScreen fxml file. It
 * declares the JavaFX objects in use in the GUI. The ArrayList<Pizza> is taken in, 
 * along with the stage for the GUI, and an instance of PizzaStoreController in 
 * setOrderVars() to show the current orders and calculate the total price.
 * 
 * @author Brett Conetta, Stephen Prospero
 *
 */
public class OrderScreenController implements Initializable
{

	@FXML
	private Button clearOrder;
	@FXML
	private Button clearSelected;
	@FXML
	private Button back;
	@FXML
	private ListView<String> pizzaOrdersListView;
	@FXML
	private Label price;

	private ObservableList<String> stringOrderList = FXCollections.observableArrayList();

	private ArrayList<Pizza> orderList;
	private Stage orderStage;
	private PizzaStoreController pizzaStoreInstance;

	public static final String emptyMessage = "Your order is empty. Are you not hungry? :-(";

	/**
	 * This method clears the orderList that holds all of the Pizza objects and clears
	 * the stringOrderList that populates the ListView which displays the pizzas. It also
	 * displays a message teling the user that the order is empty and disables the "clear order"
	 * and "clear selected" buttons. The price changes to $0 when the order is cleared and the empty 
	 * Pizza ArrayList is sent back to the PizzaStoreController
	 * 
	 * @param event ActionEvent objec that triggers this method.
	 */
	public void clearOrderClicked(ActionEvent event)
	{
		this.stringOrderList.clear();
		this.orderList.clear();

		this.stringOrderList.add(emptyMessage);
		this.clearOrder.setDisable(true);
		this.clearSelected.setDisable(true);

		double totalPrice = 0;

		this.price.setText(String.format("%.2f", totalPrice));

		this.pizzaStoreInstance.setOrderList(this.orderList);
	}

	/**
	 * This method removes the selected Pizza objects from the orderList that holds all of the Pizza 
	 * objects and removes the selected String objects from the stringOrderList that populates the 
	 * ListView which displays the pizzas. If the orderList is empty, it displays a message telling the 
	 * user that the order is empty and disables the "clear order" and "clear selected" buttons. 
	 * The price is recalculated without the price of the Pizza objects that will be removed and the 
	 * updated Pizza ArrayList is sent back to the PizzaStoreController.
     * 
     * @param event ActionEvent object that triggers this method.
	 */
	public void clearSelectedClicked(ActionEvent event)
	{
		ObservableList<Integer> indicesToRemove = this.pizzaOrdersListView
				.getSelectionModel().getSelectedIndices();
		ArrayList<Pizza> pizzasToKeep = new ArrayList<Pizza>();

		for (int i = 0; i < this.orderList.size(); ++i)
		{
			if (!indicesToRemove.contains(i))
			{
				pizzasToKeep.add(this.orderList.get(i));
			}
		}

		this.orderList = pizzasToKeep;

		this.stringOrderList.clear();
		if (this.orderList.size() == 0)
		{
			this.stringOrderList.add(emptyMessage);
			this.clearOrder.setDisable(true);
			this.clearSelected.setDisable(true);
		}
		else
		{
			for (Pizza pizza : this.orderList)
			{
				this.stringOrderList.add(pizza.toString());
			}
		}

		double totalPrice = 0;
		for (Pizza currentPie : this.orderList)
		{
			totalPrice += currentPie.pizzaPrice();
		}

		this.price.setText(String.format("%.2f", totalPrice));

		this.pizzaStoreInstance.setOrderList(this.orderList);
	}

	/**
	 * This method closes the Stage object that uses this controller.
	 * @param event ActionEvent objec that triggers this method.
	 */
	public void backClicked(ActionEvent event)
	{
		this.orderStage.close();
	}

	/**
	 * This method takes in the current ArrayList of Pizza objects, the Stage object that is the order screen, 
	 * and an instance of the PizzaStoreController. It populates the stringOrderList and sets the ListView to use 
	 * that stringOrderList. It also calculates the initial price of the order when the stage is loaded.
	 * @param orderList An ArrayList<Pizza> which holds the Pizza objects that have been added to the order.
	 * @param orderStage A Stage object which called setScene() to load the order screen. Used to close the Stage
	 * upon back button pressed.
	 * @param pizzaStoreInstance PizzaStoreController object which started up the order screen. Used to return the 
	 * updated orderList to the PizzaStoreController.
	 */
	public void setOrderVars(ArrayList<Pizza> orderList, Stage orderStage,
			PizzaStoreController pizzaStoreInstance)
	{
		this.orderList = orderList;
		this.orderStage = orderStage;
		this.pizzaStoreInstance = pizzaStoreInstance;

		if (this.orderList.size() == 0)
		{
			this.stringOrderList.add(emptyMessage);
			this.clearOrder.setDisable(true);
			this.clearSelected.setDisable(true);
		}
		else
		{
			for (int i = 0; i < this.orderList.size(); ++i)
			{
				this.stringOrderList.add(this.orderList.get(i).toString());
			}
		}

		this.pizzaOrdersListView.setItems(this.stringOrderList);
		this.pizzaOrdersListView.getSelectionModel()
				.setSelectionMode(SelectionMode.MULTIPLE);

		double totalPrice = 0;
		for (Pizza currentPie : this.orderList)
		{
			totalPrice += currentPie.pizzaPrice();
		}

		this.price.setText(String.format("%.2f", totalPrice));
	}

	/**
	 * This method is required to be included in order to implement Initializable.
	 */
	public void initialize(URL arg0, ResourceBundle arg1)
	{

	}
}
