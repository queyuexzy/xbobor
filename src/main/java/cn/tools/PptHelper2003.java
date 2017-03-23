/*  
 * @(#) PptHelper.java Create on 2012-9-26 上午09:24:47
 *
 * Copyright 2012 by xl.   
 */
package cn.tools;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import org.apache.poi.hslf.model.Line;
import org.apache.poi.hslf.model.Picture;
import org.apache.poi.hslf.model.Slide;
import org.apache.poi.hslf.model.SlideMaster;
import org.apache.poi.hslf.model.Table;
import org.apache.poi.hslf.model.TableCell;
import org.apache.poi.hslf.model.TextBox;
import org.apache.poi.hslf.model.TextRun;
import org.apache.poi.hslf.model.TextShape;
import org.apache.poi.hslf.usermodel.RichTextRun;
import org.apache.poi.hslf.usermodel.SlideShow;

/**
 * 
 * @author zxd
 * @date 2012-9-26
 */
public class PptHelper2003 {

	/**
	 * 保存ppt
	 * 
	 * @Title: savePPTFile
	 * @data:2012-9-26上午09:43:45
	 * @author:zxd
	 * 
	 */
	public static void savePPTFile(SlideShow ppt, String endPath) {
		try {
			FileOutputStream out = new FileOutputStream(endPath);
			ppt.write(out);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 创建一个新的幻灯片
	 * @Title: createNewSlide
	 * @data:2012-9-26下午02:55:25
	 * @author:zxd
	 *
	 * @param ppt
	 * @return
	 */
	public static Slide createNewSlide(SlideShow ppt){
		 Slide s1 = ppt.createSlide();
		return s1;
	}

	/**
	 * 设置母版
	 * 
	 * @Title: setSide
	 * @data:2012-9-26上午09:36:36
	 * @author:zxd
	 * 
	 */
	public static void setSide(SlideShow ppt, int width, int height, String imgPath) {
		try {
			ppt.setPageSize(new Dimension(width, height));
			SlideMaster master = ppt.getSlidesMasters()[0];
			// 设置母板背景,支持多种图片格式
			int picIndex = ppt.addPicture(new File(imgPath), Picture.PNG);
			Picture background = new Picture(picIndex);
			// 设置图片位置
			background.setAnchor(new java.awt.Rectangle(0, 0, ppt.getPageSize().width, ppt.getPageSize().height));
			master.addShape(background);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 在第几个幻灯片中插入文本框内容
	 * 
	 * @Title: createSideInsertText
	 * @data:2012-9-26上午09:42:42
	 * @author:zxd
	 * 
	 * @param ppt
	 */
	@SuppressWarnings("static-access")
	public static void AddInsertText(SlideShow ppt, int i,String content, int x, int y, int weidth, int height) {
		
		Slide[] side = ppt.getSlides();
		Slide newSlide = side[i];
		// 添加文本框
		TextBox txt = new TextBox();
		txt.setText(content);
		txt.setAnchor(new java.awt.Rectangle(x, y,weidth,height));
		RichTextRun richTextRun = txt.getTextRun().getRichTextRuns()[0];
		richTextRun.setFontName("Porsche News Gothic");
		richTextRun.setFontSize(10);
		richTextRun.setFontColor(Color.DARK_GRAY);
		richTextRun.setAlignment(txt.AlignLeft);
		// setText参数字符串可以包含回车、换行符,但是最后一行不能以\r\n结尾,否则设置的格式没有效果(v3.5)
		newSlide.addShape(txt);
	}
	
	/**
	 * 添加标题
	 * @Title: AddTitle
	 * @data:2012-9-26下午03:30:23
	 * @author:zxd
	 *
	 * @param ppt
	 * @param i
	 * @param titles
	 */
	@SuppressWarnings("static-access")
	public static void AddTitle(SlideShow ppt, int i, String titles){
		Slide[] side = ppt.getSlides();
		Slide newSlide = side[i];
		// 添加幻灯片标题
		TextBox title = newSlide.addTitle();
		RichTextRun titleRun = title.getTextRun().getRichTextRuns()[0];
		titleRun.setAlignment(title.AlignCenter);
		titleRun.setFontColor(Color.RED);
		title.setAnchor(new Rectangle(210, 50, 100, 80));
		title.setText(titles);
	}

	/**
	 * 在原有的幻灯片上添加图片
	 * 
	 * @Title: addImg
	 * @data:2012-9-26上午09:53:22
	 * @author:zxd
	 * 
	 * @param ppt
	 * SlideShow对象
	 * @param i第几个幻灯片
	 * @param pathImg图片路径
	 * @param x位置
	 * @param y flag:是否是本地图片，true,wangluo,false:bendi
	 * @param weidth
	 * @param height
	 */
	public static void addImg(SlideShow ppt, int i,boolean flag, String pathImg, int x, int y,int sourWidth,int sourHeight) {
		try {
			int width = 0;
			int height=0;
			Slide[] side = ppt.getSlides();
			Slide newSide = side[i];
			int picIndex = 0;
			if(flag){
				BufferedImage image = getImg(pathImg);
				//压缩图片
				int[] size = ImageHelper.getSize(sourWidth, sourWidth, image);
				width = size[0];
				height = size[1];
				byte[] by = ImagetoByte(image);
				picIndex = ppt.addPicture(by, Picture.JPEG);
			}else{
				BufferedImage image = ImageIO.read(new File(pathImg));
				int[] size = ImageHelper.getSize(300, 800, image);
				width = size[0];
				height = size[1];
				byte[] by = ImagetoByte(image);
				picIndex = ppt.addPicture(by, Picture.JPEG);
			}
			Picture jpg = new Picture(picIndex);
			// 设置图片的位置
			jpg.setAnchor(new java.awt.Rectangle(x, y,width,height));
			
			newSide.addShape(jpg);
		} catch (Exception e) {
		}
	}
	/**
	 * 
	 * @Title: addHeadImg
	 * @data:2012-9-28下午04:42:55
	 * @author:zxd
	 *
	 * @param ppt
	 * @param i
	 * @param flag
	 * @param pathImg
	 * @param x
	 * @param y
	 * @param sourWidth
	 * @param sourHeight
	 */
	public static void addHeadImg(SlideShow ppt, int i,boolean flag, String pathImg, int x, int y,int sourWidth,int sourHeight) {
		try {
			int width = 0;
			int height=0;
			Slide[] side = ppt.getSlides();
			Slide newSide = side[i];
			int picIndex = 0;
			if(flag){
				BufferedImage image = getImg(pathImg);
				//压缩图片
				int[] size = ImageHelper.getSize(sourWidth, sourWidth, image);
				width = size[0];
				height = size[1];
				byte[] by = ImagetoByte(image);
				picIndex = ppt.addPicture(by, Picture.JPEG);
			}else{
				BufferedImage image = ImageIO.read(new File(pathImg));
				int[] size = ImageHelper.getSize(300, 800, image);
				width = size[0];
				height = size[1];
				byte[] by = ImagetoByte(image);
				picIndex = ppt.addPicture(by, Picture.JPEG);
			}
			Picture jpg = new Picture(picIndex);
			// 设置图片的位置
			jpg.setAnchor(new java.awt.Rectangle(x, y,width,height));
			
			newSide.addShape(jpg);
		} catch (Exception e) {
		}
	}

	/**
	 * 创建新的幻灯片并且添加文本
	 * 
	 * @Title: CreateInsertText
	 * @data:2012-9-26上午09:56:47
	 * @author:zxd
	 * 
	 * @param ppt
	 * @param i
	 * @param text
	 * @param content
	 */
	public static void createInsertText(SlideShow ppt, String text, String content, int x, int y, int weidth, int height) {
		Slide newSlide = ppt.createSlide();
		TextBox title = newSlide.addTitle();
		RichTextRun titleRun = title.getTextRun().getRichTextRuns()[0];
		titleRun.setFontColor(Color.RED);
		title.setText(text);

		// 添加文本框
		TextBox txt = new TextBox();
		RichTextRun richTextRun = txt.getTextRun().getRichTextRuns()[0];
		richTextRun.setFontColor(Color.BLUE);
		// setText参数字符串可以包含回车、换行符,但是最后一行不能以\r\n结尾,否则设置的格式没有效果(v3.5)
		richTextRun.setText(content);
		txt.setAnchor(new Rectangle(x, y, weidth, height));
		newSlide.addShape(txt);
	}

	/**
	 * 创建一个新的幻灯片，并且添加图片
	 * 
	 * @Title: createAddImg
	 * @data:2012-9-26上午10:01:16
	 * @author:zxd
	 * 
	 * @param ppt
	 * @param pathImg
	 * @param x
	 * @param y
	 * @param weidth
	 * @param height
	 */
	public static void createAddImg(SlideShow ppt, Slide newSlide,String pathImg, int x, int y){
		try {
			BufferedImage image = getImg(pathImg);
			byte[] by = ImagetoByte(image);
			int picIndex = ppt.addPicture(by, Picture.JPEG);
			Picture jpg = new Picture(picIndex);
			// 设置图片的位置和大小
			int a[] = getWebUrlImg(pathImg);
			int weidth = a[0];//宽度
			int height = a[1];//高度
			if(weidth>800){
				weidth = 500;
			}
			if(height>800){
				height = 500;
			}
			jpg.setAnchor(new java.awt.Rectangle(x, y, weidth, height));
			// 添加图片
			newSlide.addShape(jpg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 添加表格
	 * 
	 * @Title: addTable
	 * @data:2012-9-26上午10:06:17
	 * @author:zxd
	 * 
	 * @param ppt
	 * @param flag
	 * ：是否创建一个新的幻灯片
	 * @param datas
	 * :数据
	 * @param fontSize
	 * :字体大小
	 * @param fontFamily
	 * ：什么字体
	 * @param borderLineWidth
	 * :边框宽度
	 */
	public static void addTable(SlideShow ppt, boolean flag, int which, String[][] datas) {
		if (flag) {
			Slide newSlide = ppt.createSlide();
			Table table = new Table(3, 3);
			for (int i = 0; i < datas.length; i++) {
				for (int j = 0; j < datas[i].length; j++) {
					TableCell cell = table.getCell(i, j);

					RichTextRun rt = cell.getTextRun().getRichTextRuns()[0];
					rt.setFontName("宋体");
					rt.setFontSize(12);

					cell.setVerticalAlignment(TextShape.AnchorMiddle);
					cell.setHorizontalAlignment(TextShape.AlignCenter);
					cell.setText(datas[i][j]);
					if (i == 0) {// 首行背景设置为灰色
						cell.setFillColor(Color.GRAY);
					}
				}
			}
			Line border = table.createBorder();
			border.setLineColor(Color.black);
			border.setLineWidth(2.0);
			table.setAllBorders(border);
			newSlide.addShape(table);
			table.moveTo(160, 260);
		} else {
			// 在第几个幻灯片上添加表格
			Slide[] slide = ppt.getSlides();
			Slide oldSlide = slide[which];
			Table table = new Table(3, 3);
			for (int i = 0; i < datas.length; i++) {
				for (int j = 0; j < datas[i].length; j++) {
					TableCell cell = table.getCell(i, j);
					RichTextRun rt = cell.getTextRun().getRichTextRuns()[0];
					rt.setFontName("宋体");
					rt.setFontSize(12);
					cell.setVerticalAlignment(TextShape.AnchorMiddle);
					cell.setHorizontalAlignment(TextShape.AlignCenter);
					cell.setText(datas[i][j]);
					if (i == 0) {// 首行背景设置为灰色
						cell.setFillColor(Color.GRAY);
					}
				}
			}
			Line border = table.createBorder();
			border.setLineColor(Color.black);
			border.setLineWidth(2.0);
			table.setAllBorders(border);
			oldSlide.addShape(table);
			table.moveTo(160, 260);
		}
	}

	/**
	 * 打开一个模板
	 * 
	 * @Title: openPptTemplate
	 * @data:2012-9-26上午10:42:54
	 * @author:zxd
	 * 
	 * @param ppt
	 * @param templatePath
	 */
	public static SlideShow openPptTemplate(String templatePath) {
		SlideShow ppt = null;
		try {
			ppt = new SlideShow(new FileInputStream(templatePath));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ppt;
	}

	/**
	 * 得到图片宽和稿
	 * 
	 * @Title: getImgSize
	 * @data:2012-9-26上午11:05:48
	 * @author:zxd
	 * 
	 * @param imgPath
	 */
	@SuppressWarnings("unused")
	public static int[] getLocalImgSize(String imgPath) {
		int[] a = new int[2];
		// 得到文件
		java.io.File file = new java.io.File(imgPath);
		BufferedImage bi = null;
		boolean imgwrong = false;
		try {
			// 读取图片
			bi = javax.imageio.ImageIO.read(file);
			try {
				// 判断文件图片是否能正常显示,有些图片编码不正确
				int i = bi.getType();
				imgwrong = true;
			} catch (Exception e) {
				imgwrong = false;
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		if (imgwrong) {
			a[0] = bi.getWidth(); // 获得 宽度
			System.out.println(a[0]);
			a[1] = bi.getHeight(); // 获得 高度
			System.out.println(a[1]);
		} else {
			a = null;
		}
		return a;
	}

	/**
	 * 获取远程url图片的宽和高
	 * 
	 * @Title: getUrlImg
	 * @data:2012-9-26上午11:57:34
	 * @author:zxd
	 * 
	 * @param urlPath
	 */
	@SuppressWarnings("unused")
	public static int[] getWebUrlImg(String urlPath) {
		Image image = null;
		int[] a = new int[2];
		try {
			java.net.URL url = new URL(urlPath);
			BufferedImage bi = null;
			bi = javax.imageio.ImageIO.read(url);
			image = javax.imageio.ImageIO.read(url);
			a[0] = bi.getWidth(); // 宽度
			a[1] = bi.getHeight(); // 获得 高度
			System.out.println(a[0]);
			System.out.println(a[1]);
			int b = a[0];
		} catch (IOException e) {
			e.printStackTrace();
		}
		return a;
	}
	/**
	 * 获得图片
	 * @Title: getImg
	 * @data:2012-9-26下午01:59:33
	 * @author:zxd
	 *
	 * @param urlPath
	 * @return
	 */
	@SuppressWarnings("unused")
	public static BufferedImage getImg(String urlPath){
		BufferedImage  image = null;
		int[] a = new int[2];
		try {
			java.net.URL url = new URL(urlPath);
			BufferedImage bi = null;
			bi = javax.imageio.ImageIO.read(url);
			image = javax.imageio.ImageIO.read(url);
			a[0] = bi.getWidth(); // 宽度
			a[1] = bi.getHeight(); // 获得 高度
			System.out.println(a[0]);
			System.out.println(a[1]);
			int b = a[0];
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}
	
	/**
	 * imge对象转化成byte
	 * @Title: ImagetoByte
	 * @data:2012-9-26下午02:57:53
	 * @author:zxd
	 *
	 * @param image
	 * @return
	 */
	public static byte[] ImagetoByte(BufferedImage image){
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			ImageIO.write(image, "jpg", baos);
		} catch (IOException e) {
			e.printStackTrace();
		}
		byte[] by = baos.toByteArray();
		return by; 
	}
	/**
	 * 画横线
	 * @Title: drawLine
	 * @data:2012-9-27下午04:40:56
	 * @author:zxd
	 *
	 */
	public static void drawLine(SlideShow ppt, int i,int y,Slide Slide){
		Slide[] side = ppt.getSlides();
		Slide newSlide = side[i];
		Line line = new Line();
		line.setAnchor(new java.awt.Rectangle(20, y, 505, 0));
		line.setLineColor(new Color(0, 0, 0));
		line.setLineStyle(Line.LINE_SIMPLE);
		newSlide.addShape(line);
	}
	/**
	 * 
	 * @Title: getHeadText
	 * @data:2012-9-28上午10:41:19
	 * @author:zxd
	 *
	 */
	public static String[] getHeadText(SlideShow ppt){
		Slide slides[] =  ppt.getSlides();
		Slide newSlide  = slides[0];
		TextRun[] txt2 =newSlide.getTextRuns();
		String head = "";
		for (int j = 0; j < 2; j++) {
			head +=txt2[j].getRawText()+",";
		}
		head = head.substring(0, head.length()-1);
		String headText [] = head.split(",");
		return headText;
	}
	/**
	 * 获得英文字数
	 * @Title: getEngCount
	 * @data:2012-9-28下午02:28:32
	 * @author:zxd
	 *
	 */
	@SuppressWarnings("unused")
	public static void getEngCount(SlideShow ppt){
		Slide slides[] =  ppt.getSlides();
		Slide newSlide  = slides[0];
		TextRun[] txt = newSlide.getTextRuns();
		String engCount = txt[1].getRawText();
		int count = engCount.length();
	}
	/**
	 * 得到内容的字数
	 * @Title: getContentCount
	 * @data:2012-10-10下午02:40:10
	 * @author:zxd
	 *
	 * @param ppt
	 */
	@SuppressWarnings("unused")
	public static int getContentCount(String content){
		int count = 0;
		int height = 0;
		String contents[] = content.split("\n");
		int row = contents.length;
		height = row*13;
		return height;
	}
	/**
	 * 获得当前页数
	 * @Title: getCurrentPage
	 * @data:2012-9-29上午09:31:30
	 * @author:zxd
	 *
	 * @param ppt
	 * @param i
	 * @return
	 */
	public static int getCurrentPage(SlideShow ppt,int i){
		int page = 0;
		Slide slides[] = ppt.getSlides();
		Slide newSlide = slides[i];
		page = newSlide.getSlideNumber();
		return page;
	}
	
}
