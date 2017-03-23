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
	<link rel="stylesheet" href="//at.alicdn.com/t/font_1463381134_5581925.css">
	<link rel="stylesheet" href="//common.toomao.com/css?p=bootstrap-core.css,buttons.css,common.css,citySelect,clearable,deletable,events,formJSON,formValidator,formOnInvalid,lazyload,loadpage,loadingPage,shareWX,tabs,template,toast,ajaxUpload,webcom"> 
	<title>广场</title>
</head>
<body style="width: 540px; margin: 0px auto;">
	<div data-role="page">
		<link href="<%=path%>/css/square-index.css" rel="stylesheet" type="text/css" />
		<!-- <link rel="import-webcom" href="//common.toomao.com/webcom?q=mobile:mobile-menu,mobile:to-top,mobile:slider"> -->
		<div class="square">
			<div class="square-banner"> 
				<div class="icons">
					<a href="/square/search" class="iconfont icon-search"></a>
				</div>
				<div data-is="slider" data-bind="data-was-hidden-by-webcom" class="slider">
					<div class="slider-wrapper" style="display:none;"></div>
					<ul class="islider-outer" style="cursor: pointer;">
						<li class="islider-node islider-active" style="transform: translateZ(0px) translateX(0px); overflow: auto; transition: -webkit-transform 800ms ease;">
							<a href="https://static.toomao.com/temphtml/index.v3.html?id=ec753b8c312d447ab81253735888b4f2" class="swiper-slide" data-backgroundimage="//pic.toomao.com/21542821a156c433d583411b1cd64e9bf3352ed3" style="background-image: url(&quot;//pic.toomao.com/21542821a156c433d583411b1cd64e9bf3352ed3&quot;);"></a>
						</li>
						<li class="islider-node islider-next" style="transform: translateZ(0px) translateX(540px); overflow: auto; transition: none;"><a href="https://static.toomao.com/temphtml/index.v3.html?id=ef2838903ba2401aacae2e8cbce2b5f1" class="swiper-slide" data-backgroundimage="//pic.toomao.com/0f5beb58a6ce6c1af45e8b8ff3a6c42bb7d55cdb" style="background-image: url(&quot;//pic.toomao.com/0f5beb58a6ce6c1af45e8b8ff3a6c42bb7d55cdb&quot;);"></a>
						</li>
						<li class="islider-node islider-prev" style="transform: translateZ(0px) translateX(-540px); overflow: auto; transition: -webkit-transform 800ms ease;"><a href="https://m.toomao.com/shop/goods/5718b15161a95f7cf5dda26e?v=1.2" class="swiper-slide" data-backgroundimage="//pic.toomao.com/e5818cc98b28e165905a1bbff86866e061b04910" style="background-image: url(&quot;//pic.toomao.com/e5818cc98b28e165905a1bbff86866e061b04910&quot;);"></a>
						</li>
					</ul>
					<ul class="islider-dot-wrap">
						<li class="islider-dot" index="0"></li>
						<li class="islider-dot active" index="1"></li>
						<li class="islider-dot" index="2"></li>
						<li class="islider-dot" index="3"></li>
					</ul>
				</div>
			</div>
			<div class="cate-list-wrapper">
				<ul class="cate-list sum-8">
					<li> 
						<a href="/square/cate/9999?name=全部"> 
							<img src="//pic.toomao.com/b5ee2c337302352e6f143bebf606fd4a63f0571a" data-src="//pic.toomao.com/b5ee2c337302352e6f143bebf606fd4a63f0571a" class="">
							<div class="name">全部</div>
						</a>
					</li>
					<li>
						<a href="/square/cate/1000?name=零食"> 
							<img src="//pic.toomao.com/46c12e928eea967fa7224095f629dbaf98cd3232" data-src="//pic.toomao.com/46c12e928eea967fa7224095f629dbaf98cd3232" class="">
							<div class="name">零食</div>
						</a>
					</li>
					<li>
						<a href="/square/cate/1200?name=生鲜">
							<img src="//pic.toomao.com/41c09e38b3fced9ff39bb5e85a3f9733e64298df" data-src="//pic.toomao.com/41c09e38b3fced9ff39bb5e85a3f9733e64298df" class="">
	    					<div class="name">生鲜</div>
	    				</a> 
	    			</li> 
	   				<li> 
	    				<a href="/square/cate/1500?name=粮油">
	     					<img src="//pic.toomao.com/065d6b9169d47af178656cbc4185a72843876327" data-src="//pic.toomao.com/065d6b9169d47af178656cbc4185a72843876327" class=""> 
	    					<div class="name">粮油</div>
	        			</a>
	        		</li> 
			        <li>
			        	<a href="/square/cate/1300?name=滋补"> 
			        		<img src="//pic.toomao.com/4aa7bb020ddbd3dfb123993cc454feceb751ac36" data-src="//pic.toomao.com/4aa7bb020ddbd3dfb123993cc454feceb751ac36" class="">
			        		<div class="name">滋补</div>
			        	</a>
			        </li>
	         		<li>
	         			<a href="/square/cate/1100?name=果蔬">
		         			<img src="//pic.toomao.com/7876aa8203d8e20974e9f459d4501be750850fa5" data-src="//pic.toomao.com/7876aa8203d8e20974e9f459d4501be750850fa5" class="">
		         			<div class="name">果蔬</div>
	          			</a>
	           		</li>
	          		<li>
	           			<a href="/square/cate/1600?name=副食">
	           				<img src="//pic.toomao.com/b6e73c3da510b42ac90b6cea2772bb6acdddfdde" data-src="//pic.toomao.com/b6e73c3da510b42ac90b6cea2772bb6acdddfdde" class="">
	           				<div class="name">副食</div>
	          			</a>
	          		</li>
	           		<li>
	           			<a href="/square/cate/1400?name=饮品">
	           				<img src="//pic.toomao.com/90f3a0bec147620538ca7698552f57a3e3afd3cf" data-src="//pic.toomao.com/90f3a0bec147620538ca7698552f57a3e3afd3cf" class="">
	           				<div class="name">饮品</div>
	           			</a>
	           		</li>
	           </ul>
	        </div>
	        <a class="image-view" href="https://m.toomao.com/redirect?url=https%3A%2F%2Fstatic.toomao.com%2Ftemphtml%2Findex.v3.html%3Fid%3D7db580989ccc447c8506e4d232d9087b"> 
	           	<img src="//pic.toomao.com/67c0e96e1af09f1ea6ef8f5f0ee03206ba3bc86e" data-src="//pic.toomao.com/67c0e96e1af09f1ea6ef8f5f0ee03206ba3bc86e" class="">
	        </a>
	        <a class="image-view" href="https://static.toomao.com/temphtml/index.v3.html?id=c0dc6114754e413e9115ab7367898086">
	            <img src="//pic.toomao.com/ae4e07b7ac438a6787f96677c0692f29948fe088" data-src="//pic.toomao.com/ae4e07b7ac438a6787f96677c0692f29948fe088" class="">
        	</a>
            <a class="image-view" href="https://m.toomao.com/square/special/st-2,_573aefeb61a95f265343e7a8?t=138&amp;v=1.2"> 
	           <img src="//pic.toomao.com/47e8e745f6694ddb7ce822b09f594323fc148e22" data-src="//pic.toomao.com/47e8e745f6694ddb7ce822b09f594323fc148e22" class="">
	        </a>
	        <a class="image-view" href="https://m.toomao.com/square/special/st-2,_570ce92d61a95f52953c38dc?t=295&amp;v=1.2"> 
	           <img src="//pic.toomao.com/e9b6ab0ef137b2dc7da66a022f5af81ce984457b" data-src="//pic.toomao.com/e9b6ab0ef137b2dc7da66a022f5af81ce984457b" class="">
	        </a>
	        <a class="image-view" href="https://m.toomao.com/square/special/st-2,_57216d9498a1135d4f13b668?t=157&amp;v=1.2">
	           <img src="//pic.toomao.com/52cd873139e751f222c8f44bacc811030fd81d67" data-src="//pic.toomao.com/52cd873139e751f222c8f44bacc811030fd81d67" class="">
	        </a>
	        <a class="image-view" href="https://m.toomao.com/square/special/st-2,_570ce69898a1136ac60a5144?t=162&amp;v=1.2">
	           <img src="//pic.toomao.com/937eac4102732af210f6aea088c3e0b5836cbd8a" data-src="//pic.toomao.com/937eac4102732af210f6aea088c3e0b5836cbd8a" class="">
	        </a>
            <a class="image-view" href="https://m.toomao.com/square/special/st-2,_57577b4461a95f578c1f8431?t=37&amp;v=1.2">
				<img src="//pic.toomao.com/97909fbf33f83e707bcba80879fc046085df38b7" data-src="//pic.toomao.com/97909fbf33f83e707bcba80879fc046085df38b7" class="">
			</a>       
			<a class="image-view" href="https://static.toomao.com/temphtml/index.v3.html?id=ec753b8c312d447ab81253735888b4f2"> 
				<img src="//pic.toomao.com/c1f345bc63199c5b040688b9d94bc519da846522" data-src="//pic.toomao.com/c1f345bc63199c5b040688b9d94bc519da846522" class="">
 			</a>         
			<div class="square-title-wrapper"> 
 				<div class="square-line"></div>
  				<div class="square-title">
  					<img src="//pic.toomao.com/hui.png">特食汇
  				</div> 
  			</div> 
  			<ul class="special-list">  
  				<li>
					<a href="https://m.toomao.com/square/special/st-1,_571599dd98a1132c2ff9db13?t=85&amp;v=1.2">
						<img src="//pic.toomao.com/7b5b2985888c5aece91c8296131bdb49913a4f5b" data-src="//pic.toomao.com/7b5b2985888c5aece91c8296131bdb49913a4f5b" class="">
					</a>
 				 </li> 
			   	<li>
				   <a href="https://m.toomao.com/square/special/st-1,_56d8ee123ef3c531e79f90ce?t=149&amp;v=1.2">
				  		<img src="//pic.toomao.com/cb83e90841abc201f33dbd3528d5000c33c59a68" data-src="//pic.toomao.com/cb83e90841abc201f33dbd3528d5000c33c59a68" class="">
				   </a>
			   	</li>
     			<li>
     				<a href="https://m.toomao.com/square/special/st-1,_56e004d198a1137bc81975f2?t=12&amp;v=1.2">
     					<img src="//pic.toomao.com/ad0a3e73d7223de8137a22234d36f19a33eb7482" data-src="//pic.toomao.com/ad0a3e73d7223de8137a22234d36f19a33eb7482" class="">
     				</a>
     			</li> 
     			<li>
     				<a href="https://static.toomao.com/temphtml/index.v3.html?id=7db580989ccc447c8506e4d232d9087b">
     					<img src="//pic.toomao.com/67c0e96e1af09f1ea6ef8f5f0ee03206ba3bc86e" data-src="//pic.toomao.com/67c0e96e1af09f1ea6ef8f5f0ee03206ba3bc86e" class="">
     				</a>
     			</li>
     		</ul>
     		<div class="square-title-wrapper">
     			<div class="square-line"></div>
     			<div class="square-title">
     				<img src="//pic.toomao.com/pin.png">推荐名品
     			</div>
     		</div>
     		<ul class="special-list">
     			<li>
     				<a href="https://m.toomao.com/shop/goods/src-hot,_5722d80098a1135d4f1b2845?t=1&amp;v=1.2">
     					<img src="//pic.toomao.com/73ee675be6e9e1ac82a4dbe2d506186aa9cd7831" data-src="//pic.toomao.com/73ee675be6e9e1ac82a4dbe2d506186aa9cd7831" class="">
     				</a>
     			</li>
     			<li>
     				<a href="https://m.toomao.com/shop/goods/src-hot,_568b8b500cf21acfd2cdbeaa?t=1&amp;v=1.2">
     					<img src="//pic.toomao.com/7ced1b8a34fd7d6f1fa8db04d505a521c16165e4" data-src="//pic.toomao.com/7ced1b8a34fd7d6f1fa8db04d505a521c16165e4" class="">
     				</a>
     			</li>
     			<li>
     				<a href="https://m.toomao.com/shop/goods/src-hot,_56430f6a0cf28fced3b228f8?t=1&amp;v=1.2">
     					<img src="//pic.toomao.com/acd8ccb136ed5fe06b2ecce47613fe563bf008b7" data-src="//pic.toomao.com/acd8ccb136ed5fe06b2ecce47613fe563bf008b7" class="">
     				</a>
     			</li>
     			<li>
     				<a href="https://m.toomao.com/shop/goods/src-hot,_567262cf0cf20a37de3de537?t=1&amp;v=1.2">
     					<img src="//pic.toomao.com/eeb018459455f47de0725e7e9b1062f220043992" data-src="//pic.toomao.com/eeb018459455f47de0725e7e9b1062f220043992" class="">
     				</a>
     			</li>
     			<li>
     				<a href="https://m.toomao.com/shop/goods/src-hot,_567a72100cf20a37de5d96ce?t=1&amp;v=1.2">
     					<img src="//pic.toomao.com/28ba98588ce42f03e4e86eb050b6715fa5ec6f3f" data-src="//pic.toomao.com/28ba98588ce42f03e4e86eb050b6715fa5ec6f3f" class="">
     				</a>
     			</li>
     			<li>
     				<a href="https://m.toomao.com/shop/goods/src-hot,_56f0b50898a1130973606117?t=1&amp;v=1.2">
     					<img src="//pic.toomao.com/e8f0d6eb53c0cb190f40a11cc04a6619a8e23125" data-src="//pic.toomao.com/e8f0d6eb53c0cb190f40a11cc04a6619a8e23125" class="">
     				</a>
     			</li>
     			<li>
     				<a href="https://m.toomao.com/shop/goods/src-hot,_5695c3830cf2e9f830d9891e?t=1&amp;v=1.2">
     					<img src="//pic.toomao.com/a6ba84b88d5c7973636b1f73415c8e55f6583399" data-src="//pic.toomao.com/a6ba84b88d5c7973636b1f73415c8e55f6583399" class="">
     				</a>
     			</li>
     			<li>
     				<a href="https://m.toomao.com/shop/goods/src-hot,_5694dcab0cf2e9f830d5f959?t=1&amp;v=1.2">
     					<img src="http://m.toomao.com/img/img-default.png" data-src="//pic.toomao.com/a3860861110cef2d37d082e65184d8ace4b0dc97" class="lazy">
     				</a>
     			</li>
     			<li>
     				<a href="https://m.toomao.com/shop/goods/src-hot,_565baabd0cf234c92695b78c?t=1&amp;v=1.2">
     					<img src="http://m.toomao.com/img/img-default.png" data-src="//pic.toomao.com/aee52be60850f68a1e17dc153f69301265eea6e9" class="lazy">
     				</a>
     			</li>
     			<li>
     				<a href="https://m.toomao.com/shop/goods/src-hot,_5729bcf361a95f455e566f86?t=1&amp;v=1.2">
     					<img src="http://m.toomao.com/img/img-default.png" data-src="//pic.toomao.com/b6dad156f47c7c29b28c2e6222d162020b0f596f" class="lazy">
     				</a>
     			</li>
     			<li>
     				<a href="https://m.toomao.com/shop/goods/src-hot,_563c5c190cf2d9597226845c?t=1&amp;v=1.2">
     					<img src="http://m.toomao.com/img/img-default.png" data-src="//pic.toomao.com/0dfde50cba1be4c509e4b92a8ae423b0b2b8cd15" class="lazy">
     				</a>
     			</li>
     			<li>
     				<a href="https://m.toomao.com/shop/goods/src-hot,_56e112ad98a1137bc81daea7?t=1&amp;v=1.2">
     					<img src="http://m.toomao.com/img/img-default.png" data-src="//pic.toomao.com/3f6c2a7b77b4a9c95c3b55afbb3022e7db7d1292" class="lazy">
     				</a>
     			</li>
     			<li>
     				<a href="https://m.toomao.com/shop/goods/src-hot,_564c33dd0cf24e2a8026c296?t=1&amp;v=1.2">
     					<img src="http://m.toomao.com/img/img-default.png" data-src="//pic.toomao.com/b86622a073e29ba167f4760ccbcbfc544ec44c2b" class="lazy">
     				</a>
     			</li>
     			<li>
     				<a href="https://m.toomao.com/shop/goods/src-hot,_56829f340cf23c1c91e0416e?t=1&amp;v=1.2">
     					<img src="http://m.toomao.com/img/img-default.png" data-src="//pic.toomao.com/734266530b92e42b18fda46a63de0b1007aecd97" class="lazy">
     				</a>
     			</li>
     			<li>
     				<a href="https://m.toomao.com/shop/goods/src-hot,_569309670cf2e9f830ce74a2?t=1&amp;v=1.2">
     					<img src="http://m.toomao.com/img/img-default.png" data-src="//pic.toomao.com/7f66d1d7bbea6ea9f9a5f8d73983c08ad33b4c32" class="lazy">
     				</a>
     			</li>
     			<li>
     				<a href="https://m.toomao.com/shop/goods/src-hot,_5730401b61a95f325024ffc6?t=1&amp;v=1.2">
     					<img src="http://m.toomao.com/img/img-default.png" data-src="//pic.toomao.com/e6aaed335bf9bcd4d9901dc3df9a3b7de8e47102" class="lazy">
     				</a>
     			</li>
     			<li>
     				<a href="https://m.toomao.com/shop/goods/src-hot,_56a592073ef3c54c5e582bc5?t=1&amp;v=1.2">
     					<img src="http://m.toomao.com/img/img-default.png" data-src="//pic.toomao.com/7077c07fac145ccede5acbcfbbaa0f21f905aa9f" class="lazy">
     				</a>
     			</li>
     			<li>
     				<a href="https://m.toomao.com/shop/goods/src-hot,_5696103d0cf2e9f830dac1ac?t=1&amp;v=1.2">
     					<img src="http://m.toomao.com/img/img-default.png" data-src="//pic.toomao.com/228248a16294416f4e86342037fa0b655526c041" class="lazy">
     				</a>
     			</li>
     			<li>
     				<a href="https://m.toomao.com/shop/goods/src-hot,_566c30c80cf26ce0d6bd5ba1?t=1&amp;v=1.2">
     					<img src="http://m.toomao.com/img/img-default.png" data-src="//pic.toomao.com/b06018cc14c5afab77d40d1d3b9c0e4d325133e9" class="lazy">
     				</a>
     			</li>
     			<li>
     				<a href="https://m.toomao.com/shop/goods/src-hot,_5729b40d61a95f455e564b2b?t=1&amp;v=1.2">
     					<img src="http://m.toomao.com/img/img-default.png" data-src="//pic.toomao.com/89592c38ae9d1f0ff62fbf3bb50dfdee3947483d" class="lazy">
     				</a>
     			</li>
     		</ul>
     		<div class="toomao-logo"></div>
     	</div>
     	<div data-is="mobile-menu" data-bind="data-was-hidden-by-webcom">
     		<nav class="menu show-menu">
     			<ul class="menu-list clearfix">
     				<li class="active">
     					<a href="/square">
     						<i class="iconfont icon-square"></i>
     						<span>首页</span>
     					</a>
     				</li>
     				<li>
     					<a href="/discover">
     						<i class="iconfont icon-discover"></i>
     						<span>田园</span>
     					</a>
     				</li>
     				<li>
     					<a href="/area">
     						<i class="iconfont icon-area"></i>
     						<span>分类</span>
     					</a>
     				</li>
     				<li>
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