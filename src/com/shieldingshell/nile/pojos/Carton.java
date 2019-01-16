package com.shieldingshell.nile.pojos;

public class Carton {
	private Long largeurCarton;
	private Long longueurCarton;

	public Carton(Long largeurCarton, Long longueurCarton) {
		super();
		this.largeurCarton = largeurCarton;
		this.longueurCarton = longueurCarton;
	}

	public final Long getLargeurCarton() {
		return largeurCarton;
	}

	public final void setLargeurCarton(Long largeurCarton) {
		this.largeurCarton = largeurCarton;
	}

	public final Long getLongueurCarton() {
		return longueurCarton;
	}

	public final void setLongueurCarton(Long longueurCarton) {
		this.longueurCarton = longueurCarton;
	}

	@Override
	public String toString() {
		return "Carton [largeurCarton=" + largeurCarton + ", longueurCarton=" + longueurCarton + "]";
	}

}
