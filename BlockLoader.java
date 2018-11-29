package application;

import java.io.Serializable;

import javafx.scene.paint.Color;

public class BlockLoader implements Serializable
{
	
	private Color color;
	private int value;
	
	public int getValue() 
	{
		return value;
	}
	public void setValue(int value) 
	{
		this.value = value;
	}
	public Color getColor() 
	{
		return color;
	}
	public void setColor(Color color) 
	{
		this.color = color;
	}
	
	public void Converter1(Block b)
	{
		setValue(b.getValue());
		setColor(b.getColor());
	}
	public Block Converter2()
	{
		Block b=new Block(getValue(),getColor());
		return b;
	}
	
}
