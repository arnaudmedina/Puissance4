package puissance4;

class Joueur {
	private String nom;
	private int couleur;

	public Joueur(String nom, int couleur) {
		this.nom = nom;
		this.couleur = couleur;
	}

	public String getNom() {
		return nom;
	}

	public int getCouleur() {
		return couleur;
	}

	/**
	 * Cette m�thode joue un coup avec le tableau re�u en param�tre. La m�thode
	 * est vide car les sous-classes doivent l'impl�menter. (Vous verrez
	 * prochainement comment g�rer ce genre de cas plus proprement)
	 * 
	 * @param jeu
	 *            Le Jeu avec lequel jouer.
	 */
	public void joue(Jeu jeu) {
	}

}

class Humain extends Joueur {

	public Humain(String nom, int couleur) {
		super(nom, couleur);
	}

	public void joue(Jeu jeu) {
		jeu.afficher();

		boolean valide;
		do {
			System.out.println("Joueur " + this.getNom() + ", entrez un num�ro de colonne" + "  (entre 1 et "
					+ jeu.getTaille() + ") : ");

			int col = Puissance4.scanner.nextInt(); // on pourrait faire ici la
													// validation de la lecture
			col--; // remet entre 0 et taille-1 (indice � la Java)

			valide = jeu.joueCoup(col, this.getCouleur());
			if (valide == false) {
				System.out.println("-> Coup NON valide.");
			}
		} while (valide == false);
	}
}

// ======================================================================

class Ordinateur extends Joueur {

	public Ordinateur(int couleur) {
		super("Le programme", couleur);
	}

	public void joue(Jeu jeu) {
		for (int col = 0; col < jeu.getTaille(); col++) {
			if (jeu.joueCoup(col, this.getCouleur())) {
				System.out.println(this.getNom() + " a jou� en " + (col + 1));
				return;
			}
		}
	}
}