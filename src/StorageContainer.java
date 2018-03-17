import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Stack;

//constructs the storage container. Check if it is empty, paints it, pops, pushes, etc. 
public class StorageContainer {

	Stack<Box> boxes = new Stack<Box>();
	Rectangle base;
	final int WIDTH = 50;
	final int HEIGHT = 15;
	private int x;
	private int y;
	boolean isVisible;

	public StorageContainer() { // default constructor to ensure it isn't on
								// frame
		isVisible = false;
	}

	public StorageContainer(int x, int y) { // constructor that takes parameters
											// when clicked the 7th time
		this.x = x;
		this.y = y;
		makeBoxes();
		isVisible = true;
	}

	public void makeBoxes() {

		int letter = 65;
		for (int i = 0; i < 5; i++) {
			boxes.push(new Box(x + 15, y - (HEIGHT * 2 * i) - 20, letter));
			letter++;
		}
	}

	public void draw(Graphics2D g2) {
		base = new Rectangle(x, y, WIDTH, HEIGHT);
		g2.setColor(Color.MAGENTA);
		g2.fill(base);
	}

	public boolean isEmpty() {
		return boxes.empty();
	}

	public Box pop() {
		return boxes.pop();
	}

	public void push(Box box) {
		boxes.push(new Box(x + 15, y - (HEIGHT * 2 * boxes.size()) - 20, box.letter));
	}
}