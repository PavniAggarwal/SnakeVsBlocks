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

public class DestroyBlock extends Token
{
	@FXML
	private ImageView db;
	private ArrayList<ChainOfBlocks> list;
	private double[] XCoordinates = {24,25,26,27,28,29,30,31,32};
	DestroyBlock()
	{
		FileInputStream input;
		try {
			input = new FileInputStream("C:\\Users\\PAVNI\\eclipse-workspace\\SnakeVsBlock\\src\\application\\DestroyBlock.jpg");
			Image pic = new Image(input);
			db = new ImageView();
			db.setImage(pic);
		} catch (FileNotFoundException e) {}
		db.setFitHeight(20.0);
		db.setFitWidth(20.0);
		Random rand = new Random();
		int index = rand.nextInt(9);
		int factor = rand.nextInt(7);
		db.setLayoutX(factor*46 + XCoordinates[index]);
	}
	DestroyBlock(double x,double y)
	{
		FileInputStream input;
		try {
			input = new FileInputStream("C:\\Users\\PAVNI\\eclipse-workspace\\SnakeVsBlock\\src\\application\\DestoyBlock.jpg");
			Image pic = new Image(input);
			db = new ImageView();
			db.setImage(pic);
		} catch (FileNotFoundException e) {}
		db.setFitHeight(25.0);
		db.setFitWidth(25.0);
		db.setLayoutX(x);
		db.setLayoutY(y);
	}
	public double getX()
	{
		return db.getLayoutX();
	}
	public double getY()
	{
		return db.getLayoutY();
	}
	public void setList(ArrayList<ChainOfBlocks> arr) 
	{
		list = arr;
	}
	public ImageView getId() 
	{
		return db;
	}
	@SuppressWarnings("unlikely-arg-type")
	@Override
	public void Hit(Snake s, AnchorPane AP, ArrayList<Transition> transitions) {
		for(int i=0;i<list.size();i++)
		{
			list.get(i).Destroy(s);
		}
		for(int i=0;i<list.size();i++)
		{
			AP.getChildren().remove(list.get(i));
		}
		AP.getChildren().remove(db);
		list = null;		
	}
}