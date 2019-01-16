package com.shieldingshell.nile.pojos;

import java.util.List;

public class Commande {
	List<Carton> cartons;

	public Commande(List<Carton> cartons) {
		super();
		this.cartons = cartons;
	}

	public final List<Carton> getCartons() {
		return cartons;
	}

	public final void setCartons(List<Carton> cartons) {
		this.cartons = cartons;
	}

	@Override
	public String toString() {
		return "Commande [cartons=" + cartons + "]";
	}
}
