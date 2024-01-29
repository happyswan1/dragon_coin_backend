package com.example.demo.po;

import lombok.Data;

@Data
public class Coin {
    private String id;
    private Long phoneNumber; // 使用 Long 以允许 null 值
    private String coinNumber;
}
