package com.github.micwypych.usos_time_table.web;

import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Controller
public class EndpointDocController {
	 @Autowired
	 private RequestMappingHandlerMapping handlerMapping;
		 
	
	 @RequestMapping(value="/endpointdoc", method=RequestMethod.GET)
	 public void show(Model model) {
		 for( Entry<RequestMappingInfo, HandlerMethod> e: this.handlerMapping.getHandlerMethods().entrySet() ) {
			 System.out.println(e.getKey());
		 	 System.out.println(e.getValue());
		 }
			 
		 //model.addAttribute("handlerMethods", this.handlerMapping.getHandlerMethods());
	 } 
	}