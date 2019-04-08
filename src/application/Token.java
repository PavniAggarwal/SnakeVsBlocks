package application;

import java.util.ArrayList;

import javafx.animation.Transition;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

public abstract class Token {
	abstract public void Hit(Snake s,AnchorPane AP,ArrayList<Transition> transitions);
	abstract public Node getId();
}
