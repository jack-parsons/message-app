package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {
	ServerSocket ss;
	Socket clientSocket;
	static int portNumber = 1042;
	
	public static void main(String[] args) {
		String inputLine;
		// Open socket with client as resources
		while (true) {
			try (ServerSocket serverSocket = new ServerSocket(portNumber);
					Socket clientSocket = serverSocket.accept();
				    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
				    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
					) {
				System.out.println("Connected");
				while ((inputLine = in.readLine()) != null) {
					System.out.println(inputLine);
				}
				System.out.println("Finished");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
