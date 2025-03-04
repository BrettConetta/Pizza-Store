package application;

import java.awt.Taskbar;
import java.awt.Toolkit;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

public class Main extends Application
{
	@Override
	public void start(Stage primaryStage)
	{
		try
		{
			try
			{
				Taskbar.getTaskbar().setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getClassLoader().getResource("Pizza_Window_Icon.png")));
			} 
			catch (final UnsupportedOperationException e)
			{
				
			} 
			catch (final SecurityException e)
			{
				
			}

			primaryStage.getIcons().add(new Image("Pizza_Window_Icon.png"));
			primaryStage.setTitle("My Pizza Store");
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("MyPizzaStore.fxml"));
			Scene scene = new Scene(root, 610, 625);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void main(String[] args)
	{
		launch(args);
	}
}
