package demo.ldz.util;

import java.io.File;
import java.io.FilenameFilter;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;

public class Doc2PdfUtil {
	public void doc2Pdf(File inputFile, File outputFile) {
		// 启动服务
		String OpenOffice_HOME = "C:/Program Files (x86)/OpenOffice 4";// 这里是OpenOffice的安装目录
		if (OpenOffice_HOME.charAt(OpenOffice_HOME.length() - 1) != '/') {
			OpenOffice_HOME += "/";
		}
		Process pro = null;
		OpenOfficeConnection connection = null;
		// 启动OpenOffice的服务
		String command = OpenOffice_HOME
				+ "program/soffice.exe -headless -accept=\"socket,host=127.0.0.1,port=8100;urp;\"";
		// connect to an OpenOffice.org instance running on port 8100

		try {
			pro = Runtime.getRuntime().exec(command);
			connection = new SocketOpenOfficeConnection("127.0.0.1", 8100);
			connection.connect();

			// convert
			DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
			System.out.println(inputFile + "=" + outputFile);

			converter.convert(inputFile, outputFile);
		} catch (Exception ex) {
			System.out.println("程序出错了");
			ex.printStackTrace();

		} finally {
			// close the connection
			try {
				if (connection != null) {
					connection.disconnect();
					connection = null;
				}
				pro.destroy();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("生成" + outputFile.getName());
	}

	/**
	 * @param dirs
	 *            doc转换文件夹，批量转换
	 */
	public static void batchDoc2Pdf(String srcDir) {
		File dir = new File(srcDir);
		File[] files = dir.listFiles(new WordFilenameFilter());
		// 遍历文件夹方式
		if (files == null || files.length == 0) {
			throw new NullPointerException("该路径下没有doc文件");
		}
		for (int i = 0; i < files.length; i++) {
			String strFileName = files[i].getAbsolutePath().toLowerCase();
			TestThread t1 = new Doc2PdfUtil.TestThread();
			// 输入文件名
			t1.setInputFile(new File(strFileName));
			// 获得"."前面的文件名并将其输入为PDF
			t1.setOutputFile(new File(strFileName.substring(0, strFileName.indexOf(".")) + ".pdf"));
			t1.start();
		}

	}

	/**
	 * 转换后文档与源文件保存在同一目录下
	 * 
	 * @param orgfileName
	 *            原始word文件名
	 */
	public static String word2Pdf(String orgfileName) {
		if (!isWord(orgfileName)) {
			throw new IllegalArgumentException("原始word文件名不是word文档,或者descFileName为空");
		}
		Doc2PdfUtil t = new Doc2PdfUtil();
		String descFileName = orgfileName.substring(0, orgfileName.indexOf(".")) + ".pdf";
		t.doc2Pdf(new File(orgfileName), new File(descFileName));
		return descFileName;
	}

	static class WordFilenameFilter implements FilenameFilter {
		@Override
		// 只转换word文档
		public boolean accept(File dir, String name) {
			return isWord(name);
		}

	}

	/**
	 * @param name
	 *            文件名
	 * @return 判断是否为doc word文档
	 */
	public static boolean isWord(String name) {
		return name.endsWith(".doc") || name.endsWith(".docx") || name.endsWith(".wps") || name.endsWith(".xlsx")|| name.endsWith(".xls");
		// return true;
	}

	// 生产pdf线程
	static class TestThread extends java.lang.Thread {
		private File inputFile;
		private File outputFile;

		public void run() {
			Doc2PdfUtil t = new Doc2PdfUtil();
			t.doc2Pdf(inputFile, outputFile);
			System.out.println(outputFile.getName() + "文件已生成");
		}

		public void setInputFile(File inputFile) {
			this.inputFile = inputFile;
		}

		public void setOutputFile(File outputFile) {
			this.outputFile = outputFile;
		}

	}
}
