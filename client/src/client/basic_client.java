package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class basic_client {

	private String hostname;
    private int port;
    Socket socketClient;
    BufferedReader stdIn;
    PrintWriter stdOut;

    public basic_client(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
        this.stdIn = null;
        this.stdOut = null;
    }

    public void connect() throws UnknownHostException, IOException{
        System.out.println("Attempting to connect to "+hostname+":"+port);
        socketClient = new Socket(hostname,port);
        System.out.println("Connection Established");
    }
    
    public void openStreams() throws UnknownHostException, IOException{
    	System.out.println("Attempting to open input stream");
    	stdIn = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
    	System.out.println("Attempting to open output stream");
    	stdOut = new PrintWriter(socketClient.getOutputStream(),true);
    }

    public void readCommand() throws IOException{
        String userInput;
        System.out.println("Response from server:");
        while ((userInput = stdIn.readLine()) != "Server response: End") {
        	System.out.println("Loop");
            System.out.println(userInput);
        }
    }
    
    public void writeCommand(String userOutput) throws IOException{
    	System.out.println("Transmitting...");
    	stdOut.println(userOutput);
    	System.out.println("Done!");
    }

    public static void main(String arg[]){
        //Creating a SocketClient object
        basic_client client = new basic_client ("localhost",10000);
        try {
            //trying to establish connection to the server
            client.connect();
            //try opening the streams
            client.openStreams();
            //if successful, read and write responses from/to server
            while(true) {
            	System.out.println("Enter something here : ");
            	try{
            		BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
            		String s = bufferRead.readLine();
            		client.writeCommand(s);
            		client.readCommand();
            		System.out.println("Functions write and read have returned");
            	}
            	catch(IOException e)
            	{
            		e.printStackTrace();
            	}
            }

        } catch (UnknownHostException e) {
            System.err.println("Host unknown. Cannot establish connection");
        } catch (IOException e) {
            System.err.println("Cannot establish connection. Server may not be up."+e.getMessage());
        }
    }


}
