package presenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import algorithms.search.State;
import model.Model;
import view.View;

/**
 * The Class Presenter.
 */
public class SnakesPresenter implements Observer {
	
	/** The model. */
	private Model model;
	
	/** The view. */
	private View view;

	/**
	 * Instantiates a new presenter.
	 *
	 * @param model the model
	 * @param view the view
	 */
	public SnakesPresenter(Model model, View view) {
		this.model = model;
		this.view = view;
	}


	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {
		if(o == view) {
			model.solveSnakeBoard((Maze3d)arg);
		}
		else {
			view.displaySolution((Solution<Position>) arg);
		}
	}

}
