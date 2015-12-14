package com.github.micwypych.usos_time_table.web;

import java.util.Arrays;
import java.util.Collection;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.github.micwypych.usos_time_table.model.Title;
import com.github.micwypych.usos_time_table.model.Tutor;
import com.github.micwypych.usos_time_table.service.UsosTimeTableService;

@Controller
public class TutorController {

	private UsosTimeTableService service;

	@Autowired
	public TutorController(UsosTimeTableService service) {
		this.service = service;
		LoggerFactory.getLogger(this.getClass()).info("TUTOR CONTROLLER CREATED");
	}

	@RequestMapping(path = "/tutors", method = RequestMethod.GET)
	public ModelAndView index() {
		Collection<Tutor> tutors = service.findAllTutors();
		ModelAndView mv = new ModelAndView("tutors/index");
		mv.addObject("tutors", tutors);
		return mv;
	}
	
	@RequestMapping(path = "/tutors/new", method = RequestMethod.GET)
	public ModelAndView newTutor() {
		Tutor tutor = new Tutor("","",Title.UNKNOWN,0);
		ModelAndView mv = new ModelAndView("tutors/new");
		mv.addObject("tutor",tutor);
		mv.addObject("isNew",true);
		mv.addObject("allTitles",Title.values());
		return mv;
	}
	
//	@RequestMapping(path = "/tutors/new", method = RequestMethod.POST, headers="content-type=application/x-www-form-urlencoded")
//	public String create(@RequestBody Tutor tutor) {
//		service.create(tutor);
//		return "tutors";
//	}
	
	@RequestMapping(path = "/tutors/new", method = RequestMethod.POST, headers="content-type=application/x-www-form-urlencoded")
	public String create(@RequestBody MultiValueMap<String,String> body) {
		String names[] = body.get("name").get(0).split("\\s+");
		Title title = Title.fromString(body.get("title").get(0));
		Integer usosId = Integer.valueOf(body.get("usosId").get(0));
		LoggerFactory.getLogger(this.getClass()).info(Arrays.toString(names));
		LoggerFactory.getLogger(title.toString());
		LoggerFactory.getLogger(""+usosId);
		Tutor tutor = new Tutor(names[0],names[1],title,usosId);
		service.create(tutor);
		return "redirect:/tutors";
	}
	
	@RequestMapping(path = "/tutors/{tutorId}", method = RequestMethod.GET)
	public ModelAndView show(@PathVariable("tutorId") Integer tutorId) {
		Tutor tutor = service.findById(tutorId);
		ModelAndView mv = new ModelAndView("tutors/show");
		mv.addObject("tutor", tutor);
		return mv;
	}
	
	@RequestMapping(path = "/tutors/{tutorId}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable("tutorId")Integer tutorId) {
		Tutor tutor = service.findById(tutorId);
		ModelAndView mv = new ModelAndView("tutors/edit");
		mv.addObject("tutor", tutor);
		return mv;
	}
	
	@RequestMapping(path = "/tutors/{tutorId}", method = RequestMethod.PUT)
	public String update() {
		return "tutors";
	}
	
	@RequestMapping(path = "/tutors/{tutorId}", method = RequestMethod.DELETE)
	public String destroy() {
		return "tutors";
	}
	
}