import java.util.*;
import java.io.*;
import java.util.HashMap;
public class Encoder {
	private HashMap <String,Integer> dict;
	private int counter, maxCodes;
	public Encoder(int maxCodes) {
		//dictionary
		dict = new HashMap<String, Integer>();
		counter = 0;
		this.maxCodes = maxCodes;
		setup(256);
	}
	public void encode (String fileName) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(new File(fileName)));
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File(fileName.substring(0, fileName.length()-4)+".lzw")));
		char current;
		String temp = "";//temp holds the string of characters to be compressed
		while (br.ready()) {
			current = (char) br.read();
			temp += current;
			while (isKey(temp) && br.ready()) {
				current = (char) br.read();
				temp += current;
			}
			//break case where we arent at the end of the file, and thus temp is no longer a key
			if (br.ready()) {
				try {
					write(addKey(temp), bw);
				} catch (Exception ex) {
					System.out.println(temp);
					System.out.println(dict.get(temp));
					System.out.println(addKey(temp));
			        ex.printStackTrace();
			    }
				temp = "" + current;
			} else {
				//break case  where we are at the end of a file, but temp is a key
				if (isKey(temp)) {
					write(dict.get(temp), bw);
				//break case where we are at the end of a file, but temp is not a key
				} else {
					write(addKey(temp), bw);
						write(dict.get(""+current), bw);
				}
			}
		}
		br.close();
		bw.flush();
		bw.close();
	}

	//writes the Integer onto the output file as one character
	private void write (Integer num, BufferedWriter writer) throws IOException {
		writer.write((char) (num.intValue()));


	}
	//sets up the first 256 ascii chars in the dictionary
	private void setup (int chars) {
		for (int i = 0; i <= chars; i++) {
			dict.put("" + (char) i, i);
		}
		counter = chars;
	}

	//checks if a string is a key in our dictionary
	private boolean isKey (String s) {
		return dict.containsKey(s);
	}

	//adds new string to HashMap with the value of counter. Increments counter. Returns the associated value of str - 1 letter.
	private Integer addKey (String str) {
		if (counter < maxCodes) {
			dict.put(str, counter);
			counter++;
		}
		return dict.get(str.substring(0,str.length()-1));
	}
}
