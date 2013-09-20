// PACKAGE: SERVER
package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
//Module imports
import java.net.InetAddress;
import java.net.SocketException;

// Class User (object)
public class User {
	
	// Static Constant definitions
	public static final int MAX_USERNAME_LENGTH = 32;
	
	// Dynamic Class variables
	private InetAddress src_inet;
	private int src_port;
	protected DatagramSocket socket = null;
	private String username;
	private boolean authenticated;
	
	// Class constructor (no arguments)
	public User(){
		src_inet = null;
		src_port = 0;
		try {
			socket = new DatagramSocket();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		username = "";
		authenticated = false;
	}
	
	// Class constructor (src + port)
	public User(InetAddress inet, int port){
		this.src_inet = inet;
		this.src_port = port;
		try {
			socket = new DatagramSocket();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		username = "";
		authenticated = false;
	}
	
	// Send data to user
	public void sendData(byte[] data){
		DatagramPacket packet = new DatagramPacket(data, data.length, src_inet, src_port);
    	try {
			socket.send(packet);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// User variable set methods
	public void setInet(InetAddress inet){
		this.src_inet = inet;
	}
	public void setPort(int port){
		this.src_port = port;
	}
	public void setUsername(String username){
		this.username = username;
	}
	// User variable access methods
	public InetAddress getInet(){
		return this.src_inet;
	}
	public int getPort(){
		return this.src_port;
	}
	public String getUsername(){
		return this.username;
	}

	// User authentication
	public boolean attemptAuthentication(){
		return false;
	}
	
	// What defines if two Users are equal?
	@Override
	public boolean equals(Object other){
		// Two User objects are equal if they have the same src inet and src port
		if( this.src_inet.equals(((User)other).src_inet) && this.src_port == ((User)other).src_port )
			return true;
		return false;
	}
	
}// end User class
