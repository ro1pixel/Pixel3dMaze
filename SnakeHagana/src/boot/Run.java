package boot;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import model.Model;
import model.MyModel;
import presenter.MyController;
import presenter.Presenter;
import view.CLI;
import view.GUI;
import view.MazeWindow;
import view.MyView;
import view.View;

public class Run {

	public static void main(String[] args) {
		

//		
//		MyView view = new MyView(in,out);
//		MyModel model = new MyModel();
//		
//		Presenter presenter = new Presenter(model, view);
//		
//		model.addObserver(presenter);
//		view.addObserver(presenter);
//
//		view.start();
		
//		MazeWindow mw = new MazeWindow(500, 500);
//		mw.run();
		
		
		MyModel model = new MyModel();
		String viewStyle = model.getViewStyle();
		
		if(viewStyle.equals("CLI")) {
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			PrintWriter out = new PrintWriter(System.out);
			MyView view = new MyView(in,out);
			Presenter presenter = new Presenter(model, view);
			view.addObserver(presenter);
			model.addObserver(presenter);
			view.start();
		}
		else if (viewStyle.equals("GUI")) {
			GUI gui = new GUI();
			Presenter presenter = new Presenter(model, gui);
			gui.addObserver(presenter);
			model.addObserver(presenter);
			gui.start();
		}

	}

}
