import javax.swing.JFrame;


public class Driver {
	public static void main (String[] args)
	{
		JFrame frame = new JFrame("Drawing Pad");
		DrawingUI panel = new DrawingUI();
		frame.getContentPane().add(panel);
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}
