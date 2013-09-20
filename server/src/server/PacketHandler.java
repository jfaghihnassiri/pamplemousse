// PACKAGE: SERVER
package server;

//Module imports
import java.net.DatagramPacket;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;

// Threadable PacketHandler class
public class PacketHandler extends Thread {
	
	// Static Constant definitions
	private static final int MAX_PKT_SIZE = 2048;
	private static final int MIN_PKT_SIZE = 10;
	
	// Packet parsing variables
	DatagramPacket dp;
	byte[] data;
	int ctl;
	int cmd;
	int seq;
	int ack;
	int len;
	byte[] payload;
	boolean ctl_ackreq;
	boolean ctl_ackrsp;
	boolean ctl_expire;
	
	
	// User variables
	boolean newUser;
	int userIndex;
	ArrayList<User> userList;
	
	
	// constructor, takes UDP packet and array of all users
	public PacketHandler(DatagramPacket dp, ArrayList<User> userList){
		this.dp = dp;
		this.userList = userList;
	}
	
	// runnable function for threading
	public void run(){
		// make sure the data meets the length requirements, otherwise ignore
		if( dp.getLength() < MIN_PKT_SIZE || dp.getLength() > MAX_PKT_SIZE ){
			return;
		}
		
		// print for debugging
		System.out.println("Incoming:  IP: " + dp.getAddress().getHostAddress());
		System.out.println("           PORT: " + dp.getPort());
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
		
		// parse data
		data = dp.getData();
		ctl = data[0]<<8 + data[1];
		cmd = data[2]<<8 + data[3];
		seq = data[4]<<8 + data[5];
		ack = data[6]<<8 + data[7];
		len = data[8]<<8 + data[9];
		// parse control flags
		ctl_ackreq = (ctl & 0x0001) > 0 ? true: false;
		ctl_ackrsp = (ctl & 0x0002) > 0 ? true: false;
		ctl_expire = (ctl & 0x0003) > 0 ? true: false;
		// parse payload if present
		if( len > 0 ){
			payload = Arrays.copyOfRange(data, 10, len);
		}
		
		
		// --- returns and kills thread ---
	}
	
	// determine if this user is new
	public int findUser(User user){
		if( userList.contains(user) ){
			return userList.indexOf(user);
		}
		return -1;
	}
}
