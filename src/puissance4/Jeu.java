package puissance4;

class Jeu {

	public final static int VIDE = 0;
	public final static int BLEU = 1;
	public final static int ROUGE = 2;

	private int taille;
	private int[][] grille; // 0 = vide, 1 = joueur bleu, 2 = joueur rouge

	public Jeu(int taille) {
		initJeu(taille);
	}

	public Jeu() {
		initJeu(8);
	}

	private void initJeu(int taille) {
		this.taille = taille;
		grille = new int[taille][taille];
		for (int col = 0; col < taille; col++) {
			for (int row = 0; row < taille; row++) {
				grille[col][row] = VIDE;
			}
		}
	}

	public boolean joueCoup(int col, int joueur) {
		if ((col < 0) || (col >= taille)) {
			return false;
		}

		// Trouve la premi�re place vide dans la colonne
		for (int ligne = 0; ligne < taille; ligne++) {
			if (grille[col][ligne] == VIDE) {
				grille[col][ligne] = joueur;
				return true;
			}
		}
		// La colonne est pleine
		return false;
	}

	/**
	 * Cette m�thode v�rifie toutes les lignes, colonnes et diagonales pour une
	 * s�rie de 4 pions de la m�me couleur. Si une telle s�rie existe, retourne
	 * true.
	 *
	 * Notez qu'il n'est pas n�cessaire de retourner la couleur des 4 pions
	 * align�s, puisqu'il s'agit de celle de celui qui vient de jouer.
	 * 
	 * @return true si le jeu contient 4 pions align�s
	 */
	public boolean cherche4() {
		// V�rifie les horizontales ( - )
		for (int ligne = 0; ligne < taille; ligne++) {
			if (cherche4alignes(0, ligne, 1, 0)) {
				return true;
			}
		}

		// V�rifie les verticales ( � )
		for (int col = 0; col < taille; col++) {
			if (cherche4alignes(col, 0, 0, 1)) {
				return true;
			}
		}

		// Diagonales (cherche depuis la ligne du bas)
		for (int col = 0; col < taille; col++) {
			// Premi�re diagonale ( / )
			if (cherche4alignes(col, 0, 1, 1)) {
				return true;
			}
			// Deuxi�me diagonale ( \ )
			if (cherche4alignes(col, 0, -1, 1)) {
				return true;
			}
		}

		// Diagonales (cherche depuis les colonnes gauches et droites)
		for (int ligne = 0; ligne < taille; ligne++) {
			// Premi�re diagonale ( / )
			if (cherche4alignes(0, ligne, 1, 1)) {
				return true;
			}
			// Deuxi�me diagonale ( \ )
			if (cherche4alignes(taille - 1, ligne, -1, 1)) {
				return true;
			}
		}

		// On n'a rien trouv�
		return false;
	}

	/**
	 * Cette m�thode cherche 4 pions align�s sur une ligne. Cette ligne est
	 * d�finie par le point de d�part, ou origine de coordonn�es (oCol,oLigne),
	 * et par le d�placement delta (dCol,dLigne). En utilisant des valeurs
	 * appropri�es pour dCol et dLigne on peut v�rifier toutes les directions: -
	 * horizontale: dCol = 0, dLigne = 1 - v�rticale: dCol = 1, dLigne = 0 -
	 * 1�re diagonale: dCol = 1, dLigne = 1 - 2�me diagonale: dCol = 1, dLigne =
	 * -1
	 *
	 * @param oCol
	 *            Colonne d'origine de la recherche
	 * @param oLigne
	 *            Ligne d'origine de la recherche
	 * @param dCol
	 *            Delta de d�placement sur une colonne
	 * @param dLigne
	 *            Delta de d�placement sur une ligne
	 * @return true si on trouve un alignement
	 */
	private boolean cherche4alignes(int oCol, int oLigne, int dCol, int dLigne) {
		int couleur = VIDE;
		int compteur = 0;

		int curCol = oCol;
		int curRow = oLigne;

		while ((curCol >= 0) && (curCol < taille) && (curRow >= 0) && (curRow < taille)) {
			if (grille[curRow][curCol] != couleur) {
				// Si la couleur change, on r�initialise le compteur
				couleur = grille[curRow][curCol];
				compteur = 1;
			} else {
				// Sinon on l'incr�mente
				compteur++;
			}

			// On sort lorsque le compteur atteint 4
			if ((couleur != VIDE) && (compteur == 4)) {
				return true;
			}

			// On passe � l'it�ration suivante
			curCol += dCol;
			curRow += dLigne;
		}

		// Aucun alignement n'a �t� trouv�
		return false;
	}

	/**
	 * V�rifie s'il est encore possible de placer des pions
	 * 
	 * @return true si le tableau est plein
	 */
	public boolean estPlein() {
		// On cherche une case vide. S'il n'y en a aucune, le tableau est plein
		for (int col = 0; col < taille; col++) {
			for (int ligne = 0; ligne < taille; ligne++) {
				if (grille[col][ligne] == VIDE) {
					return false;
				}
			}
		}

		return true;
	}

	public int getTaille() {
		return taille;
	}

	public void afficher() {
	    for (int ligne = taille - 1; ligne >= 0; --ligne) {
	      for (int col = 0; col < taille; col++) {
	        switch (grille[col][ligne]) {
	        case 0:
	        	 System.out.print(" x ");   break;
	        case ROUGE:
	          System.out.print(" R ");
	          break;
	        case BLEU:
	          System.out.print(" B ");
	          break;
	        }
	      }
	      System.out.println();
	    }
	 
	    for (int i = 0; i < taille; ++i) {
	      System.out.print('-');
	    }
	    System.out.println();
	    for (int i = 1; i <= taille; ++i) {
	      System.out.print(i);
	    }
	    System.out.println();
	  }
}