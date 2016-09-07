package view;

public class MyView implements View {
	
	CLI cli;
	
	public MyView(CLI cli) {
		super();
		this.cli = cli;
	}

	@Override
	public void start() {
		cli.start();		
	}

}
