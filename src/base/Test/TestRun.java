package base.Test;
import java.util.Scanner;
import base.Software.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;



public class TestRun {
	public static int[][] setInitialState(){
		int[][] InitialState;
		InitialState = new int[3][3];
		
		/*			Initial State			*/
		/*Row 1*/
		InitialState[0][0] = 1;
		InitialState[0][1] = 2;
		InitialState[0][2] = 3;
		
		/*Row 2*/
		InitialState[1][0] = 4;
		InitialState[1][1] = 5;
		InitialState[1][2] = 0;
		
		/*Row 3*/
		InitialState[2][0] = 7;
		InitialState[2][1] = 8;
		InitialState[2][2] = 6;
		
		return InitialState;
	}
	
	public static int[][] setGoalState(){
		/*			Goal State			*/
		int[][] GoalState;
		GoalState = new int[3][3];
		
		/*Row 1*/
		GoalState[0][0] = 1;
		GoalState[0][1] = 2;
		GoalState[0][2] = 3;
		
		/*Row 2*/
		GoalState[1][0] = 4;
		GoalState[1][1] = 5;
		GoalState[1][2] = 6;
		
		/*Row 3*/
		GoalState[2][0] = 7;
		GoalState[2][1] = 0;
		GoalState[2][2] = 8;
		
		return GoalState; 
	}
	
	public static boolean isSolvable(int[] initialArray, Map<Integer, Integer> treeMap) {
	    int count = 0; 
	    for (int i = 0; i < 7; i++) {
	    	for (int j = i + 1; j < 8; j++) {
	    		if (treeMap.get(initialArray[i]) < treeMap.get(initialArray[j]))
	    			count++;
	    	}
	    	
	    }
	    return ((count & 1) == 0); 
	} 	
	
	public void dynamic(Node n, ArrayList<Node> exploredStates, int searchType) {
		for (Node exploredNode : exploredStates) {
			if (Arrays.deepEquals(n.getGrid(), exploredNode.getGrid()) ) {
				if (searchType == 0) {
					if (n.getMisplacedTilesCost() <= exploredNode.getMisplacedTilesCost() )
						exploredStates.add(n);
				}
				else {
					if (n.getManhattanCost() <= exploredNode.getManhattanCost())
						exploredStates.add(n);		
				}
			}
		}
	}
	
	public static void addToNextStates(Node n, ArrayList<Node> ExploredStates, ArrayList<Node> NextStates) {
		boolean isIn = false;
		
		for (Node exploredNode : ExploredStates) {
			if (Arrays.deepEquals(n.getGrid(), exploredNode.getGrid())) {
				isIn = true;
				break;
			}
		}
		if (!isIn)
			NextStates.add(n);
	}
	
	public static void sortNextStates(ArrayList<Node> NextStates) {
		int min = 99999;
		int minIndex = 0;
		for (int i = 0; i < NextStates.size(); i++) {
			if (NextStates.get(i).getMisplacedTilesCost() < min) {
				min = NextStates.get(i).getMisplacedTilesCost();
				minIndex = i;
			}
		}
		Collections.swap(NextStates, minIndex, 0);
	}
	
	public static void printMoves(Node node) {
		Node current = node;
		ArrayList<String> moves = new ArrayList<String>();
		while (current.getPrevNode() != null) {
			moves.add(0, current.getFromDirection());
			current = current.getPrevNode();
		}
		
		System.out.println("Solution: ");
		for (String move : moves) {
			System.out.println("Move blank " + move);
		}
	}
	
	public static void main(String[] args) {
		/*
		Scanner row1 = new Scanner(System.in);
		System.out.println("Enter the initial state: ");
		*/
		
		
		Node InitialNode = new Node(setInitialState());
		int[][] goalState = setGoalState();
		
		//-------------------------Setting up TreeMap to determine solvability-------------
		int[] initialArray = new int[8];
		int[] goalArray = new int[8];
		Map<Integer, Integer> treeMap = new TreeMap<Integer, Integer>();
		
		int ia = 0;
		int ga = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (InitialNode.getGrid()[i][j] != 0) {
					initialArray[ia] = InitialNode.getGrid()[i][j];
					ia++;
				}
				if (goalState[i][j] != 0) {
					goalArray[ga] = goalState[i][j];
					ga++;
				}
			}
		}
		
		for (int i = 0; i < 8; i++) {
			treeMap.put(goalArray[i], i+1);
		}
		
		boolean solvable = isSolvable(initialArray, treeMap);
		System.out.println("is solvable: " + solvable);
		//-------------------------------------------------------------------------------------
		
		
		System.out.println(Arrays.toString(initialArray));
		System.out.println(Arrays.toString(goalArray));
		
		//System.out.println("is equal: " + Arrays.deepEquals(InitialNode.getGrid(), currentNode.getGrid()));
		
		System.out.println("Grid of Initial State");
		InitialNode.printGrid();
		
		Node GoalNode = new Node(setGoalState());
		System.out.println("Grid of Goal State");
		GoalNode.printGrid();
		
		ArrayList<Node> ExploredStates = new ArrayList<Node>();
		Node CurrentState = null;
		ArrayList<Node> NextStates = new ArrayList<Node>();
		
		
		boolean flag = false;
		
		while(!flag) { //This should be while(!CurrentNode!=GoalNode || !Impossible)
			if(ExploredStates.size()== 0) {
				CurrentState = InitialNode;
			}
			CurrentState.printGrid();
			if (!Arrays.deepEquals(CurrentState.getGrid(), goalState)) {
				ExploredStates.add(CurrentState);
				if(CurrentState.checkBlankDown()) {
					Node downNode = new Node(CurrentState);
					downNode.moveBlankDown();	// Before moving tile, need to check if move would revert to old state
					downNode.calculateMisplacedTiles(goalState); // THIS MAY NEED TO BE CHANGED
					downNode.setPrevNode(CurrentState);
					addToNextStates(downNode, ExploredStates, NextStates);
				//	downNode.printGrid();
				}
				if(CurrentState.checkBlankUp()) {
					Node upNode = new Node(CurrentState);
					upNode.moveBlankUp();// Before moving tile, need to check if move would revert to old state
					upNode.calculateMisplacedTiles(goalState);
					upNode.setPrevNode(CurrentState);
					addToNextStates(upNode, ExploredStates, NextStates);
			//		upNode.printGrid();
				}
				if(CurrentState.checkBlankLeft()) {
					Node leftNode = new Node(CurrentState);
					leftNode.moveBlankLeft();// Before moving tile, need to check if move would revert to old state
					leftNode.calculateMisplacedTiles(goalState);
					leftNode.setPrevNode(CurrentState);
					addToNextStates(leftNode, ExploredStates, NextStates);
			//		leftNode.printGrid();
			//		System.out.println(leftNode.getMisplacedTilesCost());
				}
				if(CurrentState.checkBlankRight()) {
					Node rightNode = new Node(CurrentState);
					rightNode.moveBlankRight();// Before moving tile, need to check if move would revert to old state
					rightNode.calculateMisplacedTiles(goalState);
					rightNode.setPrevNode(CurrentState);
					addToNextStates(rightNode, ExploredStates, NextStates);
		//			rightNode.printGrid();
				}
			}
			else {
				printMoves(CurrentState);
				break;
			}
			
			if (!NextStates.isEmpty()) {
				sortNextStates(NextStates);
				CurrentState = NextStates.remove(0);
			}
		}
		
	}

}