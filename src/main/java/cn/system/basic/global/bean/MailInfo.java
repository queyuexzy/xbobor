/*  
 * @(#) sdaf.java Create on 2012-7-31 下午06:01:21   
 *   
 * Copyright 2012 by xl.   
 */
package cn.system.basic.global.bean;

import java.util.Properties;

/**
 * 
 * @author zxd
 * @date   2012-7-31
 */
public class MailInfo {
	
	
	 // 服务器主机IP
	 private String mailServerHost;
	 // 端口
	 private String mailServerPort;
	 // 发件人地址
	 private String fromAddress;
	 //发件人别名
	 private String formName;
	 // 发件人用户名、密码
	 private String userName;
	 
	 private String password;
	 // 收件人地址
	 private String[] toAddress;
	 
	 private String[] ccs; //抄送
	 
	 private String[] bcc; //密送
	 // 邮件主题
	 private String subject;
	 // 邮件的文本内容
	 private String content;
	 // 邮件附件的文件名
	 private String[] attachFileNames;
	 // 邮件验证
	 private boolean validate = false;


	 // 系统属性
	 public Properties getProperties() {
		Properties p = System.getProperties();
		p.put("mail.smtp.host", this.getMailServerHost());
		p.put("mail.smtp.port", this.getMailServerPort());
		p.put("mail.smtp.auth", validate ? "true" : "false");
		return p;
	 }
	 
	 public String[] getCcs() {
		return ccs;
	}

	public void setCcs(String[] ccs) {
		this.ccs = ccs;
	}

	public String[] getBcc() {
		return bcc;
	}

	public void setBcc(String[] bcc) {
		this.bcc = bcc;
	}

	public String getMailServerHost() {
		 return mailServerHost;
	 }

	 public void setMailServerHost(String mailServerHost) {
		 this.mailServerHost = mailServerHost;
	 }

	 public String getMailServerPort() {
		return mailServerPort;
	 }

	 public void setMailServerPort(String mailServerPort) {
	 	this.mailServerPort = mailServerPort;
	 }

	 public String getFromAddress() {
	 	return fromAddress;
	 }

	 public void setFromAddress(String fromAddress) {
	  this.fromAddress = fromAddress;
	 }

	 public String getUserName() {
	 	return userName;
	 }

	 public void setUserName(String userName) {
	 	this.userName = userName;
	 }

	 public String getPassword() {
	 	return password;
	 }

	 public void setPassword(String password) {
	 	this.password = password;
	 }

	 public String[] getToAddress() {
	 	return toAddress;
	 }

	 public void setToAddress(String[] toAddress) {
	 	this.toAddress = toAddress;
	 }

	 public String getSubject() {
	 	return subject;
	 }

	 public void setSubject(String subject) {
	 	this.subject = subject;
	 }

	 public String getContent() {
	 	return content;
	 }

	 public void setContent(String content) {
	 	this.content = content;
	 }

	 public String[] getAttachFileNames() {
	 	return attachFileNames;
	 }

	 public void setAttachFileNames(String[] attachFileNames) {
	 	this.attachFileNames = attachFileNames;
	 }

	 public boolean isValidate() {
	 	return validate;
	 }

	 public void setValidate(boolean validate) {
	 	this.validate = validate;
	 }

	public String getFormName() {
		return formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}
}