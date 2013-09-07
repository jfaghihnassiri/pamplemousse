package client;

import java.io.*;
import java.net.*;

public class ClientCommunicator {

	protected DatagramSocket socket = null;
	protected InetAddress address;
	protected int port;
    protected PersistentListener listener;

    public ClientCommunicator(String hostname, int port) throws UnknownHostException, IOException {
        this.address = InetAddress.getByName(hostname);
        this.port = port;
        this.socket = new DatagramSocket();
        this.listener = new PersistentListener(this,"the_listener",this.socket);
        this.listener.start();
    }
    
    public void writeCommand(byte[] buf) throws IOException{
    	DatagramPacket packet = new DatagramPacket(buf,buf.length,address,port);
    	socket.send(packet);
    }
    
    public void closeListener() {
    	listener.socket.close();
    }

}
