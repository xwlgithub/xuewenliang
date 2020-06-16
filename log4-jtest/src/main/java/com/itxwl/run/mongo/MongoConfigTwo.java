//package com.itxwl.run.mongo;
//
//import com.mongodb.MongoClient;
//import com.mongodb.MongoClientOptions;
//import com.mongodb.MongoCredential;
//import com.mongodb.ServerAddress;
//import lombok.Data;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.LinkedList;
//import java.util.List;
//
///**
// * @Auther: 薛
// * @Date: 2020/5/4 09:46
// * @Description:
// */
//@Component
//@Data
//public class MongoConfigTwo extends MongoConfig {
//    //用户名
//    private String username="admin";
//    //认证库
//    private String source="test";
//    //密码
//    private String password="TFIot20190401";
//    //IP
//    private String ip="139.199.95.108";
//    //端口
//    private Integer port=20004;
//    //链接数据库
//    private String fileDatabase="TF_Iot_Energy";
//
//    @SuppressWarnings("all")
//    //@Bean("mongotianfu")
//    public  MongoTemplate getMongoTemplateTwo() throws Exception {
//        List<ServerAddress> addressList = new ArrayList<>();
//        List<MongoCredential> credentials = new LinkedList<>();
//        MongoCredential mongoCredential = MongoCredential
//                .createScramSha1Credential(username, source, password.toCharArray());
//        credentials.add(mongoCredential);
//        ServerAddress serverAddress = new ServerAddress(ip, port);
//        addressList.add(serverAddress);
//        return MongoPoolUtil.getMongoTemplate(addressList, credentials, fileDatabase);
//    }
//
//    @Override
//    public MongoTemplate getMongoTemplate() throws Exception {
//        return null;
//    }
//
//    public static void main(String[] args) throws Exception {
//        MongoConfigTwo mongoConfigTwo=new MongoConfigTwo();
//        MongoTemplate mongoTemplateTwo = mongoConfigTwo.getMongoTemplateTwo();
//    }
//}
