//package com.itxwl.run.mongo;
//
//import com.mongodb.MongoClient;
//import com.mongodb.MongoClientOptions;
//import com.mongodb.MongoCredential;
//import com.mongodb.ServerAddress;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
//
//import java.util.List;
//
///**
// * @Auther: 薛
// * @Date: 2020/5/4 10:08
// * @Description:
// */
//@Configuration
//public class MongoPoolUtil {
//    // 链接池数量
//    private static final   String connectionsPerHost = "50";
//    /**
//     * 时间单位:毫秒~~
//     * 例:最大等待时间 300000 实际计算等待为:300000/1000=300秒=5分钟
//     */
//    // 最大等待时间
//    private static final  String maxWaitTime ="300000";
//    // scoket超时时间
//    private static final  String socketTimeout = "100000";
//    // 设置连接池最长生命时间
//    private static final  String maxConnectionLifeTime ="150000";
//    // 连接超时时间
//    private static final String connectTimeout = "25000";
//
//    /**
//     * 连接池获取MongoTemplate连接
//     * @param serverAddresses
//     * @return
//     */
//    public static MongoTemplate getMongoTemplate(List<ServerAddress> serverAddresses, List<MongoCredential> mongoCredentials,String fileDatabase){
//        MongoClientOptions options = MongoClientOptions.builder()
//                .connectionsPerHost(Integer.parseInt(connectionsPerHost))
//                .maxWaitTime(Integer.parseInt(maxWaitTime))
//                .socketTimeout(Integer.parseInt(socketTimeout))
//                .maxConnectionLifeTime(Integer.parseInt(maxConnectionLifeTime))
//                .connectTimeout(Integer.parseInt(connectTimeout)).build();
//        MongoClient mongoClient=new MongoClient(serverAddresses, mongoCredentials,options);
//        return new MongoTemplate(new SimpleMongoDbFactory(mongoClient,fileDatabase));
//    }
//
//}
