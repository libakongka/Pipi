package com.example.oauthsession.dto;

public enum CategorySection {

	A("OUTER"),
	B("TOP"),
	C("BOTTOM"),
	D("BAG&SHOES"),
	E("ACC");
	
	private final String label;
	
	CategorySection(String label) {
		this.label = label;
	}
	
	public String getLabel() {
		return label;
	}
}
