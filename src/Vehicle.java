import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

//abstract vehicle class to be extended, constructs the basis for the RailCar and Engine
//contains methods for retrieving and setting coordinates
public abstract class Vehicle {

	private int x;
	private int y;
	public Rectangle2D.Double body;
	public boolean isSelected;
	public boolean isTrainEngine;
	public boolean isTrailer;
	public boolean hasBox;
	public double width;
	public Vehicle next;
	public Vehicle previous = null;
	
	

	public Vehicle(int x, int y) {
		this.x = x;
		this.y = y;
		hasBox = false;
		isSelected = false;
		isTrailer = false;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void addX(int x) {
		this.x += x;
	}

	public void addY(int y) {
		this.y += y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean checkSelection() {
		return isSelected;
	}

	abstract void draw(Color color, Graphics2D g2);

	abstract boolean isClickable(int mouseX, int mouseY);
}