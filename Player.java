package application;

import javafx.beans.property.SimpleStringProperty;

public class Player 
{
	private SimpleStringProperty name;
	private SimpleStringProperty rank;
	private SimpleStringProperty score;
	
	public void setName(String n)
	{
		name=new SimpleStringProperty(n);
	}
	public String getName()
	{
		return name.get();
	}
	public void setScore(String s) {
		score = new SimpleStringProperty(s);
	}
	public String getScore() {
		return score.get();
	}
	public void setRank(String n)
	{
		rank=new SimpleStringProperty(n);
	}
	public String getRank()
	{
		return rank.get();
	}
	public Player(String r,String n,String s)
	{
		rank=new SimpleStringProperty(r);
		name=new SimpleStringProperty(n);
		score=new SimpleStringProperty(s);
		
		
	}
}
