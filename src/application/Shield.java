package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

import org.pdfsam.ui.RingProgressIndicator;

import javafx.animation.Transition;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class Shield extends Token
{
	@FXML
	private ImageView shield;
	Timer t;
	private double[] XCoordinates = {24,25,26,27,28,29,30,31,32};
	Shield() 
	{
		FileInputStream input;
		try {
			input = new FileInputStream("C:\\Users\\PAVNI\\eclipse-workspace\\SnakeVsBlock\\src\\application\\Shield.JPG");
			Image pic = new Image(input);
			shield = new ImageView();
			shield.setImage(pic);
		} catch (FileNotFoundException e) {}
		shield.setFitHeight(20.0);
		shield.setFitWidth(20.0);
		Random rand = new Random();
		int index = rand.nextInt(9);
		int factor = rand.nextInt(7);
		shield.setLayoutX(factor*46 + XCoordinates[index]);
	}
	Shield(double x, double y)
	{
		FileInputStream input;
		try {
			input = new FileInputStream("C:\\Users\\PAVNI\\eclipse-workspace\\SnakeVsBlock\\src\\application\\Shield.JPG");
			Image pic = new Image(input);
			shield = new ImageView();
			shield.setImage(pic);
		} catch (FileNotFoundException e) {}
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
	public void Hit(Snake s,AnchorPane AP,ArrayList<Transition> transitions) 
	{
		AP.getChildren().remove(shield);
		t= new Timer(AP);
		t.startTimer();
		
	}
	public RingProgressIndicator getTimer() {
		return t.getRingProgressIndicator();
	}
}
