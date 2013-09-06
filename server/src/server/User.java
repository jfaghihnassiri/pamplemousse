// Package definition
package server;

// Class User
public class User {
	
	// Constants
	public static final int MAX_USERNAME_LENGTH = 32;
	
	// Class variables
	private String src_ip;
	private int src_port;
	private String username;
	private boolean authenticated;
	
	// Class constructor
	public User(){
		src_ip = "";
		src_port = 0;
		username = "";
		authenticated = false;
	}
	public User(String ip, int port){
		this.src_ip = ip;
		this.src_port = port;
	}
	
	// User set methods
	public void setIp(String ip){
		this.src_ip = ip;
	}
	public void setPort(int port){
		this.src_port = port;
	}
	public void setUsername(String username){
		this.username = username;
	}
	// User access methods
	public String getIp(){
		return this.src_ip;
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
	
}// end User class
