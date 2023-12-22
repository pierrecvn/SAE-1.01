
public class Conversion
{
	public static boolean[] entier2Tab ( int valeur, int nbElt )
	{
		int quotient, divi;
		boolean[] tab = new boolean[nbElt];

		divi = (int) Math.pow ( 2, nbElt-1 );

		for (int cpt=nbElt-1; cpt>0; cpt--)
		{
			quotient = valeur / divi;
			valeur   = valeur % divi;

			divi/= 2;

			if ( quotient == 1 ) tab[cpt] = true;
		}

		if (valeur==1) tab[0] = true;

		return tab;
	}


	public static int tab2Entier ( boolean[] tab )
	{
		int somme = 0;

		for (int cpt=0; cpt<tab.length; cpt++ )
			if ( tab[cpt] )
				somme+= Math.pow ( 2, cpt );

		return somme;
	}


	public static String enChaine(boolean[] tab)
	{
		String sRet="";
		String sSep= "+" + String.format ( "%"+tab.length +"s", " " ).replace ( " ", "-----+" );

		sRet = sSep + "\n";
		sRet += "|";

		for (int cpt=0; cpt< tab.length; cpt++ )
			sRet += String.format ( "%-5s", tab[cpt] ) + "|";

		sRet += "\n" + sSep + "\n ";

		for (int cpt=0; cpt< tab.length; cpt++ )
			sRet+= "  " + String.format("%2d", cpt ) + "  ";

		sRet += "\n";

		return sRet;
	}

}