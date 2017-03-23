var url = path + "/login_getMenuTree?" + ajaxFlag + Math.round(Math.random() * 10000);
var treeId = "menuTree";
var zTree1;//树
var setting;
var zNodes =[];
var oldSort = 0;//记录旧排序
var selectNodeId;
var fontCss = {
	"font-size":"13px"
};
setting = {
	checkable: false,
	showLine :true,
	async: true,
	asyncUrl: url,  
	fontCss:fontCss,
	asyncParam: ["id"],  //获取节点数据时，必须的数据名称，例如：id、name
	callback:{
		asyncSuccess: zTreeOnAsyncSuccess,
		click:clickNode
	}
};

/*预加载*/
$(function(){
	zTree1 = $("#"+treeId).zTree(setting, zNodes);
});

//加载成功
function zTreeOnAsyncSuccess(){
	if(selectNodeId){//当前选中的节点
		var node = zTree1.getNodeByParam("id", selectNodeId);
		if (node) {
			zTree1.selectNode(node);
			selectNodeId = null;
		}
	}
}
//点击树节点
function clickNode(event, treeId, treeNode,pidList){
	var id = Number(treeNode.id);
	var name = treeNode.name;
	if(!treeNode.isParent){//如果不是文件夹,就跳转
		if(id==-1){
			parent.frames["contentFrame"].location = "/welcome/loginedWelcome.html";
		}else{
			var path = treeNode.clickPath;
			parent.frames["contentFrame"].location = path;
		}
	}else if(id==-1){//如果点击的是菜单树,就跳转至首页
		parent.frames["contentFrame"].location = "/welcome/loginedWelcome.html";
	}
}
//刷新树(展开父节点)
function refreshTree(pid){
	var pnode = zTree1.getNodeByParam("id",pid);
	pnode.isParent = true;
	zTree1.reAsyncChildNodes(pnode, "refresh");
}