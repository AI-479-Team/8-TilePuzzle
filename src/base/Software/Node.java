package base.Software;

public class Node {
	//---------------------------------------
	private int[][] grid;
	private int row, col;
	private int level;
	private int misplacedTiles;
	//private int[][] LastState; //This would be used to check and make sure that the current node doesn't move back to it's previous state
	private String fromDirection;	// This is the direction that was used to get to this Node.
	private Node prevNode;		// the Node this Node came from
	
	//---------------------------------------
	
	public Node(int[][] matrix) {
		grid = new int[3][3];
	//	LastState = new int[3][3];
		setGrid(matrix);
		setRowCol();
		misplacedTiles = 0;
		fromDirection = "none"; 
		level = 0;
		prevNode = null;
	}
	// Copy Constructor
	public Node(Node node) {
		this.row = node.getRow();
		this.col = node.getCol();
		this.level = node.getLevel();
		this.misplacedTiles = node.getMisplacedTiles();
		this.fromDirection = node.getFromDirection();
		this.prevNode = node.getPrevNode();
		this.copyGrid(node.getGrid());
	}
	
	
	//---------------------------------------------BEGIN: GETTERS/SETTERS----------------------------------
	public void setGrid(int[][] matrix) {
		grid = matrix;
	}
	public int[][] getGrid() {
		return grid;
	}
	
	public void setRowCol() {
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
	public int getRow() {
		return row;
	}
	public int getCol() {
		return col;
	}
	
	public void setLevel(int l) {
		level = l;
	}
	public int getLevel() {
		return level;
	}
	
	public void calculateMisplacedTiles(int[][] GoalState) {
		misplacedTiles = 0;
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
	
	
	public void setFromDirection(String d) {
		fromDirection = d;
	}
	public String getFromDirection() {
		return fromDirection;
	}
	
	public void setPrevNode(Node node) {
		prevNode = node;
	}
	public Node getPrevNode() {
		return prevNode;
	}
	
	public int getManhattanCost() {
		
		return 1;
	}
	public int getMisplacedTilesCost() {
		return level + misplacedTiles;
	}
	//---------------------------------------------END: GETTERS/SETTERS----------------------------------
	
	//---------------------------------------------BEGIN: BLANK TILE MOVEMENT----------------------------------
	public void moveBlankUp() {
		if (row > 0) {
			grid[row][col] = grid[--row][col];
			grid[row][col] = 0;
			incrementLevel();
			setFromDirection("up");
		}
		else
			System.out.println("----------------------ERROR: CAN'T MOVE BLANK SPACE UP----------------------");
	}
	
	public void moveBlankDown() {
		if (row < 2) {
			grid[row][col] = grid[++row][col];
			grid[row][col] = 0;
			incrementLevel();
			setFromDirection("down");
		}
		else
			System.out.println("----------------------ERROR: CAN'T MOVE BLANK SPACE DOWN----------------------");
	}

	public void moveBlankRight() {
		if (col < 2) {
			grid[row][col] = grid[row][++col];
			grid[row][col] = 0;
			incrementLevel();
			setFromDirection("right");
		}
		else 
			System.out.println("----------------------ERROR: CAN'T MOVE BLANK SPACE RIGHT----------------------");
	}	
	
	public void moveBlankLeft() {
		if (col > 0) {
			grid[row][col] = grid[row][--col];
			grid[row][col] = 0;
			incrementLevel();
			setFromDirection("left");
		}
		else
			System.out.println("----------------------ERROR: CAN'T MOVE BLANK SPACE LEFT----------------------");
	}
	//---------------------------------------------END: BLANK TILE MOVEMENT----------------------------------
	
	//---------------------------------------------BEGIN: BLANK TILE CHECKING----------------------------------
	public boolean checkBlankUp() {
		if (row > 0 && !fromDirection.equalsIgnoreCase("down")) 
			return true;
		return false;
	}
	
	public boolean checkBlankDown() {
		if (row < 2 && !fromDirection.equalsIgnoreCase("up"))
			return true;
		return false;
	}

	public boolean checkBlankRight() {
		if (col < 2 && !fromDirection.equalsIgnoreCase("left"))
			return true;
		return false;
	}	
	
	public boolean checkBlankLeft() {
		if (col > 0 && !fromDirection.equalsIgnoreCase("right"))
			return true;
		return false;
	}
	//---------------------------------------------END: BLANK TILE CHECKING----------------------------------
	
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
	
	public void incrementLevel() {
		level++;
	}
	public void decrementLevel() {
		level--;
	}
	
	public void copyGrid(int[][] g) {
		grid = new int[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++)
				grid[i][j] = g[i][j];
		}
	}
}