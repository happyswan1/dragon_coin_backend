package com.example.demo;

public enum PdfExportStep {
    PVIEW_DOWNLOAD("下载pviewHtml","pviewDownload", 8500d),
    SAVA_RECORD("保存PDF导出记录","savaRecord", 0d),
    READ_REPORT("读取报告内容","readReport", 0d),
    READ_CSS("读取远端css内容","readCss", 0d),
    CREAT_HTML("生成HTML文件","creatHtml", 0d),
    CREAT_PDF("生成PDF文件","creatPdf", 160d),
    RETURN_FRONT("下载pdf文件返回前端","returnFront", 0d),
    WATERMARK("渲染水印","watermark", 350d),
    OTHER("其他","other", 3000d),
    ;

    String label;
    String value;
    Double defVal;

    PdfExportStep(String label, String value, Double defVal) {
        this.label = label;
        this.value = value;
        this.defVal = defVal;
    }

    public String getLabel() {
        return label;
    }

    public String getValue() {
        return value;
    }

    public Double getDefVal() { return defVal; }
}