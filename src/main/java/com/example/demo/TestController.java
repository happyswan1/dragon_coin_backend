package com.example.demo;

import org.apache.tomcat.util.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@RestController
public class TestController {

    @GetMapping("/test")
    public String test(){
        return "hi";
    }
    @CrossOrigin
    @PostMapping("/downloadOrGenerate")
    public void downloadOrGenerate(@RequestBody Command command, HttpServletResponse response) throws IOException {
        String status = command.getId();
        System.out.println("请求进来了");
        try {
            if (status.equals("1")) {
                File file = new File("C:/Users/qiwang/Desktop/WqFiles/otherAI.pdf");
                if (!file.exists()) {
                    response.setStatus(HttpStatus.NOT_FOUND.value());
                    response.getWriter().write("文件未找到");
                    return;
                }
                response.setContentType("application/octet-stream");
                response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("otherAI.pdf", "UTF-8"));
                response.setHeader("Content-Length", String.valueOf(file.length()));

                try (InputStream fis = new BufferedInputStream(new FileInputStream(file));
                     OutputStream toClient = new BufferedOutputStream(response.getOutputStream())) {
                    byte[] buffer = new byte[4096];
                    int bytesRead;
                    while ((bytesRead = fis.read(buffer)) != -1) {
                        toClient.write(buffer, 0, bytesRead);
                    }
                    toClient.flush();
                }
            } else if (status.equals("2")) {
                response.setContentType("application/json;charset=UTF-8");
                response.getWriter().write("{\"status\":\"processing\",\"message\":\"正在下载中\"}");
            } else {
                response.setContentType("application/json;charset=UTF-8");
                response.getWriter().write("{\"status\":\"starting\",\"message\":\"开始下载\"}");
            }
        } catch (Exception e) {
            System.out.println("===" + e + "====");
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.getWriter().write("下载文件过程中发生异常: " + e.getMessage());
        }
    }





    @CrossOrigin
    @PostMapping("/Generate")
    public String pdfWriter(@RequestBody  ReportExportPDFRecordCommand exportPDFRecordCommand) {

        StringBuilder sb = new StringBuilder();
        String reportId = exportPDFRecordCommand.getReportId();
        String reportName = exportPDFRecordCommand.getReportName();


        // 读取报告内容
        String bodyStr = "hxiqciwoqhdiqwhdqwhiodwqdhqio";


        // 内容为空则不抓css了
        if (StringUtils.isEmpty(bodyStr)) {
            System.out.println("啥也么有");
        }

        // 读取CSS内容
        String cssStr = "efefefefwefefwefwef";

        sb.append(cssStr).append(bodyStr);

        String currTime = "1917878786";
        String fileName = currTime + "_" + reportName + ".pdf";

        // 转换中文字符内容
//        String content = convertHtmlFont(sb.toString());
        String content = sb.toString();

        // 保存html
        saveFile(content, "C:/Users/qiwang/Desktop/WqFiles/" + currTime + "_" + reportName + ".html");

        PdfBoxUtil.writePdfFile("C:/Users/qiwang/Desktop/WqFiles/" + fileName, content);

        String str=testOverLoad("a","a","c","d");
        return fileName;
    }

    public String testOverLoad(String paramA,String ...paramB){
        return "hello from test overload";
    }
    private void saveFile(String toString, String fileName) {
        FileWriter fileWriter = null;
        try {
            File file = new File(fileName);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

            fileWriter = new FileWriter(file);
            fileWriter.write(toString);


        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                if (!Objects.isNull(fileWriter)) {
                    fileWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
