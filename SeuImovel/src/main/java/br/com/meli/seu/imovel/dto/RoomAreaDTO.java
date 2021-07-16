package br.com.meli.seu.imovel.dto;

public class RoomAreaDTO {
	private String name;
	private double aream2;
	
	public RoomAreaDTO(String name, double aream2) {
		super();
		this.name = name;
		this.aream2 = aream2;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getAream2() {
		return aream2;
	}

	public void setAream2(double aream2) {
		this.aream2 = aream2;
	}

}
