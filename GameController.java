package application;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

import com.sun.javafx.geom.Vec2d;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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

public class GameController extends Controller implements Initializable
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
	private AnchorPane AP;
	@FXML
	private AnchorPane AP2;
	@FXML
	private Group g1;
	@FXML
	private Group b1;
	Vec2d location;
	ParallelTransition p1;
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
	ArrayList<Object> tokens = new ArrayList<Object>();
	ArrayList<Object> resumedTokens = new ArrayList<Object>();
	ArrayList<Wall> walls = new ArrayList<Wall>();
	boolean resume;
	@Override
	public void initialize(URL url, ResourceBundle rb)
	{	
		System.out.println("boolean resume: "+ resume);
		resume=sendResume();
		resumedTokens=sendArray();
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
		transition.setDuration(Duration.seconds(10));
		transition.setPath(path);
		transition.play();
		transitionIsPlay = true;
		snk.setTransition(transition);
		Timeline timeline = new Timeline(new KeyFrame(
				Duration.millis(5),
				ae-> {
					onHit();
				}));
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
		if(resume==false)
		{
			System.out.println("in if");
			try {
				createDisplay();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			System.out.println("in else");
			createDisplay(resumedTokens);
		}
		
	}
	
	@FXML
	public void createDisplay() throws FileNotFoundException{
	
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
		Wall wall = new Wall(100);
		wall.getId().setLayoutY(132);
		AP.getChildren().add(wall.getId());
		walls.add(wall);
		ChainOfBlocks c1 = new ChainOfBlocks(3,game.getSnake().getLength());
		c1.getId().setLayoutX(0);
		c1.getId().setLayoutY(250);
		AP.getChildren().add(c1.getId());
		tokens.add(c1);
		ArrayList<Ball> arr2 = new ArrayList<Ball>();
		arr2.add(b);
		arr2.add(ball);
		m = new Magnet();
		m.getId().setLayoutY(500);
		m.getId().setLayoutX(230);
		m.setList(arr2);
		AP.getChildren().add(m.getId());
		tokens.add(m);
		s = new Shield();
		s.getId().setLayoutY(197);
		s.getId().setLayoutX(200);
		AP.getChildren().add(s.getId());
		tokens.add(s);
		ArrayList<ChainOfBlocks> arr = new ArrayList<ChainOfBlocks>();
		arr.add(c);
//		d = new DestroyBlock();
//		d.getId().setLayoutY(300);
//		d.getId().setLayoutX(200);
//		d.setList(arr);
//		AP.getChildren().add(d.getId());
//		tokens.add(d);
	}
	
	void createDisplay(ArrayList<Object> a)
	{
		for(Object token: a)
		{
			if(token instanceof Ball)
			{
				Ball b=(Ball)token;
				AP.getChildren().add(b.getId());
				System.out.println(b.getClass()+" value: "+b.getValue());
			}
			else if(token instanceof ChainOfBlocks)
			{
				ChainOfBlocks c=(ChainOfBlocks)token;
				AP.getChildren().add(c.getId());
				System.out.println(c.getClass()+" length: "+c.getLength());
				for(int i=0;i<c.getLength();i++)
				{
					System.out.println((i+1)+"Value: "+c.getChain().get(i).getValue()+" color: "+c.getChain().get(i).getColor());
				}
			}
			else if(token instanceof DestroyBlock)
			{
				DestroyBlock db=(DestroyBlock)token;
				AP.getChildren().add(db.getId());
			}
			else if(token instanceof Magnet)
			{
				Magnet m=(Magnet)token;
				AP.getChildren().add(m.getId());
			}
			else if(token instanceof Shield)
			{
				Shield s=(Shield)token;
				AP.getChildren().add(s.getId());
			}
			else if(token instanceof Snake)
			{
				Snake snk=(Snake)token;
				AP.getChildren().add(snk.getId());
			}
			else if(token instanceof Wall)
			{
				Wall w=(Wall)token;
				AP.getChildren().add(w.getId());
			}
		}
	}
	
	void createD(int time)
	{
		Snake snk=new Snake(Score);
		Random r= new Random();
		int l_chain=r.nextInt(7)+1;
		ChainOfBlocks cb=new ChainOfBlocks(l_chain,snk.getLength());
		ArrayList<Object> a=new ArrayList<Object>();
		a.add(cb);
		AssignTransition(a,time);
	}
	ArrayList<PathTransition> AssignTransition(ArrayList<Object> a,int time)
	{
		ArrayList<PathTransition> downMovement= new ArrayList<PathTransition>();
		for(Object token:a)
		{
			Path path= new Path();
			path.getElements().add(new MoveTo(0,0));
			path.getElements().add(new VLineTo(600));
			PathTransition temp= new PathTransition();
			
			//transition.play();
			if(token instanceof Ball)
			{
				Ball b=(Ball)token;
				temp.setNode(b.getId());
			}
			else if(token instanceof ChainOfBlocks)
			{
				ChainOfBlocks c=(ChainOfBlocks)token;
				temp.setNode(c.getId());
			}
			else if(token instanceof DestroyBlock)
			{
				DestroyBlock db=(DestroyBlock)token;
				temp.setNode(db.getId());
			}
			else if(token instanceof Magnet)
			{
				Magnet m=(Magnet)token;
				temp.setNode(m.getId());
			}
			else if(token instanceof Shield)
			{
				Shield s=(Shield)token;
				temp.setNode(s.getId());
			}
			else if(token instanceof Snake)
			{
				Snake snk=(Snake)token;
				//temp.setNode(snk.getId());
			}
			else if(token instanceof Wall)
			{
				Wall w=(Wall)token;
				temp.setNode(w.getId());
			}

			temp.setDuration(Duration.seconds(time));
			temp.setPath(path);
			downMovement.add(temp);
		}
		return downMovement;
	}
	void startTransition(ArrayList<PathTransition> a)
	{
		for(PathTransition tr:a)
		{
			tr.play();
		}
	}
	
	void setResume(boolean b)
	{
		resume=b;
	}
	void setRTokens(ArrayList<Object> a)
	{
		resumedTokens=a;
	}
	@FXML
	private void displayPosition(MouseEvent event)
	{
		status.setText("X = " + event.getX() + "  ;   Y = " + event.getY());
	}
	@FXML
	void onPause(ActionEvent event) throws IOException{
		
		if(transitionIsPlay)
		{
			transition.pause();
		}
		Parent ButtonClicked = FXMLLoader.load(getClass().getResource("PauseDialogBox.fxml"));
    	Scene s = new Scene(ButtonClicked,335,365);
    	Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
    	window.setScene(s);
    	window.show();
    	Loader l= new Loader();
		l.serialize(tokens);
	}
	@FXML
	public void GameOver() {
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("GameOver.fxml"));
			Stage window = (Stage) AP.getScene().getWindow();
	        window.setScene(new Scene(root, 335, 600));
	        window.show();
		} catch (IOException e) {
		}
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
	void onHit(){
		for(int i=0;i<tokens.size();i++)
		{
			Token token = (Token)tokens.get(i);
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
					System.out.println(token.getClass());
					GameOverFlag = c.getGameOverFlag();
				}
				else
				{
					if(token.getClass().equals(Shield.class))
					{
						Shield s = (Shield) token;
						ShieldFlag = 1;
						Timeline timeline = new Timeline(new KeyFrame(
								Duration.millis(5200),
								ae-> {									
								}));
						timeline.play();
						timeline.setOnFinished((e)->{
						ShieldFlag = 0;
						AP.getChildren().remove(s.getTimer());
						});
					}
					token.Hit(game.getSnake(), AP);
				}
				tokens.remove(token);
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
			transitionIsPlay = false;
		}
		//snake.setTranslateX(-50);
		game.getSnake().getId().setLayoutX(game.getSnake().getId().getLayoutX() - 10);
		for(int i=0;i<walls.size();i++)
		{
			if(game.getSnake().getId().getBoundsInParent().intersects(walls.get(i).getId().getBoundsInParent()))
			{
				game.getSnake().getId().setLayoutX(game.getSnake().getId().getLayoutX() + 10);
				break;
			}
		}
		//game.getSnake().DecLength(1);
		transition.play();
		transitionIsPlay = true;
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
			transitionIsPlay = false;
		}
		//snake.setTranslateX(50);
		game.getSnake().getId().setLayoutX(game.getSnake().getId().getLayoutX() + 10);
		for(int i=0;i<walls.size();i++)
		{
			if(game.getSnake().getId().getBoundsInParent().intersects(walls.get(i).getId().getBoundsInParent()))
			{
				game.getSnake().getId().setLayoutX(game.getSnake().getId().getLayoutX() - 10);
				break;
			}
		}
		//game.getSnake().IncLength(1);
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
		transitionIsPlay = true;
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
