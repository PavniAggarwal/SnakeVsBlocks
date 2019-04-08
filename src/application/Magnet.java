package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

import javafx.animation.Transition;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class Magnet extends Token{
	@FXML
	private ImageView magnet;
	private ArrayList<Ball> list = new ArrayList<Ball>();
	private double[] XCoordinates = {24,25,26,27,28,29,30,31,32};
	Magnet() 
	{
		FileInputStream input;
		try {
			input = new FileInputStream("C:\\Users\\PAVNI\\eclipse-workspace\\SnakeVsBlock\\src\\application\\Magnet.jpg");
			Image pic = new Image(input);
			magnet = new ImageView();
			magnet.setImage(pic);
		} catch (FileNotFoundException e) {}
		magnet.setFitHeight(20.0);
		magnet.setFitWidth(20.0);
		Random rand = new Random();
		int index = rand.nextInt(9);
		int factor = rand.nextInt(7);
		magnet.setLayoutX(factor*46 + XCoordinates[index]);
	}
	Magnet(double x,double y) 
	{
		FileInputStream input;
		try {
			input = new FileInputStream("C:\\Users\\PAVNI\\eclipse-workspace\\SnakeVsBlock\\src\\application\\Magnet.jpg");
			Image pic = new Image(input);
			magnet = new ImageView();
			magnet.setImage(pic);
		} catch (FileNotFoundException e) {}
		magnet.setFitHeight(25.0);
		magnet.setFitWidth(25.0);
		magnet.setLayoutX(x);
		magnet.setLayoutY(y);
	}
	
	public double getX()
	{
		return magnet.getLayoutX();
	}
	public double getY()
	{
		return magnet.getLayoutY();
	}
	public ImageView getId() {
		return magnet;
	}
	public void setList(ArrayList<Ball> arr) {
		list = arr;
	}
	public void Hit(Snake s,AnchorPane AP, ArrayList<Transition> transitions) {
		AP.getChildren().remove(magnet);
		for(int i=0;i<list.size();i++)
		{
			if(list.get(i).getId().getLayoutX()>magnet.getLayoutX()-50 && list.get(i).getId().getLayoutX()<magnet.getLayoutX()+71 && list.get(i).getId().getLayoutY()>magnet.getLayoutY()-50 && list.get(i).getId().getLayoutY()<magnet.getLayoutY()+71)
			{
				list.get(i).Hit(s,AP,transitions);
				list.remove(i);
			}
		}
	}
}
