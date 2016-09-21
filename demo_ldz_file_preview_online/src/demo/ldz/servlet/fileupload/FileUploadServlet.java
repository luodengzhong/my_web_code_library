package demo.ldz.servlet.fileupload;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import demo.ldz.util.Doc2PdfConverter;

public class FileUploadServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        super.doPost(request, response);
        // 文件上传采用cos组件上传，可更换为commons-fileupload上传，文件上传后，保存在upload文件夹
        // 获取文件上传路径

        String saveDirectory = request.getRealPath("/") + "upload";
        // 打印上传路径信息
        System.out.println(saveDirectory);
        // 每个文件最大50m
        int maxPostSize = 50 * 1024 * 1024;
        // 采用cos缺省的命名策略，重名后加1,2,3...如果不加dfp重名将覆盖
        DefaultFileRenamePolicy pdf = new DefaultFileRenamePolicy();
        // response的编码为"UTF-8",同时采用缺省的文件名冲突解决策略,实现上传,如果不加dfp重名将覆盖
        MultipartRequest multi = new MultipartRequest(request, saveDirectory, maxPostSize, "UTF-8", pdf);
        // MultipartRequest multi = new MultipartRequest(request, saveDirectory, maxPostSize,"UTF-8");
        // 输出反馈信息
        Enumeration files = multi.getFileNames();
        while (files.hasMoreElements()) {
            String name = (String) files.nextElement();
            File f = multi.getFile(name);
            if (f != null) {
                String fileName = multi.getFilesystemName(name);
                // 获取上传文件的扩展名
                String extName = fileName.substring(fileName.lastIndexOf(".") + 1);
                // 文件全路径
                String lastFileName = saveDirectory + "\\" + fileName;
                // 获取需要转换的文件名,将路径名中的'\'替换为'/'
                String converfilename = saveDirectory.replaceAll("\\\\", "/") + "/" + fileName;
                System.out.println(converfilename);
                // 调用转换类DocConverter,并将需要转换的文件传递给该类的构造方法
                Doc2PdfConverter d = new Doc2PdfConverter(converfilename);
                // 调用conver方法开始转换，先执行doc2pdf()将office文件转换为pdf;再执行pdf2swf()将pdf转换为swf;
                d.conver();
                // 调用getswfPath()方法，打印转换后的swf文件路径
                System.out.println(d.getswfPath());
                // 生成swf相对路径，以便传递给flexpaper播放器
                String swfpath = "upload" + d.getswfPath().substring(d.getswfPath().lastIndexOf("/"));
                request.getSession().setAttribute("swfpath", swfpath);
                // 将相对路径放入sessio中保存

                // 根据报表的类型，跳转到不同的页面
                request.getRequestDispatcher("pages/documentUploadSuccess.jsp").forward(request, response);

                return;
            }
        }
    }

}
