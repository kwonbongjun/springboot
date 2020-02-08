package com.bong.springboot;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("DBMapper")
public class DBMapper {
	public List<Customer> selectList() {
		return selectList("sampleMapper.select");
	}
	
	
	@Autowired 
	private SqlSessionTemplate sqlSession;
	
	public List<Customer> selectList(String queryId) //수정
	{return sqlSession.selectList(queryId); }

}
