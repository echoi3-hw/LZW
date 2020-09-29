import java.io.IOException;

public class Queue
{
	
	public static void main (String [] args) throws IOException
	{
		Encoder eli = new Encoder (4097);
		eli.encode("message.txt");
	}
}
