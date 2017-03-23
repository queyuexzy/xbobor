(function($) {
	$.PopInput = function(){
		var submitValue = ",";
		var divId = "";
		var className = "";
		var inputWidth = "";
		var addMessage = "保存内容不能为空！";
		var editMessage = "修改后的内容不能为空！";	
		var checkMessage = "还有未保存的项，请保存后再新建！";
		var saveMessage = "还有未保存项，请保存后再提交！";	
		var doubleMessage = "添加的值已经存在，请重新添加！";
		var flag = false;
		var g = {
			/* 添加新的输入框 */
			addPopupInput : function(id){
				var param = $("#"+divId+" > div > input");
				var typeFlag = true;
				$.each(param,function(i,n){
					if(i < param.length - 1){
						if(!$("#add"+i+divId+" :input").attr("readonly")){
							typeFlag = false;
							alert(checkMessage);
							$("#add"+i+divId+" :input").select();
						}
					}
				});
                
				if(typeFlag){
					if(g.getInputValue(param)){
						param.attr("readonly",true);
						g.createInput(submitValue);
					}
				}
		    },
			/* 加载第一个输入框 */
			create : function(){
				$("#"+divId).html("<div id='add0"+divId+"' class='"+className+"'><input type='text' name='addInput' size='"+inputWidth+"'/>" +
				"<i class='popup_i_add' id='"+divId+"iadd0' title='添加'></i></div>");
				$("#"+divId+"iadd0").click(function(){
					g.addPopupInput(0);
				});
		        	g.setFocus();
			},
			
			/* 添加新的输入框时验证当前输入框内容是否为空 */
			getInputValue : function(param){
				submitValue = ",";
		   		var flag1 = false;
				$.each(param,function(i,n){
					 if(n.value.length==0){
						alert(addMessage);
						n.focus();
						flag1 = false;
						return;
					}else{
						if(!g.checkDouble(n.value)){
							submitValue += n.value + ",";
						    flag1 = true;
						}
					}
				});
				return flag1;
			},
			/* 修改完成保存时验证内容是否为空 */
			getEditInputValue : function(param){
				submitValue = ",";
		   		var flag2 = false;
				$.each(param,function(i,n){
					if(i < param.length-1){
						if(n.value.length==0){
							alert(editMessage);
							n.focus();
							flag2 = false;
							return;
						}else{
							if(!g.checkDouble(n.value)){
								submitValue += n.value + ",";
								flag2 = true;
							}
						}
					}	
				});
				return flag2;
			},
			/* 修改输入框内容 */
			editPopupInput : function(id){
				if($("#add"+id+divId+" :input").attr("readonly")){
					$("#add"+id+divId+" :input").attr("readonly",false);
					$("#add"+id+divId+" :input").select();
				}else{
					var param =  $("#"+divId+" > div > input");
					g.getEditInputValue(param);
					$("#add"+id+divId+" :input").attr("readonly",true);
				}
			},
			/* 删除选中的输入框 */
			delPopupInput : function(id){
				$("#add"+id+divId).remove();
				var param =  $("#"+divId+" > div > input");
				g.getEditInputValue(param);
				g.createInput(submitValue);
			},
			/* 清空div的内容后根据已保存的value值重新创建所有输入框 */
			createInput : function(submitValue){
				$("#"+divId).html("");
			 	var values = submitValue.split(","); 
			 	$.each(values,function(i,n){
				 	if(i < (values.length - 1) && i > 0){
						$("#"+divId).append("<div id='add"+(i-1)+divId+"' class='"+className+"'><input type='text' name='addInput' value='"+n+"' readonly='readonly' size='"+inputWidth+"'/>"+
						"<i class='popup_i_edit' id='"+divId+"iedit"+(i-1)+"' title='修改'></i>"+
						"<i class='popup_i_del' id='"+divId+"idel"+(i-1)+"' title='删除'></i></div><div class='popup_cl'></div>");
						$("#"+divId+"iedit"+(i-1)).click(function(){
							g.editPopupInput((i-1));
						});
						$("#"+divId+"idel"+(i-1)).click(function(){
							g.delPopupInput((i-1));
						});
				 	}
				});
				$("#"+divId).append("<div id='add"+(values.length-1)+divId+"' class='"+className+"'><input type='text' name='addInput' size='"+inputWidth+"'/>"+
						"<i class='popup_i_add' id='"+divId+"iadd"+(values.length-1)+"' title='添加'></i></div>");
				$("#"+divId+"iadd"+(values.length-1)).click(function(){
					g.addPopupInput(values.length-1);
				});
				g.setFocus();
			},
			/* 光标定位到最后一个输入框 */
			setFocus : function(){
				var paramNew =  $("#"+divId+" > div > input");
				paramNew[paramNew.length-1].focus();
			},
			checkDouble : function(value){
				var b = false;
				if(!flag){
					if(Number(submitValue.indexOf(","+value+",")) >= 0){
						b = true;
						alert(doubleMessage);	
					}
				}
				return b;
			}
		}
		this.init = function(div,name,width,type){
			divId = div;
			className = name;
			flag = type;
			submitValue = "";
			if(width==null){
				inputWidth = 50;
			}else{
				inputWidth = width;
			}
			g.create();
		},
		/* 提交时检查是否有未保存的项 */
		this.check = function(checkFlag){
			var paramNew =  $("#"+divId+" > div > input");
			var a = true;
			if(paramNew.length==1){
				if(!flag){
					if(submitValue.length==0){
						alert(addMessage);
						a = false;
						return;
					}else if(submitValue == ","){
						alert(addMessage);
						a = false;
						return;
					}
				}
			}else{
				$.each(paramNew,function(i,n){
					if(i < paramNew.length-1){
						if(!$("#add"+i+divId+" :input").attr("readonly")){
							alert(saveMessage);
							n.select();
							a = false;
							return; 
						}
					}
				});
			}
			return a;
		},
		this.getSubmitValue = function(){
			return submitValue;
		},
		this.createInput = function(value){
			if(value.length != 0){
				submitValue = value;
				g.createInput(value);
			}
		}
	}
})(jQuery);
