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

/**
 * The Class PropertiesWindow.
 */
public class PropertiesWindow extends BasicWindow {

	/** The generate maze. */
	String generateMaze;
	
	/** The solution algorithm. */
	String solutionAlgorithm;
	
	/** The view style. */
	String viewStyle;
	
	/** The max threads. */
	Integer maxThreads;
	
	/**
	 * Instantiates a new properties window.
	 *
	 * @param width the width
	 * @param height the height
	 */
	public PropertiesWindow(int width,int height) {
		super(width,height);
	}

	
	/* (non-Javadoc)
	 * @see view.BasicWindow#initWidgets()
	 */
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
		
		final Combo viewStyleCombo = new Combo(shell,SWT.READ_ONLY);
		viewStyleCombo.setBounds(50, 50, 150, 65);
		String viewStyleComboItems[] = {"CLI", "GUI"};
		viewStyleCombo.setItems(viewStyleComboItems);
				
		Button btnGenerateMaze = new Button(shell, SWT.PUSH);
		shell.setDefaultButton(btnGenerateMaze);
		btnGenerateMaze.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 2, 1));
		btnGenerateMaze.setText("Save");
				
		btnGenerateMaze.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {				
				generateMaze=generationTypeCombo.getText();
				solutionAlgorithm=solAlgCombo.getText();
				maxThreads = Integer.parseInt(txtNumThreads.getText());
				viewStyle=viewStyleCombo.getText();
				displayInfoMessage("Info","The Properties are:"+"\nGenerateType: "+generateMaze+"\nSolution Algorithm: "+solutionAlgorithm
						+"\nNumber of Threads: "+maxThreads + "\nView Style: " + viewStyle);
				displayInfoMessage("Restart", "Reset application to apply changes!");
				shell.dispose();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});	

	}


	/**
	 * Gets the generate maze.
	 *
	 * @return the generate maze
	 */
	public String getGenerateMaze() {
		return generateMaze;
	}


	/**
	 * Gets the solution algorithm.
	 *
	 * @return the solution algorithm
	 */
	public String getSolutionAlgorithm() {
		return solutionAlgorithm;
	}


	/**
	 * Gets the view style.
	 *
	 * @return the view style
	 */
	public String getViewStyle() {
		return viewStyle;
	}


	/**
	 * Gets the max threads.
	 *
	 * @return the max threads
	 */
	public Integer getMaxThreads() {
		return maxThreads;
	}
	

}
