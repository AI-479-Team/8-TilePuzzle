package base.Software;

public class Node {
	//---------------------------------------
	private int row, col;
	private int[][] grid;
	private int level;
	private int misplacedTiles;
	private int[][] LastState; //This would be used to check and make sure that the current node doesn't move back to it's previous state
	
	//---------------------------------------
	
	public Node(int[][] matrix) {
		// TODO Auto-generated constructor stub
		grid = new int[3][3];
		LastState = new int[3][3];
		setGrid(matrix);
		setRowCol();
		misplacedTiles = 0;
	}
	
	public void setLastState(int[][] matrix) {
		LastState = matrix;
	}
	
	public void setGrid(int[][] matrix) {
		grid = matrix;
	}
	
	public void setRowCol(){
		outerloop:
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (grid[i][j] == 0) {
					row = i;
					col = j;
					break outerloop;
				}
			}
		}
	}
	public void printGrid() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j ++) {
				if (grid[i][j] == 0)
					System.out.print(" _");		// prints whitespace then character : " 8"
				else
					System.out.print(" " + grid[i][j]); 
			}
			System.out.println();
		}
		System.out.println();
	}
	public void calculateMisplacedTiles(int[][] GoalState) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j ++) {
				if ((GoalState[i][j] != 0) && (GoalState[i][j] != grid[i][j]))
					misplacedTiles++;
			}
		}
	}
	
	public int getMisplacedTiles() {
		return misplacedTiles;
	}
	
	public int[][] getGrid() {
		return grid;
	}

	//---------------------------------------------BEGIN: BLANK TILE MOVEMENT----------------------------------
	public void moveBlankUp() {
		if (row > 0) {
			grid[row][col] = grid[--row][col];
			grid[row][col] = 0;
		}
		else
			System.out.println("----------------------ERROR: CAN'T MOVE BLANK SPACE UP----------------------");
	}
	
	public void moveBlankDown() {
		if (row < 2) {
			grid[row][col] = grid[++row][col];
			grid[row][col] = 0;
		}
		else
			System.out.println("----------------------ERROR: CAN'T MOVE BLANK SPACE DOWN----------------------");
	}

	public void moveBlankRight() {
		if (col < 2) {
			grid[row][col] = grid[row][++col];
			grid[row][col] = 0;
		}
		else 
			System.out.println("----------------------ERROR: CAN'T MOVE BLANK SPACE RIGHT----------------------");
	}	
	
	public void moveBlankLeft() {
		if (col > 0) {
			grid[row][col] = grid[row][--col];
			grid[row][col] = 0;
		}
		else
			System.out.println("----------------------ERROR: CAN'T MOVE BLANK SPACE LEFT----------------------");
	}
	//---------------------------------------------END: BLANK TILE MOVEMENT----------------------------------
	public int checkBlankUp() {
		if (row > 0) {
			return 1;
		}
		else {
			System.out.println("----------------------ERROR: CAN'T MOVE BLANK SPACE UP----------------------");
			return 0;
		}
	}
	
	public int checkBlankDown() {
		if (row < 2) {
			return 1;
		}
		else {
			System.out.println("----------------------ERROR: CAN'T MOVE BLANK SPACE DOWN----------------------");
			return 0;
		}
	}

	public int checkBlankRight() {
		if (col < 2) {
			return 1;
		}
		else {
			System.out.println("----------------------ERROR: CAN'T MOVE BLANK SPACE RIGHT----------------------");
			return 0;
		}
	}	
	
	public int checkBlankLeft() {
		if (col > 0) {
			return 1;
		}
		else {
			System.out.println("----------------------ERROR: CAN'T MOVE BLANK SPACE LEFT----------------------");
			return 0;
		}
	}
	
	
}