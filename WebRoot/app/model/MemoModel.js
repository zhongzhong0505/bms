
/**
 * 备忘查询
 */
Ext.define("AM.model.MemoModel",{
	extend:'Ext.data.Model',
	fields:[
		{name:'memo_id',type:"int",sortable:true},//备忘编号
		{name:'now_status',type:"string",sortable:true},//当前状态
		{name:'memo_date',type:"date",sortable:true},//备忘日期
		{name:'memo_info',type:"string",sortable:true},//备忘内容
		{name:'isTip',type:"string",sortable:true},//是否提示
		{name:'tip_type',type:"string",sortable:true},//提示类型
		{name:'of_user',type:"string",sortable:true},//是否涉及读者
	
		
		]
});