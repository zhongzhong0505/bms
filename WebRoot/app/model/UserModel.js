
/**
 * 读者管理
 */
Ext.define("AM.model.UserModel",{
	extend:'Ext.data.Model',
	idProperty:"user_id",//主属性，不配置就无法取得修改的数据，默认值为id
	fields:[
		{name:'user_id',	 type:"string",sortable:true},//读者编号
		{name:'user_card',	 type:"string",sortable:true},//读者卡号
		{name:'user_name',	 type:"string",sortable:true},//读者姓名
		{name:'user_sex',	 type:"string",sortable:true},//读者性别
		{name:'user_email',	 type:"string",sortable:true},//读者邮箱
		{name:'user_phone',	 type:"string",sortable:true},//读者电话号码
		{name:'user_date',	 type:"string",sortable:true},//出生日期
		{name:'user_status', type:"string",sortable:true},//状态
		{name:'user_faculty',type:"string",sortable:true}//院系
		]
});