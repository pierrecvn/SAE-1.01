public class Dedale
{
	private Piece[][] tabPiece;
	private int NbLigne;
	private int NbColonne;

	public Dedale()
	{
		this.NbColonne = 5;
		this.NbLigne = 5;

		this.tabPiece   = Dedale.initPiece();

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
		if (lig > this.NbLigne || lig < 0)
			return null;
		if (col > this.NbColonne || col < 0)
			return null;

		return tabPiece[lig][col];
	}

	private static Piece[][] initPiece()
	{

		Piece[][] grillePiece;

		grillePiece =
				new Piece[][] {
						{ new Piece(0, "A"), new Piece(12, "B"), new Piece(14, "C"), new Piece(6, "D"),
								new Piece(0, "E") },
						{ new Piece(0, "F"), new Piece(1, "G"), new Piece(5, "H"), new Piece(1, "I"),
								new Piece(0, "J") },
						{ new Piece(8, "K"), new Piece(14, "L"), new Piece(15, "M"), new Piece(14, "N"),
								new Piece(2, "O") },
						{ new Piece(0, "P"), new Piece(4, "Q"), new Piece(13, "R"), new Piece(4, "S"),
								new Piece(0, "T") },
						{ new Piece(0, "U"), new Piece(9, "V"), new Piece(11, "W"), new Piece(3, "X"),
								new Piece(0, "Y") } };

		return grillePiece;

		// grillePiece = new Piece[][] { {new Piece(0, "A"), new Piece(12, "B"),
		// new Piece(14, "C"), new Piece( 6, "D"), new Piece(0, "E") },
		// {new Piece(0, "F"), new Piece( 1, "G"), new Piece( 5, "H"), new
		// Piece( 1, "I"), new Piece(0, "J") },
		// {new Piece(8, "K"), new Piece(10, "L"), new Piece(15, "M"), new
		// Piece(10, "N"), new Piece(2, "O") },
		// {new Piece(0, "P"), new Piece( 4, "Q"), new Piece(5, "R"), new Piece(
		// 4, "S"), new Piece(0, "T") },
		// {new Piece(0, "U"), new Piece( 9, "V"), new Piece(11, "W"), new
		// Piece( 3, "X"), new Piece(0, "Y") } };

		// return grillePiece;

	}

	public String validite()
	{
		String ret = "";

		for (int cptLig = 0; cptLig < this.NbLigne; cptLig++)
		{
			for (int cptCol = 0; cptCol < this.NbColonne; cptCol++)
			{
				ret += "Piece[" + String.format("%2s", cptLig) + "]";
				ret += "[" + String.format("%2s", cptCol) + "] ";
				ret += String.format("%2s", this.getPiece(cptLig, cptCol).getValOuvertes()) + "\t";

				Piece nord = this.getPieceAdj(cptLig, cptCol, 'N');
				Piece ouest = this.getPieceAdj(cptLig, cptCol, 'O');
				Piece sud = this.getPieceAdj(cptLig, cptCol, 'S');
				Piece est = this.getPieceAdj(cptLig, cptCol, 'E');

				boolean estCorrect = true;

				if (nord != null && nord.getOuverture('S') != this.getPiece(cptLig, cptCol).getOuverture('N'))
				{
					ret += "\n   pb avec pièce située au Nord";
					estCorrect = false;
					
				}

				if (ouest != null && ouest.getOuverture('E') != this.getPiece(cptLig, cptCol).getOuverture('O'))
				{
					ret += "\n   pb avec pièce située à l'Ouest";
					estCorrect = false;
				}
				
				if (sud != null && sud.getOuverture('N') != this.getPiece(cptLig, cptCol).getOuverture('S'))
				{
					ret += "\n   pb avec pièce située au Sud";
					estCorrect = false;
				}
				
				if (est != null && est.getOuverture('O') != this.getPiece(cptLig, cptCol).getOuverture('E'))
				{
					ret += "\n   pb avec pièce située à l'Est";
					estCorrect = false;
				}

				if (estCorrect)
					ret += "OK\n";
				else
					ret += "\n";
			}
		}

		return ret;
	}

}
