package cn.tools;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.util.zip.Adler32;
import java.util.zip.CheckedOutputStream;
import org.apache.tools.zip.*;

/**
 * 文件、目录操作工具类
 * 
 * @author xieyan
 * 
 */
public class FileHelper {

	private static int line = 0;

	/**
	 * 复制单个文件
	 * 
	 * @param oldPath
	 *			String 原文件路径 如：c:/fqf.txt
	 * @param newPath
	 *			String 复制后路径 如：f:/fqf.txt
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
	 *			String 原文件路径 如：c:/fqf
	 * @param newPath
	 *			String 复制后路径 如：f:/fqf/ff
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
	 *			String 文件夹路径 如 c:/fqf
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
	 *			String 文件路径及名称 如c:/fqf.txt
	 * @param fileContent
	 *			String
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
	 *			String 文件夹路径及名称 如c:/fqf
	 * @param fileContent
	 *			String
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
	 *			String 文件名
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
	 *			String 文件夹全路径
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
	 *			void
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
	 *			String
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
	 *			String 文件名
	 * @return boolean
	 * @author
	 */
	public static boolean isFileExist(String filename) {
		File file = new File(filename);
		return file.exists();
	}

	public static void main(String[] args) {
		// // line = 0;
		// // System.out.println(getFilesLineCount("F:/hxsd/src"));
		// // line = 0;
		// // System.out.println(getFilesLineCount("F:/hxsd/WebModule"));
		// // line = 0;
		// // System.out.println(getFilesLineCount("E:/eclipse/workspace/newdirect"));
		// // line = 0;
		// // System.out.println(getFilesLineCount("E:/eclipse/workspace/epintang"));
		// System.out
		// .println(getRemoteFile("http://61.132.221.38/newdirect/db.jsp"));
		String sourceFilePath = "d://resume//r//";
		String zipFilePath = "d://resume//";
		//String fileName = "ss2";
	 try {
		 String [] abc = getFileName(zipFilePath);
		  System.out.println(abc[0]);
		  System.out.println(abc[1]);
		//FileHelper.zipCompress(sourceFilePath, zipFilePath);
	} catch (Exception e) {
		e.printStackTrace();
	}  

		boolean flag = FileHelper.fileToZip(sourceFilePath, zipFilePath, "aa4");
		if (flag) {
			System.out.println(">>>>>> 文件打包成功. <<<<<<");
		} else {
			System.out.println(">>>>>> 文件打包成功. <<<<<<");
		}
	}

	/**
	 * 移动文件到指定目录
	 * 
	 * @param oldPath
	 *			String 如：c:/fqf.txt
	 * @param newPath
	 *			String 如：d:/fqf.txt
	 */
	public static void moveFile(String oldPath, String newPath) {
		copyFile(oldPath, newPath);
		deleteFile(oldPath);
	}

	/**
	 * 移动文件到指定目录
	 * 
	 * @param oldPath
	 *			String 如：c:/fqf.txt
	 * @param newPath
	 *			String 如：d:/fqf.txt
	 */
	public static void moveFolder(String oldPath, String newPath) {
		copyFolder(oldPath, newPath);
		deleteFolder(oldPath);
	}

	/**
	 * 新建文件
	 * 
	 * @param filePathAndName
	 *			String 文件路径及名称 如c:/fqf.txt
	 * @param fileContent
	 *			String 文件内容
	 * @return boolean
	 */
	public static void newFile(String filePathAndName, String fileContent) throws Exception {
		File myFilePath = null;
		FileWriter resultFile = null;
		PrintWriter myFile = null;
		try {
			myFilePath = new File(filePathAndName);
			if (!myFilePath.exists()) {
				myFilePath.createNewFile();
			}
			resultFile = new FileWriter(myFilePath);
			myFile = new PrintWriter(resultFile);
			String strContent = fileContent;
			myFile.println(strContent);
		} catch (Exception e) {
			if(null!=myFilePath){ myFilePath.delete(); }
			throw e;
		}finally{
			if(null!=myFile){ myFile.close(); }
			if(null!=resultFile){ resultFile.close(); }
		}
	}

	/**
	 * 新建目录
	 * 
	 * @param folderPath
	 *			String 如 c:/fqf
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
	 *			String 如 c:/fqf
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
	 *			模板相对路径
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
	 * 保存压缩文件
	 * 
	 * @Title: fileToZip
	 * @data:2012-9-13下午03:53:13
	 * @author:sansan
	 * 
	 * @param sourceFilePath
	 * @param zipFilePath
	 * @param fileName
	 * @return
	 */
	public static boolean fileToZip(String sourceFilePath, String zipFilePath, String fileName) {

		boolean flag = false;
		File sourceFile = new File(sourceFilePath);
		FileInputStream fis = null;
		BufferedInputStream bis = null;// 内部缓冲区
		FileOutputStream fos = null;// 文件输出
		ZipOutputStream zos = null;
		if (sourceFile.exists() == false) {
			System.out.println(">>>>>> 待压缩的文件目录：" + sourceFilePath + " 不存在");
		} else {
			try {
				File zipFile = new File(zipFilePath + "/" + fileName + ".zip");
				File[] sourceFiles = sourceFile.listFiles();
				if (sourceFiles == null || sourceFiles.length < 1) {
					System.out.println(">>>>>> 待压缩的文件目录：" + sourceFilePath + " 里面不存在文件,无需压缩. <<<<<<");
				} else {

					fos = new FileOutputStream(zipFile);
					zos = new ZipOutputStream(new BufferedOutputStream(fos));
					byte[] bufs = new byte[1024 * 10];
					for (int i = 0; i < sourceFiles.length; i++) {
						// 创建ZIP实体,并添加进压缩包
						String entryName = sourceFiles[i].getName();
						ZipEntry zipEntry = new ZipEntry(entryName);
						zos.setEncoding("gb2312");
						zos.putNextEntry(zipEntry);
						// 读取待压缩的文件并写进压缩包里
						fis = new FileInputStream(sourceFiles[i]);
						bis = new BufferedInputStream(fis, 1024 * 10);
						int read = 0;
						while ((read = bis.read(bufs, 0, 1024 * 10)) != -1) {
							zos.write(bufs, 0, read);
						}
					}
					flag = true;
				}

			} catch (FileNotFoundException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (bis != null) {
						bis.close();
					}
					if (zos != null) {
						zos.close();
					}
				} catch (IOException e) {
					e.printStackTrace();

				}
			}
		}
		return flag;
	}
	/**
	* 压缩文件夹
	* @Title: zipCompress
	* @data:2012-9-18下午04:36:55
	* @author:zxd
	*
	* @param src：源文件夹	
	* @param des：压缩后的文件夹
	* @throws IOException
	*/
	public static void zipCompress(String src, String des)throws IOException{
		ZipOutputStream out=null;  
		try {  
			CheckedOutputStream cusm = new CheckedOutputStream(new FileOutputStream(des),new Adler32());
			out=new ZipOutputStream(new BufferedOutputStream(cusm));
			out.setEncoding("gb2312");
			fileZip(new File(src), out, "");
		}finally{  
			if(out!=null){  
				out.close();  
			}
		}
	}

	/**
	* 压缩文件夹
	* @Title: fileZip
	* @data:2012-9-18下午04:37:31
	* @author:zxd
	*
	* @param file
	* @param out
	* @param base
	* @throws IOException
	*/
	private static void fileZip(File file, ZipOutputStream out, String base) throws IOException{
		if(file.isFile()){
			if(base.length()>0){
				out.putNextEntry(new ZipEntry(base));
			}else{
				out.putNextEntry(new ZipEntry(file.getName()));
			}
			BufferedReader in=new BufferedReader(  
					new InputStreamReader(new FileInputStream(file), "ISO8859_1"));
			
			int c;
			while((c=in.read())!=-1){
				out.write(c);
			}
			in.close();
			
		}else if(file.isDirectory()){
			File[] subFiles=file.listFiles();
			
			out.putNextEntry(new ZipEntry(base+File.separator));
			base=base.length()!=0?base+File.separator:"";
			for(File subFile:subFiles){
				fileZip(subFile, out,base+subFile.getName());
			}
		}
		
	}	
}
