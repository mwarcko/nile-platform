package com.shieldingshell.nile.pojos;

import java.io.Serializable;
import java.util.List;

public class Commande implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<CartonID> cartons;
	private String commandeName;

	public Commande() {
		
	}

	public Commande(String commandeName) {
		this.commandeName = commandeName;
	}
	
	public Commande(List<CartonID> cartons, String commandeName) {
		super();
		this.cartons = cartons;
		this.commandeName = commandeName;
	}

	public String getCommandeName() {
		return commandeName;
	}

	public void setCommandeName(String commandeName) {
		this.commandeName = commandeName;
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
