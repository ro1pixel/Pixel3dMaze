package boot;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;

import controller.Command;
import controller.MyController;
import model.Model;
import model.MyModel;
import view.CLI;
import view.MyView;
import view.View;

public class Run {

	public static void main(String[] args) {
		HashMap<String, Command> stg = new HashMap<>();
		CLI cli = null;
		try {
			cli = new CLI(new BufferedReader(new FileReader("1.maz")),
					new PrintWriter(new FileOutputStream("1.maz")), stg);
			MyView view = new MyView(cli);
			MyModel model = new MyModel();
			MyController controller = new MyController(view,model);
			view.setController(controller);
			view.start();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
