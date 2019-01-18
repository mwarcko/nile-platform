package com.shieldingshell.nile.pojos;

import java.io.Serializable;

public enum Carton implements Serializable {

	TYPE_S(60L, 150L,1), TYPE_M(60L, 300L,2), TYPE_L(120L, 300L,4), TYPE_XL(120L, 600L,8);

	private Long largeurCarton;
	private Long longueurCarton;
	private int place;

	private Carton(Long largeurCarton, Long longueurCarton, int place) {
		this.largeurCarton = largeurCarton;
		this.longueurCarton = longueurCarton;
		this.place = place;
	}

	public int getPlace() {
		return place;
	}

	public void setPlace(int place) {
		this.place = place;
	}

	public Long getLargeurCarton() {
		return largeurCarton;
	}

	public void setLargeurCarton(Long largeurCarton) {
		this.largeurCarton = largeurCarton;
	}

	public Long getLongueurCarton() {
		return longueurCarton;
	}

	public void setLongueurCarton(Long longueurCarton) {
		this.longueurCarton = longueurCarton;
	}

}
