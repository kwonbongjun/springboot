package com.bong.springboot.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.bong.springboot.bean.BoardSequence;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

@Service
public class SequenceGeneratorService {
	private static MongoOperations mongoOperations;
	 @Autowired
	    public SequenceGeneratorService(MongoOperations mongoOperations) {
	        this.mongoOperations = mongoOperations;
	    }

	    public static int generateSequence(String seqName) {

	        BoardSequence counter = mongoOperations.findAndModify(query(where("_id").is(seqName)),
	                new Update().inc("seq",1), options().returnNew(true).upsert(true),
	                BoardSequence.class);
	        return !Objects.isNull(counter) ? counter.getSeq() : 1;

	    }
}
