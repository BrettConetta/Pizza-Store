package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * This class contains every event handler method for the MyPizzaStore fxml file. It
 * declares the JavaFX objects in use in the GUI. Pizza subclass objects are
 * instantiated. Also, this class implements the Initializable interface so that 
 * the ComboBox objects can setValue and setItemscan, and the ListView objects can 
 * setItems and set the SelectionMode to multiple.
 * 
 * @author Brett Conetta, Stephen Prospero
 *
 */
public class PizzaStoreController implements Initializable
{
    @FXML
    private ComboBox<String> pizzaStyle;
    @FXML
    private ComboBox<String> pizzaSize;
    @FXML
    private ImageView pizzaImage;
    @FXML
    private ListView<String> toppings;
    @FXML
    private ListView<String> selectedToppings;
    @FXML
    private Button addToppings;
    @FXML
    private Button removeToppings;
    @FXML
    private Button clearSelection;
    @FXML
    private Button clearMessageBox;
    @FXML
    private Button addToOrder;
    @FXML
    private Button showOrder;
    @FXML
    private TextArea outputTextArea;
    
    private ObservableList<String> pizzaStyleOptions = FXCollections.observableArrayList(BuildYourOwn.BYO_STYLE, Deluxe.DELUXE_STYLE, Hawaiian.HAWAIIAN_STYLE);
    private ObservableList<String> pizzaSizeOptions = FXCollections.observableArrayList(Pizza.SMALL_SIZE, Pizza.MEDIUM_SIZE, Pizza.LARGE_SIZE);
    private ObservableList<String> selectedToppingsList = FXCollections.observableArrayList();
    private ObservableList<String> toppingsList = FXCollections.observableArrayList(Pizza.BEEF_TOPPING, Pizza.CHEESE_TOPPING, Pizza.CHICKEN_TOPPING,
                                                                                    Pizza.GREEN_PEPPER_TOPPING, Pizza.HAM_TOPPING, Pizza.MUSHROOM_TOPPING,
                                                                                    Pizza.ONION_TOPPING, Pizza.PEPPERONI_TOPPING, Pizza.PINEAPPLE_TOPPING, 
                                                                                    Pizza.SAUSAGE_TOPPING);
    
    private Image deluxePizzaImage = new Image("Deluxe_Pizza_Image.png");
    private Image hawaiianPizzaImage = new Image("Hawaiian_Pizza_Image.png");
    private Image buildYourOwnPizzaImage = new Image("BYO_Pizza_Image.jpg");
    
    private ArrayList<Pizza> orderList;
    private Stage orderStage;
    
    /**
     * 
     * @param event
     */
    public void pizzaStyleClicked(ActionEvent event)
    {
        if(this.pizzaStyle.getValue() == BuildYourOwn.BYO_STYLE)
        {
            this.selectedToppingsList.clear();
            this.toppings.setDisable(false);
            this.selectedToppings.setDisable(false);
            this.addToppings.setDisable(false);
            this.clearSelection.setDisable(false);
            this.removeToppings.setDisable(false);
            this.addToOrder.setDisable(true);
            this.pizzaImage.setImage(buildYourOwnPizzaImage);            
        }
        else
        {
            this.toppings.setDisable(true);
            this.addToppings.setDisable(true);
            this.clearSelection.setDisable(true);
            this.removeToppings.setDisable(true);
            
            if(this.pizzaStyle.getValue() == Hawaiian.HAWAIIAN_STYLE)
            {
                this.addToOrder.setDisable(false);
                this.pizzaImage.setImage(hawaiianPizzaImage);
                this.selectedToppingsList.clear();
                this.selectedToppingsList.add(Pizza.HAM_TOPPING);
                this.selectedToppingsList.add(Pizza.PINEAPPLE_TOPPING);
            }
            else
            {
                this.addToOrder.setDisable(false);
                this.pizzaImage.setImage(deluxePizzaImage);
                this.selectedToppingsList.clear();
                this.selectedToppingsList.add(Pizza.GREEN_PEPPER_TOPPING);
                this.selectedToppingsList.add(Pizza.MUSHROOM_TOPPING);
                this.selectedToppingsList.add(Pizza.ONION_TOPPING);
                this. selectedToppingsList.add(Pizza.PEPPERONI_TOPPING);
                this.selectedToppingsList.add(Pizza.SAUSAGE_TOPPING);
            }
        }
    }
    
    public void addToppingsClicked(ActionEvent event)
    {
        ObservableList<String> selectedItems = this.toppings.getSelectionModel().getSelectedItems();
        for(String toppingToAdd : selectedItems)
        {
            boolean isAlreadyAdded = false;
            for(String toppingAlreadyAdded : this.selectedToppingsList)
            {
                if(toppingToAdd.equals(toppingAlreadyAdded))
                {
                    isAlreadyAdded = true;
                }
            }
            if(isAlreadyAdded)
            {
                this.outputTextArea.setText(this.outputTextArea.getText() + toppingToAdd + " is already added.\n");
            }
            else if(this.selectedToppingsList.size() >= BuildYourOwn.BYO_MAX_TOPPING_COUNT)
            {
                this.outputTextArea.setText(this.outputTextArea.getText() + toppingToAdd + " cannot be added. No more than 6 toppings are allowed.\n");
            }
            else
            {
                this.selectedToppingsList.add(toppingToAdd);
            }
            
            if(this.selectedToppingsList.size() > 0)
            {
                this.addToOrder.setDisable(false);
            }
            
        }
    }
    
    public void removeToppingsClicked(ActionEvent event)
    {
        ObservableList<Integer> toppingsToRemove = this.selectedToppings.getSelectionModel().getSelectedIndices();
        ObservableList<String> toppingsToKeep = FXCollections.observableArrayList();
        for(int i = 0; i < selectedToppingsList.size(); ++i)
        {
            if(! toppingsToRemove.contains(i))
            {
                toppingsToKeep.add(this.selectedToppingsList.get(i));
            }
        }
        
        selectedToppingsList.clear();
        for(String topping : toppingsToKeep)
        {
            this.selectedToppingsList.add(topping);
        }
        
        if(selectedToppingsList.size() == 0)
        {
            this.addToOrder.setDisable(true);
        }
    }
    
    public void clearSelectionClicked(ActionEvent event)
    {
        this.selectedToppingsList.clear();
        this.addToOrder.setDisable(true);
    }
    
    public void clearMessageBoxClicked(ActionEvent event)
    {
        this.outputTextArea.clear();
    }
    
    public void addToOrderButtonClicked(ActionEvent event)
    {
        Pizza pizzaToAdd;
        if(this.pizzaStyle.getValue().equals(Deluxe.DELUXE_STYLE))
        {
            pizzaToAdd = new Deluxe(this.pizzaSize.getValue());
            orderList.add(pizzaToAdd);
            this.outputTextArea.setText(this.outputTextArea.getText() + Deluxe.DELUXE_STYLE + " pizza has been added to your order.\n");
        }
        else if(this.pizzaStyle.getValue().equals(Hawaiian.HAWAIIAN_STYLE))
        {
            pizzaToAdd = new Hawaiian(this.pizzaSize.getValue());
            orderList.add(pizzaToAdd);
            this.outputTextArea.setText(this.outputTextArea.getText() + Hawaiian.HAWAIIAN_STYLE + " pizza has been added to your order.\n");
        }
        else
        {
            ArrayList<String> toppingsOnPizza = new ArrayList<String>();
            for(String topping : selectedToppingsList)
            {
                toppingsOnPizza.add(topping);
            }
            
            pizzaToAdd = new BuildYourOwn(this.pizzaSize.getValue(), toppingsOnPizza);
            orderList.add(pizzaToAdd);
            this.outputTextArea.setText(this.outputTextArea.getText() + BuildYourOwn.BYO_STYLE + " pizza has been added to your order.\n");
            
            this.selectedToppingsList.clear();
            this.addToOrder.setDisable(true);
        }
    }
    
    public void showOrderClicked(ActionEvent event)
    {
        try
        {
            orderStage = new Stage();
            orderStage.getIcons().add(new Image("Order_Window_Icon.png"));
            orderStage.setTitle("Order Screen");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("OrderScreen.fxml"));
            loader.load();
            OrderScreenController orderControllerInstance = loader.getController();
            orderControllerInstance.setOrderVars(orderList, orderStage, this);
            AnchorPane orderRoot = loader.getRoot();
            Scene orderScene = new Scene(orderRoot, 561, 582);
            orderScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            orderStage.setScene(orderScene);
            orderStage.show();
            
        }
        catch(Exception e) 
        {
            e.printStackTrace();
        }
    }
    
    public void setOrderList(ArrayList<Pizza> orderList)
    {
        this.orderList = orderList;
    }
    
    public void initialize(URL arg0, ResourceBundle arg1)
    {
        this.pizzaStyle.setValue(BuildYourOwn.BYO_STYLE);
        this.pizzaStyle.setItems(pizzaStyleOptions);
        
        this.pizzaSize.setValue(Pizza.MEDIUM_SIZE);
        this.pizzaSize.setItems(pizzaSizeOptions);
        
        this.toppings.setItems(toppingsList);
        this.toppings.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
        this.selectedToppings.setItems(selectedToppingsList);
        this.selectedToppings.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
        orderList = new ArrayList<Pizza>();
    }
    
}
