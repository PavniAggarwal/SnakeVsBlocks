package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class LeaderBoardController implements Initializable
{

	@FXML
    private TableView<Player> TableLeaderBoard;

    @FXML
    private TableColumn<Player,String> ColRank;

    @FXML
    private TableColumn<Player,String> ColName;

    @FXML
    private TableColumn<Player,String> ColScore;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
	
		ColRank.setCellValueFactory(new PropertyValueFactory<Player,String>("rank"));
		ColName.setCellValueFactory(new PropertyValueFactory<Player,String>("name"));
		ColScore.setCellValueFactory(new PropertyValueFactory<Player,String>("score"));
		
		TableLeaderBoard.setItems(getData());
	}
	
	private ObservableList<Player> getData()
	{
		ObservableList<Player> players= FXCollections.observableArrayList();
		players.add(new Player("1","ABCPlayer","50"));
		players.add(new Player("2","XYZPlayer","40"));
		players.add(new Player("3","DEFPlayer","30"));
		return players;
	}
	@FXML
	void onArrow(ActionEvent event) throws IOException{
		Parent ButtonClicked = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
    	Scene s = new Scene(ButtonClicked,335,600);
    	Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
    	window.setScene(s);
    	window.show();
	}
}
