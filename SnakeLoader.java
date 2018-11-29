package application;

import java.io.Serializable;


public class SnakeLoader implements Serializable
{
	private double x;
	private double y;
	private int length;
	private int score;
	
	public int getLength() 
	{
		return length;
	}
	public void setLength(int length) 
	{
		this.length = length;
	}
	public int getScore() 
	{
		return score;
	}
	public void setScore(int score) 
	{
		this.score = score;
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
	
	public void Converter1(Snake s)
	{
		setLength(s.getLength());
		setScore(s.getScore());
		setX(s.getX());
		setY(s.getY());
	}
	public Snake Converter2()
	{
		return new Snake(getLength(),getScore(),getX(),getY());
	}
	
}
