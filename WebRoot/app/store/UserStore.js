//读者管理的数据集合类
Ext.define("AM.store.UserStore",{
	extend:'Ext.data.Store',
	autoLoad: true,
	storeId:'u_s',
    autoLoad: true,
	model:'AM.model.UserModel',
    proxy: {
        type: 'ajax',
        url: 'user',
       	reader:{
				type:'json',
				root:'users',
				//获取数据总数  
	            totalProperty:'totalCount'
		}
    },
    pageSize:22
	
});