package com.huiminpay.test;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huimipay.bean.Merchant;
import com.huimipay.mapper.IUserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisPlusTest {

    @Autowired
    IUserMapper userMapper;

    @Test
    public void instMerchant() {
        Merchant merchant = new Merchant();
        merchant.setMerchantName("plus test");
        int insert = userMapper.insert(merchant);
        System.out.println("添加的结果"+insert);
        System.out.println("merchant"+merchant);

    }
    @Test
    public void instMerchant1() {
        Merchant merchant = new Merchant();
        merchant.setId(13L);
        merchant.setMerchantName("赵");
        int insert = userMapper.insert(merchant);
        System.out.println("添加的结果"+insert);
        System.out.println("merchant"+merchant);

    }
    @Test
    public void selectid(){
        Merchant merchant = userMapper.selectById(123);
        System.out.println(merchant);
    }
    @Test
    public void selectname(){
        QueryWrapper<Merchant> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("merchant_name","赵");
        List<Merchant> merchants = userMapper.selectList(queryWrapper);

        System.out.println(merchants);
    }
    @Test
    public void selectpage(){
        Page<Merchant> merchantPage = new Page<>(1,4);
        IPage<Merchant> merchantIPage = userMapper.selectPage(merchantPage,null);
        List<Merchant> records = merchantIPage.getRecords();
        for (Merchant record : records) {
            System.out.println(record);
        }



    }
    @Test
    public void selectmohu(){
        QueryWrapper<Merchant> merchantQueryWrapper = new QueryWrapper<>();
        merchantQueryWrapper.like("merchant_name","山");
        List<Merchant> merchants = userMapper.selectList(merchantQueryWrapper);
        for (Merchant merchant : merchants) {
            System.out.println(merchant);
        }

    }
    @Test
  public void lambda(){
      LambdaQueryWrapper<Merchant> lambdaQueryWrapper = new LambdaQueryWrapper<>();
      lambdaQueryWrapper.eq(Merchant::getMerchantName,"赵");
      List<Merchant> merchants = userMapper.selectList(lambdaQueryWrapper);
      merchants.stream().filter(m -> m.getId().toString().startsWith("1"))
              .forEach(m -> System.out.println(m));


  }


}
