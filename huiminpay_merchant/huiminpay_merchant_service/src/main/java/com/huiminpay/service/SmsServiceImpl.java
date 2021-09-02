package com.huiminpay.service;

import com.huimipay.api.IMsmService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@Service
public class SmsServiceImpl implements IMsmService {


    @Value("${sms.url}")
    String url;
    @Value("${sms.effectiveTime}")
    String effectiveTime;
    @Value("${sms.name}")
    String name;
    @Autowired
    RestTemplate restTemplate;

    @Override
    public String sendSms(String phone) {

        //请求路径
        String smsUrl=url+"generate?effectiveTime="+effectiveTime+"&name="+name;

        HashMap<String, Object> body = new HashMap<>();
        body.put("mobile",phone);
        //指定请求类型为json/
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        //将要发送的信息设置到请求头发送
        HttpEntity<HashMap<String, Object>> hashMapHttpEntity = new HttpEntity<>(body,httpHeaders);
        //通过restTemplate进行远程调用
        ResponseEntity<Map> exchange = restTemplate.exchange(smsUrl, HttpMethod.POST, hashMapHttpEntity, Map.class);
        //获取验证码对应的key值返回前端进行保存
        if(exchange!=null){
            Map body1 = exchange.getBody();
            Object object = body1.get("result");
            if(object==null){//代表验证码获取失败
                throw new RuntimeException("验证码获取失败");
            }
            Map<String,String> result=( Map<String,String>) object;
            String key = result.get("key");
            return key;


        }

        return null;
    }

    @Override
    public String verify(String verifyKey, String verifiyCode) {
        String verfiy = url + "/verify?name=sms&verificationCode=" + verifiyCode + "&verificationKey=" + verifyKey;
        Map responseMap = null;
        try {
            //请求校验验证码
            ResponseEntity<Map> exchange = restTemplate.exchange(verfiy, HttpMethod.POST,
                    HttpEntity.EMPTY, Map.class);
            responseMap = exchange.getBody();
        } catch (RestClientException e) {
            e.printStackTrace();

            throw new RuntimeException("验证码错误");
        }
        if (responseMap == null || responseMap.get("result") == null || !(Boolean)
                responseMap.get("result")) {
            throw new RuntimeException("验证码错误");
        }
        return null;
    }



}
