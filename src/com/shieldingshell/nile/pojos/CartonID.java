package com.shieldingshell.nile.pojos;

import java.io.Serializable;

public class CartonID implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Carton carton;
	private Long idCarton;
	private int story;

	public CartonID(Carton carton, Long idCarton) {
		super();
		this.carton = carton;
		this.idCarton = idCarton;
	}

	public CartonID(Carton carton, Long idCarton, int story) {
		super();
		this.carton = carton;
		this.idCarton = idCarton;
		this.story = story;
	}

	public int getStory() {
		return story;
	}

	public void setStory(int story) {
		this.story = story;
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
