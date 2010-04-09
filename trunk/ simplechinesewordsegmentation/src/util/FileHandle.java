package util;

import java.io.*;
import java.util.Scanner;

/**
 * @author ����
 * 
 * �����ļ�����
 * ���ж�ȡ�ļ�����
 *
 */
public class FileHandle {
	
	static Scanner sc = null;
	
	public FileHandle(String file){
		initialize(file,  "UTF-8");
	}
	
	public FileHandle(String file, String charset){
		initialize(file, charset);
	}
	
	private void initialize(String file, String charset){
		try {
			if (sc == null)
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
		FileHandle fl = new FileHandle("E:\\test.txt");
		String line = fl.readline();
		if (line != null)
			System.out.println(line);
		else
			System.out.println("No line");
	}
}
