package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Controller implements Initializable{
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	@FXML
    void onTapToStart(ActionEvent event) throws IOException{
		Parent ButtonClicked = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
    	Scene s = new Scene(ButtonClicked,335,600);
    	Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
    	window.setScene(s);
    	window.show();
    }
	@FXML
	void onStartNewGame(ActionEvent event) throws IOException{
		Parent ButtonClicked = FXMLLoader.load(getClass().getResource("Game.fxml"));
    	Scene s = new Scene(ButtonClicked,335,600);
    	Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
    	window.setScene(s);
    	window.show();
	}
	@FXML
	void onInstructions(ActionEvent event) throws IOException{
		Parent ButtonClicked = FXMLLoader.load(getClass().getResource("InstructionPage.fxml"));
    	Scene s = new Scene(ButtonClicked,335,600);
    	Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
    	window.setScene(s);
    	window.show();
	}
	@FXML
	void onLeaderboard(ActionEvent event) throws IOException{
		Parent ButtonClicked = FXMLLoader.load(getClass().getResource("LeaderboardPage.fxml"));
    	Scene s = new Scene(ButtonClicked,335,600);
    	Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
    	window.setScene(s);
    	window.show();
	}
	@FXML
	void onExitGame(ActionEvent event) throws IOException{
		Parent ButtonClicked = FXMLLoader.load(getClass().getResource("ExitGameDialogBox.fxml"));
    	Scene s = new Scene(ButtonClicked,335,150);
    	Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
    	window.setScene(s);
    	window.show();
	}
	@FXML
	void onPause(ActionEvent event) throws IOException{
		//System.out.println("In Controller");
		Parent ButtonClicked = FXMLLoader.load(getClass().getResource("PauseDialogBox.fxml"));
    	Scene s = new Scene(ButtonClicked,335,365);
    	Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
    	window.setScene(s);
    	window.show();
	}
	@FXML
	void onArrow(ActionEvent event) throws IOException{
		Parent ButtonClicked = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
    	Scene s = new Scene(ButtonClicked,335,600);
    	Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
    	window.setScene(s);
    	window.show();
	}
	@FXML
	void onRestart(ActionEvent event) throws IOException{
		Parent ButtonClicked = FXMLLoader.load(getClass().getResource("RestartGameDialogBox.fxml"));
    	Scene s = new Scene(ButtonClicked,335,150);
    	Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
    	window.setScene(s);
    	window.show();
	}
	@FXML
	void onEndGame(ActionEvent event) throws IOException{
		Parent ButtonClicked = FXMLLoader.load(getClass().getResource("EndGameDialogBox.fxml"));
    	Scene s = new Scene(ButtonClicked,335,150);
    	Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
    	window.setScene(s);
    	window.show();
	}
	@FXML
	void onMainMenu(ActionEvent event) throws IOException{
		Parent ButtonClicked = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
    	Scene s = new Scene(ButtonClicked,335,600);
    	Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
    	window.setScene(s);
    	window.show();
	}
	@FXML
	void onExitGameYes(ActionEvent event){
	    Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
	    stage.close();
	}
	@FXML
	void onEndGameYes(ActionEvent event) throws IOException{
		Parent ButtonClicked = FXMLLoader.load(getClass().getResource("GameOver.fxml"));
    	Scene s = new Scene(ButtonClicked,335,600);
    	Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
    	window.setScene(s);
    	window.show();
	}
	@FXML
	void onResume(ActionEvent event) throws IOException
	{
		
		Parent ButtonClicked = FXMLLoader.load(getClass().getResource("Game.fxml"));
    	Scene s = new Scene(ButtonClicked,335,600);
    	Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
    	window.setScene(s);
    	window.show();
    	
    	Loader l= new Loader();
    	l.deserialize();
    	//FXMLLoader f=FXMLLoader.load(getClass().getResource("Game.fxml"));
    	//GameController gc=f.getController();
		//gc.setResume(true,a);
		
	}
}
