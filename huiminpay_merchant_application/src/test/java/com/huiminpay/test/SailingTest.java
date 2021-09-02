package com.huiminpay.test;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SailingTest {

    @Test
    public void createSmTest(){
        RestTemplate restTemplate = new RestTemplate();
        //请求路径
        String url="http://localhost:56085/sailing/generate?effectiveTime=300&name=sms";
        String phone="156515665";

        HashMap<String, Object> body = new HashMap<>();
        body.put("mobile",phone);
        //指定请求类型为json/
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        //将要发送的信息设置到请求头发送
        HttpEntity<HashMap<String, Object>> hashMapHttpEntity = new HttpEntity<>(body,httpHeaders);
        //通过restTemplate进行远程调用
        ResponseEntity<Map> exchange = restTemplate.exchange(url, HttpMethod.POST, hashMapHttpEntity, Map.class);
        //获取返回的结果
        Map body1 = exchange.getBody();
        Set set = body1.entrySet();
        Iterator iterator = set.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }


    }
}
