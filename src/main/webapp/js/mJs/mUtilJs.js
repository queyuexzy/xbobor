// 更多信息填写 插件，例如  别名，有多个，在数据库中存储是用“,”分割。
 //需知道hidden的id，要显示所在的div，与要显示的高度
var xx_moreInfoManage = {
	nowInputId : "_nowInput",
	addButtonId : "_addButon",
	viewDivId : "_viewDiv",  
	itemColor : "#dddddd",   //数据移动时的背景颜色

	//创建 hidden的id，div的id ，div的高度
	create : function(hId,dId,divHight){
		var divLeft = document.createElement("div");
		var divRight = document.createElement("div");
		$(divLeft).css({
			"height" : divHight + "px",
			"float" : "left",
			"border-right" : "#779ccb solid 1px"
		});
		var inputText = document.createElement("input");
		$(inputText).attr("type","text");
		$(inputText).attr("class","w250");
		$(inputText).attr("id",xx_moreInfoManage.nowInputId + hId);
		$(inputText).css({
			"width":"90px",
			"position":"relative",
			"top" : "50%"
		});
		
		var inputB = document.createElement("input");
		$(inputB).attr("type","button");
		$(inputB).attr("class","inputB");
		$(inputB).attr("value",">>");
		$(inputB).css({
			"position":"relative",
			"top" : "50%"
		});
		
		$(inputB).click(function(){
			xx_moreInfoManage.add(13, hId, dId);
		});
		
		$(inputText).keydown(function(evt){
			xx_moreInfoManage.add((window.event?window.event:evt).keyCode, hId, dId);
		});
		
		$(divLeft).append(inputText);
		$(divLeft).append(inputB);
		
		$(divRight).attr("id",xx_moreInfoManage.viewDivId + hId);
		$(divRight).css({
			"height" : divHight + "px",
			"overflow-y":"scroll"
		});
		
		$("#" + dId).append(divLeft);
		$("#" + dId).append(divRight);
		
		$("#" + dId).css({
			"background":"none repeat scroll 0 0 #E5F3FB",
			"padding":"5px",
			"border-color":"#A3AAAC #A3AAAC -moz-use-text-color",
			"border-width":"1px 1px medium",
			"border-style":"solid solid none"
		});
		
		xx_moreInfoManage.show(hId,dId);
	},
	
	//添加数据事件
	add : function(keyCode,hId,dId){
		if(keyCode==13){
			var nowName = $("#" + xx_moreInfoManage.nowInputId + hId).val();
			nowName = $.trim(nowName);
			if(xx_moreInfoManage.exist(nowName,hId)){
				var oldName = $("#" + hId).val();
				if(oldName.length==0){
					oldName =",";
				}
				
				oldName = "," + nowName + oldName;
				$("#" + hId).val(oldName);
				
				//动画效果
				xx_moreInfoManage.animate(hId,dId);
			}
			$("#" + xx_moreInfoManage.nowInputId + hId).select();
		}
	},
	//删除数据
	remove : function(evt,hId,dId){
		var removeName = $(evt).html();
		
		var tmpReg = ","+removeName+",";
		var oldStr = $("#" + hId).val();
		if(oldStr.indexOf(tmpReg) >= 0){
			oldStr = oldStr.replace(tmpReg,",");
			$("#" + hId).val(oldStr);
			xx_moreInfoManage.animate(hId, dId, true, evt);
		}
	},
	//判断新增的数据是否存在 
	exist : function(nowName,hId){
		var oprStr = $("#" + hId).val(); 
		if(nowName.indexOf(",")>=0){
			alert("不能包含英文逗号，请重新输入！");
			return false;
		}
		
		var tmpReg = "," + nowName + ",";
		if(oprStr.indexOf(tmpReg)>=0){
			alert("已经存在！请重新输入！");
			return false;
		}
		
		if(nowName.length==0){
			return false;
		}
		
		return true;
	},
	//显示
	show : function(hId,dId){
		var oprStr = $("#"+ hId).val();
		var viewDiv = $("#" + xx_moreInfoManage.viewDivId + hId);
		 
		viewDiv.children().remove();
		viewDiv.empty();
		
		var ul = document.createElement("ul");
		$.each(oprStr.split(","),function(i,tmpStr){
			if(tmpStr.length>0){
				var li = document.createElement("li");
				var a = document.createElement("a");
				$(li).css({
					"list-style": "inside decimal",
					"cursor": "pointer",
					"margin-left": "3px"
				});
				
				$(li).html(tmpStr);
				$(li).click(function(){
					xx_moreInfoManage.remove(this, hId, dId);
				});
				$(ul).append(li);
			}
		});
		viewDiv.append(ul);
	},
	//动画  hidden id，div id ，deleteFlag true 为删除， evt为点击删除的li
	animate : function(hId,dId,deleteFlag,evt){
		if(deleteFlag){
			var tmpClone = $(evt).clone();
			var offset = $(evt).position();
			tmpClone.css({
				"background-color": xx_moreInfoManage.itemColor,
				"left" : offset.left + 15 +  "px",
				"top" : offset.top + "px",
				"position" : "absolute",
				"list-style": "none"
			});
			
			$("#" + xx_moreInfoManage.viewDivId + hId).append(tmpClone);
			
			tmpClone.animate({ left:offset.left + 50,opacity: 'toggle'}, 500 ,"",function(){
				tmpClone.remove();
				xx_moreInfoManage.show(hId,dId);
			});
		}else{
			var toPosition = $("#" + xx_moreInfoManage.viewDivId + hId).position();
			var nowInput = $("#" + xx_moreInfoManage.nowInputId + hId);
			var tmpClone = nowInput.clone();
			var  position = nowInput.position();	
		
			tmpClone.css({
				"background-color": xx_moreInfoManage.itemColor,
				"left" : position.left + "px",
				"top" : position.top + "px",
				"position" : "absolute"
			});
			
			nowInput.parent().append(tmpClone);
			//移动到 显示数据的div坐标,opacity:toggle 为渐消失，时间，“”特殊效果，需要插件，回调函数
			tmpClone.animate({left:toPosition.left,top:toPosition.top,opacity: 'toggle'}, 200 ,"",function(){
				tmpClone.remove();
				xx_moreInfoManage.show(hId,dId);
			});
		}
	}
}