package com.Innobyte.UserService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {
		org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration.class,
		org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class

})
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

}



//@Bean(name = "mongoTemplate")@Primarypublic MongoTemplate mongoTemplate() throws Exception
//{    MappingMongoConverter converter = new MappingMongoConverter(mongoDatabaseFactory(), new MongoMappingContext());
//	converter.setTypeMapper(new DefaultMongoTypeMapper(null));
//	MongoTemplate mongoTemplate = new MongoTemplate(mongoDatabaseFactory(), converter);
//	return mongoTemplate;    // return new MongoTemplate(mongoDbFactory());}
//	has context menu
