package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread implements Runnable{
	Socket socket;
	
	public ServerThread(Socket socket) {
		this.socket = socket;
	}
		
	public void run() {
		
		String inputLine;
		try {
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			System.out.println("Connected");
			
			while ((inputLine = in.readLine()) != null) {
				System.out.println(inputLine);
			}
			
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		System.out.println("Disconnected");
	}
}
