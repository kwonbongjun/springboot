package com.bong.springboot;

import java.util.Arrays;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;





//public class DatabaseConfig {
//	@Bean
//	public SqlSessionFactory sqlSessionFactory(DataSource datasource) throws Exception{
//		SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
//		sqlSessionFactory.setDataSource(datasource);
//		sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:/mappers/*.xml"));
//		return (SqlSessionFactory) sqlSessionFactory.getObject();
//	}
//	@Bean
//	public SqlSessionTemplate sqlSession(SqlSessionFactory sqlSessionFactory) {
//		return new SqlSessionTemplate(sqlSessionFactory);
//	}
//}
//@Configuration
//@EnableMongoRepositories("com.bong.repository")
public class DatabaseConfig{
//	@Value("${spring.data.mongodb.username}")
//	private String userName;
//	@Value("${spring.data.mongodb.password}")
//	private String password;
//	@Value("${spring.data.mongodb.database}")
//	private String database;
//    @Value("${spring.data.mongodb.host}")
//    private String mongoHost;
//    @Value("${spring.data.mongodb.port}")
//    private String mongoPort;
    
	 @Autowired
	    private Environment env;

	    @Bean
	    public MongoDbFactory mongoDbFactory() {
	    	return new SimpleMongoDbFactory(new MongoClientURI(env.getProperty("spring.data.mongodb.uri")));
	    }

		@Bean
		public MongoTemplate mongoTemplate() {
			MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());

			return mongoTemplate;

	    }
//	    @Bean
//	    public Mongo mongo() throws Exception {
//	        return new MongoClient(mongoHost + ":" + mongoPort);
//	    }
//	    protected String getDatabaseName() {
//	        // TODO Auto-generated method stub
//	        return database;
//	    }

}

//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//@Configuration
//public class DataSource {
//	@Bean
//	@ConfigurationProperties("spring.datasource")
//	public DataSource dataSource() {
//	    return (DataSource) DataSourceBuilder.create().build();
//	}
//}
