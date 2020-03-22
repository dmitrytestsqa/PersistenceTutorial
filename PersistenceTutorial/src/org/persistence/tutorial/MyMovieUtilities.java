package org.persistence.tutorial;

import java.io.*;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class MyMovieUtilities {

	
	public static boolean saveStringToFile(String fileName, String saveString) {
		boolean saved = false;
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(fileName));
			try {
				bw.write(saveString);
				saved = true;
			}
			finally {
				bw.close();
			}
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
		return saved;
		
	}

	public static String getStringFromFile(String fileName) {
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		
		try {
			br = new BufferedReader(new FileReader(fileName));
				try {
					String s;
					while ((s = br.readLine()) != null) {
						sb.append(s);
						sb.append("\n");
					}	
				}
				finally {
					br.close();
				}
				
			}
		catch (IOException ex) {
			ex.printStackTrace();
		}
		return sb.toString();
	}

		
	public static String convertToXML(MyMovieLibrary ml) {
		XStream xstream = new XStream(new DomDriver());
		xstream.setMode(XStream.ID_REFERENCES);
		xstream.alias("person", Person.class);
		xstream.alias("book", Book.class);
		xstream.alias("myMovieLibrary", MyMovieLibrary.class);
		return xstream.toXML(ml);
	}

	public static MyMovieLibrary convertFromXML(String XMLString) {
		MyMovieLibrary ml = null;
		XStream xstream = new XStream(new DomDriver());
		xstream.setMode(XStream.ID_REFERENCES);
		xstream.alias("person", Person.class);
		xstream.alias("book", Book.class);
		xstream.alias("myMovieLibrary", MyMovieLibrary.class);
		Object obj = xstream.fromXML(XMLString);
		if( obj instanceof MyMovieLibrary) {
			ml = (MyMovieLibrary) obj;
		}
		return ml;
	}

	public static boolean saveMyMovieLibraryToXMLFile(String fileName, MyMovieLibrary ml) {
			return saveStringToFile(fileName, convertToXML(ml));
			}
	
	public static MyMovieLibrary getMyMovieLibraryFromXMLFile(String fileName) {
		return convertFromXML(getStringFromFile(fileName));
	}
}
