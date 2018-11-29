package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class Shield extends Token
{
	@FXML
	private ImageView shield;
	Shield() throws FileNotFoundException 
	{
		FileInputStream input = new FileInputStream("C:\\Users\\PAVNI\\eclipse-workspace\\SnakeVsBlock\\src\\application\\Shield.jpg");
		Image pic = new Image(input);
		shield = new ImageView();
		shield.setImage(pic);
		shield.setFitHeight(25.0);
		shield.setFitWidth(25.0);
	}
	Shield(double x, double y) throws FileNotFoundException 
	{
		FileInputStream input = new FileInputStream("C:\\Users\\PAVNI\\eclipse-workspace\\SnakeVsBlock\\src\\application\\Shield.jpg");
		Image pic = new Image(input);
		shield = new ImageView();
		shield.setImage(pic);
		shield.setFitHeight(25.0);
		shield.setFitWidth(25.0);
		shield.setLayoutX(x);
		shield.setLayoutY(y);
		
	}
	
	public double getX()
	{
		return shield.getLayoutX();
	}
	public double getY()
	{
		return shield.getLayoutY();
	}
	public ImageView getId() 
	{
		return shield;
	}
	public int setFlag() 
	{
		return 1;
	}
	public void Hit(Snake s,AnchorPane AP) 
	{
		
	}
}
