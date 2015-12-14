package com.github.micwypych.usos_time_table.model.usosaccess;

import org.codehaus.jackson.annotate.JsonProperty;

public class JSONCourseEditionTimeTables {
	@JsonProperty("start_time")
	public String startTime;
	@JsonProperty("end_time")
	public String endTime;
	public JSONTTCourseEditionName name;
	
	public static class JSONTTCourseEditionName {
		@JsonProperty("en")
		public String enName;
		@JsonProperty("pl")
		public String plName;
	}
}
