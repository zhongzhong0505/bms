//图书类别的数据集合类
Ext.define("AM.store.BookChartsStore",{
	extend:'Ext.data.Store',
	//autoLoad: true,
	storeId:'b_s_s',
	model:"AM.model.BookChartsModel",
	proxy: {
	        type: 'ajax',
	        url: 'bookCharts',
	       	reader:{
					type:'json'
			}
	}
	
});