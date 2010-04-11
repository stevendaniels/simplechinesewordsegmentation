package segment;

import java.io.*;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import trietree.TrieTree;
import util.FileHandle;
import word.*;

/**
 * 利用最大匹配法对文本进行分词
 * @author 朱亮
 *
 */
public class Segmentation 
{
	/**
	 * 字典
	 */
	TrieTree dictionary;
	
	/*
	 * 将输入的一行数据，拆解成以Char类型的各个单字，保存在这个数组中
	 */
	private Char[] lineChars;
	
	/*
	 * 当前行的处理到的字的游标
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
	 * 根据输入的训练数据的文件路径
	 * 获取字典引用
	 * @param trainingFilePath 训练数据的文件路径
	 */
	public Segmentation (String trainingFilePath)
	{
		super();
		getDictionary(trainingFilePath);
	}
	
	/**
	 * 根据输入的训练数据的文件路径，获取字典引用
	 * 获取字典引用
	 * @param trainingFilePath 训练数据的文件路径
	 */
	public void getDictionary(String trainingFilePath)
	{
		this.dictionary = new TrieTree(trainingFilePath);
	}
	
	/**
	 * 输入下一行文本
	 * 并将文本转换成数值
	 * 分词器将对输入的该行数据进行分词
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
	 * 逐个的返回出输入文本的切词结果
	 * 一个词一个词的返回出来
	 * @return 	以ArraylList类型包装的一个词
	 * 			如果当前行的数据已经全部处理完，则返回null
	 */
	public ArrayList<Char> nextWord ()
	{
		if ( iterator >= lineChars.length )		//当前行的数据已经处理完成
			return null;
				
		int start, end;
		start  = iterator++;
		end = getWordEnd(start);
		
		return generateArayList(start, end);
	}
	
	
	/**
	 * 从给定的start处，搜寻在词典中匹配到的词的结尾处
	 * @param start	开始搜寻的首字
	 * @return		词的结尾位置
	 */
	private int getWordEnd(int start) 
	{
		int end=start;
		
		Char currentChar = lineChars[start];							//当前这个字作为一个词的首字
		TrieTreeNode node = dictionary.getWordRefference(currentChar);	//在首字典中查找该字
		
		while ( iterator < lineChars.length )	//直到搜索完当前行
		{
			if ( node == null ) 	//最大匹配完成
				break;
			
			if (node.isLast()) {	//标记找到的最后一个可以作为词结尾的字
				end = iterator;		//当最大匹配完成却不是一个词时，将从此处截断
			}
						
			currentChar = lineChars[iterator++];	//读取下一个字
			node = node.getNextByChar(currentChar);	//在词典当前节点搜索新读入的字的后缀
		}
		
		this.iterator = end + 1;					//end之后的字不算被处理
		return end;
	}
	
	/**
	 * 将原数据中的单个字，包装成ArrayList类型，表示一个词
	 * @param start 词的首字的位置
	 * @param end	词结束的位置的后一位置（+1）
	 * @return		一个词条，包装成ArrayList类型
	 */
	private ArrayList<Char> generateArayList (int start ,int end){
		ArrayList<Char> word = new ArrayList<Char>();
		word.add(lineChars[start]);				//首字肯定加入该词中
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
		
		try			//创建输出流
		{
			resultOutput = new FileWriter(new File(resultFile));
		}
		catch( IOException e)
		{
			throw new RuntimeException();
		}
		
//		//生成分词器，输入训练数据，初始化词典
		Segmentation seg = new Segmentation(trainingFile);
//		//输入测试数据
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
