package com.github.micwypych.usos_time_table.model;

public enum Title {
	DR_HAB("dr hab."),
	DR("dr"),
	PROF_DR_HAB("prof. dr hab."),
	MGR("mgr"),
	UNKNOWN(""),
	DR_INZ("dr inż."),
	MGR_INZ("mgr inż.");
	
	private Title(String polishTitle) {
		this.textValue = polishTitle;
	}
	
	private String textValue;
	
	@Override
	public String toString() {
		return textValue;
	}
}
