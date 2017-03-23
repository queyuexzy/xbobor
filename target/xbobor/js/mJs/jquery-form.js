/**
 * 重置表单
 * 	把js对象放到form表单中
 * @param {Object}
 *            o
 * @memberOf {TypeName}
 * @return {TypeName}
 * @author jt.tao QQ:1453566283
 */
$.fn.resetObjectForm = function(o) {
	if (o == null) {
		var form = document.forms[$(this).attr('name')];
		if (form !== undefined) {
			form.reset();
			return true;
		} else {
			alert("重置表单名为" + $(this).attr('name') + "失败.");
			return false;
		}
	}
	var $this = this;
	var a = $this.serializeArray();
	$.each(a,
			function(i, n) {
				var $t = $('(input|textarea|select)[name=\'' + this.name
						+ '\']', $this);
				if ($t.attr("type") == 'radio') {
					$.each($t, function(i, n) {
						if ($(this).val() == o[this.name]) {
							$(this).attr("checked", true);
						} else {
							$(this).attr("checked", false);
						}
					});
				} else {
					if (o[this.name]) {
						$t.val(o[this.name].valueOf());
					} else {
						$t.val("");
					}
					
					if(o[this.name]===0){
						$t.val(0);
					}
				}
			});
};
// 数据
// var o={id:'1000',name:'JTtao',sex:'男',age:21};
// 调用
// $("#dome-form").resetObjectForm(o);

/**
 * 
 * 序列话一个表单,并给该表单中每一个字段添加别名
 *   把form表单的数据放到js对象中
 * @author jt.tao QQ:1453566283
 * @param {Object}
 *            n 表单对象别名
 * @memberOf {TypeName}
 * @return {TypeName}
 */
$.fn.serializeObjectForm = function(n) {
	if (!n) {
		alert('序列化表单请给别名!');
		return null
	}
	;// 在此临时简单处理n，还有待你们完善呀...
	var o = {};
	var a = this.serializeArray();
	$.each(a, function() {
		if (o[n + "." + this.name]) {
			if (!o[n + "." + this.name].push) {
				o[n + "." + this.name] = [ o[n + "." + this.name] ];
			}
			if (this.value != "") {
				o[n + "." + this.name].push(this.value);
			}
		} else {
			if (this.value != "") {
				o[n + "." + this.name] = this.value;
			}
		}
	});
	return o;
};
// var userdata=$("#dome-form").serializeObjectForm('user');
// userdata={'user.id':1000,'user.name':'JTtao','user.sex':'男','user.age':20};
