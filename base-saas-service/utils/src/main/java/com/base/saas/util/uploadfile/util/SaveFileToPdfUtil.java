package com.base.saas.util.uploadfile.util;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author:
 * @Date: 2018/05/15 09:24
 * @Description: 文本内容转换为pdf
 */
public class SaveFileToPdfUtil {
    /**
     * @Author:
     * @Date: 2018/05/15 10:57
     * @Params: map（包括 file（文件路径），context（替换的文本内容））
     * @Description: 把文件内容转换为pdf
     * @return:
     */
    public static void insertPDF(Map map) throws Exception {
        Map map1 = new HashMap();
        // 1.新建document对象
        Document document = new Document();
        String context = "";//协议模板
        FileOutputStream fos = null;
        PdfWriter writer = null;
        try {
            fos = new FileOutputStream((File) map.get("file"));
            // 2.建立一个书写器(Writer)与document对象关联，通过书写器(Writer)可以将文档写入到磁盘中。
            // 创建 PdfWriter 对象 第一个参数是对文档对象的引用，第二个参数是文件的实际名称，在该名称中还会给出其输出路径。
            writer = PdfWriter.getInstance(document, fos);
            // 3.打开文档
            document.open();
            context = String.valueOf(map.get("context"));
            BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            Font font = new Font(bfChinese);
            // 4.添加一个内容段落
            document.add(new Paragraph(context, font));
            // 5.关闭文档
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                fos.close();
            }
        }
    }
}
