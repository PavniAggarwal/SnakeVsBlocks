package application;

import java.util.Random;

import javafx.fxml.FXML;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Wall {
	@FXML
	private Rectangle wall;
	private int lengthOfWall;
	double[] positions = {26.0,72.0,118.0,164.0,210.0,256.0,302.0};
	Wall(int l) 
	{
		lengthOfWall = l;
		wall = new Rectangle();
		wall.setFill(Color.WHITE);
		wall.setHeight(l);
		wall.setWidth(8);
		Random rand = new Random();
		int index = rand.nextInt(7);
		wall.setLayoutX(positions[index]);
	}
	Wall(int l,double x,double y) 
	{
		lengthOfWall = l;
		wall = new Rectangle();
		wall.setLayoutX(x);
		wall.setLayoutY(y);
		wall.setFill(Color.WHITE);
		wall.setHeight(l);
		wall.setWidth(8);
		Random rand = new Random();
		int index = rand.nextInt(7);
		wall.setLayoutX(positions[index]);
	}
	
	public double getX()
	{
		return wall.getLayoutX();
	}
	public double getY()
	{
		return wall.getLayoutY();
	}
	public Rectangle getId() {
		return wall;
	}
	public void setLength(int l) {
		lengthOfWall = l;
	}
	public int getLength() {
		return lengthOfWall;
	}
}
