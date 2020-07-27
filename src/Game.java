//import java.awt.Frame;
import javax.swing.JFrame;

//Game class
public class Game {
	
	//Main method to create JFrame object from TitleScreen class
	public static void main(String[] args) {
		
		TitleScreen frameObject = new TitleScreen();
		//JFrame settings
		frameObject.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameObject.setResizable(false);
		frameObject.setBounds(0, 0, 1400, 900);
		frameObject.setVisible(true);
		frameObject.setLocationRelativeTo(null);
		
	}
	
}
