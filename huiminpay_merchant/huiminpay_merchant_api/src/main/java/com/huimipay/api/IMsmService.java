package com.huimipay.api;

public interface IMsmService {
    //获取验证码
    public String sendSms(String phone);
    //校验验证码
    public String verify(String verifyKey,String verifyCode);
}
