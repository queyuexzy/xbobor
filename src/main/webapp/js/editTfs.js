/**
 * Edit tfs images, zoom, crop.
 */
document.write("<script type='text/javascript' src='" + path + "/js/jcrop/jquery.Jcrop.js'><\/script>");
document.write("<link rel='stylesheet' type='text/css' href='" + path + "/js/jcrop/jquery.Jcrop.css'><\/style>");

var editPic = function(module){
	var mainDiv = $("<div class='divPopup' style='width: 1000px;'></div>");
	var mainTable = $("<table class='tableC' cellspacing='0' cellpadding='0' style='width: 100%;'></table>");
	mainDiv.append(mainTable);

	$(document).ready(function() {
		$(document.body).append(mainDiv);
	});

	module.open = function(setting) {

	};

	return module;
}(window.editPic || {});