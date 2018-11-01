package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

import java.awt.event.ActionEvent;
import server.ServerThread;

public class ClientThread implements Runnable {
	private Socket socket;
	private MessageEvent messageEvent;
	
	public ClientThread(Socket socket, MessageEvent messageEvent) {
		this.socket = socket;
		this.messageEvent = messageEvent;
	}
	
	public void run() {
		String inputLine;
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			while ((inputLine = in.readLine()) != null) {
				messageEvent.sendMessage(inputLine);
				System.out.println(inputLine);
			}
			
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
