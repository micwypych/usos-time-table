package com.github.micwypych.usos_time_table.model.usosaccess;

import java.util.ArrayList;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonDeserialize;

public class JSONCourseEditionTimeTablesAllFields extends
		JSONCourseEditionTimeTables {
	@JsonProperty("building_id")
	public String buildingID;
	@JsonProperty("classtype_name")
	public JSONTTCourseEditionName classTypeName;
	@JsonProperty("group_number")
	public int groupNumber;
	@JsonProperty("room_number")
	public String room_number;
	@JsonProperty("unit_id")
	public int unitID;
	@JsonProperty("lecturer_ids")
	@JsonDeserialize(as=ArrayList.class, contentAs=Integer.class)
	public ArrayList<Integer> lecturersIDs;
	public String frequency;
	@JsonProperty("room_id")
	public int roomID;
	@JsonProperty("classtype_id")
	public String classTypeID;
	@JsonProperty("classgroup_profile_url")
	public String classGroupProfileURL;
	public String type;
}
