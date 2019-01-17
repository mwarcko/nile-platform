package com.shieldingshell.nile.pojos;

import java.io.Serializable;

public class CartonID implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Carton carton;
	private Long idCarton;

	public CartonID(Carton carton, Long idCarton) {
		super();
		this.carton = carton;
		this.idCarton = idCarton;
	}

	public Carton getCarton() {
		return carton;
	}

	public void setCarton(Carton carton) {
		this.carton = carton;
	}

	public Long getIdCarton() {
		return idCarton;
	}

	public void setIdCarton(Long idCarton) {
		this.idCarton = idCarton;
	}

	@Override
	public String toString() {
		return "CartonID [carton=" + carton + ", idCarton=" + idCarton + "]";
	}

}
