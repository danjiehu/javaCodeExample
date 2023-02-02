
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class DailyAdviceClient {
	
	public static void main(String[] args) {
		DailyAdviceClient client = new DailyAdviceClient();
		try {
			client.go();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void go() throws UnknownHostException, IOException {
		Socket s = new Socket("localhost", 4242);
		System.out.println("Client connected to server ");
		
		InputStreamReader streamReader = new InputStreamReader(s.getInputStream());
		BufferedReader reader = new BufferedReader(streamReader);
		String advice = reader.readLine();
		System.out.println("Today, you should " + advice);
		
		//close the input stream
		reader.close();
		//close the socket
		s.close();
	}
}
