package cn.tools;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import javax.imageio.ImageIO;
import org.apache.commons.io.output.ByteArrayOutputStream;
import sun.misc.BASE64Encoder;

/**
 * 
 * @author zxd
 * @date 2012-9-27
 */
public abstract class ImageHelper {

	@SuppressWarnings("unused")
	private static final String DEFAULT_IMAGE_FORMAT = ".jpg"; // 图像文件的格式

	/**
	 * 设置图片压缩质量枚举类；
	 * 
	 * @author zxd
	 * @date 2012-9-27 上午10:36:52
	 */
	public enum ImageQuality {

		max(1.0f), high(0.75f), medium(0.5f), low(0.25f);

		private Float quality;

		public Float getQuality() {
			return this.quality;
		}

		ImageQuality(Float quality) {
			this.quality = quality;
		}
	}

	private static Image image;

	/**
	 * 
	 * @Title: getScaling通过目标对象的大小和标准（指定）大小计算出图片缩小的比例
	 * @data:2012-9-27上午10:40:04
	 * @author:zxd
	 * 
	 * @param targetWidth目标的宽度
	 * @param targetHeight目标的高度
	 * @param standardWidth标准
	 * （指定）宽度
	 * @param standardHeight标准
	 * （指定）高度
	 * @return最小的合适比例
	 */
	public static double getScaling(double targetWidth, double targetHeight, double standardWidth, double standardHeight) {
		double widthScaling = 0d;
		double heightScaling = 0d;
		if (targetWidth > standardWidth) {
			widthScaling = standardWidth / (targetWidth * 1.00d);
		} else {
			widthScaling = 1d;
		}
		if (targetHeight > standardHeight) {
			heightScaling = standardHeight / (targetHeight * 1.00d);
		} else {
			heightScaling = 1d;
		}
		return Math.min(widthScaling, heightScaling);	
	}
	/**
	 * 通过指定的比例和图片对象，返回一个放大或缩小的宽度、高度
	 * @Title: getSize
	 * @data:2012-9-27上午10:55:45
	 * @author:zxd
	 *
	 * @param scale
	 * @param image
	 * @return
	 */
	public static int[] getSize(float scale, Image image){
		 int targetWidth = image.getWidth(null); 
		 int targetHeight = image.getHeight(null);
		 long standardWidth = Math.round(targetWidth * scale); 
		 long standardHeight = Math.round(targetHeight * scale);
		 return new int[] { Integer.parseInt(Long.toString(standardWidth)), Integer.parseInt(Long.toString(standardHeight))};
	}
	/**
	 * 通过指定大小和图片的大小，计算出图片缩小的合适大小
	 * @Title: getSize
	 * @data:2012-9-27上午11:15:21
	 * @author:zxd
	 *
	 * @param width
	 * @param height
	 * @param image
	 * @return
	 */
	 public static int[] getSize(int width, int height, Image image){
		 int targetWidth = image.getWidth(null);
		 int targetHeight = image.getHeight(null);
		 double scaling = getScaling(targetWidth, targetHeight, width, height);
		 long standardWidth = Math.round(targetWidth * scaling);
		 long standardHeight = Math.round(targetHeight * scaling);
		 return new int[] { Integer.parseInt(Long.toString(standardWidth)), Integer.parseInt(Long.toString(standardHeight))};
	 }
	
	/**
	 * 指定一个宽度，返回一个放大或缩小的图片
	 * @Title: getSize
	 * @data:2012-9-27上午10:59:50
	 * @author:zxd
	 *
	 * @param width
	 * @param image
	 * @return
	 */
	 public static int[] getSize(int width, Image image)throws Exception{
		 int targetWidth = image.getWidth(null); 
		 int targetHeight = image.getHeight(null);
		 long height = Math.round((targetHeight * width) / (targetWidth * 1.00f));
		 return new int[] {width, Integer.parseInt(String.valueOf(height)) };
	 }
	 /**
	  * 指定一个高度
	  * @Title: getSizeByHeight
	  * @data:2012-9-27上午11:03:18
	  * @author:zxd
	  *
	  * @param height
	  * @param image
	  * @return
	  */
	 public static int[] getSizeByHeight(int height, Image image){
		 int targetWidth = image.getWidth(null); 
		 int targetHeight = image.getHeight(null);
		 long width = Math.round((targetWidth * height) / (targetHeight * 1.00f));
		 return new int[] { Integer.parseInt(String.valueOf(width)), height };
	 }
	 
	 /**
	  *  将一个本地的图片文件按照指定的比例进行缩放
	  * @Title: resize
	  * @data:2012-9-27上午11:25:27
	  * @author:zxd
	  *
	  * @param scale
	  * @param savePath
	  * @param targetFile
	  * @return
	  */
	public static BufferedImage resize(float scale, File targetFile){
		int[] size  =null;
		try {
			 image = ImageIO.read(targetFile);
			 size = getSize(scale, image);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		  return resize(size[0], size[1], image);
		
	}
	
	/**
	 * 将Image的宽度、高度缩放到指定width、height，并保存在savePath目录
	 * @Title: resize
	 * @data:2012-9-27上午11:24:48
	 * @author:zxd
	 *
	 * @param width
	 * @param height
	 * @param savePath
	 * @param targetImage
	 * @return
	 */
	public static BufferedImage resize(int width, int height,Image targetImage){
		 width = Math.max(width, 1); 
		 height = Math.max(height, 1);
		 BufferedImage image = null;
		 try {
			 image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			 image.getGraphics().drawImage(targetImage, 0, 0, width, height, null);
		} catch (Exception e) {
			// TODO: handle exception
		}
		 return image;
	}
	
	/**
	 * 将指定的targetFile图片文件的宽度、高度大于指定width、height的图片缩小
	 * @Title: resize
	 * @data:2012-9-27上午11:26:07
	 * @author:zxd
	 *
	 * @param width
	 * @param height
	 * @param savePath
	 * @param targetFile
	 * @return
	 */
	public static BufferedImage resize(int width, int height, File targetFile){
		int[] size = null;
		try {
			 image = ImageIO.read(targetFile);
			size = getSize(width, height, image);
		} catch (Exception e) {
		}
		 return resize(size[0], size[1], image);
	}
	/**
	 *  将指定的targetURL网络图片文件的宽度、高度大于指定width、height的图片缩小
	 * @Title: resize
	 * @data:2012-9-27下午02:07:02
	 * @author:zxd
	 *
	 * @param width
	 * @param height
	 * @param targetURL
	 * @return
	 */
	public static BufferedImage resize(int width,int height, URL targetURL){
		int[] size = null; 
		try {
			image = ImageIO.read(targetURL);
			size = getSize(width, height, image);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resize(size[0], size[1], image);
	}
	/**
	 * 将一个网络图片文件按照指定的比例进行缩放
	 * @Title: resize
	 * @data:2012-9-27下午12:27:09
	 * @author:zxd
	 *
	 * @param width
	 * @param height
	 * @param savePath
	 * @param targetURL
	 * @return
	 */
	public static BufferedImage resize(float scale, URL targetURL) {
		int[] size = null;
		try {
			image = ImageIO.read(targetURL);
			size = getSize(scale, image);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resize(size[0], size[1], image);
	}
	/**
	 * 按照固定宽度进行等比缩放本地图片
	 * @Title: resize
	 * @data:2012-9-27下午12:31:25
	 * @author:zxd
	 *
	 * @param width
	 * @param savePath
	 * @param targetFile
	 * @return
	 */
	public static BufferedImage resize(int width, File targetFile){
		 int[] size = null;
		try {
	  	  image = ImageIO.read(targetFile);
	  	  size = getSize(width, image);
		} catch (Exception e) {
		}
	  	return resize(size[0], size[1], image);
	}
	/**
	 * 按照固定宽度进行等比缩放网络图片
	 * @Title: resize
	 * @data:2012-9-27下午12:33:18
	 * @author:zxd
	 *
	 * @param width
	 * @param savePath
	 * @param targetURL
	 * @return
	 */
	public static BufferedImage resize(int width,  URL targetURL){
		int[] size  = null;
		try {
			size = getSize(width, image);
			image = ImageIO.read(targetURL);

		} catch (Exception e) {
		}
		 return resize(size[0], size[1], image);
	}
	/**
	 * 按照固定高度进行等比缩放本地图片
	 * @Title: resizeByHeight
	 * @data:2012-9-27下午12:34:52
	 * @author:zxd
	 *
	 * @param height
	 * @param savePath
	 * @param targetFile
	 * @return
	 */
	public static BufferedImage resizeByHeight(int height, File targetFile){
		int[] size = null;
		try {
			image = ImageIO.read(targetFile);
			size = getSizeByHeight(height, image);

		} catch (Exception e) {
			// TODO: handle exception
		}
		return resize(size[0], size[1], image);
	}
	/**
	 * 按照固定高度进行等比缩放网络图片
	 * @Title: resizeByHeight
	 * @data:2012-9-27下午01:52:03
	 * @author:zxd
	 *
	 * @param height
	 * @param savePath
	 * @param targetURL
	 * @return
	 */
	public static BufferedImage resizeByHeight(int height,  URL targetURL){
		int[] size  = null;
		try {
			image = ImageIO.read(targetURL);
			if (image!=null) {
				 size = getSizeByHeight(height, image);
			}
		} catch (Exception e) {
			return null;
		}
		if (size==null) {
			return null;
		}
		return resize(size[0], size[1], image);
	}
	/**
	 * 缩放图片
	 * @Title: resizeImage
	 * @data:2013-8-19下午6:02:23
	 * @author:wxy
	 *
	 * @param url
	 * @param width
	 * @param height
	 * @return
	 */
	public static BufferedImage resizeImage(URL url, double width, double height){
		BufferedImage bufferedImage = null;
		BufferedImage tag = null;
		try {
			bufferedImage = ImageIO.read(url);
			int targetWidth = bufferedImage.getWidth();
			int targetHeight = bufferedImage.getHeight();
			double scale = 1l;
			if(targetWidth>width || targetHeight >height){
				scale = getScaling(targetWidth, targetHeight, width, height);
			}
			width = targetWidth*scale;
			height = targetHeight*scale;
 Image image = bufferedImage.getScaledInstance((int)width, (int)height, Image.SCALE_DEFAULT);
 tag = new BufferedImage((int)width, (int)height,BufferedImage.TYPE_INT_RGB);
 Graphics g = tag.getGraphics();
 g.drawImage(image, 0, 0, null); // 绘制缩小后的图
 g.dispose();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tag;
	}
	
	
	public static BufferedImage resizeImage(String urlString){
		BufferedImage bufferedImage = null;
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		URL url;
		try {
			System.out.println(urlString);
			url = new URL(urlString);
			Image srcImage = toolkit.getImage(url); // 构造Image对象
			int width = -1;
			int height = -1;
			boolean flag = true;
			while (flag) {
				width = srcImage.getWidth(null); // 得到源图宽
				height = srcImage.getHeight(null); // 得到源图长
				if (width > 0 && height > 0) { // 因为 Toolkit.getImage 是异步读取，如果
												// wideth 和 height
												// 都大于0，表明图片已经加载完毕
				flag = false;
				} else {
					try {
						Thread.sleep(10);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			if (width>1000 || height >1000) {
//				float s = (float)height/width;
//				width = 100;
//				height = (int)((float)width*s);
//				bufferedImage = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
//				boolean flag2 = false;
//				while (!flag2) {
//					try {
//						flag2 = bufferedImage.getGraphics().drawImage(srcImage, 0, 0, width, height, null);
//						Thread.sleep(10);
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//				}
			}else {
				bufferedImage = resizeByHeight(700, url);
			}
		} catch (MalformedURLException e) {
			return null;
		}
		return bufferedImage;
	}
	/**
	 * 合并两张图片
	 * @Title: mergeImage
	 * @data:2013-10-14下午12:37:33
	 * @author:lilei
	 *
	 * @param fileOne
	 * @param fileTwo
	 * @param h 两图片间宽度
	 * @param lineHeigh 画线高度
	 */
	public static void mergeImage(File fileOne,File fileTwo,int h,float lineHeigh, String imageType) throws Exception{
		// 读取第一张图片
		BufferedImage imageOne = ImageIO.read(fileOne);
		int width = imageOne.getWidth();// 图片宽度
		int height = imageOne.getHeight();// 图片高度
		
		// 对第二张图片做相同的处理
		BufferedImage imageTwo = ImageIO.read(fileTwo);
		int widthTwo = imageTwo.getWidth();
		int heightTwo = imageTwo.getHeight();
		
		int newWidth = 0;
		//double percent = 1d;
		if(width <= widthTwo){
			//percent = (double)widthTwo/(double)width;
			newWidth = widthTwo;
			//height = (int) (height*percent);
			//imageOne = getScaling(imageOne, width, height);
		}else if(width > widthTwo){
			//percent = (double)width/(double)widthTwo;
			newWidth = width;
			//heightTwo = (int)(percent*heightTwo);
			//imageTwo = getScaling(imageTwo, widthTwo, heightTwo);
		}
		
		// 从图片中读取RGB
		int[] imageArrayOne = new int[newWidth * (height+h)];
		imageArrayOne = imageOne.getRGB(0, 0, width, height, imageArrayOne,	0, width);
		
		int[] imageArrayTwo = new int[newWidth * heightTwo];
		imageArrayTwo = imageTwo.getRGB(0, 0, widthTwo, heightTwo, imageArrayTwo,	0, widthTwo);
		
		// 生成新图片
		BufferedImage imageNew = new BufferedImage(newWidth, height+heightTwo+h,	BufferedImage.TYPE_INT_RGB);
		Graphics g = imageNew.getGraphics();
		g.setColor(new Color(255,255,255));
		g.fillRect(0, 0, newWidth, height+heightTwo+h);
		imageNew.setRGB(0, 0, width, height+h, imageArrayOne, 0, width);// 设置上半部分的RGB
		imageNew.setRGB(0, height+h, widthTwo, heightTwo, imageArrayTwo, 0, widthTwo);// 设置下半部分的RGB
		//ImageNew.setRGB(0, height, width,imageOne.getHeight()+imageTwo.getHeight(), imageArrayTwo, 0, width);// 设置右半部分的RGB
		/*BufferedImage ImageNew = new BufferedImage(width * 2, height, BufferedImage.TYPE_INT_RGB);
			ImageNew.setRGB(0, 0, width, height, imageArrayOne, 0, width);// 设置左半部分的RGB
			ImageNew.setRGB(width, 0, width, height, imageArrayTwo, 0, width);// 设置右半部分的RGB
		 */
		Graphics2D gp = imageNew.createGraphics();
		gp.setStroke(new BasicStroke(8.0f));
		gp.drawLine(0, height, width, height);
		ImageIO.write(imageNew, imageType, fileOne);// 写图片
	}
	/**
	 * 按照固定宽高放的图片
	 * @Title: getScaling
	 * @data:2013-10-14下午12:34:07
	 * @author:lilei
	 *
	 * @param targetImage
	 * @param width
	 * @param height
	 * @return
	 */
	public static BufferedImage getScaling(BufferedImage targetImage,int width,int height){
		width = Math.max(width, 1); 
   	 	height = Math.max(height, 1);
	   	 BufferedImage image = null;
		 try {
			 image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			 image.getGraphics().drawImage(targetImage, 0, 0, width, height, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return image;
	}
	
	/**
	 * 获取图片流字节数组
	 * @author hubaoting
	 * @date 2015年9月15日 上午9:48:26
	 * @param webUrl
	 * @return byte[]
	 */
	public static byte[] getImageByte(String webUrl){
		if(null==webUrl || "".equals(webUrl)){ return null;}
		byte[] data = null;
		try {
			URL url = new URL(webUrl);
			//打开链接
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			//设置请求方式为"GET"
			conn.setRequestMethod("GET");
			//超时响应时间为5秒
			conn.setConnectTimeout(5 * 1000);
			//通过输入流获取图片数据
			InputStream inStream = conn.getInputStream();
			//得到图片的二进制数据，以二进制封装得到数据，具有通用性
			data = readInputStream(inStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	
	/**
	 * 获取区间数的随机数
	 * @author hubaoting
	 * @date 2015年9月15日 下午6:29:30
	 * @param max 最大数
	 * @param min 最小数
	 * @return int 随机区间数
	 */
	public static int getRandom(int max, int min){
		int num = min;
		try {
			num = (int) Math.round(Math.random()*(max-min)+min);
		} catch (Exception e) {}
		return num;
	}
	
	/**
	 * 获取图片流BASE64字符串
	 * @author hubaoting
	 * @date 2015年9月15日 上午9:49:03
	 * @param webUrl
	 * @return String
	 */
	public static String getImageByteBASE64(String webUrl){
		if(null==webUrl || "".equals(webUrl)){ return "";}
		byte[] data = null;
		try {
			URL url = new URL(webUrl);
			//打开链接
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			//设置请求方式为"GET"
			conn.setRequestMethod("GET");
			//超时响应时间为5秒
			conn.setConnectTimeout(5 * 1000);
			//通过输入流获取图片数据
			InputStream inStream = conn.getInputStream();
			//得到图片的二进制数据，以二进制封装得到数据，具有通用性
			data = readInputStream(inStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(null!=data){
			BASE64Encoder encoder = new BASE64Encoder();
			return encoder.encode(data);
		}
		return "";
	}
	
	public static byte[] readInputStream(InputStream inStream){
		ByteArrayOutputStream outStream = null;
		byte[] result = null;
		try {
			outStream = new ByteArrayOutputStream();
			//创建一个Buffer字符串
			byte[] buffer = new byte[1024];
			//每次读取的字符串长度，如果为-1，代表全部读取完毕
			int len = 0;
			//使用一个输入流从buffer里把数据读取出来
			while( (len=inStream.read(buffer)) != -1 ){
				//用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度 
				outStream.write(buffer, 0, len);
			}
			//把outStream里的数据写入内存
			result = outStream.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(null!=outStream){
				try {
					outStream.close();
				} catch (IOException e) { e.printStackTrace(); }
			}
			if(null!=inStream){
				//关闭输入流
				try {
					inStream.close();
				} catch (IOException e) { e.printStackTrace(); }
			}
		}
		return result;
	}
	
	/**
	 * 获取网络上的图片
	 * @Title: getImageFromUrl
	 * @data:2015年2月27日上午9:57:54
	 * @author:lilei
	 * @param link
	 * @return
	 */
	public static BufferedImage getImageFromUrl(String link){
		InputStream in = null;
		BufferedImage image = null;
		try {
			//new一个URL对象  
			URL url = new URL(link);  
			//打开链接  
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();  
			//设置请求方式为"GET"  
			conn.setRequestMethod("GET");  
			//超时响应时间为5秒  
			conn.setConnectTimeout(5 * 1000);  
			//通过输入流获取图片数据  
			in = conn.getInputStream();
			image = ImageIO.read(in);
		} catch (Exception e) {
			//e.printStackTrace();
			//System.out.println("错误的图片链接：" + link);
		}finally{
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return image;
	}
}
