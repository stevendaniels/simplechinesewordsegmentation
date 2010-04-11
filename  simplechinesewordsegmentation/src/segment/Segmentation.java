package segment;

import java.util.ArrayList;

import trietree.TrieTree;
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
	 * ���������ѵ�����ݵ��ļ�·������ȡ�ֵ�����
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
		
		while ( iterator < lineChars.length )							//ֱ�������굱ǰ��
		{
			if (node.isLast()) {	//����ҵ������һ��������Ϊ�ʽ�β����
				end = iterator;		//�����ƥ�����ȴ����һ����ʱ�����Ӵ˴��ض�
			}
			
			if ( node == null ) 	//���ƥ�����
				break;
			
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
		
	}
	
}
