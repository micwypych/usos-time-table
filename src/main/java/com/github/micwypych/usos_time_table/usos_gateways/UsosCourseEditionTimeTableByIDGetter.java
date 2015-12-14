package com.github.micwypych.usos_time_table.usos_gateways;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.github.micwypych.usos_time_table.model.usosaccess.JSONCourseEditionTimeTablesAllFields;

public class UsosCourseEditionTimeTableByIDGetter {
	private UsosWebJsonReader<ArrayList<JSONCourseEditionTimeTablesAllFields>> getter;
	private String service;
	private HashMap<String,String> queries;
	private Integer numberOfResultsPerPage = 20;
	
	public UsosCourseEditionTimeTableByIDGetter(String webservice) {
		getter = new UsosWebJsonReader<>(webservice, ArrayList.class, JSONCourseEditionTimeTablesAllFields.class);
		service = "services/tt/course_edition";
		queries = new HashMap<>();
		//https://apps.usos.uj.edu.pl/developers/api/services/tt/#course_edition
		queries.put("fields", "start_time|end_time|name"
				+ "|type|classtype_name|lecturer_ids|group_number"
				+ "|classgroup_profile_url|building_id|room_number"
				+ "|room_id|classtype_id|frequency|unit_id");
	}
	
	public ArrayList<JSONCourseEditionTimeTablesAllFields> get(String courseID,String term_id,String startDate) throws IOException {
		ArrayList<JSONCourseEditionTimeTablesAllFields> result = new ArrayList<JSONCourseEditionTimeTablesAllFields>();
		Integer start = 0;
		while( putResultsInto(result,courseID,term_id,startDate,start) ) {
			start = start+numberOfResultsPerPage ;
		}
		return result;
	}
	
	public ArrayList<JSONCourseEditionTimeTablesAllFields> getRawData(String courseID,String termID,String startDate,Integer start) throws IOException {
		HashMap<String, String> queries = (HashMap<String, String>) this.queries.clone();
		queries.put("course_id", courseID);
		queries.put("term_id", termID);
		queries.put("start", startDate);
		return getter.readJsonFromService(service, queries);
	}
	
	private boolean putResultsInto(ArrayList<JSONCourseEditionTimeTablesAllFields> result,String courseID,String term_id,String startDate,Integer start) throws IOException {
		ArrayList<JSONCourseEditionTimeTablesAllFields> items = getRawData(courseID,term_id,startDate,start);
		result.addAll(items);
		return false;
	}
}
