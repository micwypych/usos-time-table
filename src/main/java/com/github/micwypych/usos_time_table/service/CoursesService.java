package com.github.micwypych.usos_time_table.service;

import java.io.IOException;
import java.util.HashMap;

import com.github.micwypych.usos_time_table.usos_gateways.UsosCourseIDByNameGetter;

public class CoursesService {
	public  HashMap<String, String> usosFindSimilarCoursesByName(String courseName) throws IOException {
		UsosCourseIDByNameGetter getter = new UsosCourseIDByNameGetter("https://apps.usos.uj.edu.pl");
		return getter.get(courseName);
	}
}
