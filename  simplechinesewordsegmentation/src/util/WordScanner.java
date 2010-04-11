/**
 * 
 */
package util;

/**
 * @author 朱亮
 *
 * 处理一个词，将之切分成单个汉字
 */
public class WordScanner {
	private String[] charactors;
	private int iterator;
	
	public WordScanner(String word) {
		charactors = word.split(" ");
	}
	
	/**
	 * 返回汉字对应的数值
	 */
	public int nextChar(){
		//当前的词中还有更多的汉字，往后再读取一个
		while(iterator < charactors.length){
			String strC =  charactors[iterator++].trim();
			if (strC.length() > 0)			//略掉空格符
				return Integer.valueOf(strC);
		}
		return -1;
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i< charactors.length ;i++)
			sb.append(charactors[i] + ", ");
		return sb.toString();
	}
}
