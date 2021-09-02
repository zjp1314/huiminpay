package com.huimipay.api;

import com.huimipay.bean.Merchant;
import com.huimipay.dao.MerchantDto;

public interface IMerchantService {
    public Merchant queryMerchantById(Long id);

    //商户的注册
    MerchantDto CreateMerchant(MerchantDto merchantDto);


}
