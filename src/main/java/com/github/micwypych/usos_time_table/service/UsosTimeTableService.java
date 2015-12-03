package com.github.micwypych.usos_time_table.service;

import java.util.Arrays;
import java.util.List;

import com.github.micwypych.usos_time_table.model.Title;
import com.github.micwypych.usos_time_table.model.Tutor;

public class UsosTimeTableService {

	public List<Tutor> findAllTutors() {
		return Arrays.asList(new Tutor[] {
			new Tutor("Marian","Kowalski",Title.DR,10000),
			new Tutor("Stefan","Nowak",Title.DR_HAB,10001)
		});
	}

}
