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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Title getTitle() {
		return title;
	}

	public void setTitle(Title title) {
		this.title = title;
	}

	public int getUsosId() {
		return usosId;
	}

	public void setUsosId(int usosId) {
		this.usosId = usosId;
	}
	
}
