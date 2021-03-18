
public class Queens {
	
	private int column;
	private int row;
	//constructor method that creates and tracks location of queen
	public Queens(int column, int row) {
		this.column = column;
		this.row = row;
	}
	//getter method for column value of queen
	public int getColumn() {
		return column;
	}
	//getter method for row value of queen
	public int getRow() {
		return row;
	}
	//method to change location of queens
	public void move() {
		column++;
	}
	//method to determine if a queen is in conflict with another queen
	public boolean findConflict(Queens n) {
		//if statement for straight up and down and left to right conflict
		if(row == n.getRow() || column == n.getColumn()) {
			return true;
		}
		//statement for diagonal conflict
		else if (Math.abs(column-n.getColumn()) == Math.abs(row-n.getRow())) {
			return true;
		}
		else {
			return false;
		}
	}
	
	
}
