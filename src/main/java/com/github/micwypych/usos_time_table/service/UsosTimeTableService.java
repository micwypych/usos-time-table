package com.github.micwypych.usos_time_table.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.github.micwypych.usos_time_table.model.Title;
import com.github.micwypych.usos_time_table.model.Tutor;
import com.github.micwypych.usos_time_table.usos_gateways.UsosCourseIDByNameGetter;

@Service
public class UsosTimeTableService {
	
	private static class TutorRegistry {
		
		private Map<Integer,Tutor> tutors = new HashMap<>();
		
		private TutorRegistry() {
			int i = 1;
			for( Tutor t : new Tutor[] {
				new Tutor("Marian","Kowalski",Title.DR,10000),
				new Tutor("Stefan","Nowak",Title.DR_HAB,10001),
				new Tutor("Anna","Węgieł",Title.PROF_DR_HAB,0)
			}) {
				tutors.put(i++, t);
			}
		}
		
		
		public Collection<Tutor> getAll() {
			return tutors.values();
		}
		
		public Tutor findById(Integer id) {
			return tutors.get(id);
		}


		public void create(Tutor tutor) {
			tutors.put(newKey(), tutor);
		}


		private Integer newKey() {
			int i = 1;
			while( tutors.containsKey(i) ) {
				i++;
			}
			return i;
		}
	}
	
	private TutorRegistry registry = new TutorRegistry();

	public Collection<Tutor> findAllTutors() {
		return registry.getAll();
	}

	public Tutor findById(Integer tutorId) {
		return registry.findById(tutorId);
	}

	public void create(Tutor tutor) {
		registry.create(tutor);
	}

	
}
