package client;

/**
 * Takes inputs from the user and uses it to manipulate the model
 * @author Jack
 *
 */
public class ClientController {
	private ClientView clientView;
	private ClientModel clientModel;

	public ClientController(ClientModel clientModel, ClientView clientView) {
		this.clientModel = clientModel;
		this.clientView = clientView;
		initView();
	}
	
	public void initController() {
		
	}
	
	public void initView() {
		
	}

}
