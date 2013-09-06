package client;

import java.io.*;
import java.net.*;
import java.nio.charset.Charset;

public class PersistentListener extends Thread {

	protected DatagramSocket socket = null;
	protected InetAddress address;
	protected int port;
	protected ClientCommunicator caller = null;
	protected boolean connected = true;

	public PersistentListener() throws IOException {
		System.out.println("ERROR: Default constructor called on class PersistentListener");
	}

	public PersistentListener(ClientCommunicator caller, String name, InetAddress address, int port) throws IOException {
		super(name);
		this.address = address;
		this.port = port;
		this.caller = caller;
		this.socket = new DatagramSocket(port);
	}

	public void run() {
		while(connected) {
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
		socket.close();
	}

}
