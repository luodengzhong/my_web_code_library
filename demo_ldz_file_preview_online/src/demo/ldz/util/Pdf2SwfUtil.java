package demo.ldz.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Pdf2SwfUtil {
	private static final int environment = 1;// 环境1：windows,2:linux(涉及pdf2swf路径问题)

	public static void pdf2swf(String fileString) throws Exception {
		String fileName = fileString.substring(0, fileString.lastIndexOf("."));
		if (!isPdf(fileString)) {
			return;
		}
		File pdfFile = new File(fileName + ".pdf");
		File swfFile = new File(fileName + ".swf");
		Runtime r = Runtime.getRuntime();
		if (!swfFile.exists()) {
			if (pdfFile.exists()) {
				if (environment == 1)// windows环境处理
				{
					try {
						// 这里根据SWFTools安装路径需要进行相应更改
						Process p = r.exec("C:/Program Files (x86)/SWFTools/pdf2swf.exe " + pdfFile.getPath() + " -o "
								+ swfFile.getPath() + " -T 9 -t -s storeallcharacters");
						if (pdfFile.exists()) {
							// pdfFile.delete();
						}
						System.err.println("****swf转换成功，文件输出：" + swfFile.getPath() + "****");
					} catch (Exception e) {
						e.printStackTrace();
						throw e;
					}
				}

			} else {
				System.out.println("****pdf不存在，无法转换****");
			}
		} else {
			System.out.println("****swf已存在不需要转换****");
		}
	}

	public static boolean isPdf(String name) {
		return name.endsWith(".pdf");
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
}
