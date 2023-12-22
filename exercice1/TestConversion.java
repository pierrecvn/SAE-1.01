public class TestConversion
{

	public static void main(String[] args)
	{

		Conversion c = new Conversion();
		boolean[] tab = Conversion.entier2Tab(1, 8);

		System.out.println(c.tab2Entier(tab));
		System.out.println(Conversion.enChaine(tab));
	}
}
