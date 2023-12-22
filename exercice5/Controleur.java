import ihmgui.FrameGrille;
import ihmgui.Controle; 


public class Controleur extends Controle
{
	private Dedale      metier;
	private FrameGrille frame;

	public Controleur()
	{
		this.metier = new Dedale();
		this.frame  = new FrameGrille ( this );

		frame.setSize     (  600,   600   );
		frame.setLocation (   10,   10   );
		frame.setTitle    ( "Dedale"     );
		frame.setVisible  ( true         );
	}


	public int     setNbLigne        ()  {  return metier.getNbLigne();     }
	public int     setNbColonne      ()  {  return metier.getNbColonne();   }
	public int     setLargeurImg     ()  {  return 100;                     }
	public String  setFondGrille     ()  {  return "./images/fond.png";     }

	public String setImage ( int ligne, int colonne, int couche)
	{
		String rep = "./images/";
		String sImage=null;

		if ( couche == 0 )
		{
			sImage = rep + "P" + String.format("%02d", metier.getPiece(ligne, colonne).getValOuvertes()) +".png";
		}

		return sImage;
	}

	public static void main(String[] a)
	{
		new Controleur();
	}

} 