package server;

import java.io.*;
import java.net.*;

public class ClientCommunicator {
	protected DatagramSocket socket = null;
    protected PersistentListener listener;

    
    public ClientCommunicator(DatagramSocket socket) throws UnknownHostException, IOException {
        this.socket = socket;
        this.listener = new PersistentListener(this,"the_listener", this.socket);
        this.listener.start();
    }
    
    public void writeCommand(byte[] buf) throws IOException{
    	//DatagramPacket packet = new DatagramPacket(buf,buf.length, socket., port);
    	//socket.send(packet);
    }

}


