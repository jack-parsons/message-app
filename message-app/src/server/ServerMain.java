package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerMain {
	ServerSocket ss;
	Socket clientSocket;
	static int portNumber = 1042;
	
	public static void main(String[] args) {
		List<Thread> threads = new ArrayList<Thread>();
		String inputLine;
		
		// Open socket with client as resources
		try (ServerSocket serverSocket = new ServerSocket(portNumber);) {
			while (true) {
				try {
					Socket clientSocket = serverSocket.accept();
					ServerThread runner = new ServerThread(clientSocket);
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
