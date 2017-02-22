


//增加图书信息的panel
var formpanel = new Ext.form.Panel({
				
						frame:true,
						bodyStyle : "padding:10 5 5 45",
						defaults:{
							allowBlank:false,//不能为空
							msgTarget:'side',
							labelAlign:'left',
							autoFitErrors: false//输入错误的时候不收缩输入框
						},
						items:[{
							fieldLabel:"图书类别",
							xtype:"combo",
							labelWidth:80,
							emptyText :"请选择",
							store : new Ext.data.SimpleStore({  
                                fields : ['value', 'text'],  
                                data : [['V1', '政治法律'],  
                                        ['V2', '哲学宗教'],
                                        ['V3', '经济'],
                                        ['V4', '文学'],
                                        ['V5', '历史'],
                                        ['V6', '小说']
                                       ]   
                            }),  
		                    editable : false,  
		                    triggerAction:'all',  
		                    valueField : 'text',  
		                    displayField : 'text' ,
		                    name:"book_type"
						},{
							fieldLabel:"图书名称",
							xtype:"textfield",
							labelWidth:80,
							name:"book_name"
						},{
							fieldLabel:"图书isbn",
							xtype:"textfield",
							labelWidth:80,
							name:"book_isbn"
						},{
							fieldLabel:"图书作者",
							xtype:"textfield",
							labelWidth:80,
							name:"book_author"
						},{
							fieldLabel:"出版社",
							xtype:"textfield",
							labelWidth:80,
							name:"book_press"
						},{
							fieldLabel:"出版日期",
							xtype:"datefield",
							format:"Y年m月d日",
							labelWidth:80,
							name:"book_press_date"
						},{
							fieldLabel:"馆藏数量",
							xtype:"textfield",
							labelWidth:80,
							name:"book_count"
						},{
							fieldLabel:"图书价格",
							xtype:"textfield",
							labelWidth:80,
							name:"book_price"
						},{
							fieldLabel:"图书位置",
							xtype:"textfield",
							labelWidth:80,
							name:"book_location"
						},{
							fieldLabel:"备注信息",
							xtype:"htmleditor",
							labelWidth:80,
							width:300,
							height:200,
							fontFamilies :["宋体","仿宋","黑体","微软雅黑","Courier New","华文行楷"],
							name:"book_note"
						}],
						buttonAlign:"center",
						buttons:[{
							text:"确认添加",
							handler:function(buttonObj){
								var basic = buttonObj.ownerCt.ownerCt.getForm();
								
								basic.submit({
									clientValidation: true,
									url:"bookInfo!add",
									success:function(){
										Ext.Msg.alert("提示","增加图书成功！");
										//basic.setValues("");
										buttonObj.ownerCt.ownerCt.ownerCt.hide();
										
									},
									failure:function(){
										Ext.Msg.alert("提示","增加图书失败！");
										//basic.setValues("");
									}
								});
							}
						},{
							text:"取消",
							handler:function(buttonObj){
								buttonObj.ownerCt.ownerCt.ownerCt.hide();
							}
						}]
					});
					
var book_win = new Ext.window.Window({
	title:"添加图书信息",
	width:380,
	closeAction:"hide",
	modal:true,
	resizable:false,
	height:450,
	layout:"fit",
	items:[formpanel]
});


//为结点增加右键菜单
var menu = new Ext.menu.Menu({

	items : [ {
		text : "借阅操作",
		iconCls : "book_next",
		handler:function(){
			//在未验证卡号是否正确前，让确认按钮无效
			//Ext.getCmp("book-submit-button").setDisabled(true);
			//得到借阅登记的win
			var bgw = Ext.getCmp("borrow-register-win");
			
			//得到借阅表单
			var bgp = bgw.getComponent(0);
			
			var basic = bgp.getForm();
			//得到表格对象
			var grid = Ext.getCmp("book-info-panel");
			
			//得到选中的行
			var select = grid.getSelectionModel().getSelection();
			
			//得到相应的数据
			var book_type = select[0].data.book_type;//图书类型
			var book_name = select[0].data.book_name;//图书名称
			var book_isbn = select[0].data.book_isbn;//图书isbn
			
			//将数据设置到表单中
			basic.findField("book_type").setValue(book_type);
			basic.findField("book_name").setValue(book_name);
			basic.findField("book_isbn").setValue(book_isbn);
			bgw.show();

		}
		
	}, {
		text : "归还操作",
		iconCls : "book_previous",
		handler:function(){
			//得到还书登记的win
			var bgw = Ext.getCmp("return-register-win");
			//得到还书表单
			var bgp = bgw.getComponent(0);
			
			var basic = bgp.getForm();
			//得到表格对象
			var grid = Ext.getCmp("book-info-panel");
			
			//得到选中的行
			var select = grid.getSelectionModel().getSelection();
			
			//得到相应的数据
			var book_type = select[0].data.book_type;//图书类型
			var book_name = select[0].data.book_name;//图书类型
			var book_isbn = select[0].data.book_isbn;//图书类型
			
			//将数据设置到表单中
			basic.findField("book_type").setValue(book_type);
			basic.findField("book_name").setValue(book_name);
			basic.findField("book_isbn").setValue(book_isbn);
			basic.findField("return_date").setValue(new Date());
			
			bgw.show();
			
			
		}
	},{
		text : "查看图书详细信息",
		iconCls : "book_open"
	} ]
});


//图书控制
Ext.define("AM.controller.BookController",{
	
	extend:'Ext.app.Controller',
	init:function(){
		//alert(Ext.getCmp("daily-panel"));
		this.control({
			"bookPanel":{
				itemdblclick:function(tree,record,item,index,e,eOpts){
					//得到tappanel
					var tabpanel = Ext.getCmp("content-panel");
					//遮罩
					var myMask = new Ext.LoadMask(tabpanel, {
						msg:"正在加载数据...",
						frame:true	
					});
					//遮罩
					myMask.show();
					setTimeout(function(){
						myMask.hide();//隐藏遮罩
						//设置标题
						tabpanel.setTitle(record.get("text"));
						//设置选中的面板为活动窗口
						tabpanel.setActiveTab(record.get("id")+"-panel");
					},1000);
					
				}
			},
			//为表格添加右键菜单
			"bookInfoPanel":{
				
				
				itemcontextmenu:function(view, record, htmlitem, rowIndex, e){
					
					rowNum = rowIndex;
					e.stopEvent();// 停止原有的右键事件
					menu.showAt(e.getXY());
				},
			
				itemmouseenter:function(){
					menu.hide();
				}
			},
			//删除================================
			"bookInfoPanel button[id=book-del]":{
				click:function(buttonObj){
					//得到表格对象
					var grid = buttonObj.ownerCt.ownerCt;
					
					//得到选中的数据
					var data = grid.getSelectionModel().getSelection();
					
					if(data.length==0){
						Ext.Msg.show({
							title : '警告消息',
							msg : '没有选中任何数据！',
							icon : Ext.Msg.WARNING,
							buttons : Ext.Msg.OK
						});
					}else{
						Ext.Msg.confirm("系统提示","确认删除？",function(btn){
							if(btn=="yes"){
								//1.先得到id的数组，
		        				var st = grid.getStore();//得到数据集
		        				var ids = [];
		        				Ext.Array.each(data,function(record){
		        					ids.push(record.get('book_id'));
		        					//2后台操作删除
		            				
		    	        			Ext.Ajax.request({
		    	    					url:'bookInfo!delete',
		    	    					params:{ids:ids.join(",")},
		    	    					method:'POST',
		    	    					timeout:2000,
		    	    					success:function(response,opts){
		    	    						//前台删除
		    	    						Ext.Array.each(data,function(record){
		    	    							st.remove(record);
		    	    						});
		    	    						st.load();
		    	    						Ext.Msg.alert("系统提示","<img src='images/ok.png'>删除数据成功！");
		    	    					},
		    	    					failure:function(){
		    	    						Ext.Msg.show({
												title : '错误提示',
												msg : '删除数据失败!',
												icon : Ext.Msg.ERROR,
												buttons : Ext.Msg.OK
											});
		    	    					}
		    	    				});	
			        			});
							}
						});
						
	        			
					}
				}
			},

			//刷新操作=================================
			"bookInfoPanel button[id=book-update]":{
				click:function(buttonObj){
					//得到表格对象
					var grid = buttonObj.ownerCt.ownerCt;
					//得到数据集
					var store = grid.getStore();
					
					
					store.load();
				}
			},
			//添加书籍信息==========================================
			"bookInfoPanel button[id=book-add]":{
				click:function(buttonObj){
					
					if(book_win){
						book_win.show();
					}
				}
			},
			"bookInfoPanel button[id=book-data-sta]":{
				click:function(btn){
					var win = new Ext.Window({
						title:"数据统计",
						width:700,
						height:592,
						padding:"0 10 10 10",
						modal:true,
						bodyStyle:"background:#fff",
						resizable:false,
						//frame:true,
						html:"<div id='chart' ></div><div id='chart1'></div>"
					});
					 win.show();
					var myChart = new FusionCharts( "resource/Column3D.swf", 
                   	"myChartId", "675", "515", "0","1");
			      	myChart.setJSONUrl("data");
			      	myChart.render("chart");
			      	
			      	
			      	
			      	var myExportComponent = new FusionChartsExportObject("fcExporter1","resource/FCExporter.swf");
					myExportComponent.componentAttributes.btnColor = 'EAF4FD';
					myExportComponent.componentAttributes.btnBorderColor='0372AB';
					myExportComponent.componentAttributes.btnFontFace='Verdana';
					myExportComponent.componentAttributes.btnFontColor='0372AB';
					myExportComponent.componentAttributes.btnFontSize='12';
					myExportComponent.componentAttributes.btnsavetitle='另存为';
					myExportComponent.componentAttributes.btndisabledtitle='右键生成图片';
					myExportComponent.Render("chart1");


			     
				}
			},
			//保存修改==========================================
			"bookInfoPanel button[id=book-save]":{
				click:function(buttonObj){
					//得到表格对象
					var grid = buttonObj.ownerCt.ownerCt;
					//得到数据集
					var store = grid.getStore();
					
					//得到修改过的记录
					var records = store.getUpdatedRecords();
					var data = [];
					Ext.Array.each(records, function(model) {
				        data.push(model.data);
				    });
					if(data.length==0){
						Ext.Msg.alert("系统提示","没有修改任何数据！");
					}else{
						//将数据转换为json格式
						var str = Ext.JSON.encode(data);
						Ext.Ajax.request({
						    url: 'bookInfo!update',
						    params: {
						        data: str
						    },
						    success:function(){
						    	Ext.Msg.alert("系统提示","数据修改成功！");
						    	//前台确认修改
						    	Ext.Array.each(records, function(model) {
							        model.commit();
							    });
						    },
						    failure:function(){
						    	Ext.Msg.alert("系统提示","数据修改失败！");
						    }
						});
					}
					
						
					

				}
			},
			//导出数据到excel表格
			"bookInfoPanel button[id=book-export]":{
				click:function(btn){
					window.location.href = 'bookInfo!export';
				}
			},
			//导入表格数据
			"bookInfoPanel button[id=book-import]":{
				click:function(btn){
					var importForm = new Ext.form.Panel({
						width:250,
						height:150,
						padding:"20 10 0 10",
						frame:true,
						items:[{
							xtype:"filefield",
							fieldLabel:"请选择文件",
							labelWidth:80,
							name:"importFile",
							id:"import-file",
							allowBlank: false,
							buttonText:"浏览..."
						},{
							xtype:"label",
							id:"import-msg"
						}],
						buttons:[{
							xtype:"button",
							text:"确认",
							buttonAlign:"center",
							width:80,
							handler:function(btn){
								var basicForm = btn.ownerCt.ownerCt.getForm();
								var filename = Ext.getCmp("import-file").getValue();
								if (/.*xls$/.test(filename)){
									basicForm.submit({
										url:"bookInfo!impot",
										success:function(){},
										failure:function(){}
									});
									Ext.getCmp("import-win").hide();
									var grid = Ext.getCmp("book-search-btn").ownerCt.ownerCt;
									grid.getStore().load();
									Ext.Msg.alert("系统提示","导入数据成功！");
								}else{
									Ext.getCmp("import-msg").setText("<font color=red>请选择xls格式的文件！</font>",false);
								}
							}
						}]
						
					});
					new Ext.window.Window({
						title:"导入数据",
						width:300,
						height:150,
						modal:true,
						id:"import-win",
						layout:"fit",
						closeAction:"destroy",
						items:[importForm]
					}).show();
				}
			},
			//搜索
			"bookInfoPanel textfield[id=book-search-text]":{
				change:function(obj){
					//得到表格对象
					var grid = obj.ownerCt.ownerCt;
					//得到数据集
					var store = grid.getStore();
					//如果搜索框中的内容为空
					if(obj.getValue()==""){
						//将store的url还原
						store.getProxy().url = "bookInfo";
						//重新加载数据
						store.load();
					}
				}
			},
			"bookInfoPanel button[id=book-search-btn]":{
				click:function(btn){
					//得到表格对象
					var grid = btn.ownerCt.ownerCt;
					//得到store数据集
					var store = grid.getStore();
					//得到输入的内容
					var text = Ext.getCmp("book-search-text").getValue();
					if(text==""){
						Ext.Msg.alert("系统提示","请输入您要查找的内容！");
					}else{
						//想后台发送搜索请求
						store.getProxy().url = "bookInfo!search?keyword="+text;
						//加载后台返回的数据
						store.load();
					}
					
				}
			}
			
		});
		
	},
	views:[	
		"BookPanel",
		"BookInfoPanel",
		"Content"
	],
	stores:[
		"BookInfoStore",
		"BookStore"
	],
	models:[
		"BookInfoModel"
	]
	
});