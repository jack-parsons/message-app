package client;

/**
 * For bringing all the parts of the MVC system together (the ClientModel, ClientView, ClientController)
 * @author Jack
 *
 */
public class ClientApp {
	public static void main (String[] args) {
		ClientModel clientModel = new ClientModel("localhost", 1042);
		ClientView clientView = new ClientView();
		ClientController clientController = new ClientController(clientModel, clientView);
		clientController.initController();
	}
}
