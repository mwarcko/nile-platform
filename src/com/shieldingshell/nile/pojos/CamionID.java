package com.shieldingshell.nile.pojos;

import java.util.List;

public class CamionID {

	private Camion camion;
	private List<CartonID> cartons;

	public CamionID(Camion camion, List<CartonID> cartons) {
		super();
		this.camion = camion;
		this.cartons = cartons;
	}

	public Camion getCamion() {
		return camion;
	}

	public void setCamion(Camion camion) {
		this.camion = camion;
	}

	public List<CartonID> getCartons() {
		return cartons;
	}

	public void setCartons(List<CartonID> cartons) {
		this.cartons = cartons;
	}

	@Override
	public String toString() {
		return "CamionID [camion=" + camion + ", cartons=" + cartons + "]";
	}

}
