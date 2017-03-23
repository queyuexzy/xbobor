var privilegeTree;//权限树
var treeSetting;

treeSetting = {
	    async: true,
	    asyncUrl: urlPath + "queryMenuTree?"+ajaxFlag, 
	    asyncParam: ["id"],
	    checkable: true
};

/*权限设置*/
function modifyPrivilegeUser(id,userName,realName,deptId){
	$("#privilegeDivTitle").html("权限设置 "+realName);
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
        		alert("设置成功!");
        	}
        }
    });
}