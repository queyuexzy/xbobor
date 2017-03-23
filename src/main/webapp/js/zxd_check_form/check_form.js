//校验表单
function checkFormJS(table){
var params = $("#"+ table+" input[type='text']");
var flag1 = true;
	$.each(params,function(i,param){
		
		if($(param).val().Trim()!=''){
			//防止js注入
			//var strRegnameAll = "'|;|#|([\s\b+()]+([email=select%7Cupdate%7Cinsert%7Cdelete%7Cdeclare%7C@%7Cexec%7Cdbcc%7Calter%7Cdrop%7Ccreate%7Cbackup%7Cif%7Celse%7Cend%7Cand%7Cor%7Cadd%7Cset%7Copen%7Cclose%7Cuse%7Cbegin%7Cretun%7Cas%7Cgo%7Cexists)[/s/b]select|update|insert|'|'|delete|declare|@|exec|dbcc|alter|drop|create|backup|if|else|end|and|or|add|set|open|close|use|begin|script|<|retun|as|go|exists)[\s\b[/email]+]*)";   
			//var snameAll = $(param).val().search(strRegnameAll);
			var pattern = new RegExp("[`~!@#$^&*()=|{}';',<>?~！@#￥……&*（）——|{}【】‘；“”'。，、？'']") 
			if(pattern.test($(param).val())){
				alert("填写的内容不可出现非法字符，例如:<,>");
				$(param).select();
				flag1 =false;
				return flag1;
			} else{
			//校验是否含有双引号
				var pattern2 = /["]+/; 
				if(pattern2.test($(param).val())){
					alert("填写的内容不可出现非法字符，例如:<,>");
					$(param).select();
					flag1=false;
					return flag1;
					}
				flag1=true;
				return;
				}
			}
		});
	if(flag1==true){
		return true;
	}else{
		return false;
	}
}
//校验表格是否存在非法字符
function stripscript()
{ 
	var pattern = new RegExp("[`~!@#$^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？]") 
	var rs = ""; 
	for (var i = 0; i < s.length; i++) { 
	rs = rs+s.substr(i, 1).replace(pattern, ''); 
	} 
	return rs; 
 

} 

//验证网址
function isRightWeb(text) {
	var newPar ="^((https|http|ftp|rtsp|mms)?://)"  
		+ "?(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?" //ftp的user@  
		+ "(([0-9]{1,3}\.){3}[0-9]{1,3}" // IP形式的URL- 199.194.52.184  
		+ "|" // 允许IP和DOMAIN)域名） 
		+ "([0-9a-z_!~*'()-]+\.)*" // 域名- www.  
		+ "([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\." // 二级域名  
		+ "[a-z]{2,6})" // first level domain- .com or .museum  
		+ "(:[0-9]{1,4})?" // 端口- :80  
		+ "((/?)|" // a slash isn't required if there is no file name  
		+ "(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$"; 
	var w = text.search(newPar);
	if (w==-1) {
		return false;
	} else {
		return true;
	}
}
//校验电话号码
function IsTelephone(obj)// 正则判断
{ 
	var pattern=/(^[0-9]{3,4}\-[0-9]{3,8}$)|(^[0-9]{3,8}$)|(^\([0-9]{3,4}\)[0-9]{3,8}$)|(^0{0,1}13[0-9]{9}$)/; 
	if(pattern.test(obj)) 
	{ 
		return true; 
	} 
	else 
	{ 
		return false; 
	} 
}
//验证邮编
function isPostalCode(obj)
{
 var pattern =/^[0-9]{6}$/;
 
	 if(pattern.test(obj))
	 {
	  return true;
	 }
	 else{
	  return false;
	 }
    
}

//校验手机号码
function IsMobilephone(obj){
	  var reg0 = /^13\d{5,9}$/;
      var reg1 = /^15\d{5,9}$/;
      var reg3 = /^0\d{10,11}$/;
      var reg4 = /^157\d{4,8}$/;
      var reg5 = /^18\d{5,9}$/;
      var my = false;    
      if (reg0.test(obj))my=true;    
      if (reg1.test(obj))my=true;     
      if (reg3.test(obj))my=true;
      if (reg4.test(obj))my=true;
      if (reg5.test(obj))my=true; 
      if(!my){
    	  return false;
      }else{
    	  return true
      }

}
//校验TEXT里面是否有非法字符
function Isformat(){
	
}
//清除查询框内容
function clearName(id){
	$("#"+id).val("");
	$("#"+id).select();
}

//清除查询框内容
function clearName(id){
	$("#"+id).val("");
	$("#"+id).select();
}
/**
 * 禁用多个按钮
 * @param table 表格名字
 * @param targetButton
 * @return
 */
function chooseNameButton(table,targetButton){
	var params = $("#"+table+" input[type='button']");
	$.each(params,function(i,param){
		$(param).attr("disabled",false);
			});
	$(targetButton).attr("disabled",true);

}
/**
 * 通过name禁用按钮
 * @param table
 * @param targetButton
 * @return
 */
function chooseNameButton2(name,targetButton){
	var params = $("input[name="+name+"]");
	$.each(params,function(i,param){
		$(param).attr("disabled",false);
			});
	$(targetButton).attr("disabled",true);

}