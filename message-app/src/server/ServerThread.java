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
	
	public ServerThread(Socket socket, ArrayList<ServerThread> serverThreads) {
		this.socket = socket;
		this.serverThreads = serverThreads;
		try {
			out = new PrintWriter(socket.getOutputStream(), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendMessage(String message) {
		out.println(message);
	}
		
	public void run() {
		
		String inputLine;
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			System.out.println("Connected");
			
			while ((inputLine = in.readLine()) != null) {
				System.out.println(inputLine);
				for (ServerThread st : serverThreads) {
					st.sendMessage(inputLine);
				}
			}
			
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		System.out.println("Disconnected");
	}
}
