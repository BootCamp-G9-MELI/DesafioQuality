package br.com.meli.seu.imovel.dto;

import java.math.BigDecimal;

public class PropertyPriceDTO {

	private DistrictDTO districtDTO;
	private String nameProperty;
	private double area;
	private BigDecimal price;
	
	public PropertyPriceDTO(DistrictDTO districtDTO, String nameProperty, double area, BigDecimal price) {
		super();
		this.districtDTO = districtDTO;
		this.nameProperty = nameProperty;
		this.area = area;
		this.price = price;
	}

	public DistrictDTO getDistrictDTO() {
		return districtDTO;
	}

	public void setDistrictDTO(DistrictDTO districtDTO) {
		this.districtDTO = districtDTO;
	}

	public String getNameProperty() {
		return nameProperty;
	}

	public void setNameProperty(String nameProperty) {
		this.nameProperty = nameProperty;
	}

	public double getArea() {
		return area;
	}

	public void setArea(double area) {
		this.area = area;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	
}
