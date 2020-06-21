package com.bong.springboot.bean;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Board")
public class Board {
	 @Transient
    public static final String SEQUENCE_NAME = "board_sequence";
	private int no;
	private String title;
	private String content;
//	public Board(){}
	public Board(String title, String content) {
//		this.no = no;
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
