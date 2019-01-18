package com.shieldingshell.nile.pojos;

import java.io.Serializable;
import java.util.List;

public class CamionID implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Camion camion;
	private List<CartonID> cartons;

	public CamionID(Camion camion, List<CartonID> cartons) {
		super();
		this.camion = camion;
		this.cartons = cartons;
	}
	
	public CamionID(Camion camion) {
		super();
		this.camion = camion;
	}
	public CamionID() {
		super();
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
