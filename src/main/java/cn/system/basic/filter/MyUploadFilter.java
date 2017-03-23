/*  
 * @(#) MyUploadInterceptor.java Create on 2012-8-2 上午11:41:31 
 * 
 * Copyright 2012 by xl. 
 */
package cn.system.basic.filter;

import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import cn.system.basic.global.baseAbstract.BaseAction;
import cn.tools.CommonSendMeg;
import cn.tools.ErrorFlag;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.opensymphony.xwork2.util.ValueStack;

/**
 * 
 * @author liweidong
 * @date 2012-8-2
 */
@Controller
@Scope("prototype")
public class MyUploadFilter extends BaseAction implements Interceptor{
	private static Logger	logger = LoggerFactory.getLogger(MyUploadFilter.class);
	@SuppressWarnings("unused")
	private static final int MB_2 = 2097152; //上传文件最大2M
	@SuppressWarnings("unused")
	private static final int MB_5 = 5242800; //上传文件最大5M
	@SuppressWarnings("unused")
	private static final int MB_8 = 8388608; //上传文件最大8M
	@SuppressWarnings("unused")
	private static final int MB_20 = 20971520;//20M
	@SuppressWarnings("unused")
	private static final int MB_30 = 31457280;//30M
	private static final int MB_50 = 524288000;//50M
	private static final String[] TYPE = new String[] {"xls", "xlsx"};
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void destroy() {
		logger.info("MyUploadFilter end");
	}

	@Override
	public void init() {
		logger.info("MyUploadFilter start");
	}

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		String result = "";
		ValueStack stack = arg0.getStack();
		String fileName = (String) stack.findValue("uploadFileFileName");
		File file = (File) stack.findValue("uploadFile");
		FileInputStream fis = null;
		try {
			if(file != null && fileName !=null){
				
				String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();//取后缀名
				if(!Arrays.<String> asList(TYPE).contains(fileExt)) {
					CommonSendMeg.writeMsg("-8");//格式不对
					return null;
				}
				fis = new FileInputStream(file);
				long size = Long.parseLong(String.valueOf(fis.available()));
				if(size > MB_50){
					CommonSendMeg.writeMsg("-9");//超过最大限制
					return null;
				}
				result = arg0.invoke();
			}else{
				result = arg0.invoke();
			}
		} catch (Exception e) {
			e.printStackTrace();
			CommonSendMeg.writeMsg(ErrorFlag.OPR_FAIL+"");
		} finally{
			try {
				if(fis!=null){
					fis.close();
				}
			} catch (Exception e2) {
			}
		}
		
		return result;
	}

}
