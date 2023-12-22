
public class Piece
{

	private static String[] tabDir = { "Nord", "Ouest", "Sud", "Est" };

	private String nom;
	private boolean[] ouvertures = new boolean[4];

	// Constructeur avec une valeur d'ouverture
	public Piece(int valeur)
	{
		this(valeur, "");
	}

	// Constructeur avec une valeur d'ouverture et un nom
	public Piece(int valeur, String nom)
	{

		this.ouvertures = Conversion.entier2Tab(valeur, 4);

		this.nom = nom;

	}

	public boolean getOuverture(char dir)
	{

		return ouvertures[Piece.indiceDir(dir)];

	}

	// Renvoie la valeur des ouvertures sous forme d'entier grace à la classe Conversion
	public int getValOuvertes()
	{
		Conversion c = new Conversion();

		return c.tab2Entier(ouvertures);

	}

	// Renvoie l'indice de la direction
	private static int indiceDir(char dir)
	{

		for (int cpt = 0; cpt < Piece.tabDir.length; cpt++)
		{

			if (dir == Piece.tabDir[cpt].charAt(0))
				return cpt;

		}

		return -1;

	}


	public String enChaine(Piece piece)
	{
		String ret = "";
		ret += String.format("%2s", piece.getValOuvertes()) + " ";
		if (this.nom != "")
			ret += "("+ String.format("%-12s",this.nom) + ") ";
		else
			ret += String.format("%-14s",this.nom) + " ";

		ret += "==> " + "Nord(0) :" + String.format("%2s", this.ouvertures[0]);
		ret += "\tOuest(1) :" + String.format("%2s", this.ouvertures[1]);
		ret += "\tSud  (2) :" + String.format("%2s", this.ouvertures[2]);
		ret += "\tEst  (3) :" + String.format("%2s", this.ouvertures[3]);

		return ret;
	}
}