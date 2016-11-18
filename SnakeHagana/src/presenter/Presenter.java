package presenter;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import model.Model;
import view.View;

/**
 * The Class Presenter.
 */
public class Presenter implements Observer {
	
	/** The model. */
	private Model model;
	
	/** The view. */
	private View view;
	
	/** The commands manager. */
	private CommandsManager commandsManager;
	
	/** The commands. */
	private HashMap<String, Command> commands;

	/**
	 * Instantiates a new presenter.
	 *
	 * @param model the model
	 * @param view the view
	 */
	public Presenter(Model model, View view) {
		this.model = model;
		this.view = view;
		
		commandsManager = new CommandsManager(model, view);
		commands = commandsManager.getCommandsMap();
	}


	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {
		if(o == view) {
			String commandLine = (String)arg;
			
			String arr[] = commandLine.split(" ");
			String command = arr[0];
			
			if(!commands.containsKey(command)) {
				view.printToScreen("Command doesn't exist!");
			}
			else {
				String[] args = null;
				if(arr.length > 1) {
					String commandArgs = commandLine.substring(commandLine.indexOf(" ")+1);
					args = commandArgs.split(" ");			
				}
				try {
				Command cmd = commands.get(command);
				cmd.doCommand(args);
				}
				catch (Exception e) {
					view.printToScreen("ERROR!");
					e.printStackTrace();
				}
			}
		}
		else {
			String message = (String)arg;
			view.printToScreen(message);
		}

	}

}
