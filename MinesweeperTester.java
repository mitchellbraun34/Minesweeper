package Labs;
import java.util.Scanner;

public class MinesweeperTester {

	

	public static void main(String[] args) {

System.out.println("Welcome to Minesweeper!");
Scanner input = new Scanner(System.in);
boolean playing = true;

//Playing the game
do {
	//initialize varibles
	int size = 0;
	int mines = 0;
	boolean validSetup = false;
	
	//ask for game setup
	
	do {
		System.out.print("Size of board: ");
		size = input.nextInt();
		System.out.print("Number of mines: ");
		mines = input.nextInt();
		if(mines < size * size)
		{
			validSetup = true;
		}
		else {
			System.out.println("\nError: The number of mines is greater than"
			+ "\nor equal to the size of the board\n");

		}
	}while(!validSetup);
	//creates the board
	Minesweeper game = new Minesweeper(size, mines);
	System.out.println("Game ready! \n");
	//consume remaining characters in the scanner 
	input.nextLine();
	game.printVisible();
	game.printBoard();
	
	//ask for user moves
	do {
		System.out.println("Enter coordinates with a space between followed with "
				+ "\na T to test the point or F to place a flag");
		System.out.printf("Mines Reaming:%d\n", game.mines - game.flags);
		
		game.checkPosition(input.nextLine());
	}while(!game.gameOver() && !game.gameWon());
	
	//informs the player that they have won
	if(game.gameWon())
	{
		System.out.println("CONGRATULATIONS!\nYou have won!");
		game.printBoard();
	}
	//informs the player that they have lost
	else if(game.gameOver())
	{
		System.out.println("Sorry, you lose...");
		game.printBoard();
	}
	
	//asks the player if they want to play again
	System.out.print("Play again (y/n)");
	String play = input.next();
	if(play.equalsIgnoreCase("y")) {
		playing = true;
	}
	else if(play.equalsIgnoreCase("n")) {
		playing = false;
	}
	else  {
		System.out.println("Invalid input! Program terminated.");
	}
}while(playing);
//closes scanner
input.close();
}
}

