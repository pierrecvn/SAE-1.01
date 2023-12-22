public class Plateau
{
	private char[][] grille;
	private int      posLig;
	private int      posCol;
	private String   message;

	public Plateau()
	{
		this.grille = new char[3][3];

		this.posLig=1;
		this.posCol=1;
		this.grille[posLig][posCol] = 'X';

		this.message = "";
	}

	public void fleche(char dir)
	{
		this.grille[this.posLig][this.posCol] = ' ';

		switch (dir)
		{
			case 'N' : this.posLig=0;
			           this.posCol=1;
			           break;
			case 'O' : this.posLig=1;
			           this.posCol=0;
			           break;
			case 'S' : this.posLig=2;
			           this.posCol=1;
			           break;
			case 'E' : this.posLig=1;
			           this.posCol=2;
			           break;
		}

		this.grille[posLig][posCol] = 'X';
	}

	public void action (char action)
	{
		switch (action)
		{
			case 'C' : this.message = "J'adore" ;
			           break;
			case 'A' : this.message = "J'aime pas trop";
			           break;
			default  : this.message = "";
		}

	}

	public int    getNbLigne  (){ return this.grille.length;    }
	public int    getNbColonne(){ return this.grille[0].length; }
	public String getMessage  (){ return this.message;          }

	public char getSymbole (int lig, int col){ return this.grille[lig][col]; }


}