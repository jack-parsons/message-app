package client;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JPanel;
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

	public ClientView() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		messagePane = new JTextPane();
		messagePane.setEditable(false);
		frame.getContentPane().add(messagePane, BorderLayout.CENTER);
		
		bottomPanel = new JPanel();
		frame.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
		
		sendTextField = new JTextField();
		bottomPanel.add(sendTextField);
		sendTextField.setColumns(25);
		
		sendButton = new JButton("Send");
		bottomPanel.add(sendButton);
		
		toolBar = new JToolBar();
		frame.getContentPane().add(toolBar, BorderLayout.NORTH);
		
		newConnectionButton = new JButton("New Connection");
		toolBar.add(newConnectionButton);
		frame.setVisible(true);
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

}
