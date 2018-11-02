package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import java.util.Scanner;

public class ServerMain {
	
	public static void main(String[] args) {
		ArrayList<ServerThread> serverThreads = new ArrayList<ServerThread>();
		
		// Read in the port number
		Scanner reader = new Scanner(System.in);
		int portNumber = reader.nextInt();
		reader.close();
		
		// Open socket with client as resources
		try (ServerSocket serverSocket = new ServerSocket(portNumber);) {
			while (true) {
				try {
					Socket clientSocket = serverSocket.accept();
					ServerThread runner = new ServerThread(clientSocket, serverThreads);
					serverThreads.add(runner);
					new Thread(runner).start();
				} catch (IOException e) {
					e.printStackTrace();
				} 
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
