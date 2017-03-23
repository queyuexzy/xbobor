<!DOCTYPE html>
<html>
	<head>
		<%@include file="/WEB-INF/page/include.jsp"%>
		<%@page contentType="text/html; charset=utf-8"%>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-status-bar-style" content="black-translucent">
		<meta name="format-detection" content="telephone=no">
		<meta content="yes" name="apple-mobile-web-app-capable">
		<meta content="yes" name="apple-touch-fullscreen">
		<meta name="App-Config" content="fullscreen=yes,useHistoryState=yes,transition=yes">
		<meta name="cdn" content="true">
		<link rel="stylesheet" href="//at.alicdn.com/t/font_1463381134_5581925.css">
		<link rel="stylesheet" href="//common.toomao.com/css?p=bootstrap-core.css,buttons.css,common.css,citySelect,clearable,deletable,events,formJSON,formValidator,formOnInvalid,lazyload,loadpage,loadingPage,shareWX,tabs,template,toast,ajaxUpload,webcom"> 
		<link href="<%=path%>/css/mine.css" rel="stylesheet" type="text/css" />
		<title>我的</title>
	</head>
	<body style="width: 100%; margin: 0px auto;max-width: 540px;">
	<div data-role="page">
		<div data-is="mobile-menu" data-bind="data-was-hidden-by-webcom">
		<nav class="menu">
			<ul class="menu-list clearfix">
				<li>
					<a href="<%=path%>/index/index_toSquare">
						<i class="iconfont icon-square"></i>
						<span>首页</span>
					</a>
				</li>
				<li>
					<a href="javascript:;">
						<i class="iconfont icon-discover"></i>
						<span>田园</span>
					</a>
				</li>
				<li>
					<a href="javascript:;">
						<i class="iconfont icon-area"></i>
						<span>分类</span>
					</a>
				</li>
				<li>
					<a href="javascript:;">
						<i class="iconfont icon-cart"></i>
						<span>购物车</span>
					</a>
				</li>
				<li class="active">
					<a href="<%=path%>/index/index_toMine">
						<i class="iconfont icon-user"></i>
						<span>我的</span>
					</a>
				</li>
			</ul>
		</nav>
	</div>
	<form action="https://api.toomao.com/1.1/info" method="GET" data-role="formJSON" class="info"></form>
	<form action="https://api.toomao.com/1.1/my/ordercnt" method="GET" data-role="formJSON" class="order-number"></form>
	<div class="person-wrapper">
		<a href="/user/login" class="person">
			<img src="http://pic.toomao.com/b5122b7daa5504ffebe7a8274377288b209684d1">
			<div class="title">登陆</div>
			<span class="right">
				<i class="iconfont icon-right"></i>
			</span>
		</a>
	</div>
	<ul class="list icon right-icon orders">
		<li>
			<a href="/mine/order?type=0" class="my-order">
				<i class="iconfont icon-orders"></i>
				<span>我的订单</span>
				<i class="iconfont icon-right"></i>
			</a>
		</li>
	</ul>
	<ul class="order-status clearfix">
		<li class="pay">
			<span></span>
			<a href="/mine/order?type=1">
				<i class="iconfont icon-order-pay"></i>
				<span>待付款</span>
			</a>
		</li>
		<li class="send">
			<span></span>
			<a href="/mine/order?type=2">
				<i class="iconfont icon-order-send"></i>
				<span>待发货</span>
			</a>
		</li>
		<li class="deliver">
			<span></span>
			<a href="/mine/order?type=3">
				<i class="iconfont icon-order-deliver"></i>
				<span>待收货</span>
			</a>
		</li>
		<li class="comments">
			<span></span>
			<a href="/mine/order?type=4">
				<i class="iconfont icon-order-comments"></i>
				<span>待评价</span>
			</a>
		</li>
		<li class="refund">
			<span></span>
			<a href="/mine/order-refund">
				<i class="iconfont icon-order-refund"></i>
				<span>退款中</span>
			</a>
		</li>
	</ul>
	<ul class="list icon right-icon">
		<li>
			<a href="/mine/groupon">
				<i class="iconfont icon-groupon"></i>
				<span>我的拼团</span>
				<i class="iconfont icon-right"></i>
			</a>
		</li>
		<li>
			<a href="/mine/money">
				<i class="iconfont icon-money"></i>
				<span>我的钱包</span>
				<i class="iconfont icon-right"></i>
			</a>
		</li>
		<li>
			<a href="/mine/follow">
				<i class="iconfont icon-star"></i>
				<span>我的关注</span>
				<i class="iconfont icon-right"></i>
			</a>
		</li>
		<li>
			<a href="/mine/help">
				<i class="iconfont icon-contactus"></i>
				<span>帮助与反馈</span>
				<i class="iconfont icon-right"></i>
			</a>
		</li>
		<li style="display:none" class="joinus">
			<a href="http://www.toomao.com/html/mobile/joinus/">
				<i class="iconfont icon-shop"></i>
				<span>设置</span>
				<i class="iconfont icon-right"></i>
			</a>
		</li>
		<li>
			<a href="/mine/setting">
				<i class="iconfont icon-setting"></i>
				<span>设置</span>
				<i class="iconfont icon-right"></i>
			</a>
		</li>
	</ul>
</body>
</html>