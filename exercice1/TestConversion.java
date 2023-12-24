public class TestConversion
{

	public static void main(String[] args)
	{

		boolean[] tab = Conversion.entier2Tab(8, 8);

		System.out.println(Conversion.entier2Tab(1, 8));
		System.out.println("\n");
		System.out.println(Conversion.tab2Entier(tab));		System.out.println("\n");

		System.out.println(Conversion.enChaine(tab));
	}
}
