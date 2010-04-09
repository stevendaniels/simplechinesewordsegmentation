package toXML;

import java.io.*;

import com.thoughtworks.xstream.XStream;

public class ObjectWriterToXML {
	static Object object;
	static String result;

	/**
	 * @param args
	 */
	public static String toXML(Object ob) {
		object = ob;
		XStream xstream = new XStream();
		result =xstream.toXML(object); 
		return result;
	}
	
	public static void toFile(String filepath) {
		FileWriter w;
		try {
			w = new FileWriter(new File(filepath));
			w.append(result);
			w.flush();
			w.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
		
		
	}

}
