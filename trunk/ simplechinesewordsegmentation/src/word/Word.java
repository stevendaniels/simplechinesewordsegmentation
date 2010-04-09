package word;

import java.util.ArrayList;

public class Word {
	private ArrayList<Char> word;
	private int iterator; 
	
	public Word(){
		word = new ArrayList<Char>();
		iterator = 0;
	}
	
	public Char getNextChar(){
		return word.get(iterator);
	}
	
	public void readOneChar(Char c){
		word.add(c);
	}
	
}
