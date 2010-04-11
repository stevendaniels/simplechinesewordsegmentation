/**
 * 
 */
package util;

/**
 * @author ����
 *
 * ����һ���ʣ���֮�зֳɵ�������
 */
public class WordScanner {
	private String[] charactors;
	private int iterator;
	
	public WordScanner(String word) {
		charactors = word.split(" ");
	}
	
	/**
	 * ���غ��ֶ�Ӧ����ֵ
	 */
	public int nextChar(){
		//��ǰ�Ĵ��л��и���ĺ��֣������ٶ�ȡһ��
		while(iterator < charactors.length){
			String strC =  charactors[iterator++].trim();
			if (strC.length() > 0)			//�Ե��ո��
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
