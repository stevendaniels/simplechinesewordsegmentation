package word;

import java.util.ArrayList;

public class Word {
	private ArrayList<Char> word;
	private int iterator; 
	
	public Word(){
		word = new ArrayList<Char>();
		iterator = 0;
	}
	
	public Word(ArrayList<Char> word){
		this.word = word;
	}
	
	public Char getNextChar(){
		return word.get(iterator);
	}
	
	public void readOneChar(Char c){
		word.add(c);
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<word.size(); i++) {
			sb.append(word.get(i).toString());
			sb.append(' ');
		}
		sb.append('|');
		return sb.toString();
	}
	
}
