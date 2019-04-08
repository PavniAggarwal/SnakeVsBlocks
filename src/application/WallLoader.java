package application;

import java.io.Serializable;

public class WallLoader implements Serializable
{

	private int length;
	private double x;
	private double y;
	
	public int getLength() 
	{
		return length;
	}
	public void setLength(int length) 
	{
		this.length = length;
	}
	public double getX() 
	{
		return x;
	}
	public void setX(double x) 
	{
		this.x = x;
	}
	public double getY() 
	{
		return y;
	}
	public void setY(double y) 
	{
		this.y = y;
	}
	
	public void Converter1(Wall w)
	{
		setLength(w.getLength());
		setX(w.getX());
		setY(w.getY());
	}
	public Wall Converter2()
	{
			return new Wall(getLength(),getX(),getY());	
	}
	
}
