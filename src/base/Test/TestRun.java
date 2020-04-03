package base.Test;

import base.Software.*;
import java.util.ArrayList;

public class TestRun {
	public static int[][] setInitialState(){
		int[][] InitialState;
		InitialState = new int[3][3];
		
		/*			Initial State			*/
		/*Row 1*/
		InitialState[0][0] = 4;
		InitialState[0][1] = 3;
		InitialState[0][2] = 1;
		
		/*Row 2*/
		InitialState[1][0] = 8;
		InitialState[1][1] = 0;
		InitialState[1][2] = 7;
		
		/*Row 3*/
		InitialState[2][0] = 6;
		InitialState[2][1] = 2;
		InitialState[2][2] = 5;
		
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
		GoalState[1][1] = 0;
		GoalState[1][2] = 5;
		
		/*Row 3*/
		GoalState[2][0] = 6;
		GoalState[2][1] = 7;
		GoalState[2][2] = 8;
		
		return GoalState; 
	}
	
	public static void main(String[] args) {
		
		Node InitialNode = new Node(setInitialState());
		System.out.println("Grid of Initial State");
		InitialNode.printGrid();
		
		Node GoalNode = new Node(setGoalState());
		System.out.println("Grid of Goal State");
		GoalNode.printGrid();
		
		ArrayList<Node> ExploredStates = new ArrayList<Node>();
		ArrayList<Node> CurrentStates = new ArrayList<Node>();
		ArrayList<Node> NextStates = new ArrayList<Node>();
		
		boolean flag = false;
		int level = 1;
		
		while(!flag){ //This should be while(!CurrentNode!=GoalNode || !Impossible)
			if(ExploredStates.size()== 0) {
				CurrentStates.add(InitialNode);
			}
			for(Node CurrentState : CurrentStates) {
				if(CurrentState.getGrid() != GoalNode.getGrid()) {
					if(CurrentState.checkBlankDown() == 1) {
						Node downNode = new Node(CurrentState.getGrid());
						downNode.moveBlankDown();// Before moving tile, need to check if move would revert to old state
						NextStates.add(downNode);
						ExploredStates.add(downNode);
					}
					if(CurrentState.checkBlankUp() == 1) {
						Node upNode = new Node(CurrentState.getGrid());
						upNode.moveBlankDown();// Before moving tile, need to check if move would revert to old state
						NextStates.add(upNode);
						ExploredStates.add(upNode);
					}
					if(CurrentState.checkBlankLeft() == 1) {
						Node leftNode = new Node(CurrentState.getGrid());
						leftNode.moveBlankDown();// Before moving tile, need to check if move would revert to old state
						NextStates.add(leftNode);
						ExploredStates.add(leftNode);
					}
					if(CurrentState.checkBlankRight() == 1) {
						Node rightNode = new Node(CurrentState.getGrid());
						rightNode.moveBlankDown();// Before moving tile, need to check if move would revert to old state
						NextStates.add(rightNode);
						ExploredStates.add(rightNode);
					}
				}
				else {
					flag = true;
				}
				
			}
			int minimum_cost = 0;
			int temp_cost = 0;
			ArrayList<Node> PotentialStates = new ArrayList<Node>();
			
			for(int i = 0; i< NextStates.size(); i++) {
				if(i == 0) {
					minimum_cost = NextStates.get(0).getMisplacedTiles() + level;
					PotentialStates.add(NextStates.get(0));
				}
				else {
					temp_cost = NextStates.get(i).getMisplacedTiles() + level;
					if(temp_cost<minimum_cost) {
						minimum_cost = temp_cost;
						PotentialStates = new ArrayList<Node>();
						PotentialStates.add(NextStates.get(i));
					}
					else if(temp_cost == minimum_cost) {
						PotentialStates.add(NextStates.get(i));
					}
				}
			}
			CurrentStates = new ArrayList<Node>();
			NextStates = new ArrayList<Node>();
			CurrentStates = PotentialStates;
			level++;
		}
	}
}