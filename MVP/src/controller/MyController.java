package controller;

import model.Model;
import view.View;

/**
 * The Class MyController.
 */
public class MyController implements Controller {
	
	/** The view. */
	private View view;
	
	/** The model. */
	private Model model;
	
	/** The commands manager. */
	private CommandsManager commandsManager;
	
	/**
	 * Instantiates a new my controller.
	 *
	 * @param view the view
	 * @param model the model
	 */
	public MyController(View view, Model model) {
		super();
		this.view = view;
		this.model = model;
		
		commandsManager = new CommandsManager(model,view);
		view.setCommands(commandsManager.getCommandsMap());
	}

	/* (non-Javadoc)
	 * @see controller.Controller#notifyMazeIsReady(java.lang.String)
	 */
	@Override
	public void notifyMazeIsReady(String name) {
		view.notifyMazeIsReady(name);		
	}

	/* (non-Javadoc)
	 * @see controller.Controller#notifySolutionIsReady(java.lang.String)
	 */
	@Override
	public void notifySolutionIsReady(String name) {
		view.notifySolutionIsReady(name);
	}
	
	/* (non-Javadoc)
	 * @see controller.Controller#printToScreen(java.lang.String)
	 */
	public void printToScreen(String string) {
		view.printToScreen(string);
	}
}
