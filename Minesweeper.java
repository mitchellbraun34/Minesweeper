package Labs;
import java.util.Random;
import java.util.Scanner;

public class Minesweeper {
	private int rows;
	private int colums;
	public int mines;
	public int flags = 0;
	private int currentMines = 0;
	public boolean gameover = false;
	public boolean gamewon = false;
	int[][] mineBoard;
	String[][] clearBoard;
	Random p = new Random();
	public Minesweeper(int a, int b)
	{
		rows = a;
		colums = a;
		mines = b;
		mineBoard = new int[rows][colums];
		clearBoard = new String[rows][colums];
		for(int i = 0; i < rows; i++)
		{
			for(int j = 0; j < colums; j++)
			{
				mineBoard[i][j] = 0;				
			}  
		}
		for(int i = 0; i < rows; i++)
		{
			for(int j = 0; j < colums; j++)
			{	
				clearBoard[i][j] = "| |";
			}
		}
		while(currentMines < mines)
		{
			int x = p.nextInt(rows);
			int y = p.nextInt(colums);
			if(mineBoard[y][x] != -1)
			{
				mineBoard[y][x] = -1;
				currentMines++;
			}
		}
		for(int x = 0; x < rows; x++)
		{
			for(int y = 0; y < colums; y++)
			{
				if(!(mineBoard[x][y] == -1))
				{
				if( x-1 >= 0 && x-1 < rows && y-1 >= 0 && y-1 < colums && mineBoard[x-1][y-1] == -1 )//NW
					mineBoard[x][y]++;
				if(x >= 0 && x < rows && y-1 >=0 && y-1 < colums && mineBoard[x][y-1] == -1 )//N
					mineBoard[x][y]++;
				if(x+1 >= 0 && x+1 < rows && y-1 >= 0 && y-1 < colums && mineBoard[x+1][y-1] == -1)//NE
					mineBoard[x][y]++;
				if(x-1 >= 0 && x-1 < rows && y >= 0 && y < colums && mineBoard[x-1][y] == -1)//W
					mineBoard[x][y]++;
				if(x+1 >= 0 && x+1 < rows && y >= 0 && y < colums && mineBoard[x+1][y] == -1)//E
					mineBoard[x][y]++;
				if(x-1 >= 0 && x-1 < rows && y+1 >= 0 && y+1 < colums && mineBoard[x-1][y+1] == -1)//SW
					mineBoard[x][y]++;
				if(x >= 0 && x < rows && y+1 >= 0 && y+1 < colums && mineBoard[x][y+1] == -1)//S
					mineBoard[x][y]++;
				if(x+1 >= 0 && x+1 < rows && y+1 >= 0 && y+1 < colums && mineBoard[x+1][y+1] == -1)//SE
					mineBoard[x][y]++;
				}
			}
		}
		
	}
	public boolean gameOver()
	{
		for(int i = 0; i < rows; i++)
		{
			for(int j = 0; j < colums; j++)
			{
				if(clearBoard[i][j].equals("|M|"))
				{
					gameover = true;
				}
			}
		}
		return gameover;
	}
	public boolean gameWon()
	{
		int peepee = 0;
		
		for(int i = 0; i < rows; i++)
		{
			for(int j = 0; j < colums; j++)
			{
				if((mineBoard[i][j] == -1) && clearBoard[i][j].equals("|F|"))
				{
					peepee++;
				}
			}
		}
		if (peepee == mines)
			gamewon = true;
		else
			gamewon = false;
		return gamewon;
	}
	public void checkPosition(String input)
	{
		Scanner in = new Scanner(input);
		int TESTROW = in.nextInt();
		int TESTCOLUM = in.nextInt();
		String flag = in.next().toUpperCase();
		if(flag.equals("F"))
		{
			if(!(clearBoard[TESTROW][TESTCOLUM].equals("|F|")))
			{
			if(flags >= mines)
			{
				System.out.print("You cannot place anymore flags\n");
			}
			else {
			clearBoard[TESTROW][TESTCOLUM] = "|F|";
			flags++;
			}
			}
			else {
				flags--;
				clearBoard[TESTROW][TESTCOLUM] = "| |";
			System.out.println("DESTROYING THE FLAG MASTER");
			}
		}
		
		else if(mineBoard[TESTROW][TESTCOLUM] == -1)
		{
			clearBoard[TESTROW][TESTCOLUM] = "|M|";			
		}
		else
		{
			clearBoard[TESTROW][TESTCOLUM] = "|" + String.valueOf(mineBoard[TESTROW][TESTCOLUM]) + "|"; 
		}
		appear();
		printVisible();
	}
	public void printBoard()
	{
		for(int i = 0; i < rows; i++)
		{
			for(int j = 0; j < colums; j++)
			{
				System.out.printf("%2d", mineBoard[i][j]);
			}
			System.out.print("\n");
		}
	}
	public void printVisible()
	{	
		System.out.print("  ");
		for(int i = 0; i < rows; i++)
		{
			System.out.printf("%2d ", i);
		}
		System.out.print("\n");
		for(int a = 0; a < rows; a++)
		{
			System.out.printf("%2d", a);
			for(int b = 0; b < colums; b++)
			{
				appear();
				System.out.printf("%2s", clearBoard[a][b]);
			}
			System.out.print("\n");
		}
	}
	public void appear()
	{
		for(int n = 0; n < (rows * colums); n++)
		{
			for(int x = 0; x < rows; x++)
			{
				for(int y = 0; y < colums; y++)
				{
					if(clearBoard[x][y].contains("0"))
					{
						if(x >= 0 && x < rows && y-1 >=0 && y-1 < colums)
							clearBoard[x][y-1] = "|" + mineBoard[x][y-1] + "|";
						if(x-1 >= 0 && x-1 < rows && y >= 0 && y < colums)
							clearBoard[x-1][y] = "|" + mineBoard[x-1][y] + "|";
						if(x+1 >= 0 && x+1 < rows && y >= 0 && y < colums)
							clearBoard[x+1][y] = "|" + mineBoard[x+1][y] + "|";
						if(x >= 0 && x < rows && y+1 >= 0 && y+1 < colums)
							clearBoard[x][y+1] = "|" + mineBoard[x][y+1] + "|";
						if(x-1 >= 0 && x-1 < rows && y-1 >= 0 && y-1 < colums)
							clearBoard[x-1][y-1] = "|" + mineBoard[x-1][y-1] + "|";
						if(x+1 >= 0 && x+1 < rows && y-1 >= 0 && y-1 < colums)
							clearBoard[x+1][y-1] = "|" + mineBoard[x+1][y-1] + "|"; 
						if(x-1 >= 0 && x-1 < rows && y+1 >= 0 && y+1 < colums)
							clearBoard[x-1][y+1] = "|" + mineBoard[x-1][y+1] + "|"; 
						if(x+1 >= 0 && x+1 < rows && y+1 >= 0 && y+1 < colums)
							clearBoard[x+1][y+1] = "|" + mineBoard[x+1][y+1] + "|";
					}
				}
			}
		n++;
		}
	}
}
