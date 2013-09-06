package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class MultiServer {

	private static final int SERVER_PORT = 9010;
	private static final int MAX_PKT_SIZE = 2048;
	private static int clientID = 1;
	private static DatagramSocket serverSocket;

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
			//try{
				DatagramPacket packet =  new DatagramPacket(buffer, buffer.length );
				serverSocket.receive(packet);
				System.out.println("SERVER: Accepted connection.");
				System.out.println("SERVER: received"+new String(packet.getData(), 0, packet.getLength()));

				ClientCommunicator comm = new ClientCommunicator(serverSocket);
				

			//}catch (Exception e){
			//	System.err.println("Error in connection attempt.");
			//}
		}
	}
}