package application;

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
	Snake(Text t) {
		length = 5;
		score = 0;
		snake = new Group();
		LengthBox = new Text();
		LengthBox.setFill(Color.WHITE);
		LengthBox.setLayoutX(-3.0);
		LengthBox.setLayoutY(-10.0);
		LengthBox.setStrokeWidth(0.0);
		LengthBox.setText(String.valueOf(length-1));
		snake.getChildren().add(LengthBox);
		Circle c = new Circle(8);
		c.setFill(Color.valueOf("#f4eb37"));
		c.setStroke(Color.BLACK);
		snake.getChildren().add(c);
		for(int i=0;i<length-1;i++)
		{
			Circle c1 = new Circle(8);
			c1.setFill(Color.valueOf("#f4eb37"));
			c1.setStroke(Color.BLACK);
			c1.setLayoutY((i+1)*15);
			snake.getChildren().add(c1);
		}
		ScoreBox = t;
	}
	public void setLength(int l) {
		length = l;
	}
	public int getLength() {
		return length;
	}
	public void setScore(int s) {
		score = s;
		ScoreBox.setText(String.valueOf(score));
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
	public void IncLength(int n) {
		for(int i=0;i<n;i++)
		{
			Circle c = new Circle(8);
			c.setLayoutY(length*15);
			c.setFill(Color.valueOf("#f4eb37"));
			c.setStroke(Color.BLACK);
			snake.getChildren().add(c);
			length++;
			LengthBox.setText(String.valueOf(length-1));
		}
	}
	public void DecLength(int n) {
		for(int i=0;i<n;i++)
		{
			if(length>1)
			{
				snake.getChildren().remove(length);
				length--;
				LengthBox.setText(String.valueOf(length-1));
			}
		}
	}
}
