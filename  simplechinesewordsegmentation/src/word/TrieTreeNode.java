package word;

import java.util.HashMap;

public class TrieTreeNode {
	private Char c;
	private WordInfo info;
	private HashMap<Integer, TrieTreeNode> next;
	
	public TrieTreeNode(int oneChar){
		this.c = new Char(oneChar);
		this.info = new WordInfo();
		this.next = null;
	}
	/**
	 * 在训练数据处理中，这个字又出现了一次
	 */
	public void meetAgain(){
		c.increaseFrequency();
	}
	
	/**
	 * 在本节点之后插入当前字的后缀
	 * @param suffix 当前汉字的后缀字
	 * @return 插入的后缀的引用
	 */
	public TrieTreeNode insertSuffix(int suffix){
		if(next == null)
			next = new HashMap<Integer, TrieTreeNode>();
		
		TrieTreeNode suffixNode = next.get(suffix);
		
		if (suffixNode == null) 	
			suffixNode = new TrieTreeNode(suffix);
		else
			suffixNode.meetAgain();
		
		next.put(suffix, suffixNode);
		return suffixNode;
	}
	
	/**
	 * 当前节点对应了一个词的最后一个字
	 */
	public void setLastChar(){
		info.meetLastChar();
	}
	
	/**
	 * 测试当前节点是否可以作为一个词的最后一个字
	 * @return
	 */
	public boolean isLast (){
		return info.isLast();
	}
	
	public TrieTreeNode getNextByChar (Char c)
	{
		return next.get(c.getCharValue());
	}
	
	@Override
	public int hashCode(){
		return c.hashCode();
	}
	
	@Override
	public boolean equals(Object o){
		TrieTreeNode t = (TrieTreeNode)o;
		if (this.c.equals(t.c))
			return true;
		
		return false;
	}
	
	@Override
	public String toString(){
		String content =c.toString();
		if(info.isWord()){
			content += " the end of the word with fre " + info.getFrequency();
		}
		return content ; 
	}
	
}
