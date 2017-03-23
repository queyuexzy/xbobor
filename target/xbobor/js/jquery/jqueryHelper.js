function waitImage(area){
	$("#"+area).html("<IMG SRC='<%=path%>/images/loading.gif' />&nbsp;<span style=' font-size:12px; color:Black;'>加载数据中.....</span><br>");
}
function toErrorPage(type){
	if(type=="-5"){
		window.top.location = "/return.jsp?errorInfo="+type;
		return false;
	}else if(type=="000002"){
		window.top.location = "/return.jsp?errorInfo=000002";
		return false;
	}
	return true;
}