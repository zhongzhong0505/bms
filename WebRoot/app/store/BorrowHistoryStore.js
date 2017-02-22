//借阅历史的数据集合类
Ext.define("AM.store.BorrowHistoryStore",{
	extend:'Ext.data.Store',
	autoLoad: true,
	
	storeId:'b_s_s',
	model:'AM.model.BorrowHistoryModel',
	 proxy: {
	        type: 'ajax',
	        url: 'historys',
	       	reader:{
					type:'json',
					root:'historys',
					//获取数据总数  
		            totalProperty:'totalCount'
			}
	    },
	    pageSize:22
	
});