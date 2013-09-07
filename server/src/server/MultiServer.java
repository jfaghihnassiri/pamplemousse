package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.ArrayList;

public class MultiServer {

	private static final int SERVER_PORT = 10010;
	private static final int MAX_PKT_SIZE = 2048;
	private static final int MAX_NUM_USERS = 128;
	
	private static DatagramSocket serverSocket;
	
	private static ArrayList<User> userList = new ArrayList<User>(MAX_NUM_USERS);
	

	public static void main(String[] args) throws IOException
	{
		System.out.println("Server started.");
		byte[] buffer = new byte[MAX_PKT_SIZE];

		/**
		 * ASSIGNMENT INSTRUCTION The server should be multi-threaded, and
		 * have one thread per connection.
		 */
		serverSocket = new DatagramSocket(SERVER_PORT);
		
		while (true){
			try{
				DatagramPacket packet =  new DatagramPacket(buffer, buffer.length );
				serverSocket.receive(packet);

				// spawn processing thread
				PacketHandler handler = new PacketHandler(packet, userList);
				handler.start();

			}catch (Exception e){
				System.err.println("Error in connection attempt.");
			}
		}
	}
}