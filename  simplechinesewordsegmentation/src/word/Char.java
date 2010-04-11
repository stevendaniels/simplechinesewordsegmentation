package word;

public class Char {
	private int charValue;
	private int frequency;
	
	public Char(int c){
		this.charValue = c;
		this.frequency = 1;
	}
	
	public Char(String c){
		this.charValue = Integer.valueOf(c);
		this.frequency = 1;
	}
	
	public int getCharValue(){
		return this.charValue;
	}
	
	/**
	 * 又一次出现了这个汉字，将之出现频率+1
	 */
	public void increaseFrequency(){
		this.frequency++;
	}
	
	@Override
	public int hashCode(){
//		if (charValue < 10000){
//			return charValue % 787 ;
//		}
		return (charValue % 5297);
	}
	
	@Override
	public boolean equals(Object o){
		Char c = (Char) o;
		if(c.charValue == this.charValue)
			return true;
		return false;
	}
	
	@Override
	public String toString(){
		return charValue + " with " + frequency;
	}
}
