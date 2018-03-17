import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


//Constructs all menu elements, such as push, pop, exit, new, addfirst, addlast, removefirst, removelast.
//3 seperate methods are used to construct each of the 3 drop down menus. They return the menu and are then added to the panel.

public class FontFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int FRAME_WIDTH = 500;
	private static final int FRAME_HEIGHT = 650;
	private final MyPanel panel;

	public FontFrame() {
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		menuBar.add(createListMenu());
		menuBar.add(createFileMenu());
		menuBar.add(createStackMenu());
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		panel = new MyPanel();
		add(panel);
	}

	class ExitItemListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			System.exit(0);
		}
	}

	class NewItemListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			panel.vehicles = new ArrayList<Vehicle>();
			panel.container = new StorageContainer();
			panel.selected = null;
			panel.hasSelection = false;
			panel.clickCount = 0;
		}
	}

	class PopListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			panel.popItem();
		}
	}

	class PushListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			panel.pushItem();
		}
	}

	class AddFirstListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			panel.addFirst();
		}
	}

	class AddLastListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			panel.addLast();
		}
	}

	class RemoveFirstListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			panel.removeFirst();
		}
	}

	class RemoveLastListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			panel.removeLast();
		}
	}
	
	public JMenu createStackMenu() {
		JMenu menu = new JMenu("Stack");
		JMenuItem pop = new JMenuItem("Pop!");
		ActionListener popListener = new PopListener();
		pop.addActionListener(popListener);
		menu.add(pop);
		JMenuItem push = new JMenuItem("Push!");
		ActionListener pushListener = new PushListener();
		push.addActionListener(pushListener);
		menu.add(push);
		return menu;
	}

	public JMenu createFileMenu() {
		JMenu menu = new JMenu("File");
		JMenuItem newItem = new JMenuItem("New");
		ActionListener fileListener = new NewItemListener();
		newItem.addActionListener(fileListener);
		menu.add(newItem);
		JMenuItem exit = new JMenuItem("Exit");
		ActionListener exitListener = new ExitItemListener();
		exit.addActionListener(exitListener);
		menu.add(exit);
		return menu;
	}

	public JMenu createListMenu() {
		JMenu menu = new JMenu("List");
		JMenuItem addFirst = new JMenuItem("Add first");
		ActionListener addFirstListener = new AddFirstListener();
		addFirst.addActionListener(addFirstListener);
		menu.add(addFirst);
		JMenuItem addLast = new JMenuItem("Add last");
		ActionListener addLastListener = new AddLastListener();
		addLast.addActionListener(addLastListener);
		menu.add(addLast);
		JMenuItem removeFirst = new JMenuItem("Remove first");
		ActionListener removeFirstListener = new RemoveFirstListener();
		removeFirst.addActionListener(removeFirstListener);
		menu.add(removeFirst);
		JMenuItem removeLast = new JMenuItem("Remove last");
		ActionListener removeLastListener = new RemoveLastListener();
		removeLast.addActionListener(removeLastListener);
		menu.add(removeLast);
		return menu;
	}

	
}