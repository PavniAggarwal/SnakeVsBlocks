package application;

import javafx.animation.Transition;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class Snake {
	@FXML
	private Group snake;
	@FXML
	private Text LengthBox;
	@FXML
	private Text ScoreBox;
	private int length;
	private int score;
	private Transition transition;
	Snake(Text t) 
	{
		length = 5;
		score = 0;
		snake = new Group();
		LengthBox = new Text();
		LengthBox.setFill(Color.WHITE);
		LengthBox.setLayoutX(-3.0);
		LengthBox.setLayoutY(-10.0);
		LengthBox.setStrokeWidth(0.0);
		LengthBox.setText(String.valueOf(length));
		snake.getChildren().add(LengthBox);
		Circle c = new Circle(8);
		c.setFill(Color.valueOf("#f4eb37"));
		c.setStroke(Color.BLACK);
		snake.getChildren().add(c);
		for(int i=0;i<length;i++)
		{
			Circle c1 = new Circle(8);
			c1.setFill(Color.valueOf("#f4eb37"));
			c1.setStroke(Color.BLACK);
			c1.setLayoutY((i+1)*15);
			snake.getChildren().add(c1);
		}
		ScoreBox = t;
	}
	Snake(int length,int score,double x,double y)
	{
		setScore(score);
		this.length = length;
		this.score = score;
		snake.setLayoutX(x);
		snake.setLayoutY(y);
		snake = new Group();
		LengthBox = new Text();
		LengthBox.setFill(Color.WHITE);
		LengthBox.setLayoutX(-3.0);
		LengthBox.setLayoutY(-10.0);
		LengthBox.setStrokeWidth(0.0);
		LengthBox.setText(String.valueOf(this.length));
		snake.getChildren().add(LengthBox);
		Circle c = new Circle(8);
		c.setFill(Color.valueOf("#f4eb37"));
		c.setStroke(Color.BLACK);
		snake.getChildren().add(c);
		for(int i=0;i<this.length;i++)
		{
			Circle c1 = new Circle(8);
			c1.setFill(Color.valueOf("#f4eb37"));
			c1.setStroke(Color.BLACK);
			c1.setLayoutY((i+1)*15); 
			snake.getChildren().add(c1);
		}
	}
	
	public double getX()
	{
		return snake.getLayoutX();
	}
	public double getY()
	{
		return snake.getLayoutY();
	}
	public void setLength(int l) {
		length = l-1;
	}
	public int getLength() {
		return length;
	}
	public void setScore(int s) {
		score = s;
		ScoreBox.setText("Score: " +String.valueOf(score));
	}
	public int getScore() {
		return score;
	}
	public Group getId() {
		return snake;
	}
	public void setId(Group s) {
		snake = s;
	}
	public boolean moveRight(int x, int y) {
		return false;
	}
	public boolean moveLeft(int x, int y) {
		return false;
	}
	public void setTransition(Transition tran) {
		transition = tran;
	}
	public Transition getTransition() {
		return transition;
	}
	public void IncLength(int n) {
		for(int i=0;i<n;i++)
		{
			Circle c = new Circle(8);
			c.setLayoutY((length+1)*15);
			c.setFill(Color.valueOf("#f4eb37"));
			c.setStroke(Color.BLACK);
			snake.getChildren().add(c);
			length++;
			LengthBox.setText(String.valueOf(length));
		}
	}
	public void DecLength(int n) {
		for(int i=0;i<n;i++)
		{
			if(length>0)
			{
				snake.getChildren().remove(length+1);
				length--;
				LengthBox.setText(String.valueOf(length));
			}
		}
	}
}