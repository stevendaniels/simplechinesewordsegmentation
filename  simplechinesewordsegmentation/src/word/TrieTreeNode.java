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
	
	public void meetAgain(){
		c.increaseFrequency();
	}
	
	/**
	 * �ڱ��ڵ�֮����뵱ǰ�ֵĺ�׺
	 * @param suffix ��ǰ���ֵĺ�׺��
	 * @return ����ĺ�׺������
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
	 * ��ǰ�ڵ��Ӧ��һ���ʵ����һ����
	 */
	public void isLastChar(){
		info.meetLastChar();
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
