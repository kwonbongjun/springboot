package com.bong.springboot.bean;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="debate")
public class Debate {

	private int no;
	private String title;
	private String content;
	public Debate(){}
	public Debate(int no, String title, String content) {
		this.no = no;
		this.title = title;
		this.content = content;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
