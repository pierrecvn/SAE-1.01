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

		grillePiece = new Piece[][] {
						{ new Piece(0, "A"), new Piece(12, "B"), new Piece(14, "C"), new Piece(6, "D"),
								new Piece(0, "E") },
						{ new Piece(0, "F"), new Piece(1, "G"), new Piece(5, "H"), new Piece(1, "I"),
								new Piece(0, "J") },
						{ new Piece(8, "K"), new Piece(10, "L"), new Piece(15, "M"), new Piece(10, "N"),
								new Piece(2, "O") },
						{ new Piece(0, "P"), new Piece(4, "Q"), new Piece(5, "R"), new Piece(4, "S"),
								new Piece(0, "T") },
						{ new Piece(0, "U"), new Piece(9, "V"), new Piece(11, "W"), new Piece(3, "X"),
								new Piece(0, "Y") } };

		return grillePiece;

	}

}