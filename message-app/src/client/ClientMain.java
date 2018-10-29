package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientMain {
	static int portNumber = 1046;
	
	public static void main(String[] args) {
		String inputLine;
		// Open socket with client as resources
		try (Socket clientSocket = new Socket("localhost", portNumber);
			    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
			    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				BufferedReader localInput = new BufferedReader(new InputStreamReader(System.in));
				) {
			while (true) {
				inputLine = localInput.readLine();
				out.println(inputLine);
				System.out.println(inputLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
