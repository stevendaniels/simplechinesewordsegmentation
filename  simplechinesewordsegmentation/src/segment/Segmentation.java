package segment;

import java.io.*;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import trietree.TrieTree;
import util.FileHandle;
import word.*;

/**
 * �������ƥ�䷨���ı����зִ�
 * @author ����
 *
 */
public class Segmentation 
{
	/**
	 * �ֵ�
	 */
	TrieTree dictionary;
	
	/*
	 * �������һ�����ݣ�������Char���͵ĸ������֣����������������
	 */
	private Char[] lineChars;
	
	/*
	 * ��ǰ�еĴ������ֵ��α�
	 */
	private int iterator;
	
	
	/**
	 * Constructor
	 */
	public Segmentation(){
		super();
	}
	
	/**
	 * Constructor
	 * ���������ѵ�����ݵ��ļ�·��
	 * ��ȡ�ֵ�����
	 * @param trainingFilePath ѵ�����ݵ��ļ�·��
	 */
	public Segmentation (String trainingFilePath)
	{
		super();
		getDictionary(trainingFilePath);
	}
	
	/**
	 * ���������ѵ�����ݵ��ļ�·������ȡ�ֵ�����
	 * ��ȡ�ֵ�����
	 * @param trainingFilePath ѵ�����ݵ��ļ�·��
	 */
	public void getDictionary(String trainingFilePath)
	{
		this.dictionary = new TrieTree(trainingFilePath);
	}
	
	/**
	 * ������һ���ı�
	 * �����ı�ת������ֵ
	 * �ִ�����������ĸ������ݽ��зִ�
	 * @param line
	 */
	public void inputNextLine (String line)
	{
		String[] strWords = line.split(" ");
		this.lineChars = new Char[strWords.length];
		for(int i = 0; i < this.lineChars.length; i++)
		{
			Char c = new Char(strWords[i]);
			this.lineChars[i] = c;
		}
		this.iterator = 0;
	}
	
	
	
	
	/**
	 * ����ķ��س������ı����дʽ��
	 * һ����һ���ʵķ��س���
	 * @return 	��ArraylList���Ͱ�װ��һ����
	 * 			�����ǰ�е������Ѿ�ȫ�������꣬�򷵻�null
	 */
	public ArrayList<Char> nextWord ()
	{
		if ( iterator >= lineChars.length )		//��ǰ�е������Ѿ��������
			return null;
				
		int start, end;
		start  = iterator++;
		end = getWordEnd(start);
		
		return generateArayList(start, end);
	}
	
	
	/**
	 * �Ӹ�����start������Ѱ�ڴʵ���ƥ�䵽�ĴʵĽ�β��
	 * @param start	��ʼ��Ѱ������
	 * @return		�ʵĽ�βλ��
	 */
	private int getWordEnd(int start) 
	{
		int end=start;
		
		Char currentChar = lineChars[start];							//��ǰ�������Ϊһ���ʵ�����
		TrieTreeNode node = dictionary.getWordRefference(currentChar);	//�����ֵ��в��Ҹ���
		
		while ( iterator < lineChars.length )	//ֱ�������굱ǰ��
		{
			if ( node == null ) 	//���ƥ�����
				break;
			
			if (node.isLast()) {	//����ҵ������һ��������Ϊ�ʽ�β����
				end = iterator;		//�����ƥ�����ȴ����һ����ʱ�����Ӵ˴��ض�
			}
						
			currentChar = lineChars[iterator++];	//��ȡ��һ����
			node = node.getNextByChar(currentChar);	//�ڴʵ䵱ǰ�ڵ������¶�����ֵĺ�׺
		}
		
		this.iterator = end + 1;					//end֮����ֲ��㱻����
		return end;
	}
	
	/**
	 * ��ԭ�����еĵ����֣���װ��ArrayList���ͣ���ʾһ����
	 * @param start �ʵ����ֵ�λ��
	 * @param end	�ʽ�����λ�õĺ�һλ�ã�+1��
	 * @return		һ����������װ��ArrayList����
	 */
	private ArrayList<Char> generateArayList (int start ,int end){
		ArrayList<Char> word = new ArrayList<Char>();
		word.add(lineChars[start]);				//���ֿ϶�����ô���
		for (int i=start+1; i < end; i++) {		
			word.add(lineChars[i]);
		}
		
		return word;
	}
	
	public static void main(String[] args){
		String trainingFile = "D:\\code\\java\\computationalLinguistics\\data\\mytest\\my_simple_training.txt";
		String testingFile = "D:\\code\\java\\computationalLinguistics\\data\\mytest\\my_simple_testing.txt";
		String resultFile = "D:\\code\\java\\computationalLinguistics\\data\\mytest\\my_simple_result.txt";
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
	}
	
	private static void displayOneLineResult(Writer output, Segmentation seg, String oneLineTesting)
	{
		seg.inputNextLine(oneLineTesting);
		ArrayList<Char> w;

		while ( (w = seg.nextWord()) != null)
		{
		 	Word word = new Word(w);
		 	appendToResult ( output,word);
		}
	}
	
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
	
}
