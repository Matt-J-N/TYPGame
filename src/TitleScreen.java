//Importing necessary libraries
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class TitleScreen extends JFrame {

	private JButton button;
	
	private JPanel panel;

	private static JPanel winPanel;
	
	private static JButton winButton;
	
	private static JPanel losePanel;
	
	private static JButton loseButton;
	
	private Dimension buttonDim = new Dimension(250, 100);
	
	private JLabel labelTitle = new JLabel("COMP39X Tile-Based Strategy Game", SwingConstants.CENTER);
	
	private JLabel howTitle = new JLabel("How To Play:", SwingConstants.CENTER);
	
	private JLabel selectHeader = new JLabel("Please Select Your Player Colour below:", SwingConstants.CENTER);
	
	private JLabel instructTextOne = new JLabel("- Select the colour you wish to play as", SwingConstants.CENTER);
	private JLabel instructTextTwo = new JLabel("- Click a tile to attempt to take it ", SwingConstants.CENTER);
	private JLabel instructTextThree = new JLabel("- Take all 37 tiles to win", SwingConstants.CENTER);
	private JLabel instructTextFour = new JLabel("- Don't let the enemy players reach 37 tiles", SwingConstants.CENTER);
	private JLabel instructTextFive = new JLabel("- Capture special tiles for bonuses (Shown with W, M & F) ", SwingConstants.CENTER);
	private JLabel instructTextFinal = new JLabel("See the user guide in the appendix for full instructions ", SwingConstants.CENTER);
	
	private static JLabel winText = new JLabel("You Win!", SwingConstants.CENTER);
	
	private static JLabel loseText = new JLabel("You lost.", SwingConstants.CENTER);
	
	private static JLabel subWinText = new JLabel("You managed to gain control of all the territory, eliminating the enemy players!", 
													SwingConstants.CENTER);
			
	private String[] playerColors = new String[] {"Blue", "Red", "Yellow", "Green"};
	
	private String[] buttonArray = {"PLAY", "HOW TO PLAY", "CREDITS", "EXIT"};
	
	private static String[] wlButtonArray = { "PLAY AGAIN", "EXIT"};
	
	public static Color playerColor;
	
	
	public TitleScreen() {
		
		super("COMP39X Tile-Based Strategy Game");
		
		panel = new JPanel();
		panel.setBounds(0, 0, 1400, 900);
		panel.setBackground(Color.WHITE);
		panel.setLayout(new GridLayout(0, 1));
		add(panel);
		labelTitle.setFont(new Font("Helvetica nueu", Font.BOLD, 24));
		panel.add(labelTitle);
		
		//Loop for main menu button create
		//Each button label from buttonArray assigned
		//Buttons settings established and buttons added to panel via buttonInit call
		for (int i = 0; i < buttonArray.length; i++) {
			button = new JButton(buttonArray[i]);
			buttonInit(button, panel, new ButtonListener1());
		}
	}
	
	private void buttonInit(JButton button, JPanel panel, ActionListener b) {
		button.setAlignmentX(CENTER_ALIGNMENT);
		button.setBackground(Color.WHITE);
		button.addActionListener(b);
		button.setPreferredSize(buttonDim);
		panel.add(button);
	}
	
	private void screenFrameInit(JFrame thisFrame, int closeOperation, int w, int h) {
		thisFrame.setDefaultCloseOperation(closeOperation);
		thisFrame.setBounds(0, 0, w, h);
		thisFrame.setVisible(true);
		thisFrame.setLocationRelativeTo(null);
	}
	
	//Button Listener for Main Menu screen 
	private class ButtonListener1 implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			
			JButton button = (JButton) e.getSource();
			String buttonType = button.getText();
	
			//Condition for PLAY button
			if (buttonType == buttonArray[0]) {
				playScreen();
				
			//Condition for HOW TO PLAY button	
			}else if (buttonType == buttonArray[1]) {
				howScreen();
				
			//Condition for CREDITS button	
			}else if (buttonType == buttonArray[2]) {
				creditScreen();
				
			//Condition for EXIT button	
			}else if (buttonType == buttonArray[3]) {
				System.exit(0);
			}	
		}
	}
	
	//Button Listener for colour selection menu - Prompted by playScreen()
	private class ButtonListener2 implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			
			JButton button = (JButton) e.getSource();
			String buttonType = button.getText();
	
			//Condition for PLAY button
			if (buttonType == playerColors[0]) {
				playerColor = Color.BLUE;
				
			//Condition for HOW TO PLAY button	
			}else if (buttonType == playerColors[1]) {
				playerColor = Color.RED;
				
			//Condition for CREDITS button	
			}else if (buttonType == playerColors[2]) {
				playerColor = Color.YELLOW;
				
			//Condition for EXIT button	
			}else if (buttonType == playerColors[3]) {
				playerColor = Color.GREEN;	
			}

			//Call game to run once colour selection is made
			gameRun();	
		}
	}
	
	private class ButtonListener3 implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			
			JButton winButton = (JButton) e.getSource();
			String buttonType = winButton.getText();
			
			//Condition for PLAY AGAIN on win screen
			if (buttonType == wlButtonArray[0]) {
				playScreen();
			
			//Condition for EXIT button on win screen	
			}else if (buttonType == wlButtonArray[1]) {
				System.exit(0);
			}
		}	
	}
	
	public void creditScreen() {
		JFrame creditScreen = new JFrame();
		screenFrameInit(creditScreen, HIDE_ON_CLOSE, 600, 250);

		panel = new JPanel();
		panel.setBounds(0, 0, 600, 250);
		panel.setBackground(Color.WHITE);
		creditScreen.add(panel);
		
		JLabel credits = new JLabel("<html>Idea, programming and design by Matthew Neersoo,<br/>"
								+ "with special thanks to Dr. Ullrich Hustadt<br/>"
								+ "& the authors of all referenced material.</html>");
		credits.setFont(new Font("Helvetica nueu", Font.PLAIN, 18));
		credits.setAlignmentX(CENTER_ALIGNMENT);
		panel.add(credits);
		
	}
	
	public void howScreen() {
		JFrame howScreen = new JFrame();
		screenFrameInit(howScreen, HIDE_ON_CLOSE, 900, 600);

		panel = new JPanel();
		panel.setBounds(0, 0, 900, 600);
		panel.setBackground(Color.WHITE);
		panel.setLayout(new GridLayout(0, 1));
		howScreen.add(panel);
		
		howTitle.setFont(new Font("Helvetica nueu", Font.BOLD, 24));
		panel.add(howTitle);
		instructTextOne.setFont(new Font("Helvetica nueu", Font.PLAIN, 20));
		instructTextTwo.setFont(new Font("Helvetica nueu", Font.PLAIN, 20));
		instructTextThree.setFont(new Font("Helvetica nueu", Font.PLAIN, 20));
		instructTextFour.setFont(new Font("Helvetica nueu", Font.PLAIN, 20));
		instructTextFive.setFont(new Font("Helvetica nueu", Font.PLAIN, 20));
		panel.add(instructTextOne);		
		panel.add(instructTextTwo);
		panel.add(instructTextThree);
		panel.add(instructTextFour);
		panel.add(instructTextFive);
		instructTextFinal.setFont(new Font("Helvetica nueu", Font.BOLD, 22));
		panel.add(instructTextFinal);
	}
	
	public void colorSelect() {
		JFrame colorSelect = new JFrame();
		screenFrameInit(colorSelect, HIDE_ON_CLOSE, 500, 400);

		panel = new JPanel();
		panel.setBounds(0, 0, 300, 500);
		panel.setBackground(Color.WHITE);
		panel.setLayout(new GridLayout(0, 1));
		colorSelect.add(panel);
		
		selectHeader.setFont(new Font("Helvetica nueu", Font.PLAIN, 22));
		panel.add(selectHeader);
		
		for (int i = 0; i < playerColors.length; i++) {
			button = new JButton(playerColors[i]);
			buttonInit(button, panel, new ButtonListener2());
		}
	}
	
	public void winScreen() {
		JFrame winScreen = new JFrame();
		screenFrameInit(winScreen, HIDE_ON_CLOSE, 1400, 900);

		winPanel = new JPanel();
		winPanel.setBounds(0, 0, 1400, 900);
		winPanel.setBackground(Color.WHITE);
		winPanel.setLayout(new GridLayout(0, 1));
		winScreen.add(winPanel);
		winText.setFont(new Font("Helvetica nueu", Font.BOLD, 48));
		subWinText.setFont(new Font("Helvetica nueu", Font.BOLD, 24));
		winPanel.add(winText);
		
		for (int i = 0; i < wlButtonArray.length; i++) {
			winButton = new JButton(wlButtonArray[i]);
			buttonInit(winButton, winPanel, new ButtonListener3());
		}

		winPanel.add(subWinText);
		
	}
	
	public void loseScreen() {
		JFrame loseScreen = new JFrame();
		screenFrameInit(loseScreen, HIDE_ON_CLOSE, 1400, 900);
		
		losePanel = new JPanel();
		losePanel.setBounds(0, 0, 1400, 900);
		losePanel.setBackground(Color.WHITE);
		losePanel.setLayout(new GridLayout(0, 1));
		loseScreen.add(losePanel);
		loseText.setFont(new Font("Helvetica nueu", Font.BOLD, 48));
		losePanel.add(loseText);
		
		for (int i = 0; i < wlButtonArray.length; i++) {
			loseButton = new JButton(wlButtonArray[i]);
			buttonInit(loseButton, losePanel, new ButtonListener3());
		}
	}
	
	public void gameRun() {
		Draw drawObject = new Draw();
		drawObject.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		drawObject.setResizable(false);
		drawObject.setBounds(0, 0, 1400, 900);
		drawObject.setVisible(true);
		drawObject.setLocationRelativeTo(null);
		
		drawObject.tileTypeGen(drawObject.citTiles, drawObject.farmTiles, drawObject.medTiles, drawObject.weapTiles, drawObject.emptyTiles);
	}
	
	public static Color playerColor() {
		return playerColor;
	}
	
	public void playScreen() {
		colorSelect();
	}
	
}
