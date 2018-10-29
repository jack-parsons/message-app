package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Manages the data, logic and rules of the program.
 * @author Jack
 *
 */
public class ClientModel {
	private String hostName;
	private int portNumber;

	public ClientModel(String hostName, int portNumber) {
		this.hostName = hostName;
		this.portNumber = portNumber;
	}

	public void connectToMessageServer() {
		String inputLine;
		
		// Open socket with client connection as resources (so automatically closes everything)
		try (	Socket clientSocket = new Socket(hostName, portNumber);
			    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
			    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				BufferedReader localInput = new BufferedReader(new InputStreamReader(System.in));
			) {
			while (true) {
				inputLine = localInput.readLine();
				out.println(inputLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public int getPortNumber() {
		return portNumber;
	}

	public void setPortNumber(int portNumber) {
		this.portNumber = portNumber;
	}
}
