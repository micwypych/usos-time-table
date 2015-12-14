package com.github.micwypych.usos_time_table.usos_gateways;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;

import com.github.micwypych.usos_time_table.model.usosaccess.JSONCourseSearchItems;
import com.github.micwypych.usos_time_table.model.usosaccess.JSONCourseSearchItems.CourseSearchCourse;

public class UsosCourseIDByNameGetter {
	private UsosWebJsonReader<JSONCourseSearchItems> getter;
	private String service;
	private HashMap<String,String> queries;
	private Integer numberOfResultsPerPage = 20;
	
	public UsosCourseIDByNameGetter(String webservice) {
		getter = new UsosWebJsonReader<>(webservice, JSONCourseSearchItems.class);
		service = "services/courses/search";
		queries = new HashMap<>();
		//https://apps.usos.uj.edu.pl/developers/api/services/courses/#courses
		queries.put("lang", "pl");
		//maximum returned results per request - 20 is maximal value
		queries.put("num", numberOfResultsPerPage.toString());
//		//return results only for faculty
//		queries.put("fac_id", "??");
//		//for faculty and its subdivisions
//		queries.put("fac_deep", "true");
	}
	
	public String getMostProbableResult(String courseName) throws IOException {
		HashMap<String, String> result = get(courseName);
		ArrayList<ScoreResult> scores = new ArrayList<>();
		for( Entry<String,String> entry : result.entrySet() ) {
			ScoreResult s = new ScoreResult();
			s.setScore(entry.getValue());
			s.courseID = entry.getKey();
			scores.add(s);
		}
		Collections.sort(scores);
		return scores.get(0).courseID;
	}
	
	private static class ScoreResult implements Comparable<ScoreResult>{
		public int score;
		public String courseID;
		
		public void setScore(String queryResult) {
			score = Iterables.size(Splitter.on("<b>").split(queryResult)) - 1;
		}

		@Override
		public int compareTo(ScoreResult o) {
			return -Integer.compare(score, o.score);
		}
	}
	
	public HashMap<String,String> get(String courseName) throws IOException {
		HashMap<String, String> result = new HashMap<String, String>();
		Integer start = 0;
		while( putResultsInto(result,courseName,start) ) {
			start = start+numberOfResultsPerPage ;
		}
		return result;
	}
	
	public JSONCourseSearchItems getRawData(String courseName,Integer start) throws IOException {
		HashMap<String, String> queries = (HashMap<String, String>) this.queries.clone();
		queries.put("start", start.toString());
		queries.put("name", courseName);
		return getter.readJsonFromService(service, queries);
	}
	
	private boolean putResultsInto(HashMap<String, String> result,String courseName,Integer start) throws IOException {
		JSONCourseSearchItems items = getRawData(courseName,start);
		for( CourseSearchCourse item : items.items ) {
			result.put(item.courseID,item.match);
		}
		return items.nextPage;
	}
}
