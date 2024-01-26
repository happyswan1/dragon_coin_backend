package com.example.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.mapper.CoinMapper;
import com.example.demo.po.Coin;
import org.springframework.stereotype.Service;

@Service
public class CoinService extends ServiceImpl<CoinMapper, Coin> {

    public Page<Coin> selectCoinPage(int page, int pageSize) {
        Page<Coin> coinPage = new Page<>(page, pageSize);
        return this.page(coinPage);
    }
    public Page<Coin> searchCoins(String coinNumber, int page, int pageSize) {
        Page<Coin> coinPage = new Page<>(page, pageSize);
        return lambdaQuery().like(Coin::getCoinNumber, coinNumber).page(coinPage);
    }
    public Page<Coin> searchCoinsByUser(String phoneNumber, int page, int pageSize) {
        Page<Coin> coinPage = new Page<>(page, pageSize);
        return lambdaQuery().like(Coin::getPhoneNumber, phoneNumber).page(coinPage);
    }
    // 这里可以添加其他自定义方法
}