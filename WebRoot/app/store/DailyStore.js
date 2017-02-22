//日常操作的数据集合
Ext.define("AM.store.DailyStore",{
	extend:'Ext.data.TreeStore',
	autoLoad: true,
	storeId:'d_s',
	root : {
		children : [  {
			id:"borrow-search",
			text : "借阅查询",
			iconCls:"date",
			leaf : true
			},{
				id:"borrow-register",
				text : "借阅登记",
				iconCls:"computer_go",
				leaf : true
			},{
				id:"return-register",
				text : "还书登记",
				iconCls:"computer_go",
				leaf : true
			}/*,{
				id:"memo-mana",
				text : "备忘管理",
				iconCls:"book_open",
				leaf : true
			} */]
		}
});