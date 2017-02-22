Ext.onReady(function(){
	Ext.window.MessageBox.prototype.buttonText = { 
            ok: "确定", 
            cancel: "取消", 
            yes: "是", 
            no: "否" 
 }; 
Ext.MessageBox = Ext.Msg = new Ext.window.MessageBox(); 
	Ext.QuickTips.init();//初始化
	//启用loader
	Ext.Loader.setConfig({
		enabled:true
	});

	Ext.application({
		name:'AM',	//应用的名字
		appFolder:"app",//应用的目录
		launch:function(){//当前页面加载完成执行的函数
			Ext.create("Ext.container.Viewport",{
				//border:0,
				layout:'border',//布局
				items:[{
					xtype:"menuPanel",
					region:"west"
					
				},{
					id:"content-panel",
					xtype:"contentPanel",
					region:"center"
				}]
			});
			
		},
		controllers:[
			'MainController',
			"DailyController",
			"MemoController",
			"UserController",
			"BookController",
			"BookStatisticController",
			"BorrowHistoryController",
			"BorrowSearchController"
		]
	});
	
});