package main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import segment.Segmentation;
import util.FileHandle;
import word.Char;
import word.Word;

public class Main {
	public static void main(String[] args){
		String trainingFile = "data\\mytest\\my_simple_training.txt";
		String testingFile = "data\\mytest\\my_simple_testing.txt";
		String resultFile = "data\\mytest\\my_simple_result.txt";
		FileWriter resultOutput ;
		
		try			//���������
		{
			resultOutput = new FileWriter(new File(resultFile));
		}
		catch( IOException e)
		{
			throw new RuntimeException();
		}
		
//		//���ɷִ���������ѵ�����ݣ���ʼ���ʵ�
		Segmentation seg = new Segmentation(trainingFile);
//		//�����������
		FileHandle testingFileHandle = new FileHandle(testingFile);
		String oneLineTesting;
		while( true)
		{
			try
			{
				oneLineTesting = testingFileHandle.readline();
				System.out.println(oneLineTesting);
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
		catch (Exception e)
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
		System.out.println(strWord);
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
	
}
