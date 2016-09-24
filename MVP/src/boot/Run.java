package boot;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.swing.JApplet;
import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import model.MyModel;
import presenter.Presenter;
import presenter.Properties;
import view.CLI;
import view.GUIView;
import view.MyView;

public class Run {

	@SuppressWarnings("unused")
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
		
		GUIView gv = new GUIView();
		MyModel model = new MyModel();
		
		Presenter presenter = new Presenter(model, gv);
		model.addObserver(presenter);
		gv.addObserver(presenter);
		
		gv.start();
	}

}
