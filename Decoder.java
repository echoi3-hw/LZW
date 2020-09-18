import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

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
		String numberToWord = "";
		//I am able to get the number correctly
		while (br.ready()) {
			String addedFirstWord = "";
			String addedSecondWord = "";
			int codedLetter = br.read();
			if (key.size() < codedLetter) {
				numberToWord += key.get(key.indexOf(codedLetter));
				pw.print(numberToWord);
			}
			else {
				key.add(addedFirstWord + addedFirstWord.substring(0,1));
				numberToWord += key.get(key.indexOf(codedLetter));
				pw.print(numberToWord);
			}
			addedSecondWord = numberToWord;
			if (!addedFirstWord.equals("")) {
				key.add(addedFirstWord + addedSecondWord);
			}
			addedFirstWord = addedSecondWord;
			addedSecondWord = "";
		}
		br.close();
		pw.close();
	}
}
