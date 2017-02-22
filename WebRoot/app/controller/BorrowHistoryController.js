





Ext.define("AM.controller.BorrowHistoryController",{
	
	extend:'Ext.app.Controller',
	init:function(){
		
		//alert(Ext.getCmp("daily-panel"));
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
			//刷新
			"borrowHistoryPanel button[id=history-update]":{
				click:function(btn){
					//得到表格对象
					var grid = btn.ownerCt.ownerCt;
					//得到数据集
					var store = grid.getStore();
					
					
					store.load();
				}
			},
			//搜索
			"borrowHistoryPanel textfield[id=history-search-text]":{
				change:function(obj){
					//得到表格对象
					var grid = obj.ownerCt.ownerCt;
					//得到数据集
					var store = grid.getStore();
					//如果搜索框中的内容为空
					if(obj.getValue()==""){
						//将store的url还原
						store.getProxy().url = "historys";
						//重新加载数据
						store.load();
					}
				}
			},
			"borrowHistoryPanel button[id=history-search-btn]":{
				click:function(btn){
					//得到表格对象
					var grid = btn.ownerCt.ownerCt;
					//得到store数据集
					var store = grid.getStore();
					//得到输入的内容
					var text = Ext.getCmp("history-search-text").getValue();
					if(text==""){
						Ext.Msg.alert("系统提示","请输入您要查找的内容！");
					}else{
						//想后台发送搜索请求
						store.getProxy().url = "historys!search?keyword="+text;
						//加载后台返回的数据
						store.load();
					}
					
				}
			}
			
		});
		
	},
	views:[	
		"ReaderPanel",
		"BorrowHistoryPanel",
		"Content"
		
	],
	stores:[
		"BorrowHistoryStore"
	],
	models:[
		"BorrowHistoryModel"
	]
	
});