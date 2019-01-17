package com.shieldingshell.nile.pojos;

import java.io.Serializable;

public enum Camion implements Serializable{

	TYPE_S(600L, 120L, 1), TYPE_M(900L, 120L, 2), TYPE_L(1200L, 240L, 3), TYPE_XL(1500L, 240L, 4);

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
