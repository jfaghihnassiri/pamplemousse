package client;

import java.io.*;
import java.net.*;
import java.util.*;

public class Pamplemousse {
	
	public static void main(String[] args) {
		System.out.println("Client Started");
		System.out.print("Port:");
		Scanner in = new Scanner(System.in);
		int port = in.nextInt();
		System.out.println("Got port "+port);
        try {
    		//Creating a SocketClient object
        	System.out.println("Pre-constructor");
        	ClientCommunicator client = new ClientCommunicator ("localhost",port);
        	System.out.println("Pre-constructor");
        	//Continuously take in user input, send it to the server, and print the response
            boolean connected = true;
            while(connected) {
            	byte[] buf = new byte[80];
            	System.out.print("Message:");
            	System.in.read(buf);
            	client.writeCommand(buf);
            	if("exit"==buf.toString()) {
            		connected = false;
            	}
            }
            client.closeListener();
            System.out.println("Closed connection successfully");
        } catch (UnknownHostException e) {
            System.err.println("Host unknown. Cannot establish connection");
        } catch (IOException e) {
            System.err.println("Cannot establish connection. Server may not be up."+e.getMessage());
        }
	}

}
