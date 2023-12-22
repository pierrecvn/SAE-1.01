import ihmgui.*;

public class Controleur extends Controle
{
	private   Plateau     metier;       // Classe M�tier
	private   FrameGrille frame;        // Classe Vue

	public Controleur()
	{
		metier = new Plateau();				// instanciation de votre classe m�tier
		frame  = new FrameGrille ( this );  // instanciation de la fen�tre graphique


		frame.setSize     ( 650, 400            );
		frame.setLocation ( 200,  10            );
		frame.setTitle    ( "Exemple"           );
		frame.setVisible  ( true                );
	}


	/*--------------------------------------------------------*/
	/* M�thodes qui peuvent �tre d�clar�es pour controler  ou */
	/* ou intercepter des actions de l'IHM graphique.         */
	/* Dans cet exemple toutes les m�thodes possibles ne sont */
	/* pas g�r�es, reportez vous � la documentation  compl�te */
	/* des ressources.                                        */
	/*--------------------------------------------------------*/

	/* M�thode pour la gestion des boutons */
	public String setBouton(int numBtn)
	{
		String lib;

		switch ( numBtn )
		{
			case 0 : lib = "Chocolat";      break;
			case 1 : lib = "Amande";        break;
			default: lib = null;                      // cette derni�re ligne est importante, elle met fin � la contruction des boutons
		}

		return lib;
	}

	public String setLabel (int numLbl)
	{
		String lib;

		switch ( numLbl )
		{
			case 0 : lib = "message :";        break;
			default: lib = null;					// cette derni�re ligne est importante, elle met fin � la contruction des labels
		}

		return lib;
	}



	/* M�thode d�clench�e par les touches de l'IHM          */
	public void jouer (String touche)
	{
		if ( touche.equals ( "CR-Z" ) ) System.out.println ( "Pourquoi voulez-vous annuler ? " );
		if ( touche.equals ( "FL-H" ) ) metier.fleche ( 'N' );
		if ( touche.equals ( "FL-G" ) ) metier.fleche ( 'O' );
		if ( touche.equals ( "FL-B" ) ) metier.fleche ( 'S' );
		if ( touche.equals ( "FL-D" ) ) metier.fleche ( 'E' );

		if ( touche.equals ( "A")     ) System.out.println ( "Vous avez appuyer sur [A]" );
		frame.majIHM();
	}

	public void cliquer (int ligne, int colonne)
	{
		System.out.println ("cliquer sur " + ligne +":" + colonne );

	}

	public void glisser (int ligne1, int colonne1, int ligne2, int colonne2)
	{
		if ( ligne1 != ligne2 && colonne1 != colonne2 )
			System.out.println ( "glisser de (" + ligne1 +":" + colonne1  + ")  vers  (" + ligne2 + ":" + colonne2 + ")" );
		frame.majIHM();

	}

	public void bouton  (int action            )
	{
		if ( action == 0 ) { metier.action ('C');  frame.majIHM(); }
		if ( action == 1 ) { metier.action ('A');  frame.majIHM(); }
		if ( action == 2 ) { metier.action (' ');  frame.majIHM(); }
	}


	/* M�thode utilis�e par l'IHM (paintComponnent)         */

	public String setImage ( int ligne, int colonne, int couche)
	{
		char   symbole;
		String rep = "./images/";
		String sImage=null;

		if ( couche==0)
		{
			symbole = metier.getSymbole (ligne, colonne);

			if      ( symbole == 'X' && ligne == 1 && colonne == 1 ) sImage = rep + "centre.gif";
			else if ( symbole == 'X' && ligne == 1 && colonne == 0 ) sImage = rep + "fleche_gauche.gif";
			else if ( symbole == 'X' && ligne == 1 && colonne == 2 ) sImage = rep + "fleche_droite.gif";
			else if ( symbole == 'X' && ligne == 0 && colonne == 1 ) sImage = rep + "fleche_haute.gif";
			else if ( symbole == 'X' && ligne == 2 && colonne == 1 ) sImage = rep + "fleche_basse.gif";
			else                                                     sImage = rep + "vide30.gif";
		}

		return sImage;
	}

	public String  setTextLabel      (int numLbl)
	{
		return metier.getMessage ();

	}

	public int     setNbLigne        () { return metier.getNbLigne();                  }
	public int     setNbColonne      () { return metier.getNbColonne();                }
	public boolean setNumLigneColonne() { return true;                                 }
	public int     setLargeurImg     () { return 30;                                   }
	public String  setFondGrille     () { return "./images/fond.gif";                  }

	public static void main(String[] arg)
	{
		Controleur exemple = new Controleur();
	}

}