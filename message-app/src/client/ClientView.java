package client;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JToolBar;

/**
 * The GUI
 * @author Jack
 *
 */
public class ClientView {

	private JFrame frame;
	private JTextField sendTextField;
	private JPanel bottomPanel;
	private JTextPane messagePane;
	private JButton sendButton;
	private JToolBar toolBar;
	private JButton newConnectionButton;
	private JTextField portNumberTextField;
	private JTextField hostNameTextField;
	private JTextField usernameBox;

	public ClientView() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		messagePane = new JTextPane();
		messagePane.setEditable(false);
		JScrollPane scrollPlane2 = new JScrollPane(messagePane);
		frame.getContentPane().add(scrollPlane2, BorderLayout.CENTER);
		
		bottomPanel = new JPanel();
		frame.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
		bottomPanel.setLayout(new BorderLayout(0, 0));
		
		sendTextField = new JTextField();
		bottomPanel.add(sendTextField);
		sendTextField.setColumns(30);
		
		sendButton = new JButton("Send");
		bottomPanel.add(sendButton, BorderLayout.EAST);
		
		toolBar = new JToolBar();
		frame.getContentPane().add(toolBar, BorderLayout.NORTH);
		
		newConnectionButton = new JButton("New Connection");
		toolBar.add(newConnectionButton);
		
		portNumberTextField = new JTextField();
		portNumberTextField.setText("1042");
		portNumberTextField.setToolTipText("Port Number");
		toolBar.add(portNumberTextField);
		portNumberTextField.setColumns(1);
		
		hostNameTextField = new JTextField();
		hostNameTextField.setText("localhost");
		hostNameTextField.setToolTipText("IP Address (can be localhost)");
		toolBar.add(hostNameTextField);
		hostNameTextField.setColumns(1);
		
		usernameBox = new JTextField();
		usernameBox.setToolTipText("Username");
		usernameBox.setText("Client 1");
		toolBar.add(usernameBox);
		usernameBox.setColumns(5);
		frame.setVisible(true);
	}
	
	public void showSucessfulConnectionDialogue() {
		JOptionPane.showMessageDialog(frame, "Connection to server sucessfull");
	}
	
	public void showFailedConnectionDialogue() {
		JOptionPane.showMessageDialog(frame, "Connection to server failed", "", JOptionPane.ERROR_MESSAGE);
	}

	public JTextField getPortNumberTextField() {
		return portNumberTextField;
	}

	public void setPortNumberTextField(JTextField portNumberTextField) {
		this.portNumberTextField = portNumberTextField;
	}

	public JTextField getHostNameTextField() {
		return hostNameTextField;
	}

	public void setHostNameTextField(JTextField hostNameTextField) {
		this.hostNameTextField = hostNameTextField;
	}

	public JTextField getSendTextField() {
		return sendTextField;
	}

	public void setSendTextField(JTextField sendTextField) {
		this.sendTextField = sendTextField;
	}

	public JTextPane getMessagePane() {
		return messagePane;
	}

	public void setMessagePane(JTextPane messagePane) {
		this.messagePane = messagePane;
	}

	public JButton getSendButton() {
		return sendButton;
	}

	public void setSendButton(JButton sendButton) {
		this.sendButton = sendButton;
	}

	public JButton getNewConnectionButton() {
		return newConnectionButton;
	}

	public void setNewConnectionButton(JButton newConnectionButton) {
		this.newConnectionButton = newConnectionButton;
	}

	public JTextField getUsernameBox() {
		return usernameBox;
	}

	public void setUsernameBox(JTextField usernameBox) {
		this.usernameBox = usernameBox;
	}

}
