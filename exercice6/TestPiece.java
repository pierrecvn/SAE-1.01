
public class TestPiece
{

	public static void main(String[] args)
	{

		Piece p1 = new Piece(5, "Hall");
		Piece p2 = new Piece(10, "Bibliothèque");
		Piece p3 = new Piece(25);
		Piece p4 = new Piece(32);

		System.out.println(p1.enChaine(p1));
		System.out.println(p2.enChaine(p2));
		System.out.println(p3.enChaine(p3));
		System.out.println(p4.enChaine(p4));
	}
}
