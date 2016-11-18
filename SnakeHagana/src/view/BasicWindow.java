package view;

import java.util.Observable;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

/**
 * The Class BasicWindow.
 */
public abstract class BasicWindow extends Observable implements Runnable {
	
	/** The display. */
	Display display;
	
	/** The shell. */
	Shell shell;
	
	/** The main window. */
	boolean mainWindow = false;
	

	/**
	 * Instantiates a new basic window.
	 */
	public BasicWindow() {
		display = new Display();
		shell = new Shell(display);
	}
	
	/**
	 * Instantiates a new basic window.
	 *
	 * @param width the width
	 * @param height the height
	 */
	public BasicWindow(int width, int height) {
		display = Display.getCurrent();
 		if(display == null) {
 			display = new Display();
 			mainWindow = true;
 		}
		shell = new Shell(display);
		shell.setSize(width, height);
		initWidgets();
	}

	/**
	 * Inits the widgets.
	 */
	public abstract void initWidgets();

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		shell.open();
		while (!shell.isDisposed()) {
	        if (!display.readAndDispatch()) {
	                display.sleep();
	         }
		}
		
		if(mainWindow)
			display.dispose();
	}
	
	
	/**
	 * Display message.
	 *
	 * @param iconNum the icon num
	 * @param title the title
	 * @param message the message
	 */
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
	
	/**
	 * Display info message.
	 *
	 * @param title the title
	 * @param message the message
	 */
	public void displayInfoMessage(String title,String message) {
		displayMessage(SWT.ICON_INFORMATION, title, message);
	}
	
	/**
	 * Display error message.
	 *
	 * @param title the title
	 * @param message the message
	 */
	public void displayErrorMessage(String title,String message) {
		displayMessage(SWT.ICON_ERROR, title, message);
	}
	
	/**
	 * Display file dialog.
	 *
	 * @param style the style
	 * @param title the title
	 * @param filterExtention the filter extention
	 * @param Path the path
	 * @return the string
	 */
	public String displayFileDialog(int style,String title,String [] filterExtention,String Path)
	{
		FileDialog fd=new FileDialog(shell,style);
		fd.setText(title);
		fd.setFilterPath(Path);
		fd.setFilterExtensions(filterExtention);
		return fd.open();
	}
	
	/**
	 * Display directory dialog.
	 *
	 * @param style the style
	 * @param title the title
	 * @param Path the path
	 * @return the string
	 */
	public String displayDirectoryDialog(int style,String title,String Path){
		DirectoryDialog d=new DirectoryDialog(shell, style);
		d.setFilterPath(Path);
		return d.open();
	}
	
	/**
	 * Display question.
	 *
	 * @param title the title
	 * @param message the message
	 * @return true, if successful
	 */
	public boolean displayQuestion(String title,String message) {
		MessageBox messageBox = new MessageBox(shell,SWT.ICON_QUESTION | SWT.YES | SWT.NO);
		messageBox.setMessage(message);
		messageBox.setText(title);
		
		int answer = messageBox.open();
		
		if(answer==SWT.YES)
			return true;
		else
			return false;
	}

	/**
	 * Gets the shell.
	 *
	 * @return the shell
	 */
	public Shell getShell() {
		return shell;
	}
	

}
