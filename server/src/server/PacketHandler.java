package server;

import java.net.DatagramPacket;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;

public class PacketHandler extends Thread {
	DatagramPacket dp;
	boolean newUser;
	int userIndex;
	ArrayList<User> userList;
	
	
	// constructor
	public PacketHandler(DatagramPacket dp, ArrayList<User> userList){
		this.dp = dp;
		this.userList = userList;
	}
	
	public void run(){
		// is it a new user?
		
		System.out.println("SERVER: Incoming connection:");
		System.out.println("        IP: " + dp.getAddress().getHostAddress());
		System.out.println("        PORT: " + dp.getPort());
		
		System.out.println(new String(Arrays.copyOfRange(dp.getData(), 0, dp.getLength()), Charset.forName("ISO-8859-1")) );
		
		// does this user exist already?
		User thisUser = new User(dp.getAddress(), dp.getPort());
		userIndex = findUser(thisUser);

		if( userIndex == -1 ){
			// add user to userList
			userList.add(thisUser);
			System.out.println("added new user");
		}
		
		// print the userList
		System.out.println(" ------ USER LIST ---------");
		for (User u : userList){
			System.out.println("User: " + u.getInet().getHostAddress() + ", " + u.getPort());
		}
		
		// --- returns and kills thread ---
	}
	
	// is this a new user?
	public int findUser(User user){
		if( userList.contains(user) ){
			return userList.indexOf(user);
		}
		return -1;
	}
}
