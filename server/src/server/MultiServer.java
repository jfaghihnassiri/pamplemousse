// PACKAGE: SERVER
package server;

// Module imports
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.ArrayList;

// Multi-user Server Class (non-object)
public class MultiServer {

	// Static Constant definitions
	private static final int SERVER_PORT = 45120;
	private static final int MAX_PKT_SIZE = 2048;
	private static final int MAX_NUM_USERS = 128;
	
	// Static Class Variables 
	private static DatagramSocket serverSocket;
	private static ArrayList<User> userList = new ArrayList<User>(MAX_NUM_USERS);
	
	// main loop
	public static void main(String[] args) throws IOException{
		System.out.println("Multi-user Server listening on " + SERVER_PORT + "...");
		
		// allocate memory for receiving new UDP packets
		byte[] buffer = new byte[MAX_PKT_SIZE];

		// create socket to receive all incoming UDP packets
		serverSocket = new DatagramSocket(SERVER_PORT);
		
		// loop forever
		while (true){
			try{
				// block for any incoming UDP packets and put data in buffer
				DatagramPacket packet =  new DatagramPacket(buffer, buffer.length );
				serverSocket.receive(packet);

				// spawn processing thread to parse received UDP packet
				PacketHandler handler = new PacketHandler(packet, userList);
				handler.start();

			}catch (Exception e){
				System.err.println("Error in connection attempt.");
			}
		}
	}
}