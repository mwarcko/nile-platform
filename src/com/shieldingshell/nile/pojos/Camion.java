package com.shieldingshell.nile.pojos;

public enum Camion {
	
	TYPE_S(250L,180L,1),
	TYPE_M(350L,200L,2),
	TYPE_L(400L,220L,3),
	TYPE_XL(550L,230L,4);
	
	private Long longueur;
	private Long largeur;
	private int hauteur;
	
	private Camion(Long longueur, Long largeur, int hauteur) {
		this.longueur = longueur;
		this.largeur = largeur;
		this.hauteur = hauteur;
	}

	public final Long getLongueur() {
		return longueur;
	}

	public final void setLongueur(Long longueur) {
		this.longueur = longueur;
	}

	public final Long getLargeur() {
		return largeur;
	}

	public final void setLargeur(Long largeur) {
		this.largeur = largeur;
	}

	public final int getHauteur() {
		return hauteur;
	}

	public final void setHauteur(int hauteur) {
		this.hauteur = hauteur;
	}

}
