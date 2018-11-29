package application;

import java.io.Serializable;
import java.util.ArrayList;

public class ChainOfBlocksLoader implements Serializable
{
	
	private ArrayList<BlockLoader> chainofBL;
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
			if(chain.get(i)!=null)
			{
				BlockLoader temp=new BlockLoader();
				temp.setValue(chain.get(i).getValue());
				temp.setColor(chain.get(i).getColor());
				chainofBL.set(i,temp);
			}
			else
			{
				chainofBL.set(i,null);
			}
			
		}
	}
	public ChainOfBlocks Converter2()
	{
		ArrayList<Block> a=new ArrayList<Block>();
		ArrayList<BlockLoader> temp=getChainofBL();
		for(int i=0;i<temp.size();i++)
		{
			if(temp.get(i)!=null)
			{
				Block b= new Block(temp.get(i).getValue(),temp.get(i).getColor());
				a.set(i,b);
			}
			else
			{
				a.set(i,null);
			}
		}
		return  new ChainOfBlocks(getLength(),a,getX(),getY());
	}
	
}
