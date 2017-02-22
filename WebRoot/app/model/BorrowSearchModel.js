
/**
 * 借阅查询
 */
Ext.define("AM.model.BorrowSearchModel",{
	extend:'Ext.data.Model',
	
	fields:[
		{name:'book_id',type:"int",sortable:true},//图书id
		{name:'book_type',type:"string",sortable:true},//图书类别
		{name:'book_name',type:"string",sortable:true},//图书名称
		{name:'book_isbn',type:"string",sortable:true},//图书isbn
		{name:'book_count',type:"int",sortable:true},//图书数量
		{name:'user_card',type:"string",sortable:true},//读者id
		{name:'user_name',type:"string",sortable:true},//读者姓名
		{name:'user_sex',type:"string",sortable:true},//读者性别
		
		{name:'borrow_date',type:"date",sortable:true},//借出日期
		{name:'return_date',type:"date",sortable:true},//应还日期
		{name:'phone_number',type:"string",sortable:true}//联系电话
		]
});