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
	
	static FileHandle fileHandle;
	private String[] words;
	private int iterator;
	
	public LineScanner(String filePath){
		if (fileHandle == null)
			fileHandle = new FileHandle(filePath);
		getLine();
	}
	
	
	private boolean getLine(){
		//���ļ��ж�ȡһ�У����������з�
		String line ;
		try{
			line = fileHandle.readline();
		} catch (NoSuchElementException e) {
			line = null;
		}
		
		if(line ==null || line.length() == 0){
			System.out.println("all data in the file processed");
			words = null;
			return false;
		}
		//������������ | Ϊ�ָ��ֳɴ�
		words = line.split("\\|");
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
