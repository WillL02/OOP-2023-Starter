package ie.tudublin;

import java.util.ArrayList;

import processing.core.PApplet;

public class DANI extends PApplet {
	String[] sonnetArr;
	ArrayList<Word> model;
	ArrayList<String> sonnect;
	StringBuilder stringBuilder;
	char sonnetRefreshKey = ' ';
	public void settings() {
		size(1000, 1000);
		//fullScreen(SPAN);
	}

	public void setup() {
		model = new ArrayList<Word>();
		sonnetArr = new String[14];
		loadFile();
		printModel();
		writeSonnet();
		printSonnet();
	}

	public void loadFile()
	{
		String[] file = loadStrings("small.txt");
		// Lower cases all of file strings and replaces all punctuation
		for(int i = 0; i < file.length; i ++) {
			file[i] = file[i].replaceAll("[^a-zA-Z ]", "");
			file[i] = file[i].toLowerCase();
		}

		for(int i = 0; i < file.length; i ++)
		{
			String[] line = split(file[i], " ");
			for(int j = 0; j < line.length; j ++)
			{
				addModel(line, j);
			}
		}
	}

	public void addModel(String[] line, int j) 
	{
				Word word;
				boolean last;
				int result = findWord(line[j]);
				if(j+1 == line.length) {
					last = true;
				} else {
					last = false;
				}

				if(result == -1) {
					word = new Word(line[j]);
					model.add(word);
				}
				else {
					word = model.get(result);
				}

				if(!last) {
					if(word.findFollow(line[j+1]) == -1) {
						word.addFollow(new Follow(line[j+1], 1));
					}
					else {
						word.addFollowCount(word.getFollows().get(word.findFollow(line[j+1])));
					}
				}

	}
	public int findWord(String word)
	{
		for(int i = 0; i < model.size(); i ++)
		{
			if(model.get(i).getWord().equals(word))
			{
				return i;
			}
		}
		return -1;
	}
	
	public void printModel()
	{
		for(Word w:model)
		{
			System.out.println(w.toString());
		}
	}
	public void writeSonnet()
	{
		for (int i = 0; i < 14; i++)
		{
			int r = (int) random(0, model.size());
			Word w = model.get(r);

			//String builder used to build string sentence
			stringBuilder = new StringBuilder();
			stringBuilder.append(w.getWord() + " ");

			for(int k = 0; k < 7;k++)
			{
				int r2;
				if(w.getFollows().size() == 0)
				{
					break;
				}
				else
				{
					r2 = (int) random(0, w.getFollows().size());
				}
				
				Follow f = w.getFollows().get(r2);
				stringBuilder.append(f.getWord() + " ");
				w = model.get(findWord(f.getWord()));

			}
			String s = stringBuilder.toString();
			sonnetArr[i] = s;
		}
	}
	public void printSonnet()
	{ 
		int i = 0;

		for(String str = sonnetArr[i];i<sonnetArr.length;i++) 
		{
			System.out.println(str);
		}
	}

	public void keyPressed() 
	{
		
		if(key == sonnetRefreshKey) 
		{
			writeSonnet();
		}
	}

	public void draw() 
    {
		background(0);
		int lineSpacing = 30;
		for(int i = 0;i<sonnetArr.length;i++)
		{
			text(sonnetArr[i], 200,lineSpacing);
			lineSpacing += 30;
		}
	}
}
