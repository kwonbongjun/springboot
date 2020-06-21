package com.bong.springboot.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.bong.springboot.bean.Board;
import com.bong.springboot.bean.Debate;
import com.bong.springboot.service.SequenceGeneratorService;

@RestController
public class BoardController {
	 @Autowired 
	  MongoTemplate mongoTemplate;
	  
	  @CrossOrigin(origins = "http://localhost:8080")
	  @RequestMapping("/getBoard")
	  public List<Board> getDebate(ModelAndView model) {
//		  List<Debate> resultList = mapper.selectDebate();
//		  Board debate = new Debate();
//		  Query query=new Query(new Criteria("No").is(2));
		  Query query=new Query();
		  List<Board> resultList = new ArrayList<Board>();
		  Debate d = new Debate();
		  
//		  mongoTemplate.insert(d,"debate");
//		  resultList = mongoTemplate.find(query, Board.class, "Board");
		  resultList = mongoTemplate.findAll(Board.class, "Board");
//		  resultList = mongoTemplate.find(query, Board.class);
		  return resultList;
	  }
	  
	  @CrossOrigin(origins = "http://localhost:8080")
	  @RequestMapping(value = "/addBoard", method=RequestMethod.POST)
	  public void addDebate(HttpServletRequest req) {
		  String url = "http://localhost:8080/board";
//		  String no = req.getParameter("no");
		  String title = req.getParameter("title");
		  String content = req.getParameter("content");
		  System.out.println(title);
		  
		  Board board = new Board(title,content);
		  board.setNo(SequenceGeneratorService.generateSequence(Board.SEQUENCE_NAME));
//		  mapper.addDebate(debate);
		  mongoTemplate.insert(board,"Board");
//		  return new ModelAndView("redirect:" + url);
	  }
	  
	  @CrossOrigin(origins = "http://localhost:8080")
	  @RequestMapping(value = "/addBoard", method=RequestMethod.GET)
	  public void getAddDebate(HttpServletRequest req) {
		  System.out.println("get");
		  return;
	  }
	  
//	  @CrossOrigin(origins = "http://localhost:8080")
//	  @RequestMapping(value = "/getDebateContent", method=RequestMethod.POST)
//	  public Debate getDebateContent(HttpServletRequest req) {
//		  int no;
////		  Enumeration<?> e = req.getParameterNames();
////		  while(e.hasMoreElements()) {
////			  String name = (String) e.nextElement();
////				String[] values = req.getParameterValues(name);		
////				for (String value : values) {
////					System.out.println("name=" + name + ",value=" + value);
////				}   
////			}
//		  String noStr = (req.getParameter("bNo"));
//		  System.out.println(noStr);
//		  
//		  if(noStr == null)
//			  return null;
//		  else
//			  no = Integer.parseInt(noStr);
//		  Query query=new Query(new Criteria("No").is(no));
////		  Debate debate = mapper.getDebateContent(no);
//		  Debate debate = mongoTemplate.findOne(query, Debate.class);
//		  return debate;
//	  }
//	  @CrossOrigin(origins = "http://localhost:8080")
//	  @RequestMapping(value = "/getDebateContent", method=RequestMethod.GET)
//	  public void getDebateContentGet(HttpServletRequest req) {
//		  System.out.println("get");
//		  return;
//	  }
}
