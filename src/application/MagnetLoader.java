package application;

import java.io.FileNotFoundException;
import java.io.Serializable;

public class MagnetLoader implements Serializable
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
	
	public void Converter1(Magnet m)
	{
		setX(m.getX());
		setY(m.getY());
	}
	public Magnet Converter2()
	{
		return new Magnet(getX(),getY());
	}
	
}
