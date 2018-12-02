package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class DestroyBlock extends Token
{
	@FXML
	private ImageView db;
	private ArrayList<ChainOfBlocks> list;
	DestroyBlock() throws FileNotFoundException 
	{
		FileInputStream input = new FileInputStream("C:\\Users\\PAVNI\\eclipse-workspace\\SnakeVsBlock\\src\\application\\DestroyBlock.jpg");
		Image pic = new Image(input);
		db = new ImageView();
		db.setImage(pic);
		db.setFitHeight(25.0);
		db.setFitWidth(25.0);
	}
	DestroyBlock(double x,double y) throws FileNotFoundException 
	{
		FileInputStream input = new FileInputStream("C:\\Users\\PAVNI\\eclipse-workspace\\SnakeVsBlock\\src\\application\\DestoyBlock.jpg");
		Image pic = new Image(input);
		db = new ImageView();
		db.setImage(pic);
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
	public void Hit(Snake s,AnchorPane AP) 
	{
		AP.getChildren().remove(db);
		for(int i=0;i<list.size();i++)
		{
			list.get(i).Destroy(s);
		}
		for(int i=0;i<list.size();i++)
		{
			AP.getChildren().remove(list.get(i));
		}
		list = null;
	}
}
