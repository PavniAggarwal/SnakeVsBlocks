package application;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Loader 
{
	public ArrayList<Object> ConversionSerialize(ArrayList<Object> CurrentPage)
	{
		ArrayList<Object> data= new ArrayList<Object>();
		for(Object token :CurrentPage)
		{
			if(token instanceof Ball)
			{
				BallLoader bl= new BallLoader();
				bl.Converter1((Ball)token);
				data.add(bl);
			}
			else if(token instanceof ChainOfBlocks)
			{
				ChainOfBlocksLoader cb=new ChainOfBlocksLoader();
				cb.Converter1((ChainOfBlocks)token);
				data.add(cb);
			}
			else if(token instanceof DestroyBlock)
			{
				DestroyBlockLoader dbl=new DestroyBlockLoader();
				dbl.Converter1((DestroyBlock)token);
				data.add(dbl);
			}
			else if(token instanceof Magnet)
			{
				MagnetLoader ml=new MagnetLoader();
				ml.Converter1((Magnet)token);
				data.add(ml);
			}
			else if(token instanceof Shield)
			{
				ShieldLoader sl= new ShieldLoader();
				sl.Converter1((Shield)token);
				data.add(sl);
			}
			else if(token instanceof Snake)
			{
				SnakeLoader snkl= new SnakeLoader();
				snkl.Converter1((Snake)token);
				data.add(snkl);
			}
			else if(token instanceof Wall)
			{
				WallLoader wl= new WallLoader();
				wl.Converter1((Wall)token);
				data.add(wl);
			}
		}
		return data;
	}
	
	public ArrayList<Object> ConversionDeserialize(ArrayList<Object> des)
	{
		ArrayList<Object> tokens=new ArrayList<Object>();
		for(Object token: des)
		{
			if(token instanceof BallLoader)
			{
				Ball b=((BallLoader)token).Converter2();
				tokens.add(b);
			}
			else if(token instanceof ChainOfBlocksLoader)
			{
				ChainOfBlocks cbl=((ChainOfBlocksLoader) token).Converter2();
				tokens.add(cbl);
			}
			else if(token instanceof DestroyBlockLoader)
			{
				DestroyBlock db= ((DestroyBlockLoader)token).Converter2();
				tokens.add(db);
			}
			else if(token instanceof MagnetLoader)
			{
				Magnet m=((MagnetLoader) token).Converter2();
				tokens.add(m);
			}
			else if(token instanceof ShieldLoader)
			{
				Shield s= ((ShieldLoader) token).Converter2();
				tokens.add(s);
			}
			else if(token instanceof SnakeLoader)
			{
				Snake snk=((SnakeLoader) token).Converter2();
				tokens.add(snk);
			}
			else if(token instanceof WallLoader)
			{
				Wall w=((WallLoader) token).Converter2();
				tokens.add(w);
			}
		}
		return tokens;
	}
	
	public void SaveData(ArrayList<Object> data)
	{
		try
		{
			FileOutputStream fileOut= new FileOutputStream("savedgame.ser");
			ObjectOutputStream out= new ObjectOutputStream(fileOut);
			out.writeObject(data);
			out.close();
			fileOut.close();
		}
		catch(IOException e)
		{
			System.out.println("Exception in saveData");
			e.printStackTrace();
		}	
	}
	public ArrayList<Object> LoadData()
	{
		ArrayList<Object> des= new ArrayList<Object>();
		try
		{
			FileInputStream fileIn= new FileInputStream("savedgame.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			des=(ArrayList<Object>)in.readObject();		
			in.close();
			fileIn.close();
			return des;
		}
		catch(IOException e)
		{
			System.out.println("Exception in IOException of LoadData");
			return null;
		}
		catch(ClassNotFoundException e)
		{
			System.out.println("Exception in ClassNotFoundException of LoadData");
			return null;
		}	
	}
	public void serialize(ArrayList<Object> tokens)
	{
		SaveData(ConversionSerialize(tokens));
	}
	public ArrayList<Object> deserialize()
	{
		return ConversionDeserialize(LoadData());
	}
	
}
