package com.solstice.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * 服务器保存文件的工具类
 */
public class FileUpLoadUtil {

	private FileUpLoadUtil() {
	}

	private static String FILEDIR = null;

	/**
	 * 上传
	 */
	public static List<String> upload(HttpServletRequest request) {
		List<String> filePathList = new ArrayList<String>();
		try {
			initFileDir(request);
			MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
			Map<String, MultipartFile> fileMap = mRequest.getFileMap();
			for (Map.Entry<String, MultipartFile> entry : fileMap.entrySet()) {
				MultipartFile mFile = entry.getValue();
				if (mFile.getSize() != 0 && !"".equals(mFile.getName())) {
					String fileName = initFileName(mFile.getOriginalFilename());
					write(mFile.getInputStream(), new FileOutputStream(FILEDIR
							+ File.separator + fileName));
					System.out.println(FILEDIR);
					filePathList
							.add("http://localhost:8080/frame-study-demo/file/"+ fileName);
				}
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return filePathList;
	}

	/**
	 * 初始化文件根目录
	 */
	private static void initFileDir(HttpServletRequest request) {
		if (FILEDIR == null) {
			FILEDIR = request.getSession().getServletContext().getRealPath("/")
					+ "file";
		}
		File file = new File(FILEDIR);
		if (!file.exists()) {
			boolean mkdir = file.mkdir();
			if (!mkdir)
				throw new RuntimeException("创建文件出错了");
		}
	}

	/**
	 * 初始化文件名
	 */
	private static String initFileName(String name) {
		Long num = new Date().getTime();
		Double d = Math.random() * num;
		return (num + d.longValue() + "_" + name).replaceAll(" ", "-");
	}

	/**
	 * 写入数据
	 * 
	 * @param in
	 *            输入流
	 * @param out
	 *            输出流
	 * @throws IOException
	 *             io异常
	 */
	public static void write(InputStream in, OutputStream out)
			throws IOException {
		byte[] buffer = new byte[1024];
		int bytesRead = -1;
		while ((bytesRead = in.read(buffer)) != -1) {
			out.write(buffer, 0, bytesRead);
		}
		out.flush();
		in.close();
		out.close();
	}

}
