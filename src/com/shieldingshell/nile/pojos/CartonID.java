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
	private int space;

	public CartonID(Carton carton, Long idCarton, int story, int space) {
		super();
		this.carton = carton;
		this.idCarton = idCarton;
		this.story = story;
		this.space = space;
	}

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

	public int getSpace() {
		return space;
	}

	public void setSpace(int space) {
		this.space = space;
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
		return "CartonID [carton=" + carton + ", idCarton=" + idCarton + ", story=" + story + ", space=" + space + "]";
	}

}
