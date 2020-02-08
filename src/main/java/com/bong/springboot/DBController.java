package com.bong.springboot;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class DBController {

	  @Autowired
	  DBMapper mapper;
	  @CrossOrigin(origins = "http://localhost:8081")
	  @RequestMapping("/db")
	  public ModelAndView selectList(ModelAndView model) {
		  List<Customer> resultList = mapper.selectList();
		  System.out.println(resultList);
		  //model.addAttribute("list",resultList);
		  model.setViewName("index");
		  model.addObject("message",resultList);
		  
		  return model;
	  }
	}

