package controller;

public interface Controller {
	void notifyMazeIsReady(String name);
	void notifySolutionIsReady(String name);
	void printToScreen(String string);
}
