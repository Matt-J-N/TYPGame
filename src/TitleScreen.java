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
		
		button = new JButton(buttonArray[0]);
		button.setAlignmentX(CENTER_ALIGNMENT);
		button.setBackground(Color.WHITE);
		button.addActionListener(new ButtonListener1());
		button.setPreferredSize(buttonDim);
		panel.add(button);
		
		button = new JButton(buttonArray[1]);
		button.setAlignmentX(CENTER_ALIGNMENT);
		button.setBackground(Color.WHITE);
		button.addActionListener(new ButtonListener1());
		button.setPreferredSize(buttonDim);
		panel.add(button);
		
		button = new JButton(buttonArray[2]);
		button.setAlignmentX(CENTER_ALIGNMENT);
		button.setBackground(Color.WHITE);
		button.addActionListener(new ButtonListener1());
		button.setPreferredSize(buttonDim);
		panel.add(button);

		button = new JButton(buttonArray[3]);
		button.setAlignmentX(CENTER_ALIGNMENT);
		button.setBackground(Color.WHITE);
		button.addActionListener(new ButtonListener1());
		button.setPreferredSize(buttonDim);
		panel.add(button);
		
		/*int j = 50;
		
		for(int i = 0; i < buttonArray.length; i++) {
			
			panel = new JPanel();
			panel.setBounds(500, j, 300, 150);
			add(panel);
			
			button = new JButton(buttonArray[i]);
			button.setPreferredSize(buttonDim);
			panel.add(button);
			
			j = j + 150;
		
		}*/
		
	}
	
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
		creditScreen.setDefaultCloseOperation(HIDE_ON_CLOSE);
		creditScreen.setBounds(0, 0, 600, 250);
		creditScreen.setVisible(true);
		
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
		howScreen.setDefaultCloseOperation(HIDE_ON_CLOSE);
		howScreen.setBounds(0, 0, 900, 600);
		howScreen.setVisible(true);
		
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
		//Change from exit to pop up again? > Player MUST choose colour
		colorSelect.setDefaultCloseOperation(EXIT_ON_CLOSE);
		colorSelect.setBounds(400, 200, 500, 400);
		colorSelect.setVisible(true);
		colorSelect.setLocationRelativeTo(null);
		
		panel = new JPanel();
		panel.setBounds(0, 0, 300, 500);
		panel.setBackground(Color.WHITE);
		panel.setLayout(new GridLayout(0, 1));
		colorSelect.add(panel);
		
		selectHeader.setFont(new Font("Helvetica nueu", Font.PLAIN, 22));
		panel.add(selectHeader);
		
		button = new JButton(playerColors[0]);
		button.setAlignmentX(CENTER_ALIGNMENT);
		button.setBackground(Color.WHITE);
		button.addActionListener(new ButtonListener2());
		panel.add(button);
		
		button = new JButton(playerColors[1]);
		button.setAlignmentX(CENTER_ALIGNMENT);
		button.setBackground(Color.WHITE);
		button.addActionListener(new ButtonListener2());
		panel.add(button);
		
		button = new JButton(playerColors[2]);
		button.setAlignmentX(CENTER_ALIGNMENT);
		button.setBackground(Color.WHITE);
		button.addActionListener(new ButtonListener2());
		panel.add(button);
		
		button = new JButton(playerColors[3]);
		button.setAlignmentX(CENTER_ALIGNMENT);
		button.setBackground(Color.WHITE);
		button.addActionListener(new ButtonListener2());
		panel.add(button);
		
	}
	
	public void winScreen() {
		JFrame winScreen = new JFrame();
		winScreen.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		winScreen.setResizable(false);
		winScreen.setBounds(0, 0, 1400, 900);
		winScreen.setVisible(true);
		winScreen.setLocationRelativeTo(null);
		
		winPanel = new JPanel();
		winPanel.setBounds(0, 0, 1400, 900);
		winPanel.setBackground(Color.WHITE);
		winPanel.setLayout(new GridLayout(0, 1));
		winScreen.add(winPanel);
		winText.setFont(new Font("Helvetica nueu", Font.BOLD, 48));
		subWinText.setFont(new Font("Helvetica nueu", Font.BOLD, 24));
		winPanel.add(winText);
		
		winButton = new JButton(wlButtonArray[0]);
		winButton.setAlignmentX(CENTER_ALIGNMENT);
		winButton.setBackground(Color.WHITE);
		winButton.addActionListener(new ButtonListener3());
		winPanel.add(winButton);
		
		winButton = new JButton(wlButtonArray[1]);
		winButton.setAlignmentX(CENTER_ALIGNMENT);
		winButton.setBackground(Color.WHITE);
		winButton.addActionListener(new ButtonListener3());
		winPanel.add(winButton);
		
		winPanel.add(subWinText);
		
	}
	
	public void loseScreen() {
		JFrame loseScreen = new JFrame();
		loseScreen.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		loseScreen.setResizable(false);
		loseScreen.setBounds(0, 0, 1400, 900);
		loseScreen.setVisible(true);
		loseScreen.setLocationRelativeTo(null);
		
		losePanel = new JPanel();
		losePanel.setBounds(0, 0, 1400, 900);
		losePanel.setBackground(Color.WHITE);
		losePanel.setLayout(new GridLayout(0, 1));
		loseScreen.add(losePanel);
		loseText.setFont(new Font("Helvetica nueu", Font.BOLD, 48));
		losePanel.add(loseText);
		
		loseButton = new JButton(wlButtonArray[0]);
		loseButton.setAlignmentX(CENTER_ALIGNMENT);
		loseButton.setBackground(Color.WHITE);
		loseButton.addActionListener(new ButtonListener3());
		losePanel.add(loseButton);
		
		loseButton = new JButton(wlButtonArray[1]);
		loseButton.setAlignmentX(CENTER_ALIGNMENT);
		loseButton.setBackground(Color.WHITE);
		loseButton.addActionListener(new ButtonListener3());
		losePanel.add(loseButton);
		
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
