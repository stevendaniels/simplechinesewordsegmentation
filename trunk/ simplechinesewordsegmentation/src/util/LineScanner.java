/**
 * 
 */
package util;
import java.util.NoSuchElementException;

/**
 * @author 朱亮
 * 
 * 分析文本中的一个整行
 *
 */
public class LineScanner {
	/**
	 * 一行文本，不包含换行符
	 */
	
	
	private FileHandle fileHandle;
	private String filepath;
	private String[] words;
	private int iterator;
	
	public LineScanner(String file){
		this.filepath = file;
		fileHandle = new FileHandle(file);
		getLine();
	}
	
	
	private boolean getLine(){
		//从文件中读取一行，不包含换行符
		String line ;
		try{
			while (true) {
				line = fileHandle.readline();
				if(line ==null){
					System.out.println("all data in "+filepath+" processed");
					words = null;
					return false;
				}
				if (line.length() > 1) {
					break;
				}
			}
		} catch (NoSuchElementException e) {
			line = null;
			System.out.println("all data in "+filepath+" processed");
			words = null;
			return false;
		}
		
		
		
		
		//将该行数据以 | 为分割拆分成词
		words = line.split("\\|");
		for (int i = 0; i < words.length; i++) 
			words[i] = words[i].trim();
		iterator = 0;
		return true;
	}
	

	public String nextWord(){
		//当前行还有没有处理的数据
		if (words!= null &&
				iterator < words.length) 
			return words[iterator++];
		
		//当前行已经全部处理完，则继续从文件中再读取出一行
		if(getLine()){
			return words[iterator++];
		}
		
		return null;
	}
	
	public static void main(String[] args){
		LineScanner lines = new LineScanner("E:\\test.txt");
		String word = null;
		while((word = lines.nextWord()) != null){
			System.out.println(word);
		}
	}
}
