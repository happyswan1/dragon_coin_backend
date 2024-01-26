package com.example.demo;

public class ReportRecordCatalogCommand extends Command{
    /**
     * 主键
     */
    private String id;

    /**
     * 报告ID
     */
    private String reportId;

    /**
     * 目录名称
     */
    private String ctlgName;

    /**
     * 目录层级
     */
    private Integer ctlgLevel;

    /**
     * 目录父ID
     */
    private String ctlgPid;

    /**
     * 模板配置ID
     */
    private String templateConfigId;

    /**
     * 报告章节ID
     */
    private String recordCptrId;

    /**
     * 章节编码
     */
    private String cptrCode;

    /**
     * 详版、简版标识
     */
    private String ctlgDetaSimp;

    /**
     * 显示顺序
     */
    private Integer displaySeq;

    /**
     * 报告目录索引
     */
    private String cataSeq;

    /**
     * 组件ID
     */
    private String componentId;

    /**
     * 报告组件类型
     */
    private String rptComponentType;

    /**
     * 客户ID
     */
    private String custId;

    /**
     * 版本
     */
    private Integer rev;

    /**
     * 章节样式编码
     */
    private String styleCode;

    /**
     * 目录节点类型
     */
    private String nodeType;

    /**
     * 是否可隐藏
     */
    private String hiddenStatus;

    /**
     * 显示模式
     */
    private String dsplyType;

    /**
     * 说明
     */
    private String ctlgRemark;

    /**
     * 组件配置ID
     */
    private String componentConfigId;

    /**
     * 版式要求
     */
    private String fmtFillReq;

    /**
     * 标题隐藏标志
     */
    private String titleHiddenFlag;
}
