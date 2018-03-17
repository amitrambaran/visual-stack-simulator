import javax.swing.JFrame;

//main method to create the frame,set title, etc
public class FontViewer {

	public static void main(String[] args) {
		JFrame frame = new FontFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Stack Simulator");
		frame.setVisible(true);
	}
}
