package application;

import java.util.ArrayList;
import java.util.Random;

import javafx.animation.ParallelTransition;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;

public class ChainOfBlocks extends Token{
	@FXML
	private Group ID;
	private ArrayList<Block> chain = new ArrayList<Block>();
	private double[] XCoordinates = {7.0,53.0,99.0,145.0,191.0,237.0,283.0};
	private int lengthOfChain;
	ChainOfBlocks(int l,int length) {
		ID = new Group();
		lengthOfChain = l;
		for(int i=0;i<lengthOfChain-1;i++)
		{
			Random rand = new Random();
			int v = rand.nextInt(50) + 1;
			Group b = new Group();
			Block block = new Block(v,b);
			int flag = 0;
			int n=0;
			while(flag==0)
			{
				n = rand.nextInt(7);
				if(XCoordinates[n]!=0)
				{
					block.getId().setLayoutX(XCoordinates[n]);
					XCoordinates[n] = 0;
					flag = 1;
				}
			}
			ID.getChildren().add(block.getId());
			chain.add(block);
		}
		Group b = new Group();
		Block block = new Block(length - 1,b);
		int flag = 0;
		int n=0;
		while(flag==0)
		{
			Random rand = new Random();
			n = rand.nextInt(7);
			if(XCoordinates[n]!=0)
			{
				block.getId().setLayoutX(XCoordinates[n]);
				XCoordinates[n] = 0;
				flag = 1;
			}
		}
		ID.getChildren().add(block.getId());
		chain.add(block);
	}
	public Group getId() {
		return ID;
	}
	public void Hit(Snake s,AnchorPane AP) {
		for(int i=0;i<lengthOfChain;i++)
		{
			if(s.getId().getLayoutX()>=chain.get(i).getId().getLayoutX() && s.getId().getLayoutX()<=chain.get(i).getId().getLayoutX()+45)
			{
				chain.get(i).Hit(s);
				s.DecLength(chain.get(i).getValue());
				ParallelTransition p= chain.get(i).getAnimation();
				final int j = i;
				p.setOnFinished((e) ->{
					ID.getChildren().remove(j);
					chain.remove(j);
					lengthOfChain--;
				});
				return;
			}
		}
		if(lengthOfChain==0)
		{
			AP.getChildren().remove(ID);
		}
	}
	public void Destroy(Snake s) {
		for(int i=0;i<lengthOfChain;i++)
		{
			chain.get(i).Hit(s);
		}
		chain = null;
		lengthOfChain = 0;
	}
	public void HitWithShield(Snake s,AnchorPane AP) {
		for(int i=0;i<lengthOfChain;i++)
		{
			if(s.getId().getLayoutX()>=chain.get(i).getId().getLayoutX() && s.getId().getLayoutX()<=chain.get(i).getId().getLayoutX()+45)
			{
				chain.get(i).Hit(s);
				ParallelTransition p= chain.get(i).getAnimation();
				final int j = i;
				p.setOnFinished((e) ->{
					ID.getChildren().remove(j);
					chain.remove(j);
					lengthOfChain--;
				});
				return;
			}
		}
		if(lengthOfChain==0)
		{
			AP.getChildren().remove(ID);
		}
	}
}
