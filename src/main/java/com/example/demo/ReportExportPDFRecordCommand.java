package com.example.demo;

import lombok.Data;

import java.util.List;

@Data
public class ReportExportPDFRecordCommand {

    /**
     * 主键
     */
    private String id;

    /**
     * 报告编号
     */
    private String reportId;

    /**
     * 报告名称
     */
    private String reportName;

//    /**
//     * 报告导出HTML文件编号
//     */
//    private List<String> chapterIds;

    /**
     * 报告导出HTML 目录id
     */
    private List<String> catalogIds;

    /**
     * 加载章节数据标志
     */
    private String loadChapterFlag;

}