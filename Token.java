package application;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

public abstract class Token {
	abstract public void Hit(Snake s,AnchorPane AP);
	abstract public Node getId();
}
