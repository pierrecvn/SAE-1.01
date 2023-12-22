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

		this.tabPiece = Dedale.initPiece();
		for (int lig = 0; lig < tabPiece.length; lig++)
			for (int col = 0; col < tabPiece[lig].length; col++)
				if (this.tabPiece[lig][col] != null && this.tabPiece[lig][col].getDepart())
					this.pieceHeros = this.tabPiece[lig][col];
		this.dirHeros = 's';
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
		if (lig > this.NbLigne || lig < 0 || col > this.NbColonne || col < 0)
			return null;
		return tabPiece[lig][col];
	}

	private static Piece[][] initPiece()
	{
		int cpt;
		int[][] values = { { 0, 0, 0, 0, 0 }, { 8 + 32, 10, 10, 6, 0 }, { 0, 12, -1, 3, 0 }, { 0, 9, 10, 6 + 16, 0 },
						{ 0, 0, 0, 0, 0 } };

		Piece[][] grillePiece = new Piece[values.length][values[0].length];

		cpt = 0;
		for (int cptLig = 0; cptLig < values.length; cptLig++)
		{
			for (int cptCol = 0; cptCol < values[cptLig].length; cptCol++)
			{
				if (values[cptLig][cptCol] >= 0)
					grillePiece[cptLig][cptCol] = new Piece(values[cptLig][cptCol], String.valueOf(('A' + cpt)));
				else
					grillePiece[cptLig][cptCol] = null;

				cpt++;
			}
		}

		return grillePiece;
	}

	public char getSymboleHero(int lig, int col)
	{

		if (this.pieceHeros == this.tabPiece[lig][col])
			return this.dirHeros;
		else
			return ' ';
	}

	public void deplacer(char dir)
	{
		Position posHeros = this.rechercherPosition(pieceHeros);

		this.dirHeros = switch (dir)
		{
		case 'N' ->
		{
			if (posHeros.getLig() > 0 && this.pieceHeros.getOuverture('N') && this.dirHeros == 'n'
					&& this.getPieceAdj(posHeros.getLig(), posHeros.getCol(), 'N') != null
					&& this.getPieceAdj(posHeros.getLig(), posHeros.getCol(), 'N').getValOuvertes() != 0
					&& this.getPieceAdj(posHeros.getLig(), posHeros.getCol(), 'N').getOuverture('S'))
			{
				this.pieceHeros = this.getPieceAdj(posHeros.getLig(), posHeros.getCol(), 'N');
			}
			yield 'n';
		}
		case 'O' ->
		{
			if (posHeros.getCol() > 0 && this.pieceHeros.getOuverture('O') && this.dirHeros == 'o'
					&& this.getPieceAdj(posHeros.getLig(), posHeros.getCol(), 'O') != null
					&& this.getPieceAdj(posHeros.getLig(), posHeros.getCol(), 'O').getValOuvertes() != 0
					&& this.getPieceAdj(posHeros.getLig(), posHeros.getCol(), 'O').getOuverture('E'))
			{
				this.pieceHeros = this.getPieceAdj(posHeros.getLig(), posHeros.getCol(), 'O');
			}
			yield 'o';
		}
		case 'S' ->
		{
			if (posHeros.getLig() < this.getNbLigne() && this.pieceHeros.getOuverture('S') && this.dirHeros == 's'
					&& this.getPieceAdj(posHeros.getLig(), posHeros.getCol(), 'S') != null
					&& this.getPieceAdj(posHeros.getLig(), posHeros.getCol(), 'S').getValOuvertes() != 0
					&& this.getPieceAdj(posHeros.getLig(), posHeros.getCol(), 'S').getOuverture('N'))
			{
				this.pieceHeros = this.getPieceAdj(posHeros.getLig(), posHeros.getCol(), 'S');
			}
			yield 's';
		}
		case 'E' ->
		{
			if (posHeros.getCol() < this.getNbColonne() && this.pieceHeros.getOuverture('E') && this.dirHeros == 'e'
					&& this.getPieceAdj(posHeros.getLig(), posHeros.getCol(), 'E') != null
					&& this.getPieceAdj(posHeros.getLig(), posHeros.getCol(), 'E').getValOuvertes() != 0
					&& this.getPieceAdj(posHeros.getLig(), posHeros.getCol(), 'E').getOuverture('O'))
			{
				this.pieceHeros = this.getPieceAdj(posHeros.getLig(), posHeros.getCol(), 'E');
			}
			yield 'e';
		}
		default -> throw new IllegalArgumentException("Direction inconnue: " + dir);
		};
	}

	private Position rechercherPosition(Piece p)
	{
		for (int lig = 0; lig < this.getNbLigne(); lig++)
			for (int col = 0; col < this.getNbColonne(); col++)
				if (this.tabPiece[lig][col] == p)
					return new Position(lig, col);

		return null;
	}

	public boolean deplacerPiece(int ligne1, int colonne1, int ligne2, int colonne2)
	{

		Piece temp;

		// Vérifie si les coordonnées sont hors du tableau
		if (ligne1 < 0 || ligne1 >= this.NbLigne || colonne1 < 0 || colonne1 >= this.NbColonne || ligne2 < 0
				|| ligne2 >= this.NbLigne || colonne2 < 0 || colonne2 >= this.NbColonne)
			return false;

		// Vérifie si le héros est sur l'une des pièces
		if (this.tabPiece[ligne1][colonne1] == this.pieceHeros || this.tabPiece[ligne2][colonne2] == this.pieceHeros)
			return false;

		// Vérifie si la destination est déjà occupée
		if (this.tabPiece[ligne2][colonne2] != null)
			return false;

		// Vérifie si le déplacement est en diagonale
		if (ligne1 != ligne2 && colonne1 != colonne2)
			return false;

		// Vérifie si la pièce a au moins une ouverture
		

		if (this.tabPiece[ligne1][colonne1].getValOuvertes() == 0)
			return false;

		// Vérifie si le déplacement est sur une pièce adjacente
		if (Math.abs(ligne1 - ligne2) > 1 || Math.abs(colonne1 - colonne2) > 1)
			return false;

		// Déplace la pièce de la position de départ à la position d'arrivée
		temp = this.tabPiece[ligne2][colonne2];
		this.tabPiece[ligne2][colonne2] = this.tabPiece[ligne1][colonne1];
		this.tabPiece[ligne1][colonne1] = temp;

		return true;

	}

	public String getNbDeplacement()
	{
		return null;
	}

}
