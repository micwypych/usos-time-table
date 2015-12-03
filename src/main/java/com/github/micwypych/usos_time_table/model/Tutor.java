package com.github.micwypych.usos_time_table.model;

public class Tutor {
	
	private String name;
	private String surname;
	private Title title;
	private int usosId;
	
	public Tutor(String name,String surname,Title title,int usosId) {
		this.name = name;
		this.surname = surname;
		this.title = title;
		this.usosId = usosId; 
	}

	
}
