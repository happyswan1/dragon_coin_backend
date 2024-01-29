package com.example.demo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.po.Coin;
import com.example.demo.service.CoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/coin")
public class CoinController {

    @Autowired
    private CoinService coinService;

    @GetMapping("/{id}")
    public Coin getCoinById(@PathVariable Long id) {
        return coinService.getById(id);
    }

    @PostMapping("/upload")
    public boolean addCoin(@RequestBody Coin coin) {
        return coinService.save(coin);
    }

    @PutMapping
    public boolean updateCoin(@RequestBody Coin coin) {
        return coinService.updateById(coin);
    }

    @PostMapping("/delete/{id}")
    public boolean deleteCoin(@PathVariable String id) {
        Long longId = Long.parseLong(id);
        return coinService.removeById(longId);
    }

    @GetMapping("/page")
    public Page<Coin> getCoins(@RequestParam(defaultValue = "1") int page,
                               @RequestParam(defaultValue = "10") int pageSize) {
        return coinService.selectCoinPage(page, pageSize);
    }
    @GetMapping("/test")
    public String test(){
        return "hello from coincontroller";
    }
    @GetMapping("/search")
    public Page<Coin> searchCoins(@RequestParam String coinNumber,
                                  @RequestParam(defaultValue = "1") int page,
                                  @RequestParam(defaultValue = "10") int pageSize) {
        return coinService.searchCoins(coinNumber, page, pageSize);
    }

    @GetMapping("/searchByPhone")
    public Page<Coin> searchCoinsByPhoneNumber(@RequestParam String phoneNumber,
                                  @RequestParam(defaultValue = "1") int page,
                                  @RequestParam(defaultValue = "10") int pageSize) {
        return coinService.searchCoinsByUser(phoneNumber, page, pageSize);
    }
    // 其他方法...
}