package com.shieldingshell.nile.pojos;

import java.io.Serializable;
import java.util.List;

public class Commande implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<CartonID> cartons;

	public Commande() {
		
	}
	
	public Commande(List<CartonID> cartons) {
		super();
		this.cartons = cartons;
	}

	public final List<CartonID> getCartons() {
		return cartons;
	}

	public final void setCartons(List<CartonID> cartons) {
		this.cartons = cartons;
	}

	@Override
	public String toString() {
		return "Commande [cartons=" + cartons + "]";
	}
}
