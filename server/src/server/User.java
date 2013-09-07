// Package definition
package server;

import java.net.InetAddress;

// Class User
public class User {
	
	// Constants
	public static final int MAX_USERNAME_LENGTH = 32;
	
	// Class variables
	private InetAddress src_inet;
	private int src_port;
	private String username;
	private boolean authenticated;
	
	// Class constructor
	public User(){
		src_inet = null;
		src_port = 0;
		username = "";
		authenticated = false;
	}
	public User(InetAddress inet, int port){
		this.src_inet = inet;
		this.src_port = port;
	}
	
	// User set methods
	public void setInet(InetAddress inet){
		this.src_inet = inet;
	}
	public void setPort(int port){
		this.src_port = port;
	}
	public void setUsername(String username){
		this.username = username;
	}
	// User access methods
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
	
	// Are two users equal?
	@Override
	public boolean equals(Object other){
		if( this.src_inet.equals(((User)other).src_inet) && this.src_port == ((User)other).src_port )
			return true;
		return false;
	}
	
}// end User class
