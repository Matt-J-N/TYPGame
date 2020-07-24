//import java.awt.Frame;
import javax.swing.BoxLayout;
import javax.swing.JFrame;

public class Game {
	
	public static void main(String[] args) {
		
		TitleScreen frameObject = new TitleScreen();
		frameObject.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameObject.setResizable(false);
		frameObject.setBounds(0, 0, 1400, 900);
		frameObject.setVisible(true);
		frameObject.setLocationRelativeTo(null);
		
	}
	
}
