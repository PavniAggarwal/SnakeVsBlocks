package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class Magnet extends Token{
	@FXML
	private ImageView magnet;
	private ArrayList<Ball> list = new ArrayList<Ball>();
	Magnet() throws FileNotFoundException {
		FileInputStream input = new FileInputStream("C:\\Users\\PAVNI\\eclipse-workspace\\SnakeVsBlock\\src\\application\\Magnet.jpg");
		Image pic = new Image(input);
		magnet = new ImageView();
		magnet.setImage(pic);
		magnet.setFitHeight(25.0);
		magnet.setFitWidth(25.0);
	}
	public ImageView getId() {
		return magnet;
	}
	public void setList(ArrayList<Ball> arr) {
		list = arr;
	}
	public void Hit(Snake s,AnchorPane AP) {
		AP.getChildren().remove(magnet);
		for(int i=0;i<list.size();i++)
		{
			if(list.get(i).getId().getLayoutX()>magnet.getLayoutX()-50 && list.get(i).getId().getLayoutX()<magnet.getLayoutX()+71 && list.get(i).getId().getLayoutY()>magnet.getLayoutY()-50 && list.get(i).getId().getLayoutY()<magnet.getLayoutY()+71)
			{
				list.get(i).Hit(s, AP);
				list.remove(i);
			}
		}
	}
}
