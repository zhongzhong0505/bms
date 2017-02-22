//读者管理的数据集合类
Ext.define("AM.store.ReaderStore",{
	extend:'Ext.data.TreeStore',
	autoLoad: true,
	storeId:'r_s',
	root : {
		children : [ {
			text : "基本信息管理",
			id:"user-info",
			iconCls:"application_xp",
			leaf : true
		}, {
			text : "借阅历史",
			id:"borrow-history",
			iconCls:"date",
			leaf : true
		} ]
	}
});