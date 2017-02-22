//借阅查询的数据集合类
Ext.define("AM.store.BorrowSearchStore",{
	extend:'Ext.data.Store',
	autoLoad: true,
	
	storeId:'b_s_s',
	model:'AM.model.BorrowSearchModel',
    proxy: {
        type: 'ajax',
        url: 'borrowSearch',
       	reader:{
				type:'json',
				root:'borrows',
				//获取数据总数  
	            totalProperty:'totalCount'
		}
    },
    pageSize:22
	
});