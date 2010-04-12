package main;

import java.io.*;
import java.util.*;

import segment.Segmentation;
import util.FileHandle;
import word.*;

public class Main {
	static String trainingFile="";
	static String testingFile="";
	static String resultFile="";
	
	public static void main(String[] args){
		getProperties();
		FileWriter resultOutput ;
		
		try			//���������
		{
			resultOutput = new FileWriter(new File(resultFile));
		}
		catch( IOException e)
		{
			e.printStackTrace();
			throw new RuntimeException();
		}
		
//		//���ɷִ���������ѵ�����ݣ���ʼ���ʵ�
		Segmentation seg = new Segmentation(trainingFile);
		//TODO : �ʵ仹�д��ء���5555����
		System.out.println("generate dictionary success");
		System.out.println("**********************************");
		
//		//�����������
		FileHandle testingFileHandle = new FileHandle(testingFile);
		String oneLineTesting;
		while( true)
		{
			try
			{
				oneLineTesting = testingFileHandle.readline();
				System.out.println("Main.java\t: I am segementing this line: "+oneLineTesting);
			}
			catch (NoSuchElementException e) 
			{
				System.out.println("all testing data processed");
				break;
			}
			
			displayOneLineResult(resultOutput ,seg, oneLineTesting);	
		}
		try
		{
			resultOutput.flush();
		}
		catch (IOException e)
		{
			throw new RuntimeException();
		}
	}
	
	/**
	 * ���һ�����ݵķִʽ��
	 * @param output
	 * @param seg
	 * @param oneLineTesting
	 */
	private static void displayOneLineResult(Writer output, Segmentation seg, String oneLineTesting)
	{
		seg.inputNextLine(oneLineTesting);
		ArrayList<Char> w;

		while ( (w = seg.nextWord()) != null)
		{
		 	Word word = new Word(w);
		 	System.out.println("Main.java: display: I've got a new word!\t"+ word.toString());
		 	appendToResult ( output, word );
		 	appendToResult ( output, "| " );
		}
		appendToResult ( output, "\n" );
	}
	
	/**
	 * ���������
	 * @param output
	 * @param word
	 */
	private static void appendToResult (Writer output, Word word) 
	{
		FileWriter resultOutput  = (FileWriter) output;
		String strWord = word.toString();
		
		try {
			resultOutput.write(strWord);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	private static void appendToResult (Writer output, String content) {
		FileWriter resultOutput  = (FileWriter) output;
		try {
			resultOutput.write(content);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	private static void getProperties(){
		Properties p = new Properties();
		try {
			p.load(new FileInputStream(new File("filepath.property")));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
		trainingFile = p.getProperty("trainingFile");
		testingFile = p.getProperty("testingFile");
		resultFile = p.getProperty("resultFile");
	}
	
}
