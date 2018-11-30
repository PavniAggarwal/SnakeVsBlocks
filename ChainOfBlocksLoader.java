package application;

import java.io.Serializable;
import java.util.ArrayList;

public class ChainOfBlocksLoader implements Serializable
{
	
	private ArrayList<BlockLoader> chainofBL=new ArrayList<BlockLoader>();
	private int length;
	
	private double x;
	private double y;
	
	public int getLength() 
	{
		return length;
	}
	public void setLength(int length) 
	{
		this.length = length;
	}
	public ArrayList<BlockLoader> getChainofBL() 
	{
		return chainofBL;
	}
	public void setChainofBL(ArrayList<BlockLoader> chainofBL) 
	{
		this.chainofBL = chainofBL;
	}
	public double getX() 
	{
		return x;
	}
	public void setX(double x) 
	{
		this.x = x;
	}
	public double getY() 
	{
		return y;
	}
	public void setY(double y) 
	{
		this.y = y;
	}
	public void Converter1(ChainOfBlocks cob)
	{
		ArrayList<Block> chain =cob.getChain();
		setLength(cob.getLength());
		setX(cob.getX());
		setY(cob.getY());
		for(int i=0;i<chain.size();i++)
		{
			Block b= chain.get(i);
			//if(b!=null)
			//{
				BlockLoader temp=new BlockLoader();
				temp.setValue(b.getValue());
				temp.setColor(b.getColor());
				temp.setX(b.getId().getLayoutX());
				temp.setY(b.getId().getLayoutY());
				chainofBL.add(temp);
			//}
			
		}
	}
	public ChainOfBlocks Converter2()
	{
		ArrayList<Block> a=new ArrayList<Block>();
		ArrayList<BlockLoader> temp=getChainofBL();
		for(int i=0;i<temp.size();i++)
		{
			BlockLoader bl=temp.get(i);
			Block b= new Block(bl.getValue(),bl.getColor(),bl.getX(),bl.getY());
			a.add(b);
		}
		return  new ChainOfBlocks(getLength(),a,getX(),getY());
	}
	
}
