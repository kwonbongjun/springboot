package com.bong.springboot.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bong.springboot.bean.Customer;
import com.bong.springboot.bean.Debate;

//@Repository("DBMapper")
//public class DBMapper {
//	public List<Customer> selectList() {
//		return selectList("sampleMapper.select");
//	}
//	
//	public List<Debate> selectDebate() {
//		return sqlSession.selectList("debate.selectDebate");
//	}
//	
//	public void addDebate(Debate debate) {
//		sqlSession.insert("debate.addDebate", debate);
//	}
//	
//	public Debate getDebateContent(int no) {
//		return sqlSession.selectOne("debate.getDebateContent", no);
//	}
//	
//	@Autowired 
//	private SqlSessionTemplate sqlSession;
//	
//	public List<Customer> selectList(String queryId) //수정
//	{return sqlSession.selectList(queryId); }
//
//}

//public interface DBMapper extends MongoRepository<Debate,String> {
//	
//}
