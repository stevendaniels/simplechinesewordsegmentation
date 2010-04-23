/**
 * 
 */
package util;
import java.util.NoSuchElementException;

/**
 * @author ����
 * 
 * �����ı��е�һ������
 *
 */
public class LineScanner {
	/**
	 * һ���ı������������з�
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
		//���ļ��ж�ȡһ�У����������з�
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
		
		
		
		
		//������������ | Ϊ�ָ��ֳɴ�
		words = line.split("\\|");
		for (int i = 0; i < words.length; i++) 
			words[i] = words[i].trim();
		iterator = 0;
		return true;
	}
	

	public String nextWord(){
		//��ǰ�л���û�д��������
		if (words!= null &&
				iterator < words.length) 
			return words[iterator++];
		
		//��ǰ���Ѿ�ȫ�������꣬��������ļ����ٶ�ȡ��һ��
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
