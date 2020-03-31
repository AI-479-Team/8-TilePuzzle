package base.Test;

import base.Software.*;

public class TestRun {
	public static void main(String[] args) {
		Node n1 = new Node();
		n1.printGrid();
		n1.moveBlankUp();
		n1.printGrid();
		n1.moveBlankUp();
		n1.printGrid();
		n1.moveBlankLeft();
		n1.printGrid();
		n1.moveBlankDown();
		n1.printGrid();
		n1.moveBlankLeft();
		n1.printGrid();
		n1.moveBlankRight();
		n1.printGrid();
		n1.moveBlankRight();
		n1.printGrid();
		n1.moveBlankDown();
		n1.printGrid();
		
	}
}