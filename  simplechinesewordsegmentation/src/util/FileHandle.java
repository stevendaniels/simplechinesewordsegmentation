package util;

import java.io.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * @author ����
 * 
 * �����ļ�����
 * ���ж�ȡ�ļ�����
 *
 */
public class FileHandle {
	
	Scanner sc = null;
	
	public FileHandle(String file){
		initialize(file,  "UTF-8");
	}
	
	public FileHandle(String file, String charset){
		initialize(file, charset);
	}
	
	private void initialize(String file, String charset){
		try {
			sc = new Scanner(new File(file), charset);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	/**
	 * ���ļ�����һ��һ�еĶ�ȡ�����������Ϸ���
	 * 
	 * @return ��ǰ�У���������β���зָ���
	 */
	public String readline(){
		return sc.nextLine();
	}
	
	public static void main(String[] args){
		String testingFile = "D:\\code\\java\\computationalLinguistics\\data\\mytest\\my_simple_testing.txt";
		FileHandle fl = new FileHandle(testingFile);
		String line;
		while( true)
		{
			try
			{
				line = fl.readline();
				System.out.println(line);
			}
			catch (NoSuchElementException e) 
			{
				System.err.println("all testing data processed");
				break;
			}
		}
	}
}
