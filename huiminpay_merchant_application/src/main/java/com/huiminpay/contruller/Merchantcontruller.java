package com.huiminpay.contruller;

import com.huiminpay.vo.MerchantVo;
import com.huimipay.api.IMerchantService;
import com.huimipay.api.IMsmService;
import com.huimipay.bean.Merchant;

import com.huimipay.dao.MerchantDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

@Api(value = "商铺应用API接口",description = "商铺应用API接口包含增删改查功能")
@RestController

public class Merchantcontruller {

    @org.apache.dubbo.config.annotation.Reference
    IMerchantService service;
    @org.apache.dubbo.config.annotation.Reference
    IMsmService iMsmService;



    @ApiOperation(value = "根据商铺id获取商铺信息")
   @ApiImplicitParams(@ApiImplicitParam(name = "id",value = "店铺id",required = true,dataType = "Long"))
    @GetMapping("/queryMerchant/{id}")
    public Merchant queryMerchant(@PathVariable("id")Long id){


        return service.queryMerchantById(id);
    }
    @ApiImplicitParams(@ApiImplicitParam(name = "phone",value = "手机号",required = true,paramType = "path"))
    @GetMapping("/sendSms/{phone}")
    public String sendSms(@PathVariable("phone") String phone){
        return iMsmService.sendSms(phone);
    }
    /**
     * 商户的注册接口
     * */
    @PostMapping("/registerMerchant")
    public MerchantVo registerMerchant(@RequestBody MerchantVo merchantVo){
        //校验验证码
        iMsmService.verify(merchantVo.getVerifiykey(),merchantVo.getVerifiyCode());
        //用户的祖册
        MerchantDto merchantDto = new MerchantDto();
        merchantDto.setMobile(merchantVo.getMobile());
        service.CreateMerchant(merchantDto);
        return merchantVo;

    }
}
