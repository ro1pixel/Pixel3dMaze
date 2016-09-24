package view;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Image;

public class Character {
	
	int x;
	int y;
	Image image;
	
	public Character() {
		x=0;
		y=0;
	}
	
	public Character(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void drawCharacter(PaintEvent e, int length, int width) {
		image = new Image(e.display, "./resources/character.png");
		e.gc.drawImage(image, x, y);
	}

}
