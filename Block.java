package application;

import java.io.IOException;
import java.util.Random;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.Circle;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Block {
	private Color [] Colors = {Color.LIGHTSKYBLUE,Color.CHARTREUSE,Color.CORNFLOWERBLUE,Color.DARKGRAY,Color.DARKSALMON,Color.KHAKI,Color.ORANGE}; 
	@FXML
	private Group block;
	@FXML
	private Group animate;
	@FXML
	private Text tb;
	ParallelTransition p;
	private int value;
	
	Block(int v) {
		block = new Group();
		animate = new Group();
		Circle c1 = new Circle(7);
		c1 = createAnimation(c1);
		Circle c2 = new Circle(7);
		c2 = createAnimation(c2);
		Circle c3 = new Circle(7);
		c3 = createAnimation(c3);
		Circle c4 = new Circle(7);
		c4 = createAnimation(c4);
		Circle c5 = new Circle(7);
		c5 = createAnimation(c5);
		animate.getChildren().add(c1);
		animate.getChildren().add(c2);
		animate.getChildren().add(c3);
		animate.getChildren().add(c4);
		animate.getChildren().add(c5);
		animate.setLayoutX(19.5);
		animate.setLayoutY(10.0);
		block.getChildren().add(animate);
		Rectangle rect = new Rectangle();
		rect.setHeight(45.0);
		rect.setWidth(45.0);
		rect.setArcHeight(5.0);
		rect.setArcWidth(5.0);
		rect.setStroke(Color.BLACK);
		Random rand = new Random();
		int n = rand.nextInt(7);
		rect.setFill(Colors[n]);
		block.getChildren().add(rect);
		tb = new Text();
		if(v<10)
		{
			tb.setLayoutX(17.0);
		}
		else
		{
			tb.setLayoutX(11.0);
		}
		tb.setLayoutY(30.0);
		tb.setStrokeWidth(0.0);
		tb.setStyle("-fx-font-size: 20;");
		tb.setText(String.valueOf(v));
		block.getChildren().add(tb);
		value = v;
		setTransition();
	}
	public Circle createAnimation(Circle c) {
		c.setFill(Color.valueOf("#f44d4d"));
		c.setLayoutX(4.0);
		c.setLayoutY(13.0);
		c.setStroke(Color.BLACK);
		return c;
	}
	public Group getId() {
		return block;
	}
	public void setID(Group b) {
		block = b;
	}
	public void setValue(int v) {
		value = v;
	}
	public int getValue() {
		return value;
	}
	private void setTransition()
	{
		Path path1= new Path();
		path1.getElements().add(new MoveTo(0,0));
		ArcTo arcTo1= new ArcTo();
		arcTo1.setX(50.0);
		arcTo1.setY(50.0);
		arcTo1.setRadiusX(50.0);		
		arcTo1.setRadiusY(50.0);
		path1.getElements().add(arcTo1);
		
		Path path2= new Path();
		path2.getElements().add(new MoveTo(0,0));
		ArcTo arcTo2= new ArcTo();
		arcTo2.setX(50.0);
		arcTo2.setY(-50.0);
		arcTo2.setRadiusX(50.0);		
		arcTo2.setRadiusY(50.0);
		path2.getElements().add(arcTo2);
		
		Path path3= new Path();
		path3.getElements().add(new MoveTo(0,0));
		ArcTo arcTo3= new ArcTo();
		arcTo3.setX(-50.0);
		arcTo3.setY(50.0);
		arcTo3.setRadiusX(50.0);		
		arcTo3.setRadiusY(50.0);
		path3.getElements().add(arcTo3);
		
		Path path4= new Path();
		path4.getElements().add(new MoveTo(0,0));
		ArcTo arcTo4= new ArcTo();
		arcTo4.setX(-50.0);
		arcTo4.setY(-50.0);
		arcTo4.setRadiusX(50.0);		
		arcTo4.setRadiusY(50.0);
		path4.getElements().add(arcTo4);
		
		Path path5= new Path();
		path5.getElements().add(new MoveTo(0,0));
		ArcTo arcTo5= new ArcTo();
		arcTo5.setX(50.0);		
		arcTo5.setY(50.0);
		arcTo5.setRadiusX(50.0);		
		arcTo5.setRadiusY(50.0);
		path5.getElements().add(arcTo5);
				
		PathTransition transition1= new PathTransition();
		transition1.setNode(animate.getChildren().get(1));		
		transition1.setPath(path1);
		transition1.setDuration(Duration.seconds(1));
		
		PathTransition transition2= new PathTransition();
		transition2.setNode(animate.getChildren().get(2));
		transition2.setPath(path2);
		transition2.setDuration(Duration.seconds(1));
		
		PathTransition transition3= new PathTransition();
		transition3.setNode(animate.getChildren().get(3));
		transition3.setPath(path3);
		transition3.setDuration(Duration.seconds(1));
		
		PathTransition transition4= new PathTransition();
		transition4.setNode(animate.getChildren().get(4));
		transition4.setPath(path4);
		transition4.setDuration(Duration.seconds(1));
				
		ScaleTransition S_transition1= new ScaleTransition(Duration.seconds(1),animate.getChildren().get(0));
		S_transition1.setToX(2);
		S_transition1.setToY(2);
		
		ScaleTransition S_transition2= new ScaleTransition(Duration.seconds(1),animate.getChildren().get(1));
		S_transition2.setToX(2);
		S_transition2.setToY(2);
		
		ScaleTransition S_transition3= new ScaleTransition(Duration.seconds(1),animate.getChildren().get(2));
		S_transition3.setToX(2);
		S_transition3.setToY(2);
		
		ScaleTransition S_transition4= new ScaleTransition(Duration.seconds(1),animate.getChildren().get(3));
		S_transition4.setToX(2);
		S_transition4.setToY(2);
		
		ScaleTransition S_transition5= new ScaleTransition(Duration.seconds(1),animate.getChildren().get(4));
		S_transition5.setToX(2);
		S_transition5.setToY(2);
		
		ParallelTransition pt1= new ParallelTransition(transition1,S_transition1);
		
		ParallelTransition pt2= new ParallelTransition(transition2,S_transition2);
		
		ParallelTransition pt3= new ParallelTransition(transition3,S_transition3);
		
		ParallelTransition pt4= new ParallelTransition(transition4,S_transition4);
		
		ParallelTransition pt5= new ParallelTransition(S_transition5);
		
		FadeTransition fadeOut1= new FadeTransition(Duration.seconds(1),animate.getChildren().get(0));
		fadeOut1.setFromValue(1.0);
		fadeOut1.setToValue(0.0);
		
		FadeTransition fadeOut2= new FadeTransition(Duration.seconds(1),animate.getChildren().get(1));
		fadeOut2.setFromValue(1.0);
		fadeOut2.setToValue(0.0);
		
		FadeTransition fadeOut3= new FadeTransition(Duration.seconds(1),animate.getChildren().get(2));
		fadeOut3.setFromValue(1.0);
		fadeOut3.setToValue(0.0);
		
		FadeTransition fadeOut4= new FadeTransition(Duration.seconds(1),animate.getChildren().get(3));
		fadeOut4.setFromValue(1.0);
		fadeOut4.setToValue(0.0);
		
		FadeTransition fadeOut5= new FadeTransition(Duration.seconds(1),animate.getChildren().get(4));
		fadeOut5.setFromValue(1.0);
		fadeOut5.setToValue(0.0);
		
		SequentialTransition sequentialTransition1 = new SequentialTransition(pt1,fadeOut1);
		SequentialTransition sequentialTransition2 = new SequentialTransition(pt2,fadeOut2);
		SequentialTransition sequentialTransition3 = new SequentialTransition(pt3,fadeOut3);
		SequentialTransition sequentialTransition4 = new SequentialTransition(pt4,fadeOut4);
		SequentialTransition sequentialTransition5 = new SequentialTransition(pt5,fadeOut5);
		
		p = new ParallelTransition(sequentialTransition1,sequentialTransition2,sequentialTransition3,sequentialTransition4,sequentialTransition5);
	}
	public void Hit(Snake s,AnchorPane AP) {		
		if(value<=5)
		{
			if(s.getLength()>=value)
			{
				p.play();
				try {
					block.getChildren().remove(2);
					block.getChildren().remove(1);
					}
				catch(IndexOutOfBoundsException e) {}
				p.setOnFinished((e) ->{
				block.getChildren().remove(0);
				});
				s.DecLength(value);
				s.setScore(s.getScore()+value);
			}
			else
			{
				Parent root;
				try {
					root = FXMLLoader.load(getClass().getResource("GameOver.fxml"));
					Stage window = (Stage) AP.getScene().getWindow();
			        window.setScene(new Scene(root, 335, 600));
			        window.show();
				} catch (IOException e) {
				}
				catch(NullPointerException e) {}
			}
		}
		else
		{
			int flag=0;
			if(s.getLength()<value)
			{
				flag = 1;
			}
			final int f = flag;
			s.getTransition().pause();
			for(int i=0;i<value;i++)
			{
				if(s.getLength()>0)
				{
					Timeline t = new Timeline();
					t.getKeyFrames().addAll(
					    new KeyFrame(Duration.seconds(i), actionEvent -> {
					    	for(int j=0;j<value;j++)
					    	{
					    		s.DecLength(1);
					    		value--;
								tb.setText(String.valueOf(value));
								s.setScore(s.getScore()+1);
					    	}	    	
					    	}
					    ),
					    new KeyFrame(Duration.seconds(0.5), actionEvent -> {
					    	if(f==0)
							{
					    		//s.getId().setLayoutY(s.getId().getLayoutY()-5);
								p.play();
								System.out.println();
								try {
								block.getChildren().remove(2);
								block.getChildren().remove(1);
								}
								catch(IndexOutOfBoundsException e) {}
								p.setOnFinished((e) ->{
								block.getChildren().remove(0);
								});
							}
							else
							{
								Parent root;
								try {
									root = FXMLLoader.load(getClass().getResource("GameOver.fxml"));
									Stage window = (Stage) AP.getScene().getWindow();
							        window.setScene(new Scene(root, 335, 600));
							        window.show();
								} catch (IOException e) {
								}
								catch (NullPointerException e) {}
							}
					    })				    
					    //new KeyFrame(Duration.seconds(3), actionEvent -> System.out.println("4"))
					);
					t.playFromStart();
					t.getKeyFrames().add(new KeyFrame(Duration.seconds(1), actionEvent ->{
						s.getTransition().play();
					}
					));
					t.play();
				}
		    }
		}
	}
	public void Destroy(Snake s) {
		p.play();
		block.getChildren().remove(2);
		block.getChildren().remove(1);
		p.setOnFinished((e) ->{
		block.getChildren().remove(0);
		});
		s.setScore(s.getScore()+value);
	}
	public ParallelTransition getAnimation() {
		return p;
	}
}
