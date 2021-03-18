import java.util.Random;
public class Main {

	private static int numRestarts;
	private static int heu = 0;
	private static int stateChanges = 0;
	
	//Constructor that creates the board and fills it with 8 queens
	public static Queens[] createBoard() {
		Queens[] boardBegin = new Queens[8];
		//randomly chooses the location of the 8 queens
		Random rnd = new Random();
		for(int i = 0; i < 8; i++) {
			boardBegin[i] = new Queens(rnd.nextInt(8), i);
		}
		return boardBegin;
	}
	
	//method to determine heuristic value
		public static int locateHeu(Queens[] currState) {
			int heu = 0;
			//nested loop that goes through the array to find heuristics using findConflict method
			for(int i = 0; i < currState.length; i++) {
				for(int k = i + 1; k < currState.length; k++) {
					if (currState[i].findConflict(currState[k])) {
						heu++;
					}
				}
			}
			return heu;
		}
	
	//Displays current array as 8x8 board with queens represented by 1's
	private static void printCurrBoard (Queens[] currState) {
		int[][] boardTemp = new int[8][8];
		for (int i = 0; i < 8; i++) {
			boardTemp[currState[i].getRow()][currState[i].getColumn()] = 1;
		}
		System.out.println();
		System.out.println("Current Heuristic: " + heu);
		for(int i = 0; i < 8; i++) {
			System.out.println();
			for(int k = 0; k < 8; k++) {
				System.out.print(boardTemp[i][k] + ",");
			}
		}
		System.out.println();
	}
	
	
	//method that updates the board and keeps track of heuristic value, if the algorithm
	//reaches a local maxima, it restarts
	public static Queens[] updateBoard (Queens[] currBoard) {
		//2 boards created to copy data from current board and create an updated board after
		//moving or restarting
		Queens[] boardNew = new Queens[8];
		Queens[] boardTemp = new Queens[8];
		int currHeu = locateHeu(currBoard);
		int bestHeu = currHeu;
		int tempHeu;
		
		for(int i = 0; i < 8; i++) {
			boardNew[i] = new Queens(currBoard[i].getColumn(), currBoard[i].getRow());
			boardTemp[i] = boardNew[i];
		}
		
		for(int i = 0; i < 8; i++) {
			if (i > 0) {
				boardTemp[i - 1] = new Queens (currBoard[i - 1].getColumn(), currBoard[i- 1].getRow());
				boardTemp[i] = new Queens (0, boardTemp[i].getRow());
			}
				for(int k = 0; k < 8; k++) {
					tempHeu = locateHeu(boardTemp);
					if(tempHeu < bestHeu) {
						bestHeu = tempHeu;
						for(int l = 0; l < 8; l++) {
							boardNew[l] = new Queens(boardTemp[l].getColumn(), boardTemp[l].getRow());
						}
					}
					if(boardTemp[i].getColumn() != 8-1) {
						boardTemp[i].move();
					}
				}
			}
		//restart condition if heuristic value fails to change
		if(bestHeu == currHeu) {
			numRestarts++;
			boardNew = createBoard();
			heu = locateHeu(boardNew);
			System.out.println("Setting new current state");
		}
		else 
			stateChanges++;
			heu = bestHeu;
			return boardNew;	
	}
	
	public static void main(String[] args) {
		int pHeu;
		Queens[] currBoard = createBoard();
		pHeu = locateHeu(currBoard);
		while(pHeu != 0) {
			currBoard = updateBoard(currBoard);
			pHeu = heu;
			printCurrBoard(currBoard);
			System.out.println("Restarts: " + numRestarts);
		}
		System.out.println("Solution Found!");
		System.out.println("Total state changes: " + stateChanges);
	}
	
}
