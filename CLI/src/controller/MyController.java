package controller;

import model.Model;
import view.View;

public class MyController implements Controller {
	
	private View view;
	private Model model;
	private CommandsManager commandsManager;
	
	public MyController(View view, Model model) {
		super();
		this.view = view;
		this.model = model;
		
		commandsManager = new CommandsManager(model,view);
	}

	@Override
	public void NotifyMazeIsReady(String name) {
		view.notifyMazeIsReady(name);		
	}
}
