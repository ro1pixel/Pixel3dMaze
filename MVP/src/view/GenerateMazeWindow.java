package view;


import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class GenerateMazeWindow extends BasicWindow {

	String name;
	int floors;
	int height;
	int width;
	
	public GenerateMazeWindow(int width,int height) {
		super(width,height);
		initWidgets();
	}
	
	@Override
	void initWidgets() {
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
		btnGenerateMaze.setLayoutData(new GridData(SWT.LEFT,SWT.CENTER,true,false,2,1));
		btnGenerateMaze.setText("Generate!");
		
		btnGenerateMaze.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				name=txtMazeName.getText();
				floors=Integer.parseInt(txtFloorsNum.getText());
				height=Integer.parseInt(txtHeight.getText());
				width=Integer.parseInt(txtWidth.getText());
				displayInfoMessage("Your Maze", "Maze Name: " + name + "\nFloors: "+floors +"\nHeight: " + height + "\nWidth: " + width);
				shell.dispose();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
		
	}

	public String getName() {
		return name;
	}

	public int getFloors() {
		return floors;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}
	
}
