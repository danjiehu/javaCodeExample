import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class DailyAdviceServer {

	String[] adviceList = { 
			"take smaller bites",
			"be honest",
			"rethink that haircut" };
	
	public static void main(String[] args) {
		DailyAdviceServer server = new DailyAdviceServer();
		try {
			System.out.println("Go");
			server.go();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void go() throws IOException {
		try (ServerSocket serverSock = new ServerSocket(4242)) 
			 {
			
			System.out.println("Server is running");
			while(true)
			{
				Socket s = serverSock.accept();
				System.out.println("Connection accepted from Client");
				
				//Server sends advice to client
				PrintWriter writer = new PrintWriter(s.getOutputStream());
				String advice = getAdvice();
				writer.println(advice);
				writer.flush();
				
				//close the output stream
				writer.close();
				//close the socket
				s.close();
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	private String getAdvice() {
		int random = (int) (Math.random() * adviceList.length);
		return adviceList[random];
	}
}
