package application;

import java.io.FileNotFoundException;
import java.io.Serializable;

public class DestroyBlockLoader implements Serializable
{
	
	private double x;
	private double y;
	
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
	
	public void Converter1(DestroyBlock db)
	{
		setX(db.getX());
		setY(db.getY());
	}
	public DestroyBlock Converter2()
	{
		return new DestroyBlock(getX(),getY());
	}
}
