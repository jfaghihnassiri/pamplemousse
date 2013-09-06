package server;

import java.io.*;
import java.net.*;
import java.nio.charset.Charset;

public class PersistentListener extends Thread {

	protected DatagramSocket socket = null;
	protected ClientCommunicator caller = null;
	protected boolean connected = true;

	public PersistentListener() throws IOException {
		System.out.println("ERROR: Default constructor called on class PersistentListener");
	}

	public PersistentListener(ClientCommunicator caller, String name, DatagramSocket socket) throws IOException {
		super(name);
		this.caller = caller;
		this.socket = socket;
	}

	public void run() {
		while(connected) {
			// yield processor to other threads
			try {
				Thread.sleep(5);  // 5ms
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			try {
				// create a buffer 
				byte[] buf = new byte[2048];

				// receive request
				DatagramPacket packet = new DatagramPacket(buf,buf.length);
				socket.receive(packet);

				// TODO call the command parser
				
				// call the adder for the queue
				System.out.println(new String(buf, Charset.forName("ISO-8859-1")));
				
			}
			catch (IOException e) {
				e.printStackTrace();
				connected = false;
			}
		}
		
		// clean up the socket connection
		socket.close();
	}

}

