import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Stack;
import javax.swing.Timer;

public class MyPanel extends JPanel {

	// provides the functionality for painting the Engine, Cars and Stack.
	// Implements functionality of menu items.
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
	Vehicle selected;
	boolean hasSelection = false;
	StorageContainer container;
	MouseAdapter frameListener;
	Timer updateTimer; // used for counting clicks and knowing when to paint the
						// stack.
	int clickCount = 0;
	int x;
	int y;

	public MyPanel() { // constructs the panel, counts clicks
		class FrameListener extends MouseAdapter {

			public void mousePressed(MouseEvent event) {

				x = event.getX();
				y = event.getY();

				switch (clickCount) {
				case 0:
					vehicles.add(new TrainEngine(x, y)); //creates the train engine,
					clickCount++;
					break;
				case 1:
				case 2:
				case 3:
				case 4:		//case 1-5 does the same thing, as we are creating 5 identical railcars in different positions
				case 5:
					vehicles.add(new RailCar(x, y, clickCount));
					clickCount++;
					break;
				case 6:
					container = new StorageContainer(x, y);
					container.isVisible = true;
					clickCount++;
				default:

					if (clickCount == 7) {

						for (Vehicle vehicle : vehicles) { // checks if
															// selected, if so,
															// deselects current
															// selected car and
															// selects clicked
															// one

							if (vehicle.isClickable(x, y) && vehicle.checkSelection() == false) {
								removeSelected();
								vehicle.isSelected = true;
								selected = vehicle;
								hasSelection = true;
								break;
							}
						}
					}
				}
			}

			public void mouseDragged(MouseEvent event) { // enables dragging

				int x1 = event.getX() - x;
				int y1 = event.getY() - y;

				if (clickCount == 7) {

					for (Vehicle vehicle : vehicles) {

						if (vehicle.isClickable(x, y) && vehicle.checkSelection() == true) {
							vehicle.addX(x1);
							vehicle.addY(y1);
							repaint();
						}
					}
				}

				x += x1;
				y += y1;
			}

			public void mouseReleased(MouseEvent event) { // checks if car is
															// within range of
															// another car, and
															// connects them

				if (clickCount == 7 && hasSelection) {

					for (Vehicle vehicle : vehicles) {

						if (vehicle.isSelected == false && selected.body.intersects(vehicle.body)
								&& vehicle.next == null && !selected.isTrainEngine && !vehicle.isTrailer) {
							vehicle.next = selected;
							vehicle.next.previous = vehicle;
							vehicle.isTrailer = true;
							break;
						}
					}
				}
			}
		}

		frameListener = new FrameListener();
		addMouseListener(frameListener);
		addMouseMotionListener(frameListener);

		class UpdateListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent event) {
				repaint();
			}
		}

		UpdateListener updateListener = new UpdateListener();
		updateTimer = new Timer(10, updateListener);

		updateTimer.start();
	}

	@Override
	public void paintComponent(Graphics g) { // paint checking for cars

		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;

		for (Vehicle vehicle : vehicles) {

			if (vehicle.isTrailer && vehicle.previous == null) {

				if (vehicle.isSelected) {
					drawTrailer(vehicle, Color.RED, g2);

				} else {
					drawTrailer(vehicle, Color.BLUE, g2);
				}

			} else if (vehicle.previous == null) {

				if (vehicle.isSelected) {
					vehicle.draw(Color.RED, g2);

				} else {
					vehicle.draw(Color.BLUE, g2);
				}
			}

		}

		if (container != null && container.isVisible) { // draw method for
														// "stack"
														// representation
			container.draw(g2);
			Stack<Box> temp = container.boxes;

			for (Box single : temp) {
				single.draw(g2);
			}
		}
	}

	/**
	 * Remove all the selection status of each vehicle
	 */
	public void removeSelected() {

		for (Vehicle vehicle : vehicles) {
			vehicle.isSelected = false;
		}
	}

	public void drawTrailer(Vehicle vehicle, Color color, Graphics2D g2) { // paint
																			// component
																			// for
																			// cars

		if (vehicle == null) {
			return;
		}
		vehicle.draw(color, g2);

		int nextX;
		int nextY;

		if (vehicle.next != null) {
			nextX = vehicle.getX() + (int) vehicle.width;
			nextY = vehicle.getY();
			vehicle.next.setX(nextX);
			vehicle.next.setY(nextY);
		}

		drawTrailer(vehicle.next, color, g2);
	}

	public void removeFirst() {

		Vehicle train = vehicles.get(0);

		if (train.next == null) {
			return;
		}

		if (train.next.next == null) {
			Vehicle removedRail = train.next;
			removedRail.setX(200);
			removedRail.setY(200);
			train.isTrailer = false;
			train.next = null;
			removedRail.previous = null;

		} else {

			Vehicle removedRail = train.next;
			removedRail.setX(200);
			removedRail.setY(200);
			removedRail.isTrailer = false;
			train.next = train.next.next;
			removedRail.next = null;
			removedRail.previous = null;
		}
		removeSelected();
	}

	public void drawVehicle(Vehicle vehicle, Color color, Graphics2D g2) {
		vehicle.draw(color, g2);
	}

	public void removeLast() {

		Vehicle current = vehicles.get(0);

		Vehicle previousious = null;

		if (current.next == null) {
			return;
		}

		while (current.next != null) {
			previousious = current;
			current = current.next;
		}
		previousious.next.setX(200);
		previousious.next.setY(200);
		previousious.isTrailer = false;
		previousious.next = null;
		current.previous = null;
		repaint();
		removeSelected();
	}

	public void addFirst() {

		Vehicle current = vehicles.get(0);

		if (hasSelection && selected != null && !selected.isTrainEngine) {

			if (current.next == null) {
				current.next = selected;
				current.isTrailer = true;

			} else {

				Vehicle selectLast = selected;

				while (selectLast.next != null) {
					selectLast = selectLast.next;
				}
				Vehicle others = current.next;
				current.next = selected;
				selected.isTrailer = true;
				selectLast.next = others;
			}
		}
		removeSelected();
	}

	public void addLast() {

		Vehicle current = vehicles.get(0);

		if (hasSelection && selected != null && !selected.isTrainEngine) {

			if (current.next == null) {
				current.next = selected;
				current.isTrailer = true;
				return;
			}

			while (current.next != null) {
				current = current.next;
			}
			current.next = selected;
			current.isTrailer = true;
			repaint();
		}
		removeSelected();
	}

	public void popItem() {

		for (Vehicle vehicle : vehicles) {

			if (vehicle.checkSelection() == true && !container.isEmpty()) {

				if (vehicle.isTrailer) {

					RailCar temp = (RailCar) checkTrailerBox(vehicle);

					if (temp == null) {
						return;
					}

					Box tempBox = container.pop();
					temp.addBox(tempBox);
					break;

				} else if (!vehicle.hasBox && !vehicle.isTrainEngine) {
					RailCar temp = (RailCar) vehicle;
					Box box = container.pop();
					temp.addBox(box);
					break;
				}
			}
		}
	}

	public void pushItem() {

		for (Vehicle vehicle : vehicles) {

			if (vehicle.checkSelection() == true) {

				if (vehicle.isTrailer) {

					RailCar temp = (RailCar) findTrailerBox(vehicle);

					if (temp == null) {
						return;
					}
					container.push(temp.getBox());
					temp.removeBox();
					break;

				} else if (vehicle.hasBox) {

					RailCar temp = (RailCar) vehicle;
					container.push(temp.getBox());
					temp.removeBox();
					break;
				}
			}
		}
	}

	public RailCar checkTrailerBox(Vehicle vehicle) {

		if (vehicle == null) {
			return null;
		}

		else if (vehicle.isTrainEngine) {
			return checkTrailerBox(vehicle.next);
		}

		else if (!vehicle.hasBox) {
			return (RailCar) vehicle;

		} else {
			return checkTrailerBox(vehicle.next);
		}
	}

	public RailCar findTrailerBox(Vehicle vehicle) {

		if (vehicle == null) {
			return null;
		}

		if (vehicle.isTrainEngine) {
			return findTrailerBox(vehicle.next);
		}

		if (vehicle.hasBox) {
			return (RailCar) vehicle;

		} else {
			return findTrailerBox(vehicle.next);
		}
	}
}