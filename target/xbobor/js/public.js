/*
 * 兼容规范加载器模块
 * 功能描述：
 * 
 *  
 * 
 */

(function(name, context, factory) {

	// 支持 UMD. AMD, CommonJS/Node.js   
	if (typeof module2 !== "undefined" && module.exports) {
		module.exports = factory();
	} else if (typeof define2 === "function" && define.amd) {
		define(factory);
	} else {
		context[name] = factory();
	}

})("P", this, function() {
	var P = {},
	progress = null,
	pgStatus = null,
	lastPubTime = null;
	
	P.publish = function(option,callback){
		progress = null;
		pgStatus = null;
		setInterval(function(){
	  		P.getChange(option,callback);
	  	},1000);
	};
	P.getChange = function(option,callback){
		$.ajax({
			url : option.changeurl,
			type : "post",
			dataType : "json",
			beforeSend : function() {
			},
			success : function(data) {
				if (ajaxResponseValidate(data)) {
					if (data.code < 0) {
						L.msg.error(data.message,function(){
							if(progress != null){
								progress.close();
							}
						});
					} else {
						var status = data.data.status;
						if(status != pgStatus && status < 4){
							if (progress == null) {
								progress = new L.progressBar();
							}
							progress.progress(status);
							pgStatus = status;
						}else {
							if(status == 4 && progress != null){
								pgStatus = status;
								progress.close(function(){
									progress = null;
									if(option.timeurl){
										P.getLastWriteBack(option,callback);
									}
								});
							}
						}
					}
				}
			}
		});
	};
	P.getLastWriteBack = function(option,callback){
		$.ajax({
			url : option.timeurl,
			type : "post",
			dataType : "json",
			beforeSend : function() {
			},
			success : function(data) {
				if (ajaxResponseValidate(data)) {
					if (data.code < 0) {
						L.msg.error(data.message);
					} else {
						if(option.descspan){
							$(option.descspan).text("提示：" + data.data.describe);
						}
				        lastPubTime = data.data._lastWriteTime==null?"":data.data._lastWriteTime.substring(0,19);
				        if(option.timespan){
				        	$(option.timespan).text("最后发布时间：" + lastPubTime.substring(0,19));
				        }
				        if(callback){
				        	callback();
				        }else{
				        	if(option.table){
				        		var param = new Array;
				        		param.push({name:"updateTime",value:lastPubTime});
				        		$(option.table).flexOptions({
				        			newp : 1,
				        			addparams : param
				        		}).flexReload();
				        	}
				        }
					}
				}
			}
		});
	};
	P.publish.prototype = {
		constructor: P.publish,	
		publishData:function(option){
			$.ajax({
	            url: option.url,
	            data: {
	                id: option.id
	            },
	            type: "post",
	            dataType: "json",
	            beforeSend: function () {
	            	progress = new L.progressBar();
	            },
	            success: function (data) {
	                if (ajaxResponseValidate(data)) {
	                    if (data.code < 0) {
	                        L.msg.error(data.message,function(){
	                        	progress.close();
	                        });
	                    } else {
	                    	progress.progress(0);
	                    }
	                }
	            },
	            error: function () {
	                L.msg.error("ajax访问异常");
	            }
	        });
		},
		setWriteTime:function(writeTime){
			if(lastPubTime != null && writeTime != lastPubTime){
				writeTime = lastPubTime;
			}
			return writeTime;
		},
		getStatus:function(){
			return pgStatus;
		}
	};
	return P;
});