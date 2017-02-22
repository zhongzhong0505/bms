Ext.define("AM.controller.MainController",{
	
	extend:'Ext.app.Controller',
	init:function(){
		this.control({
			
		});
	},
	views:[	
		"Menu",
		"BookPanel",
		"ReaderPanel",
		"StatisticPanel",
		"Content"
	],
	stores:[
		"BookStore",
		"ReaderStore",
		"StatisticStore"
	],
	models:[
	]
	
});