/**
 * 借阅历史
 */
Ext.define("AM.model.BorrowHistoryModel",{
	extend:'Ext.data.Model',
	fields:[
		{name:'user_card',type:"string",sortable:true},//读者卡号
		{name:'user_name',type:"string",sortable:true},//读者姓名
		{name:'borrow_book_name',type:"string",sortable:true},//所借书名
		{name:'borrow_book_date',type:"date",sortable:true},//借书日期
		{name:'return_book_date',type:"date",sortable:true},//还书日期
		{name:'user_phone',type:"string",sortable:true},//读者电话号码
		{name:'user_faculty',type:"string",sortable:true}//院系
		]
});