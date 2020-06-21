package com.bong.springboot.controller;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.bong.springboot.bean.Customer;
import com.bong.springboot.bean.Debate;
//import com.bong.springboot.dao.DBMapper;

@RestController
public class Controller {

//	  @Autowired
//	  DBMapper mapper;
//	  @CrossOrigin(origins = "http://localhost:8080")
//	  @RequestMapping("/db")
//	  public ModelAndView selectList(ModelAndView model) {
//		  List<Customer> resultList = mapper.selectList();
//		  System.out.println(resultList);
//		  //model.addAttribute("list",resultList);
//		  model.setViewName("index");
//		  model.addObject("message",resultList);
//		  
//		  return model;
//	  }
	  @Autowired 
	  MongoTemplate mongoTemplate;
	  
	  @CrossOrigin(origins = "http://localhost:8080")
	  @RequestMapping("/getDebate")
	  public List<Debate> getDebate(ModelAndView model) {
//		  List<Debate> resultList = mapper.selectDebate();
		  Debate debate = new Debate();
		  Query query=new Query(new Criteria("No").is(2));
		  List<Debate> resultList = new ArrayList<Debate>();
		  Debate d = new Debate();
		  d.setNo(1);
		  d.setTitle("1");
		  d.setContent("1");
//		  mongoTemplate.insert(d,"debate");
		  resultList = mongoTemplate.find(query, Debate.class, "debate");
		  
		  return resultList;
	  }
	  
	  @CrossOrigin(origins = "http://localhost:8080")
	  @RequestMapping(value = "/addDebate", method=RequestMethod.POST)
	  public ModelAndView addDebate(HttpServletRequest req) {
		  String url = "http://localhost:8080/debate";
		  String title = req.getParameter("title");
		  String content = req.getParameter("content");
		  System.out.println(title);
		  Debate debate = new Debate(0,title,content);
//		  mapper.addDebate(debate);
		  return new ModelAndView("redirect:" + url);
	  }
	  @CrossOrigin(origins = "http://localhost:8080")
	  @RequestMapping(value = "/addDebate", method=RequestMethod.GET)
	  public void getAddDebate(HttpServletRequest req) {
		  System.out.println("get");
		  return;
	  }
	  
	  @CrossOrigin(origins = "http://localhost:8080")
	  @RequestMapping(value = "/getDebateContent", method=RequestMethod.POST)
	  public Debate getDebateContent(HttpServletRequest req) {
		  int no;
//		  Enumeration<?> e = req.getParameterNames();
//		  while(e.hasMoreElements()) {
//			  String name = (String) e.nextElement();
//				String[] values = req.getParameterValues(name);		
//				for (String value : values) {
//					System.out.println("name=" + name + ",value=" + value);
//				}   
//			}
		  String noStr = (req.getParameter("bNo"));
		  System.out.println(noStr);
		  
		  if(noStr == null)
			  return null;
		  else
			  no = Integer.parseInt(noStr);
		  Query query=new Query(new Criteria("No").is(no));
//		  Debate debate = mapper.getDebateContent(no);
		  Debate debate = mongoTemplate.findOne(query, Debate.class);
		  return debate;
	  }
//	  @CrossOrigin(origins = "http://localhost:8080")
//	  @RequestMapping(value = "/getDebateContent", method=RequestMethod.GET)
//	  public void getDebateContentGet(HttpServletRequest req) {
//		  System.out.println("get");
//		  return;
//	  }
	}

