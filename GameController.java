package application;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;

import com.sun.javafx.geom.Vec2d;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.Circle;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.VLineTo;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameController implements Initializable
{
	@FXML
	private Group snake;
	private Ball b;
	@FXML
	private Text SnakeLength;
	@FXML
	private Text status;
	@FXML
	private Text Score;
	@FXML
	private Circle c11;
	@FXML
	private Circle c12;
	@FXML
	private Circle c13;
	@FXML
	private Circle c14;
	@FXML
	private Circle c15;
	@FXML
	private Circle c21;
	@FXML
	private Circle c22;
	@FXML
	private Circle c23;
	@FXML
	private Circle c24;
	@FXML
	private Circle c25;
	@FXML
	private Circle c31;
	@FXML
	private Circle c32;
	@FXML
	private Circle c33;
	@FXML
	private Circle c34;
	@FXML
	private Circle c35;
	@FXML
	private Circle c41;
	@FXML
	private Circle c42;
	@FXML
	private Circle c43;
	@FXML
	private Circle c44;
	@FXML
	private Circle c45;
	@FXML
	private Circle c51;
	@FXML
	private Circle c52;
	@FXML
	private Circle c53;
	@FXML
	private Circle c54;
	@FXML
	private Circle c55;
	@FXML
	private Circle c61;
	@FXML
	private Circle c62;
	@FXML
	private Circle c63;
	@FXML
	private Circle c64;
	@FXML
	private Circle c65;
	@FXML
	private AnchorPane AP;
	@FXML
	private AnchorPane AP2;
	@FXML
	private Group g1;
	@FXML
	private Group b1;
	@FXML
	private Group g2;
	@FXML
	private Group b2;
	@FXML
	private Group g3;
	@FXML
	private Group b3;
	@FXML
	private Group g4;
	@FXML
	private Group b4;
	@FXML
	private Group g5;
	@FXML
	private Group b5;
	@FXML
	private Group g6;
	@FXML
	private Group b6;
	Vec2d location;
	ParallelTransition p1;
	ParallelTransition p2;
	ParallelTransition p3;
	ParallelTransition p4;
	ParallelTransition p5;
	ParallelTransition p6;
	PathTransition transition;
	Boolean transitionIsPlay;
	Ball ball;
	ChainOfBlocks c;
	DestroyBlock d;
	Magnet m;
	Game game;
	Shield s;
	int ShieldFlag = 0;
	int GameOverFlag = 0;
	ArrayList<Token> tokens = new ArrayList<Token>();
	@Override
	public void initialize(URL url, ResourceBundle rb)
	{
		game = new Game();
		Snake snk = new Snake(Score);
		snk.getId().setLayoutX(200);
		snk.getId().setLayoutY(450);
		AP.getChildren().add(snk.getId());
		game.setSnake(snk);
		Path path= new Path();
		path.getElements().add(new MoveTo(0,0));
		path.getElements().add(new VLineTo(-600));
		//Line line = new Line();
		//line.setStartX(0.0f);
		//line.setStartY(0.0f);
		//line.setEndY(-600.0f);

		transition= new PathTransition();
		transition.setNode(game.getSnake().getId());
		transition.setDuration(Duration.seconds(5));
		transition.setPath(path);
		transition.play();
		transitionIsPlay = true;
		Timeline timeline = new Timeline(new KeyFrame(
				Duration.millis(50),
				ae-> {
					try {
						onHit();
					} catch (IOException e1) {}
				}));
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
		try {
			createDisplay();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
		}
	}
	@FXML
	public void createDisplay() throws FileNotFoundException {
		b = new Ball(2);
		b.getId().setLayoutX(150);
		b.getId().setLayoutY(500);
		AP.getChildren().add(b.getId());
		tokens.add(b);
		ball = new Ball(2);
		ball.getId().setLayoutX(260);
		ball.getId().setLayoutY(360);
		AP.getChildren().add(ball.getId());
		tokens.add(ball);
		c = new ChainOfBlocks(5,game.getSnake().getLength());
		c.getId().setLayoutX(0);
		c.getId().setLayoutY(100);
		AP.getChildren().add(c.getId());
		tokens.add(c);
//		Wall wall = new Wall(100);
//		wall.getId().setLayoutY(132);
//		AP.getChildren().add(wall.getId());
		ArrayList<Ball> arr2 = new ArrayList<Ball>();
		arr2.add(b);
		arr2.add(ball);
		m = new Magnet();
		m.getId().setLayoutY(300);
		m.getId().setLayoutX(190);
		m.setList(arr2);
		AP.getChildren().add(m.getId());
		tokens.add(m);
		s = new Shield();
		s.getId().setLayoutY(500);
		s.getId().setLayoutX(230);
		AP.getChildren().add(s.getId());
		tokens.add(s);
//		if(s.getClass().equals(Shield.class))
//		{
//			System.out.println("Same");
//		}
		ArrayList<ChainOfBlocks> arr = new ArrayList<ChainOfBlocks>();
		arr.add(c);
//		d = new DestroyBlock();
//		d.getId().setLayoutY(300);
//		d.getId().setLayoutX(200);
//		d.setList(arr);
//		AP.getChildren().add(d.getId());
//		tokens.add(d);
	}
	@FXML
	private void displayPosition(MouseEvent event)
	{
		status.setText("X = " + event.getX() + "  ;   Y = " + event.getY());
	}
	@FXML
	void onPause(ActionEvent event) throws IOException{
		Parent ButtonClicked = FXMLLoader.load(getClass().getResource("PauseDialogBox.fxml"));
    	Scene s = new Scene(ButtonClicked,335,365);
    	Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
    	window.setScene(s);
    	window.show();
	}
	@FXML
	public void GameOver() throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("GameOver.fxml"));
		Stage window = new Stage();
        window.setScene(new Scene(root, 335, 600));
        window.show();
	}
	@FXML
	private void setTransition1()
	{
		//for button1
		Path path1= new Path();
		path1.getElements().add(new MoveTo(0,0));
		ArcTo arcTo1= new ArcTo();
		arcTo1.setX(50.0);		//change the following coordinates for different paths
		arcTo1.setY(50.0);
		arcTo1.setRadiusX(50.0);		
		arcTo1.setRadiusY(50.0);
		path1.getElements().add(arcTo1);
		
		//for button2
		Path path2= new Path();
		path2.getElements().add(new MoveTo(0,0));
		ArcTo arcTo2= new ArcTo();
		arcTo2.setX(50.0);		//change the following coordinates for different paths
		arcTo2.setY(-50.0);
		arcTo2.setRadiusX(50.0);		
		arcTo2.setRadiusY(50.0);
		path2.getElements().add(arcTo2);
		
		//for button3
		Path path3= new Path();
		path3.getElements().add(new MoveTo(0,0));
		ArcTo arcTo3= new ArcTo();
		arcTo3.setX(-50.0);		//change the following coordinates for different paths
		arcTo3.setY(50.0);
		arcTo3.setRadiusX(50.0);		
		arcTo3.setRadiusY(50.0);
		path3.getElements().add(arcTo3);
		
		//for button4
		Path path4= new Path();
		path4.getElements().add(new MoveTo(0,0));
		ArcTo arcTo4= new ArcTo();
		arcTo4.setX(-50.0);		//change the following coordinates for different paths
		arcTo4.setY(-50.0);
		arcTo4.setRadiusX(50.0);		
		arcTo4.setRadiusY(50.0);
		path4.getElements().add(arcTo4);
		
		//for button5
		Path path5= new Path();
		path5.getElements().add(new MoveTo(0,0));
		ArcTo arcTo5= new ArcTo();
		arcTo5.setX(50.0);		//change the following coordinates for different paths
		arcTo5.setY(50.0);
		arcTo5.setRadiusX(50.0);		
		arcTo5.setRadiusY(50.0);
		path5.getElements().add(arcTo5);
				
		PathTransition transition1= new PathTransition();
		transition1.setNode(c11);		//change the button to circle name from the controller
		transition1.setPath(path1);
		transition1.setDuration(Duration.seconds(1));
		
		PathTransition transition2= new PathTransition();
		transition2.setNode(c12);		//change the button to circle name from the controller
		transition2.setPath(path2);
		transition2.setDuration(Duration.seconds(1));
		
		PathTransition transition3= new PathTransition();
		transition3.setNode(c13);		//change the button to circle name from the controller
		transition3.setPath(path3);
		transition3.setDuration(Duration.seconds(1));
		
		PathTransition transition4= new PathTransition();
		transition4.setNode(c14);		//change the button to circle name from the controller
		transition4.setPath(path4);
		transition4.setDuration(Duration.seconds(1));
				
		ScaleTransition S_transition1= new ScaleTransition(Duration.seconds(1),c11);
		S_transition1.setToX(2);
		S_transition1.setToY(2);
		
		ScaleTransition S_transition2= new ScaleTransition(Duration.seconds(1),c12);
		S_transition2.setToX(2);
		S_transition2.setToY(2);
		
		ScaleTransition S_transition3= new ScaleTransition(Duration.seconds(1),c13);
		S_transition3.setToX(2);
		S_transition3.setToY(2);
		
		ScaleTransition S_transition4= new ScaleTransition(Duration.seconds(1),c14);
		S_transition4.setToX(2);
		S_transition4.setToY(2);
		
		ScaleTransition S_transition5= new ScaleTransition(Duration.seconds(1),c15);
		S_transition5.setToX(2);
		S_transition5.setToY(2);
		
		ParallelTransition pt1= new ParallelTransition(transition1,S_transition1);
		
		ParallelTransition pt2= new ParallelTransition(transition2,S_transition2);
		
		ParallelTransition pt3= new ParallelTransition(transition3,S_transition3);
		
		ParallelTransition pt4= new ParallelTransition(transition4,S_transition4);
		
		ParallelTransition pt5= new ParallelTransition(S_transition5);
		
		FadeTransition fadeOut1= new FadeTransition(Duration.seconds(1),c11);
		fadeOut1.setFromValue(1.0);
		fadeOut1.setToValue(0.0);
		
		FadeTransition fadeOut2= new FadeTransition(Duration.seconds(1),c12);
		fadeOut2.setFromValue(1.0);
		fadeOut2.setToValue(0.0);
		
		FadeTransition fadeOut3= new FadeTransition(Duration.seconds(1),c13);
		fadeOut3.setFromValue(1.0);
		fadeOut3.setToValue(0.0);
		
		FadeTransition fadeOut4= new FadeTransition(Duration.seconds(1),c14);
		fadeOut4.setFromValue(1.0);
		fadeOut4.setToValue(0.0);
		
		FadeTransition fadeOut5= new FadeTransition(Duration.seconds(1),c15);
		fadeOut5.setFromValue(1.0);
		fadeOut5.setToValue(0.0);
		
		SequentialTransition sequentialTransition1 = new SequentialTransition(pt1,fadeOut1);
		SequentialTransition sequentialTransition2 = new SequentialTransition(pt2,fadeOut2);
		SequentialTransition sequentialTransition3 = new SequentialTransition(pt3,fadeOut3);
		SequentialTransition sequentialTransition4 = new SequentialTransition(pt4,fadeOut4);
		SequentialTransition sequentialTransition5 = new SequentialTransition(pt5,fadeOut5);
		
		p1 = new ParallelTransition(sequentialTransition1,sequentialTransition2,sequentialTransition3,sequentialTransition4,sequentialTransition5);
	}

	@FXML
	void onHit() throws IOException {
		for(int i=0;i<tokens.size();i++)
		{
			Token token = tokens.get(i);
			if(game.getSnake().getId().getBoundsInParent().intersects(token.getId().getBoundsInParent()))
			{
				if(token.getClass().equals(ChainOfBlocks.class))
				{
					ChainOfBlocks c = (ChainOfBlocks) token;
					if(ShieldFlag==1)
					{
						c.HitWithShield(game.getSnake(), AP);
					}
					else
					{
						c.Hit(game.getSnake(), AP);
					}
					tokens.remove(token);
					System.out.println(token.getClass());
					GameOverFlag = c.getGameOverFlag();
					if(GameOverFlag==1)
					{
						GameOver();
					}
				}
				if(token.getClass().equals(Shield.class))
				{
					ShieldFlag = 1;
				}
				tokens.remove(token);
				token.Hit(game.getSnake(), AP);
				System.out.println(token.getClass());
			}
		}
		/*if(flag1==0 && game.getSnake().getId().getBoundsInParent().intersects(d.getId().getBoundsInParent()))
		{
			d.Hit(game.getSnake(),AP);
			flag1 = 1;
		}
		if(flag2==0 && game.getSnake().getId().getBoundsInParent().intersects(s.getId().getBoundsInParent()))
		{
			s.Hit(game.getSnake(),AP);
			int flag=s.setFlag();
			flag2 = 1;
		}
		Bounds gbound1 = g1.getBoundsInParent();
		Bounds gbound2 = g2.getBoundsInParent();
		Bounds gbound3 = g3.getBoundsInParent();
		Bounds gbound4 = g4.getBoundsInParent();
		Bounds gbound5 = g5.getBoundsInParent();
		Bounds gbound6 = g6.getBoundsInParent();
		Bounds sbound = snake.getBoundsInParent();		
		if(gbound1.intersects(sbound))
		{
			p1.play();
			p1.setOnFinished((e) ->{
				AP.getChildren().remove(g1);
			});
			AP.getChildren().remove(b1);
		}
		if(gbound2.intersects(sbound))
		{
			p2.play();
			p2.setOnFinished((e) ->{
				AP.getChildren().remove(g2);
			});
			AP.getChildren().remove(b2);
		}
		if(gbound3.intersects(sbound))
		{
			p3.play();
			p3.setOnFinished((e) ->{
				AP.getChildren().remove(g3);
			});
			AP.getChildren().remove(b3);
		}
		if(gbound4.intersects(sbound))
		{
			p4.play();
			p4.setOnFinished((e) ->{
				AP.getChildren().remove(g4);
			});
			AP.getChildren().remove(b4);
		}
		if(gbound5.intersects(sbound))
		{
			p5.play();
			p5.setOnFinished((e) ->{
				AP.getChildren().remove(g5);
			});
			AP.getChildren().remove(b5);
		}
		if(gbound6.intersects(sbound))
		{
			p6.play();
			p6.setOnFinished((e) ->{
				AP.getChildren().remove(g6);
			});
			AP.getChildren().remove(b6);
		}*/
	}
	@FXML
	void moveLeft() 
	{
		if(transitionIsPlay)
		{
			transition.pause();
		}
		//snake.setTranslateX(-50);
		game.getSnake().getId().setLayoutX(game.getSnake().getId().getLayoutX() - 50);
		game.getSnake().DecLength(1);
		transition.play();
		//Path path=new Path();
		//path.getElements().add(new MoveTo(snake.getLayoutX(),snake.getLayoutY()));
		//path.getElements().add(new MoveTo(0,0));
		//path.getElements().add(new HLineTo(-30));
		//PathTransition leftmove= new PathTransition();
		//leftmove.setNode(snake);
		//leftmove.setDuration(Duration.millis(500));
		//leftmove.setPath(path);
		//leftmove.play();
		//leftmove.setOnFinished((e)->{
		//	Path remainingPath= new Path();
		//	remainingPath.getElements().add(new MoveTo(snake.getLayoutX(),snake.getLayoutY()));
		//	remainingPath.getElements().add(new VLineTo(-600));
		//	;});
	}
	@FXML
	void moveRight() 
	{
		if(transitionIsPlay)
		{
			transition.pause();
		}
		//snake.setTranslateX(50);
		game.getSnake().getId().setLayoutX(game.getSnake().getId().getLayoutX() + 50);
		game.getSnake().IncLength(1);
		//Circle c = new Circle(8);
		//c.setLayoutY((Integer.parseInt(SnakeLength.getText())+1)*15);
//		SnakeLength.setText(String.valueOf(Integer.parseInt(SnakeLength.getText())+1));
//		c.setFill(Color.WHITE);
//		c.setStroke(Color.BLACK);
//		snake.getChildren().add(c);
		//AP2.setLayoutY(AP2.getLayoutY() + 100);
		Path path = new Path();
		path.getElements().add(new MoveTo(167.5,600));
		path.getElements().add(new VLineTo(1200));
		PathTransition PaneTransition= new PathTransition();
		PaneTransition.setNode(AP2);
		PaneTransition.setDuration(Duration.seconds(5));
		PaneTransition.setPath(path);
		PaneTransition.play();
		transition.play();
		//Path path=new Path();
		//path.getElements().add(new MoveTo(snake.getLayoutX(),snake.getLayoutY()));
		//path.getElements().add(new HLineTo(30));
		//PathTransition rightmove= new PathTransition();
		//rightmove.setNode(snake);
		//rightmove.setDuration(Duration.millis(500));
		//rightmove.setPath(path);
		//rightmove.setOnFinished((e)->{transition.play();});
	}
}
