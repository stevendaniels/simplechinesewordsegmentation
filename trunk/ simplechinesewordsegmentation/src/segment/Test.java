package segment;

import java.io.*;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException{
		String resultFile = "data\\mytest\\my_simple_result_t.txt";
		FileWriter resultOutput = null;
		
		//���������
		resultOutput = new FileWriter(new File(resultFile));
		
		String content = "abcd";		
		resultOutput.write(content);
		resultOutput.flush();
		resultOutput.close();
	}

}
