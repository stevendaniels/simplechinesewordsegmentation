/**
 * 
 */
package word;

/**
 * @author ÷Ï¡¡
 *
 */
public class WordInfo {
	private int frequency;
	private boolean isWord;
	
	public WordInfo(){
		this.frequency = 0;
		this.isWord = false;
	}
	
	public boolean isWord(){
		return this.isWord;
	}
	
	public int getFrequency(){
		return this.frequency;
	}
	
	public void meetLastChar(){
		this.isWord = true;
		this.frequency ++;
	}
	
	public boolean isLast()
	{
		return isWord;
	}
}
