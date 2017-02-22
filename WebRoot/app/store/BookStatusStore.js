//图书状态的数据集合类
Ext.define("AM.store.BookStatusStore",{
	extend:'Ext.data.Store',
	//autoLoad: true,
	storeId:'b_s_s',
	model:"AM.model.BookChartsModel",
	proxy:{
		type:"ajax",
		url:"bookStatus",
		reader:{
			type:"json"
		}
	}
	
});