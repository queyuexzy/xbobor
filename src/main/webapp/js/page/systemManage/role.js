var urlPath = path + "/role/role_";
var tableName = "roleTable";
var relationTable = "relationTable";
/*初始化*/
$(function(){
	//加载用户列表
	$("#" + tableName).flexigrid({
		url : urlPath + "getRoles?"+ajaxFlag+"&tmp="+ Math.round(Math.random()*100000),
		dataType : 'json',
		colModel : [
			{	display : '角色名称', 
				name : "roleName",
				width : "120",
				align : 'center'
			}
			,
			{	display : '操作', 
				width : "310",
				align : 'center',
				render:function(val,row){
					var result = "";
					if(checkHavePrivilegeSet()){
						result += "<input type='button' class='inputB' value='权限设置' onclick='modifyPrivilegeRole("+row.id+",\""+row.roleName+"\")'/>";		
					}
					result += "<input type='button' class='inputB' value='修改' onclick='editRole("+row.id+")'/>";
					result += "<input type='button' class='inputB' value='删除' onclick='deleteRole("+row.id+")'/>";
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
		width : "auto",			
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
		},
		onError : function(errorThrown) {
            alert('<%=AjaxResponse.getAjaxErrorInfo()%>');
			showWaitDiv(false);
		}
	});
	
	searchDataTable();
});

/*回车自动查询*/
document.onkeydown = function(evt){
	 var evt = window.event?window.event:evt;
	 if(evt.keyCode==13)
	 {
		 searchDataTable();
	 }
}

/*查询条件*/
function searchDataTable(){
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
//添加角色
function addRole(){
	cleanDiv();
	showCustomDiv(true,'addDiv',1,false);
}
//清空div
function cleanDiv(){
	$("#roleName").val("")
	$("#roleId").val("-1")
}
//保存角色
function saveBean(){
	var roleId = $("#roleId").val();
	var roleName = $("#roleName").val();
	if(roleName.length==0){
		alert("角色名称不能为空！");$("#roleName").focus();return;
	}
	var url = urlPath + "saveRoleBean?" + ajaxFlag +"&tmp="+ Math.round(Math.random() * 10000);
	$.ajax({
		url:url,
		type:"post",
		data:"roleId="+roleId+"&roleName="+roleName,
		dataType:"json",
		beforeSend:function(){
			showWaitDiv(true);
		},
		success:function(data){
			showWaitDiv(false);
			if(ajaxResultValidate(data)){
				showCustomDiv(false,'addDiv',1,false);
				popUp("保存成功！",1000);
				$("#"+tableName).flexReload();
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
            alert('<%=AjaxResponse.getAjaxErrorInfo()%>');
			showWaitDiv(false);
        }
	});
}
//修改角色
function editRole(roleId){
	var url = urlPath + "getRoleBean?" + ajaxFlag +"&tmp="+ Math.round(Math.random() * 10000);
	$.ajax({
		url:url,
		type:"post",
		data:"roleId="+roleId,
		dataType:"json",
		beforeSend:function(){
			showWaitDiv(true);
		},
		success:function(data){
			showWaitDiv(false);
			if(ajaxResultValidate(data)){
				$("#roleId").val(data.id);
				$("#roleName").val(data.roleName);
				showCustomDiv(true,'addDiv',1,false);
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
            alert('<%=AjaxResponse.getAjaxErrorInfo()%>');
			showWaitDiv(false);
        }
	});
}
//删除角色
function deleteRole(roleId){
	if(!confirm("删除角色时相关信息也会删除且不可恢复,是否确认删除角色信息？")){
		return;
	}
	var url = urlPath + "deleteRole?" + ajaxFlag +"&tmp="+ Math.round(Math.random() * 10000);
	$.ajax({
		url:url,
		type:"post",
		data:"roleId="+roleId,
		dataType:"json",
		beforeSend:function(){
			showWaitDiv(true);
		},
		success:function(data){
			showWaitDiv(false);
			if(ajaxResultValidate(data)){
				$("#"+tableName).flexReload();
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
            alert('<%=AjaxResponse.getAjaxErrorInfo()%>');
			showWaitDiv(false);
        }
	});
}

var privilegeTree;//权限树
var treeSetting;

treeSetting = {
	    async: true,
	    asyncUrl: urlPath + "queryMenuTree?"+ajaxFlag, 
	    asyncParam: ["id"],
	    checkable: true
};

/*权限设置*/
function modifyPrivilegeRole(id, roleName) {
	$("#privilegeDivTitle").html("权限设置 "+roleName);
	treeSetting.asyncParamOther = ["oprBean.id", id];
    privilegeTree = $("#privilegeTreeDiv").zTree(treeSetting, []);
    showCustomDiv(true, "privilegeViewDiv", 0, false);
}

/*保存权限*/
function savePrivilege(targetButton){
    var tmp = privilegeTree.getCheckedNodes();
    var menuIds = "";
    var clientMenus = [];
    $.each(tmp, function(i, node){
        menuIds += node.id + ",";
    });
    menuIds = menuIds.replace(/,$/, "");
    var saveClientPrivilegeUrl = urlPath + "savePrivilege?" + ajaxFlag;
    saveClientPrivilegeUrl += "&menuIds=" + menuIds;
    saveClientPrivilegeUrl += "&oprBean.id=" + treeSetting.asyncParamOther[1];
    $.ajax({
        url: saveClientPrivilegeUrl,
        type: "GET",
        dataType: "json",
		beforeSend: function(){
        	showCustomDiv(false, "privilegeViewDiv", 1, false);
        	showWaitDiv(true);
        },
        success: function(data){
        	showWaitDiv(false);
        	if(ajaxResultValidate(data)){
				popUp("设置成功！",1000);
        	}
        },
		error: function(XMLHttpRequest, textStatus, errorThrown) {
            alert('<%=AjaxResponse.getAjaxErrorInfo()%>');
			showWaitDiv(false);
        }
    });
}
