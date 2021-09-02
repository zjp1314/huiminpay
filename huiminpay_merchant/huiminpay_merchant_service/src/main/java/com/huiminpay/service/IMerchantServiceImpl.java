package com.huiminpay.service;

import com.huimipay.api.IMerchantService;
import com.huimipay.bean.Merchant;
import com.huimipay.dao.MerchantDto;
import com.huimipay.mapper.IUserMapper;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class IMerchantServiceImpl implements IMerchantService {
    @Autowired
    IUserMapper iUserMapper;


    public Merchant queryMerchantById(Long id) {
        return iUserMapper.selectById(id);
    }

    @Override
    public MerchantDto CreateMerchant(MerchantDto merchantDto) {
        Merchant merchant = new Merchant();
        merchant.setAuditStatus("0");
        merchant.setMobile(merchantDto.getMobile());
        iUserMapper.insert(merchant);
        //将添加成功后的id回显到Dto中在返回到前端
        merchantDto.setId(merchant.getId());
        return merchantDto;
    }
}
