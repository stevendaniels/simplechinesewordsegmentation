package util;

import java.io.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * @author 朱亮
 * 
 * 处理文件操作
 * 逐行读取文件数据
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
	 * 将文件数据一行一行的读取出来，并向上返回
	 * 
	 * @return 当前行，不包含结尾的行分隔符
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
