<!DOCTYPE html PUBLIC "-//W3C//Dtd XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/Dtd/xhtml1-strict.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html>
    <head>
        <%@include file="/WEB-INF/page/include.jsp"%>
        <%@ page trimDirectiveWhitespaces="true"%>
        <%@ taglib prefix="c" uri="/tlds/c.tld"%>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
        <%@ taglib prefix="page" uri="/tlds/page.tld"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
        <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent">
        <meta name="format-detection" content="telephone=no">
        <meta content="yes" name="apple-mobile-web-app-capable">
        <meta content="yes" name="apple-touch-fullscreen">
        <meta name="App-Config" content="fullscreen=yes,useHistoryState=yes,transition=yes">
        <meta name="cdn" content="false">
        <link rel="stylesheet" href="/css/font_4dh8xlaq9jtc5wmi.css">
        <link rel="stylesheet" href="/css/css.css">
        <style>.page {position: relative;}</style>
        <title>产品列表</title>
    </head>
    <body>
        <div data-role="page" class="page">
            <style>
                .name{
                    margin-bottom:15px;
                    line-height:16px;
                }
                .pay{
                    position:absolute;
                    right:12px;
                    top:246px;
                    font-size:11px;
                    height:11px;
                }
                ul.goods-list {
                    margin-top: 0;
                }
            </style>
            <div data-is="mobile-menu" data-bind="data-was-hidden-by-webcom" class="mobile-menu">
                <nav class="menu">
                    <ul class="menu-list clearfix">
                        <li>
                            <a href="https://m.toomao.com/square">
                                <i class="iconfont icon-square"></i>
                                <span>首页</span>
                            </a>
                        </li>
                        <li>
                            <a href="https://m.toomao.com/discover">
                                <%--<i class="iconfont icon-discover"></i>--%>
                                <%--<span>发现</span>--%>
                            </a>
                        </li>
                        <li>
                            <a href="https://m.toomao.com/area">
                                <i class="iconfont icon-cart"></i>
                                <span>购物车</span>
                            </a>
                        </li>
                        <li>
                            <a href="https://m.toomao.com/cart">
                                <%--<i class="iconfont icon-area"></i>--%>
                                <%--<span>区域馆</span>--%>
                            </a>
                        </li>
                        <li>
                            <a href="https://m.toomao.com/mine">
                                <i class="iconfont icon-user"></i>
                                <span>我的</span>
                            </a>
                        </li>
                    </ul>
                </nav>
            </div>

		<form action="https://api.toomao.com/1.1/productsByTag" data-role="formJSON" method="GET">
			<input type="hidden" name="tagid" value="256">
		</form>
		<div data-is="goods-list" data-bind="data-was-hidden-by-webcom" class="goods-list">
			<ul class="goods-list">
                <c:if test="${not empty result}">
                    <c:forEach var="bean" items="${result}" varStatus="status">
                        <c:if test="${not empty bean}">
                            <li class="col-xs-6 col-sm-6 col-md-6 col-lg-4">
                                <div class="wrapper">
                                    <a href="/index/product?name=${bean.productId}" class="img place" data-place="${bean.place}" style="width:100%;background-image: url(&quot;${bean.image}&quot;);"></a>
                                    <div class="name ">${bean.title}</div>
                                    <ul class="flags"></ul>
                                    <div class="number">
                                        <small>￥</small>
                                        <span>${bean.price}</span>
                                    </div>
                                    <div class="add-to-cart-btn" onclick="addToButton(${bean.productId})">
                                        <i class="icon cart"></i>
                                    </div>
                                </div>
                            </li>
                        </c:if>
                    </c:forEach>
                </c:if>

				<%--<li class="col-xs-6 col-sm-6 col-md-6 col-lg-4">--%>
					<%--<div class="wrapper">--%>
                        <%--<a href="/index/product?name=shenghuo" class="img place" data-place="蚌埠" style="width:100%;background-image: url(&quot;http://pic.toomao.com/156758adde24d6142ea2122479ae0ecf85351ca1&quot;);"></a>--%>
                        <%--<div class="name ">怀远特产 亚太石榴酒 精品系列石榴酒750ml 单瓶</div>--%>
                        <%--<ul class="flags"></ul>--%>
                        <%--<div class="number">--%>
                            <%--<small>￥</small>--%>
                            <%--<span>19.9</span>--%>
                        <%--</div>--%>
                        <%--<div class="add-to-cart-btn" onclick="addToButton()">--%>
                            <%--<i class="icon cart"></i>--%>
                        <%--</div>--%>
                    <%--</div>--%>
				<%--</li>--%>
				<%--<li class="col-xs-6 col-sm-6 col-md-6 col-lg-4">--%>
					<%--<div class="wrapper">--%>
                        <%--<a href="/index/product?name=shenghuo" class="img place" data-place="蚌埠" style="width:100%;background-image: url(&quot;http://pic.toomao.com/6295bb235f4b37017674fcc7d6f3f15d5cc85178&quot;);"></a>--%>
                        <%--<div class="name ">安徽怀远特产 亚太石榴酒 生态礼品 红酒 果酒 750ml*2礼盒装 </div>--%>
                        <%--<ul class="flags"></ul>--%>
                        <%--<div class="number">--%>
                            <%--<small>￥</small>--%>
                            <%--<span>59.9</span>--%>
                        <%--</div>--%>
                        <%--<div class="add-to-cart-btn" onclick="addToButton()">--%>
                            <%--<i class="icon cart"></i>--%>
                        <%--</div>--%>
                    <%--</div>--%>
				<%--</li>--%>
				<%--<li class="col-xs-6 col-sm-6 col-md-6 col-lg-4">--%>
					<%--<div class="wrapper">--%>
                        <%--<a href="/index/product?name=shenghuo" class="img place" data-place="宝鸡市" style="width:100%;background-image: url(&quot;http://pic.toomao.com/a8fc4eda5ae51034a2225f6d972bc7403643e304&quot;);"></a>--%>
                        <%--<div class="name ">陕西特产 45度西凤酒1991  500ml</div>--%>
                        <%--<ul class="flags"></ul>--%>
                        <%--<div class="number">--%>
                            <%--<small>￥</small>--%>
                            <%--<span>169.0</span>--%>
                        <%--</div>--%>
                        <%--<div class="add-to-cart-btn" onclick="addToButton()">--%>
                            <%--<i class="icon cart"></i>--%>
                        <%--</div>--%>
					<%--</div>--%>
				<%--</li>--%>
			</ul>
		</div>
    </div>
        <script type="text/javascript">
           function addToButton() {
               alert("addToButton");
           }
        </script>
		<div data-register="download-tip" style="display: none;">
			<style>
				.download-tip{
					background-color:rgba(0,0,0,.8);
					color:#fff;
					font-size:12px;
					width:100%;
					line-height:50px;
					position:fixed;
					top:0;
					left:0;
					z-index:5
				}
				.download-tip .toomao-icon-close{
					background-image:url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgAgMAAAAOFJJnAAAADFBMVEX///9MaXH///////+1it7yAAAAA3RSTlOgAEBV0tguAAAAbUlEQVR4XkWQyxXAIAgEtwkL8ZgeuCmHFJBb2qSvZJ6+1QOOfGRBuY/udT96F1wajXuWZgGjKTsQqQyg/0DSrBSGh3ATFhYWfqIik3xRG7kg+oZRhhNyssv94WnhppZhYUeqxXscD+iRvQSv5QNf/VX78gYA8QAAAABJRU5ErkJggg==);
					margin:0 10px;
					color:#fff;
					height:16px;
					width:16px;
					display:inline-block;
					background-size:100% 100%;
					vertical-align:middle
				}
				.download-tip img{
					height:30px;
					width:30px;
					vertical-align:middle;
					margin-right:10px
				}
				.download-tip .span{
					font-size:.9em
				}
				.download-tip a{
					color:#fff;
					text-decoration:none;
					border-left:solid 1px;
					float:right;
					margin:18px 5px 0;
					padding-left:10px;
					line-height:normal
				}
			</style>
		</div>
		<div data-register="mobile-menu" style="display: none;">
			<style>
				.menu{
					position:fixed;
					width:100%;
					left:0;
					bottom:0;
					cursor:pointer;
					z-index:101;
					transition:all 1s;
					border-top:solid 1px #e2e2e2
				}
				.menu.show-menu{
					bottom:0
				}
				.menu.hide-menu{
					bottom:-80px
				}
				.menu-list{
					list-style:none;
					margin:0;
					background-color:rgba(255,255,255,.95);
					padding:0;
					font-size:0
				}
				.menu-list a{
					display:block;
					text-decoration:none;
					color:inherit
				}
				.menu-list .iconfont{
					display:block;
					margin:auto;
					font-size:22px;
					color:#1a1a1a
				}
				.menu-list li{
					text-align:center;
					color:#1a1a1a;
					cursor:pointer;
					width:20%;
					display:inline-block;
					padding:6px 0;
					position:relative;
					float:left
				}
				.menu-list li.active{
					color:#B31800
				}
				.menu-list li.active .iconfont{
					color:#B31800
				}
				.menu .menu-list span{
					font-size:11px;
					margin-top:4px;
					display:block
				}
			</style>
		</div>
		<div data-register="goods-list" style="display: none;">
			<style>
				ul.goods-list{
					background-color:#fff;
					list-style:none;padding:0}ul.goods-list:after,ul.goods-list:before{content:" ";display:table}ul.goods-list:after{clear:both}ul.goods-list li{padding:2.5px;position:relative;background:#F4F4F4;box-sizing:border-box}ul.goods-list li .wrapper{background:#fff}ul.goods-list a{text-decoration:none;color:#999;position:relative}ul.goods-list a.img.place:before{content:attr(data-place);position:absolute;background-image:url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAFoAAAAeCAMAAACbv/lKAAAANlBMVEUXFhkXFhkXFhkXFhkXFhkXFhkXFhlHcEwXFhkXFhkXFhkXFhkXFhkXFhkXFhkXFhkXFhkXFhkXTcqaAAAAEnRSTlOAL1x6UgYRAHQKJUkBbR5iOzaExsEHAAAAaklEQVR4XtXWuQHAIABCUTwxXon7L5spfiEDvIIGhCVjcnswOpiSo43VgdHBFB1N0ali9DFFx0nRqZqijyn6nRSdqin6M0Zvjl4FozU4WpujV8FoDY5W5+hWMFqZo9V94+wqc7S6bzxmyj+V4gTS+T8fNAAAAABJRU5ErkJggg==);background-size:100% 100%;color:#fff;height:15px;width:40px;top:5px;left:0;font-size:10px;line-height:15px;text-align:center;padding-right:5px}ul.goods-list .img{background-position:center center;background-size:cover;display:block;min-height:150px}.goods-list .number{color:#b41800;font-size:12px;padding:0 24px 15px 5px;white-space:nowrap;text-overflow:ellipsis;word-break:break-word;overflow:hidden;padding-top:11px}.goods-list.ustore-mode .number{color:#4ea73c}.goods-list .number span{font-size:18px}.goods-list .number del{color:#707070;display:none}.goods-list .name{color:#1a1a1a;margin-top:6px;font-size:14px;margin-bottom:0;line-height:normal;padding-right:52px;text-overflow:ellipsis;overflow:hidden;padding:0 5px;-webkit-line-clamp:2;display:-webkit-box;-webkit-box-orient:vertical;white-space:pre-wrap;height:40px;line-height:19px}.goods-list .name.has-flags{-webkit-line-clamp:1;height:20px}.goods-list .flags{padding:0 5px;font-size:11px;color:#94929e;list-style:none}.goods-list .flags li img{height:15px;width:15px;vertical-align:middle}.goods-list .flags li{background:#fff;display:inline-block;font-size:10px}.goods-list .flags li:nth-child(3),.goods-list .flags li:nth-child(4){display:none}ul.goods-list{background-color:inherit}.goods-list .buy{position:absolute;bottom:10px;right:12px;font-size:1.5em;color:#B41800}.goods-list.line>li,.line>.goods-list>li{width:100%}.goods-list.line>li .wrapper,.line>.goods-list>li .wrapper{padding-left:125px;min-height:130px;position:relative;overflow:hidden}.goods-list.line>li .wrapper .img,.line>.goods-list>li .wrapper .img{height:120px;width:120px;position:absolute;left:0;top:5px}.goods-list.line>li .wrapper .name,.line>.goods-list>li.wrapper .name{margin-top:15px;font-size:14px;white-space:inherit}.goods-list.line>li .wrapper .number,.line>.goods-list>li .wrapper .number{position:absolute;bottom:0}.goods-list.line>li .wrapper .buy,.line>.goods-list>li .wrapper .buy{bottom:10px}.goods-list.line>li,.line>.goods-list>li{padding:0}.goods-list.line>li+li,.line>.goods-list>li+li{border-top:solid 1px #e2e2e2}.goods-list.line .flags li:nth-child(3),.goods-list.line .flags li:nth-child(4),.line>.goods-list .flags li:nth-child(3),.line>.goods-list .flags li:nth-child(4){display:inline-block}.goods-list.line .name.has-flags,.line>.goods-list .name.has-flags{-webkit-line-clamp:2;height:42px;margin-bottom:10px}
			</style>
		</div>
		<div data-register="add-to-cart-btn" style="display: none;">
			<style>.add-to-cart-btn{position:absolute;bottom:10px;right:12px}.add-to-cart-btn .text{font-size:12px;border:solid 1px;padding:5px;border-radius:5px;margin-bottom:5px;position:relative;bottom:0;right:0;background:#b41800;color:#fff;border-color:#b41800}.add-to-cart-btn .icon.cart{background-image:url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAACUUlEQVRYR+2XT07bQBTGf8+C0h3uoti7hhMQTtCwrHBEjgAnIJyA9AbhBE1PQCUHddncoO4N6M5RF6W7Itq8agY7coJTT0wQbdVsEjvvzzffvPfmGxkGfERokn+URIWTdspo+q7ixzCgi3AQpey5+uR2Em/RwSsAgBbKzpqw+yrl0iVgHNITOI1SxMW+aHPH4dzHX3/KV4XX7ZSeS8CVAjAJhyH6zwC4eE5z4nEqgp+xealK0h5zZp5L92yVDGQ11i0UuS/CDkpyc83egwMoqyHDinqMEN49CgADKg7oi3DweACy1v0P4O9k4H1IY/ITf/8LiS2ouVFspumTdRr5/2WdkPvUYiCr4GOBo/2UQRGAHeUbfAC2o/F0+NzBcC8AJsnaBiMzUAyICTTMYXTznWd5cpnQejAGzHKKIMxUs0e6+YbtquTFbau1BTmfMyDMS+WbS/KVAZhhwmxDBe3FQrhXDZRVtekMVwFTzUDAlQp9V0HiIlrmbX7LQBwyEmUzGrNbJ7iLTxxwbgu2dEhs0RHPGgw8GLgEXMZmonQQuignC0WkVbrQQ9hcJrirbS75KlVsHNJyDepqV5T8lQBcg9a1+3MBGDEpHsd1V+bgN4hS3pYycBFyqPBGlU9WOhsVC58Vt5tSWXKBBvBiJuaiLjBzwARpp7cFOP/ssLqy43eE4rfHt9fAYWjb+2X5HAhIREiilMPcWJVm7lwXQHG4WU0Bh+UAMoWD0rfJhO4yV7UygPm2muGGcmVjKmcLuyCb1ZYBhcEqzoVpTMU3MX9c0/sFcgBGOXC3DtQAAAAASUVORK5CYII=);background-size:100% 100%;height:24px;width:24px;display:inline-block}.add-to-cart-btn .text{display:none}.add-to-cart-btn.buy-mode .icon.cart,.add-to-cart-btn.buy-mode .text.cart,.goods-list.buy-mode .add-to-cart-btn .icon.cart,.goods-list.buy-mode .add-to-cart-btn .text.cart{display:none}.add-to-cart-btn.buy-mode .text.buy,.goods-list.buy-mode .add-to-cart-btn .text.buy{display:block}.add-to-cart-btn.buy-mode.ustore-mode .text.buy,.goods-list.buy-mode.ustore-mode .add-to-cart-btn .text.buy{background:#4ea73c;border-color:#4ea73c}.add-to-cart-btn.cart-mode .icon.cart,.add-to-cart-btn.cart-mode .text.buy,.goods-list.cart-mode .add-to-cart-btn .icon.cart,.goods-list.cart-mode .add-to-cart-btn .text.buy{display:none}.add-to-cart-btn.cart-mode .text.cart,.goods-list.cart-mode .add-to-cart-btn .text.cart{display:block;background:0 0;border-color:#b41800;color:#b41800}.add-to-cart-btn.cart-mode.ustore-mode .text.cart,.goods-list.cart-mode.ustore-mode .add-to-cart-btn .text.cart{border-color:#4ea73c;color:#4ea73c}
			</style>
		</div>
		<form action="https://api.toomao.com/1.1/cart/update" data-role="formJSON">
			<input type="hidden" name="add.0.seriesId">
			<input type="hidden" name="add.0.amount" value="1">
		</form>
		<style>.goods-list .img{ height:175px}</style>
	</body>
</html>