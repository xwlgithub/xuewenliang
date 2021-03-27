package com.example.demo.rest;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
@SpringBootTest(classes = TestRestTemplate.class)
public class TestRestTemplate {
    @Qualifier("myHttpFactory")
    private static ClientHttpRequestFactory clientHttpRequestFactory;
    public static void main(String[] args) {
//        SimpleClientHttpRequestFactory sy = new SimpleClientHttpRequestFactory();
//        sy.setConnectTimeout(30000);
//        sy.setReadTimeout(30000);
        RestTemplate re=new RestTemplate(clientHttpRequestFactory);
        String url="https://localhost:8080/getList";
        ResponseEntity<Object> forEntity = re.getForEntity(url, Object.class);
        System.out.println(forEntity.toString());
    }

    /**
     * 自定义factory工厂
     * @param maxConn
     * @return
     */
    @Bean(name = "myHttpFactory")
    @Primary
    public ClientHttpRequestFactory createRequestFactory(String maxConn) {
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        //连接池最大数
        connectionManager.setMaxTotal(500);
        //每个路由最大连接数默认10
        connectionManager.setDefaultMaxPerRoute(10);
        RequestConfig config = RequestConfig.custom().setConnectTimeout(100000).build();
        CloseableHttpClient httpClient = HttpClientBuilder.create().setConnectionManager(connectionManager)
                .setDefaultRequestConfig(config).build();
        HttpComponentsClientHttpRequestFactory hy = new HttpComponentsClientHttpRequestFactory(httpClient);
        hy.setConnectTimeout(30000);
        hy.setReadTimeout(30000);
        return hy;
    }
}
