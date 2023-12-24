public class Piece
{

	private static String[] tabDir = { "Nord", "Ouest", "Sud", "Est" };

	private String nom;
	private boolean[] ouvertures = new boolean[6];

	// Constructeur avec une valeur d'ouverture
	public Piece(int valeur)
	{
		this(valeur, "");
	}

	
	public Piece(int valeur, String nom)
	{
		
	// Constructeur avec une valeur d'ouverture et un n
		this.ouvertures = Conversion.entier2Tab(valeur , 7);

		this.nom = nom;

	}


	// Renvoie la valeur des ouvertures sous forme d'entier grace à la classe Conversion
	public int getValOuvertes()
	{

		int val;

		val = Conversion.tab2Entier(this.ouvertures);

		val = (this.ouvertures[4]) ? val - 16 : val;
		val = (this.ouvertures[5]) ? val - 32 : val;
		

		return val;

	}

	public boolean getOuverture(char dir) 	{ return ouvertures[Piece.indiceDir(dir)]; }

	public boolean getDepart() 				{ return this.ouvertures[4]; }

	public boolean getArrive() 				{ return this.ouvertures[5]; }
	

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
			ret += "("+ String.format("%-12s",this.nom)     +") ";
		else
			ret += String.format("%-14s",this.nom)          + " ";

		ret += "==> " + "Nord(0) :" +   String.format("%2s", this.ouvertures[0]);
		ret += "\tOuest(1) :" +         String.format("%2s", this.ouvertures[1]);
		ret += "\tSud  (2) :" +         String.format("%2s", this.ouvertures[2]);
		ret += "\tEst  (3) :" +         String.format("%2s", this.ouvertures[3]);
		ret += "\tDepart   :" +         String.format("%2s", this.ouvertures[4]);
		ret += "\tArrive   :" +         String.format("%2s", this.ouvertures[5]);
		ret += "\tNull     :" +         String.format("%2s", this.ouvertures[6]);

		return ret;
	}
}