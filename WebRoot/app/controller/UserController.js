
//增加用户信息的panel
var formpanel = new Ext.form.Panel({
						/*width : 350,
						height : 400,*/
						frame:true,
						
						bodyStyle : "padding:10 5 5 45",
						defaults:{
							//disabled:true,
							allowBlank:false,//不能为空
							msgTarget:'side',
							labelAlign:'left',
							autoFitErrors: false//输入错误的时候不收缩输入框
						},
						items:[{
							fieldLabel:"读者卡号",
							xtype:"textfield",
							labelWidth:80,
							name:"user_card"
						},{
							fieldLabel:"读者姓名",
							xtype:"textfield",
							labelWidth:80,
							name:"user_name"
						},{
							fieldLabel:"读者性别",
							xtype:"combo",
							labelWidth:80,
							emptyText :"请选择",
							store : new Ext.data.SimpleStore({  
	                            fields : ['value', 'text'],  
	                            data : [['V1', '男'],  
	                                    ['V2', '女'] 
	                                   ]  
	                        }),  
		                    editable : false,  
		                    triggerAction:'all',  
		                    valueField : 'text',  
		                    displayField : 'text',
		                    name:"user_sex"
		                   
						},{
							fieldLabel:"读者邮箱",
							xtype:"textfield",
							labelWidth:80,
							name:"user_email"
						},{
							fieldLabel:"电话号码",
							xtype:"textfield",
							labelWidth:80,
							name:"user_phone"
						},{
							fieldLabel:"出生日期",
							xtype:"datefield",
							format:"Y年m月d日",
							labelWidth:80,
							name:"user_date"
						},{
							fieldLabel:"所在院系",
							xtype:"textfield",
							labelWidth:80,
							name:"user_faculty"
						}],
						buttonAlign:"center",
						buttons:[{
							text:"确认添加",
							handler:function(buttonObj){
								var basic = buttonObj.ownerCt.ownerCt.getForm();
								
								basic.submit({
									clientValidation: true,
									url:"user!add",
									success:function(){
										Ext.Msg.alert("系统提示","<img src='images/ok.png'>增加用户成功！");
										buttonObj.ownerCt.ownerCt.ownerCt.hide();
									},
									failure:function(){
										Ext.Msg.alert("系统提示","增加用户失败！");
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
					
var user_win = new Ext.window.Window({
	title:"添加读者信息",
	width:350,
	height:350,
	resizable:false,
	closeAction:"hide",
	modal:true,
	layout:"fit",
	items:[formpanel]
});
					


/**
 * 用户管理的控制器
 */
Ext.define("AM.controller.UserController",{
	
	extend:'Ext.app.Controller',
	init:function(){
		this.control({
			"readerPanel":{
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
						myMask.hide();
						//设置标题
						tabpanel.setTitle(record.get("text"));
						//设置选中的面板为活动窗口
						tabpanel.setActiveTab(record.get("id")+"-panel");
					},1000);	
				}
			},
			//删除==============================================
			"userPanel button[id=user-del]":{
				
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
					Ext.Msg.confirm("提示","确认删除？",function(btn){
						if(btn=="yes"){
							//1.先得到id的数组，
	        				var st = grid.getStore();//得到数据集
	        				var ids = [];
	        				Ext.Array.each(data,function(record){
	        					ids.push(record.get('user_id'));
	        					//2后台操作删除
	            				
	                			Ext.Ajax.request({
	            					url:'user!delete',
	            					params:{ids:ids.join(",")},
	            					method:'POST',
	            					timeout:2000,
	            					success:function(response,opts){
	            						//前台删除
	            						Ext.Array.each(data,function(record){
	            							st.remove(record);
	            						});
	            						st.load();
	            						Ext.Msg.alert("提示","删除数据成功！");
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
			"userPanel button[id=user-update]":{
				click:function(buttonObj){
					//得到表格对象
					var grid = buttonObj.ownerCt.ownerCt;
					//得到数据集
					var store = grid.getStore();
					
					store.load();
				}
			},
			//增加读者
			"userPanel button[id=user-add]":{
				click:function(buttonObj){
					if(user_win){
						user_win.show();
					}
				}
			},
			//搜索功能
			"userPanel textfield[id=user-search-text]":{
				change:function(obj){
					//得到表格对象
					var grid = obj.ownerCt.ownerCt;
					//得到数据集
					var store = grid.getStore();
					//如果搜索框中的内容为空
					if(obj.getValue()==""){
						//将store的url还原
						store.getProxy().url = "user";
						//重新加载数据
						store.load();
					}
				}
			},
			"userPanel button[id=user-search-btn]":{
				click:function(btn){
					//得到表格对象
					var grid = btn.ownerCt.ownerCt;
					//得到store数据集
					var store = grid.getStore();
					//得到输入的内容
					var text = Ext.getCmp("user-search-text").getValue();
					if(text==""){
						Ext.Msg.alert("提示","请输入您要查找的内容！");
					}else{
						//想后台发送搜索请求
						store.getProxy().url = "user!search?keyword="+text;
						//加载后台返回的数据
						store.load();
					}
					
				}
			},
			//导出表格
			"userPanel button[id=user-export]":{
				click:function(btn){
					window.location.href = 'user!export';
				}
			},
			//导入表格数据
			"userPanel button[id=user-import]":{
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
										url:"user!impot",
										timeout: 60000,
										success:function(){
											Ext.Msg.alert("系统提示","导入数据成功！");
										},
										failure:function(){
											Ext.Msg.alert("系统提示","导入数据失败！");
										}
									});
									Ext.getCmp("import-win").hide();
									var grid = Ext.getCmp("user-search-btn").ownerCt.ownerCt;
									grid.getStore().load();
									//console.info(grid.getStore());
									//Ext.Msg.alert("系统提示","导入数据成功！");
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
			
			//修改
			"userPanel button[id=user-save]":{
				click:function(btn){
					//得到表格对象
					var grid = btn.ownerCt.ownerCt;
					//得到数据集
					var store = grid.getStore();
					var records = store.getUpdatedRecords();
			
					var data = [];
					Ext.Array.each(records, function(model) {
				        data.push(model.data);
				    });
					if(data.length==0){
						Ext.Msg.alert("提示","没有修改任何数据！");
					}else{
						//将数据转换为json格式
						var str = Ext.JSON.encode(data);
						Ext.Ajax.request({
						    url: 'user!update',
						    params: {
						        data: str
						    },
						    success:function(){
						    	Ext.Msg.alert("提示","数据修改成功！");
						    	//前台确认修改
						    	Ext.Array.each(records, function(model) {
							        model.commit();
							    });
						    },
						    failure:function(){
						    	Ext.Msg.alert("提示","数据修改失败！");
						    }
						});
					}
					
				}
			}
		});
	},
	views:[	
		"UserPanel",
		"Content",
		"ReaderPanel"
	],
	stores:[
		"UserStore"
	],
	models:[
		"UserModel"
	]
	
});