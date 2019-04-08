package application;

import java.util.HashMap;

public class Leaderboard {
	HashMap<String,Integer> data = new HashMap<>();
	private String[] Names;
	private int[] Scores;
	private int numberOfEntries;
	Leaderboard() {
		Names = new String[10];
		Scores = new int[10];
		numberOfEntries = 0;
	}
	public void insertInLb(String name, int score) {
		for(int i=0;i<numberOfEntries;i++)
		{
			if(score>Scores[i])
			{
				for(int j=numberOfEntries-1;j>i;j--)
				{
					Scores[j+1] = Scores[j];
					Names[j+1] =  Names[j];
 				}
				Scores[i] = score;
				Names[i] = name;
				numberOfEntries ++;
			}
		}
		
	}
}
