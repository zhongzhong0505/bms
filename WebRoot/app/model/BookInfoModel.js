
/**
 * 图书管理
 */
Ext.define("AM.model.BookInfoModel",{
	extend:'Ext.data.Model',
	idProperty:"book_id",//主属性，不配置就无法取得修改的数据，默认值为id
	fields:[
		{name:'book_id',type:"int",sortable:true},//图书id
		{name:'book_type',type:"string",sortable:true},//图书类别
		{name:'book_name',type:"string",sortable:true},//图书名称
		{name:'book_isbn',type:"string",sortable:true},//图书isbn
		{name:'book_author',type:"string",sortable:true},//作者
		{name:'isBorrow',type:"string",sortable:true},//是否借出
		{name:'book_press',type:"string",sortable:true},//出版社
		{name:'book_press_date',type:"date",sortable:true},//出版日期
		{name:'book_count',type:"int",sortable:true},//数量
		{name:'book_price',type:"string",sortable:true},//图书价格
		{name:'book_location',type:"string",sortable:true},//书籍位置
		{name:'book_note',type:"string",sortable:true}//备注信息
		]
});
