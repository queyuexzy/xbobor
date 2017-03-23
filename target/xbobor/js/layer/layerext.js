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

})
		(
				"L",
				this,
				function() {

					var L = {}, loadingindex;
					// layer.use('L/css/base.css');
					// layer.use('L/css/fream.css');
					layer.config({
						extend : [ 'skin/theme1/style.css' ]
					// 加载您的扩展样式
					// skin: 'layer-ext-theme1'
					});
					L.menu = function(option) {
						option = option || {};
						var tar = $(option.target), con = $(option.content), x = option.x || 0, y = option.y || 0, h = tar
								.height(), w = tar.width(), dom = tar[0]
								.getBoundingClientRect(),

						nh = 0, nw = 0;
						switch (option.position) {
						case 0:
							nh = nh + h;
							nw = nw - con.width();
							break;
						case 1:
							nh = nh - con.height();
							nw = nw - con.width();
							break;
						case 2:
							nh = nh - con.height();
							// nw = nw + con.width();
							break;
						case 3:
							nh = nh - con.height();
							nw = nw - con.width() + w;
							break;
						case 4:
							nh = nh + h;
							break;
						case 5:
							nw = nw - con.width();
							break;
						default:
							nh = nh + h;
							// nw = nw - con.width();
							break;
						}
						var i = layer.open({
							title : false,
							fix : true,
							type : 1,
							closeBtn : false,
							shift : option.shift ? option.shift : -1, // -1表示不启用动画可选值0-6
							shadeClose : true, // 是否点击空白关闭
							content : con, // dom
							offset : [ dom.top + nh + y, dom.left + nw + x ]
						});
						return i;
					};
					L.load = function(i) {
						this.itype = i || 0;
						this.loadindex = null;
						this.loadNum = 0;
					};
					L.load.prototype = {
						constructor : L.load,
						loading : function() {
							this.loadNum++;
							if (this.loadNum > 1) {
								return;
							}
							this.loadindex = layer.load(this.itype, {
								skin : 'layer-ext-theme1',
								scrollbar : false,
								shade : [ 0.3, '#000' ]
							});
						},
						loadingOff : function() {
							this.loadNum--;
							if (this.loadNum === 0) {
								layer.close(this.loadindex);
							}
						}
					};
					L.loading = function(i) {
						var index = layer.load(i ? i : 0, {
							skin : 'layer-ext-theme1',
							scrollbar : false,
							shade : [ 0.3, '#000' ]
						//
						});
						loadingindex = index;
						this.loadingdex = loadingindex;
						return index;
					};
					L.loadingOff = function() {
						if (loadingindex) {
							layer.close(loadingindex);
						} else {
							layer.closeAll();
						}
					};
					L.closeAll = function() {
						layer.closeAll();
					};
					L.pop = function(option) {
						option = option || {};
						layer
								.open({
									type : 1,
									area : option.area ? option.area : [
											'auto', 'auto' ],
									shadeClose : option.shade ? true : false,
									closeBtn : option.btn || false,
									title : option.title ? [
											option.title,
											'background-color:#6fb3e0; color: #333; font-size: 14px;line-height: 35px;font-weight:normal' ]
											: false,
									content : $(option.content), // 这里content是一个DOM
									success : function(layero, index) {
										return function(i) {
											$(layero).on('click', '.fr',
													function(event) {
														event.preventDefault();
														layer.close(i);
													});
										}(index);
									}
								});
					};
					L.ESC = function() {
						$(document).on('keydown', function(e) {
							var ev = window.event || e;
							var code = ev.keyCode || ev.which;
							if (code === 27) {
								layer.closeAll();
								if (ev.preventDefault) {
									ev.preventDefault();
								} else {
									ev.keyCode = 0;
									ev.returnValue = false;
								}
							}
						});
					};
					L.msg = layer.msg;
					L.tpl = {};
					L.tpl.success = '<div style=" display:none; left: 0;position: fixed;top: 0;z-index:19910324;" id="msg-success" class="w350 popTip borRad">'
							+ '<div class="popPor">'
							+ '<div class="popTitle">'
							+ ' <h2 class="titleH2 fl">提示框</h2>'
							+ '<a href="javascript:;" class="bthPop bthClosed fr"></a></div>'
							+ '<div class="popCon p15I clearb"> <i class="siteIco icoTipSuccess fl mgr10"></i>'
							+ ' <div class="popTipTex"><strong class="c_468847">成功</strong>'
							+ '<p class="c_999">$content</p>'
							+ '</div>'
							+ '</div>' + '</div></div>';
					L.tpl.warn = '<div style=" display:none; left: 0;position: fixed;top: 0;z-index:19910324;" id="msg-success" class="w350 popTip borRad">'
							+ '<div class="popPor">'
							+ '<div class="popTitle">'
							+ ' <h2 class="titleH2 fl">提示框</h2>'
							+ '<a href="javascript:;" class="bthPop bthClosed fr"></a></div>'
							+ '<div class="popCon p15I clearb"> <i class="siteIco icoTipWarning fl mgr10"></i>'
							+ ' <div class="popTipTex"><strong class="c_c09853">提醒警告</strong>'
							+ '<p class="c_999">$content</p>'
							+ '</div>'
							+ '</div>' + '</div></div>';
					L.tpl.confirm = '<div id="msg-confirm" class="w350 popTip borRad">'
							+ '<div class="popPor">'
							+ '<div class="popTitle">'
							+ ' <h2 class="titleH2 fl">提示框</h2>'
							+ '<a href="javascript:;" class="bthPop bthClosed fr"></a></div>'
							+ '<div class="popCon p15I clearb"> <i class="siteIco icoTipWarning fl mgr10"></i>'
							+ ' <div class="popTipTex"><strong class="c_c09853">提醒</strong>'
							+ '<p class="c_999">$content</p>'
							+ '</div>'
							+ '</div>' + '</div></div>';
					L.tpl.error = '<div id="msg-error" class="w350 popTip borRad">'
							+ '<div class="popPor">'
							+ '<div class="popTitle">'
							+ ' <h2 class="titleH2 fl">提示框</h2>'
							+ '<a href="javascript:;" class="bthPop bthClosed fr"></a></div>'
							+ '<div class="popCon p15I clearb"> <i class="siteIco icoTipError fl mgr10"></i>'
							+ ' <div class="popTipTex"><strong class="c_b94a48">操作失败</strong>'
							+ '<p class="c_999">$content</p>'
							+ '</div>'
							+ '</div>' + '</div></div>';
					L.msg.success = function(option) {
						$('body').append(
								L.tpl.success.replace('$content',
										option.content || '无消息'));
						var dom = $('#msg-success'), w = dom.width(), h = dom
								.height(), fw = _wsize().width, fh = _wsize().height;
						switch (option.position || '') {
						case 'center':
							dom.css({
								top : (fh - h) * 0.5,
								left : (fw - w) * 0.5
							});
							break;
						case 'top':
							dom.css({
								top : 0,
								left : (fw - w) * 0.5
							});
							break;
						default:
							dom.css({
								top : (fh - h) * 0.5,
								left : (fw - w) * 0.5
							});
							break;
						}
						dom.fadeIn();
						var ti;
						dom.on('click', '.fr', function(event) {
							event.preventDefault();
							if (ti) {
								clearTimeout(ti);
							}
							dom.fadeOut('slow', function() {
								dom.remove();
							});
						});
						ti = setTimeout(function() {
							dom.fadeOut('slow', function() {
								dom.remove();
							});
						}, option.time || 2000);
					};
					L.msg.warn = function(option) {
						$('body').append(
								L.tpl.warn.replace('$content',
										option.content || '无消息'));
						var dom = $('#msg-success'), w = dom.width(), h = dom
						.height(), fw = _wsize().width, fh = _wsize().height;
						switch (option.position || '') {
						case 'center':
							dom.css({
								top : (fh - h) * 0.5,
								left : (fw - w) * 0.5
							});
							break;
						case 'top':
							dom.css({
								top : 0,
								left : (fw - w) * 0.5
							});
							break;
						default:
							dom.css({
								top : (fh - h) * 0.5,
								left : (fw - w) * 0.5
							});
						break;
						}
						dom.fadeIn();
						var ti;
						dom.on('click', '.fr', function(event) {
							event.preventDefault();
							if (ti) {
								clearTimeout(ti);
							}
							dom.fadeOut('slow', function() {
								dom.remove();
							});
						});
						ti = setTimeout(function() {
							dom.fadeOut('slow', function() {
								dom.remove();
							});
						}, option.time || 2000);
					};

					function _wsize() {
						var e = window, a = 'inner';

						if (!('innerWidth' in window)) {
							a = 'client';
							e = document.documentElement || document.body;
						}

						return {
							width : e[a + 'Width'],
							height : e[a + 'Height']
						};
					}

					L.msg.confirm = function(option) {
						_msg(option, '#msg-confirm', L.tpl.confirm);
					};

					function _msg(option, id, template) {
						option = option || {};
						layer.open({
							type : 1,
							title : false,
							closeBtn : false,
							btn : [ option.btnNameYes || '确认', option.btnNameCancel || '取消' ],
							skin : 'layer-ext-theme1',
							content : template.replace('$content',
									option.content || '无消息'),
							yes : function(index) {
								// do something
								if (option.yes) {
									option.yes();
								}

								layer.close(index);
							},
							cancel : function(index) {
								// do something
								if (option.cancel) {
									option.cancel();
								}
								layer.close(index);
							},
							success : function(layero, index) {
								return function(i) {
									$(id).on('click', '.fr', function(event) {
										event.preventDefault();
										if (option.cancel) {
											option.cancel();
										}
										layer.close(i);
									});
								}(index);

							}
						});
					}
					L.msg.error = function(message, callback) {
						layer.open({
							type : 1,
							title : false,
							closeBtn : false,
							btn : [ '确认' ],
							skin : 'layer-ext-theme1',
							content : L.tpl.error.replace('$content', message
									|| '无消息'),
							yes : function(index) {
								layer.close(index);
								if (callback) {
									callback();
								}
							},
							success : function(layero, index) {
								return function(i) {
									$('#msg-error').on('click', '.fr',
											function(event) {
												event.preventDefault();
												layer.close(i);
												if (callback) {
													callback();
												}
											});
								}(index);

							}
						});
					};
					L.alert = function(message) {
						layer
								.alert(
										message || '无消息',
										{
											title : [
													'提示框',
													'background-color:#6fb3e0; color: #333; font-size: 14px;line-height: 35px;font-weight:normal' ],
											closeBtn : false,
											icon : 2
										});
					};
					L.confirm = layer.confirm;
					L.tips = function(message, target, time, color) {
						layer.tips(message || '无消息', target, {
							time : time || 2000,
							success : function(layero) {
								$(layero).find('.layui-layer-content').css({
									'background-color' : color || 'black'
								});
								$(layero).find('.layui-layer-content i').css({
									'border-bottom-color' : color || 'black'
								});
							}
						});
					};
					L.tpl.progressbar = '<div class="w600 popTip"> <div class="popPor"><div class="popTitle"><h2 class="titleH2 fl">发布</h2></div>'
							+ '<div class="popCon p15I clearb tc">'
							+ '<span class="processItem processA proHover">等待发布</span><span class="processLine"><span style="width:100%;" class="processLineCon"></span></span><span class="processItem processB proHover">发布中</span><span class="processLine"><span style="width:100%;" class="processLineCon"></span></span><span class="processItem processC porErro">发布结束</span>'
							+ '</div>'
							+ '<div class="popBot p10 tr">'
							+ '<p class="c_b94a48">$content</p>'
							+ '</div>'
							+ '</div>' + '</div>';
					L.progressBar = function(option) {
						option = option || {};
						var t = this;
						t.obj = {};
						t.nowprogress = 0;
						t.index = layer
								.open({
									type : 1,
									title : false,
									closeBtn : false,
									area : [ 'auto', 'auto' ],
									content : L.tpl.progressbar
											.replace(
													'$content',
													option.content
															|| '提示：当处在发布状态时，将禁止任何操作，待发布完成后恢复！'),
									success : function(layero, index) {
										t.obj = layero;
										// _clear(layero);
										_progress(option.type, layero, null,
												t.nowprogress);
									}
								});
					};
					L.progressBar.prototype = {
						constructor : L.progressBar,
						progress : function(itype, callback) {
							if (itype !== "undefined") {
								if (this.nowprogress != itype) {
									var np = this.nowprogress;
									this.nowprogress = itype;
									_progress(itype, this.obj, callback, np);
								}
							}
						},
						message : function(itext) {
							this.obj.find('p.c_b94a48').html(itext || '');
						},
						close : function(callback) {
							layer.close(this.index);
							if (callback) {
								callback();
							}
						},
						lazyclose : function(time, callback) {
							var t = this;
							setTimeout(function() {
								layer.close(t.index);
								if (callback) {
									callback();
								}
							}, time ? time : 1000);
						}
					};

					function _clear(obj) {
						obj.find('.processItem').removeClass('proHover')
								.removeClass('porErro');
						obj.find('.processLineCon').css({
							width : '0%'
						});
					}

					function _progress(itype, obj, callback, np) {
						switch (itype) {
						case 0:
							_clear(obj);
							obj.find('.processItem').eq(0).addClass('proHover');
							if (typeof (callback) == "function") {
								callback();
							}
							break;
						case 1:
							_clear(obj);
							obj.find('.processItem').eq(0).addClass('proHover');
							var p = obj.find('.processLineCon').eq(0);
							p.animate({
								width : '100%'
							}, 2000, function() {
								obj.find('.processItem').eq(1).addClass(
										'proHover');
								if (typeof (callback) == "function") {
									callback();
								}
							});
							break;
						case 2:
							if (np != null && typeof (np) != "undefined"
									&& np == 0) {
								obj.find('.processItem').eq(0).addClass(
										'proHover');
								var p = obj.find('.processLineCon').eq(0);
								p
										.animate(
												{
													width : '100%'
												},
												2000,
												function() {
													obj.find('.processItem')
															.eq(1).addClass(
																	'proHover');
													obj
															.find(
																	'.processLineCon')
															.eq(1)
															.animate(
																	{
																		width : '100%'
																	},
																	2000,
																	function() {
																		obj
																				.find(
																						'.processItem')
																				.eq(
																						2)
																				.addClass(
																						'proHover')
																				.html(
																						'发布结束');
																		if (typeof (callback) == "function") {
																			callback();
																		}
																	});
												});
							} else {
								obj
										.find('.processLineCon')
										.eq(1)
										.animate(
												{
													width : '100%'
												},
												2000,
												function() {
													obj.find('.processItem')
															.eq(2).addClass(
																	'proHover')
															.html('发布结束');
													if (typeof (callback) == "function") {
														callback();
													}
												});
							}
							break;
						case 3:
							if (np != null && typeof (np) != "undefined"
									&& np == 0) {
								obj.find('.processItem').eq(0).addClass(
										'proHover');
								var p = obj.find('.processLineCon').eq(0);
								p
										.animate(
												{
													width : '100%'
												},
												2000,
												function() {
													obj.find('.processItem')
															.eq(1).addClass(
																	'proHover');
													obj
															.find(
																	'.processLineCon')
															.eq(1)
															.animate(
																	{
																		width : '100%'
																	},
																	2000,
																	function() {
																		obj
																				.find(
																						'.processItem')
																				.eq(
																						2)
																				.addClass(
																						'porErro')
																				.html(
																						'发布失败');
																		if (typeof (callback) == "function") {
																			callback();
																		}
																	});
												});
							} else {
								obj
										.find('.processLineCon')
										.eq(1)
										.animate(
												{
													width : '100%'
												},
												2000,
												function() {
													obj.find('.processItem')
															.eq(2).addClass(
																	'porErro')
															.html('发布失败');
													if (typeof (callback) == "function") {
														callback();
													}
												});
							}
							break;
						default:
							_clear(obj);
							break;
						}
					}
					return L;
				});