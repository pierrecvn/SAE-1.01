import ihmgui.FrameGrille;
import ihmgui.Controle;

public class Controleur extends Controle
{
	private Dedale metier;
	private FrameGrille frame;

	public Controleur()
	{
		this.metier = new Dedale();
		this.frame  = new FrameGrille(this);

		frame.setSize(1200, 600);
		frame.setLocation(10, 10);
		frame.setTitle("Dédale");
		frame.setVisible(true);
	}

	public int      setNbLigne()        { return metier.getNbLigne(); }
	public int      setNbColonne()      { return metier.getNbColonne(); }
	public int      setLargeurImg()     { return 100; }
	public int      setLargeurLabel()   { return 1000; }
	public String   setFondGrille()     { return "./images/fond.png"; }

	public String setImage(int ligne, int colonne, int couche)
	{
		String rep = "./images/";
		String sImage = null;
		Piece piece = metier.getPiece(ligne, colonne);

		switch (couche)
		{
			case 0 -> {

				if (piece != null)
				{
					sImage = rep + "P" + String.format("%02d", piece.getValOuvertes()) + ".png";
				}
				else
				{
					sImage = rep + "lave.png";
				}

			}
			case 1 -> {

				sImage = rep + "dw_" + metier.getSymboleHero(ligne, colonne) + ".png";

			}
			case 2 -> {

				if (piece != null)
				{
					if (piece.getArrive())
					{
						sImage = rep + "arrivee.png";
					}
					if (piece.getDepart())
					{
						sImage = rep + "depart.png";
					}
				}

			}
		}

		return sImage;
	}

	// Méthode déclenchée par les touches dans l'IHM
	public void jouer(String touche)
	{
		if (touche.equals("FL-H")) metier.deplacer('N');
		if (touche.equals("FL-G")) metier.deplacer('O');
		if (touche.equals("FL-B")) metier.deplacer('S');
		if (touche.equals("FL-D")) metier.deplacer('E');

		frame.majIHM();
	}

	public void glisser(int ligne1, int colonne1, int ligne2, int colonne2)
	{
		if (ligne1 != ligne2 || colonne1 != colonne2)
		{

			metier.deplacerPiece(ligne1, colonne1, ligne2, colonne2);
			System.out.println("glisser de (" + ligne1 + ":" + colonne1 + ")  vers  (" + ligne2 + ":" + colonne2 + ")"+ metier.getNbDeplacement());

		}
		frame.majIHM();
	}

	public String setTextLabel(int numLbl)
	{
		return String.valueOf(metier.getNbDeplacement());
	}

	public String setLabel(int numLbl)
	{
	

		return switch (numLbl)
		{
			case 0  -> "Nb Dep :";
			default -> null;
		};

	}

	public String setBouton(int numBtn)
	{

		return switch (numBtn)
		{
			case 0  ->  "niveau précédent";
			case 1  ->  "niveau suivant";
			default ->  null;
		};

	}

	public void bouton(int action)
	{
		if (action == 0) { metier.changerNiveau('B'); frame.majIHM();}

		if (action == 1) { metier.changerNiveau('A'); frame.majIHM();}
		
	}

	public static void main(String[] a)
	{
		new Controleur();
	}

}