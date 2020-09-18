import java.io.IOException;

public class DecoderTester {
	public static void main (String [] args) throws IOException
	{
		Decoder liam = new Decoder ();
		liam.decode("message.lzw");
	}
}
