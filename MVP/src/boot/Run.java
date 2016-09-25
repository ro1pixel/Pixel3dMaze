package boot;

import model.MyModel;
import presenter.Presenter;
import view.GUI;
import view.MazeWindow;

public class Run {

	public static void main(String[] args) {
		
//		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//		PrintWriter out = new PrintWriter(System.out);
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
		
		GUI gv = new GUI();
		MyModel model = new MyModel();
		
		Presenter presenter = new Presenter(model, gv);
		model.addObserver(presenter);
		gv.addObserver(presenter);
		
		gv.start();
		
//		MazeWindow view = new MazeWindow(500,500);
//		Presenter presenter = new Presenter(model, view);
//		model.addObserver(presenter);
//		view.addObserver(presenter);
//		view.start();
	}

}
