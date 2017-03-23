<!DOCTYPE html>
<html>
<head>
	<%@include file="/WEB-INF/page/include.jsp"%>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no">
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent">
    <meta name="format-detection" content="telephone=no">
    <meta name="copyright" content="浙江蓝麦电子商务有限公司版权所有">
    <meta name="description" content="土冒网致力于搭建全国最好的故事性特产平台，土冒网以一座城市的历史，本地特色农业背后的故事来讲述特产文化，诉说乡土情怀，为商家和用户搭建最好的“互联网+特色农业”平台。一座城市一段历史，一个特产一个故事; 讲述的是文化，诉说的是情怀!">
    <meta name="author" content="zhoukekestar@163.com">
    <meta name="keywords" content="土冒,土冒网,土特产,特产,手机版">
    <meta content="yes" name="apple-mobile-web-app-capable">
    <meta content="yes" name="apple-touch-fullscreen">
    <meta name="App-Config" content="fullscreen=yes,useHistoryState=yes,transition=yes">
    <meta name="cdn" content="true">
    <link rel="dns-prefetch" href="//hm.baidu.com">
    <link rel="dns-prefetch" href="//at.alicdn.com">
    <link rel="dns-prefetch" href="//static.toomao.com">
    <link rel="dns-prefetch" href="//toomaocommon.duapp.com">
    <link rel="dns-prefetch" href="//pic.toomao.com">
    <link rel="dns-prefetch" href="//api.toomao.com">
    <link rel="stylesheet" href="//at.alicdn.com/t/font_1463381134_5581925.css">
    <link rel="stylesheet" href="//common.toomao.com/css?p=bootstrap-core.css,buttons.css,common.css,citySelect,clearable,deletable,events,formJSON,formValidator,formOnInvalid,lazyload,loadpage,loadingPage,shareWX,tabs,template,toast,ajaxUpload,webcom">
    <script type="text/javascript" src="<%=path%>/WEB-INF/page/include.js"></script>
    <link href="<%=path%>/css/cart.css" rel="stylesheet" type="text/css" />
    <title>购物车</title>
</head>
<body>
    <div data-role="page" class="page">
        <link rel="import-webcom" href="//common.toomao.com/webcom?q=mobile:goods-list@2.0.1,mobile:recommended-goods,mobile:mobile-menu,mobile:add-to-cart-btn,mobile:show-cart-number,mobile:cart-number-adjuster">
        <div data-is="show-cart-number" data-bind="data-was-hidden-by-webcom">
            <form action="https://api.toomao.com/1.1/cart/count" data-role="formJSON" method="GET"></form>
        </div>
        <div data-is="mobile-menu" data-bind="data-was-hidden-by-webcom">
            <nav class="menu">
                <ul class="menu-list clearfix">
                    <li>
                        <a href="/square">
                            <i class="iconfont icon-square"></i>
                            <span>广场</span>
                        </a>
                    </li>
                    <li>
                        <a href="/discover">
                            <i class="iconfont icon-discover"></i>
                            <span>发现</span>
                        </a>
                    </li>
                    <li>
                        <a href="/area">
                            <i class="iconfont icon-area"></i>
                            <span>区域馆</span>
                        </a>
                    </li>
                    <li class="active">
                        <a href="/cart">
                            <i class="iconfont icon-cart"></i>
                            <span>购物车</span>
                        </a>
                    </li>
                    <li>
                        <a href="/mine">
                            <i class="iconfont icon-user"></i>
                            <span>我的</span>
                        </a>
                    </li>
                </ul>
            </nav>
        </div>
        <div class="cart-wrapper">
            <ul class="shop-list"> 
                <li class="order">
                    <a href="/shop/565ea61f0cf248f0b5994199" class="shop header">
                        <span class="checkbox" onclick="shopClick.apply(this, arguments)"></span>
                        <i class="iconfont icon-shop"></i>
                        <span class="name">美粒壳新疆特产</span>
                        <i class="iconfont icon-right"></i>
                    </a>
                    <ul class="items-list"> 
                        <li class="item" data-price="45" data-seriesid="56726a830cf20a37de3e04e6">
                            <span class="checkbox" onclick="itemClick.apply(this, arguments)"></span>
                            <a href="/shop/goods/56726a830cf20a37de3e04e5" class="item-img img" style="background-image: url(http://pic.toomao.com/81c24ef1203aa9b5f110a86976173059113f0ece)"></a>
                            <div class="item-title name">新疆特产 美粒壳黑香妃葡萄干320g</div>
                            <div class="item-price price">45.00</div>
                            <div data-is="cart-number-adjuster" data-bind="data-was-hidden-by-webcom" class="cart-number-adjuster">
                                <i class="iconfont icon-minus" style="color: #ccc;"></i>
                                <input class="visiable-input" name="" value="1" disabled="">
                                <i class="iconfont icon-plus" data-max="100"></i>
                            </div>
                            <div class="delete" onclick="deleteCurrent(&quot;56726a830cf20a37de3e04e6&quot;)">删除</div>
                        </li>
                    </ul>
                </li>
                <li class="order">
                    <a href="/shop/56493abf0cf2c3cd32ff3818" class="shop header">
                        <span class="checkbox" onclick="shopClick.apply(this, arguments)"></span>
                        <i class="iconfont icon-shop"></i>
                        <span class="name">姚生记</span>
                        <i class="iconfont icon-right"></i>
                    </a>
                    <ul class="items-list">
                        <li class="item" data-price="37.9" data-seriesid="56dfd04098a11377df727f9f">
                            <span class="checkbox" onclick="itemClick.apply(this, arguments)"></span>
                            <a href="/shop/goods/56dfd04098a11377df727f9e" class="item-img img" style="background-image: url(http://pic.toomao.com/595a261063835422c67940b07963ce19111bc6f0)"></a>
                            <div class="item-title name">姚生记口感细腻山核桃奶油味160g 满48包邮</div>
                            <div class="item-price price">37.90</div>
                            <div data-is="cart-number-adjuster" data-bind="data-was-hidden-by-webcom" class="cart-number-adjuster">
                                <i class="iconfont icon-minus" style="color: #ccc;"></i>
                                <input class="visiable-input" name="" value="1" disabled="">
                                <i class="iconfont icon-plus" data-max="966"></i>
                            </div>
                            <div class="delete" onclick="deleteCurrent(&quot;56dfd04098a11377df727f9f&quot;)">删除</div>
                        </li>
                    </ul>
                    <a class="take-a-look" href="/shop/56493abf0cf2c3cd32ff3818"> 差10.10元,即可享受"满48.00元包邮"优惠 
                        <span class="right">去凑单&gt;</span> 
                    </a>  
                </li>  
            </ul>
        </div>
        <div class="cart-footer"> 
            <span class="checkbox" onclick="checkAll.call(this)"></span> 
            <span>合计：<i>￥0.00</i>（不含邮费）</span> 
            <button class="disabled" onclick="buy.call(this)">结算 </button>
        </div>
    </div>
</body>
</html>