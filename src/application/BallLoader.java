package application;

import java.io.Serializable;

public class BallLoader implements Serializable
{

	private int value;
	private double x;
	private double y;
	
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
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
	
	public void Converter1(Ball b)
	{
		setValue(b.getValue());
		setX(b.getX());
		setY(b.getY());
	}
	public Ball Converter2()
	{
		Ball b=new Ball(getValue(),getX(),getY());
		return b;
	}
	
}
