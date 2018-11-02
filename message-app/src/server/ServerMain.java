package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerMain {
	
	public static void main(String[] args) {
		ArrayList<ServerThread> serverThreads = new ArrayList<ServerThread>();
		int portNumber = 1042;
		// Open socket with client as resources
		try (ServerSocket serverSocket = new ServerSocket(portNumber);) {
			while (true) {
				try {
					Socket clientSocket = serverSocket.accept();
					ServerThread runner = new ServerThread(clientSocket, serverThreads);
//					for (ServerThread serverThread : serverThreads) {
//						serverThread.sendMessage(String.format("", ));
//					}
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
