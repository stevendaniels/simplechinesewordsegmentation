package trietree;

import word.*;
import java.util.HashMap;

import toXML.ObjectWriterToXML;
import util.*;

public class TrieTree {
	/*
	 * ����ɢ�б�
	 */
	private HashMap<Integer, TrieTreeNode> firstChars;
	
	public TrieTreeNode getWordRefference(Char firstChar){
		int charValue = firstChar.getCharValue();
		return firstChars.get(charValue);
	}
	
	/**
	 * ���캯��
	 * ֱ���ɡ�ѵ�����ݡ��ļ��е��ı����ɴʵ�
	 * ��trie tree�ĸ�ʽ�洢
	 * 
	 * @param filePath ��ѵ�����ݡ��ļ�·��
	 */
	public TrieTree(String filePath){
		LineScanner lines = new LineScanner(filePath);
		
		//����5283���ַ������������С����Ϊ5297��װ����������Ϊ0.75
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
	 * ����һ���ʣ����ôʴ���Trie Tree�ʵ���
	 * 
	 * @param oneWord
	 */
	private void getOneWord(String oneWord){
		WordScanner word = new WordScanner(oneWord);
		
		int charactor = word.nextChar();
		TrieTreeNode currentNode = firstChar (charactor);	//����ʵ�����
		TrieTreeNode suffixNode = currentNode;
		
		while((charactor = word.nextChar())> 0 ){	//�������ʣ�µĺ���
			currentNode = suffixNode;
			suffixNode = processOneChar (charactor, currentNode);
		}
		suffixNode.setLastChar();
	}
	
	/**
	 * ������еĵ�һ����
	 * 
	 * @param firstchar ���еĵ�һ����
	 * @return ����������ɢ�б��е�����
	 */
	private TrieTreeNode firstChar(int firstchar){
		TrieTreeNode node = firstChars.get(firstchar);
		if(node == null)			//����hash�����޸��֣���hash�������һ���ڵ�
			node = new TrieTreeNode(firstchar);
		else
			node.meetAgain();		
		
		firstChars.put(firstchar, node);
		return node;
	}
	
	/**
	 * ������е������ĺ���
	 * ��������һ����
	 * 
	 * @param oneChar һ������
	 * @param node �ú���ǰ׺��
	 * @return ���˴ʼ���ʵ������á�����׺������
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
