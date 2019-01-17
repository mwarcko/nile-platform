package com.shieldingshell.nile.pojos;

import java.io.Serializable;

public enum Carton implements Serializable{

	TYPE_S(60L, 150L), TYPE_M(60L, 300L), TYPE_L(120L, 300L), TYPE_XL(120L, 600L);

	private Long largeurCarton;
	private Long longueurCarton;

	private Carton(Long largeurCarton, Long longueurCarton) {
		this.largeurCarton = largeurCarton;
		this.longueurCarton = longueurCarton;
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
