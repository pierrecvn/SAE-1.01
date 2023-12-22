public class Dedale
{
	private Piece[][] tabPiece;
	private int NbLigne;
	private int NbColonne;
	private Piece pieceHeros;
	private char dirHeros;

	public Dedale()
	{
		this.NbColonne = 5;
		this.NbLigne = 5;

		this.tabPiece   = Dedale.initPiece(); 

		// mettre le héros dans la pièce de départ
		for (int lig=0; lig<tabPiece.length; lig++)
			for (int col=0; col<tabPiece[lig].length; col++)
				if ( this.tabPiece[lig][col] != null && this.tabPiece[lig][col].getDepart())
					this.pieceHeros = this.tabPiece[lig][col];
		this.dirHeros   = 's';
	}

	private Piece getPieceAdj(int lig, int col, char dir)
	{
		switch (dir)
		{
			case 'N':
				if (lig > 0)
					return this.tabPiece[lig - 1][col];
				break;
			case 'O':
				if (col > 0)
					return this.tabPiece[lig][col - 1];
				break;
			case 'S':
				if (lig < this.getNbLigne() - 1)
					return this.tabPiece[lig + 1][col];
				break;
			case 'E':
				if (col < this.getNbColonne() - 1)
					return this.tabPiece[lig][col + 1];
				break;
		}
		return null;
	}

	public int getNbLigne()
	{
		return this.NbLigne;
	}

	public int getNbColonne()
	{
		return this.NbColonne;
	}

	public Piece getPiece(int lig, int col)
	{
		if (lig > this.NbLigne || lig < 0 || col > this.NbColonne || col < 0) return null;
		return tabPiece[lig][col];
	}


	// initialise le tableau de pièce

	private static Piece[][] initPiece()
	{
		int	cpt;
		int[][] values = {  { 0, 12, 14, 6, 0                          }, 
							{ 0, 1 + (int) Math.pow(2, 4), 5, 1, 0 }, 
							{ 8, 10, 15, 10, 2                         },
							{ 0, 4 + (int) Math.pow(2, 5), 5, 4, 0 }, 
							{ 0, 9, 11, 3, 0                           } };

		

		Piece[][] grillePiece = new Piece[values.length][values[0].length];

		cpt = 0;
		for (int cptLig = 0; cptLig < values.length; cptLig++)
		{
			for (int cptCol = 0; cptCol < values[cptLig].length; cptCol++)
			{
				grillePiece[cptLig][cptCol] = new Piece(values[cptLig][cptCol], String.valueOf(('A' + cpt)));

				cpt++;
			}
		}

		return grillePiece;
	}


	// retourne le symbole de la pièce en fonction de la direction du héros

	public char getSymboleHero(int lig, int col)
	{

		if (this.pieceHeros == this.tabPiece[lig][col])
			return this.dirHeros;
		else
			return ' ';
	}

	// deplace le héros dans la direction donnée

	public void deplacer(char dir)
	{
		Position posHeros = this.rechercherPosition(pieceHeros);

		this.dirHeros = switch (dir)
		{
			case 'N' ->
			{
				if (posHeros.getLig() > 0 && this.pieceHeros.getOuverture('N') && this.dirHeros == 'n')
				{
					this.pieceHeros = this.getPieceAdj(posHeros.getLig(), posHeros.getCol(), 'N');
				}
				yield 'n';
			}
			case 'O' ->
			{
				if (posHeros.getCol() > 0 && this.pieceHeros.getOuverture('O') && this.dirHeros == 'o')
				{
					this.pieceHeros = this.getPieceAdj(posHeros.getLig(), posHeros.getCol(), 'O');
				}
				yield 'o';
			}
			case 'S' ->
			{
				if (posHeros.getLig() < this.getNbLigne() && this.pieceHeros.getOuverture('S') && this.dirHeros == 's')
				{
					this.pieceHeros = this.getPieceAdj(posHeros.getLig(), posHeros.getCol(), 'S');
				}
				yield 's';
			}
			case 'E' ->
			{
				if (posHeros.getCol() < this.getNbColonne() && this.pieceHeros.getOuverture('E') && this.dirHeros == 'e')
				{
					this.pieceHeros = this.getPieceAdj(posHeros.getLig(), posHeros.getCol(), 'E');
				}
				yield 'e';
			}
			default -> throw new IllegalArgumentException("Direction inconnue: " + dir);
		};
	}
	// recherche la position d'une pièce

	private Position rechercherPosition(Piece p)
	{
		for (int lig = 0; lig < this.getNbLigne(); lig++)
			for (int col = 0; col < this.getNbColonne(); col++)
				if (this.tabPiece[lig][col] == p)
					return new Position(lig, col);

		return null;
	}

}
