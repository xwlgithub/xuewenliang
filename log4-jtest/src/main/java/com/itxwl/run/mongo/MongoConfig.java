//package com.itxwl.run.mongo;
//
//import com.mongodb.MongoClientURI;
//import lombok.Data;
//import org.springframework.data.mongodb.MongoDbFactory;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
//
///**
// * @Auther: 薛
// * @Date: 2020/5/4 09:43
// * @Description:
// */
//@Data
//public abstract  class MongoConfig {
//    private String uri;
//    /*
//     * Method that creates MongoDbFactory Common to both of the MongoDb
//     * connections
//     */
//    public MongoDbFactory mongoDbFactory() throws Exception {
//
//        return new SimpleMongoDbFactory(new MongoClientURI(uri));
//    }
//    /*
//     * Factory method to create the MongoTemplate
//     */
//    abstract public MongoTemplate getMongoTemplate() throws Exception;
//
//}
