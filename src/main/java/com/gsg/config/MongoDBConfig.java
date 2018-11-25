package com.gsg.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = { "com.gsg.mongo.repository" },mongoTemplateRef="mongoTemplate")
@EnableMongoAuditing(modifyOnCreate=true)
public class MongoDBConfig {

/*	@Bean
	public MappingMongoConverter mappingMongoConverter(MongoDbFactory factory, MongoMappingContext mctx,
			BeanFactory beanFactory) {

		DbRefResolver dbRefResolver = new DefaultDbRefResolver(factory);
		MappingMongoConverter mappingMongoConverter = new MappingMongoConverter(dbRefResolver, mctx);
		try {
			mappingMongoConverter.setCustomConversions(beanFactory.getBean(CustomConversions.class));
		} catch (NoSuchBeanDefinitionException ex) {

		}
		mappingMongoConverter.setTypeMapper(new DefaultMongoTypeMapper(null));
		return mappingMongoConverter;
	}
*/
	
	 /*public @Bean MongoClient mongoClient() {
	       return new MongoClient(
	    		   //Collections.singletonList(new ServerAddress("localhost", 27017)),
	    		   //Collections.singletonList(MongoCredential.createScramSha1Credential("myUserAdmin", "gsg", "abc123".toCharArray())));
	    		   //new MongoClientURI("mongodb://gsgadmin:gsgadmin@ds131137.mlab.com:31137/gsg"));
	    		   new MongoClientURI("mongodb://localhost:27017/gsg"));
	       
	   }
	 
	 public @Bean MongoTemplate mongoTemplate() {
	      return new MongoTemplate(mongoClient(), "gsg");
	  }*/
}
