var urlPath = path + "/userRole/userRole_";
var tableName = "roleTable";
var relationTable = "relationTable";
var userRoleTable = "userRoleTable";
/*初始化*/
$(function(){
	//加载用户列表
	$("#" + tableName).flexigrid({
		url : urlPath + "getRoles?"+ajaxFlag+"&tmp="+ Math.round(Math.random()*100000),
		dataType : 'json',
		colModel : [
			{	display : '角色名称', 
				name : "roleName",
				width : "200",
				align : 'center'
			}
			,
			{	display : '操作', 
				width : "60",
				align : 'center',
				render:function(val,row){
					var result = "";
					result += "<input type='button' name='selectBut' class='inputB' value='选择' onclick='checkBut("+row.id+",this)'/>";
					return result;
				}
			}
        ],
        rp : 10,
		useRp : false,	
		rowId:"id",
		resizable : false,		
		autoload :false,
		sortname:"id",
		sortorder:"desc",
		singleSelect : false,
		width : "310",			
		height : "auto",
		//title : "原创信息",
		showTableToggleBtn: false,
		showToggleBtn : false,
		onSubmit:function(){
			showWaitDiv(true);
			return true;
		},
		onSuccess:function(grid,data){
			showWaitDiv(false);
			if(data!=undefined){
				if(data.page>1 && data.rows.length==0){
					$('#' + actionTable).flexOptions({
						newp : data.page-1,
						addparams : params
					}).flexReload();
				}
			}
			return true;
		}	
	});
	
	searchRoleName();
});
/*查询条件*/
function searchRoleName(){
	var params = [];
	var roleName = $("#roleNameQuery").val();
	if(roleName != null && roleName != ""){
		params[params.length]={
				name:"roleName",
				value:roleName	
		};
	}
	$('#' + tableName).flexOptions({
		newp : 1,
		addparams : params
	}).flexReload();
}
//保存角色用户
function saveRelation(){
	var ids = "";
	var arr = $("#"+relationTable).getSelectedRows();
	for(var i=0;i<arr.length;i++){
		ids += arr[i].attr("id")+",";
	}
	ids = ids.replace(/,$/,"");
	if(ids == ""){
		alert("至少选中一个用户！");
		return;
	}
	var roleId = $("#userRoleId").val();
	var url = urlPath + "saveRelation?"+ajaxFlag+"&tmp="+ Math.round(Math.random()*100000);
	$.ajax({
		url:url,
		type:"post",
		dataType:"json",
		data:"roleId="+roleId+"&userIds="+ids,
		beforeSend:function(){
			showWaitDiv(true);
		},
		success:function(data){
			showWaitDiv(false);
			if(ajaxResultValidate(data)){
				$("#"+userRoleTable).flexReload();
				showCustomDiv(false,'addRelationDiv',1,false);
			}
		}
	});
}
function getRoleUser(){
	//加载用户列表
	$("#" + userRoleTable).flexigrid({
		url : urlPath + "getRoleUsers?"+ajaxFlag+"&tmp="+ Math.round(Math.random()*100000),
		dataType : 'json',
		colModel : [
			{	display : '用户名', 
				width : "120",
				align : 'center',
				render:function(val,row){
					var content = stringFilter(row.userName);
					return content;
				}
			}
			,
			{	display : '真实姓名', 
				width : "100",
				align : 'center',
				render:function(val,row){
					var content = stringFilter(row.realName);
					return content;
				}
			}
			,
			{	display : '性别', 
				width : "30",
				align : 'center',
				render:function(val,row){
					if(row.sex!=null&&row.sex=="0"){
						return "女";
					}else{
						return "男";
					}					
				}
			}
			,
			{	display : '职位', 
            	name :'_postName',
            	width : "80",
            	align : 'center'
            }
			,
			{	display : '部门', 
            	name :'_deptName',
            	width : "100",
            	align : 'center'
            }
			,
			{	display : '最后登录时间', 
            	name :'lastLoginTime',
            	width : "150",
            	align : 'center',
            	render:function(val,row){
            		if(row.lastLoginTime!=null&&row.lastLoginTime!="")
            			return row.lastLoginTime.replace(/.0$/g,"");
            		return "";
				}
            }
			,
			{	display : '最后登录IP', 
            	name :'lastLoginIP',
            	width : "120",
            	align : 'center'
            }
			,
			{	display : '是否离职', 
				width : "50",
				align : 'center',
				render:function(val,row){
					if(row.separationFlag==0){
						return "离职";
					}else{
						return "在职";
					}
				}
			}
			,
			{   display : '操作', 
				width : "50",
				align : 'center',
				render:function(val,row){
					var result = "<input type='button' class='inputB' value='删除' onclick='deleteRoleUser("+row.id+")'/>";
					return result;
				}
			}
            ],
        rp : 10,
		useRp : false,	
		rowId:"id",
	    showcheckbox:true,
		resizable : false,		
		autoload :false,
		sortname:"id",
		sortorder:"desc",
		singleSelect : false,
		width : "930",			
		height : "auto",
		//title : "原创信息",
		checkBoxId : "topCheckbox",
		showTableToggleBtn: false,
		showToggleBtn : false,
		onSubmit:function(){
			showWaitDiv(true);
			return true;
		},
		onSuccess:function(grid,data){
			showWaitDiv(false);
			if(data!=undefined){
				if(data.page>1 && data.rows.length==0){
					$('#' + userRoleTable).flexOptions({
						newp : data.page-1,
						addparams : params
					}).flexReload();
				}
			}
			return true;
		}	
	});
	searchDataTable2();
}
/*查询条件*/
function searchDataTable2(){
	var params = [];
	var roleId = $("#userRoleId").val();
	var userName = $("#userNameRole").val();
	if(userName != null && userName != ""){
		params[params.length]={
				name:"userName",
				value:$.trim(userName)	
		};
	}
	var realName = $("#realNameRole").val();
	if(realName != null && realName != ""){
		params[params.length]={
				name:"realName",
				value:$.trim(realName)		
		};
	}
	if(roleId != null && roleId != ""){
		params[params.length]={
				name:"roleId",
				value:roleId		
		};
	}
	$('#' + userRoleTable).flexOptions({
		newp : 1,
		addparams : params
	}).flexReload();
}
function getUser(){
	$("#" + relationTable).flexigrid({
		url : urlPath + "getUserList?"+ajaxFlag+"&tmp="+ Math.round(Math.random()*100000),
		dataType : 'json',
		colModel : [
			{	display : '用户名', 
				width : "120",
				align : 'center',
				render:function(val,row){
					var content = stringFilter(row.userName);
					return content;
				}
			}
			,
			{	display : '真实姓名', 
				width : "100",
				align : 'center',
				render:function(val,row){
					var content = stringFilter(row.realName);
					return content;
				}
			}
			,
			{	display : '性别', 
				width : "30",
				align : 'center',
				render:function(val,row){
					if(row.sex!=null&&row.sex=="0"){
						return "女";
					}else{
						return "男";
					}					
				}
			}
			,
			{	display : '职位', 
            	name :'_postName',
            	width : "80",
            	align : 'center'
            }
			,
			{	display : '部门', 
            	name :'_deptName',
            	width : "100",
            	align : 'center'
            }
			,
			{	display : '最后登录时间', 
            	name :'lastLoginTime',
            	width : "150",
            	align : 'center',
            	render:function(val,row){
            		if(row.lastLoginTime!=null&&row.lastLoginTime!="")
            			return row.lastLoginTime.replace(/.0$/g,"");
            		return "";
				}
            }
			,
			{	display : '最后登录IP', 
            	name :'lastLoginIP',
            	width : "120",
            	align : 'center'
            }
			,
			{	display : '是否离职', 
				width : "50",
				align : 'center',
				render:function(val,row){
					if(row.separationFlag==0){
						return "离职";
					}else{
						return "在职";
					}
				}
			}
			,
			{   display : '操作', 
				width : "50",
				align : 'center',
				render:function(val,row){
					var result = "<input type='button' class='inputB' value='选择' onclick='selectUser("+row.id+")'/>";
					return result;
				}
			}
            ],
        rp : 10,
		useRp : false,	
		rowId:"id",
	    showcheckbox:true,
		resizable : false,		
		autoload :false,
		sortname:"id",
		sortorder:"desc",
		singleSelect : false,
		width : "950",			
		height : "auto",
		//title : "原创信息",
		checkBoxId : "topCheckbox",
		showTableToggleBtn: false,
		showToggleBtn : false,
		onSubmit:function(){
			showWaitDiv(true);
			return true;
		},
		onSuccess:function(grid,data){
			showWaitDiv(false);
			if(data!=undefined){
				if(data.page>1 && data.rows.length==0){
					$('#' + relationTable).flexOptions({
						newp : data.page-1,
						addparams : params
					}).flexReload();
				}
			}
			return true;
		}	
	});
	searchDataTable();
}
/*查询条件*/
function searchDataTable(){
	var params = [];
	var roleId = $("#userRoleId").val();
	var userName = $("#userNameQuery").val();
	if(userName != null && userName != ""){
		params[params.length]={
				name:"userName",
				value:$.trim(userName)	
		};
	}
	var realName = $("#realNameQuery").val();
	if(realName != null && realName != ""){
		params[params.length]={
				name:"realName",
				value:$.trim(realName)		
		};
	}
	if(roleId != null && roleId != ""){
		params[params.length]={
				name:"roleId",
				value:roleId		
		};
	}
	$('#' + relationTable).flexOptions({
		newp : 1,
		addparams : params
	}).flexReload();
}
/**
 * 角色关联用户权限
 * @param roleId
 */
function relateUser(){
	var roleId = $("#userRoleId").val();
	if(roleId.length==0){
		alert("请先选择一个角色！");return;
	}
	getUser();
	showCustomDiv(true,'addRelationDiv',1,false);
}
/**
 * 获取关联角色的用户
 * @param roleId
 * @param obje
 */
function checkBut(roleId,obje){
	var params = $("input[name='selectBut']")
	$.each(params,function(i,n){
		$(n).removeAttr("disabled");
	});
	$(obje).attr("disabled","disabled");
	$("#userRoleId").val(roleId);
	getRoleUser();
}
//添加一个用户
function selectUser(userId){
	var roleId = $("#userRoleId").val();
	var url = urlPath + "saveRoleUser?"+ajaxFlag+"&tmp="+ Math.round(Math.random()*100000);
	$.ajax({
		url:url,
		type:"post",
		dataType:"json",
		data:"roleId="+roleId+"&userId="+userId,
		beforeSend:function(){
			showWaitDiv(true);
		},
		success:function(data){
			showWaitDiv(false);
			if(ajaxResultValidate(data)){
				$("#"+userRoleTable).flexReload();
				$("#"+relationTable).flexReload();
			}
		}
	});
}
function batchDelete(){
	var ids = "";
	var arr = $("#"+userRoleTable).getSelectedRows();
	for(var i=0;i<arr.length;i++){
		ids += arr[i].attr("id")+",";
	}
	ids = ids.replace(/,$/,"");
	if(ids == ""){
		alert("至少选中一个用户！");
		return;
	}
	var roleId = $("#userRoleId").val();
	var url = urlPath + "delRelation?"+ajaxFlag+"&tmp="+ Math.round(Math.random()*100000);
	$.ajax({
		url:url,
		type:"post",
		dataType:"json",
		data:"roleId="+roleId+"&userIds="+ids,
		beforeSend:function(){
			showWaitDiv(true);
		},
		success:function(data){
			showWaitDiv(false);
			if(ajaxResultValidate(data)){
				$("#"+userRoleTable).flexReload();
			}
		}
	});
}
function deleteRoleUser(userId){
	var roleId = $("#userRoleId").val();
	var url = urlPath + "delRelation?"+ajaxFlag+"&tmp="+ Math.round(Math.random()*100000);
	$.ajax({
		url:url,
		type:"post",
		dataType:"json",
		data:"roleId="+roleId+"&userIds="+userId,
		beforeSend:function(){
			showWaitDiv(true);
		},
		success:function(data){
			showWaitDiv(false);
			if(ajaxResultValidate(data)){
				$("#"+userRoleTable).flexReload();
			}
		}
	});
}