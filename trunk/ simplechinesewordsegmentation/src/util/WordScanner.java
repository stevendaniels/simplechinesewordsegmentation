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
	
//	public boolean getWord(){
//		if (word != null && word.length() != 0){
//			charactors = word.split(" ");
//			iterator = 0;
//			return true;
//		}
//		return false;
//	}
	
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
		
		//��ǰ�Ĵ����Ѿ�û�и�������ˣ�����Ҫһ����
//		if(getWord()){
//			return charactors[iterator++].trim();
//		}
//		return null;
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i< charactors.length ;i++)
			sb.append(charactors[i] + ", ");
		return sb.toString();
	}
}
