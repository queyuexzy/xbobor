<%@page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%
	String path = request.getContextPath();
%>

<script type="text/javascript" src="<%=path%>/js/jquery/jquery-1.7.1.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery/jqueryHelper.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery/jquery.easing.1.3.js"></script>
<script type="text/javascript" src="<%=path%>/js/common.js"></script>
<script type="text/javascript" src="<%=path%>/js/layer/layer.js"></script>
<script type="text/javascript" src="<%=path%>/js/layer/layerext.js"></script>
<script type="text/javascript" src="<%=path%>/js/public.js"></script>
<link href="<%=path%>/res/v1/css/base.css" rel="stylesheet" type="text/css" />
<link href="<%=path%>/res/v1/css/fream.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	var path = "<%=path%>"; // 应用名
	var ajaxFlag = "ajaxFlag"; // ajax请求标识
	//等待框加载样式
	var loadType = 1;
</script>
