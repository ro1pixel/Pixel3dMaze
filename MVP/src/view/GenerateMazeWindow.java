package view;


import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * The Class GenerateMazeWindow.
 */
public class GenerateMazeWindow extends BasicWindow {

	/** The name. */
	String name;
	
	/** The floors. */
	int floors;
	
	/** The height. */
	int height;
	
	/** The width. */
	int width;
	
	/**
	 * Instantiates a new generate maze window.
	 *
	 * @param width the width
	 * @param height the height
	 */
	public GenerateMazeWindow(int width,int height) {
		super(width, height);
		height = 0;
		width = 0;
		floors = 0;
	}

	/* (non-Javadoc)
	 * @see view.BasicWindow#initWidgets()
	 */
	@Override
	public void initWidgets() {
		shell.setText("Generate Maze");
		shell.setLayout(new GridLayout(2,false));
		
		Label lblMazeName = new Label(shell, SWT.NONE);
		lblMazeName.setText("Maze Name: ");
		
		Text txtMazeName = new Text(shell, SWT.BORDER);
		txtMazeName.setLayoutData(new GridData(SWT.FILL,SWT.CENTER,true,false));
		
		Label lblFloorsNum = new Label(shell, SWT.NONE);
		lblFloorsNum.setText("Floors: ");
		
		Text txtFloorsNum = new Text(shell, SWT.BORDER);
		txtFloorsNum.setLayoutData(new GridData(SWT.FILL,SWT.CENTER,true,false));
		
		Label lblHeight = new Label(shell, SWT.NONE);
		lblHeight.setText("Height: ");
		
		Text txtHeight = new Text(shell, SWT.BORDER);
		txtHeight.setLayoutData(new GridData(SWT.FILL,SWT.CENTER,true,false));
		
		Label lblWidth = new Label(shell, SWT.NONE);
		lblWidth.setText("Width: ");
		
		Text txtWidth = new Text(shell, SWT.BORDER);
		txtWidth.setLayoutData(new GridData(SWT.FILL,SWT.CENTER,true,false));
		
		Button btnGenerateMaze = new Button(shell,SWT.PUSH);
		shell.setDefaultButton(btnGenerateMaze);
		btnGenerateMaze.setLayoutData(new GridData(SWT.CENTER,SWT.CENTER,true,false,2,1));
		btnGenerateMaze.setText("Generate!");
		
		btnGenerateMaze.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
//				
//				if(txtHeight.getText() == "") {
//					displayErrorMessage("Error", "Minimum Height is 10"); 
//				}
//				else if(txtWidth.getText() == "")
//					displayErrorMessage("Error", "Minimum Height is 10"); 
//				else if(txtFloorsNum.getText() == "")
//					displayErrorMessage("Error", "Minimum Height is 10"); 
				
				name=txtMazeName.getText();
				floors=Integer.parseInt(txtFloorsNum.getText());
				height=Integer.parseInt(txtHeight.getText());
				width=Integer.parseInt(txtWidth.getText());
				
				if(name == "") 
					displayErrorMessage("Error", "Please insert name"); 
				else if(height < 10)
					displayErrorMessage("Error", "Minimum Height is 10"); 
				else if (width < 10)
					displayErrorMessage("Error", "Minimum Width is 10");
				else if (floors < 1)
					displayErrorMessage("Error", "Minimum floors is 3");
				else {
				displayInfoMessage("Your Maze", "Maze Name: " + name + "\nFloors: "+floors +"\nHeight: " + height + "\nWidth: " + width);
				shell.dispose();
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
		
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the floors.
	 *
	 * @return the floors
	 */
	public int getFloors() {
		return floors;
	}

	/**
	 * Gets the height.
	 *
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Gets the width.
	 *
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}
	
}
