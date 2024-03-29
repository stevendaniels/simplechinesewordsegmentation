package segment;

import java.util.ArrayList;

import toXML.ObjectWriterToXML;
import trietree.TrieTree;
import word.*;
//import toXML.ObjectWriterToXML;

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
	protected TrieTree dictionary;
	
	/*
	 * 将输入的一行数据，拆解成以Char类型的各个单字，保存在这个数组中
	 */
	protected Char[] lineChars;
	
	/*
	 * 当前行的处理到的字的游标
	 */
	protected int iterator;
	
	
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
	 * 
	 * @param trainingFilePath 训练数据的文件路径
	 */
	public void getDictionary(String trainingFilePath)
	{
		this.dictionary = new TrieTree(trainingFilePath);
//		ObjectWriterToXML.toXML(this.dictionary);
//		ObjectWriterToXML.toFile("data\\dictionary.xml");
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
		ArrayList<Char> chars = new ArrayList<Char>();
		for(int i = 0; i < strWords.length; i++)
		{
			try{
				Char c = new Char(strWords[i]);
				chars.add(c);
			}catch (NumberFormatException e) {}
		}
		this.lineChars =new Char[chars.size()];
		chars.toArray(this.lineChars);
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
	 * 算法核心
	 * 最大匹配 分词法
	 * 从给定的start处，搜寻在词典中匹配到的词的结尾处
	 * @param start	开始搜寻的首字
	 * @return		词的结尾位置
	 */
	protected int getWordEnd(int start) 
	{
		int end=start+1;
		
		Char currentChar = lineChars[start];							//当前这个字作为一个词的首字
		TrieTreeNode node = dictionary.getWordRefference(currentChar);	//在首字典中查找该字
		
		if (node == null) {	//首字典中无此字
			return end;
		}
		
		while ( iterator < lineChars.length )	//直到搜索完当前行
		{
			if ( node == null ) 	//最大匹配完成
				break;				//即上一个字没有后缀，上一次循环必然标记了end
			
			if (node.isLast()) {	//标记找到的最后一个可以作为词结尾的字
				end = iterator;		//当最大匹配完成却不是一个词时，将从此处截断
			}
						
			currentChar = lineChars[iterator++];	//读取下一个字
			node = node.getNextByChar(currentChar);	//在词典搜索：以currentChar为后缀的词，返回后缀的引用
		}
		
		if (iterator == lineChars.length)		//遍历到最后
			if (node != null && node.isLast())
				end = iterator;
			
		
		this.iterator = end;					//end之后的字不算被处理
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
	
	
}
