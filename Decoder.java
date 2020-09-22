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
	private final int ORIG_LENGTH = 256; //starting length of the arraylist
	private int maxLength;
	public Decoder (int maxLength) {
		this.maxLength = maxLength;
	}
	
	public void decode (String fileName) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(new File(fileName)));
		PrintWriter pw = new PrintWriter (new FileWriter (fileName.substring(0, fileName.length()-4)+"1.txt"));
		ArrayList<String> key = new ArrayList<String> ();
		
		//setting up my key with ascii characters 0-255
		for (int i = 0; i < ORIG_LENGTH; i++) {
			key.add(""+(char)i);
		}
		
		//declaring variables I will be using
		String numberToWord = ""; //this is the output that will be printed
		String addedFirstWord = ""; //the last thing that was just printed, used to add to our key with the next character (builds on itself)
		String addedSecondWord = ""; //new string being read in up until we don't already have it in our key
		int codedIndex; //this is what is read in by our buffered reader
		
		while (br.ready()) { //will continue intil the last character of the .lzw file is read in
			codedIndex = br.read();
			
			if (key.size() > codedIndex) { //this is the "typical case" in which the character we just read in (codedIndex) already exists within our key
				numberToWord = key.get(codedIndex); //getting the string associated with that character in our key
				pw.print(numberToWord); 
			}
			else { //this is the "special case" in which the character we read in (codedIndex) is not already in the key
				numberToWord = addedFirstWord + addedFirstWord.substring(0,1); //as discussed in class, we can simply use the last word + its first letter. ex: liam is the last word --> liaml
				key.add(numberToWord); //this is added to our key for storage
				pw.print(numberToWord);
			}
			
			addedSecondWord = numberToWord; //we store what was just printed
			
			if (!addedFirstWord.equals("") && key.size() < maxLength) { //this is simply to make sure that this isnt the very start of the .lzw file, in which we don't have anything to add to our key. (A more efficient way to do this may be with a counter, but oh well...)
				key.add(addedFirstWord + addedSecondWord.substring(0, 1)); //add to our key here
			}
			addedFirstWord = addedSecondWord; //what was just printed becomes stored for the next iteration of the while loop
			System.out.println(key.size());
		}
		br.close();
		pw.close();
	}
}
