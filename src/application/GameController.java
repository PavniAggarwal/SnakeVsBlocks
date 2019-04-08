package application;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.animation.Transition;
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
	@FXML
	private Text SnakeLength;
	@FXML
	private Text status;
	@FXML
	private Text Score;
	@FXML
	private AnchorPane AP;
	Boolean transitionIsPlay;
	int ShieldFlag = 0;
	int GameOverFlag = 0;

	ArrayList<Object> allElements = new ArrayList<Object>();
	ArrayList<Object> objects = new ArrayList<Object>();
	ArrayList<Object> tokens = new ArrayList<Object>();
	ArrayList<Object> resumedTokens = new ArrayList<Object>();
	ArrayList<Transition> transitions = new ArrayList<Transition>();
	ArrayList<ChainOfBlocks> blocks = new ArrayList<ChainOfBlocks>();
	ArrayList<Ball> balls = new ArrayList<Ball>();
	ArrayList<Wall> walls = new ArrayList<Wall>();
	boolean resume;
	@Override
	public void initialize(URL url, ResourceBundle rb)
	{	
		System.out.println("boolean resume: "+ resume);
		resume=sendResume();
		resumedTokens=sendArray();
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
			createSnake();
			createD(10);
			//try {
			//	createDisplay();
			//} catch (FileNotFoundException e) {}
		}
		else
		{
			System.out.println("in else");
			createDisplay(resumedTokens);
		}
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
	void createSnake() {
			Snake snk=new Snake(Score);
			snk.getId().setLayoutX(167.5);
			snk.getId().setLayoutY(430);
			game.setSnake(snk);
			Path path= new Path();
			path.getElements().add(new MoveTo(0,0));
			path.getElements().add(new VLineTo(-80));
			PathTransition transition= new PathTransition();
			transition.setNode(game.getSnake().getId());
			transition.setDuration(Duration.seconds(1));
			transition.setPath(path);
			transition.play();
			snk.setTransition(transition);
			transitionIsPlay = true;
			allElements.add(snk);
			AP.getChildren().add(snk.getId());
	}
	void createD(int time)
	{
		Random r= new Random();
		Wall w = new Wall(r.nextInt(200)+1);
		w.getId().setLayoutY(-1 * w.getLength()/2 - 5);
		walls.add(w);
		allElements.add(w);
		objects.add(w);
		AP.getChildren().add(w.getId());
		Path path_wall= new Path();
		path_wall.getElements().add(new MoveTo(0,0));
		path_wall.getElements().add(new VLineTo(1000));
		PathTransition temp= new PathTransition();
		temp.setNode(w.getId());
		temp.setDuration(Duration.seconds(5));
		temp.setPath(path_wall);
		
		ChainOfBlocks cb=new ChainOfBlocks(r.nextInt(7)+1,game.getSnake().getLength());
		cb.getId().setLayoutX(167.5);
		cb.getId().setLayoutY(w.getId().getLayoutY() - w.getLength()/2 - 24);
		blocks.add(cb);
		tokens.add(cb);
		allElements.add(cb);
		objects.add(cb);
		AP.getChildren().add(cb.getId());
		Path path1= new Path();
		path1.getElements().add(new MoveTo(0,0));
		path1.getElements().add(new VLineTo(1000));
		PathTransition temp1= new PathTransition();
		temp1.setNode(cb.getId());
		temp1.setDuration(Duration.seconds(5));
		temp1.setPath(path1);
		ParallelTransition p = new ParallelTransition(temp,temp1);
		transitions.add(p);
		p.play();
		p.setOnFinished((e)->{
			blocks.remove(cb);
			tokens.remove(cb);
			allElements.remove(cb);
			objects.remove(cb);
			walls.remove(w);
			allElements.remove(w);
			objects.remove(w);
			AP.getChildren().remove(cb.getId());
			AP.getChildren().remove(w.getId());
			createD(10);
		});
		double position = cb.getId().getLayoutY();
		for(int i=0;i<2;i++)
		{
			Ball b = new Ball(r.nextInt(5)+1);
			b.getId().setLayoutY(position -200);
			balls.add(b);
			tokens.add(b);
			allElements.add(b);
			objects.add(b);
			AP.getChildren().add(b.getId());
			Path path2= new Path();
			path2.getElements().add(new MoveTo(0,0));
			path2.getElements().add(new VLineTo(-1 * position + 1000));
			PathTransition temp2= new PathTransition();
			temp2.setNode(b.getId());
			temp2.setDuration(Duration.seconds((-1 * position + 1000)/200));
			temp2.setPath(path2);
			transitions.add(temp2);
			temp2.play();
			temp2.setOnFinished((e)->{
				balls.remove(b);
				tokens.remove(b);
				allElements.remove(b);
				objects.remove(b);
				AP.getChildren().remove(b.getId());
			});
			position = position - 200;
			}
		
		int choice = r.nextInt(3);
		Token t;
		if(choice==0)
		{
			t = new Magnet();
		}
		else if(choice==1)
		{
			t = new Shield();
		}
		else
		{
			t = new DestroyBlock();
		}
		t.getId().setLayoutY(position -200);
		tokens.add(t);
		allElements.add(t);
		objects.add(t);
		AP.getChildren().add(t.getId());
		Path path2= new Path();
		path2.getElements().add(new MoveTo(0,0));
		path2.getElements().add(new VLineTo(-1 * position + 1000));
		PathTransition temp2= new PathTransition();
		temp2.setNode(t.getId());
		temp2.setDuration(Duration.seconds((-1 * position + 1000)/200));
		temp2.setPath(path2);
		transitions.add(temp2);
		temp2.play();
		temp2.setOnFinished((e)->{
			tokens.remove(t);
			allElements.remove(t);
			objects.remove(t);
			AP.getChildren().remove(t.getId());
		});	
	}
	void pauseTransition() {
		for(Transition t:transitions)
		{
			t.pause();
		}
	}
	void playTransition()
	{
		for(Transition t:transitions)
		{
			t.play();
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
			pauseTransition();
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
						c.Hit(game.getSnake(), AP,transitions);
					}
					System.out.println(token.getClass());
					GameOverFlag = c.getGameOverFlag();
					if(GameOverFlag==1)
					{
						
					}
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
					token.Hit(game.getSnake(),AP,transitions);
				}
				tokens.remove(token);
				System.out.println(token.getClass());
			}
		}
	}
	@FXML
	void moveLeft() 
	{
		game.getSnake().getId().setLayoutX(game.getSnake().getId().getLayoutX() - 10);
		for(int i=0;i<walls.size();i++)
		{
			if(game.getSnake().getId().getBoundsInParent().intersects(walls.get(i).getId().getBoundsInParent()))
			{
				game.getSnake().getId().setLayoutX(game.getSnake().getId().getLayoutX() + 10);
				break;
			}
		}
	}
	@FXML
	void moveRight() 
	{
		game.getSnake().getId().setLayoutX(game.getSnake().getId().getLayoutX() + 10);
		for(int i=0;i<walls.size();i++)
		{
			if(game.getSnake().getId().getBoundsInParent().intersects(walls.get(i).getId().getBoundsInParent()))
			{
				game.getSnake().getId().setLayoutX(game.getSnake().getId().getLayoutX() - 10);
				break;
			}
		}
	}
}