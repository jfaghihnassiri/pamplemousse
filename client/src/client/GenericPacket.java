package client;

import java.nio.*;
import java.util.Arrays;

public class GenericPacket {
	
	public byte[] totalCommand; 		// 10-2048 bytes
	public int length;

	public byte[] controlSignals; 		// 2 bytes
	public byte[] commandType; 			// 2 bytes
	public byte[] sequenceNumber; 		// 2 bytes
	public byte[] acknowledgeNumber; 	// 2 bytes
	public byte[] dataLength; 			// 2 bytes
	public byte[] commandData; 			// 0-2038 bytes
	
	public GenericPacket(){
		System.out.println("ERROR: Default constructor called on class Generic Packet");
	}
	
	public GenericPacket(byte[] totalInput){
		controlSignals = Arrays.copyOfRange(totalInput, 0, 2);
		commandType = Arrays.copyOfRange(totalInput, 2, 4);
		sequenceNumber = Arrays.copyOfRange(totalInput, 4, 6);	
		acknowledgeNumber = Arrays.copyOfRange(totalInput, 6, 8);	
		dataLength = Arrays.copyOfRange(totalInput, 8, 10);	
		
		ByteBuffer wrapped = ByteBuffer.wrap(dataLength);
		length = (int)wrapped.getShort() + 10;
		
		commandData = Arrays.copyOfRange(totalInput, 10, length);
		totalCommand = Arrays.copyOfRange(totalInput, 0, length);
	}
	
	public GenericPacket(byte[] controlSignals,byte[] commandType,byte[] sequenceNumber,
			byte[] acknowledgeNumber,byte[] dataLength,byte[] commandData) {
		this.controlSignals = controlSignals;
		this.commandType = commandType;
		this.sequenceNumber = sequenceNumber;
		this.acknowledgeNumber = acknowledgeNumber;	
		this.dataLength = dataLength;	
		this.commandData = commandData;
		
		ByteBuffer wrapped = ByteBuffer.wrap(dataLength);
		length = (int)wrapped.getShort() + 10;
		
		totalCommand = new byte[length];
		ByteBuffer tmpTotal = ByteBuffer.wrap(totalCommand);
		tmpTotal.put(controlSignals);
		tmpTotal.put(commandType);
		tmpTotal.put(sequenceNumber);
		tmpTotal.put(acknowledgeNumber);
		tmpTotal.put(dataLength);
		if(length>10) tmpTotal.put(commandData);
		totalCommand = tmpTotal.array();
	}
	
}
