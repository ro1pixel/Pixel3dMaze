package boot;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;

import model.Model;
import model.MyModel;
import presenter.Command;
import presenter.Controller;
import presenter.MyController;
import view.CLI;
import view.MyView;
import view.View;

public class Run {

	public static void main(String[] args) {
		
		CLI cli = new CLI(new BufferedReader(new InputStreamReader(System.in)), 
				new PrintWriter(System.out));
		
		Model model = new MyModel();
		View view = new MyView(cli);
		Controller controller = new MyController(view, model);
		
		view.setController(controller);
		model.setController(controller);
		
		view.start();

	}

}
