package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.net.ConnectException;

import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

/**
 * Manages the data, logic and rules of the program.
 * @author Jack
 *
 */
public class ClientModel {
	private PrintWriter connectionOutput;
	private BufferedReader connectionInput;
	private Socket connectionSocket;
	private Document messageDocument;
	private Document sendDocument;
	private Document hostNameDocument;
	private Document portDocument;

	public ClientModel(String hostName, int portNumber) {
		connectionSocket = new Socket(); // Create unconnected socket
	}

	/**
	 * Connects to the server at the current port and host. 
	 * @return true iff successfully connected to server
	 */
	public boolean connectToMessageServer() {
		try {	
			// Set up connection
//			System.out.printf("'%s' '%d'", getHostName(), getPortNumber());
			connectionSocket = new Socket(getHostName(), getPortNumber());
		    connectionOutput = new PrintWriter(connectionSocket.getOutputStream(), true);
		    connectionInput = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
		    
		    //When app is closed, close the connection socket
		    Runtime.getRuntime().addShutdownHook(new Thread() {
		        @Override
		        public void run() {
	            	endMessageServerConnection();
		        }
		    });
		    
		    return true;
		    
		} catch(UnknownHostException | ConnectException e) {
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public void endMessageServerConnection() {
		try {
			connectionSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Attempt to send message to server and update message text field.
	 * @return true iff socket is connected
	 */
	public boolean sendMessage() {
		if (connectionSocket.isConnected()) {
			try {
				// Put the message in the message document so it is displayed locally
				String messageInSendBox = getMessageInSendBox();
				messageDocument.insertString(messageDocument.getLength(), getMessageInSendBox() + "\n", null);
				// Clear the message in the send box
				sendDocument.remove(0, sendDocument.getLength());
				// Send out message to server
				getConnectionOutput().println(messageInSendBox);
			} catch (BadLocationException e) {
				e.printStackTrace();
			}
			return true;
		}
		return false; // If the socket is not connected return false
	}
	
	public PrintWriter getConnectionOutput() {
		return connectionOutput;
	}

	public void setConnectionOutput(PrintWriter connectionOutput) {
		this.connectionOutput = connectionOutput;
	}

	public BufferedReader getConnectionInput() {
		return connectionInput;
	}

	public void setConnectionInput(BufferedReader connectionInput) {
		this.connectionInput = connectionInput;
	}

	public Document getHostNameDocument() {
		return hostNameDocument;
	}

	public void setHostNameDocument(Document ipDocument) {
		this.hostNameDocument = ipDocument;
	}

	public Document getPortDocument() {
		return portDocument;
	}

	public void setPortDocument(Document portDocument) {
		this.portDocument = portDocument;
	}

	private String getMessageInSendBox() throws BadLocationException {
		return sendDocument.getText(0, sendDocument.getLength());
	}
	
	public void setMessageDocument(Document document) {
		this.messageDocument = document;
	}

	public String getHostName() {
		try {
			return getHostNameDocument().getText(0, getHostNameDocument().getLength());
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
		return null; // Should never happen
	}

	public int getPortNumber() {
		try {
			return Integer.parseInt(getPortDocument().getText(0, getPortDocument().getLength()));
		} catch (NumberFormatException e) {
			return -1; // Invalid port number in field
		} catch (BadLocationException e) {
			e.printStackTrace();
			return -1; // Should never happen
		} 
	}

	public void setSendDocument(Document sendDocument) {
		this.sendDocument = sendDocument;
	}
}
