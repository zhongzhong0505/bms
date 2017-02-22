//增加备忘的panel
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
						fieldLabel:"当前状态",
						xtype:"combo",
						labelWidth:80,
						emptyText :"请选择",
						store : new Ext.data.SimpleStore({  
                            fields : ['value', 'text'],  
                            data : [['V1', '完成'],  
                                    ['V2', '进行中'],  
                                    ['V3', '未开始'] 
                                   ]  
                        }),  
	                    editable : false,  
	                    triggerAction:'all',  
	                    valueField : 'value',  
	                    displayField : 'text' 
					},{
						fieldLabel:"备忘日期",
						xtype:"datefield",
						format:"Y年m月d日",
						labelWidth:80
					},{
						fieldLabel:"是否提示",
						xtype:"combo",
						labelWidth:80,
						emptyText :"请选择",
						store : new Ext.data.SimpleStore({  
                            fields : ['value', 'text'],  
                            data : [['V1', '是'],  
                                    ['V2', '否'] 
                                   ]  
                        }),  
	                    editable : false,  
	                    triggerAction:'all',  
	                    valueField : 'value',  
	                    displayField : 'text' 
					},{
						fieldLabel:"提示类型",
						xtype:"combo",
						labelWidth:80,
						emptyText :"请选择",
						store : new Ext.data.SimpleStore({  
                            fields : ['value', 'text'],  
                            data : [['V1', '邮件'],  
                                    ['V2', '电话'] 
                                   ]  
                        }),  
	                    editable : false,  
	                    triggerAction:'all',  
	                    valueField : 'value',  
	                    displayField : 'text' 
					},{
						fieldLabel:"是否涉及读者",
						xtype:"combo",
						labelWidth:80,
						emptyText :"请选择",
						store : new Ext.data.SimpleStore({  
                            fields : ['value', 'text'],  
                            data : [['V1', '是'],  
                                    ['V2', '否'] 
                                   ]  
                        }),  
	                    editable : false,  
	                    triggerAction:'all',  
	                    valueField : 'value',  
	                    displayField : 'text' 
					},{
						fieldLabel:"备忘内容",
						xtype:"textarea",
						labelWidth:80
					}],
					buttonAlign:"center",
					buttons:[{
						text:"确认添加",
						handler:function(){
							//ajax请求
						}
					},{
						text:"取消",
						handler:function(buttonObj){
							buttonObj.ownerCt.ownerCt.ownerCt.hide();
						}
					}]
	});
var memo_win = new Ext.window.Window({
	title:"添加备忘",
	width:380,
	height:300,
	resizable:false,
	layout:"fit",
	closeAction:"hide",
	modal:true,
	items:[formpanel]
});

/**
 * 备忘管理的控制器
 */
Ext.define("AM.controller.MemoController",{
	
	extend:'Ext.app.Controller',
	init:function(){
		this.control({
			//删除操作=======================================
			"memoPanel button[id=memo-del]":{
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
	    						var ids = [];
	            				var st = grid.getStore();//得到数据集
		        				Ext.Array.each(data,function(record){
		        					
		        					ids.push(record.get('memo_id'));
		        							        					
			        			});
		        				//2后台操作删除
	            				
	    	        			Ext.Ajax.request({
	    	    					url:'',
	    	    					params:{ids:ids.join(",")},
	    	    					method:'POST',
	    	    					timeout:2000,
	    	    					success:function(response,opts){
	    	    						//前台删除
	    	    						Ext.Array.each(data,function(record){
	    	    							st.remove(record);
	    	    						});
	    	    						st.load();
	    	    						Ext.Msg.alert("系统提示","删除数据成功！");
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
							}
						});
					}
					
				}
			},
		
		//刷新操作=================================
		"memoPanel button[id=memo-update]":{
			click:function(buttonObj){
				//得到表格对象
				var grid = buttonObj.ownerCt.ownerCt;
				//得到数据集
				var store = grid.getStore();
			
				store.load();
			}
		},
		//添加备忘==========================================
		"memoPanel button[id=memo-add]":{
			click:function(buttonObj){
				
				if(memo_win){
					memo_win.show();
				}
				
				
			}
		},
		//保存修改==========================================
		"memoPanel button[id=memo-save]":{
			click:function(buttonObj){
				//得到表格对象
				var grid = buttonObj.ownerCt.ownerCt;
				//得到数据集
				var store = grid.getStore();
				
				//得到修改过的记录
				var records = store.getUpdatedRecords();
				
				if(records.length==0){
					Ext.Msg.alert("系统提示","没有修改任何数据！");
				}else{
					this.store.setProxy({
						type: 'ajax',
				        url : '',
				        reader: {
				            type: 'json',
				            root: 'users',
				            //获取数据总数  
				            totalProperty:'totalCount'
				        }
					});
					this.store.sync();
					Ext.Array.each(records,function(model){
						model.commit();
						
					});
					Ext.Msg.alert("系统提示","信息保存成功！");
					
				}

			}
		},
		//搜索功能
		"memoPanel textfield[id=memo-search-text]":{
			change:function(obj){
				//得到表格对象
				var grid = obj.ownerCt.ownerCt;
				//得到数据集
				var store = grid.getStore();
				//如果搜索框中的内容为空
				if(obj.getValue()==""){
					//将store的url还原
					store.getProxy().url = "";
					//重新加载数据
					store.load();
				}
			}
		},
		"memoPanel button[id=memo-search-btn]":{
			click:function(btn){
				//得到表格对象
				var grid = btn.ownerCt.ownerCt;
				//得到store数据集
				var store = grid.getStore();
				//得到输入的内容
				var text = Ext.getCmp("memo-search-text").getValue();
				if(text==""){
					Ext.Msg.alert("系统提示","请输入您要查找的内容！");
				}else{
					//想后台发送搜索请求
					store.getProxy().url = "?keyword="+text;
					//加载后台返回的数据
					store.load();
				}
				
			}
		}
		
		});
	},
	views:[	
		"MemoPanel"
	],
	stores:[
		"MemoStore"
	],
	models:[
		"MemoModel"
	]
	
});