package application;

import java.io.Serializable;

import javafx.scene.paint.Color;

public class BlockLoader implements Serializable
{
	
	private String color;
	
	private int value;
	private double x;
	private double y;
	
	public String getColor() 
	{
		return color;
	}
	public void setColor(String color) 
	{
		this.color = color;
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public int getValue() 
	{
		return value;
	}
	public void setValue(int value) 
	{
		this.value = value;
	}
	public void Converter1(Block b)
	{
		setX(b.getId().getLayoutX());
		setY(b.getId().getLayoutY());
		setValue(b.getValue());
		setColor(b.getColor());
	}
	public Block Converter2()
	{
		Block b=new Block(getValue(),getColor(),getX(),getY());
		return b;
	}
	
}
