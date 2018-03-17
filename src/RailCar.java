import java.awt.*;
import java.awt.geom.*;

//construction of the cars is done here, such as placing numbers, adding/removing boxes, painting the car, etc. 
public class RailCar extends Vehicle {

	public static final int UNIT = 10;
	public static final int U6 = 6 * UNIT;
	public static final int U5 = 5 * UNIT;
	public static final int U4 = 4 * UNIT;
	public static final int U3 = 3 * UNIT;
	public static final int U2 = 2 * UNIT;
	public static final int U15 = UNIT + UNIT / 2;
	public static final int U05 = UNIT / 2;
	public static final int BODY_WIDTH = U3;
	public static final int BODY_HEIGHT = U2;

	private final int railNumber;

	public Box boxOnCar;

	public RailCar(int x, int y, int number) {
		super(x, y);
		railNumber = number;
		isTrainEngine = false;
		width = U6 + UNIT;
	}

	public int getNumber() {
		return railNumber;
	}

	@Override
	public void draw(Color color, Graphics2D g2) {
		int xValue = getX();
		int yValue = getY();
		g2.setColor(color);
		drawBox(g2);
		body = new Rectangle2D.Double(xValue, yValue + UNIT, U6, UNIT);
		Ellipse2D.Double frontTire = new Ellipse2D.Double(xValue + UNIT, yValue + U2, UNIT, UNIT);
		Ellipse2D.Double rearTire = new Ellipse2D.Double(xValue + U4, yValue + U2, UNIT, UNIT);
		Point2D.Double r1 = new Point2D.Double(xValue + UNIT, yValue + UNIT);
		Point2D.Double r2 = new Point2D.Double(xValue + U2, yValue);
		Point2D.Double r3 = new Point2D.Double(xValue + U4, yValue);
		Point2D.Double r4 = new Point2D.Double(xValue + U5, yValue + UNIT);
		Point2D.Double r5 = new Point2D.Double(xValue + U6, yValue + U15);
		Point2D.Double r6 = new Point2D.Double(xValue + U6 + U05, yValue + U15);
		Line2D.Double hitch = new Line2D.Double(r5, r6);
		g2.draw(body);
		g2.draw(hitch);
		g2.draw(frontTire);
		g2.draw(rearTire);
		g2.draw(body);
		g2.drawString("" + getNumber(), xValue + U2, yValue + U2);
	}

	public boolean isClickable(int mouseX, int mouseY) {

		if (body.contains(mouseX, mouseY)) {
			return true;

		} else {
			return false;
		}
	}

	public void addBox(Box box) {

		if (!hasBox) {
			hasBox = true;
			boxOnCar = box;
		}
	}

	public void removeBox() {
		if (hasBox) {
			hasBox = false;
		}
	}

	public Box getBox() {
		return boxOnCar;
	}

	public void drawBox(Graphics2D g2) {

		if (hasBox) {
			boxOnCar = new Box(getX() + U6 / 3, getY() - UNIT, boxOnCar.letter);
			boxOnCar.draw(g2);
		}
	}
}