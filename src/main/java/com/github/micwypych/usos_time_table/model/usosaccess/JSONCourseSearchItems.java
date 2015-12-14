package com.github.micwypych.usos_time_table.model.usosaccess;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonDeserialize;

public class JSONCourseSearchItems {
	@JsonDeserialize(as=ArrayList.class, contentAs=CourseSearchCourse.class)
	public List<CourseSearchCourse> items;
	@JsonProperty("next_page")
	public boolean nextPage;
	
	public static class CourseSearchCourse {
		@JsonProperty("course_id")
		public String courseID;
		public String match;
	}
}
