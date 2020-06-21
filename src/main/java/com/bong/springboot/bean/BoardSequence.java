package com.bong.springboot.bean;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "board_sequences")
public class BoardSequence {
	@Id
    private String id;
 
    private int seq;

	public int getSeq() {
		// TODO Auto-generated method stub
		return seq;
	}
}
