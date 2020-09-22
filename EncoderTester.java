import java.io.IOException;

public class EncoderTester{
	public static void main (String [] args) throws IOException
	{
		Encoder eli = new Encoder (40000);
		eli.encode("message.txt");
	}
}
