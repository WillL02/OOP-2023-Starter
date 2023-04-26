package ie.tudublin;

import java.util.ArrayList;

import processing.core.PApplet;

public class DANI extends PApplet {
	String[] sonnetArr;
	ArrayList<Word> model;
	ArrayList<String> sonnect;
	StringBuilder stringBuilder;
	int limit = 14;
	char sonnetRefreshKey = ' ';
	public void settings() {
		size(1000, 1000);
	} // end settings

	public void setup() {
		background(0);
		model = new ArrayList<Word>();
		sonnetArr = new String[14];
		loadFile();
		printModel();
		writeSonnet();
		
	} // end setup

	public void loadFile()
	{
		String[] file = loadStrings("small.txt");
		// Lower cases all of file strings and replaces all punctuation
		for(int i = 0; i < file.length; i ++) {
			file[i] = file[i].replaceAll("[^a-zA-Z ]", "");
			file[i] = file[i].toLowerCase();
		} // end for



		for(int i = 0; i < file.length; i ++)
		{
			String[] line = split(file[i], " ");
			for(int j=0;j<line.length;j++)
			{
				addModel(line, j);
			} // end for
		} // enf for
	} // loadFile method

	public void addModel(String[] line, int j) 
	{
				Word word;
				boolean last;
				int result = findWord(line[j]);
				if(j+1 == line.length) {
					last = true;
				} else {
					last = false;
				} // end else

				if(result == -1) {
					word = new Word(line[j]);
					model.add(word);
				} // end if
				else {
					word = model.get(result);
				} // end else

				if(!last) {
					if(word.findFollow(line[j+1]) == -1) {
						word.addFollow(new Follow(line[j+1], 1));
					} // end if
					else {
						word.addFollowCount(word.getFollows().get(word.findFollow(line[j+1])));
					} // end else
				} // end if

	} // end addModel method
	public int findWord(String word)
	{
		for(int i = 0; i < model.size(); i ++)
		{
			if(model.get(i).getWord().equals(word))
			{
				return i;
			} // end if
		} // end for
		return -1;
	} // end findWord method
	
	public void printModel()
	{
		for(Word wordIn :model)
		{
			System.out.println(wordIn.toString());
		} // end for
	} // end printModel method
	public void writeSonnet()
	{
		for (int i = 0;i<limit;i++)
		{
			
			int r = (int) random(0, model.size());


			Word w = model.get(r);

			//String builder used to build string sentence
			stringBuilder = new StringBuilder();

			stringBuilder.append(w.getWord() + " ");

			for(int k = 0; k < 7;k++)
			{
				int r2;
				if(w.getFollows().size() == 0) {
					break;
				} else {
					r2 = (int) random(0, w.getFollows().size());
				} // end else
				
				Follow f = w.getFollows().get(r2);
				stringBuilder.append(f.getWord() + " ");
				w = model.get(findWord(f.getWord()));
			} // end for
			String s = stringBuilder.toString();
			sonnetArr[i] = s;
		} // end for
	} // end writeSonnet method
	public void keyPressed() 
	{
		
		if(key == sonnetRefreshKey) 
		{
			writeSonnet();
		} // end if
	} // end keyPressed method

	public void draw() 
    {
		clear();
		int lineSpacing = 30;

		for(int i = 0;i<sonnetArr.length;i++)
		{
			text(sonnetArr[i], 200,lineSpacing);
			lineSpacing += 30;
		} // end for
	} // end draw
} // end DANI Class
