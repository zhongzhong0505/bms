//图书信息的数据集合类
Ext.define("AM.store.BookInfoStore",{
	extend:'Ext.data.Store',
	autoLoad: true,
	storeId:'b_i_s',
	model:'AM.model.BookInfoModel',
    proxy: {
        type: 'ajax',
        url: 'bookInfo',
       	reader:{
				type:'json',
				root:'books',
				//获取数据总数  
	            totalProperty:'totalCount'
		}
    },
    pageSize:22
	
});