import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.util.Scanner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class Uuid {
		
	public static void setUuid()
	{
		// fait une regex
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";


		String uuid = "";
		int length = 50;

		for (int i = 0; i < length; i++)
		{
			int index = (int) (Math.random() * characters.length());
			uuid += characters.charAt(index);
		}

		try
		{
			File directory = new File("data");
			if (!directory.exists())
			{
				directory.mkdir();
			}

			PrintWriter pw = new PrintWriter(new FileOutputStream("data/uuid.data"));

			pw.println(uuid);
			pw.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static String getUuid()
	{
		String uuid = "";
	
		try
		{
			Scanner sc = new Scanner(new FileInputStream("data/uuid.data"));

			while (sc.hasNextLine())
				
				uuid = sc.nextLine();

			sc.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return uuid;	
	}


	public static boolean estPresent()
	{
		boolean present = false;
		try
		{
			Scanner sc = new Scanner(new FileInputStream("data/uuid.data"));

			while (sc.hasNextLine())
			{
				present = true;
				sc.nextLine();
			}

			sc.close();
		} catch (FileNotFoundException e) {
			return false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return present;
	}

	public static void main(String[] args) {
		Uuid.setUuid();
		System.out.println(Uuid.getUuid());
	}
}
