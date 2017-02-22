//统计分析的数据集合类
Ext.define("AM.store.StatisticStore",{
	extend:'Ext.data.TreeStore',
	autoLoad: true,
	storeId:'s_s',
	root : {
		children : [ {
			text : "图书类别统计",
			iconCls:"chart_bar",
			id:"book-charts",
			leaf : true
		}, {
			text : "图书状态统计",
			iconCls:"chart_pie",
			id:"book-status",
			leaf : true
		}]
	}
});