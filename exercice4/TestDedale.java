public class TestDedale
{
	public static void main(String[] a)
	{
		/*---------------*/
		/*   Donnes     */
		/*---------------*/
		Dedale dedale;
		String separateur;


		/*---------------*/
		/* Instructions  */
		/*---------------*/
		dedale = new Dedale();




		// Affichage du plan
		System.out.println ( "--------"   );
		System.out.println ( " Grille"    );
		System.out.println ( "--------\n" );

		System.out.println ( Conversion.grille ( dedale ) );

		System.out.println();


		// Affichage du Détail
		System.out.println ( "--------"   );
		System.out.println ( " Detail"    );
		System.out.println ( "--------\n" );

		System.out.println ( Conversion.detail ( dedale ) );

		System.out.println();



		System.out.println(dedale.validite());

		
	}

}