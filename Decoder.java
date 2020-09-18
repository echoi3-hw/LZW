import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Decoder {
	public Decoder () {
	}
	
	public void decode (String fileName) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(new File(fileName)));
		PrintWriter pw = new PrintWriter (new FileWriter (fileName.substring(0, fileName.length()-4)+".txt"));
		ArrayList<String> key = new ArrayList<String> ();
		for (int i = 0; i < 256; i++) {
			key.add(""+(char)i);
		}
		String updatedWord = "";
		//I am able to get the number correctly
		while (br.ready()) {
			int codedLetter = br.read();
			updatedWord += (char)codedLetter;
			if (!key.contains(updatedWord)) {
				pw.print(key.get(key.indexOf(updatedWord.substring(0, updatedWord.length()-1))));
			}
			if (!key.contains(updatedWord)) {//change
				key.add(updatedWord);
				updatedWord = "";
			}
			
		}
	}
}
