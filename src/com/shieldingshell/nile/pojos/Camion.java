package com.shieldingshell.nile.pojos;

import java.io.Serializable;

public enum Camion implements Serializable{

	TYPE_S(1, 8), TYPE_M(3, 32), TYPE_XL(4, 48);

	private int hauteur;
	private int place;
	private Camion(int hauteur, int place) {
		this.hauteur = hauteur;
		this.place = place;
	}
	public int getHauteur() {
		return hauteur;
	}
	public void setHauteur(int hauteur) {
		this.hauteur = hauteur;
	}
	public int getPlace() {
		return place;
	}
	public void setPlace(int place) {
		this.place = place;
	}

	

}
