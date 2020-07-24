import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import java.lang.Thread.State;

public class MCTS {
    
    public int[] getSurTiles(int curPos) {
		int[] surTiles = null;

		if (curPos == 1) {
			surTiles = new int[] {2, 5, 6};
		} else if (curPos == 2) {
			surTiles = new int[] {1, 3, 6, 7};
		} else if (curPos == 3) {
			surTiles = new int[] {2, 4, 7, 8};
		} else if (curPos == 4) {
			surTiles = new int[] {3, 8, 9};
		} else if (curPos == 5) {
			surTiles = new int[] {1, 6, 10, 11};
		} else if (curPos == 6) {
			surTiles = new int[] {1, 2, 5, 7, 11, 12};
		} else if (curPos == 7) {
			surTiles = new int[] {2, 3, 6, 8, 12, 13};
		} else if (curPos == 8) {
			surTiles = new int[] {3, 4, 7, 9, 13, 14};
		} else if (curPos == 9) {
			surTiles = new int[] {4, 8, 14, 15};
		} else if (curPos == 10) {
			surTiles = new int[] {5, 11, 16, 17};
		} else if (curPos == 11) {
			surTiles = new int[] {5, 6, 10, 12, 17, 18};
		} else if (curPos == 12) {
			surTiles = new int[] {6, 7, 11, 13, 18, 19};
		} else if (curPos == 13) {
			surTiles = new int[] {7, 8, 12, 14, 19, 20};
		} else if (curPos == 14) {
			surTiles = new int[] {8, 9, 13, 15, 20, 21};
		} else if (curPos == 15) {
			surTiles = new int[] {9, 14, 21, 22};
		} else if (curPos == 16) {
			surTiles = new int[] {10, 17, 23};
		} else if (curPos == 17) {
			surTiles = new int[] {10, 11, 16, 18, 23, 24};
		} else if (curPos == 18) {
			surTiles = new int[] {11, 12, 17, 19, 24, 25};
		} else if (curPos == 19) {
			surTiles = new int[] {12, 13, 18, 20, 25, 26};
		} else if (curPos == 20) {
			surTiles = new int[] {13, 14, 19, 21, 26, 27};
		} else if (curPos == 21) {
			surTiles = new int[] {14, 15, 20, 22, 27, 28};
		} else if (curPos == 22) {
			surTiles = new int[] {15, 21, 28};
		} else if (curPos == 23) {
			surTiles = new int[] {16, 17, 24, 29};
		} else if (curPos == 24) {
			surTiles = new int[] {17, 18, 23, 25, 29, 30};
		} else if (curPos == 25) {
			surTiles = new int[] {18, 19, 24, 26, 30, 31};
		} else if (curPos == 26) {
			surTiles = new int[] {19, 20, 25, 27, 31, 32};
		} else if (curPos == 27) {
			surTiles = new int[] {20, 21, 26, 28, 32, 33};
		} else if (curPos == 28) {
			surTiles = new int[] {21, 22, 27, 33};
		} else if (curPos == 29) {
			surTiles = new int[] {23, 24, 30, 34};
		} else if (curPos == 30) {
			surTiles = new int[] {24, 25, 29, 31, 34, 35};
		} else if (curPos == 31) {
			surTiles = new int[] {25, 26, 30, 32, 35, 36};
		} else if (curPos == 32) {
			surTiles = new int[] {26, 27, 31, 33, 36, 37};
		} else if (curPos == 33) {
			surTiles = new int[] {27, 28, 32, 37};
		} else if (curPos == 34) {
			surTiles = new int[] {29, 30, 35};
		} else if (curPos == 35) {
			surTiles = new int[] {30, 31, 34, 36};
		} else if (curPos == 36) {
			surTiles = new int[] {31, 32, 35, 37};
		} else if (curPos == 37) {
			surTiles = new int[] {32, 33, 36};
		}
		
		System.out.println(Arrays.toString(surTiles));
		return surTiles;
	}
    
    public int pickTile(int curPos, boolean isEmpty, boolean isCitzSet, boolean isMediaComp, boolean isWeapFac, boolean isFarmland) {
    	int[] curSurTiles = getSurTiles(curPos);
    	int[] tmpSurTiles = getSurTiles(curPos);
    	int[] rewards = new int[curSurTiles.length];
    	int tmpRewMax = 0;
    	int rewMax = 0;
    	int tileChosen = 0;
    	
    	rewards = getRewards(curSurTiles, rewards, isEmpty, isCitzSet, isMediaComp, isWeapFac, isFarmland);
    	rewMax = getRewMax(rewards, rewMax);
    	
    	System.out.println("Rewards: " + Arrays.toString(rewards));
    	System.out.println("rewMax: " + rewMax);
    		
    		for (int j = 0; j < curSurTiles.length; j++) {
    			tmpSurTiles = getSurTiles(curSurTiles[j]);
    			int[] tmpRewards = new int[tmpSurTiles.length];
    			tmpRewards = getRewards(tmpSurTiles, tmpRewards, isEmpty, isCitzSet, isMediaComp, isWeapFac, isFarmland);
    			tmpRewMax = getRewMax(tmpRewards, tmpRewMax);
    			rewards[j] += tmpRewMax;
    		}
    		
    	System.out.println("Rewards update" + Arrays.toString(rewards));
    		
    	//FIX: All rewards returning 1/2
    	
    	
    	
    	
    	tileChosen = curSurTiles[rewMax];
    	System.out.println("CHOSEN BY AI: " + tileChosen);
    	return tileChosen;
    	
    }
    
    private int[] getRewards(int[] array, int[] rewards, boolean isEmpty, boolean isCitzSet, boolean isMediaComp, boolean isWeapFac, boolean isFarmland) {
    	for (int i = 0; i < array.length; i++) {
    		if (isEmpty == true || isCitzSet == true) {
    			rewards[i] = 1;
    		} else if (isMediaComp == true || isWeapFac == true || isFarmland == true) {
    			rewards[i] = 2;
    		}
    	}	
    	
    	return rewards;
    }
    
    private int getRewMax(int[] rewards, int rewMax) {
    	for (int i = 0; i < rewards.length; i++) {
    		if (rewards[i] > rewMax) {
    			rewMax = i;
    		}
    	}
    	
    	return rewMax;
    }
    
}

/*public class MCTS {
	
	public class Node {
		State state;
		Node parent;
		List<Node> childArray;
		
	}
	
	public class Tree {
		Node root;
		
		public Node getRoot() {
			return root;
		}
	}
	
	/*public class State {
		int visitCount;
		double winScore;
		
		public List<State> getAllPossibleStates(){
			
		}
		
		public void randomPlay() {
			getSurTiles(curPos)
		}
	}*/
	
	//Pass relevant values when calling in draw method
	/*public void test(int curPlayer, int curPos) {
		if (curPlayer == 1) {
			
		} else if (curPlayer == 2 || curPlayer == 3 || curPlayer == 4){
			
		}
	}
	
	public void tileValues(int hexagon) {
		boolean tileIsSpecial = false;
		int reward = 1;
		if (tileIsSpecial == true) {
			reward = 2;
		} else if (tileIsSpecial == false) {
			reward = 1;
		}
	}
	
	
	
	public void play(int curPos) {
		int[] curSurTiles = getSurTiles(curPos);
		for (int i = 0; i < curSurTiles.length; i++) {
			
		}
	}
	
	
}*/

////////////////////////////////////////////////////////////////////////////////////////////
	
	/*public class Search {
		public Node moveSelect() {
			Tree tree = new Tree();
			Node rootNode = tree.getRoot();
			
			while(System.currentTimeMillis() < end) {
				Node promisingNode = selectPromisingNode(rootNode);
				expandNode(promisingNode);
			
			
				Node nodeToExplore = promisingNode;
				if (promisingNode.getChildArray().size() > 0) {
					nodeToExplore = promisingNode.getRandomChildNode();
				}
			
				int playOutResult = simulateRandomPlayout(nodeToExplore);
				backPropagation(nodeToExplore, playoutResult);
			
			}
		
			Node winnerNode = rootNode.getChildWithMaxScore();
			tree.setRoot(winnerNode);
			return winnerNode;
		}
	}	
	
	private Node selectPromisingNode(Node rootNode) {
	    Node node = rootNode;
	    while (node.getChildArray().size() != 0) {
	        node = UCT.findBestNodeWithUCT(node);
	    }
	    return node;
	}
	
	public class UCT {
	    public static double uctValue (int totalVisit, double nodeWinScore, int nodeVisit) {
	        if (nodeVisit == 0) {
	            return Integer.MAX_VALUE;
	        }
	        return ((double) nodeWinScore / (double) nodeVisit) 
	          + 1.41 * Math.sqrt(Math.log(totalVisit) / (double) nodeVisit);
	    }
	 
	    public static Node findBestNodeWithUCT(Node node) {
	        int parentVisit = node.getVisitCount();
	        return Collections.max(
	          node.getChildArray(),
	          Comparator.comparing(c -> uctValue(parentVisit, 
	            c.getWinScore(), c.getVisitCount())));
	    }
	}
	
	private void expandNode(Node node) {
		List<State> possibleStates = node.getSurTiles();
		possibleStates.forEach(state -> {
			Node newNode = new Node();
			newNode.setParent(node);
			newNode.getState()
		});
	}/*
	
}	
		
		
	
	/*public class State {
		Board board;
		int visitCount;
		int player = drawObject.getCurPlayer();
		
		public List<State> getAllPossibleStates(){
			
		}
		
		public void randomPlay() {
			
		}
		
	}
	
	public Board findNextMove(Board board, int player) {
		Tree tree = new Tree();
		Node rootNode = tree.getRoot();
		rootNode.getState().setBoard(board);
		rootNode.getState().setPlayerNo(a);
		
		while (System.currentTimeMillis() < end) {
			Node promisingNode = selectPromisingNode(rootNode);
			if (promisingNode.getState().getBoard.checkStatus() == Board.IN_PROGRESS) {
				expandNode(promisingNode);
			}
			Node nodeToExplore = promisingNode;
			if (promisingNode.getChildArray().size() > 0) {
				nodeToExplore = promisingNode.getRandomChildNode();
			}
			int playOutResults = simulateRandomPlayout(nodeToExplore);
			backPropagation(nodeToExplore, playoutResult);
		}
		
		Node winnerNode = rootNode.getChildWithMaxScore();
		tree.setRoot(winnerNode);
		return winnerNode.getState().getBoard();
		
	}
	
	x
	
	public class UCT {
		public static double uctValue(int totalVisit, double nodeWinScore, int nodeVisit) {
			if (nodeVisit == 0) {
				return Integer.MAX_VALUE;
			}
			return ((double) nodeWinScore / (double) nodeVisit)
				+ 1.41 * Math.sqrt(Math.log(totalVisit) / (double) nodeVisit);
		}
		
		public static Node findBestNodeWithUCT(Node node) {
			int parentVisit = node.getState().getVisitCount();
			return Collections.max(node.getChildArray(),
					Comparator.comparing(c -> uctValue(parentVisit, c.getState().getWinScore(), c.getState().getVisitCount())));
		}
	}
	
	private void expandNode(Node node) {
		List<State> possibleStates = node.getState().getAllPossibleStates();
		possibleStates.forEach(state -> {
			Node newNode = new Node(state);
			newNode.setParent(node);
			newNode.getState()
		});
	}*/
	
	/*public void getMoves(int player, int curPos) {
		
	}*/
	

