package util;

import java.io.*;
import java.util.Scanner;

/**
 * @author 朱亮
 * 
 * 处理文件操作
 * 逐行读取文件数据
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
	 * 将文件数据一行一行的读取出来，并向上返回
	 * 
	 * @return 当前行，不包含结尾的行分隔符
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
