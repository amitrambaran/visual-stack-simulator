import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;


//Constructs the boxes used for the stack; draw method simply draws them.
public class Box {

	public int x, y;
	int letter;
	Rectangle base;
	final int SIDE = 18;

	public Box(int x, int y, int boxletter) {
		this.x = x;
		this.y = y;
		letter = boxletter;
		base = new Rectangle(x, y, SIDE, SIDE);

	}

	public void draw(Graphics2D g2) {
		g2.setColor(Color.MAGENTA);
		g2.draw(base);
		g2.drawString("" + (char) letter, x + 5, y + 15);
	}
}

