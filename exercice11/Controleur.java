import ihmgui.FrameGrille;
import ihmgui.Controle;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import iut.algo.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;

public class Controleur extends Controle
{
	private Dedale metier;
	private FrameGrille frame;
	private JPanel rightPanel;
	private JLabel label;
	private FetchApi api;


	public Controleur() 
	{
		this.api    = new FetchApi();
		this.metier = new Dedale();
		this.frame  = new FrameGrille(this);


		frame.setSize(1920, 650);
		frame.setLocation(10, 10);
		frame.setTitle("Dédale");


		frame.setVisible(true);

		// Création du panel de droite
		rightPanel = new JPanel();
		rightPanel.setLayout(new BorderLayout());
		rightPanel.setBorder(new EmptyBorder( 40, 50, 0, 0));
		rightPanel.setPreferredSize(new Dimension(900, 100));
		
		
		label = new JLabel("");
		label.setFont(new Font("Arial", Font.PLAIN, 20));
		rightPanel.add(label, BorderLayout.NORTH);
		label.setText(this.initLeaderboard());
		frame.add(rightPanel, BorderLayout.EAST);
		

	}

	public int      setNbLigne()        { return metier.getNbLigne(); }
	public int      setNbColonne()      { return metier.getNbColonne(); }
	public int      setLargeurImg()     { return 100; }
	public int      setLargeurLabel()   { return 1000; }
	public String   setFondGrille()     { return "./images/fond.png"; }

	public String setImage(int ligne, int colonne, int couche)
	{
		String rep = "images/";
		String sImage = null;
		Piece piece = metier.getPiece(ligne, colonne);

		switch (couche)
		{
			case 0 -> {

				if (piece != null)
				{
					sImage = rep + "P" + String.format("%02d", piece.getValOuvertes()) + ".png";
				}
				else
				{
					if (metier.getDifficulte().equals("Débutant"     )) sImage = rep + "herbe.png";
					if (metier.getDifficulte().equals("Intermédiaire")) sImage = rep + "lac.png";
					if (metier.getDifficulte().equals("Expert"       )) sImage = rep + "roche.png";
					if (metier.getDifficulte().equals("Maître"       )) sImage = rep + "lave.png";
				}

			}
			case 1 -> {

				sImage = rep + "dw_" + metier.getSymboleHero(ligne, colonne) + ".png";

			}
			case 2 -> {

				if (piece != null)
				{
					if (piece.getArrive())
					{
						sImage = rep + "arrivee.png";
					}
					if (piece.getDepart())
					{
						sImage = rep + "depart.png";
					}
				}

			}
		}

		return sImage;
	}

	// Méthode déclenchée par les touches dans l'IHM
	public void jouer(String touche)
	{
		if (touche.equals("FL-H"))    	metier.deplacer('N');
		if (touche.equals("FL-G"))    	metier.deplacer('O');
		if (touche.equals("FL-B"))    	metier.deplacer('S');
		if (touche.equals("FL-D"))    	metier.deplacer('E');

		if (touche.equals("O"))        metier.changerNiveau('A');
		if (touche.equals("P"))        metier.changerNiveau('B');
		if (touche.equals("R"))        metier.changerNiveau('C');
		
		if (touche.equals("Z"))        metier.bougerPieceTouche('z');
		if (touche.equals("Q"))        metier.bougerPieceTouche('q');
		if (touche.equals("S"))        metier.bougerPieceTouche('s');
		if (touche.equals("D"))        metier.bougerPieceTouche('d');


		frame.majIHM();
	}

	public void glisser(int ligne1, int colonne1, int ligne2, int colonne2)
	{
		if (ligne1 != ligne2 || colonne1 != colonne2)
		{

			metier.deplacerPiece(ligne1, colonne1, ligne2, colonne2);
			System.out.println("glisser de (" + ligne1 + ":" + colonne1 + ")  vers  (" + ligne2 + ":" + colonne2 + ")"+ metier.getNbDeplacement());

		}
		frame.majIHM();
	}

	public String setTextLabel(int numLbl)
	{
		return switch (numLbl) {
			case 0 -> String.valueOf(metier.getNom());
			case 1 -> String.valueOf(metier.getNbDeplacement());
			case 2 -> String.valueOf(metier.getTempsNiveau());
			case 3 -> String.valueOf(metier.getTempsTotal());
			case 4 -> String.valueOf(metier.getDifficulte());
			case 5 -> {
				yield String.valueOf(metier.getNiveau()) + " / " + String.valueOf(metier.getNbNiveau());
			}
			default -> null;
		};
	}

	public String setLabel(int numLbl)
	{
	

		return switch (numLbl)
		{
			case 0  -> "Nom :"
;			case 1  -> "Nb Dep :";
			case 2  -> "Tps-niv :";
			case 3  -> "Tps-total :";
			case 4  -> "Difficulté:";
			case 5  -> "Niveau :";


			default -> null;
		};

	}

	public String setBouton(int numBtn)
	{

		return switch (numBtn)
		{
			case 0  ->  "Niveau précédent";
			case 1  ->  "Niveau suivant";
			case 2  ->  "Rese Nv";
			case 3  ->  "Supp Données";
			case 4  ->  "Quitter";

			default ->  null;
		};

	}

	public void bouton(int action)
	{
		if (action == 0) { metier.changerNiveau('B'); this.updateLeaderboard(); frame.majIHM(); }
	
		if (action == 1) {
			 metier.changerNiveau('A'); 
			 if(this.estJeuFini()) return; else this.updateLeaderboard(); 
			 frame.majIHM(); }
	
		if (action == 2) { metier.changerNiveau('C'); this.updateLeaderboard(); frame.majIHM(); }
		
		if (action == 3) {
			// Suppression des données
			 this.api.resetAllLevel(); 
			 // changer le niveau et actualiser le plateau
			 metier.setNiveau(1); 
			 metier.setTempsNiveau();

			 this.updateLeaderboard();
			 frame.majIHM();
			
			}

		if (action == 4) { frame.fermer();}
	}

	public boolean estJeuFini()
	{
		if (metier.getNiveau() > metier.getNbNiveau())
		{

			try
			{
				label.setText("Félicitations, vous avez terminé tous les niveaux !");
				Thread.sleep(3000);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}

			frame.fermer();
			return true;
		}
		return false;
	}

	public String initLeaderboard()
	{
		String sRet = "<html><pre>";
		String[][] tabLeader;
		int cpt = 0;

		sRet += "     Nom      \t\t";
		sRet += "    Temps     \t\t";
		sRet += "  Déplacement  ";

		sRet += "\n------------------------------------------------------------------";
		
		
		sRet += "\n";

		tabLeader = this.api.getLeaderBoard(metier.getNiveau());

		for (int cptLig = 1; cptLig < tabLeader.length; cptLig++) 
		{
			String playerName = tabLeader[cptLig][0];
			if (playerName.length() < 15)
			{
				int espacementAjoute = 15 - playerName.length();
				playerName = playerName + " ".repeat(espacementAjoute);
			} else
			{
				if (playerName.length() > 15) playerName = playerName.substring(0, 12) + "...";
			}
			
			String score = tabLeader[cptLig][1];
			if (score.length() < 10)
			{
				int espacementAjoute = 10 - score.length();
				score = " ".repeat(espacementAjoute) + score;
			}
			
			sRet += playerName + "\t\t" + score + "\t\t" + tabLeader[cptLig][2];

			sRet += "\n";
			cpt++;
		}

		if (cpt == 0) sRet += "\n<i>\t\tAucun résultat pour le moment</i>"; else {
			sRet += "\n<i>Faîtes le meilleur score pour battre " + ((cpt > 1) ? "les " : "") + String.valueOf(cpt) + " joueur" + ((cpt > 1) ? "s" : "") + "</i>";
		} 

		sRet += "</pre></html>";

		return sRet;
	}

	public void updateLeaderboard()
	{

		String sRet = this.initLeaderboard();

		this.label.setText(sRet);

		this.frame.majIHM();

	}

	public static void main(String[] a)
	{
		String nom = "";
		FetchApi api = new FetchApi();
		Controleur controleur;
		try
		{
			if (!Uuid.estPresent())
			{
				System.out.println("Bienvenue dans le jeu Dédale !\n\nVeuillez entrer votre nom :");
				nom = Clavier.lireString();
				Uuid.setUuid();
				api.creerJoueur(nom);
				System.out.println("uuid -> /data/uuid.data  /!\\ ne pas perdre votre UUID :" + Uuid.getUuid());

			}
			nom = api.getJoueur();
			System.out.println("Votre nom est   : " + api.getJoueur() +" \nNiveau          : "+ api.getLevel());
			controleur = new Controleur();
			controleur.metier.setNom(nom);
			controleur.metier.setNiveau(api.getLevel());


			
		} catch (Exception e)
		{

		}
	}


}