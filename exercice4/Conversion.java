
public class Conversion
{

	public static boolean[] entier2Tab(int valeur, int nbElt)
	{

		boolean[] tab;
		int cpt;
		int nbActuel;

		tab = new boolean[nbElt];

		cpt = 0;
		nbActuel = valeur;
		while (cpt < nbElt && nbActuel > 0)
		{

			tab[cpt] = ((nbActuel % 2) == 1) ? true : false;

			nbActuel = nbActuel / 2;
			cpt++;

		}

		return tab;
	}

	public static int tab2Entier(boolean[] tab)
	{
		int ret = 0;

		for (int i = 0; i < tab.length; i++)
			if (tab[i])
				ret += Math.pow(2, i);

		return ret;
	}

	public static String enChaine(boolean[] tab)
	{
		
		String ret = "";

		for (int cpt = 0; cpt < tab.length; cpt++)
			ret += "+---------";

		ret += "+\n";

		for (int i = 0; i < tab.length; i++)
			ret += "|  " + String.format("%5s", tab[i]) + "  ";
		
		ret += "|\n";

		for (int cpt = 0; cpt < tab.length; cpt++)
			ret += "+---------";

		ret += "+\n";

		for (int i = 0; i < tab.length; i++)
			ret += "  " + String.format("%5s", i) + "   ";

		return ret;
	}

	

	

	public static String grille(Dedale dedale)
	{
		String gRet;
		
		gRet = "";
		
		for (int cptLig = 0; cptLig < dedale.getNbLigne(); cptLig++)
		{
			gRet += "\n+";
			for (int cptCol = 0; cptCol < dedale.getNbColonne(); cptCol++)
				gRet += "----+";

			gRet += "\n|";

			for (int cptCol = 0; cptCol < dedale.getNbColonne(); cptCol++)
				gRet += " " + String.format("%2s", dedale.getPiece(cptLig, cptCol).getValOuvertes()) + " |";
		}

		gRet += "\n+";

		for (int cptCol = 0; cptCol < dedale.getNbColonne(); cptCol++)
				gRet += "----+";
		
		
		return gRet;
	}




	public static String detail(Dedale dedale)
	{
		String sRet;
		
		sRet = "";
		
		for (int cptLig = 0; cptLig < dedale.getNbLigne(); cptLig++)
		{
			
			sRet += "== Ligne "+String.format("%2s", cptLig)+" ==========================================================================================\n";


			for (int cptCol = 0; cptCol < dedale.getNbColonne(); cptCol++)
				sRet += " " + String.format("%-12s", dedale.getPiece(cptLig, cptCol).enChaine(dedale.getPiece(cptLig, cptCol))) + " \n";
		}
	

		sRet += "======================================================================================================";
		
		
		return sRet;
	}
		
}