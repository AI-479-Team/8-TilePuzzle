package base.Software;

public class Node {
	//---------------------------------------
	private int row, col;
	private int[][] grid;
	private int level;
	private int misplacedTiles;
	
	//---------------------------------------
	
	public Node() {
		// TODO Auto-generated constructor stub
		grid = new int[3][3];
		setGrid();
		setRowCol();
	}
	//-----------------------------SETTERS AND GETTERS-----------------------------
	public void setGrid() {
		int num = 8;	// 0 will be the blank tile
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j ++) {
				grid[i][j] = num;
				num--;
			}
		}
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
	
	
}