package puissance4;

import java.util.Scanner;

/**
 * Classe principale
 */
class Puissance4 {
	protected static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.println("Entrez votre nom: ");
		String nom = scanner.nextLine();
		System.out.println("--");

		Partie p = new Partie(new Ordinateur(Jeu.BLEU), new Humain(nom, Jeu.ROUGE));
		p.joue();
	}
}

class Partie {

	private Joueur[] joueurs = new Joueur[2];
	private Jeu jeu;

	public Partie(Joueur joueur1, Joueur joueur2) {
		joueurs[0] = joueur1;
		joueurs[1] = joueur2;
		jeu = new Jeu();
	}

	public void joue() {
		int vainqueur = -1;
		int cJoueur = 0;

		while (vainqueur == -1) {
			joueurs[cJoueur].joue(jeu);
			if (jeu.estPlein()) {
				vainqueur = -1;
			}

			// Si 4 pions sont align�s, on a un vainqueur
			// (m�me si le jeu est plein!)

			if (jeu.cherche4()) {
				vainqueur = cJoueur;
			}

			// On change de joueur pour l'it�ration suivante
			cJoueur++;
			cJoueur %= 2;
		}

		System.out.println("La partie est finie.");
		jeu.afficher();
		if (vainqueur == -1) {
			System.out.println("Match nul.");
		} else {
			System.out.println("Le vainqueur est " + joueurs[vainqueur].getNom());
		}
	}
}
