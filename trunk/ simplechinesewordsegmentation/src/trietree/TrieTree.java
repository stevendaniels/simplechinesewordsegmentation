package trietree;

import word.*;
import java.util.HashMap;

import toXML.ObjectWriterToXML;
import util.*;

public class TrieTree {
	/*
	 * 首字散列表
	 */
	private HashMap<Integer, TrieTreeNode> firstChars;
	
	public TrieTreeNode getWordRefference(Char firstChar){
		int charValue = firstChar.getCharValue();
		return firstChars.get(charValue);
	}
	
	/**
	 * 构造函数
	 * 直接由“训练数据”文件中的文本生成词典
	 * 以trie tree的格式存储
	 * 
	 * @param filePath “训练数据”文件路径
	 */
	public TrieTree(String filePath){
		LineScanner lines = new LineScanner(filePath);
		
		//共有5283个字符，比它大的最小质数为5297，装载因子设置为0.75
		firstChars = new HashMap<Integer, TrieTreeNode>(7100); 
		String word = null;
		int i = 0;
		while((word = lines.nextWord()) != null)
		{
			getOneWord(word);
			i++;
		}
		System.out.println("totally trained "+i+" words");
	}
	
	/**
	 * 处理一个词，将该词存入Trie Tree词典中
	 * 
	 * @param oneWord
	 */
	private void getOneWord(String oneWord){
		WordScanner word = new WordScanner(oneWord);
		
		int charactor = word.nextChar();
		TrieTreeNode currentNode = firstChar (charactor);	//处理词的首字
		TrieTreeNode suffixNode = currentNode;
		
		while((charactor = word.nextChar())> 0 ){	//逐个处理剩下的汉字
			currentNode = suffixNode;
			suffixNode = processOneChar (charactor, currentNode);
		}
		suffixNode.setLastChar();
	}
	
	/**
	 * 处理词中的第一个字
	 * 
	 * @param firstchar 词中的第一个字
	 * @return 该字在首字散列表中的引用
	 */
	private TrieTreeNode firstChar(int firstchar){
		TrieTreeNode node = firstChars.get(firstchar);
		if(node == null)			//首字hash表中无该字，在hash表中添加一个节点
			node = new TrieTreeNode(firstchar);
		else
			node.meetAgain();		
		
		firstChars.put(firstchar, node);
		return node;
	}
	
	/**
	 * 处理词中的其它的汉字
	 * 不包括第一个字
	 * 
	 * @param oneChar 一个汉字
	 * @param node 该汉字前缀字
	 * @return 将此词加入词典后的引用——后缀的引用
	 */
	private TrieTreeNode processOneChar(int oneChar, TrieTreeNode currentNode){
		return 	currentNode.insertSuffix(oneChar);
	}
	
	
	public static void main(String[] args){
		//TODO: The entrance of this program is here.
		TrieTree tree = new TrieTree("data\\test.txt");
		String treeContent = ObjectWriterToXML.toXML(tree);
		ObjectWriterToXML.toFile("data\\result.xml");
		System.out.println(treeContent);
		
//		getMinPrimer(5283);
	}
	
//	private static void getMinPrimer(int start){
//		int i = start;
//		for (;; i++){
//			if(isPrimer(i)){
//				System.out.println("find!\t"+i);
//				return;
//			}
//			System.err.println(i);
//		}
//	}
//	
//	private static  boolean isPrimer(int value){
//		int i = 2;
//		for(;i*i < value; i++){
//			if(value % i == 0)
//				return false;
//		}
//		return true;
//	}
	
}
