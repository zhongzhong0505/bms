//图书管理的数据集合类
Ext.define("AM.store.BookStore",{
	extend:'Ext.data.TreeStore',
	autoLoad: true,
	storeId:'b_s',
	root : {
		children : [ {
			text : "书籍管理",
			leaf : true,
			id:"book-info"
		} ]
	}
});