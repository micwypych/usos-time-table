package com.github.micwypych.usos_time_table.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.github.micwypych.usos_time_table.model.Tutor;
import com.github.micwypych.usos_time_table.service.UsosTimeTableService;


@Controller
@RequestMapping("/tutors")
public class TutorController {
	
	private UsosTimeTableService service;
	
	public TutorController(UsosTimeTableService service) {
		this.service = service;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView index() {
		List<Tutor> tutors = service.findAllTutors();
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("tutors", tutors);
		return mv;
	}
}