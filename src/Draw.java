import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class Draw extends JFrame {

    //private static final long serialVersionUID = 3775690273871048733L;

	private DrawingPanel drawingPanel;
    
    Random rd = new Random();
    
    private int defaultRad = 50;
    
    private int round = 0;
    
    private String[] colorStrings = new String[] {"Blue", "Red", "Yellow", "Green"};
    
    private int[] controlledTiles = new int[38];

    //General tile storage arrays
    
    int[] emptyTiles = new int[21];
    
    int[] citTiles = new int[4];
    
    int[] medTiles = new int[4];
    
    int[] weapTiles = new int[4];
    
    int[] farmTiles = new int[4];
    
    //Human player (controlled) tile arrays
    
    int[] pControls = new int[38];
    
    int[] pConMilPower = new int[38];
    
    int[] pConInfluence = new int[38];
    
    int[] pConFood = new int[38];
    
    //AI control arrays
    
    int[] aiOneControls = new int[38];
    
    int[] aiTwoControls = new int[38];
    
    int[] aiThreeControls = new int[38];
    
    //Colour handling variables
    
    private Color col = Color.WHITE;
    
    private String colString;
    
	private Color playerColor = TitleScreen.playerColor();
	
	private Color colorAIOne;
    
    private Color colorAITwo;
    
    private Color colorAIThree; 
	
	//Bonus modifier initialisations
	
	private double pMilPower = 1;
	
	private double pFood = 1;
	
    private double pInfluence = 1;
    
    private double milPowerAIOne = 1;
    
    private double foodAIOne = 1;
    
    private double influenceAIOne = 1;
    
    private double milPowerAITwo = 1;
    	    
    private double foodAITwo = 1;
    	    
    private double influenceAITwo = 1;
    
    private double milPowerAIThree = 1;
    	    
    private double foodAIThree = 1;
    	    
    private double influenceAIThree = 1;
    
    //private int curPlayer = 1;
    
    private int curHex = 0;
    
    /*private int pCurPos = 0;
    
    private int curPosAIOne;
    
    private int curPosAITwo;
    
    private int curPosAIThree;
    
    private int oneInit = 15;
    
    private int twoInit = 23;
    
    private int threeInit = 28;*/
    
    public Draw(){
    	//Reference for hexagon drawing: https://stackoverflow.com/questions/35853902/drawing-hexagon-using-java-error
    	
        //Initial coordinate positions in drawingPanel for first hexagon
    	int coordX = 500;
    	int coordY= 100;
    	
    	//Hexagon tiers relative to Y-coordinate position
    	//Tier 1
    	Hexagon hexagon1 = new Hexagon(new Point(coordX, coordY), defaultRad);
    	Hexagon hexagon2 = new Hexagon(new Point(coordX + (2*defaultRad), coordY), defaultRad);
    	Hexagon hexagon3 = new Hexagon(new Point(coordX + (4*defaultRad), coordY), defaultRad);
    	Hexagon hexagon4 = new Hexagon(new Point(coordX + (6*defaultRad), coordY), defaultRad);
    	
    	//Tier 2
    	Hexagon hexagon5 = new Hexagon(new Point(coordX - defaultRad, coordY + (2*defaultRad)), defaultRad);
    	Hexagon hexagon6 = new Hexagon(new Point(coordX + defaultRad, coordY + (2*defaultRad)), defaultRad);
    	Hexagon hexagon7 = new Hexagon(new Point(coordX + (3*defaultRad), coordY + (2*defaultRad)), defaultRad);
    	Hexagon hexagon8 = new Hexagon(new Point(coordX + (5*defaultRad), coordY + (2*defaultRad)), defaultRad);
    	Hexagon hexagon9 = new Hexagon(new Point(coordX + (7*defaultRad), coordY + (2*defaultRad)), defaultRad);
    	
    	//Tier 3
    	Hexagon hexagon10 = new Hexagon(new Point(coordX - (2*defaultRad), coordY + (4*defaultRad)), defaultRad);
    	Hexagon hexagon11 = new Hexagon(new Point(coordX, coordY + (4*defaultRad)), defaultRad);
    	Hexagon hexagon12 = new Hexagon(new Point(coordX + (2*defaultRad), coordY + (4*defaultRad)), defaultRad);
    	Hexagon hexagon13 = new Hexagon(new Point(coordX + (4*defaultRad), coordY + (4*defaultRad)), defaultRad);
    	Hexagon hexagon14 = new Hexagon(new Point(coordX + (6*defaultRad), coordY + (4*defaultRad)), defaultRad);
    	Hexagon hexagon15 = new Hexagon(new Point(coordX + (8*defaultRad), coordY + (4*defaultRad)), defaultRad);
    	
    	//Tier 4
    	Hexagon hexagon16 = new Hexagon(new Point(coordX - (3*defaultRad), coordY + (6*defaultRad)), defaultRad);
    	Hexagon hexagon17 = new Hexagon(new Point(coordX - defaultRad, coordY + (6*defaultRad)), defaultRad);
    	Hexagon hexagon18 = new Hexagon(new Point(coordX + defaultRad, coordY + (6*defaultRad)), defaultRad);
    	Hexagon hexagon19 = new Hexagon(new Point(coordX + (3*defaultRad), coordY + (6*defaultRad)), defaultRad);
    	Hexagon hexagon20 = new Hexagon(new Point(coordX + (5*defaultRad), coordY + (6*defaultRad)), defaultRad);
    	Hexagon hexagon21 = new Hexagon(new Point(coordX + (7*defaultRad), coordY + (6*defaultRad)), defaultRad);
    	Hexagon hexagon22 = new Hexagon(new Point(coordX + (9*defaultRad), coordY + (6*defaultRad)), defaultRad);
    	
    	//Tier 5
    	Hexagon hexagon23 = new Hexagon(new Point(coordX - (2*defaultRad), coordY + (8*defaultRad)), defaultRad);
    	Hexagon hexagon24 = new Hexagon(new Point(coordX, coordY + (8*defaultRad)), defaultRad);
    	Hexagon hexagon25 = new Hexagon(new Point(coordX + (2*defaultRad), coordY + (8*defaultRad)), defaultRad);
    	Hexagon hexagon26 = new Hexagon(new Point(coordX + (4*defaultRad), coordY + (8*defaultRad)), defaultRad);
    	Hexagon hexagon27 = new Hexagon(new Point(coordX + (6*defaultRad), coordY + (8*defaultRad)), defaultRad);
    	Hexagon hexagon28 = new Hexagon(new Point(coordX + (8*defaultRad), coordY + (8*defaultRad)), defaultRad);
    	
    	//Tier 6
    	Hexagon hexagon29 = new Hexagon(new Point(coordX - defaultRad, coordY + (10*defaultRad)), defaultRad);
    	Hexagon hexagon30 = new Hexagon(new Point(coordX + defaultRad, coordY + (10*defaultRad)), defaultRad);
    	Hexagon hexagon31 = new Hexagon(new Point(coordX + (3*defaultRad), coordY + (10*defaultRad)), defaultRad);
    	Hexagon hexagon32 = new Hexagon(new Point(coordX + (5*defaultRad), coordY + (10*defaultRad)), defaultRad);
    	Hexagon hexagon33 = new Hexagon(new Point(coordX + (7*defaultRad), coordY + (10*defaultRad)), defaultRad);

    	
    	//Tier 7
    	Hexagon hexagon34 = new Hexagon(new Point(coordX, coordY + (12*defaultRad)), defaultRad);
    	Hexagon hexagon35 = new Hexagon(new Point(coordX + (2*defaultRad), coordY + (12*defaultRad)), defaultRad);
    	Hexagon hexagon36 = new Hexagon(new Point(coordX + (4*defaultRad), coordY + (12*defaultRad)), defaultRad);
    	Hexagon hexagon37 = new Hexagon(new Point(coordX + (6*defaultRad), coordY + (12*defaultRad)), defaultRad);
    	
        drawingPanel = new DrawingPanel(hexagon1, hexagon2, hexagon3, hexagon4, hexagon5, hexagon6, hexagon7,
        								hexagon8, hexagon9, hexagon10, hexagon11, hexagon12, hexagon13, hexagon14,
        								hexagon15, hexagon16, hexagon17, hexagon18, hexagon19, hexagon20, hexagon21,
        								hexagon22, hexagon23, hexagon24, hexagon25, hexagon26, hexagon27,
        							    hexagon28, hexagon29, hexagon30, hexagon31, hexagon32,
        								hexagon33, hexagon34, hexagon35, hexagon36, hexagon37);
        
        drawingPanel.setBounds(0, 0, 1400, 800);
		drawingPanel.setBackground(Color.WHITE);
        add(drawingPanel);
        
        pack();
        setLocationByPlatform(true);
        setVisible(true);
        TitleScreen ts = new TitleScreen();
        assignAI();
        MCTS mc = new MCTS();
                
        drawingPanel.addMouseListener(new MouseAdapter(){
        	
        	//Retrieve X-Y coordinates of mouse click(s)
            public void mouseClicked(MouseEvent e) {
            	
            	int clickX = e.getX();
			    int clickY = e.getY();
			    
			    /*int[] playerSur = mc.getSurTiles(curHex);
			    System.out.println("PLAYER SURTILES: " + Arrays.toString(playerSur));*/
			    
			    //POTENTIAL EFFICIENT LOOP
			    /*for (int i = 0; i < hexArray.length; i++) {
			    	if (hexArray[i].isInside(clickX, clickY)){
			    		curHex = i+1;
				    	double probability = tileProcess(curHex, pFood, pMilPower, pInfluence);
				    			
				    	if (tileTakeAttempt(probability) == true) {
				    		col = playerColor;
				    		pControls[curHex-1] = curHex;
				    		
				    		if (isWeapFac(curHex) == true) {
				    			pConMilPower[curHex-1] = curHex;
				    		} else if (isMediaComp(1) == true) {
				    			pConInfluence[curHex-1] = curHex;
				    		} else if (isFarmland(1) == true) {
				    			pConFood[curHex-1] = curHex;
				    		}
				    		
				    		tileLoss(aiOneControls, aiTwoControls, aiThreeControls, curHex, milPowerAIOne, influenceAIOne, foodAIOne, milPowerAITwo, influenceAITwo,
				    				foodAITwo, milPowerAIThree, influenceAIThree, foodAIThree, isWeapFac(curHex), isMediaComp(curHex), isFarmland(curHex));
				    		drawingPanel.paintImmediately(getXMin(hexagon1), getYMin(hexagon1), calcWidth(hexagon1), calcHeight(hexagon1));
				    		takeSuccess();
				    		
				    	}else if (tileTakeAttempt(probability) == false){
				    		failedTake();

				    	}
			    	}else {
			    		System.out.println("NO HEX");
			    	}
			    }*/

			    if (hexagon1.isInside(clickX, clickY)) {
			    	curHex = 1;
			    	double probability = tileProcess(curHex, pFood, pMilPower, pInfluence);
			    			
			    	if (tileTakeAttempt(probability) == true) {
			    		col = playerColor;
			    		pControls[curHex-1] = curHex;
			    		
			    		//Change to isWeapFac(curHex)
			    		if (isWeapFac(curHex) == true) {
			    			pConMilPower[curHex-1] = curHex;
			    		} else if (isMediaComp(1) == true) {
			    			pConInfluence[curHex-1] = curHex;
			    		} else if (isFarmland(1) == true) {
			    			pConFood[curHex-1] = curHex;
			    		}
			    		
			    		tileLoss(aiOneControls, aiTwoControls, aiThreeControls, curHex, milPowerAIOne, influenceAIOne, foodAIOne, milPowerAITwo, influenceAITwo,
			    				foodAITwo, milPowerAIThree, influenceAIThree, foodAIThree, isWeapFac(curHex), isMediaComp(curHex), isFarmland(curHex));
			    		drawingPanel.paintImmediately(getXMin(hexagon1), getYMin(hexagon1), calcWidth(hexagon1), calcHeight(hexagon1));
			    		takeSuccess();
			    		
			    	}else if (tileTakeAttempt(probability) == false){
			    		failedTake();

			    	}
			    	
			    }else if (hexagon2.isInside(clickX, clickY)) {
			    	curHex = 2;
			    	double probability = tileProcess(curHex, pFood, pMilPower, pInfluence);
			    	
			    	if (tileTakeAttempt(probability) == true) {
			    		col = playerColor;
			    		pControls[curHex-1] = curHex;
			    		
			    		if (isWeapFac(curHex) == true) {
			    			pConMilPower[curHex-1] = curHex;
			    		} else if (isMediaComp(curHex) == true) {
			    			pConInfluence[curHex-1] = curHex;
			    		} else if (isFarmland(curHex) == true) {
			    			pConFood[curHex-1] = curHex;
			    		}
			    		
			    		tileLoss(aiOneControls, aiTwoControls, aiThreeControls, curHex, milPowerAIOne, influenceAIOne, foodAIOne, milPowerAITwo, influenceAITwo,
			    				foodAITwo, milPowerAIThree, influenceAIThree, foodAIThree, isWeapFac(curHex), isMediaComp(curHex), isFarmland(curHex));
			    		drawingPanel.paintImmediately(getXMin(hexagon2), getYMin(hexagon2), calcWidth(hexagon2), calcHeight(hexagon2));
			    		takeSuccess();
			    		
			    	}else if (tileTakeAttempt(probability) == false){
			    		failedTake();
			    	}

			    }else if (hexagon3.isInside(clickX, clickY)) {
			    	curHex = 3;
			    	double probability = tileProcess(curHex, pFood, pMilPower, pInfluence);
			    			
			    	if (tileTakeAttempt(probability) == true) {
			    		col = playerColor;
			    		pControls[curHex-1] = curHex;
			    		
			    		if (isWeapFac(curHex) == true) {
			    			pConMilPower[curHex-1] = curHex;
			    		} else if (isMediaComp(curHex) == true) {
			    			pConInfluence[curHex-1] = curHex;
			    		} else if (isFarmland(curHex) == true) {
			    			pConFood[curHex-1] = curHex;
			    		}
			    		
			    		tileLoss(aiOneControls, aiTwoControls, aiThreeControls, curHex, milPowerAIOne, influenceAIOne, foodAIOne, milPowerAITwo, influenceAITwo,
			    				foodAITwo, milPowerAIThree, influenceAIThree, foodAIThree, isWeapFac(curHex), isMediaComp(curHex), isFarmland(curHex));
			    		drawingPanel.paintImmediately(getXMin(hexagon3), getYMin(hexagon3), calcWidth(hexagon3), calcHeight(hexagon3));
			    		takeSuccess();
			    		
			    	}else if (tileTakeAttempt(probability) == false){
			    		failedTake();
			    	}

			    }else if (hexagon4.isInside(clickX, clickY)) {
			    	curHex = 4;
			    	double probability = tileProcess(curHex, pFood, pMilPower, pInfluence);
			    			
			    	if (tileTakeAttempt(probability) == true) {
			    		col = playerColor;
			    		pControls[curHex-1] = curHex;
			    		
			    		if (isWeapFac(curHex) == true) {
			    			pConMilPower[curHex-1] = curHex;
			    		} else if (isMediaComp(curHex) == true) {
			    			pConInfluence[curHex-1] = curHex;
			    		} else if (isFarmland(curHex) == true) {
			    			pConFood[curHex-1] = curHex;
			    		}
			    		
			    		tileLoss(aiOneControls, aiTwoControls, aiThreeControls, curHex, milPowerAIOne, influenceAIOne, foodAIOne, milPowerAITwo, influenceAITwo,
			    				foodAITwo, milPowerAIThree, influenceAIThree, foodAIThree, isWeapFac(curHex), isMediaComp(curHex), isFarmland(curHex));
			    		drawingPanel.paintImmediately(getXMin(hexagon4), getYMin(hexagon4), calcWidth(hexagon4), calcHeight(hexagon4));
			    		takeSuccess();
			    		
			    	}else if (tileTakeAttempt(probability) == false){
			    		failedTake();
			    	}

			    }else if (hexagon5.isInside(clickX, clickY)) {
			    	curHex = 5;
			    	double probability = tileProcess(curHex, pFood, pMilPower, pInfluence);
			    			
			    	if (tileTakeAttempt(probability) == true) {
			    		col = playerColor;
			    		pControls[curHex-1] = curHex;
			    		
			    		if (isWeapFac(curHex) == true) {
			    			pConMilPower[curHex-1] = curHex;
			    		} else if (isMediaComp(curHex) == true) {
			    			pConInfluence[curHex-1] = curHex;
			    		} else if (isFarmland(curHex) == true) {
			    			pConFood[curHex-1] = curHex;
			    		}
			    		
			    		tileLoss(aiOneControls, aiTwoControls, aiThreeControls, curHex, milPowerAIOne, influenceAIOne, foodAIOne, milPowerAITwo, influenceAITwo,
			    				foodAITwo, milPowerAIThree, influenceAIThree, foodAIThree, isWeapFac(curHex), isMediaComp(curHex), isFarmland(curHex));
			    		drawingPanel.paintImmediately(getXMin(hexagon5), getYMin(hexagon5), calcWidth(hexagon5), calcHeight(hexagon5));
			    		takeSuccess();
			    		
			    	}else if (tileTakeAttempt(probability) == false){
			    		failedTake();
			    	}

			    }else if (hexagon6.isInside(clickX, clickY)) {
			    	curHex = 6;
			    	double probability = tileProcess(curHex, pFood, pMilPower, pInfluence);
			    			
			    	if (tileTakeAttempt(probability) == true) {
			    		col = playerColor;
			    		pControls[curHex-1] = curHex;
			    		
			    		if (isWeapFac(curHex) == true) {
			    			pConMilPower[curHex-1] = curHex;
			    		} else if (isMediaComp(curHex) == true) {
			    			pConInfluence[curHex-1] = curHex;
			    		} else if (isFarmland(curHex) == true) {
			    			pConFood[curHex-1] = curHex;
			    		}
			    		
			    		tileLoss(aiOneControls, aiTwoControls, aiThreeControls, curHex, milPowerAIOne, influenceAIOne, foodAIOne, milPowerAITwo, influenceAITwo,
			    				foodAITwo, milPowerAIThree, influenceAIThree, foodAIThree, isWeapFac(curHex), isMediaComp(curHex), isFarmland(curHex));
			    		drawingPanel.paintImmediately(getXMin(hexagon6), getYMin(hexagon6), calcWidth(hexagon6), calcHeight(hexagon6));
			    		takeSuccess();
			    		
			    	}else if (tileTakeAttempt(probability) == false){
			    		failedTake();
			    	}

			    }else if (hexagon7.isInside(clickX, clickY)) {
			    	curHex = 7;
			    	double probability = tileProcess(curHex, pFood, pMilPower, pInfluence);
			    			
			    	if (tileTakeAttempt(probability) == true) {
			    		col = playerColor;
			    		pControls[curHex-1] = curHex;
			    		
			    		if (isWeapFac(curHex) == true) {
			    			pConMilPower[curHex-1] = curHex;
			    		} else if (isMediaComp(curHex) == true) {
			    			pConInfluence[curHex-1] = curHex;
			    		} else if (isFarmland(curHex) == true) {
			    			pConFood[curHex-1] = curHex;
			    		}
			    		
			    		tileLoss(aiOneControls, aiTwoControls, aiThreeControls, curHex, milPowerAIOne, influenceAIOne, foodAIOne, milPowerAITwo, influenceAITwo,
			    				foodAITwo, milPowerAIThree, influenceAIThree, foodAIThree, isWeapFac(curHex), isMediaComp(curHex), isFarmland(curHex));
			    		drawingPanel.paintImmediately(getXMin(hexagon7), getYMin(hexagon7), calcWidth(hexagon7), calcHeight(hexagon7));
			    		takeSuccess();
			    		
			    	}else if (tileTakeAttempt(probability) == false){
			    		failedTake();
			    	}

			    }else if (hexagon8.isInside(clickX, clickY)) {
			    	curHex = 8;
			    	double probability = tileProcess(curHex, pFood, pMilPower, pInfluence);
			    			
			    	if (tileTakeAttempt(probability) == true) {
			    		col = playerColor;
			    		pControls[curHex-1] = curHex;
			    		
			    		if (isWeapFac(curHex) == true) {
			    			pConMilPower[curHex-1] = curHex;
			    		} else if (isMediaComp(curHex) == true) {
			    			pConInfluence[curHex-1] = curHex;
			    		} else if (isFarmland(curHex) == true) {
			    			pConFood[curHex-1] = curHex;
			    		}
			    		
			    		tileLoss(aiOneControls, aiTwoControls, aiThreeControls, curHex, milPowerAIOne, influenceAIOne, foodAIOne, milPowerAITwo, influenceAITwo,
			    				foodAITwo, milPowerAIThree, influenceAIThree, foodAIThree, isWeapFac(curHex), isMediaComp(curHex), isFarmland(curHex));
			    		drawingPanel.paintImmediately(getXMin(hexagon8), getYMin(hexagon8), calcWidth(hexagon8), calcHeight(hexagon8));
			    		takeSuccess();
			    		
			    	}else if (tileTakeAttempt(probability) == false){
			    		failedTake();
			    	}

			    }else if (hexagon9.isInside(clickX, clickY)) {
			    	curHex = 9;
			    	double probability = tileProcess(curHex, pFood, pMilPower, pInfluence);
			    			
			    	if (tileTakeAttempt(probability) == true) {
			    		col = playerColor;
			    		pControls[curHex-1] = curHex;
			    		
			    		if (isWeapFac(curHex) == true) {
			    			pConMilPower[curHex-1] = curHex;
			    		} else if (isMediaComp(curHex) == true) {
			    			pConInfluence[curHex-1] = curHex;
			    		} else if (isFarmland(curHex) == true) {
			    			pConFood[curHex-1] = curHex;
			    		}
			    		
			    		tileLoss(aiOneControls, aiTwoControls, aiThreeControls, curHex, milPowerAIOne, influenceAIOne, foodAIOne, milPowerAITwo, influenceAITwo,
			    				foodAITwo, milPowerAIThree, influenceAIThree, foodAIThree, isWeapFac(curHex), isMediaComp(curHex), isFarmland(curHex));
			    		drawingPanel.paintImmediately(getXMin(hexagon9), getYMin(hexagon9), calcWidth(hexagon9), calcHeight(hexagon9));
			    		takeSuccess();
			    		
			    	}else if (tileTakeAttempt(probability) == false){
			    		failedTake();
			    	}

			    }else if (hexagon10.isInside(clickX, clickY)) {
			    	curHex = 10;
			    	double probability = tileProcess(curHex, pFood, pMilPower, pInfluence);
			    			
			    	if (tileTakeAttempt(probability) == true) {
			    		col = playerColor;
			    		pControls[curHex-1] = curHex;
			    		
			    		if (isWeapFac(curHex) == true) {
			    			pConMilPower[curHex-1] = curHex;
			    		} else if (isMediaComp(curHex) == true) {
			    			pConInfluence[curHex-1] = curHex;
			    		} else if (isFarmland(curHex) == true) {
			    			pConFood[curHex-1] = curHex;
			    		}
			    		
			    		tileLoss(aiOneControls, aiTwoControls, aiThreeControls, curHex, milPowerAIOne, influenceAIOne, foodAIOne, milPowerAITwo, influenceAITwo,
			    				foodAITwo, milPowerAIThree, influenceAIThree, foodAIThree, isWeapFac(curHex), isMediaComp(curHex), isFarmland(curHex));
			    		drawingPanel.paintImmediately(getXMin(hexagon10), getYMin(hexagon10), calcWidth(hexagon10), calcHeight(hexagon10));
			    		takeSuccess();
			    		
			    	}else if (tileTakeAttempt(probability) == false){
			    		failedTake();
			    	}

			    }else if (hexagon11.isInside(clickX, clickY)) {
			    	curHex = 11;
			    	double probability = tileProcess(curHex, pFood, pMilPower, pInfluence);
			    			
			    	if (tileTakeAttempt(probability) == true) {
			    		col = playerColor;
			    		pControls[curHex-1] = curHex;
			    		
			    		if (isWeapFac(curHex) == true) {
			    			pConMilPower[curHex-1] = curHex;
			    		} else if (isMediaComp(curHex) == true) {
			    			pConInfluence[curHex-1] = curHex;
			    		} else if (isFarmland(curHex) == true) {
			    			pConFood[curHex-1] = curHex;
			    		}
			    		
			    		tileLoss(aiOneControls, aiTwoControls, aiThreeControls, curHex, milPowerAIOne, influenceAIOne, foodAIOne, milPowerAITwo, influenceAITwo,
			    				foodAITwo, milPowerAIThree, influenceAIThree, foodAIThree, isWeapFac(curHex), isMediaComp(curHex), isFarmland(curHex));
			    		drawingPanel.paintImmediately(getXMin(hexagon11), getYMin(hexagon11), calcWidth(hexagon11), calcHeight(hexagon11));
			    		takeSuccess();
			    		
			    	}else if (tileTakeAttempt(probability) == false){
			    		failedTake();
			    	}
	
			    }else if (hexagon12.isInside(clickX, clickY)) {
			    	curHex = 12;
			    	double probability = tileProcess(curHex, pFood, pMilPower, pInfluence);
			    			
			    	if (tileTakeAttempt(probability) == true) {
			    		col = playerColor;
			    		pControls[curHex-1] = curHex;
			    		
			    		if (isWeapFac(curHex) == true) {
			    			pConMilPower[curHex-1] = curHex;
			    		} else if (isMediaComp(curHex) == true) {
			    			pConInfluence[curHex-1] = curHex;
			    		} else if (isFarmland(curHex) == true) {
			    			pConFood[curHex-1] = curHex;
			    		}
			    		
			    		tileLoss(aiOneControls, aiTwoControls, aiThreeControls, curHex, milPowerAIOne, influenceAIOne, foodAIOne, milPowerAITwo, influenceAITwo,
			    				foodAITwo, milPowerAIThree, influenceAIThree, foodAIThree, isWeapFac(curHex), isMediaComp(curHex), isFarmland(curHex));
			    		drawingPanel.paintImmediately(getXMin(hexagon12), getYMin(hexagon12), calcWidth(hexagon12), calcHeight(hexagon12));
			    		takeSuccess();
			    		
			    	}else if (tileTakeAttempt(probability) == false){
			    		failedTake();
			    	}

			    }else if (hexagon13.isInside(clickX, clickY)) {
			    	curHex = 13;
			    	double probability = tileProcess(curHex, pFood, pMilPower, pInfluence);
			    			
			    	if (tileTakeAttempt(probability) == true) {
			    		col = playerColor;
			    		pControls[curHex-1] = curHex;
			    		
			    		if (isWeapFac(curHex) == true) {
			    			pConMilPower[curHex-1] = curHex;
			    		} else if (isMediaComp(curHex) == true) {
			    			pConInfluence[curHex-1] = curHex;
			    		} else if (isFarmland(curHex) == true) {
			    			pConFood[curHex-1] = curHex;
			    		}
			    		
			    		tileLoss(aiOneControls, aiTwoControls, aiThreeControls, curHex, milPowerAIOne, influenceAIOne, foodAIOne, milPowerAITwo, influenceAITwo,
			    				foodAITwo, milPowerAIThree, influenceAIThree, foodAIThree, isWeapFac(curHex), isMediaComp(curHex), isFarmland(curHex));
			    		drawingPanel.paintImmediately(getXMin(hexagon13), getYMin(hexagon13), calcWidth(hexagon13), calcHeight(hexagon13));
			    		takeSuccess();
			    		
			    	}else if (tileTakeAttempt(probability) == false){
			    		failedTake();
			    	}

			    }else if (hexagon14.isInside(clickX, clickY)) {
			    	curHex = 14;
			    	double probability = tileProcess(curHex, pFood, pMilPower, pInfluence);
			    			
			    	if (tileTakeAttempt(probability) == true) {
			    		col = playerColor;
			    		pControls[curHex-1] = curHex;
			    		
			    		if (isWeapFac(curHex) == true) {
			    			pConMilPower[curHex-1] = curHex;
			    		} else if (isMediaComp(curHex) == true) {
			    			pConInfluence[curHex-1] = curHex;
			    		} else if (isFarmland(curHex) == true) {
			    			pConFood[curHex-1] = curHex;
			    		}
			    		
			    		tileLoss(aiOneControls, aiTwoControls, aiThreeControls, curHex, milPowerAIOne, influenceAIOne, foodAIOne, milPowerAITwo, influenceAITwo,
			    				foodAITwo, milPowerAIThree, influenceAIThree, foodAIThree, isWeapFac(curHex), isMediaComp(curHex), isFarmland(curHex));
			    		drawingPanel.paintImmediately(getXMin(hexagon14), getYMin(hexagon14), calcWidth(hexagon14), calcHeight(hexagon14));
			    		takeSuccess();
			    		
			    	}else if (tileTakeAttempt(probability) == false){
			    		failedTake();
			    	}

			    }else if (hexagon15.isInside(clickX, clickY)) {
			    	curHex = 15;
			    	double probability = tileProcess(curHex, pFood, pMilPower, pInfluence);
			    			
			    	if (tileTakeAttempt(probability) == true) {
			    		col = playerColor;
			    		pControls[curHex-1] = curHex;
			    		
			    		if (isWeapFac(curHex) == true) {
			    			pConMilPower[curHex-1] = curHex;
			    		} else if (isMediaComp(curHex) == true) {
			    			pConInfluence[curHex-1] = curHex;
			    		} else if (isFarmland(curHex) == true) {
			    			pConFood[curHex-1] = curHex;
			    		}
			    		
			    		tileLoss(aiOneControls, aiTwoControls, aiThreeControls, curHex, milPowerAIOne, influenceAIOne, foodAIOne, milPowerAITwo, influenceAITwo,
			    				foodAITwo, milPowerAIThree, influenceAIThree, foodAIThree, isWeapFac(curHex), isMediaComp(curHex), isFarmland(curHex));
			    		drawingPanel.paintImmediately(getXMin(hexagon15), getYMin(hexagon15), calcWidth(hexagon15), calcHeight(hexagon15));
			    		takeSuccess();
			    		
			    	}else if (tileTakeAttempt(probability) == false){
			    		failedTake();
			    	}
	
			    }else if (hexagon16.isInside(clickX, clickY)) {
			    	curHex = 16;
			    	double probability = tileProcess(curHex, pFood, pMilPower, pInfluence);
			    			
			    	if (tileTakeAttempt(probability) == true) {
			    		col = playerColor;
			    		pControls[curHex-1] = curHex;
			    		
			    		if (isWeapFac(curHex) == true) {
			    			pConMilPower[curHex-1] = curHex;
			    		} else if (isMediaComp(curHex) == true) {
			    			pConInfluence[curHex-1] = curHex;
			    		} else if (isFarmland(curHex) == true) {
			    			pConFood[curHex-1] = curHex;
			    		}
			    		
			    		tileLoss(aiOneControls, aiTwoControls, aiThreeControls, curHex, milPowerAIOne, influenceAIOne, foodAIOne, milPowerAITwo, influenceAITwo,
			    				foodAITwo, milPowerAIThree, influenceAIThree, foodAIThree, isWeapFac(curHex), isMediaComp(curHex), isFarmland(curHex));
			    		drawingPanel.paintImmediately(getXMin(hexagon16), getYMin(hexagon16), calcWidth(hexagon16), calcHeight(hexagon16));
			    		takeSuccess();
			    		
			    	}else if (tileTakeAttempt(probability) == false){
			    		failedTake();
			    	}

			    }else if (hexagon17.isInside(clickX, clickY)) {
			    	curHex = 17;
			    	double probability = tileProcess(curHex, pFood, pMilPower, pInfluence);
			    			
			    	if (tileTakeAttempt(probability) == true) {
			    		col = playerColor;
			    		pControls[curHex-1] = curHex;
			    		
			    		if (isWeapFac(curHex) == true) {
			    			pConMilPower[curHex-1] = curHex;
			    		} else if (isMediaComp(curHex) == true) {
			    			pConInfluence[curHex-1] = curHex;
			    		} else if (isFarmland(curHex) == true) {
			    			pConFood[curHex-1] = curHex;
			    		}
			    		
			    		tileLoss(aiOneControls, aiTwoControls, aiThreeControls, curHex, milPowerAIOne, influenceAIOne, foodAIOne, milPowerAITwo, influenceAITwo,
			    				foodAITwo, milPowerAIThree, influenceAIThree, foodAIThree, isWeapFac(curHex), isMediaComp(curHex), isFarmland(curHex));
			    		drawingPanel.paintImmediately(getXMin(hexagon17), getYMin(hexagon17), calcWidth(hexagon17), calcHeight(hexagon17));
			    		takeSuccess();
			    		
			    	}else if (tileTakeAttempt(probability) == false){
			    		failedTake();
			    	}

			    }else if (hexagon18.isInside(clickX, clickY)) {
			    	curHex = 18;
			    	double probability = tileProcess(curHex, pFood, pMilPower, pInfluence);
			    			
			    	if (tileTakeAttempt(probability) == true) {
			    		col = playerColor;
			    		pControls[curHex-1] = curHex;
			    		
			    		if (isWeapFac(curHex) == true) {
			    			pConMilPower[curHex-1] = curHex;
			    		} else if (isMediaComp(curHex) == true) {
			    			pConInfluence[curHex-1] = curHex;
			    		} else if (isFarmland(curHex) == true) {
			    			pConFood[curHex-1] = curHex;
			    		}
			    		
			    		tileLoss(aiOneControls, aiTwoControls, aiThreeControls, curHex, milPowerAIOne, influenceAIOne, foodAIOne, milPowerAITwo, influenceAITwo,
			    				foodAITwo, milPowerAIThree, influenceAIThree, foodAIThree, isWeapFac(curHex), isMediaComp(curHex), isFarmland(curHex));
			    		drawingPanel.paintImmediately(getXMin(hexagon18), getYMin(hexagon18), calcWidth(hexagon18), calcHeight(hexagon18));
			    		takeSuccess();
			    		
			    	}else if (tileTakeAttempt(probability) == false){
			    		failedTake();
			    	}

			    }else if (hexagon19.isInside(clickX, clickY)) {
			    	curHex = 19;
			    	double probability = tileProcess(curHex, pFood, pMilPower, pInfluence);
			    			
			    	if (tileTakeAttempt(probability) == true) {
			    		col = playerColor;
			    		pControls[curHex-1] = curHex;
			    		
			    		if (isWeapFac(curHex) == true) {
			    			pConMilPower[curHex-1] = curHex;
			    		} else if (isMediaComp(curHex) == true) {
			    			pConInfluence[curHex-1] = curHex;
			    		} else if (isFarmland(curHex) == true) {
			    			pConFood[curHex-1] = curHex;
			    		}
			    		
			    		tileLoss(aiOneControls, aiTwoControls, aiThreeControls, curHex, milPowerAIOne, influenceAIOne, foodAIOne, milPowerAITwo, influenceAITwo,
			    				foodAITwo, milPowerAIThree, influenceAIThree, foodAIThree, isWeapFac(curHex), isMediaComp(curHex), isFarmland(curHex));
			    		drawingPanel.paintImmediately(getXMin(hexagon19), getYMin(hexagon19), calcWidth(hexagon19), calcHeight(hexagon19));
			    		takeSuccess();
			    		
			    	}else if (tileTakeAttempt(probability) == false){
			    		failedTake();
			    	}

			    }else if (hexagon20.isInside(clickX, clickY)) {
			    	curHex = 20;
			    	double probability = tileProcess(curHex, pFood, pMilPower, pInfluence);
			    			
			    	if (tileTakeAttempt(probability) == true) {
			    		col = playerColor;
			    		pControls[curHex-1] = curHex;
			    		
			    		if (isWeapFac(curHex) == true) {
			    			pConMilPower[curHex-1] = curHex;
			    		} else if (isMediaComp(curHex) == true) {
			    			pConInfluence[curHex-1] = curHex;
			    		} else if (isFarmland(curHex) == true) {
			    			pConFood[curHex-1] = curHex;
			    		}
			    		
			    		tileLoss(aiOneControls, aiTwoControls, aiThreeControls, curHex, milPowerAIOne, influenceAIOne, foodAIOne, milPowerAITwo, influenceAITwo,
			    				foodAITwo, milPowerAIThree, influenceAIThree, foodAIThree, isWeapFac(curHex), isMediaComp(curHex), isFarmland(curHex));
			    		drawingPanel.paintImmediately(getXMin(hexagon20), getYMin(hexagon20), calcWidth(hexagon20), calcHeight(hexagon20));
			    		takeSuccess();
			    		
			    	}else if (tileTakeAttempt(probability) == false){
			    		failedTake();
			    	}

			    }else if (hexagon21.isInside(clickX, clickY)) {
			    	curHex = 21;
			    	double probability = tileProcess(curHex, pFood, pMilPower, pInfluence);
			    			
			    	if (tileTakeAttempt(probability) == true) {
			    		col = playerColor;
			    		pControls[curHex-1] = curHex;
			    		
			    		if (isWeapFac(curHex) == true) {
			    			pConMilPower[curHex-1] = curHex;
			    		} else if (isMediaComp(curHex) == true) {
			    			pConInfluence[curHex-1] = curHex;
			    		} else if (isFarmland(curHex) == true) {
			    			pConFood[curHex-1] = curHex;
			    		}
			    		
			    		tileLoss(aiOneControls, aiTwoControls, aiThreeControls, curHex, milPowerAIOne, influenceAIOne, foodAIOne, milPowerAITwo, influenceAITwo,
			    				foodAITwo, milPowerAIThree, influenceAIThree, foodAIThree, isWeapFac(curHex), isMediaComp(curHex), isFarmland(curHex));
			    		drawingPanel.paintImmediately(getXMin(hexagon21), getYMin(hexagon21), calcWidth(hexagon21), calcHeight(hexagon21));
			    		takeSuccess();
			    		
			    	}else if (tileTakeAttempt(probability) == false){
			    		failedTake();
			    	}

			    }else if (hexagon22.isInside(clickX, clickY)) {
			    	curHex = 22;
			    	double probability = tileProcess(curHex, pFood, pMilPower, pInfluence);
			    			
			    	if (tileTakeAttempt(probability) == true) {
			    		col = playerColor;
			    		pControls[curHex-1] = curHex;
			    		
			    		if (isWeapFac(curHex) == true) {
			    			pConMilPower[curHex-1] = curHex;
			    		} else if (isMediaComp(curHex) == true) {
			    			pConInfluence[curHex-1] = curHex;
			    		} else if (isFarmland(curHex) == true) {
			    			pConFood[curHex-1] = curHex;
			    		}
			    		
			    		tileLoss(aiOneControls, aiTwoControls, aiThreeControls, curHex, milPowerAIOne, influenceAIOne, foodAIOne, milPowerAITwo, influenceAITwo,
			    				foodAITwo, milPowerAIThree, influenceAIThree, foodAIThree, isWeapFac(curHex), isMediaComp(curHex), isFarmland(curHex));
			    		drawingPanel.paintImmediately(getXMin(hexagon22), getYMin(hexagon22), calcWidth(hexagon22), calcHeight(hexagon22));
			    		takeSuccess();
			    		
			    	}else if (tileTakeAttempt(probability) == false){
			    		failedTake();
			    	}

			    }else if (hexagon23.isInside(clickX, clickY)) {
			    	curHex = 23;
			    	double probability = tileProcess(curHex, pFood, pMilPower, pInfluence);
			    			
			    	if (tileTakeAttempt(probability) == true) {
			    		col = playerColor;
			    		pControls[curHex-1] = curHex;
			    		
			    		if (isWeapFac(curHex) == true) {
			    			pConMilPower[curHex-1] = curHex;
			    		} else if (isMediaComp(curHex) == true) {
			    			pConInfluence[curHex-1] = curHex;
			    		} else if (isFarmland(curHex) == true) {
			    			pConFood[curHex-1] = curHex;
			    		}
			    		
			    		tileLoss(aiOneControls, aiTwoControls, aiThreeControls, curHex, milPowerAIOne, influenceAIOne, foodAIOne, milPowerAITwo, influenceAITwo,
			    				foodAITwo, milPowerAIThree, influenceAIThree, foodAIThree, isWeapFac(curHex), isMediaComp(curHex), isFarmland(curHex));
			    		drawingPanel.paintImmediately(getXMin(hexagon23), getYMin(hexagon23), calcWidth(hexagon23), calcHeight(hexagon23));
			    		takeSuccess();
			    		
			    	}else if (tileTakeAttempt(probability) == false){
			    		failedTake();
			    	}

			    }else if (hexagon24.isInside(clickX, clickY)) {
			    	curHex = 24;
			    	double probability = tileProcess(curHex, pFood, pMilPower, pInfluence);
			    			
			    	if (tileTakeAttempt(probability) == true) {
			    		col = playerColor;
			    		pControls[curHex-1] = curHex;
			    		
			    		if (isWeapFac(curHex) == true) {
			    			pConMilPower[curHex-1] = curHex;
			    		} else if (isMediaComp(curHex) == true) {
			    			pConInfluence[curHex-1] = curHex;
			    		} else if (isFarmland(curHex) == true) {
			    			pConFood[curHex-1] = curHex;
			    		}
			    		
			    		tileLoss(aiOneControls, aiTwoControls, aiThreeControls, curHex, milPowerAIOne, influenceAIOne, foodAIOne, milPowerAITwo, influenceAITwo,
			    				foodAITwo, milPowerAIThree, influenceAIThree, foodAIThree, isWeapFac(curHex), isMediaComp(curHex), isFarmland(curHex));
			    		drawingPanel.paintImmediately(getXMin(hexagon24), getYMin(hexagon24), calcWidth(hexagon24), calcHeight(hexagon24));
			    		takeSuccess();
			    		
			    	}else if (tileTakeAttempt(probability) == false){
			    		failedTake();
			    	}

			    }else if (hexagon25.isInside(clickX, clickY)) {
			    	curHex = 25;
			    	double probability = tileProcess(curHex, pFood, pMilPower, pInfluence);
			    			
			    	if (tileTakeAttempt(probability) == true) {
			    		col = playerColor;
			    		pControls[curHex-1] = curHex;
			    		
			    		if (isWeapFac(curHex) == true) {
			    			pConMilPower[curHex-1] = curHex;
			    		} else if (isMediaComp(curHex) == true) {
			    			pConInfluence[curHex-1] = curHex;
			    		} else if (isFarmland(curHex) == true) {
			    			pConFood[curHex-1] = curHex;
			    		}
			    		
			    		tileLoss(aiOneControls, aiTwoControls, aiThreeControls, curHex, milPowerAIOne, influenceAIOne, foodAIOne, milPowerAITwo, influenceAITwo,
			    				foodAITwo, milPowerAIThree, influenceAIThree, foodAIThree, isWeapFac(curHex), isMediaComp(curHex), isFarmland(curHex));
			    		drawingPanel.paintImmediately(getXMin(hexagon25), getYMin(hexagon25), calcWidth(hexagon25), calcHeight(hexagon25));
			    		takeSuccess();
			    		
			    	}else if (tileTakeAttempt(probability) == false){
			    		failedTake();
			    	}

			    }else if (hexagon26.isInside(clickX, clickY)) {
			    	curHex = 26;
			    	double probability = tileProcess(curHex, pFood, pMilPower, pInfluence);
			    			
			    	if (tileTakeAttempt(probability) == true) {
			    		col = playerColor;
			    		pControls[curHex-1] = curHex;
			    		
			    		if (isWeapFac(curHex) == true) {
			    			pConMilPower[curHex-1] = curHex;
			    		} else if (isMediaComp(curHex) == true) {
			    			pConInfluence[curHex-1] = curHex;
			    		} else if (isFarmland(curHex) == true) {
			    			pConFood[curHex-1] = curHex;
			    		}
			    		
			    		tileLoss(aiOneControls, aiTwoControls, aiThreeControls, curHex, milPowerAIOne, influenceAIOne, foodAIOne, milPowerAITwo, influenceAITwo,
			    				foodAITwo, milPowerAIThree, influenceAIThree, foodAIThree, isWeapFac(curHex), isMediaComp(curHex), isFarmland(curHex));
			    		drawingPanel.paintImmediately(getXMin(hexagon26), getYMin(hexagon26), calcWidth(hexagon26), calcHeight(hexagon26));
			    		takeSuccess();
			    		
			    	}else if (tileTakeAttempt(probability) == false){
			    		failedTake();
			    	}
	
			    }else if (hexagon27.isInside(clickX, clickY)) {
			    	curHex = 27;
			    	double probability = tileProcess(curHex, pFood, pMilPower, pInfluence);
			    			
			    	if (tileTakeAttempt(probability) == true) {
			    		col = playerColor;
			    		pControls[curHex-1] = curHex;
			    		
			    		if (isWeapFac(curHex) == true) {
			    			pConMilPower[curHex-1] = curHex;
			    		} else if (isMediaComp(curHex) == true) {
			    			pConInfluence[curHex-1] = curHex;
			    		} else if (isFarmland(curHex) == true) {
			    			pConFood[curHex-1] = curHex;
			    		}
			    		
			    		tileLoss(aiOneControls, aiTwoControls, aiThreeControls, curHex, milPowerAIOne, influenceAIOne, foodAIOne, milPowerAITwo, influenceAITwo,
			    				foodAITwo, milPowerAIThree, influenceAIThree, foodAIThree, isWeapFac(curHex), isMediaComp(curHex), isFarmland(curHex));
			    		drawingPanel.paintImmediately(getXMin(hexagon27), getYMin(hexagon27), calcWidth(hexagon27), calcHeight(hexagon27));
			    		takeSuccess();
			    		
			    	}else if (tileTakeAttempt(probability) == false){
			    		failedTake();
			    	}

			    }else if (hexagon28.isInside(clickX, clickY)) {
			    	curHex = 28;
			    	double probability = tileProcess(curHex, pFood, pMilPower, pInfluence);
			    			
			    	if (tileTakeAttempt(probability) == true) {
			    		col = playerColor;
			    		pControls[curHex-1] = curHex;
			    		
			    		if (isWeapFac(curHex) == true) {
			    			pConMilPower[curHex-1] = curHex;
			    		} else if (isMediaComp(curHex) == true) {
			    			pConInfluence[curHex-1] = curHex;
			    		} else if (isFarmland(curHex) == true) {
			    			pConFood[curHex-1] = curHex;
			    		}
			    		
			    		tileLoss(aiOneControls, aiTwoControls, aiThreeControls, curHex, milPowerAIOne, influenceAIOne, foodAIOne, milPowerAITwo, influenceAITwo,
			    				foodAITwo, milPowerAIThree, influenceAIThree, foodAIThree, isWeapFac(curHex), isMediaComp(curHex), isFarmland(curHex));
			    		drawingPanel.paintImmediately(getXMin(hexagon28), getYMin(hexagon28), calcWidth(hexagon28), calcHeight(hexagon28));
			    		takeSuccess();
			    		
			    	}else if (tileTakeAttempt(probability) == false){
			    		failedTake();
			    	}

			    }else if (hexagon29.isInside(clickX, clickY)) {
			    	curHex = 29;
			    	double probability = tileProcess(curHex, pFood, pMilPower, pInfluence);
			    			
			    	if (tileTakeAttempt(probability) == true) {
			    		col = playerColor;
			    		pControls[curHex-1] = curHex;
			    		
			    		if (isWeapFac(curHex) == true) {
			    			pConMilPower[curHex-1] = curHex;
			    		} else if (isMediaComp(curHex) == true) {
			    			pConInfluence[curHex-1] = curHex;
			    		} else if (isFarmland(curHex) == true) {
			    			pConFood[curHex-1] = curHex;
			    		}
			    		
			    		tileLoss(aiOneControls, aiTwoControls, aiThreeControls, curHex, milPowerAIOne, influenceAIOne, foodAIOne, milPowerAITwo, influenceAITwo,
			    				foodAITwo, milPowerAIThree, influenceAIThree, foodAIThree, isWeapFac(curHex), isMediaComp(curHex), isFarmland(curHex));
			    		drawingPanel.paintImmediately(getXMin(hexagon29), getYMin(hexagon29), calcWidth(hexagon29), calcHeight(hexagon29));
			    		takeSuccess();
			    		
			    	}else if (tileTakeAttempt(probability) == false){
			    		failedTake();
			    	}

			    }else if (hexagon30.isInside(clickX, clickY)) {
			    	curHex = 30;
			    	double probability = tileProcess(curHex, pFood, pMilPower, pInfluence);
			    			
			    	if (tileTakeAttempt(probability) == true) {
			    		col = playerColor;
			    		pControls[curHex-1] = curHex;
			    		
			    		if (isWeapFac(curHex) == true) {
			    			pConMilPower[curHex-1] = curHex;
			    		} else if (isMediaComp(curHex) == true) {
			    			pConInfluence[curHex-1] = curHex;
			    		} else if (isFarmland(curHex) == true) {
			    			pConFood[curHex-1] = curHex;
			    		}
			    		
			    		tileLoss(aiOneControls, aiTwoControls, aiThreeControls, curHex, milPowerAIOne, influenceAIOne, foodAIOne, milPowerAITwo, influenceAITwo,
			    				foodAITwo, milPowerAIThree, influenceAIThree, foodAIThree, isWeapFac(curHex), isMediaComp(curHex), isFarmland(curHex));
			    		drawingPanel.paintImmediately(getXMin(hexagon30), getYMin(hexagon30), calcWidth(hexagon30), calcHeight(hexagon30));
			    		takeSuccess();
			    		
			    	}else if (tileTakeAttempt(probability) == false){
			    		failedTake();
			    	}

			    }else if (hexagon31.isInside(clickX, clickY)) {
			    	curHex = 31;
			    	double probability = tileProcess(curHex, pFood, pMilPower, pInfluence);
			    			
			    	if (tileTakeAttempt(probability) == true) {
			    		col = playerColor;
			    		pControls[curHex-1] = curHex;
			    		
			    		if (isWeapFac(curHex) == true) {
			    			pConMilPower[curHex-1] = curHex;
			    		} else if (isMediaComp(curHex) == true) {
			    			pConInfluence[curHex-1] = curHex;
			    		} else if (isFarmland(curHex) == true) {
			    			pConFood[curHex-1] = curHex;
			    		}
			    		
			    		tileLoss(aiOneControls, aiTwoControls, aiThreeControls, curHex, milPowerAIOne, influenceAIOne, foodAIOne, milPowerAITwo, influenceAITwo,
			    				foodAITwo, milPowerAIThree, influenceAIThree, foodAIThree, isWeapFac(curHex), isMediaComp(curHex), isFarmland(curHex));
			    		drawingPanel.paintImmediately(getXMin(hexagon31), getYMin(hexagon31), calcWidth(hexagon31), calcHeight(hexagon31));
			    		takeSuccess();
			    		
			    	}else if (tileTakeAttempt(probability) == false){
			    		failedTake();
			    	}

			    }else if (hexagon32.isInside(clickX, clickY)) {
			    	curHex = 32;
			    	double probability = tileProcess(curHex, pFood, pMilPower, pInfluence);
			    			
			    	if (tileTakeAttempt(probability) == true) {
			    		col = playerColor;
			    		pControls[curHex-1] = curHex;
			    		
			    		if (isWeapFac(curHex) == true) {
			    			pConMilPower[curHex-1] = curHex;
			    		} else if (isMediaComp(curHex) == true) {
			    			pConInfluence[curHex-1] = curHex;
			    		} else if (isFarmland(curHex) == true) {
			    			pConFood[curHex-1] = curHex;
			    		}
			    		
			    		tileLoss(aiOneControls, aiTwoControls, aiThreeControls, curHex, milPowerAIOne, influenceAIOne, foodAIOne, milPowerAITwo, influenceAITwo,
			    				foodAITwo, milPowerAIThree, influenceAIThree, foodAIThree, isWeapFac(curHex), isMediaComp(curHex), isFarmland(curHex));
			    		drawingPanel.paintImmediately(getXMin(hexagon32), getYMin(hexagon32), calcWidth(hexagon32), calcHeight(hexagon32));
			    		takeSuccess();
			    		
			    	}else if (tileTakeAttempt(probability) == false){
			    		failedTake();
			    	}

			    }else if (hexagon33.isInside(clickX, clickY)) {
			    	curHex = 33;
			    	double probability = tileProcess(curHex, pFood, pMilPower, pInfluence);
			    			
			    	if (tileTakeAttempt(probability) == true) {
			    		col = playerColor;
			    		pControls[curHex-1] = curHex;
			    		
			    		if (isWeapFac(curHex) == true) {
			    			pConMilPower[curHex-1] = curHex;
			    		} else if (isMediaComp(curHex) == true) {
			    			pConInfluence[curHex-1] = curHex;
			    		} else if (isFarmland(curHex) == true) {
			    			pConFood[curHex-1] = curHex;
			    		}
			    		
			    		tileLoss(aiOneControls, aiTwoControls, aiThreeControls, curHex, milPowerAIOne, influenceAIOne, foodAIOne, milPowerAITwo, influenceAITwo,
			    				foodAITwo, milPowerAIThree, influenceAIThree, foodAIThree, isWeapFac(curHex), isMediaComp(curHex), isFarmland(curHex));
			    		drawingPanel.paintImmediately(getXMin(hexagon33), getYMin(hexagon33), calcWidth(hexagon33), calcHeight(hexagon33));
			    		takeSuccess();
			    		
			    	}else if (tileTakeAttempt(probability) == false){
			    		failedTake();
			    	}
	
			    }else if (hexagon34.isInside(clickX, clickY)) {
			    	curHex = 34;
			    	double probability = tileProcess(curHex, pFood, pMilPower, pInfluence);
			    			
			    	if (tileTakeAttempt(probability) == true) {
			    		col = playerColor;
			    		pControls[curHex-1] = curHex;
			    		
			    		if (isWeapFac(curHex) == true) {
			    			pConMilPower[curHex-1] = curHex;
			    		} else if (isMediaComp(curHex) == true) {
			    			pConInfluence[curHex-1] = curHex;
			    		} else if (isFarmland(curHex) == true) {
			    			pConFood[curHex-1] = curHex;
			    		}
			    		
			    		tileLoss(aiOneControls, aiTwoControls, aiThreeControls, curHex, milPowerAIOne, influenceAIOne, foodAIOne, milPowerAITwo, influenceAITwo,
			    				foodAITwo, milPowerAIThree, influenceAIThree, foodAIThree, isWeapFac(curHex), isMediaComp(curHex), isFarmland(curHex));
			    		drawingPanel.paintImmediately(getXMin(hexagon34), getYMin(hexagon34), calcWidth(hexagon34), calcHeight(hexagon34));
			    		takeSuccess();
			    		
			    	}else if (tileTakeAttempt(probability) == false){
			    		failedTake();
			    	}

			    }else if (hexagon35.isInside(clickX, clickY)) {
			    	curHex = 35;
			    	double probability = tileProcess(curHex, pFood, pMilPower, pInfluence);
			    			
			    	if (tileTakeAttempt(probability) == true) {
			    		col = playerColor;
			    		pControls[curHex-1] = curHex;
			    		
			    		if (isWeapFac(curHex) == true) {
			    			pConMilPower[curHex-1] = curHex;
			    		} else if (isMediaComp(curHex) == true) {
			    			pConInfluence[curHex-1] = curHex;
			    		} else if (isFarmland(curHex) == true) {
			    			pConFood[curHex-1] = curHex;
			    		}
			    		
			    		tileLoss(aiOneControls, aiTwoControls, aiThreeControls, curHex, milPowerAIOne, influenceAIOne, foodAIOne, milPowerAITwo, influenceAITwo,
			    				foodAITwo, milPowerAIThree, influenceAIThree, foodAIThree, isWeapFac(curHex), isMediaComp(curHex), isFarmland(curHex));
			    		drawingPanel.paintImmediately(getXMin(hexagon35), getYMin(hexagon35), calcWidth(hexagon35), calcHeight(hexagon35));
			    		takeSuccess();
			    		
			    	}else if (tileTakeAttempt(probability) == false){
			    		failedTake();
			    	}

			    }else if (hexagon36.isInside(clickX, clickY)) {
			    	curHex = 36;
			    	double probability = tileProcess(curHex, pFood, pMilPower, pInfluence);
			    			
			    	if (tileTakeAttempt(probability) == true) {
			    		col = playerColor;
			    		pControls[curHex-1] = curHex;
			    		
			    		if (isWeapFac(curHex) == true) {
			    			pConMilPower[curHex-1] = curHex;
			    		} else if (isMediaComp(curHex) == true) {
			    			pConInfluence[curHex-1] = curHex;
			    		} else if (isFarmland(curHex) == true) {
			    			pConFood[curHex-1] = curHex;
			    		}
			    		
			    		tileLoss(aiOneControls, aiTwoControls, aiThreeControls, curHex, milPowerAIOne, influenceAIOne, foodAIOne, milPowerAITwo, influenceAITwo,
			    				foodAITwo, milPowerAIThree, influenceAIThree, foodAIThree, isWeapFac(curHex), isMediaComp(curHex), isFarmland(curHex));
			    		drawingPanel.paintImmediately(getXMin(hexagon36), getYMin(hexagon36), calcWidth(hexagon36), calcHeight(hexagon36));
			    		takeSuccess();
			    		
			    	}else if (tileTakeAttempt(probability) == false){
			    		failedTake();
			    	}

			    }else if (hexagon37.isInside(clickX, clickY)) {
			    	curHex = 37;
			    	double probability = tileProcess(curHex, pFood, pMilPower, pInfluence);
			    			
			    	if (tileTakeAttempt(probability) == true) {
			    		col = playerColor;
			    		pControls[curHex-1] = curHex;
			    		
			    		if (isWeapFac(curHex) == true) {
			    			pConMilPower[curHex-1] = curHex;
			    		} else if (isMediaComp(curHex) == true) {
			    			pConInfluence[curHex-1] = curHex;
			    		} else if (isFarmland(curHex) == true) {
			    			pConFood[curHex-1] = curHex;
			    		}
			    		
			    		tileLoss(aiOneControls, aiTwoControls, aiThreeControls, curHex, milPowerAIOne, influenceAIOne, foodAIOne, milPowerAITwo, influenceAITwo,
			    				foodAITwo, milPowerAIThree, influenceAIThree, foodAIThree, isWeapFac(curHex), isMediaComp(curHex), isFarmland(curHex));
			    		drawingPanel.paintImmediately(getXMin(hexagon37), getYMin(hexagon37), calcWidth(hexagon37), calcHeight(hexagon37));
			    		takeSuccess();
			    		
			    	}else if (tileTakeAttempt(probability) == false){
			    		failedTake();
			    	}
			   
			    }else {
			    	System.out.println("NO HEX DETECTED");
			    }
			    
			    //Update modifiers and other info. at bottom of screen
			    
			    aiTurns(colorAIOne, milPowerAIOne, foodAIOne, influenceAIOne, aiOneControls, pControls, aiTwoControls, aiThreeControls,
			    		milPowerAITwo, influenceAITwo, foodAITwo, milPowerAIThree, influenceAIThree, foodAIThree,  
			    		hexagon1, hexagon2, hexagon3, hexagon4, hexagon5, hexagon6, hexagon7,
						hexagon8, hexagon9, hexagon10, hexagon11, hexagon12, hexagon13, hexagon14,
						hexagon15, hexagon16, hexagon17, hexagon18, hexagon19, hexagon20, hexagon21,
						hexagon22, hexagon23, hexagon24, hexagon25, hexagon26, hexagon27,
					    hexagon28, hexagon29, hexagon30, hexagon31, hexagon32,
						hexagon33, hexagon34, hexagon35, hexagon36, hexagon37);
			    
			    aiTurns(colorAITwo, milPowerAITwo, foodAITwo, influenceAITwo, aiTwoControls, pControls, aiOneControls, aiThreeControls,
			    		milPowerAIOne, influenceAIOne, foodAIOne, milPowerAIThree, influenceAIThree, foodAIThree,  
			    		hexagon1, hexagon2, hexagon3, hexagon4, hexagon5, hexagon6, hexagon7,
						hexagon8, hexagon9, hexagon10, hexagon11, hexagon12, hexagon13, hexagon14,
						hexagon15, hexagon16, hexagon17, hexagon18, hexagon19, hexagon20, hexagon21,
						hexagon22, hexagon23, hexagon24, hexagon25, hexagon26, hexagon27,
					    hexagon28, hexagon29, hexagon30, hexagon31, hexagon32,
						hexagon33, hexagon34, hexagon35, hexagon36, hexagon37);
			    
			    aiTurns(colorAIThree, milPowerAIThree, foodAIThree, influenceAIThree, aiThreeControls, pControls, aiOneControls, aiTwoControls, 
			    		milPowerAIOne, influenceAIOne, foodAIOne, milPowerAITwo, influenceAITwo, foodAITwo,  
			    		hexagon1, hexagon2, hexagon3, hexagon4, hexagon5, hexagon6, hexagon7,
						hexagon8, hexagon9, hexagon10, hexagon11, hexagon12, hexagon13, hexagon14,
						hexagon15, hexagon16, hexagon17, hexagon18, hexagon19, hexagon20, hexagon21,
						hexagon22, hexagon23, hexagon24, hexagon25, hexagon26, hexagon27,
					    hexagon28, hexagon29, hexagon30, hexagon31, hexagon32,
						hexagon33, hexagon34, hexagon35, hexagon36, hexagon37);
			    
			    bonusUpdate();
			    round++;
			    drawingPanel.paintImmediately(0, 800, 1400, 100);
			    
			    //Win condition based on territory
			    if (getArrayLength(pControls) == 37) {
			    	ts.winScreen();
			    //Lose condition based on territory	
			    } else if (getArrayLength(aiOneControls) == 37 || getArrayLength(aiTwoControls) == 37 || getArrayLength(aiThreeControls) == 37) {
			    	ts.loseScreen();
			    }
			    
            }   
            
         });
        
    }

    public class DrawingPanel extends JPanel {
        //private static final long serialVersionUID = 5701311351092275287L;
		private static final long serialVersionUID = 1L;
		
		private String[] hexes = new String[] {"hexagon1","hexagon2", "hexagon3", "hexagon4", "hexagon5", "hexagon6", "hexagon7", 
												"hexagon8", "hexagon9", "hexagon10", "hexagon11", "hexagon12", "hexagon13", "hexagon14", 
												"hexagon15", "hexagon16", "hexagon17", "hexagon18", "hexagon19", "hexagon20", "hexagon21", 
												"hexagon22", "hexagon23", "hexagon24", "hexagon25", "hexagon26", "hexagon27",
												"hexagon28", "hexagon29", "hexagon30", "hexagon31", "hexagon32", 
												"hexagon33", "hexagon34", "hexagon35", "hexagon36", "hexagon37"};

		//Tier 1
        private Hexagon hexagon1;
        private Hexagon hexagon2;
        private Hexagon hexagon3;
        private Hexagon hexagon4;
        
        //Tier 2
        private Hexagon hexagon5;
        private Hexagon hexagon6;
        private Hexagon hexagon7;
        private Hexagon hexagon8;
        private Hexagon hexagon9;
        
        //Tier 3
        private Hexagon hexagon10;
        private Hexagon hexagon11;
        private Hexagon hexagon12;
        private Hexagon hexagon13;
        private Hexagon hexagon14;
        private Hexagon hexagon15;
        
        //Tier 4
        private Hexagon hexagon16;
        private Hexagon hexagon17;
        private Hexagon hexagon18;
        private Hexagon hexagon19;
        private Hexagon hexagon20;
        private Hexagon hexagon21;
        private Hexagon hexagon22;
        
        //Tier 5
        private Hexagon hexagon23;
        private Hexagon hexagon24;
        private Hexagon hexagon25;
        private Hexagon hexagon26;
        private Hexagon hexagon27;
        private Hexagon hexagon28;
        
        //Tier 6
        private Hexagon hexagon29;
        private Hexagon hexagon30;
        private Hexagon hexagon31;
        private Hexagon hexagon32;
        private Hexagon hexagon33;
        
        //Tier 7
        private Hexagon hexagon34;
        private Hexagon hexagon35;
        private Hexagon hexagon36;
        private Hexagon hexagon37;
        

        public DrawingPanel(Hexagon hexagon1, Hexagon hexagon2, Hexagon hexagon3, Hexagon hexagon4, Hexagon hexagon5,
        					Hexagon hexagon6, Hexagon hexagon7, Hexagon hexagon8, Hexagon hexagon9, Hexagon hexagon10,
        					Hexagon hexagon11, Hexagon hexagon12, Hexagon hexagon13, Hexagon hexagon14, Hexagon hexagon15,
        					Hexagon hexagon16, Hexagon hexagon17, Hexagon hexagon18, Hexagon hexagon19, Hexagon
        					hexagon20, Hexagon hexagon21, Hexagon hexagon22, Hexagon hexagon23, Hexagon hexagon24,
        					Hexagon hexagon25, Hexagon hexagon26, Hexagon hexagon27, Hexagon hexagon28, Hexagon
        					hexagon29, Hexagon hexagon30, Hexagon hexagon31, Hexagon hexagon32, Hexagon hexagon33,
        					Hexagon hexagon34, Hexagon hexagon35, Hexagon hexagon36, Hexagon hexagon37) {
        	
            this.hexagon1 = hexagon1;
            this.hexagon2 = hexagon2;
            this.hexagon3 = hexagon3;
            this.hexagon4 = hexagon4;
            this.hexagon5 = hexagon5;
            this.hexagon6 = hexagon6;
            this.hexagon7 = hexagon7;
            this.hexagon8 = hexagon8;
            this.hexagon9 = hexagon9;
            this.hexagon10 = hexagon10;
            this.hexagon11 = hexagon11;
            this.hexagon12 = hexagon12;
            this.hexagon13 = hexagon13;
            this.hexagon14 = hexagon14;
            this.hexagon15 = hexagon15;
            this.hexagon16 = hexagon16;
            this.hexagon17 = hexagon17;
            this.hexagon18 = hexagon18;
            this.hexagon19 = hexagon19;
            this.hexagon20 = hexagon20;
            this.hexagon21 = hexagon21;
            this.hexagon22 = hexagon22;
            this.hexagon23 = hexagon23;
            this.hexagon24 = hexagon24;
            this.hexagon25 = hexagon25;
            this.hexagon26 = hexagon26;
            this.hexagon27 = hexagon27;
            this.hexagon28 = hexagon28;
            this.hexagon29 = hexagon29;
            this.hexagon30 = hexagon30;
            this.hexagon31 = hexagon31;
            this.hexagon32 = hexagon32;
            this.hexagon33 = hexagon33;
            this.hexagon34 = hexagon34;
            this.hexagon35 = hexagon35;
            this.hexagon36 = hexagon36;
            this.hexagon37 = hexagon37;
                        
            this.setPreferredSize(new Dimension(1400, 900));
        }
        
        
        
        @Override
        protected void paintComponent(Graphics g) {

            super.paintComponent(g);    
            
            Graphics2D g2 = (Graphics2D) g;
            
            g2.setStroke(new BasicStroke(4));
            
            g.setColor(Color.BLACK);
            g.setFont(new Font("Helvetica nueu", Font.PLAIN, 18));
            
            g.drawPolygon(hexagon1.getHexagon());
            if (curHex == 1) {
            	g.setColor(col);
                g.fillPolygon(hexagon1.getHexagon());
            }
            g.setColor(Color.BLACK);
            
            g.drawPolygon(hexagon2.getHexagon());
            if (curHex == 2) {
            	g.setColor(col);
            	g.fillPolygon(hexagon2.getHexagon());
            }
            g.setColor(Color.BLACK);
            
            g.drawPolygon(hexagon3.getHexagon());
            if (curHex == 3) {
            	g.setColor(col);
            	g.fillPolygon(hexagon3.getHexagon());
            }
            g.setColor(Color.BLACK);
            
            g.drawPolygon(hexagon4.getHexagon());
            if (curHex == 4) {
            	g.setColor(col);
            	g.fillPolygon(hexagon4.getHexagon());
            }
            g.setColor(Color.BLACK);
            
            g.drawPolygon(hexagon5.getHexagon());
            if (curHex == 5) {
            	g.setColor(col);
            	g.fillPolygon(hexagon5.getHexagon());
            }
            g.setColor(Color.BLACK);
            
            g.drawPolygon(hexagon6.getHexagon());
            if (curHex == 6) {
            	g.setColor(col);
            	g.fillPolygon(hexagon6.getHexagon());
            }
            g.setColor(Color.BLACK);
            
            g.drawPolygon(hexagon7.getHexagon());
            if (curHex == 7) {
            	g.setColor(col);
            	g.fillPolygon(hexagon7.getHexagon());
            }
            g.setColor(Color.BLACK);
            
            g.drawPolygon(hexagon8.getHexagon());
            if (curHex == 8) {
            	g.setColor(col);
            	g.fillPolygon(hexagon8.getHexagon());
            }
            g.setColor(Color.BLACK);
            
            g.drawPolygon(hexagon9.getHexagon());
            if (curHex == 9) {
            	g.setColor(col);
            	g.fillPolygon(hexagon9.getHexagon());
            }
            g.setColor(Color.BLACK);
            
            g.drawPolygon(hexagon10.getHexagon());
            if (curHex == 10) {
            	g.setColor(col);
            	g.fillPolygon(hexagon10.getHexagon());
            }
            g.setColor(Color.BLACK);
            
            g.drawPolygon(hexagon11.getHexagon());
            if (curHex == 11) {
            	g.setColor(col);
            	g.fillPolygon(hexagon11.getHexagon());
            }
            g.setColor(Color.BLACK);
            
            g.drawPolygon(hexagon12.getHexagon());
            if (curHex == 12) {
            	g.setColor(col);
            	g.fillPolygon(hexagon12.getHexagon());
            }
            g.setColor(Color.BLACK);
            
            g.drawPolygon(hexagon13.getHexagon());
            if (curHex == 13) {
            	g.setColor(col);
            	g.fillPolygon(hexagon13.getHexagon());
            }
            g.setColor(Color.BLACK);
            
            g.drawPolygon(hexagon14.getHexagon());
            if (curHex == 14) {
            	g.setColor(col);
            	g.fillPolygon(hexagon14.getHexagon());
            }
            g.setColor(Color.BLACK);
            
            g.drawPolygon(hexagon15.getHexagon());
            if (curHex == 15) {
            	g.setColor(col);
            	g.fillPolygon(hexagon15.getHexagon());
            }
            g.setColor(Color.BLACK);
            
            g.drawPolygon(hexagon16.getHexagon());
            if (curHex == 16) {
            	g.setColor(col);
            	g.fillPolygon(hexagon16.getHexagon());
            }
            g.setColor(Color.BLACK);
            
            g.drawPolygon(hexagon17.getHexagon());
            if (curHex == 17) {
            	g.setColor(col);
            	g.fillPolygon(hexagon17.getHexagon());
            }
            g.setColor(Color.BLACK);
            
            g.drawPolygon(hexagon18.getHexagon());
            if (curHex == 18) {
            	g.setColor(col);
            	g.fillPolygon(hexagon18.getHexagon());
            }
            g.setColor(Color.BLACK);
            
            g.drawPolygon(hexagon19.getHexagon());
            if (curHex == 19) {
            	g.setColor(col);
            	g.fillPolygon(hexagon19.getHexagon());
            }
            g.setColor(Color.BLACK);
            
            g.drawPolygon(hexagon20.getHexagon());
            if (curHex == 20) {
            	g.setColor(col);
            	g.fillPolygon(hexagon20.getHexagon());
            }
            g.setColor(Color.BLACK);
            
            g.drawPolygon(hexagon21.getHexagon());
            if (curHex == 21) {
            	g.setColor(col);
            	g.fillPolygon(hexagon21.getHexagon());
            }
            g.setColor(Color.BLACK);
            
            g.drawPolygon(hexagon22.getHexagon());
            if (curHex == 22) {
            	g.setColor(col);
            	g.fillPolygon(hexagon22.getHexagon());
            }
            g.setColor(Color.BLACK);
            
            g.drawPolygon(hexagon23.getHexagon());
            if (curHex == 23) {
            	g.setColor(col);
            	g.fillPolygon(hexagon23.getHexagon());
            }
            g.setColor(Color.BLACK);
            
            g.drawPolygon(hexagon24.getHexagon());
            if (curHex == 24) {
            	g.setColor(col);
            	g.fillPolygon(hexagon24.getHexagon());
            }
            g.setColor(Color.BLACK);
            
            g.drawPolygon(hexagon25.getHexagon());
            if (curHex == 25) {
            	g.setColor(col);
            	g.fillPolygon(hexagon25.getHexagon());
            }
            g.setColor(Color.BLACK);
            
            g.drawPolygon(hexagon26.getHexagon());
            if (curHex == 26) {
            	g.setColor(col);
            	g.fillPolygon(hexagon26.getHexagon());
            }
            g.setColor(Color.BLACK);
            
            g.drawPolygon(hexagon27.getHexagon());
            if (curHex == 27) {
            	g.setColor(col);
            	g.fillPolygon(hexagon27.getHexagon());
            }
            g.setColor(Color.BLACK);
            
            g.drawPolygon(hexagon28.getHexagon());
            if (curHex == 28) {
            	g.setColor(col);
            	g.fillPolygon(hexagon28.getHexagon());
            }
            g.setColor(Color.BLACK);
            
            g.drawPolygon(hexagon29.getHexagon());
            if (curHex == 29) {
            	g.setColor(col);
            	g.fillPolygon(hexagon29.getHexagon());
            }
            g.setColor(Color.BLACK);
            
            g.drawPolygon(hexagon30.getHexagon());
            if (curHex == 30) {
            	g.setColor(col);
            	g.fillPolygon(hexagon30.getHexagon());
            }
            g.setColor(Color.BLACK);
            
            g.drawPolygon(hexagon31.getHexagon());
            if (curHex == 31) {
            	g.setColor(col);
            	g.fillPolygon(hexagon31.getHexagon());
            }
            g.setColor(Color.BLACK);
            
            g.drawPolygon(hexagon32.getHexagon());
            if (curHex == 32) {
            	g.setColor(col);
            	g.fillPolygon(hexagon32.getHexagon());
            }
            g.setColor(Color.BLACK);
            
            g.drawPolygon(hexagon33.getHexagon());
            if (curHex == 33) {
            	g.setColor(col);
            	g.fillPolygon(hexagon33.getHexagon());
            }
            g.setColor(Color.BLACK);
            
            g.drawPolygon(hexagon34.getHexagon());
            if (curHex == 34) {
            	g.setColor(col);
            	g.fillPolygon(hexagon34.getHexagon());
            }
            g.setColor(Color.BLACK);
            
            g.drawPolygon(hexagon35.getHexagon());
            if (curHex == 35) {
            	g.setColor(col);
            	g.fillPolygon(hexagon35.getHexagon());
            }
            g.setColor(Color.BLACK);
            
            g.drawPolygon(hexagon36.getHexagon());
            if (curHex == 36) {
            	g.setColor(col);
            	g.fillPolygon(hexagon36.getHexagon());
            }
            g.setColor(Color.BLACK);
            
            g.drawPolygon(hexagon37.getHexagon());
            if (curHex == 37) {
            	g.setColor(col);
            	g.fillPolygon(hexagon37.getHexagon());
            }
            g.setColor(Color.BLACK);
            
            colCheck();
            
            g.drawString("Player "+ colString, 50, 840);
            g.drawString("Territory: " + getArrayLength(pControls), 350, 840);
            g.drawString("Food: " + getArrayLength(pConFood), 500, 840);
            g.drawString("Influence: " + getArrayLength(pConInfluence), 650, 840);
            g.drawString("Militia power: " + getArrayLength(pConMilPower), 800, 840);
            g.drawString("Round: " + round, 1200, 840);
            
            tileTypeSignify(g, hexagon1, 1);
            tileTypeSignify(g, hexagon2, 2);
            tileTypeSignify(g, hexagon3, 3);
            tileTypeSignify(g, hexagon4, 4);
            tileTypeSignify(g, hexagon5, 5);
            tileTypeSignify(g, hexagon6, 6);
            tileTypeSignify(g, hexagon7, 7);
            tileTypeSignify(g, hexagon8, 8);
            tileTypeSignify(g, hexagon9, 9);
            tileTypeSignify(g, hexagon10, 10);
            tileTypeSignify(g, hexagon11, 11);
            tileTypeSignify(g, hexagon12, 12);
            tileTypeSignify(g, hexagon13, 13);
            tileTypeSignify(g, hexagon14, 14);
            tileTypeSignify(g, hexagon15, 15);
            tileTypeSignify(g, hexagon16, 16);
            tileTypeSignify(g, hexagon17, 17);
            tileTypeSignify(g, hexagon18, 18);
            tileTypeSignify(g, hexagon19, 19);
            tileTypeSignify(g, hexagon20, 20);
            tileTypeSignify(g, hexagon21, 21);
            tileTypeSignify(g, hexagon22, 22);
            tileTypeSignify(g, hexagon23, 23);
            tileTypeSignify(g, hexagon24, 24);
            tileTypeSignify(g, hexagon25, 25);
            tileTypeSignify(g, hexagon26, 26);
            tileTypeSignify(g, hexagon27, 27);
            tileTypeSignify(g, hexagon28, 28);
            tileTypeSignify(g, hexagon29, 29);
            tileTypeSignify(g, hexagon30, 30);
            tileTypeSignify(g, hexagon31, 31);
            tileTypeSignify(g, hexagon32, 32);
            tileTypeSignify(g, hexagon33, 33);
            tileTypeSignify(g, hexagon34, 34);
            tileTypeSignify(g, hexagon35, 35);
            tileTypeSignify(g, hexagon36, 36);
            tileTypeSignify(g, hexagon37, 37);
            
        }
    }
    
    public class Hexagon {
    	
        private final int radius;

        private final Point center;

        private final Polygon hexagon1;
		private final Polygon hexagon2;
		private final Polygon hexagon3;
		private final Polygon hexagon4;
		
		private final Polygon hexagon5;
		private final Polygon hexagon6;
		private final Polygon hexagon7;
		private final Polygon hexagon8;
		private final Polygon hexagon9;
		
		private final Polygon hexagon10;
		private final Polygon hexagon11;
		private final Polygon hexagon12;
		private final Polygon hexagon13;
		private final Polygon hexagon14;
		private final Polygon hexagon15;
		
		private final Polygon hexagon16;
		private final Polygon hexagon17;
		private final Polygon hexagon18;
		private final Polygon hexagon19;
		private final Polygon hexagon20;
		private final Polygon hexagon21;
		private final Polygon hexagon22;
		
		private final Polygon hexagon23;
		private final Polygon hexagon24;
		private final Polygon hexagon25;
		private final Polygon hexagon26;
		private final Polygon hexagon27;
		private final Polygon hexagon28;
		
		private final Polygon hexagon29;
		private final Polygon hexagon30;
		private final Polygon hexagon31;
		private final Polygon hexagon32;
		private final Polygon hexagon33;
		
		private final Polygon hexagon34;
		private final Polygon hexagon35;
		private final Polygon hexagon36;
		private final Polygon hexagon37;
		
		public Hexagon(Point center, int radius) {
            this.center = center;
            this.radius = radius;
            this.hexagon1 = createHexagon();
            this.hexagon2 = createHexagon();
            this.hexagon3 = createHexagon();
            this.hexagon4  = createHexagon();
            this.hexagon5 = createHexagon();
            this.hexagon6 = createHexagon();
            this.hexagon7 = createHexagon();
            this.hexagon8 = createHexagon();
            this.hexagon9 = createHexagon();
            this.hexagon10 = createHexagon();
            this.hexagon11 = createHexagon();
            this.hexagon12 = createHexagon();
            this.hexagon13 = createHexagon();
            this.hexagon14 = createHexagon();
            this.hexagon15 = createHexagon();
            this.hexagon16 = createHexagon();
            this.hexagon17 = createHexagon();
            this.hexagon18 = createHexagon();
            this.hexagon19 = createHexagon();
            this.hexagon20 = createHexagon();
            this.hexagon21 = createHexagon();
            this.hexagon22 = createHexagon();
            this.hexagon23 = createHexagon();
            this.hexagon24 = createHexagon();
            this.hexagon25 = createHexagon();
            this.hexagon26 = createHexagon();
            this.hexagon27 = createHexagon();
            this.hexagon28 = createHexagon();
            this.hexagon29 = createHexagon();
            this.hexagon30 = createHexagon();
            this.hexagon31 = createHexagon();
            this.hexagon32 = createHexagon();
            this.hexagon33 = createHexagon();
            this.hexagon34 = createHexagon();
            this.hexagon35 = createHexagon();
            this.hexagon36 = createHexagon();
            this.hexagon37 = createHexagon();
            
        }

        private Polygon createHexagon() {
            Polygon polygon = new Polygon();

            for (int i = 0; i < 6; i++) {
                int xval = (int) (center.x + radius * Math.cos((i * 2 * Math.PI / 6) - (Math.PI/6)));
                int yval = (int) (center.y + radius * Math.sin((i * 2 * Math.PI / 6) - (Math.PI/6)));
                polygon.addPoint(xval, yval);
            }

            return polygon;
        }

        public int getRadius() {
            return radius;
        }

        public Point getCenter() {
            return center;
        }

        public Polygon getHexagon() {
            return hexagon1;
        }
        
        public int[] xPoly() {
        	return (hexagon1.xpoints);
        }
        
        public int[] yPoly() {
        	return (hexagon1.ypoints);
        }
        
        public boolean isInside(int x, int y) {
        	return hexagon1.contains(x, y);
        }
        
    }
    
    private String colCheck() {
    	if (playerColor == Color.BLUE) {
    		colString = colorStrings[0];
    	}else if (playerColor == Color.RED) {
    		colString = colorStrings[1];
    	}else if (playerColor == Color.YELLOW) {
    		colString = colorStrings[2];
    	}else if (playerColor == Color.GREEN) {
    		colString = colorStrings[3];
    	}
    	return colString;
    }
    
	public void tileTypeGen(int[] tileArrayOne, int[] tileArrayTwo, int[] tileArrayThree, int[] tileArrayFour, int[] tileArrayFive) {	
    	int[] shuffledArray = arrayShuffle();
    	int j = 0;
    	
    	for (int i = 0; i < 4; i++) {
    		tileArrayOne[i] = shuffledArray[j];
    		j++;
    	}	
    		
    	for (int i = 0; i < 4; i++) {
    		tileArrayTwo[i] = shuffledArray[j];
    		j++;
    	}
    	
    	for (int i = 0; i < 4; i++) {
    		tileArrayThree[i] = shuffledArray[j];
    		j++;
    	}
    	
    	for (int i = 0; i < 4; i++) {
    		tileArrayFour[i] = shuffledArray[j];
    		j++;
    	}
    	
    	for (int i = 0; i < 21; i++) {
    		tileArrayFive[i] = shuffledArray[j];
    		j++;
    	}
    }
    
    private int[] arrayShuffle() {
    	List<Integer> shuffled = new ArrayList<>();
    	for (int i = 1; i <= 37; i++) {
    	    shuffled.add(i);
    	}
    	Collections.shuffle(shuffled);
    	
    	int[] shuffledArray = new int[37];
    	for (int i = 0; i <= 36; i++) {
    		shuffledArray[i] = shuffled.get(i);
    	}
    	
    	return shuffledArray;
    	
    }
    
    boolean isEmpty(int hexagon) {
    	boolean emptyOut = false;
    	for (int i = 0; i < 21; i++) {
    		if (hexagon == emptyTiles[i]) {
    			emptyOut = true;
    		}
    	}
    	return emptyOut;
    }
    
    private boolean isCitzSet(int hexagon) {
    	boolean citOut = false;
    	for (int i = 0; i < 4; i++) {
    		if (hexagon == citTiles[i]) {
    			citOut = true;
    		}
    	}
    	return citOut;
    }
    
    private boolean isMediaComp(int hexagon) {
    	boolean mediaOut = false;
    	for (int i = 0; i < 4; i++) {
    		if (hexagon == medTiles[i]) {
    			mediaOut = true;
    		}
    	}
    	return mediaOut;
    }
    
    private boolean isWeapFac(int hexagon) {
    	boolean weapOut = false;
    	for (int i = 0; i < 4; i++) {
    		if (hexagon == weapTiles[i]) {
    			weapOut = true;
    		}
    	}
    	return weapOut;
    }
    
    private boolean isFarmland(int hexagon) {
    	boolean farmOut = false;
    	for (int i = 0; i < 4; i++) {
    		if (hexagon == farmTiles[i]) {
    			farmOut = true;
    		}
    	}
    	return farmOut;
    }
    
    private double isControlled(int hexagon) {
    	double controlledOut = 1;
    	for (int i = 0; i < 37; i++) {
    		if (hexagon == controlledTiles[i]) {
    			controlledOut = 2;
    		}
    	}
    	return controlledOut;
    }
    
    private double getDifficulty(int hexagon) {
    	double d = 0;
    	boolean empty = isEmpty(hexagon);
    	boolean citz = isCitzSet(hexagon);
    	boolean media = isMediaComp(hexagon);
    	boolean weap = isWeapFac(hexagon);
    	boolean farm = isFarmland(hexagon);
    	
    	if (empty == true) {
    		d = 2;
    	}else if (citz == true) {
    		d = 3;
    	}else if (media == true || weap == true || farm == true) {
    		d = 4;
    	/*}else if (weap == true) {
    		d = 4;
    	}else if (farm == true) {
    		d = 4;*/
    	}else {
    		System.out.println("ERROR getDifficulty()");
    	}
    	
    	return d;
    }
    
    private double preCaptureCalculate(double d, double c, double f, double p, double i) {
    	if (p >= 2 && i >= 2){
    		return altAlgThree(d, c, f, p, i);
    	}else if (i >= 2) {
    		return altAlgTwo(d, c, f, i);

    	}else if (p >= 2) {
    		return altAlgOne(d, c, f, p);

    	}else {
    		return baseAlg(d, c, f);

    	}
    }
    
    private double baseAlg(double d, double c, double f) {
    	double probability = 2*(1/(d*c))*(1/f);
    	return probability;
    }
    
    private double altAlgOne(double d, double c, double f, double p) {
    	double probability = 2*(1/(d*c))*(Math.log(p+1))*(1/f);
    	return probability;
    }
    
    private double altAlgTwo(double d, double c, double f, double i) {
    	double probability = 2*(1/(d*c))*(Math.log(i+1))*(1/f);
    	return probability;
    }
    
    private double altAlgThree(double d, double c, double f, double p, double i) {
    	double probability = 2*(1/(d*c))*(Math.log(p+1))*(Math.log(i+1))*(1/f);
    	return probability;
    }
    
    private boolean tileTakeAttempt(double probability) {
    	boolean captured = false;
    	double probRoll = rd.nextDouble();
    	
    	if (probRoll <= probability) {
    		captured = true;
    	}
    	
    	return captured;    
    }
    
    private double tileProcess(int hexagon, double f, double p, double i) {
    	double c = isControlled(hexagon);
    	double d = getDifficulty(hexagon);
    	double probability = preCaptureCalculate(d, c, f, p, i); 
    	
    	return probability;
    }

    private int getXMax(Hexagon hexagon) {
    	int[] xValues = hexagon.xPoly();
    	int xMax = 0;
    	
    	for (int i = 0; i < xValues.length; i++) {
    		if (xValues[i] > xMax) {
    			xMax = xValues[i];
    		}
    	}
    	return xMax;
    }
    
    		
    private int getXMin(Hexagon hexagon) {
    	int[] xValues = hexagon.xPoly();
    	int xMin = 3000;
    	
    	for (int i = 0; i < xValues.length; i++) {
    		if (xValues[i] < xMin && xValues[i] != 0) {
    			xMin = xValues[i];
    		}
    	}
    	return xMin;
    }	
    	
    private int getYMax(Hexagon hexagon) {
    	int[] yValues = hexagon.yPoly();
    	int yMax = 0;
    	
    	for (int i = 0; i < yValues.length; i++) {
    		if (yValues[i] > yMax) {
    			yMax = yValues[i];
    		}
    	}
    	return yMax;
    }	
    	
    private int getYMin(Hexagon hexagon) {
    	int[] yValues = hexagon.yPoly();
    	int yMin = 3000;
    	
    	for (int i = 0; i < yValues.length; i++) {
    		if (yValues[i] < yMin && yValues[i] != 0) {
    			yMin = yValues[i];
    		}
    	}
    	return yMin;
    }	
    	
    private int calcWidth(Hexagon hexagon) {
    	int xMax = getXMax(hexagon);
    	int xMin = getXMin(hexagon);
    	
    	int width = xMax - xMin;
    	return width;
    }
    
    private int calcHeight(Hexagon hexagon) {
    	int yMax = getYMax(hexagon);
    	int yMin = getYMin(hexagon);
    	
    	int height = yMax - yMin;
    	return height;
    }
    
    public void failedTake() {
		JOptionPane.showMessageDialog(drawingPanel, "Failed to take tile!"," ", JOptionPane.PLAIN_MESSAGE);
	}
    
    public void takeSuccess() {
    	JOptionPane.showMessageDialog(drawingPanel, "Tile taken!", " ", JOptionPane.PLAIN_MESSAGE);
    }
    
    public void assignAI() {
    	if (playerColor == Color.BLUE) {
    		colorAIOne = Color.RED;
    		colorAITwo = Color.YELLOW;
    		colorAIThree = Color.GREEN;
    	} else if (playerColor == Color.RED) {
    		colorAIOne = Color.BLUE;
    		colorAITwo = Color.YELLOW;
    		colorAIThree = Color.GREEN;
    	} else if (playerColor == Color.YELLOW) {
    		colorAIOne = Color.BLUE;
    		colorAITwo = Color.RED;
    		colorAIThree = Color.GREEN;
    	} else if (playerColor == Color.GREEN) {
    		colorAIOne = Color.BLUE;
    		colorAITwo = Color.RED;
    		colorAIThree = Color.YELLOW;
    	}
    }

    private int getArrayLength(int[] thisArray) {
    	int i = 0;
    	for (int j = 0; j < thisArray.length; j++) {
    		if (thisArray[j] != 0) {
    			i++;
    		}
    	}
    	return i;
    }
    
    private void tileLoss(int[] oneArray, int[] twoArray, int[] threeArray, int curHex, double oneMilPow, double oneInf, double oneFood, double twoMilPow,
    					double twoInf, double twoFood, double threeMilPow, double threeInf, double threeFood, boolean isWeapFac, boolean isMediaComp, boolean isFarmland) {
    	if (oneArray[curHex-1] != 0) {
    		oneArray[curHex-1] = 0;
    		
    	} else if (twoArray[curHex-1] != 0) {
    		twoArray[curHex-1] = 0;
    		
    	} else if (threeArray[curHex-1] != 0) {
    		threeArray[curHex-1] = 0;
    		
    	}
    }
    
    private void aiTileLoss(int[] oneArray, int[] twoArray, int curHex, double oneMilPow, double oneInf, double oneFood, double twoMilPow,
			double twoInf, double twoFood, boolean isWeapFac, boolean isMediaComp, boolean isFarmland) {
    	if (oneArray[curHex-1] != 0) {
    		oneArray[curHex-1] = 0;

    	} else if (twoArray[curHex-1] != 0) {
    		twoArray[curHex-1] = 0;
    	}
    }
    
    private void bonusLoss() {
    	if (pConMilPower[curHex-1] != 0) {
    		pConMilPower[curHex-1] = 0;
    		
    	} else if (pConInfluence[curHex-1] != 0) {
    		pConInfluence[curHex-1] = 0;
    		
    	}else if (pConFood[curHex-1] != 0) {
    		pConFood[curHex-1] = 0;
    	}
    }
    
    private void bonusUpdate() {
    	pMilPower = getArrayLength(pConMilPower) + 1;
    	pInfluence = getArrayLength(pConInfluence) + 1; 
    	pFood = getArrayLength(pConFood) + 1;
    }
    
    private void tileTypeSignify(Graphics g, Hexagon hexagon, int hex) {
    	g.setFont(new Font("Helvetica nueu", Font.BOLD, 28));
    	
    	if (isCitzSet(hex) == true) {
			g.drawString("C", hexagon.center.x - (hexagon.radius/4), hexagon.center.y + (hexagon.radius/4));
		} else if (isWeapFac(hex) == true) {
			g.drawString("W", hexagon.center.x - (hexagon.radius/4), hexagon.center.y + (hexagon.radius/4));
		} else if (isMediaComp(hex) == true) {
			g.drawString("M", hexagon.center.x - (hexagon.radius/4), hexagon.center.y + (hexagon.radius/4));
		} else if (isFarmland(hex) == true) {
			g.drawString("F", hexagon.center.x - (hexagon.radius/4), hexagon.center.y + (hexagon.radius/4));
		}
    }
    
    private boolean checkSurTiles(int[] pArray, int curHex) {
    	boolean isInSur = false;
    	for (int i = 0; i < pArray.length; i++) {
    		if (pArray[i] == curHex) {
    			isInSur = true;
    		} else {
    			isInSur = false;
    		}
    	}
    	return isInSur;
    }
    
    public void aiTurns(Color thisCol, double milPowerAI, double foodAI, double influenceAI, int[] aiArray, int[] oneArray, int[] twoArray, int[] threeArray,
    		double otherMilPowerOne, double otherInfluenceOne, double otherFoodOne, double otherMilPowerTwo, double otherInfluenceTwo, double otherFoodTwo,
    		Hexagon hexagon1, Hexagon hexagon2, Hexagon hexagon3, Hexagon hexagon4, Hexagon hexagon5,
			Hexagon hexagon6, Hexagon hexagon7, Hexagon hexagon8, Hexagon hexagon9, Hexagon hexagon10,
			Hexagon hexagon11, Hexagon hexagon12, Hexagon hexagon13, Hexagon hexagon14, Hexagon hexagon15,
			Hexagon hexagon16, Hexagon hexagon17, Hexagon hexagon18, Hexagon hexagon19, Hexagon
			hexagon20, Hexagon hexagon21, Hexagon hexagon22, Hexagon hexagon23, Hexagon hexagon24,
			Hexagon hexagon25, Hexagon hexagon26, Hexagon hexagon27, Hexagon hexagon28, Hexagon
			hexagon29, Hexagon hexagon30, Hexagon hexagon31, Hexagon hexagon32, Hexagon hexagon33,
			Hexagon hexagon34, Hexagon hexagon35, Hexagon hexagon36, Hexagon hexagon37) {
    	MCTS mc = new MCTS();
    	int chosen = mc.pickTile(curHex, isEmpty(curHex), isCitzSet(curHex), isMediaComp(curHex), isWeapFac(curHex), isFarmland(curHex));
    	
	    if (chosen == 1) {
	    	curHex = 1;
	    	double probability = tileProcess(curHex, foodAI, milPowerAI, foodAI);
	    			
	    	if (tileTakeAttempt(probability) == true) {
	    		col = thisCol;
	    		aiArray[curHex-1] = curHex;
	    		
	    		if (isWeapFac(1) == true) {
	    			milPowerAI++;
	    		} else if (isMediaComp(1) == true) {
	    			influenceAI++;
	    		} else if (isFarmland(1) == true) {
	    			foodAI++;
	    		}
	    		
	    		tileLoss(oneArray, twoArray, threeArray, curHex, milPowerAIOne, influenceAIOne, foodAIOne, milPowerAITwo, influenceAITwo,
	    				foodAITwo, milPowerAIThree, influenceAIThree, foodAIThree, isWeapFac(curHex), isMediaComp(curHex), isFarmland(curHex));
	    		
	    		bonusLoss();
	    		drawingPanel.paintImmediately(getXMin(hexagon1), getYMin(hexagon1), calcWidth(hexagon1), calcHeight(hexagon1));
	    		
	    	}else if (tileTakeAttempt(probability) == false){
	    		
	    	}
	    	
	    }else if (chosen == 2) {
	    	curHex = 2;
	    	double probability = tileProcess(curHex, foodAI, milPowerAI, foodAI);
	    	
	    	if (tileTakeAttempt(probability) == true) {
	    		col = thisCol;
	    		aiArray[curHex-1] = curHex;
	    		
	    		if (isWeapFac(curHex) == true) {
	    			milPowerAI++;
	    		} else if (isMediaComp(curHex) == true) {
	    			influenceAI++;
	    		} else if (isFarmland(curHex) == true) {
	    			foodAI++;
	    		}
	    		
	    		tileLoss(oneArray, twoArray, threeArray, curHex, milPowerAIOne, influenceAIOne, foodAIOne, milPowerAITwo, influenceAITwo,
	    				foodAITwo, milPowerAIThree, influenceAIThree, foodAIThree, isWeapFac(curHex), isMediaComp(curHex), isFarmland(curHex));
	    		bonusLoss();
	    		drawingPanel.paintImmediately(getXMin(hexagon2), getYMin(hexagon2), calcWidth(hexagon2), calcHeight(hexagon2));
	    		
	    	}else if (tileTakeAttempt(probability) == false){
	    		
	    	}

	    }else if (chosen == 3) {
	    	curHex = 3;
	    	double probability = tileProcess(curHex, foodAI, milPowerAI, foodAI);
	    			
	    	if (tileTakeAttempt(probability) == true) {
	    		col = thisCol;
	    		aiArray[curHex-1] = curHex;
	    		
	    		if (isWeapFac(curHex) == true) {
	    			milPowerAI++;
	    		} else if (isMediaComp(curHex) == true) {
	    			influenceAI++;
	    		} else if (isFarmland(curHex) == true) {
	    			foodAI++;
	    		}
	    		
	    		tileLoss(oneArray, twoArray, threeArray, curHex, milPowerAIOne, influenceAIOne, foodAIOne, milPowerAITwo, influenceAITwo,
	    				foodAITwo, milPowerAIThree, influenceAIThree, foodAIThree, isWeapFac(curHex), isMediaComp(curHex), isFarmland(curHex));
	    		bonusLoss();
	    		drawingPanel.paintImmediately(getXMin(hexagon3), getYMin(hexagon3), calcWidth(hexagon3), calcHeight(hexagon3));
	    		
	    	}else if (tileTakeAttempt(probability) == false){
	    		
	    	}

	    }else if (chosen == 4) {
	    	curHex = 4;
	    	double probability = tileProcess(curHex, foodAI, milPowerAI, foodAI);
	    			
	    	if (tileTakeAttempt(probability) == true) {
	    		col = thisCol;
	    		aiArray[curHex-1] = curHex;
	    		
	    		if (isWeapFac(curHex) == true) {
	    			milPowerAI++;
	    		} else if (isMediaComp(curHex) == true) {
	    			influenceAI++;
	    		} else if (isFarmland(curHex) == true) {
	    			foodAI++;
	    		}
	    		
	    		tileLoss(oneArray, twoArray, threeArray, curHex, milPowerAIOne, influenceAIOne, foodAIOne, milPowerAITwo, influenceAITwo,
	    				foodAITwo, milPowerAIThree, influenceAIThree, foodAIThree, isWeapFac(curHex), isMediaComp(curHex), isFarmland(curHex));
	    		bonusLoss();
	    		drawingPanel.paintImmediately(getXMin(hexagon4), getYMin(hexagon4), calcWidth(hexagon4), calcHeight(hexagon4));
	    		
	    	}else if (tileTakeAttempt(probability) == false){
	    		
	    	}

	    }else if (chosen == 5) {
	    	curHex = 5;
	    	double probability = tileProcess(curHex, foodAI, milPowerAI, foodAI);
	    			
	    	if (tileTakeAttempt(probability) == true) {
	    		col = thisCol;
	    		aiArray[curHex-1] = curHex;
	    		
	    		if (isWeapFac(curHex) == true) {
	    			milPowerAI++;
	    		} else if (isMediaComp(curHex) == true) {
	    			influenceAI++;
	    		} else if (isFarmland(curHex) == true) {
	    			foodAI++;
	    		}
	    		
	    		tileLoss(oneArray, twoArray, threeArray, curHex, milPowerAIOne, influenceAIOne, foodAIOne, milPowerAITwo, influenceAITwo,
	    				foodAITwo, milPowerAIThree, influenceAIThree, foodAIThree, isWeapFac(curHex), isMediaComp(curHex), isFarmland(curHex));
	    		bonusLoss();
	    		drawingPanel.paintImmediately(getXMin(hexagon5), getYMin(hexagon5), calcWidth(hexagon5), calcHeight(hexagon5));
	    		
	    	}else if (tileTakeAttempt(probability) == false){
	    		
	    	}

	    }else if (chosen == 6) {
	    	curHex = 6;
	    	double probability = tileProcess(curHex, foodAI, milPowerAI, foodAI);
	    			
	    	if (tileTakeAttempt(probability) == true) {
	    		col = thisCol;
	    		aiArray[curHex-1] = curHex;
	    		
	    		if (isWeapFac(curHex) == true) {
	    			milPowerAI++;
	    		} else if (isMediaComp(curHex) == true) {
	    			influenceAI++;
	    		} else if (isFarmland(curHex) == true) {
	    			foodAI++;
	    		}
	    		
	    		tileLoss(oneArray, twoArray, threeArray, curHex, milPowerAIOne, influenceAIOne, foodAIOne, milPowerAITwo, influenceAITwo,
	    				foodAITwo, milPowerAIThree, influenceAIThree, foodAIThree, isWeapFac(curHex), isMediaComp(curHex), isFarmland(curHex));
	    		bonusLoss();
	    		drawingPanel.paintImmediately(getXMin(hexagon6), getYMin(hexagon6), calcWidth(hexagon6), calcHeight(hexagon6));
	    		
	    	}else if (tileTakeAttempt(probability) == false){
	    		
	    	}

	    }else if (chosen == 7) {
	    	curHex = 7;
	    	double probability = tileProcess(curHex, foodAI, milPowerAI, foodAI);
	    			
	    	if (tileTakeAttempt(probability) == true) {
	    		col = thisCol;
	    		aiArray[curHex-1] = curHex;
	    		
	    		if (isWeapFac(curHex) == true) {
	    			milPowerAI++;
	    		} else if (isMediaComp(curHex) == true) {
	    			influenceAI++;
	    		} else if (isFarmland(curHex) == true) {
	    			foodAI++;
	    		}
	    		
	    		tileLoss(oneArray, twoArray, threeArray, curHex, milPowerAIOne, influenceAIOne, foodAIOne, milPowerAITwo, influenceAITwo,
	    				foodAITwo, milPowerAIThree, influenceAIThree, foodAIThree, isWeapFac(curHex), isMediaComp(curHex), isFarmland(curHex));
	    		bonusLoss();
	    		drawingPanel.paintImmediately(getXMin(hexagon7), getYMin(hexagon7), calcWidth(hexagon7), calcHeight(hexagon7));
	    		
	    	}else if (tileTakeAttempt(probability) == false){
	    		
	    	}

	    }else if (chosen == 8) {
	    	curHex = 8;
	    	double probability = tileProcess(curHex, foodAI, milPowerAI, foodAI);
	    			
	    	if (tileTakeAttempt(probability) == true) {
	    		col = thisCol;
	    		aiArray[curHex-1] = curHex;
	    		
	    		if (isWeapFac(curHex) == true) {
	    		} else if (isMediaComp(curHex) == true) {
	    			influenceAI++;
	    		} else if (isFarmland(curHex) == true) {
	    			foodAI++;
	    		}
	    		
	    		tileLoss(oneArray, twoArray, threeArray, curHex, milPowerAIOne, influenceAIOne, foodAIOne, milPowerAITwo, influenceAITwo,
	    				foodAITwo, milPowerAIThree, influenceAIThree, foodAIThree, isWeapFac(curHex), isMediaComp(curHex), isFarmland(curHex));
	    		bonusLoss();
	    		drawingPanel.paintImmediately(getXMin(hexagon8), getYMin(hexagon8), calcWidth(hexagon8), calcHeight(hexagon8));
	    		
	    	}else if (tileTakeAttempt(probability) == false){
	    		
	    	}

	    }else if (chosen == 9) {
	    	curHex = 9;
	    	double probability = tileProcess(curHex, foodAI, milPowerAI, foodAI);
	    			
	    	if (tileTakeAttempt(probability) == true) {
	    		col = thisCol;
	    		aiArray[curHex-1] = curHex;
	    		
	    		if (isWeapFac(curHex) == true) {
	    			milPowerAI++;
	    		} else if (isMediaComp(curHex) == true) {
	    			influenceAI++;
	    		} else if (isFarmland(curHex) == true) {
	    			foodAI++;
	    		}
	    		
	    		tileLoss(oneArray, twoArray, threeArray, curHex, milPowerAIOne, influenceAIOne, foodAIOne, milPowerAITwo, influenceAITwo,
	    				foodAITwo, milPowerAIThree, influenceAIThree, foodAIThree, isWeapFac(curHex), isMediaComp(curHex), isFarmland(curHex));
	    		bonusLoss();
	    		drawingPanel.paintImmediately(getXMin(hexagon9), getYMin(hexagon9), calcWidth(hexagon9), calcHeight(hexagon9));
	    		
	    	}else if (tileTakeAttempt(probability) == false){
	    		
	    	}

	    }else if (chosen == 10) {
	    	curHex = 10;
	    	double probability = tileProcess(curHex, foodAI, milPowerAI, foodAI);
	    			
	    	if (tileTakeAttempt(probability) == true) {
	    		col = thisCol;
	    		aiArray[curHex-1] = curHex;
	    		
	    		if (isWeapFac(curHex) == true) {
	    			milPowerAI++;
	    		} else if (isMediaComp(curHex) == true) {
	    			influenceAI++;
	    		} else if (isFarmland(curHex) == true) {
	    			foodAI++;
	    		}
	    		
	    		tileLoss(oneArray, twoArray, threeArray, curHex, milPowerAIOne, influenceAIOne, foodAIOne, milPowerAITwo, influenceAITwo,
	    				foodAITwo, milPowerAIThree, influenceAIThree, foodAIThree, isWeapFac(curHex), isMediaComp(curHex), isFarmland(curHex));
	    		bonusLoss();
	    		drawingPanel.paintImmediately(getXMin(hexagon10), getYMin(hexagon10), calcWidth(hexagon10), calcHeight(hexagon10));
	    		
	    	}else if (tileTakeAttempt(probability) == false){
	    		
	    	}

	    }else if (chosen == 11) {
	    	curHex = 11;
	    	double probability = tileProcess(curHex, foodAI, milPowerAI, foodAI);
	    			
	    	if (tileTakeAttempt(probability) == true) {
	    		col = thisCol;
	    		aiArray[curHex-1] = curHex;
	    		
	    		if (isWeapFac(curHex) == true) {
	    			milPowerAI++;
	    		} else if (isMediaComp(curHex) == true) {
	    			influenceAI++;
	    		} else if (isFarmland(curHex) == true) {
	    			foodAI++;
	    		}
	    		
	    		tileLoss(oneArray, twoArray, threeArray, curHex, milPowerAIOne, influenceAIOne, foodAIOne, milPowerAITwo, influenceAITwo,
	    				foodAITwo, milPowerAIThree, influenceAIThree, foodAIThree, isWeapFac(curHex), isMediaComp(curHex), isFarmland(curHex));
	    		bonusLoss();
	    		drawingPanel.paintImmediately(getXMin(hexagon11), getYMin(hexagon11), calcWidth(hexagon11), calcHeight(hexagon11));
	    		
	    	}else if (tileTakeAttempt(probability) == false){
	    		
	    	}

	    }else if (chosen == 12) {
	    	curHex = 12;
	    	double probability = tileProcess(curHex, foodAI, milPowerAI, foodAI);
	    			
	    	if (tileTakeAttempt(probability) == true) {
	    		col = thisCol;
	    		aiArray[curHex-1] = curHex;
	    		
	    		if (isWeapFac(curHex) == true) {
	    			milPowerAI++;
	    		} else if (isMediaComp(curHex) == true) {
	    			influenceAI++;
	    		} else if (isFarmland(curHex) == true) {
	    			foodAI++;
	    		}
	    		
	    		tileLoss(oneArray, twoArray, threeArray, curHex, milPowerAIOne, influenceAIOne, foodAIOne, milPowerAITwo, influenceAITwo,
	    				foodAITwo, milPowerAIThree, influenceAIThree, foodAIThree, isWeapFac(curHex), isMediaComp(curHex), isFarmland(curHex));
	    		bonusLoss();
	    		drawingPanel.paintImmediately(getXMin(hexagon12), getYMin(hexagon12), calcWidth(hexagon12), calcHeight(hexagon12));
	    		
	    	}else if (tileTakeAttempt(probability) == false){
	    		
	    	}

	    }else if (chosen == 13) {
	    	curHex = 13;
	    	double probability = tileProcess(curHex, foodAI, milPowerAI, foodAI);
	    			
	    	if (tileTakeAttempt(probability) == true) {
	    		col = thisCol;
	    		aiArray[curHex-1] = curHex;
	    		
	    		if (isWeapFac(curHex) == true) {
	    			milPowerAI++;
	    		} else if (isMediaComp(curHex) == true) {
	    			influenceAI++;
	    		} else if (isFarmland(curHex) == true) {
	    			foodAI++;
	    		}
	    		
	    		tileLoss(oneArray, twoArray, threeArray, curHex, milPowerAIOne, influenceAIOne, foodAIOne, milPowerAITwo, influenceAITwo,
	    				foodAITwo, milPowerAIThree, influenceAIThree, foodAIThree, isWeapFac(curHex), isMediaComp(curHex), isFarmland(curHex));
	    		bonusLoss();
	    		drawingPanel.paintImmediately(getXMin(hexagon13), getYMin(hexagon13), calcWidth(hexagon13), calcHeight(hexagon13));
	    		
	    	}else if (tileTakeAttempt(probability) == false){
	    		
	    	}

	    }else if (chosen == 14) {
	    	curHex = 14;
	    	double probability = tileProcess(curHex, foodAI, milPowerAI, foodAI);
	    			
	    	if (tileTakeAttempt(probability) == true) {
	    		col = thisCol;
	    		aiArray[curHex-1] = curHex;
	    		
	    		if (isWeapFac(curHex) == true) {
	    			milPowerAI++;
	    		} else if (isMediaComp(curHex) == true) {
	    			influenceAI++;
	    		} else if (isFarmland(curHex) == true) {
	    			foodAI++;
	    		}
	    		
	    		tileLoss(oneArray, twoArray, threeArray, curHex, milPowerAIOne, influenceAIOne, foodAIOne, milPowerAITwo, influenceAITwo,
	    				foodAITwo, milPowerAIThree, influenceAIThree, foodAIThree, isWeapFac(curHex), isMediaComp(curHex), isFarmland(curHex));
	    		drawingPanel.paintImmediately(getXMin(hexagon14), getYMin(hexagon14), calcWidth(hexagon14), calcHeight(hexagon14));
	    		
	    	}else if (tileTakeAttempt(probability) == false){
	    		
	    	}

	    }else if (chosen == 15) {
	    	curHex = 15;
	    	double probability = tileProcess(curHex, foodAI, milPowerAI, foodAI);
	    			
	    	if (tileTakeAttempt(probability) == true) {
	    		col = thisCol;
	    		aiArray[curHex-1] = curHex;
	    		
	    		if (isWeapFac(curHex) == true) {
	    			milPowerAI++;
	    		} else if (isMediaComp(curHex) == true) {
	    			influenceAI++;
	    		} else if (isFarmland(curHex) == true) {
	    			foodAI++;
	    		}
	    		
	    		tileLoss(oneArray, twoArray, threeArray, curHex, milPowerAIOne, influenceAIOne, foodAIOne, milPowerAITwo, influenceAITwo,
	    				foodAITwo, milPowerAIThree, influenceAIThree, foodAIThree, isWeapFac(curHex), isMediaComp(curHex), isFarmland(curHex));
	    		bonusLoss();
	    		drawingPanel.paintImmediately(getXMin(hexagon15), getYMin(hexagon15), calcWidth(hexagon15), calcHeight(hexagon15));
	    		
	    	}else if (tileTakeAttempt(probability) == false){
	    		
	    	}

	    }else if (chosen == 16) {
	    	curHex = 16;
	    	double probability = tileProcess(curHex, foodAI, milPowerAI, foodAI);
	    			
	    	if (tileTakeAttempt(probability) == true) {
	    		col = thisCol;
	    		aiArray[curHex-1] = curHex;
	    		
	    		if (isWeapFac(curHex) == true) {
	    			milPowerAI++;
	    		} else if (isMediaComp(curHex) == true) {
	    			influenceAI++;
	    		} else if (isFarmland(curHex) == true) {
	    			foodAI++;
	    		}
	    		
	    		tileLoss(oneArray, twoArray, threeArray, curHex, milPowerAIOne, influenceAIOne, foodAIOne, milPowerAITwo, influenceAITwo,
	    				foodAITwo, milPowerAIThree, influenceAIThree, foodAIThree, isWeapFac(curHex), isMediaComp(curHex), isFarmland(curHex));
	    		bonusLoss();
	    		drawingPanel.paintImmediately(getXMin(hexagon16), getYMin(hexagon16), calcWidth(hexagon16), calcHeight(hexagon16));
	    		
	    	}else if (tileTakeAttempt(probability) == false){
	    		
	    	}

	    }else if (chosen == 17) {
	    	curHex = 17;
	    	double probability = tileProcess(curHex, foodAI, milPowerAI, foodAI);
	    			
	    	if (tileTakeAttempt(probability) == true) {
	    		col = thisCol;
	    		aiArray[curHex-1] = curHex;
	    		
	    		if (isWeapFac(curHex) == true) {
	    			milPowerAI++;
	    		} else if (isMediaComp(curHex) == true) {
	    			influenceAI++;
	    		} else if (isFarmland(curHex) == true) {
	    			foodAI++;
	    		}
	    		
	    		tileLoss(oneArray, twoArray, threeArray, curHex, milPowerAIOne, influenceAIOne, foodAIOne, milPowerAITwo, influenceAITwo,
	    				foodAITwo, milPowerAIThree, influenceAIThree, foodAIThree, isWeapFac(curHex), isMediaComp(curHex), isFarmland(curHex));
	    		bonusLoss();
	    		drawingPanel.paintImmediately(getXMin(hexagon17), getYMin(hexagon17), calcWidth(hexagon17), calcHeight(hexagon17));
	    		
	    	}else if (tileTakeAttempt(probability) == false){
	    		
	    	}

	    }else if (chosen == 18) {
	    	curHex = 18;
	    	double probability = tileProcess(curHex, foodAI, milPowerAI, foodAI);
	    			
	    	if (tileTakeAttempt(probability) == true) {
	    		col = thisCol;
	    		aiArray[curHex-1] = curHex;
	    		
	    		if (isWeapFac(curHex) == true) {
	    			milPowerAI++;
	    		} else if (isMediaComp(curHex) == true) {
	    			influenceAI++;
	    		} else if (isFarmland(curHex) == true) {
	    			foodAI++;
	    		}
	    		
	    		tileLoss(oneArray, twoArray, threeArray, curHex, milPowerAIOne, influenceAIOne, foodAIOne, milPowerAITwo, influenceAITwo,
	    				foodAITwo, milPowerAIThree, influenceAIThree, foodAIThree, isWeapFac(curHex), isMediaComp(curHex), isFarmland(curHex));
	    		bonusLoss();
	    		drawingPanel.paintImmediately(getXMin(hexagon18), getYMin(hexagon18), calcWidth(hexagon18), calcHeight(hexagon18));
	    		
	    	}else if (tileTakeAttempt(probability) == false){
	    		
	    	}

	    }else if (chosen == 19) {
	    	curHex = 19;
	    	double probability = tileProcess(curHex, foodAI, milPowerAI, foodAI);
	    			
	    	if (tileTakeAttempt(probability) == true) {
	    		col = thisCol;
	    		aiArray[curHex-1] = curHex;
	    		
	    		if (isWeapFac(curHex) == true) {
	    			milPowerAI++;
	    		} else if (isMediaComp(curHex) == true) {
	    			influenceAI++;
	    		} else if (isFarmland(curHex) == true) {
	    			foodAI++;
	    		}
	    		
	    		tileLoss(oneArray, twoArray, threeArray, curHex, milPowerAIOne, influenceAIOne, foodAIOne, milPowerAITwo, influenceAITwo,
	    				foodAITwo, milPowerAIThree, influenceAIThree, foodAIThree, isWeapFac(curHex), isMediaComp(curHex), isFarmland(curHex));
	    		bonusLoss();
	    		drawingPanel.paintImmediately(getXMin(hexagon19), getYMin(hexagon19), calcWidth(hexagon19), calcHeight(hexagon19));
	    		
	    	}else if (tileTakeAttempt(probability) == false){
	    		
	    	}

	    }else if (chosen == 20) {
	    	curHex = 20;
	    	double probability = tileProcess(curHex, foodAI, milPowerAI, foodAI);
	    			
	    	if (tileTakeAttempt(probability) == true) {
	    		col = thisCol;
	    		aiArray[curHex-1] = curHex;
	    		
	    		if (isWeapFac(curHex) == true) {
	    			milPowerAI++;
	    		} else if (isMediaComp(curHex) == true) {
	    			influenceAI++;
	    		} else if (isFarmland(curHex) == true) {
	    			foodAI++;
	    		}
	    		
	    		tileLoss(oneArray, twoArray, threeArray, curHex, milPowerAIOne, influenceAIOne, foodAIOne, milPowerAITwo, influenceAITwo,
	    				foodAITwo, milPowerAIThree, influenceAIThree, foodAIThree, isWeapFac(curHex), isMediaComp(curHex), isFarmland(curHex));
	    		bonusLoss();
	    		drawingPanel.paintImmediately(getXMin(hexagon20), getYMin(hexagon20), calcWidth(hexagon20), calcHeight(hexagon20));
	    		
	    	}else if (tileTakeAttempt(probability) == false){
	    		
	    	}

	    }else if (chosen == 21) {
	    	curHex = 21;
	    	double probability = tileProcess(curHex, foodAI, milPowerAI, foodAI);
	    			
	    	if (tileTakeAttempt(probability) == true) {
	    		col = thisCol;
	    		aiArray[curHex-1] = curHex;
	    		
	    		if (isWeapFac(curHex) == true) {
	    			milPowerAI++;
	    		} else if (isMediaComp(curHex) == true) {
	    			influenceAI++;
	    		} else if (isFarmland(curHex) == true) {
	    			foodAI++;
	    		}
	    		
	    		tileLoss(oneArray, twoArray, threeArray, curHex, milPowerAIOne, influenceAIOne, foodAIOne, milPowerAITwo, influenceAITwo,
	    				foodAITwo, milPowerAIThree, influenceAIThree, foodAIThree, isWeapFac(curHex), isMediaComp(curHex), isFarmland(curHex));
	    		bonusLoss();
	    		drawingPanel.paintImmediately(getXMin(hexagon21), getYMin(hexagon21), calcWidth(hexagon21), calcHeight(hexagon21));
	    		
	    	}else if (tileTakeAttempt(probability) == false){
	    		
	    	}

	    }else if (chosen == 22) {
	    	curHex = 22;
	    	double probability = tileProcess(curHex, foodAI, milPowerAI, foodAI);
	    			
	    	if (tileTakeAttempt(probability) == true) {
	    		col = thisCol;
	    		aiArray[curHex-1] = curHex;
	    		
	    		if (isWeapFac(curHex) == true) {
	    			milPowerAI++;
	    		} else if (isMediaComp(curHex) == true) {
	    			influenceAI++;
	    		} else if (isFarmland(curHex) == true) {
	    			foodAI++;
	    		}
	    		
	    		tileLoss(oneArray, twoArray, threeArray, curHex, milPowerAIOne, influenceAIOne, foodAIOne, milPowerAITwo, influenceAITwo,
	    				foodAITwo, milPowerAIThree, influenceAIThree, foodAIThree, isWeapFac(curHex), isMediaComp(curHex), isFarmland(curHex));
	    		bonusLoss();
	    		drawingPanel.paintImmediately(getXMin(hexagon22), getYMin(hexagon22), calcWidth(hexagon22), calcHeight(hexagon22));
	    		
	    	}else if (tileTakeAttempt(probability) == false){
	    		
	    	}

	    }else if (chosen == 23) {
	    	curHex = 23;
	    	double probability = tileProcess(curHex, foodAI, milPowerAI, foodAI);
	    			
	    	if (tileTakeAttempt(probability) == true) {
	    		col = thisCol;
	    		aiArray[curHex-1] = curHex;
	    		
	    		if (isWeapFac(curHex) == true) {
	    			milPowerAI++;
	    		} else if (isMediaComp(curHex) == true) {
	    			influenceAI++;
	    		} else if (isFarmland(curHex) == true) {
	    			foodAI++;
	    		}
	    		
	    		tileLoss(oneArray, twoArray, threeArray, curHex, milPowerAIOne, influenceAIOne, foodAIOne, milPowerAITwo, influenceAITwo,
	    				foodAITwo, milPowerAIThree, influenceAIThree, foodAIThree, isWeapFac(curHex), isMediaComp(curHex), isFarmland(curHex));
	    		bonusLoss();
	    		drawingPanel.paintImmediately(getXMin(hexagon23), getYMin(hexagon23), calcWidth(hexagon23), calcHeight(hexagon23));
	    		
	    	}else if (tileTakeAttempt(probability) == false){
	    		
	    	}

	    }else if (chosen == 24) {
	    	curHex = 24;
	    	double probability = tileProcess(curHex, foodAI, milPowerAI, foodAI);
	    			
	    	if (tileTakeAttempt(probability) == true) {
	    		col = thisCol;
	    		aiArray[curHex-1] = curHex;
	    		
	    		if (isWeapFac(curHex) == true) {
	    			milPowerAI++;
	    		} else if (isMediaComp(curHex) == true) {
	    			influenceAI++;
	    		} else if (isFarmland(curHex) == true) {
	    			foodAI++;
	    		}
	    		
	    		tileLoss(oneArray, twoArray, threeArray, curHex, milPowerAIOne, influenceAIOne, foodAIOne, milPowerAITwo, influenceAITwo,
	    				foodAITwo, milPowerAIThree, influenceAIThree, foodAIThree, isWeapFac(curHex), isMediaComp(curHex), isFarmland(curHex));
	    		bonusLoss();
	    		drawingPanel.paintImmediately(getXMin(hexagon24), getYMin(hexagon24), calcWidth(hexagon24), calcHeight(hexagon24));
	    		
	    	}else if (tileTakeAttempt(probability) == false){
	    		
	    	}

	    }else if (chosen == 25) {
	    	curHex = 25;
	    	double probability = tileProcess(curHex, foodAI, milPowerAI, foodAI);
	    			
	    	if (tileTakeAttempt(probability) == true) {
	    		col = thisCol;
	    		aiArray[curHex-1] = curHex;
	    		
	    		if (isWeapFac(curHex) == true) {
	    			milPowerAI++;
	    		} else if (isMediaComp(curHex) == true) {
	    			influenceAI++;
	    		} else if (isFarmland(curHex) == true) {
	    			foodAI++;
	    		}
	    		
	    		tileLoss(oneArray, twoArray, threeArray, curHex, milPowerAIOne, influenceAIOne, foodAIOne, milPowerAITwo, influenceAITwo,
	    				foodAITwo, milPowerAIThree, influenceAIThree, foodAIThree, isWeapFac(curHex), isMediaComp(curHex), isFarmland(curHex));
	    		bonusLoss();
	    		drawingPanel.paintImmediately(getXMin(hexagon25), getYMin(hexagon25), calcWidth(hexagon25), calcHeight(hexagon25));
	    		
	    	}else if (tileTakeAttempt(probability) == false){
	    		
	    	}

	    }else if (chosen == 26) {
	    	curHex = 26;
	    	double probability = tileProcess(curHex, foodAI, milPowerAI, foodAI);
	    			
	    	if (tileTakeAttempt(probability) == true) {
	    		col = thisCol;
	    		aiArray[curHex-1] = curHex;
	    		
	    		if (isWeapFac(curHex) == true) {
	    			milPowerAI++;
	    		} else if (isMediaComp(curHex) == true) {
	    			influenceAI++;
	    		} else if (isFarmland(curHex) == true) {
	    			foodAI++;
	    		}
	    		
	    		tileLoss(oneArray, twoArray, threeArray, curHex, milPowerAIOne, influenceAIOne, foodAIOne, milPowerAITwo, influenceAITwo,
	    				foodAITwo, milPowerAIThree, influenceAIThree, foodAIThree, isWeapFac(curHex), isMediaComp(curHex), isFarmland(curHex));
	    		bonusLoss();
	    		drawingPanel.paintImmediately(getXMin(hexagon26), getYMin(hexagon26), calcWidth(hexagon26), calcHeight(hexagon26));
	    		
	    	}else if (tileTakeAttempt(probability) == false){
	    		
	    	}

	    }else if (chosen == 27) {
	    	curHex = 27;
	    	double probability = tileProcess(curHex, foodAI, milPowerAI, foodAI);
	    			
	    	if (tileTakeAttempt(probability) == true) {
	    		col = thisCol;
	    		aiArray[curHex-1] = curHex;
	    		
	    		if (isWeapFac(curHex) == true) {
	    			milPowerAI++;
	    		} else if (isMediaComp(curHex) == true) {
	    			influenceAI++;
	    		} else if (isFarmland(curHex) == true) {
	    			foodAI++;
	    		}
	    		
	    		tileLoss(oneArray, twoArray, threeArray, curHex, milPowerAIOne, influenceAIOne, foodAIOne, milPowerAITwo, influenceAITwo,
	    				foodAITwo, milPowerAIThree, influenceAIThree, foodAIThree, isWeapFac(curHex), isMediaComp(curHex), isFarmland(curHex));
	    		bonusLoss();
	    		drawingPanel.paintImmediately(getXMin(hexagon27), getYMin(hexagon27), calcWidth(hexagon27), calcHeight(hexagon27));
	    		
	    	}else if (tileTakeAttempt(probability) == false){
	    		
	    	}

	    }else if (chosen == 28) {
	    	curHex = 28;
	    	double probability = tileProcess(curHex, foodAI, milPowerAI, foodAI);
	    			
	    	if (tileTakeAttempt(probability) == true) {
	    		col = thisCol;
	    		aiArray[curHex-1] = curHex;
	    		
	    		if (isWeapFac(curHex) == true) {
	    			milPowerAI++;
	    		} else if (isMediaComp(curHex) == true) {
	    			influenceAI++;
	    		} else if (isFarmland(curHex) == true) {
	    			foodAI++;
	    		}
	    		
	    		tileLoss(oneArray, twoArray, threeArray, curHex, milPowerAIOne, influenceAIOne, foodAIOne, milPowerAITwo, influenceAITwo,
	    				foodAITwo, milPowerAIThree, influenceAIThree, foodAIThree, isWeapFac(curHex), isMediaComp(curHex), isFarmland(curHex));
	    		bonusLoss();
	    		drawingPanel.paintImmediately(getXMin(hexagon28), getYMin(hexagon28), calcWidth(hexagon28), calcHeight(hexagon28));
	    		
	    	}else if (tileTakeAttempt(probability) == false){
	    		
	    	}

	    }else if (chosen == 29) {
	    	curHex = 29;
	    	double probability = tileProcess(curHex, foodAI, milPowerAI, foodAI);
	    			
	    	if (tileTakeAttempt(probability) == true) {
	    		col = thisCol;
	    		aiArray[curHex-1] = curHex;
	    		
	    		if (isWeapFac(curHex) == true) {
	    			milPowerAI++;
	    		} else if (isMediaComp(curHex) == true) {
	    			influenceAI++;
	    		} else if (isFarmland(curHex) == true) {
	    			foodAI++;
	    		}
	    		
	    		tileLoss(oneArray, twoArray, threeArray, curHex, milPowerAIOne, influenceAIOne, foodAIOne, milPowerAITwo, influenceAITwo,
	    				foodAITwo, milPowerAIThree, influenceAIThree, foodAIThree, isWeapFac(curHex), isMediaComp(curHex), isFarmland(curHex));
	    		bonusLoss();
	    		drawingPanel.paintImmediately(getXMin(hexagon29), getYMin(hexagon29), calcWidth(hexagon29), calcHeight(hexagon29));
	    		
	    	}else if (tileTakeAttempt(probability) == false){
	    		
	    	}

	    }else if (chosen == 30) {
	    	curHex = 30;
	    	double probability = tileProcess(curHex, foodAI, milPowerAI, foodAI);
	    			
	    	if (tileTakeAttempt(probability) == true) {
	    		col = thisCol;
	    		aiArray[curHex-1] = curHex;
	    		
	    		if (isWeapFac(curHex) == true) {
	    			milPowerAI++;
	    		} else if (isMediaComp(curHex) == true) {
	    			influenceAI++;
	    		} else if (isFarmland(curHex) == true) {
	    			foodAI++;
	    		}
	    		
	    		tileLoss(oneArray, twoArray, threeArray, curHex, milPowerAIOne, influenceAIOne, foodAIOne, milPowerAITwo, influenceAITwo,
	    				foodAITwo, milPowerAIThree, influenceAIThree, foodAIThree, isWeapFac(curHex), isMediaComp(curHex), isFarmland(curHex));
	    		bonusLoss();
	    		drawingPanel.paintImmediately(getXMin(hexagon30), getYMin(hexagon30), calcWidth(hexagon30), calcHeight(hexagon30));
	    		
	    	}else if (tileTakeAttempt(probability) == false){
	    		
	    	}

	    }else if (chosen == 31) {
	    	curHex = 31;
	    	double probability = tileProcess(curHex, foodAI, milPowerAI, foodAI);
	    			
	    	if (tileTakeAttempt(probability) == true) {
	    		col = thisCol;
	    		aiArray[curHex-1] = curHex;
	    		
	    		if (isWeapFac(curHex) == true) {
	    			milPowerAI++;
	    		} else if (isMediaComp(curHex) == true) {
	    			influenceAI++;
	    		} else if (isFarmland(curHex) == true) {
	    			foodAI++;
	    		}
	    		
	    		tileLoss(oneArray, twoArray, threeArray, curHex, milPowerAIOne, influenceAIOne, foodAIOne, milPowerAITwo, influenceAITwo,
	    				foodAITwo, milPowerAIThree, influenceAIThree, foodAIThree, isWeapFac(curHex), isMediaComp(curHex), isFarmland(curHex));
	    		bonusLoss();
	    		drawingPanel.paintImmediately(getXMin(hexagon31), getYMin(hexagon31), calcWidth(hexagon31), calcHeight(hexagon31));
	    		
	    	}else if (tileTakeAttempt(probability) == false){
	    		
	    	}

	    }else if (chosen == 32) {
	    	curHex = 32;
	    	double probability = tileProcess(curHex, foodAI, milPowerAI, foodAI);
	    			
	    	if (tileTakeAttempt(probability) == true) {
	    		col = thisCol;
	    		aiArray[curHex-1] = curHex;
	    		
	    		if (isWeapFac(curHex) == true) {
	    			milPowerAI++;
	    		} else if (isMediaComp(curHex) == true) {
	    			influenceAI++;
	    		} else if (isFarmland(curHex) == true) {
	    			foodAI++;
	    		}
	    		
	    		tileLoss(oneArray, twoArray, threeArray, curHex, milPowerAIOne, influenceAIOne, foodAIOne, milPowerAITwo, influenceAITwo,
	    				foodAITwo, milPowerAIThree, influenceAIThree, foodAIThree, isWeapFac(curHex), isMediaComp(curHex), isFarmland(curHex));
	    		bonusLoss();
	    		drawingPanel.paintImmediately(getXMin(hexagon32), getYMin(hexagon32), calcWidth(hexagon32), calcHeight(hexagon32));
	    		
	    	}else if (tileTakeAttempt(probability) == false){
	    		
	    	}

	    }else if (chosen == 33) {
	    	curHex = 33;
	    	double probability = tileProcess(curHex, foodAI, milPowerAI, foodAI);
	    			
	    	if (tileTakeAttempt(probability) == true) {
	    		col = thisCol;
	    		aiArray[curHex-1] = curHex;
	    		
	    		if (isWeapFac(curHex) == true) {
	    			milPowerAI++;
	    		} else if (isMediaComp(curHex) == true) {
	    			influenceAI++;
	    		} else if (isFarmland(curHex) == true) {
	    			foodAI++;
	    		}
	    		
	    		tileLoss(oneArray, twoArray, threeArray, curHex, milPowerAIOne, influenceAIOne, foodAIOne, milPowerAITwo, influenceAITwo,
	    				foodAITwo, milPowerAIThree, influenceAIThree, foodAIThree, isWeapFac(curHex), isMediaComp(curHex), isFarmland(curHex));
	    		bonusLoss();
	    		drawingPanel.paintImmediately(getXMin(hexagon33), getYMin(hexagon33), calcWidth(hexagon33), calcHeight(hexagon33));
	    		
	    	}else if (tileTakeAttempt(probability) == false){
	    		
	    	}

	    }else if (chosen == 34) {
	    	curHex = 34;
	    	double probability = tileProcess(curHex, foodAI, milPowerAI, foodAI);
	    			
	    	if (tileTakeAttempt(probability) == true) {
	    		col = thisCol;
	    		aiArray[curHex-1] = curHex;
	    		
	    		if (isWeapFac(curHex) == true) {
	    			milPowerAI++;
	    		} else if (isMediaComp(curHex) == true) {
	    			influenceAI++;
	    		} else if (isFarmland(curHex) == true) {
	    			foodAI++;
	    		}
	    		
	    		tileLoss(oneArray, twoArray, threeArray, curHex, milPowerAIOne, influenceAIOne, foodAIOne, milPowerAITwo, influenceAITwo,
	    				foodAITwo, milPowerAIThree, influenceAIThree, foodAIThree, isWeapFac(curHex), isMediaComp(curHex), isFarmland(curHex));
	    		bonusLoss();
	    		drawingPanel.paintImmediately(getXMin(hexagon34), getYMin(hexagon34), calcWidth(hexagon34), calcHeight(hexagon34));
	    		
	    	}else if (tileTakeAttempt(probability) == false){
	    		
	    	}

	    }else if (chosen == 35) {
	    	curHex = 35;
	    	double probability = tileProcess(curHex, foodAI, milPowerAI, foodAI);
	    			
	    	if (tileTakeAttempt(probability) == true) {
	    		col = thisCol;
	    		aiArray[curHex-1] = curHex;
	    		
	    		if (isWeapFac(curHex) == true) {
	    			milPowerAI++;
	    		} else if (isMediaComp(curHex) == true) {
	    			influenceAI++;
	    		} else if (isFarmland(curHex) == true) {
	    			foodAI++;
	    		}
	    		
	    		tileLoss(oneArray, twoArray, threeArray, curHex, milPowerAIOne, influenceAIOne, foodAIOne, milPowerAITwo, influenceAITwo,
	    				foodAITwo, milPowerAIThree, influenceAIThree, foodAIThree, isWeapFac(curHex), isMediaComp(curHex), isFarmland(curHex));
	    		bonusLoss();
	    		drawingPanel.paintImmediately(getXMin(hexagon35), getYMin(hexagon35), calcWidth(hexagon35), calcHeight(hexagon35));
	    		
	    	}else if (tileTakeAttempt(probability) == false){
	    		
	    	}

	    }else if (chosen == 36) {
	    	curHex = 36;
	    	double probability = tileProcess(curHex, foodAI, milPowerAI, foodAI);
	    			
	    	if (tileTakeAttempt(probability) == true) {
	    		col = thisCol;
	    		aiArray[curHex-1] = curHex;
	    		
	    		if (isWeapFac(curHex) == true) {
	    			milPowerAI++;
	    		} else if (isMediaComp(curHex) == true) {
	    			influenceAI++;
	    		} else if (isFarmland(curHex) == true) {
	    			foodAI++;
	    		}
	    		
	    		tileLoss(oneArray, twoArray, threeArray, curHex, milPowerAIOne, influenceAIOne, foodAIOne, milPowerAITwo, influenceAITwo,
	    				foodAITwo, milPowerAIThree, influenceAIThree, foodAIThree, isWeapFac(curHex), isMediaComp(curHex), isFarmland(curHex));
	    		bonusLoss();
	    		drawingPanel.paintImmediately(getXMin(hexagon36), getYMin(hexagon36), calcWidth(hexagon36), calcHeight(hexagon36));
	    		
	    	}else if (tileTakeAttempt(probability) == false){
	    		
	    	}

	    }else if (chosen == 37) {
	    	curHex = 37;
	    	double probability = tileProcess(curHex, foodAI, milPowerAI, foodAI);
	    			
	    	if (tileTakeAttempt(probability) == true) {
	    		col = thisCol;
	    		aiArray[curHex-1] = curHex;
	    		
	    		if (isWeapFac(curHex) == true) {
	    			milPowerAI++;
	    		} else if (isMediaComp(curHex) == true) {
	    			influenceAI++;
	    		} else if (isFarmland(curHex) == true) {
	    			foodAI++;
	    		}
	    		
	    		tileLoss(oneArray, twoArray, threeArray, curHex, milPowerAIOne, influenceAIOne, foodAIOne, milPowerAITwo, influenceAITwo,
	    				foodAITwo, milPowerAIThree, influenceAIThree, foodAIThree, isWeapFac(curHex), isMediaComp(curHex), isFarmland(curHex));
	    		bonusLoss();
	    		drawingPanel.paintImmediately(getXMin(hexagon37), getYMin(hexagon37), calcWidth(hexagon37), calcHeight(hexagon37));
	    		
	    	}else if (tileTakeAttempt(probability) == false){
	    		
	    	}

	    }else {
	    	System.out.println("NO HEX DETECTED");
	    }
	    
	    System.out.println("MILPOWER" + milPowerAI);
	    System.out.println("INFLUENCE" + influenceAI);
	    System.out.println("FOOD" + foodAI);
    }

}


