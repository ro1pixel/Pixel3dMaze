package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class PropertiesWindow extends BasicWindow {

	String generateMaze;
	String solutionAlgorithm;
	String viewStyle;
	Integer maxThreads;
	
	public PropertiesWindow(int width,int height) {
		super(width,height);
	}

	
	@Override
	public void initWidgets() {
		shell.setText("Properties");
		shell.setLayout(new GridLayout(2,false));
		
		Label lblGenerateMaze = new Label(shell, SWT.NONE);
		lblGenerateMaze.setText("Generation Type: ");
		
		final Combo generationTypeCombo = new Combo(shell,SWT.READ_ONLY);
		generationTypeCombo.setBounds(50, 50, 150, 65);
		String generationTypeItems[] = {"Growing"};
		generationTypeCombo.setItems(generationTypeItems);
		
		Label lblSolutionAlgorithm = new Label(shell, SWT.NONE);
		lblSolutionAlgorithm.setText("Solution Algorithm: ");
		
		final Combo solAlgCombo = new Combo(shell,SWT.READ_ONLY);
		solAlgCombo.setBounds(50, 50, 150, 65);
		String solAlgItems[] = {"BFS", "DFS"};
		solAlgCombo.setItems(solAlgItems);
		
		Label lblMaxThreads = new Label(shell, SWT.NONE);
		lblMaxThreads.setText("Max Threads: ");
		
		Text txtNumThreads = new Text(shell, SWT.BORDER);
		txtNumThreads.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		
		Label lblViewStyle = new Label(shell, SWT.NONE);
		lblViewStyle.setText("View Style: ");
		
		Text txtViewStyle = new Text(shell, SWT.BORDER);
		txtViewStyle.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
				
		Button btnGenerateMaze = new Button(shell, SWT.PUSH);
		shell.setDefaultButton(btnGenerateMaze);
		btnGenerateMaze.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 2, 1));
		btnGenerateMaze.setText("Edit");
				
		btnGenerateMaze.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {				
				generateMaze=generationTypeCombo.getText();
				solutionAlgorithm=solAlgCombo.getText();
				maxThreads = Integer.parseInt(txtNumThreads.getText());
				viewStyle=txtViewStyle.getText();
				displayInfoMessage("Info","The Properties are:"+"\nGenerateType: "+generateMaze+"\nSolution Algorithm: "+solutionAlgorithm
						+"\nNumber of Threads: "+maxThreads + "\nView Style: " + viewStyle);
				shell.dispose();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});	

	}


	public String getGenerateMaze() {
		return generateMaze;
	}


	public String getSolutionAlgorithm() {
		return solutionAlgorithm;
	}


	public String getViewStyle() {
		return viewStyle;
	}


	public Integer getMaxThreads() {
		return maxThreads;
	}
	

}