package com.example.demo;

import lombok.Data;

import java.awt.*;

@Data
public class PdfWaterMarkConfig {
    /**
     * 水印文本
     */
    private String text;

    /**
     * 字体大小
     */
    private Float fontSize;

    /**
     * 字体颜色
     */
    private Color coler;

    /**
     * 行间距
     */
    private Integer rowSpace;

    /**
     * 列间距
     */
    private Integer colSpace;

    /**
     * 倾斜角度
     */
    private Double theta;

    /**
     * 透明度
     */
    private Float opacity;
}