package demo.ldz.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;

public class File2PreviewConverter {
    private static final int environment = 1;// 环境1：windows,2:linux(涉及pdf2swf路径问题)

    private String fileString;

    private String decsFileFullName = "";// 输入路径，如果不设置就输出在默认位置

    private String fileName;

    private File pdfFile;

    private File swfFile;

    private File docFile;

    public File2PreviewConverter(String fileString) {
        ini(fileString);
    }

    /*
     * 重新设置 file @param fileString
     */
    public void setFile(String fileString) {
        ini(fileString);
    }

    /*
     * 初始化 @param fileString
     */
    private void ini(String fileString) {
        this.fileString = fileString;
        fileName = fileString.substring(0, fileString.lastIndexOf("."));
        docFile = new File(fileString);
        pdfFile = new File(fileName + ".pdf");
        swfFile = new File(fileName + ".swf");
    }

    static String loadStream(InputStream in) throws IOException {
        int ptr = 0;
        // 把InputStream字节流 替换为BufferedReader字符流 2013-07-17修改
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder buffer = new StringBuilder();
        while ((ptr = reader.read()) != -1) {
            buffer.append((char) ptr);
        }
        return buffer.toString();
    }

    /*
     * 转换主方法
     */
    public boolean conver() {
        if (swfFile.exists()) {
            System.out.println("****swf转换器开始工作，该文件已经转换为swf****");
            return true;
        }

        if (environment == 1) {
            System.out.println("****swf转换器开始工作，当前设置运行环境windows****");
        } else {
            System.out.println("****swf转换器开始工作，当前设置运行环境linux****");
        }

        try {
            if (Doc2PdfUtil.isWord(fileString)) {
                String pdfName = Doc2PdfUtil.word2Pdf(fileString);
                decsFileFullName = Pdf2SwfUtil.pdf2swf(pdfName);
                File f = new File(pdfName);
                f.delete();
            } else if (Pdf2SwfUtil.isPdf(fileString)) {
                decsFileFullName = Pdf2SwfUtil.pdf2swf(fileString);
            } else {
                decsFileFullName = fileString;
            }
            swfFile = new File(decsFileFullName);
            return true;
        } catch (Exception e) {
            // TODO: Auto-generated catch block
            e.printStackTrace();
            return false;
        }

    }

    /*
     * 返回文件路径 @param s
     */
    public String getswfPath() {
        if (swfFile.exists()) {
            String tempString = swfFile.getPath();
            tempString = tempString.replaceAll("\\\\", "/");
            return tempString;
        } else {
            return "";
        }
    }

    public static void main(String s[]) {
        File2PreviewConverter d = new File2PreviewConverter("g:/美好生活B健康一生计划书系统.xls");
        d.conver();
    }
}