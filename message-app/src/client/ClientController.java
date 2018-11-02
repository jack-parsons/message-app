package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.text.BadLocationException;

/**
 * Takes inputs from the user and uses it to manipulate the ClientModel
 * @author Jack
 *
 */
public class ClientController {
	private ClientView clientView;
	private ClientModel clientModel;

	public ClientController(ClientModel clientModel, ClientView clientView) {
		this.clientModel = clientModel;
		this.clientView = clientView;
		initModel();
	}
	
	/**
	 * Add the ActionListeners to the ClientView elements
	 */
	public void initController() {
		clientView.getSendButton().addActionListener(e -> sendButtonEvent(e));
		clientView.getSendTextField().addActionListener(e -> sendButtonEvent(e));
		clientView.getNewConnectionButton().addActionListener(e -> newConnectionButton(e));
	}
	
	public void initModel() {
		clientModel.setMessageDocument(clientView.getMessagePane().getDocument());
		clientModel.setSendDocument(clientView.getSendTextField().getDocument());
		clientModel.setPortDocument(clientView.getPortNumberTextField().getDocument());
		clientModel.setHostNameDocument(clientView.getHostNameTextField().getDocument());
		changeUsername();
	}
	
	public void sendButtonEvent(ActionEvent e) {
		try {
			clientModel.sendMessage(clientModel.getMessageInSendBox());
		} catch (BadLocationException e1) {
			e1.printStackTrace();
		}
	}
	
	public void changeUsername() {
		clientModel.setUsername(clientView.getUsernameBox().getText());
	}
	
	public void newConnectionButton(ActionEvent e) {

		changeUsername();
		if (clientModel.connectToMessageServer()) {
			// If the connection succeeds
			clientView.showSucessfulConnectionDialogue();

			MessageEvent event = new MessageEvent() {
				public void sendMessage(String message) {
					clientModel.addMessage(message);
					
					// Autoscroll to bottom
					int lastCharPos = clientModel.getMessageDocument().getLength();
					clientView.getMessagePane().select(lastCharPos, lastCharPos);
				}
			};
			new Thread(new ClientThread(clientModel.getSocket(), event)).start();
		} else {
			// If the connection fails
			clientView.showFailedConnectionDialogue();
		}
		
	}

}
