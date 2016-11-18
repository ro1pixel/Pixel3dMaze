package boot;

import haganaBk15.SnakesBoard;
import model.MyModel;
import presenter.Presenter;
import presenter.SnakesPresenter;
import view.SnakeWindow;
import view.SnakeGUIWindow;

public class Run1 {

	public static void main(String[] args) {
		MyModel model = new MyModel();
		SnakeGUIWindow gui  = new SnakeGUIWindow();
		SnakesPresenter presenter = new SnakesPresenter(model, gui);
		gui.addObserver(presenter);
		model.addObserver(presenter);
		gui.start();
	}
}
