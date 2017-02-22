Ext.define("AM.controller.BookStatisticController",{
	
	extend:'Ext.app.Controller',
	init:function(){
		
		//alert(Ext.getCmp("daily-panel"));
		this.control({
			"statisticPanel":{
				itemdblclick:function(tree,record,item,index,e,eOpts){
					
					//得到tappanel
					var tabpanel = Ext.getCmp("content-panel");
					
					var win = Ext.getCmp(record.get("id")+"-panel");
					
					
					win.getComponent(0).store.load();
					//设置标题
					//tabpanel.setTitle(record.get("text"));
					Ext.getCmp(record.get("id")+"-panel").show();
					//设置选中的面板为活动窗口
					tabpanel.setActiveTab(record.get("id")+"-panel");
					
				}
			}
			
		});
		
	},
	views:[	
		"StatisticPanel",
		"BookStatusPanel",
		"Content",
		"BookChartsPanel"
		
	],
	stores:[
		"BookStatusStore",
		"StatisticStore",
		"BookChartsStore"
	],
	models:[
		
	]
	
});