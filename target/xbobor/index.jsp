<!DOCTYPE html PUBLIC "-//W3C//Dtd XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/Dtd/xhtml1-strict.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html>
<head>
	<%@include file="/WEB-INF/page/include.jsp"%>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no">
	<meta name="apple-mobile-web-app-status-bar-style" content="black-translucent">
	<meta name="format-detection" content="telephone=no">
	<link rel="stylesheet" href="//at.alicdn.com/t/font_1463381134_5581925.css">
	<link rel="stylesheet" href="//common.toomao.com/css?p=bootstrap-core.css,buttons.css,common.css,citySelect,clearable,deletable,events,formJSON,formValidator,formOnInvalid,lazyload,loadpage,loadingPage,shareWX,tabs,template,toast,ajaxUpload,webcom">
	<title>首页</title>
</head>
<body>
	<div data-role="page">
		<link href="<%=path%>/css/square-index.css" rel="stylesheet" type="text/css" />
			<div class="cate-list-wrapper">
				<ul class="cate-list sum-8">
					<li> 
						<a href="/index/category?name=quanbu">
							<img src="image/ico-dianpu.png" data-src="image/ico-dianpu.png" class="">
							<div class="name">全部</div>
						</a>
					</li>
					<li>
						<a href="/index/category?name=lingshi">
							<img src="image/ico-dianpu.png" data-src="image/ico-dianpu.png" class="">
							<div class="name">零食</div>
						</a>
					</li>
					<li>
						<a href="/index/category?name=shuiguo">
							<img src="image/ico-dianpu.png" data-src="image/ico-dianpu.png" class="">
	    					<div class="name">水果</div>
	    				</a> 
	    			</li> 
	   				<li> 
	    				<a href="/index/category?name=liangyou">
	     					<img src="image/ico-dianpu.png" data-src="image/ico-dianpu.png" class="">
	    					<div class="name">粮油</div>
	        			</a>
	        		</li> 
			        <li>
			        	<a href="/index/category?name=shucai">
			        		<img src="image/ico-dianpu.png" data-src="image/ico-dianpu.png" class="">
			        		<div class="name">蔬菜</div>
			        	</a>
			        </li>
	         		<li>
	         			<a href="/index/category?name=shenghuo">
		         			<img src="image/ico-dianpu.png" data-src="image/ico-dianpu.png" class="">
		         			<div class="name">生活</div>
	          			</a>
	           		</li>
	          		<li>
	           			<a href="/index/category?name=fushi">
	           				<img src="image/ico-dianpu.png" data-src="image/ico-dianpu.png" class="">
	           				<div class="name">副食</div>
	          			</a>
	          		</li>
	           		<li>
	           			<a href="/index/category?name=yinpin">
	           				<img src="image/ico-dianpu.png" data-src="image/ico-dianpu.png" class="">
	           				<div class="name">饮品</div>
	           			</a>
	           		</li>
	           </ul>
	        </div>
	        <a class="image-view" href="javascript:;">
	           	<img src="/image/tuijian-01.jpg" data-src="/image/tuijian-01.jpg" class="">
	        </a>
	        <a class="image-view" href="javascript:;">
	            <img src="/image/tuijian-02.jpg" data-src="/image/tuijian-02.jpg" class="">
        	</a>
            <a class="image-view" href="javascript:;">
	           <img src="/image/tuijian-03.jpg" data-src="/image/tuijian-03.jpg" class="">
	        </a>
	        <a class="image-view" href="javascript:;">
	           <img src="/image/tuijian-04.jpg" data-src="/image/tuijian-04.jpg" class="">
	        </a>
	        <a class="image-view" href="javascript:;">
	           <img src="/image/tuijian-05.jpg" data-src="/image/tuijian-05.jpg" class="">
	        </a>
	        <a class="image-view" href="javascript:;">
	           <img src="/image/tuijian-06.jpg" data-src="/image/tuijian-06.jpg" class="">
	        </a>
            <a class="image-view" href="javascript:;">
				<img src="/image/tuijian-07.jpg" data-src="/image/tuijian-07.jpg" class="">
			</a>       
			<a class="image-view" href="javascript:;">
				<img src="/image/tuijian-08.jpg" data-src="/image/tuijian-08.jpg" class="">
 			</a>         
     	<div data-is="mobile-menu" data-bind="data-was-hidden-by-webcom">
     		<nav class="menu show-menu">
     			<ul class="menu-list clearfix">
     				<li class="active">
     					<a href="javascript:;">
     						<i class="iconfont icon-square"></i>
     						<span>首页</span>
     					</a>
     				</li>
     				<li>
     					<a href="javascript:;">
     						<%--<i class="iconfont icon-discover"></i>--%>
     						<%--<span>田园</span>--%>
     					</a>
     				</li>
     				<li>
     					<a href="javascript:;">
							<i class="iconfont icon-cart"></i>
							<span>购物车</span>
     					</a>
     				</li>
     				<li>
     					<a href="javascript:;">
							<%--<i class="iconfont icon-area"></i>--%>
							<%--<span>分类</span>--%>
     					</a>
     				</li>
     				<li>
     					<a href="javascript:;">
     						<i class="iconfont icon-user"></i>
     						<span>我的</span>
     					</a>
     				</li>
     			</ul>
     		</nav>
		</div>
		<div data-is="to-top" data-bind="data-was-hidden-by-webcom" class="to-top">
			<div class="iconfont icon-top"></div> 
		</div>
		<div data-role="shareWX" data-api="//m.toomao.com/wechat/sdk/sign" data-title="土冒，我们只卖特产" data-desc="想您所想，提供最贴心的选品策划，供应最地道、健康、品质的特色美味。" data-link="http://m.toomao.com/square" data-imgurl="http://m.toomao.com/favicon.png" class="shareWX"></div>
			<form action="https://api.toomao.com/1.2/home" data-role="formJSON" method="GET" class="square-info"></form>
		</div>
		<div data-register="mobile-menu" style="display: none;">
		</div>
		<div data-register="to-top" style="display: none;">
 		</div>
 		<div data-register="slider" style="display: none;">
		</div>
	</body>
</html>