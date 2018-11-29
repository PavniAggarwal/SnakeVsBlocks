package application;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class Ball extends Token{
	@FXML
	Group ball;
	private int value;
	Ball(int v) {
		ball = new Group();
		Text t = new Text();
		t.setFill(Color.WHITE);
		t.setStrokeWidth(0.0);
		t.setText(String.valueOf(v));
		t.setLayoutX(-3.0);
		t.setLayoutY(-10.0);
		ball.getChildren().add(t);
		Circle c = new Circle(8);
		c.setFill(Color.valueOf("#f4eb37"));
		c.setStroke(Color.BLACK);
		ball.getChildren().add(c);
		value = v;
	}
	Ball(int v,double x,double y)
	{
		ball = new Group();
		ball.setLayoutX(x);
		ball.setLayoutY(y);
		Text t = new Text();
		t.setFill(Color.WHITE);
		t.setStrokeWidth(0.0);
		t.setText(String.valueOf(v));
		t.setLayoutX(-3.0);
		t.setLayoutY(-10.0);
		ball.getChildren().add(t);
		Circle c = new Circle(8);
		c.setFill(Color.YELLOW);
		c.setStroke(Color.BLACK);
		c.setLayoutX(x);
		c.setLayoutY(y);
		ball.getChildren().add(c);
		value = v;
	}
	public Group getId() {
		return ball;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int v) {
		value = v;
	}
	public double getX()
	{
		return ball.getLayoutX();
	}
	public double getY()
	{
		return ball.getLayoutY();
	}
	public void Hit(Snake s,AnchorPane AP) {
		s.IncLength(value);
		AP.getChildren().remove(ball);
	}
}
