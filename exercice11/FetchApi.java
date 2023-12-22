import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import iut.algo.*;

public class FetchApi
{

	private final String APIKEY = "1x9fcAGs5p3PR0eZBxwED6yWln8EsTiZ8wbNVNSNxBKotZNv56v604ay9lbD1PZ7toe0KVeHjZu95cAOQDoZXc2QqOyFsb2fl";

	public boolean creerJoueur(String nomJoueur)
	{

        String resultat = "";

		try {

			String urlStr ="https://api.e-carsadventure.fr/create_joueur.php?apikey="
							+ URLEncoder.encode(this.APIKEY, StandardCharsets.UTF_8) 
							+ "&joueur="
							+ URLEncoder.encode(nomJoueur, StandardCharsets.UTF_8) 
							+ "&uid="
							+ URLEncoder.encode(Uuid.getUuid(), StandardCharsets.UTF_8);

			URL url = new URL(urlStr);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			conn.setRequestMethod("GET");

			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String inputLine;
			StringBuilder content = new StringBuilder();
			while ((inputLine = in.readLine()) != null)
			{
				content.append(inputLine);
			}

			in.close();
			conn.disconnect();

			resultat = content.toString();

			Decomposeur decomposeur = new Decomposeur(resultat);

			boolean erreur = (decomposeur.getString(0) == "true") ? true : false;

			if (erreur) System.out.println(decomposeur.getString(1));


			return erreur;

		} catch (Exception e)
		{
			e.printStackTrace();
		}

		return false;


	}


	public String getJoueur()
	{

		String resultat = "";

		try {
			
			String urlStr ="https://api.e-carsadventure.fr/get_player.php?apikey="
							+ URLEncoder.encode(this.APIKEY, StandardCharsets.UTF_8) 
							+ "&uid="
							+ URLEncoder.encode(Uuid.getUuid(), StandardCharsets.UTF_8);

			URL url = new URL(urlStr);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			conn.setRequestMethod("GET");

			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String inputLine;
			StringBuilder content = new StringBuilder();
			while ((inputLine = in.readLine()) != null)
			{
				content.append(inputLine);
			}

			in.close();
			conn.disconnect();

			resultat = content.toString();

			Decomposeur decomposeur = new Decomposeur(resultat);

			boolean erreur = (decomposeur.getString(0) == "true") ? true : false;

			if (erreur) System.out.println(decomposeur.getString(1));

			return decomposeur.getString(1);

		} catch (Exception e)
		{
			e.printStackTrace();
		}

		return null;


	}

	public boolean finishLevel(int deplacement, float temps, int niveau)
	{

		String resultat = "";

		try {

			System.out.println(temps + " " + String.valueOf(temps));

			String urlStr ="https://api.e-carsadventure.fr/end_level.php?apikey="
							+ URLEncoder.encode(this.APIKEY, StandardCharsets.UTF_8) 
							+ "&uid="
							+ URLEncoder.encode(Uuid.getUuid(), StandardCharsets.UTF_8)
							+ "&level="
							+ URLEncoder.encode(String.valueOf(niveau), StandardCharsets.UTF_8)
							+ "&temps="
							+ URLEncoder.encode(String.valueOf(temps), StandardCharsets.UTF_8)
							+ "&dep="
							+ URLEncoder.encode(String.valueOf(deplacement), StandardCharsets.UTF_8);

			URL url = new URL(urlStr);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			conn.setRequestMethod("GET");

			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String inputLine;
			StringBuilder content = new StringBuilder();
			while ((inputLine = in.readLine()) != null)
			{
				content.append(inputLine);
			}

			in.close();
			conn.disconnect();

			resultat = content.toString();

			Decomposeur decomposeur = new Decomposeur(resultat);

			boolean erreur = (decomposeur.getString(0) == "true") ? true : false;

			if (erreur) System.out.println(decomposeur.getString(1));

			return erreur;

		} catch (Exception e) {

			e.printStackTrace();

		}

		return false;


	}

	public int getLevel()
	{

		String resultat = "";

		try {

			String urlStr ="https://api.e-carsadventure.fr/get_level.php?apikey="
							+ URLEncoder.encode(this.APIKEY, StandardCharsets.UTF_8) 
							+ "&uid="
							+ URLEncoder.encode(Uuid.getUuid(), StandardCharsets.UTF_8);

			URL url = new URL(urlStr);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			conn.setRequestMethod("GET");

			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String inputLine;
			StringBuilder content = new StringBuilder();
			while ((inputLine = in.readLine()) != null)
			{
				content.append(inputLine);
			}

			in.close();
			conn.disconnect();

			resultat = content.toString();

			Decomposeur decomposeur = new Decomposeur(resultat);

			boolean erreur = (decomposeur.getString(0) == "true") ? true : false;

			if (erreur) System.out.println(decomposeur.getString(1));

			return decomposeur.getInt(1);

		} catch (Exception e)
		{
			e.getStackTrace();
		}

		return 0;

	}

	public String[][] getLeaderBoard(int niveau)
	{

		String resultat = "";
		Scanner scComptageLigne;
		Scanner sc;
		String[][] tabLeader;
		int	nbLigne = 0;
		int cpt = 0;

		try {

			String urlStr ="https://api.e-carsadventure.fr/get_leader.php?apikey="
							+ URLEncoder.encode(this.APIKEY, StandardCharsets.UTF_8) 
							+ "&level="
							+ URLEncoder.encode(String.valueOf(niveau), StandardCharsets.UTF_8);

			URL url = new URL(urlStr);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			conn.setRequestMethod("GET");

			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String inputLine;
			StringBuilder content = new StringBuilder();
			while ((inputLine = in.readLine()) != null)
			{
				content.append(inputLine);
			}

			in.close();
			conn.disconnect();

			resultat = content.toString().replaceAll("§", "\n");

			// Compter le nombre de ligne
			scComptageLigne = new Scanner(resultat);

			while (scComptageLigne.hasNextLine()) {

				nbLigne++;

				scComptageLigne.nextLine();

			}

			tabLeader = new String[nbLigne][3];

			// Affecter le résultat dans un tableau
			sc = new Scanner(resultat);

			while (sc.hasNextLine()) {

				Decomposeur dc = new Decomposeur(sc.nextLine());

				for (int cptCol = 0; cptCol < 3; cptCol++)
					tabLeader[cpt][cptCol] = dc.getString(cptCol); 

				cpt++;

			}


			sc.close();
			scComptageLigne.close();

			return tabLeader;

		} catch (Exception e)
		{
			e.printStackTrace();
		}

		return null;


	}

}
