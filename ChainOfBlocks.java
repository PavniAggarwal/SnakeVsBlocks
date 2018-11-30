package application;

import java.io.IOException;
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
	private int GameOverFlag = 0;
	ChainOfBlocks(int l,int length) {
		ID = new Group();
		lengthOfChain = l;
		for(int i=0;i<lengthOfChain-1;i++)
		{
			Random rand = new Random();
			int v = rand.nextInt(20) + 1;
			Block block = new Block(v);
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
		Block block = new Block(length - 1);
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
	public ChainOfBlocks(int l,ArrayList<Block> a,double x,double y)
	{
		ID = new Group();
		lengthOfChain = l;
		chain=a;
		ID.setLayoutX(x);
		ID.setLayoutY(y);
		for(int i=0;i<lengthOfChain;i++)
		{
			Block b=a.get(i);
			b.getId().setLayoutX(XCoordinates[i]);
			ID.getChildren().add(b.getId());
		}
	}
	public double getX()
	{
		return ID.getLayoutX();
	}
	public double getY()
	{
		return ID.getLayoutY();
	}
	public Group getId() {
		return ID;
	}
	public ArrayList<Block> getChain()
	{
		return chain;
	}
	public int getLength()
	{
		return lengthOfChain;
	}
	public int getGameOverFlag() {
		return GameOverFlag;
	}
	public void Hit(Snake s,AnchorPane AP) {
		for(int i=0;i<lengthOfChain;i++)
		{
			if(s.getId().getLayoutX()>=chain.get(i).getId().getLayoutX() && s.getId().getLayoutX()<=chain.get(i).getId().getLayoutX()+45)
			{
				if(s.getLength()>=chain.get(i).getValue())
				{
					chain.get(i).Hit(s,AP);
					ParallelTransition p= chain.get(i).getAnimation();
					final int j = i;
					p.setOnFinished((e) ->{
						ID.getChildren().remove(j);
						chain.remove(j);
						lengthOfChain--;
					});
					return;
				}
				else
				{
					GameOverFlag = 1;
					chain.get(i).Hit(s,AP);
				}
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
			chain.get(i).Destroy(s);
		}
		chain = null;
		lengthOfChain = 0;
	}
	public void HitWithShield(Snake s,AnchorPane AP) {
		for(int i=0;i<lengthOfChain;i++)
		{
			if(s.getId().getLayoutX()>=chain.get(i).getId().getLayoutX() && s.getId().getLayoutX()<=chain.get(i).getId().getLayoutX()+45)
			{
				chain.get(i).Destroy(s);
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