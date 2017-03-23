package cn.tools;

import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.GZIPInputStream;
import java.util.zip.Inflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

/**
 * 文件操作类
 * @author hubaoting
 * @date 2015年9月18日 上午10:08:15
 */
public class FileTools {
	
	public static final String DOCEXT = ".doc";
	public static final String ZIPEXT = ".zip";

	private static int line = 0;

	/**
	 * 复制单个文件
	 * 
	 * @param oldPath
	 * String 原文件路径 如：c:/fqf.txt
	 * @param newPath
	 * String 复制后路径 如：f:/fqf.txt
	 * @return boolean
	 */
	@SuppressWarnings("unused")
	public static void copyFile(String oldPath, String newPath) {
		try {
			int bytesum = 0;
			int byteread = 0;
			File oldfile = new File(oldPath);
			if (oldfile.exists()) { // 文件存在时
				InputStream inStream = new FileInputStream(oldPath); // 读入原文件
				FileOutputStream fs = new FileOutputStream(newPath);
				byte[] buffer = new byte[1444];
				while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread; // 字节数 文件大小
					fs.write(buffer, 0, byteread);
				}
				inStream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 复制整个文件夹内容
	 * 
	 * @param oldPath
	 * String 原文件路径 如：c:/fqf
	 * @param newPath
	 * String 复制后路径 如：f:/fqf/ff
	 * @return boolean
	 */
	public static void copyFolder(String oldPath, String newPath) {

		try {
			(new File(newPath)).mkdirs(); // 如果文件夹不存在 则建立新文件夹
			File a = new File(oldPath);
			String[] file = a.list();
			File temp = null;
			for (int i = 0; i < file.length; i++) {
				if (oldPath.endsWith(File.separator)) {
					temp = new File(oldPath + file[i]);
				} else {
					temp = new File(oldPath + File.separator + file[i]);
				}

				if (temp.isFile()) {
					FileInputStream input = new FileInputStream(temp);
					FileOutputStream output = new FileOutputStream(newPath + "/" + (temp.getName()).toString());
					byte[] b = new byte[1024 * 5];
					int len;
					while ((len = input.read(b)) != -1) {
						output.write(b, 0, len);
					}
					output.flush();
					output.close();
					input.close();
				}
				if (temp.isDirectory()) { // 如果是子文件夹
					copyFolder(oldPath + "/" + file[i], newPath + "/" + file[i]);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除文件夹里面的所有文件
	 * 
	 * @param path
	 * String 文件夹路径 如 c:/fqf
	 */
	public static void deleteAllFileInFolder(String path) {
		File file = new File(path);
		if (!file.exists()) {
			return;
		}
		if (!file.isDirectory()) {
			return;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				deleteAllFileInFolder(path + "/" + tempList[i]); // 先删除文件夹里面的文件
				deleteFolder(path + "/" + tempList[i]); // 再删除空文件夹
			}
		}
	}

	/**
	 * 删除文件
	 * 
	 * @param filePathAndName
	 * String 文件路径及名称 如c:/fqf.txt
	 * @param fileContent
	 * String
	 * @return boolean
	 */
	public static void deleteFile(String filePathAndName) {
		try {
			File myDelFile = new File(filePathAndName);
			myDelFile.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 删除文件夹
	 * 
	 * @param filePathAndName
	 * String 文件夹路径及名称 如c:/fqf
	 * @param fileContent
	 * String
	 * @return boolean
	 */
	public static void deleteFolder(String folderPath) {
		try {
			deleteAllFileInFolder(folderPath); // 删除完里面所有内容
			String filePath = folderPath;
			filePath = filePath.toString();
			File myFilePath = new File(filePath);
			myFilePath.delete(); // 删除空文件夹
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 由给定的文件，得到文件名和扩展名
	 * 
	 * @param filename
	 * String 文件名
	 * @return String[] 元素1是文件名，元素2是扩展名
	 * @since 2006-9-4 下午10:41:28
	 * @author
	 */
	public static String[] getFileName(String filename) {
		String[] rtn = new String[2];
		rtn[0] = filename.substring(0, filename.lastIndexOf("."));
		rtn[1] = filename.substring(filename.lastIndexOf(".") + 1);
		return rtn;
	}

	/**
	 * 取得某个文件夹下的所有文件，不包括子文件夹
	 * 
	 * @param folder
	 * String 文件夹全路径
	 * @return String[] 文件名称数组
	 * @since 2006-9-4 下午10:21:13
	 * @author
	 */
	public static String[] getFiles(String folder) {

		String[] files = {};
		try {
			File a = new File(folder);
			files = a.list();
			// File temp = null;
			// for (int i = 0; i < files.length; i++) {
			// if (folder.endsWith(File.separator)) {
			// temp = new File(folder + files[i]);
			// } else {
			// temp = new File(folder + File.separator + files[i]);
			// }
			//
			// if (temp.isFile()) {
			// System.out.println(temp);
			// }
			// }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return files;
	}

	/**
	 * 统计文件夹中文件内容的行数。
	 * 
	 * @param path
	 * void
	 */
	public static int getFilesLineCount(String path) {
		try {
			File a = new File(path);
			String[] file = a.list();
			File temp = null;
			for (int i = 0; i < file.length; i++) {
				if (path.endsWith(File.separator)) {
					temp = new File(path + file[i]);
				} else {
					temp = new File(path + File.separator + file[i]);
				}

				if (temp.isFile()) {
					if (temp.getName().endsWith("java") || temp.getName().endsWith("jsp")) {
						BufferedReader br = new BufferedReader(new FileReader(temp));
						int lineCount = 1;
						String s = "";
						while (true) {
							s = br.readLine();
							if (s == null)
								break;
							lineCount++;
						}
						br.close();

						line += lineCount;
					}
				}
				if (temp.isDirectory()) { // 如果是子文件夹
					getFilesLineCount(path + "/" + file[i]);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return line;
	}

	/**
	 * Get remote file
	 * 
	 * @param fileUrl
	 * String
	 * @return String
	 * @throws IOException
	 */
	public static String getRemoteFile(String fileUrl) {
		java.net.URL urlfile = null;
		HttpURLConnection httpUrl = null;
		BufferedInputStream bis = null;
		String fileContent = null;
		try {
			// 连接指定的网络资源,获取网络输入流
			urlfile = new java.net.URL(fileUrl);
			// System.out.println(fileUrl);
			httpUrl = (HttpURLConnection) urlfile.openConnection();
			httpUrl.connect();
			bis = new BufferedInputStream(httpUrl.getInputStream());

			byte[] b = new byte[1024];
			while (bis.read(b) != -1) {
				fileContent = new String(b);
			}
			fileContent = fileContent.trim();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				bis.close();
				httpUrl.disconnect();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return fileContent;
	}

	/**
	 * 判断文件是否存在
	 * 
	 * @param filename
	 * String 文件名
	 * @return boolean
	 * @author
	 */
	public static boolean isFileExist(String filename) {
		File file = new File(filename);
		return file.exists();
	}

	public static void main(String[] args) {
		try {
			fileToZIP("d://resume/cc.zip", "d://resume/bb");
			
		} catch (Exception e) {
			e.printStackTrace();
		}  
	}

	/**
	 * 移动文件到指定目录
	 * 
	 * @param oldPath
	 * String 如：c:/fqf.txt
	 * @param newPath
	 * String 如：d:/fqf.txt
	 */
	public static void moveFile(String oldPath, String newPath) {
		copyFile(oldPath, newPath);
		deleteFile(oldPath);
	}

	/**
	 * 移动文件到指定目录
	 * 
	 * @param oldPath
	 * String 如：c:/fqf.txt
	 * @param newPath
	 * String 如：d:/fqf.txt
	 */
	public static void moveFolder(String oldPath, String newPath) {
		copyFolder(oldPath, newPath);
		deleteFolder(oldPath);
	}

	/**
	 * 新建文件
	 * 
	 * @param filePathAndName
	 * String 文件路径及名称 如c:/fqf.txt
	 * @param fileContent
	 * String 文件内容
	 * @return boolean
	 */
	public static void newFile(String filePathAndName, String fileContent) {

		try {
			File myFilePath = new File(filePathAndName);
			if (!myFilePath.exists()) {
				myFilePath.createNewFile();
			}
			FileWriter resultFile = new FileWriter(myFilePath);
			PrintWriter myFile = new PrintWriter(resultFile);
			String strContent = fileContent;
			myFile.println(strContent);
			resultFile.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 新建目录
	 * 
	 * @param folderPath
	 * String 如 c:/fqf
	 * @return boolean
	 */
	public static void newFolder(String folderPath) {
		String[] folders = folderPath.split("/");
		String folder = folders[0] + "/";
		File myFilePath = null;
		try {
			for (int i = 1; i < folders.length; i++) {
				folder = folder + folders[i] + "/";
				myFilePath = new File(folder);
				if (!myFilePath.exists()) {
					myFilePath.mkdir();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 新建目录
	 * 
	 * @param folderPath
	 * String 如 c:/fqf
	 * @return boolean
	 */
	public static void newFolders(String folderPath) {
		File myFilePath = null;
		try {
			myFilePath = new File(folderPath);
			if (!myFilePath.exists()) {
				myFilePath.mkdirs();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 读取模板文件
	 * 
	 * @param templatePath
	 * 模板相对路径
	 * @return
	 * @throws IOException
	 */
	public String readTemplate(String templatePath) throws IOException {
		String result = "";
		File file = new File(templatePath);
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line;
		while ((line = reader.readLine()) != null) {
			result += line;
		}
		// InputStream fileinputstream = this.getClass().getResourceAsStream(templatePath);// 读取模块文件
		// int lenght = fileinputstream.available();
		// byte bytes[] = new byte[lenght];
		// fileinputstream.read(bytes);
		// fileinputstream.close();
		// String templateContent = new String(bytes);
		// return templateContent.toString();
		return result;
	}

	/**
	 * 写文件--xieyan
	 * 
	 * @param filePathAndName
	 * @param content
	 * @throws Exception
	 */
	public static void writeStringToFile(String filePathAndName, String content) throws Exception {
		FileOutputStream fileoutputstream = new FileOutputStream(filePathAndName);
		byte tag_bytes[] = content.getBytes();
		fileoutputstream.write(tag_bytes);
		fileoutputstream.close();
	}

	/**
	 * 得到基本路径到WEB-INF
	 * 
	 * @Title: getBasePath
	 * @data:2012-7-24下午06:16:25
	 * @author:zxd
	 * 
	 * @throws Exception
	 */
	public static String getBase_class() throws Exception {
		String basePath = FileHelper.class.getResource("/").getPath();
		basePath = basePath.substring(0, basePath.indexOf("classes"));
		return basePath;
	}

	/**
	 * 得到基本路径到webroot
	 * 
	 * @Title: getBaseWebInf
	 * @data:2012-7-24下午06:27:26
	 * @author:zxd
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String getBase_WebInf() throws Exception {
		String basePath = FileHelper.class.getResource("/").getPath();
		basePath = basePath.substring(0, basePath.indexOf("WEB-INF") - 1);
		return basePath;
	}

	/**
	 * 得到内容
	 * 
	 * @Title: getContent
	 * @data:2012-8-7上午10:38:53
	 * @author:zxd
	 * 
	 * @param file
	 * @param encodType
	 * @return
	 * @throws IOException
	 */
	public String getContent(String file, String encodType) throws IOException {
		// "xx\r\n" read -> "xx"
		StringBuffer content = new StringBuffer();
		FileInputStream fis = new FileInputStream(file);
		DataInputStream dis = new DataInputStream(fis);
		BufferedReader br = new BufferedReader(new InputStreamReader(dis, encodType));
		String line = null;
		if ((line = br.readLine()) != null) {
			content.append(line);
		}
		while ((line = br.readLine()) != null) {
			content.append("\r\n" + line);
		}
		br.close();
		dis.close();
		fis.close();
		return content.toString();
	}
	
	/**
	 * 将文件打成ZIP包
	 * 
	 * @param zipName
	 * zip包名带路径(例：D:/Demo.zip)
	 * @param directoryPath
	 * 文件所在目录路径(d:/directory)
	 */
	public static void fileToZIP(String zipName, String directoryPath) throws Exception {
		File dir = new File(directoryPath);
		if (!dir.exists()) { throw new NullPointerException("文件目录不存在！"); }
		File[] files = dir.listFiles();
		if(null==files || 0==files.length){ throw new NullPointerException("文件目录下没有文件！"); }

		byte[] buffer = new byte[1024];
		// 生成的ZIP文件名为XXXX.zip
		ZipOutputStream out = null;
		try {
			out = new ZipOutputStream(new FileOutputStream(zipName));
			for (File file : files) {
				FileInputStream fis = null;
				try {
					fis = new FileInputStream(file);
					out.putNextEntry(new ZipEntry(file.getName()));
					int len;
					// 读入需要打包的文件内容，打包到zip文件
					while ((len = fis.read(buffer)) > 0) {
						out.write(buffer, 0, len);
					}
				} catch (Exception e) {
					System.out.println(">>>>>> 文件压缩失败：" + e.getMessage());
					throw e;
				}finally{
					out.closeEntry();
					if(null!=fis){
						fis.close();
						fis = null;
					}
					if (null != file) {
						file.delete(); // 写完后删除对应文件
						file = null;
					}
				}
			}
		} catch (Exception e) {
			throw e;
		}finally{
			if(null!=out){
				out.close();
				out = null;
			}
		}
	}
	
	/**
	 * 将文件打成ZIP包
	 * 
	 * @param zipName
	 * zip包名带路径(例：D:/Demo.zip)
	 * @param files
	 * 文件Path集合(d:/testFile.xml,d:/testFile.html,d:/BeautyGirl.gif)
	 */
	public static void fileToZIP(String zipName, List<String> files) throws Exception {
		byte[] buffer = new byte[1024];
		// 生成的ZIP文件名为XXXX.zip
		ZipOutputStream out = null;
		try {
			out = new ZipOutputStream(new FileOutputStream(zipName));
			// 需要同时打包的多个文件例：result.txt ,source.txt
			for (int i = 0; i < files.size(); i++) {
				File file = null;
				FileInputStream fis = null;
				try {
					file = new File(files.get(i));
					fis = new FileInputStream(file);
					out.putNextEntry(new ZipEntry(file.getName()));
					int len;
					// 读入需要打包的文件内容，打包到zip文件
					while ((len = fis.read(buffer)) > 0) {
						out.write(buffer, 0, len);
					}
				} catch (Exception e) {
					System.out.println(">>>>>> 文件："+files.get(i));
					System.out.println(">>>>>> 文件压缩失败：" + e.getMessage());
					throw e;
				}finally{
					out.closeEntry();
					if(null!=fis){
						fis.close();
						fis = null;
					}
					if (null != file) {
						file.delete(); // 写完后删除对应文件
						file = null;
					}
				}
			}
		} catch (Exception e) {
			System.out.println(">>>>>> 文件压缩失败：" + e.getMessage());
			throw e;
		}finally{
			if(null!=out){
				out.close();
				out = null;
			}
		}
	}

	/**
	 * ZIP方法压缩
	 * 
	 * @param data
	 * 原始数据
	 * @return 压缩数据
	 */
	public static byte[] compressZIP(byte[] data) {
		byte[] output = new byte[0];
		Deflater compresser = new Deflater();
		compresser.reset();
		compresser.setInput(data);
		compresser.finish();
		ByteArrayOutputStream bos = new ByteArrayOutputStream(data.length);
		byte[] buf = new byte[1024];
		while (!compresser.finished()) {
			int i = compresser.deflate(buf);
			bos.write(buf, 0, i);
		}
		output = bos.toByteArray();
		try {
			bos.close();
		} catch (IOException e) {
		}
		compresser.end();
		return output;
	}

	/**
	 * ZIP方法解压<br />
	 * 当读取的字节格式不是ZIP时,该方法返回NULL
	 * 
	 * @param data
	 * 压缩数据
	 * @return 原始数据
	 */
	public static byte[] decompressZIP(byte[] data) {
		byte[] output = new byte[0];
		Inflater decompresser = new Inflater();
		decompresser.reset();
		decompresser.setInput(data);
		ByteArrayOutputStream o = new ByteArrayOutputStream(data.length);
		byte[] buf = new byte[1024];
		try {
			while (!decompresser.finished()) {
				int i = decompresser.inflate(buf);
				o.write(buf, 0, i);
			}
		} catch (DataFormatException e) {
			return null;
		}
		output = o.toByteArray();
		try {o.close();} catch (IOException e) {}
		decompresser.end();
		return output;
	}
	
	/**
	 * 创建文件
	 * 
	 * @param path
	 * 文件路径，该路径会自动创建
	 * @param fileName
	 * 文件名称，带后缀
	 * @param content
	 * 文本内容
	 * @param flag
	 * 同名是否新建，true为新建，false为直接覆盖原文件
	 * @param format
	 * 文件后缀(例： .txt .xml .html)
	 * @return 新文件的存放路径
	 */
	public static String createFile(String path, String fileName,
			String content, Boolean flag, String format) throws Exception {
		File file = new File(path + fileName);
		File folder = new File(path);
		String nfn = new String();
		if (folder.exists()) {
			if (flag) {
				// 判断文件是否存在
				if (file.exists()) {
					String[] fn = fileName.split("\\.");
					nfn = fn[0] + "_" + getName(5) + format;
					file = new File(path + nfn);
				} else {
					nfn = fileName;
					file.createNewFile();// 不存在则创建
				}
			} else {
				nfn = fileName;
			}
		} else {
			folder.mkdirs();
			file.createNewFile();
			nfn = fileName;
		}
		BufferedWriter output = new BufferedWriter(new FileWriter(file));
		output.write(content);
		output.close();
		return path + nfn;
	}

	/**
	 * 创建文件
	 * 
	 * @param path
	 * 文件存放路径
	 * @param fileName
	 * 文件名称
	 * @param content
	 * 文件内容二进制数组
	 * @param flag
	 * 同名是否新建，true为新建，false为直接覆盖原文件
	 * @param format
	 * 文件后缀(例： .txt .xml .html)
	 * @return 新文件的存放路径
	 */
	public static String createFile(String path, String fileName,
			byte[] content, Boolean flag, String format) throws Exception {
		File file = new File(path + fileName);
		File folder = new File(path);
		String nfn = new String();
		if (folder.exists()) {
			if (flag) {
				// 判断文件是否存在
				if (file.exists()) {
					String[] fn = fileName.split("\\.");
					nfn = fn[0] + "_" + getName(5) + format;
					file = new File(path + nfn);
				} else {
					nfn = fileName;
					file.createNewFile();// 不存在则创建
				}
			} else {
				nfn = fileName;
			}
		} else {
			folder.mkdirs();
			file.createNewFile();
			nfn = fileName;
		}
		OutputStream fos = new FileOutputStream(file);
		fos.write(content);
		fos.close();
		return path + nfn;

	}

	/**
	 * 获取由数据和字母组成的随机数
	 * 
	 * @param length
	 * 数据长度限制
	 * @return 随机数
	 */
	public static String getName(int length) {

		Random rd = new Random();
		StringBuffer sb = new StringBuffer();
		int rdGet; // 取得随机数

		char ch;
		for (int i = 0; i < length; i++) {
			if (rd.nextBoolean()) {
				rdGet = Math.abs(rd.nextInt()) % 10 + 48; // 产生48到57的随机数(0-9的键位值)
			} else {
				rdGet = Math.abs(rd.nextInt()) % 26 + 97; // 产生97到122的随机数(a-z的键位值)
			}
			ch = (char) rdGet;
			sb.append(ch);
		}
		return sb.toString();
	}

	/**
	 * @param bufferedImage
	 * bufferedImage
	 * @return
	 * @throws IOException
	 *  IOException
	 */
	public static byte[] getImageBytes(BufferedImage bufferedImage)
			throws IOException {
		return getImageBytes(bufferedImage, 1.0f);
	}

	/**
	 * 根据路径获取文件字节数组
	 * 
	 * @param path
	 * 文件路径
	 * @return byte[](为空是返回NULL值)
	 */
	public static byte[] getImageBytes(String path) {
		byte[] imageArray = null;
		try {
			BufferedInputStream in = new BufferedInputStream(
					new FileInputStream(path));
			byte[] temp = new byte[in.available()];
			ByteArrayOutputStream out = new ByteArrayOutputStream(
					in.available());
			int size = 0;
			while ((size = in.read(temp)) != -1) {
				out.write(temp, 0, size);
			}
			in.close();
			imageArray = out.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取图片在本地生成新的图片
	 * 
	 * @param urlPath
	 * 远程URL (例：http://www.baidu.com/img/bdlogo.gif)
	 * @param localPath
	 * 新存储本地路径(例：d:/BeautyGirl.gif)
	 */
	public static void createImage(String urlPath, String localPath) throws Exception {
		File imageFile = null;
		InputStream inStream = null;
		FileOutputStream outStream = null;
		try {
			// new一个URL对象
			URL url = new URL(urlPath);
			// 打开链接
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			// 设置请求方式为"GET"
			conn.setRequestMethod("GET");
			// 超时响应时间为5秒
			conn.setConnectTimeout(5 * 1000);
			// 通过输入流获取图片数据
			inStream = conn.getInputStream();
			// 得到图片的二进制数据，以二进制封装得到数据，具有通用性
			byte[] data = readInputStream(inStream);
			//data = unGZip(data);
			//data = decompressZIP(data);
			if (null == data || 100 >= data.length) {
				new NullPointerException("图片数据无法获取");
			}
			// new一个文件对象用来保存图片，默认保存当前工程根目录
			imageFile = new File(localPath);
			// 创建输出流
			outStream = new FileOutputStream(imageFile);
			// 写入数据
			outStream.write(data);
			/*
			 * //关闭输出流 outStream.close();
			 */
		} catch (Exception e) {
			if (null != imageFile) {
				imageFile.delete();
			}
			throw e;
		} finally {
			if (null != outStream) {
				outStream.close();
				outStream = null;
			}
			if (null != inStream) {
				inStream.close();
				inStream = null;
			}
		}

	}

	public static byte[] readInputStream(InputStream inStream)throws Exception{
		byte[] newBuffer = null;
		ByteArrayOutputStream outStream = null;
		try {
			outStream = new ByteArrayOutputStream();
			// 创建一个Buffer字符串
			byte[] buffer = new byte[1024];
			// 每次读取的字符串长度，如果为-1，代表全部读取完毕
			int len = 0;
			int index = 0;
			// 使用一个输入流从buffer里把数据读取出来
			while ((len = inStream.read(buffer)) != -1) {
				// 用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
				outStream.write(buffer, 0, len);
				index++;
			}
			// 关闭输入流
			inStream.close();
			inStream = null;
			if (0 == index || 1==index) {
				outStream.close();
				outStream = null;
				return null;
			}
			// 把outStream里的数据写入内存
			newBuffer = outStream.toByteArray();
		} catch (Exception e) {
			throw e;
		}finally{
			if(null!=inStream){
				try {inStream.close(); inStream = null;} catch (IOException e) {}
			}
			if(null!=outStream){
				try {outStream.close(); outStream = null;} catch (IOException e) {}
			}
		}
		return newBuffer;
	}

	/**
	 * @param bufferedImage
	 * bufferedImage
	 * @param compressionQuality
	 * compressionQuality
	 * @return
	 */
	public static byte[] getImageBytes(BufferedImage bufferedImage,
			float compressionQuality) {
		if (bufferedImage == null) {
			return null;
		}
		ImageTypeSpecifier type = ImageTypeSpecifier
				.createFromRenderedImage(bufferedImage);
		Iterator<ImageWriter> iter = ImageIO.getImageWriters(type, "jpg");
		ImageWriter writer = (ImageWriter) iter.next();
		if (writer == null) {
			return null;
		}
		IIOImage iioImage = new IIOImage(bufferedImage, null, null);
		ImageWriteParam param = writer.getDefaultWriteParam();

		param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
		param.setCompressionQuality(compressionQuality);

		param.setSourceBands(new int[] { 0, 1, 2 });
		// specify the correct desination type without alpha:
		param.setDestinationType(ImageTypeSpecifier.createPacked(
				ColorSpace.getInstance(ColorSpace.CS_sRGB), 0x00ff0000,
				0x0000ff00, 0x000000ff, 0x0, DataBuffer.TYPE_INT, false));

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			ImageOutputStream outputStream = ImageIO
					.createImageOutputStream(out);
			writer.setOutput(outputStream);
			writer.write(null, iioImage, param);
			return out.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static byte[] unGZip(byte[] data) {
		if(null==data || 0==data.length){return null;}
		System.out.println(data.length);
		byte[] b = null;
		ByteArrayInputStream bis = null;
		GZIPInputStream gzip = null;
		ByteArrayOutputStream baos = null;
		try {
			bis = new ByteArrayInputStream(data);
			gzip = new GZIPInputStream(bis);
			byte[] buf = new byte[1024];
			int num = -1;
			baos = new ByteArrayOutputStream();
			while ((num = gzip.read(buf, 0, buf.length)) != -1) {
				baos.write(buf, 0, num);
			}
			b = baos.toByteArray();
		} catch (Exception ex) {
			ex.printStackTrace();
		}finally{
			try {
				if(null!=baos){
					baos.flush();
					baos.close();
				}
				if(null!=gzip){
					gzip.close();
				}
				if(null!=bis){
					bis.close();
				}
			} catch (IOException e) {}
		}
		return b;
	}

	public static byte[] getFileByte(String filePath) throws Exception{
		if(null==filePath || "".equals(filePath)){ return null;}
		byte[] buffer = null;
		FileInputStream fis = null;
		ByteArrayOutputStream bos = null;
		int index = 0;
		try
		{
			File file = new File(filePath);
			fis = new FileInputStream(file);
			bos = new ByteArrayOutputStream();
			byte[] b = new byte[1024];
			int n;
			while ((n = fis.read(b)) != -1)
			{
				bos.write(b, 0, n);
				index++;
			}
			// 关闭输入流
			fis.close();
			fis = null;
			if (0 == index || 1==index) {
				bos.close();
				bos = null;
				return null;
			}
			buffer = bos.toByteArray();
		} catch(Exception e){
			throw e;
		} finally{
			if(null!=fis){
				try {fis.close(); fis = null;} catch (IOException e) {}
			}
			if(null!=bos){
				try {bos.close(); bos = null;} catch (IOException e) {}
			}
		}
		return buffer;
	}

}
