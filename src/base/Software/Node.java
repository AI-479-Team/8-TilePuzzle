package base.Software;

public class Node {
	
	private int x, y;
	private int[][] grid;
	private int level;
	private int misplacedTiles;
	
	
	public Node() {
		// TODO Auto-generated constructor stub
	}
	public void setGrid() {
		int num = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j ++) {
				grid[i][j] = num;
				num++;
			}
		}
	}
	

}