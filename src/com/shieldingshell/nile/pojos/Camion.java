package com.shieldingshell.nile.pojos;

public class Camion {
	private Long largeurCamion;
	private Long longueurCamion;
	private int hauteurCamion;
	private String typeCamion;

	public Camion(Long largeurCamion, Long longueurCamion, int hauteurCamion, String typeCamion) {
		super();
		this.largeurCamion = largeurCamion;
		this.longueurCamion = longueurCamion;
		this.hauteurCamion = hauteurCamion;
		this.typeCamion = typeCamion;
	}

	public final Long getLargeurCamion() {
		return largeurCamion;
	}

	public final void setLargeurCamion(Long largeurCamion) {
		this.largeurCamion = largeurCamion;
	}

	public final Long getLongueurCamion() {
		return longueurCamion;
	}

	public final void setLongueurCamion(Long longueurCamion) {
		this.longueurCamion = longueurCamion;
	}

	public final int getHauteurCamion() {
		return hauteurCamion;
	}

	public final void setHauteurCamion(int hauteurCamion) {
		this.hauteurCamion = hauteurCamion;
	}

	public final String getTypeCamion() {
		return typeCamion;
	}

	public final void setTypeCamion(String typeCamion) {
		this.typeCamion = typeCamion;
	}

	@Override
	public String toString() {
		return "Camion [largeurCamion=" + largeurCamion + ", longueurCamion=" + longueurCamion + ", hauteurCamion="
				+ hauteurCamion + ", typeCamion=" + typeCamion + "]";
	}

}
