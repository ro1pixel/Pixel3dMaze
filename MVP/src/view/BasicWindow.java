package view;

import java.util.Observable;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

public abstract class BasicWindow extends Observable implements Runnable {
	
	Display display;
	Shell shell;
	

	public BasicWindow() {
		display = new Display();
		shell = new Shell(display);
	}
	
	public BasicWindow(int width, int height) {
		display = new Display();
		shell = new Shell(display);
		shell.setSize(width, height);
	}

	abstract void initWidgets();

	@Override
	public void run() {
		initWidgets();
		shell.open();
		runEventLoop();		
	}
	
	public void runEventLoop() {
		while (!shell.isDisposed()) {
		        if (!display.readAndDispatch())
		         {
		                display.sleep();
		         }
		}

		display.dispose();
	}
	
	public void displayMessage(int iconNum,String title,String message)
	{
		display.syncExec(new Runnable() {
			
			@Override
			public void run() {
				MessageBox messageBox=new MessageBox(shell,iconNum);
				messageBox.setText(title);
				messageBox.setMessage(message);
				messageBox.open();
			}
		});
	}
	
	public void displayInfoMessage(String title,String message) {
		displayMessage(SWT.ICON_INFORMATION, title, message);
	}
	
	public void displayErrorMessage(String title,String message) {
		displayMessage(SWT.ICON_ERROR, title, message);
	}

}
