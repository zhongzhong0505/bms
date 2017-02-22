//备忘管理的数据集合
Ext.define("AM.store.MemoStore",{
	extend:'Ext.data.Store',
	autoLoad: true,
	storeId:'m_s',
	model:'AM.model.MemoModel',
    proxy: {
        type: 'ajax',
        url: '',
       	reader:{
				type:'json',
				root:'memos',
				//获取数据总数  
	            totalProperty:'totalCount'
		}
    },
    pageSize:22
	
});