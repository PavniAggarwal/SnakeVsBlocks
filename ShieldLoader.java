package application;

import java.io.FileNotFoundException;
import java.io.Serializable;

public class ShieldLoader implements Serializable
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
	public void Converter1(Shield s)
	{
		setX(s.getX());
		setY(s.getY());
	}
	public Shield Converter2()
	{
		try 
		{
			return new Shield(getX(),getY());
		} catch (FileNotFoundException e) 
		{
			System.out.println("Exception in Shield image");
			return null;
		}
	}
	
}
