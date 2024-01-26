package com.example.demo;

import com.openhtmltopdf.outputdevice.helper.BaseRendererBuilder;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.graphics.state.PDExtendedGraphicsState;
import org.apache.pdfbox.util.Matrix;
import org.apache.tomcat.util.ExceptionUtils;
import org.jsoup.Jsoup;
import org.jsoup.helper.W3CDom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.pdfbox.pdmodel.PDDocument;
public class PdfBoxUtil {
    private static Logger logger = LoggerFactory.getLogger(PdfBoxUtil.class);

    private static String LINUX_FONT_PATH = "/data/plos/Fonts/";
    //  private static String LINUX_FONT_PATH = "/usr/share/Fonts/";


    private static String WIN_FONT_PATH = "C:\\Windows\\Fonts\\";

    private static String FONT_TYPE_NAME = "simsun.ttc";
//    private static String WATERMARK_FONT_TYPE_NAME = "simfang.ttf";


    private static String WATERMARK_FONT_TYPE_NAME = "simsunb.ttf";
    /**
     * 水印渲染
     * @param fileName
     * @param config
     */
    public static void waterMark(String fileName, PdfWaterMarkConfig config) {
        try {
            // 加载PDF文件
            File tempFile = new File(fileName);
            PDDocument document = PDDocument.load(tempFile);
            document.setAllSecurityToBeRemoved(true);

            // 配置数据
            Integer rowSpace = config.getRowSpace();
            Integer colSpace = config.getColSpace();
            String text = config.getText();
            Double theta = config.getTheta();
            Float opacity = config.getOpacity();
            Float fontSize = config.getFontSize();

            int colFlag = 0;
            int xDefaultPosition = -10;
            int yDefaultPosition = 10;

            // 遍历PDF文件，在每一页加上水印
            for (PDPage page : document.getPages()) {
                PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.PREPEND, true, true);

                // 加载水印字体
                PDFont font = PDType0Font.load(document, new File(getFontPath() + WATERMARK_FONT_TYPE_NAME));

                PDExtendedGraphicsState pdExtGfxState = new PDExtendedGraphicsState();

                // 设置透明度
                pdExtGfxState.setNonStrokingAlphaConstant(opacity);
                pdExtGfxState.setAlphaSourceFlag(true);
                contentStream.setGraphicsStateParameters(pdExtGfxState);

                // 设置水印字体颜色
                contentStream.setNonStrokingColor(config.getColer());
                contentStream.beginText();
                contentStream.setFont(font, fontSize);
                contentStream.newLineAtOffset(0, -15);

                // 获取PDF页面大小
                float pageHeight = page.getMediaBox().getHeight();
                float pageWidth = page.getMediaBox().getWidth();

                // 根据纸张大小添加水印
                for (int h = yDefaultPosition; h < pageHeight; h = h + rowSpace) {
                    // 错位半个列宽
                    int colOffSet = xDefaultPosition - colSpace/2*colFlag;
                    colFlag = colFlag ^ 1;
                    for (int w = colOffSet; w < pageWidth; w = w + colSpace) {
                        contentStream.setTextMatrix(Matrix.getRotateInstance(theta, w, h));
                        contentStream.showText(text);
                    }
                }

                // 结束渲染，关闭流
                contentStream.endText();
                contentStream.restoreGraphicsState();
                contentStream.close();
            }
            document.save(tempFile);
        } catch (IOException e) {

            e.printStackTrace();

        }
    }

    /**
     * 输出到文件
     * @param fileName
     * @param content
     */
    public static void writePdfFile(String fileName, String content) {
        try (OutputStream os = new FileOutputStream(fileName))  {
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.useFastMode();

            // 设置中文字体
            File fontFile = new File(getFontPath()+FONT_TYPE_NAME);
            builder.useFont(fontFile, "SimSun");
            builder.useFont(fontFile, "SimHei");
            builder.useFont(fontFile, "Microsoft Yahei");
            builder.useFont(fontFile, "Microsoft JhengHei");
            builder.useFont(fontFile, "KaiTi");
            builder.useFont(fontFile, "NSimSun");
            builder.useFont(fontFile, "FangSong");
            builder.useFont(fontFile, "宋体");
            builder.useFont(fontFile, "Calibri");
            builder.useFont(fontFile, "Avenir");
            builder.useFont(fontFile, "Helvetica");
            builder.useFont(fontFile, "Arial");
            builder.useFont(fontFile, "sans-serif");
            builder.useFont(fontFile, "microsoft yahei");
            builder.useFont(fontFile, "Avenir, Helvetica, Arial, sans-serif");
            builder.useFont(fontFile, "Times New Roman");

            // 设置页面大小
            builder.useDefaultPageSize(370f, 440f, BaseRendererBuilder.PageSizeUnits.MM);

            // 获取w3c的文档
            org.w3c.dom.Document document = htmlContentParseDocument(content);

            // 生成pdf
            builder.withW3cDocument(document, "file:///");
            builder.toStream(os);
            builder.run();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    /**
     * 将html内容解析为标准w3c文档
     * @param content
     * @return
     */
    private static org.w3c.dom.Document htmlContentParseDocument(String content) {
        org.jsoup.nodes.Document doc = Jsoup.parse(content);
        return new W3CDom().fromJsoup(doc);
    }

    /**
     * 获取系统中文字符路径
     * @return
     */
    private static String getFontPath() {
        return File.separator.equals("/") ? LINUX_FONT_PATH : WIN_FONT_PATH;
    }
}