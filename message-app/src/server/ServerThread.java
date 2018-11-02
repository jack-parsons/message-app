package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ServerThread implements Runnable{
	private Socket socket;
	private ArrayList<ServerThread> serverThreads;
	private PrintWriter out;
	private String username;
	
	public ServerThread(Socket socket, ArrayList<ServerThread> serverThreads) {
		this.socket = socket;
		this.serverThreads = serverThreads;
		try {
			out = new PrintWriter(socket.getOutputStream(), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendMessage(String message, String username) {
		out.printf("[%s]%s\n", username, message);
	}
	
	public void sendPlainMessage(String message) {
		out.println(message);
	}
		
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void run() {
		
		String inputLine;
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			// Send connected message
			setUsername(in.readLine());
			String connectedMessage = String.format("[Server] %s connected", getUsername());
			for (ServerThread st : serverThreads) {
				st.sendPlainMessage(connectedMessage);
			}
			System.out.println(connectedMessage);
			
			while ((inputLine = in.readLine()) != null) {
				System.out.println(inputLine);
				for (ServerThread st : serverThreads) {
					st.sendMessage(inputLine, getUsername());
				}
			}
			
			// Send disconnected message
			String disconnectedMessage = String.format("[Server] %s disconnected", getUsername());
			for (ServerThread st : serverThreads) {
				st.sendPlainMessage(disconnectedMessage);
			}
			System.out.println(disconnectedMessage);
			
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		System.out.println("Disconnected");
	}
}
