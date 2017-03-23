//保存控件
function saveElement(data) {
	var top16 = document.getElementById('top16');
	var selectId16 = data.selectIds16;
	var selectId8 = data.selectIds8;
	var selectId4 = data.selectIds4;
	var selectId2 = data.selectIds2;
	var selectThirdId = data.thirdId;
	var selectFirstId = data.firstId;
	var selectTitle = data.selectTitles;
	var team = data.teams;
	var myTop8 = new Array(16);
	var htmlContant = "";
	for ( var i = 0; i < selectId16.length; i++) {
		htmlContant = htmlContant + " " + selectTitle[i] + " : "
				+ "<select name=" + selectId16[i] + " id=" + selectId16[i]
				+ " style='width:150px'>";
		for ( var j = 0; j < 32; j++) {
			if (selectTitle[i].substr(0, 1) == team[j].group) {
				htmlContant = htmlContant + "<option value='" + team[j].id
						+ "'>" + team[j].name + "</option>";
			}
		}
		htmlContant = htmlContant
				+ "</select><button class='inputB' onclick=saveData('"
				+ selectId16[i] + "');>确定</button><br>";
	}
	top16.innerHTML = htmlContant;
	htmlContant = "";
	for ( var i = 0; i < selectId8.length; i++) {
		htmlContant = htmlContant + "<select name=" + selectId8[i] + " id="
				+ selectId8[i] + " style='width:150px'>";
		myTop8[0] = selectId16[i];

		for ( var j = 0; j < 32; j++) {
			htmlContant = htmlContant + "<option value='" + team[j].id + "'>"
					+ team[j].name + "</option>";
		}
		htmlContant = htmlContant
				+ "</select><button class='inputB' onclick=saveData('"
				+ selectId8[i] + "');>确定</button><br>";
	}
	top8.innerHTML = htmlContant;
	htmlContant = "";
	for ( var i = 0; i < selectId4.length; i++) {
		htmlContant = htmlContant + "<select name=" + selectId4[i] + " id="
				+ selectId4[i] + " style='width:150px'>";
		for ( var j = 0; j < 32; j++) {
			htmlContant = htmlContant + "<option value='" + team[j].id + "'>"
					+ team[j].name + "</option>";
		}
		htmlContant = htmlContant
				+ "</select><button class='inputB' onclick=saveData('"
				+ selectId4[i] + "');>确定</button><br>";
	}
	top4.innerHTML = htmlContant;
	htmlContant = "";
	for ( var i = 0; i < selectId2.length; i++) {
		htmlContant = htmlContant + "<select name=" + selectId2[i] + " id="
				+ selectId2[i] + " style='width:150px'>";
		for ( var j = 0; j < 32; j++) {
			htmlContant = htmlContant + "<option value='" + team[j].id + "'>"
					+ team[j].name + "</option>";
		}
		htmlContant = htmlContant
				+ "</select><button class='inputB' onclick=saveData('"
				+ selectId2[i] + "');>确定</button><br>";
	}
	top2.innerHTML = htmlContant;
	htmlContant = "";
	htmlContant = htmlContant + " 冠军 : " + "<select name=" + selectFirstId
			+ " id=" + selectFirstId + " style='width:150px'>";
	for ( var j = 0; j < 32; j++) {
		htmlContant = htmlContant + "<option value='" + team[j].id + "'>"
				+ team[j].name + "</option>";
	}
	htmlContant = htmlContant
			+ "</select><button class='inputB' onclick=saveData('"
			+ selectFirstId + "');>确定</button><br>";
	htmlContant = htmlContant + " 季军 : " + "<select name=" + selectThirdId
			+ " id=" + selectThirdId + " style='width:150px'>";
	for ( var j = 0; j < 32; j++) {
		htmlContant = htmlContant + "<option value='" + team[j].id + "'>"
				+ team[j].name + "</option>";
	}
	htmlContant = htmlContant
			+ "</select><button class='inputB' onclick=saveData('"
			+ selectThirdId + "');>确定</button><br>";
	top1.innerHTML = htmlContant;
	showCustomDiv(false, "apdiv", "waitIframe", 0);
}
function getSelectIds() {
	var url = "<%=path%>/worldCup/worldCup_toShow";
	$.ajax({
		url : url,
		dataType : "json",
		type : "POST",
		send : showCustomDiv(true, "apdiv", "waitIframe", 0),
		success : function(data) {
			saveElement(data);
		}
	});
	// var obj = document.getElementById(selectId[i]).selectedIndex; //selectid
	// var index = obj.selectedIndex; // 选中索引
	// var value =
	// document.getElementById(selectId[i]).options[document.getElementById(selectId[i]).selectedIndex].value;
	// // 选中值
	// alert(value);
	// var text = obj.options[index].text; // 选中文本
}